package com.wordsweeper.service;

import com.wordsweeper.service.model.Board;
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

        return Response /* Return response with the game object */
                .ok(game)
                .build();
    }

    @GET
    @Path("/join/{gameId}/{playerName}")
    public Response join(@PathParam("gameId") String gameId, @PathParam("playerName") String playerName) {

        // TODO: actually load the game here
        Game game = new Game(null);

        Player newPlayer = new Player(playerName);

        game.addPlayer(newPlayer);

        return Response /* Return response with the game object */
                .ok(game)
                .build();
    }

    @GET
    @Path("/reset/{gameId}")
    public Response reset(@PathParam("gameId") String gameId) {

        // TODO: actually load the game here
        Game game = new Game(null);

        game.reset(); /* resets board and player scores */

        // TODO: persist board here


        return Response /* Return response with the game object */
                .ok(game)
                .build();
    }
}
