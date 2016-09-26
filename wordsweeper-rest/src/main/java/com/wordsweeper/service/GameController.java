package com.wordsweeper.service;

import com.wordsweeper.service.model.Game;
import com.wordsweeper.service.model.Player;
import org.apache.commons.lang3.StringUtils;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 * Created by francisco on 9/15/16.
 */
@Path("/game")
public class GameController {

    @GET
    @Path("/create/{playerName}{password:(/password/[^/]+?)?}")
    public Response create(@PathParam("playerName") String playerName, @PathParam("password") String password) {

        Player player = new Player(playerName); /* create a new player for the board */

        Game game;

        if (StringUtils.isBlank(password)) {
            game = new Game(player); /* create a game with the new player */
        } else {
            game = new Game(player, password); /* Create a password protected game */
        }

        return Response /* Return response with the game object */
                .ok(game)
                .build();
    }

    @GET
    @Path("/join/{gameId}/{playerName}{password:(/password/[^/]+?)?}")
    public Response join(@PathParam("gameId") String gameId,
                         @PathParam("playerName") String playerName,
                         @PathParam("password") String password) {

        // TODO: actually load the game here
        Game game = new Game(null);

        if (game.ended() || game.isLocked()) {
            return null;
        }

        boolean addPlayer;

        if (StringUtils.isBlank(password)) {
            addPlayer = game.addPlayer(playerName); /* join the game */
        } else {
            addPlayer = game.addPlayer(playerName, password); /* join a password protected game */
        }

        if (addPlayer) {
            game.getBoard().reset(); /* finally, reset the board */
        }

        return Response /* Return response with the game object */
                .ok(game)
                .build();
    }

    @GET
    @Path("/lock/{gameId}/{playerName}")
    public Response lock(@PathParam("gameId") String gameId, @PathParam("playerName") String playerName) {

        // TODO: actually load the game here
        Game game = new Game(null);

        game.lock(playerName);

        // TODO: persist game

        return Response /* Return response with the game object */
                .ok(game)
                .build();
    }

    @GET
    @Path("/exit/{gameId}/{playerName}")
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
    @Path("/reset/{gameId}")
    public Response reset(@PathParam("gameId") String gameId) {

        // only managing user

        // TODO: actually load the game here
        Game game = new Game(null);

        game.reset(); /* resets board and player scores */

        // TODO: persist board here


        return Response /* Return response with the game object */
                .ok(game)
                .build();
    }
}
