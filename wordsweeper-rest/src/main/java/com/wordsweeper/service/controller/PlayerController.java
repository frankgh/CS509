package com.wordsweeper.service.controller;

import com.wordsweeper.service.model.Game;
import com.wordsweeper.service.model.Player;
import com.wordsweeper.service.model.RequestError;
import com.wordsweeper.service.repository.GameDao;
import com.wordsweeper.service.repository.GameDaoImpl;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Model class for the WordSweeper Player
 *
 * @author francisco
 */
@Path("/player")
public class PlayerController {

    /**
     * Reposition a Player's board by the provided rowChange and columnChange values.
     *
     * @param gameId       the game id
     * @param playerName   the player name
     * @param rowChange    the row change
     * @param columnChange the column change
     * @return the response
     */
    @GET
    @Path("/reposition/{gameId}/{playerName}/{rowChange}/{columnChange}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response repositionBoard(@PathParam("gameId") String gameId,
                                    @PathParam("playerName") String playerName,
                                    @PathParam("rowChange") int rowChange,
                                    @PathParam("columnChange") int columnChange) {

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

        if (game.repositionBoard(player, rowChange, columnChange)) {
            gameDao.save(game);
        } else {
            return Response
                    .ok(new RequestError(RequestError.BOARD_POSITION_NOT_MODIFIED, "No change in position"))
                    .status(Response.Status.NOT_MODIFIED)
                    .build();
        }

        return Response.ok(game).build(); /* Return response with the game object */
    }
}
