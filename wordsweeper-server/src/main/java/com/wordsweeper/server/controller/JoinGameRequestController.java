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
public class JoinGameRequestController extends ControllerChain {

    /**
     * Instantiates a new Join game request controller.
     *
     * @param model the model
     */
    public JoinGameRequestController(ServerModel model) {
        this.model = model;
    }

    public boolean canProcess(Request request) {
        return request != null && request.getJoinGameRequest() != null;
    }

    public Response process(ClientState client, Request request) {

        if (model.isClientInGame(client)) {
            return getUnsuccessfulResponse(request); /* Return empty response */
        }

        Game game = null;
        Call<Game> call;

        if (request.getJoinGameRequest().getPassword() != null) {
            call = WordSweeperServiceFactory.getService().joinGameWithPassword(
                    request.getJoinGameRequest().getGameId(),
                    request.getJoinGameRequest().getName(),
                    request.getJoinGameRequest().getPassword());
        } else {
            call = WordSweeperServiceFactory.getService().joinGame(
                    request.getJoinGameRequest().getGameId(),
                    request.getJoinGameRequest().getName());
        }

        try {
            game = call.execute().body();
        } catch (IOException e) {
            System.err.println("Error connecting to the webservice");
        }

        if (game == null ||
                !model.joinGame(client, game)) {
            return getUnsuccessfulResponse(request);
        }

        BoardResponse boardResponse = MappingUtil.mapGameToBoardResponse(game);
        Response response = getObjectFactory().createResponse();
        response.setId(request.getId());
        response.setSuccess(true);
        response.setBoardResponse(boardResponse);

        broadcastResponse(response, client.id(), request.getJoinGameRequest().getGameId());

        // send this response back to the client which sent us the request.
        return response;
    }
}
