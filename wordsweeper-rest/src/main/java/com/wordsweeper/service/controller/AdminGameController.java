package com.wordsweeper.service.controller;

import com.wordsweeper.service.repository.GameDao;
import com.wordsweeper.service.repository.GameDaoImpl;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by francisco on 10/4/16.
 *
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
}
