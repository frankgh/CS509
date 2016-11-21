package com.wordsweeper.service.controller;

import com.wordsweeper.service.model.Game;
import com.wordsweeper.service.model.Player;
import com.wordsweeper.service.model.RequestError;
import com.wordsweeper.service.model.Word;
import com.wordsweeper.service.repository.GameDao;
import com.wordsweeper.service.repository.GameDaoImpl;
import com.wordsweeper.service.util.Util;
import com.wordsweeper.service.util.WordTable;
import org.apache.commons.lang3.StringUtils;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * The game controller
 * <p>
 * Created by francisco on 9/15/16.
 *
 * @author francisco
 */
@Path("/game")
public class GameController {

    /**
     * Creates a game in the persistence layer with the provided playerName and optional password
     *
     * @param playerName the name of the player
     * @param password   (optional) an optional password for the game
     * @return the Game object
     */
    @GET
    @Path("/create/{playerName}{p:(/password/)?}{password:([^/]+)?}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@PathParam("playerName") String playerName, @PathParam("password") String password) {

        Player player = new Player(playerName); /* create a new player for the board */
        Game game = new Game(player, StringUtils.isBlank(password) ? null : password); /* create the game */

        GameDao gameDao = new GameDaoImpl(); /* Data-access (persistence) object for Game */
        gameDao.save(game); /* persist the game */

        return Response.ok(game).build();
    }

    /**
     * Join an existing game
     *
     * @param gameId     the unique ID of the existing game
     * @param playerName the name of the joining player
     * @param password   (optional) if the game is password protected, supply the password of the game
     * @return the updated game status if success, a request error otherwise
     */
    @GET
    @Path("/join/{gameId}/{playerName}{p:(/password/)?}{password:([^/]+)?}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response join(@PathParam("gameId") String gameId,
                         @PathParam("playerName") String playerName,
                         @PathParam("password") String password) {

        GameDao gameDao = new GameDaoImpl(); /* Data-access (persistence) object for Game */
        Game game = gameDao.findByGameId(gameId);

        if (game == null || game.ended()) {
            return Response
                    .ok(new RequestError(RequestError.NO_SUCH_GAME_EXISTS, "The game does not exist"))
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }

        if (game.isLocked()) {
            return Response
                    .ok(new RequestError(RequestError.GAME_IS_LOCKED, "The game is locked"))
                    .status(Response.Status.FORBIDDEN)
                    .build();
        }

        if (game.isPasswordProtected() && !StringUtils.equals(password, game.getPassword())) {
            return Response
                    .ok(new RequestError(RequestError.INVALID_PASSWORD, "The game is password protected"))
                    .status(Response.Status.FORBIDDEN)
                    .build();
        }

        boolean addPlayer = game.addPlayer(playerName,
                StringUtils.isBlank(password) ? null : password); /* join the game */

        if (addPlayer) {
            // game.getBoard().reset(); /* finally, reset the board */
            game.resetPlayersScores(); /* and reset player scores */
            gameDao.save(game);
        } else {
            return Response
                    .ok(new RequestError(RequestError.PLAYER_ALREADY_EXISTS,
                            "There is a player in the game with the same name"))
                    .status(Response.Status.BAD_REQUEST)
                    .build();
        }

        return Response.ok(game).build(); /* Return response with the game object */
    }

    /**
     * Lock the game.
     *
     * @param gameId     the unique ID of the existing game
     * @param playerName the name of the managing player name
     * @return the updated game status if success, a request error otherwise
     */
    @GET
    @Path("/lock/{gameId}/{playerName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response lock(@PathParam("gameId") String gameId, @PathParam("playerName") String playerName) {

        GameDao gameDao = new GameDaoImpl();
        Game game = gameDao.findByGameId(gameId);

        if (game == null || game.ended()) {
            return Response
                    .ok(new RequestError(RequestError.NO_SUCH_GAME_EXISTS, "The game does not exist"))
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }

        if (game.isLocked()) {
            return Response
                    .ok(new RequestError(RequestError.GAME_IS_LOCKED, "The game is already locked"))
                    .status(Response.Status.FORBIDDEN)
                    .build();
        }

        if (StringUtils.equals(game.getManagingPlayerName(), playerName)) {
            game.lock();
            gameDao.save(game);
        } else {
            return Response
                    .ok(new RequestError(RequestError.UNAUTHORIZED, "You are not authorized to perform this task"))
                    .status(Response.Status.UNAUTHORIZED)
                    .build();
        }

        return Response.ok(game).build(); /* Return response with the game object */
    }

    /**
     * Exit current game.
     *
     * @param gameId     the unique ID of the existing game
     * @param playerName the name of the player that is leaving the game
     * @return the updated game status if success, a request error otherwise
     */
    @GET
    @Path("/exit/{gameId}/{playerName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response exit(@PathParam("gameId") String gameId, @PathParam("playerName") String playerName) {

        GameDao gameDao = new GameDaoImpl();
        Game game = gameDao.findByGameId(gameId);

        if (game == null || game.ended()) {
            return Response
                    .ok(new RequestError(RequestError.NO_SUCH_GAME_EXISTS, "The game does not exist"))
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }

        if (game.removePlayer(playerName)) {
            if (game.isEmpty()) {
                game.end();
            }

            gameDao.save(game);
        } else {
            return Response
                    .ok(new RequestError(RequestError.NO_SUCH_PLAYER_EXISTS, "No such player exists in the game"))
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }

        return Response.ok(game).build(); /* Return response with the game object */
    }

    /**
     * Reset the game.
     *
     * @param gameId     the unique ID of the existing game
     * @param playerName the name of the managing player name
     * @return the updated game status if success, a request error otherwise
     */
    @GET
    @Path("/reset/{gameId}/{playerName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response reset(@PathParam("gameId") String gameId, @PathParam("playerName") String playerName) {

        GameDao gameDao = new GameDaoImpl();
        Game game = gameDao.findByGameId(gameId);

        if (game == null || game.ended()) {
            return Response
                    .ok(new RequestError(RequestError.NO_SUCH_GAME_EXISTS, "The game does not exist"))
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }

        if (StringUtils.equals(game.getManagingPlayerName(), playerName)) {
            game.reset(); /* resets board and player scores */
            gameDao.save(game);
        } else {
            return Response
                    .ok(new RequestError(RequestError.UNAUTHORIZED, "You are not authorized to perform this task"))
                    .status(Response.Status.UNAUTHORIZED)
                    .build();
        }

        return Response.ok(game).build(); /* Return response with the game object */
    }

    @GET
    @Path("/findword/{gameId}/{playerName}/{word}/{cellPositions}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findWord(@PathParam("gameId") String gameId, @PathParam("playerName") String playerName,
                             @PathParam("word") String word, @PathParam("cellPositions") String cellPositions) {

        if (!WordTable.isWord(word)) {
            return Response
                    .ok(new RequestError(RequestError.INVALID_WORD, "I can't find that word in my dictionary"))
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }

        GameDao gameDao = new GameDaoImpl();
        Game game = gameDao.findByGameId(gameId);

        if (game == null || game.ended()) {
            return Response
                    .ok(new RequestError(RequestError.NO_SUCH_GAME_EXISTS, "The game does not exist"))
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }

        Player player = game.getPlayer(playerName);

        if (player == null) {
            return Response
                    .ok(new RequestError(RequestError.NO_SUCH_PLAYER_EXISTS, "No such player exists in the game"))
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }

        Word wordWrapper = new Word(word, Util.parseCellPositions(cellPositions));

        if (game.validateWord(player, wordWrapper)) {
            int wordScore = game.calculateWordScore(wordWrapper);
            game.getBoard().claimWord(wordWrapper);
            player.setScore(player.getScore() + wordScore);
            player.setLatestScore(wordScore);
            gameDao.save(game);
        } else {
            return Response
                    .ok(new RequestError(RequestError.INVALID_WORD, "Invalid word"))
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }

        return Response.ok(game).build(); /* Return response with the game object */
    }
}
