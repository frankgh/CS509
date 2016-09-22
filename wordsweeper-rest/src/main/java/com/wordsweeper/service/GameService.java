package com.wordsweeper.service;

import com.wordsweeper.service.model.Game;
import com.wordsweeper.service.model.Player;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 * Created by francisco on 9/15/16.
 */
@Path("/game")
public class GameService {

    @GET
    @Path("/create/{playerName}")
    public Response create(@PathParam("playerName") String playerName) {

        Player player = new Player(playerName); /* create a new player for the board */
        Game game = new Game(player); /* create a game with the new player */

        return null;
    }

    @GET
    @Path("/join/{gameId}/{playerName}")
    public String join(@PathParam("gameId") String gameId, @PathParam("playerName") String playerName) {
        return null;
    }
}
