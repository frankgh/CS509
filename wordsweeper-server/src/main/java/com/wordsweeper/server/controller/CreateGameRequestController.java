package com.wordsweeper.server.controller;

import com.wordsweeper.server.api.WordSweeperServiceFactory;
import com.wordsweeper.server.api.model.Game;
import com.wordsweeper.server.model.ServerModel;
import com.wordsweeper.server.util.MappingUtil;
import com.wordsweeper.server.xml.BoardResponse;
import com.wordsweeper.server.xml.Request;
import com.wordsweeper.server.xml.Response;
import retrofit2.Call;
import server.ClientState;

import java.io.IOException;

/**
 * Controller on server to package up the current state of the model
 * as an updateResponse message and send it back to the client.
 */
public class CreateGameRequestController extends ControllerChain {

    /**
     * Instantiates a new Create game request controller.
     *
     * @param model the model
     */
    public CreateGameRequestController(ServerModel model) {
        this.model = model;
    }

    public boolean canProcess(Request request) {
        return request != null && request.getCreateGameRequest() != null;
    }

    public Response process(ClientState client, Request request) {

        if (model.isClientInGame(client)) {
            // Rogue client wants to create a game without exiting his previous game
            return getUnsuccessfulResponse(request);
        }

        Game game = null;
        Call<Game> call;

        if (request.getCreateGameRequest().getPassword() != null) {
            call = WordSweeperServiceFactory.getService().createGameWithPassword(
                    request.getCreateGameRequest().getName(),
                    request.getCreateGameRequest().getPassword());
        } else {
            call = WordSweeperServiceFactory.getService().createGame(request.getCreateGameRequest().getName());
        }

        try {
            game = call.execute().body();
        } catch (IOException e) {
            System.err.println("Error connecting to the webservice");
        }

        if (game == null ||
                !model.createGame(client, game, request.getCreateGameRequest().getName())) { /* associate a clientState to the game */
            return getUnsuccessfulResponse(request);
        }

        /* Map the game to a BoardResponse object */
        BoardResponse boardResponse = MappingUtil.mapGameToBoardResponse(game);

        /* Create the response object */
        Response response = getObjectFactory().createResponse();
        response.setId(request.getId());
        response.setSuccess(true);
        response.setBoardResponse(boardResponse);

        // send this response back to the client which sent us the request.
        return response;
    }
}
