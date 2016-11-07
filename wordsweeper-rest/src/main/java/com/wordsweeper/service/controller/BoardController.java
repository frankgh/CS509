package com.wordsweeper.service.controller;

import com.wordsweeper.service.model.Game;
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
 * The board controller
 * <p>
 * Created by francisco on 11/7/16.
 *
 * @author francisco
 */
@Path("/board")
public class BoardController {


    @GET
    @Path("/reposition/{gameId}/{playerName}/{rowChange}/{columnChange}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response repositionBoard(@PathParam("gameId") String gameId,
                                    @PathParam("playerName") String playerName,
                                    @PathParam("rowChange") String rowChange,
                                    @PathParam("columnChange") String columnChange) {

        GameDao gameDao = new GameDaoImpl();
        Game game = gameDao.findByGameId(gameId);

        if (game == null || game.ended()) {
            return Response
                    .ok(new RequestError(RequestError.NO_SUCH_GAME_EXISTS, "The game does not exist"))
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }

        if (!game.containsPlayer(playerName)) {
            return Response
                    .ok(new RequestError(RequestError.NO_SUCH_PLAYER_EXISTS, "No such player exists in the game"))
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }

        blaaahh

        return null;
    }
}
