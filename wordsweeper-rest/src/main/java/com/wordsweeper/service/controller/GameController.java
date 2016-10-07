package com.wordsweeper.service.controller;

import com.wordsweeper.service.model.Game;
import com.wordsweeper.service.model.Player;
import com.wordsweeper.service.repository.GameDao;
import com.wordsweeper.service.repository.GameDaoImpl;
import org.apache.commons.lang3.StringUtils;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by francisco on 9/15/16.
 */
@Path("/game")
public class GameController {

    @GET
    @Path("/create/{playerName}{p:(/password/)?}{password:([^/]+)?}")
    @Produces(MediaType.APPLICATION_JSON)
    public Game create(@PathParam("playerName") String playerName, @PathParam("password") String password) {

        Player player = new Player(playerName); /* create a new player for the board */
        Game game = new Game(player, StringUtils.isBlank(password) ? null : password); /* create the game */

        GameDao gameDao = new GameDaoImpl(); /* Data-access (persistence) object for Game */
        gameDao.save(game); /* persist the game */

        return game;
    }

    @GET
    @Path("/join/{gameId}/{playerName}{p:(/password/)?}{password:([^/]+)?}")
    @Produces(MediaType.APPLICATION_JSON)
    public Game join(@PathParam("gameId") String gameId,
                     @PathParam("playerName") String playerName,
                     @PathParam("password") String password) {

        GameDao gameDao = new GameDaoImpl(); /* Data-access (persistence) object for Game */
        Game game = gameDao.findByGameId(gameId);

        if (game == null || game.ended() || game.isLocked()) {
            return null;
        }

        boolean addPlayer = game.addPlayer(playerName,
                StringUtils.isBlank(password) ? null : password); /* join the game */

        if (addPlayer) {
            game.getBoard().reset(); /* finally, reset the board */
            gameDao.save(game);
        }

        return game; /* Return response with the game object */
    }

    @GET
    @Path("/lock/{gameId}/{playerName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Game lock(@PathParam("gameId") String gameId, @PathParam("playerName") String playerName) {

        GameDao gameDao = new GameDaoImpl();
        Game game = gameDao.findByGameId(gameId);

        if (game == null) {
            return null;
        }

        if (StringUtils.equals(game.getManagingPlayerName(), playerName)) {
            game.lock(playerName);
            gameDao.save(game);
        }

        return game;
    }

    @GET
    @Path("/exit/{gameId}/{playerName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response exit(@PathParam("gameId") String gameId, @PathParam("playerName") String playerName) {

        // TODO: actually load the game here
        Game game = new Game(null);

        game.removePlayer(playerName);

        if (game.isEmpty()) {
            game.end();
        }

        // TODO: persist game

        return Response /* Return response with the game object */
                .ok(game)
                .build();
    }

    @GET
    @Path("/reset/{gameId}/{playerName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response reset(@PathParam("gameId") String gameId, @PathParam("playerName") String playerName) {

        GameDao gameDao = new GameDaoImpl();
        Game game = gameDao.findByGameId(gameId);

        if (game == null) {
            return null;
        }

        if (StringUtils.equals(game.getManagingPlayerName(), playerName)) {
            game.reset(); /* resets board and player scores */
            gameDao.save(game);
        }

        return Response /* Return response with the game object */
                .ok(game)
                .build();
    }
}
