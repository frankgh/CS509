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
 * This class tells game controller from admin client side..
 * @author francisco
 */
@Path("/admin/game")
public class AdminGameController {

    /**
     * Return a list of all games in the server.
     *
     * @return the list of games
     */
    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response list() {
        GameDao gameDao = new GameDaoImpl();

        return Response /* Return response with the game list */
                .ok(gameDao.findAll())
                .build();
    }

    /**
     * Show Game Status for Administrative Clients
     *
     * @param gameId the game id
     * @return the response
     */
    @GET
    @Path("/{gameId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("gameId") String gameId) {

        Game game = new GameDaoImpl().findByGameId(gameId);

        if (game == null || game.ended()) {
            return Response
                    .ok(new RequestError(RequestError.NO_SUCH_GAME_EXISTS, "The game does not exist"))
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }

        return Response.ok(game).build(); /* Return response with the game object */
    }
}
