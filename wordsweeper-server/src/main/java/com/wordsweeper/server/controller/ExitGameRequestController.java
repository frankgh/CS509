package com.wordsweeper.server.controller;

import com.wordsweeper.server.api.WordSweeperServiceFactory;
import com.wordsweeper.server.api.model.Game;
import com.wordsweeper.server.model.ClientState;
import com.wordsweeper.server.model.ServerModel;
import com.wordsweeper.server.util.MappingUtil;
import com.wordsweeper.server.xml.BoardResponse;
import com.wordsweeper.server.xml.ObjectFactory;
import com.wordsweeper.server.xml.Request;
import com.wordsweeper.server.xml.Response;
import retrofit2.Call;

import java.io.IOException;

/**
 * Controller on server to handle exitGame Requests from the clients.
 *
 * @author francisco
 */
public class ExitGameRequestController extends ControllerChain implements IShutdownHandler {

    /**
     * Instantiates a new Exit game request controller.
     *
     * @param model the model
     */
    public ExitGameRequestController(ServerModel model) {
        this.model = model;
    }

    public void logout(ClientState state) {

        /* If the client is still joined to game, we need to remove him */
        if (model.isClientInGame(state)) {
            Request request = getObjectFactory().createRequest();
            request.setId("CLIENT_SHUTDOWN");
            process(state, request);
        }
    }

    public boolean canProcess(Request request) {
        return request != null && request.getExitGameRequest() != null;
    }

    public Response process(ClientState client, Request request) {
        String gameId = model.getGameId(client);
        String playerName = (String) client.getData();

        Game game = null;
        Call<Game> call = WordSweeperServiceFactory.getService().exitGame(gameId, playerName);

        try {
            game = call.execute().body();
        } catch (IOException e) {
            System.err.println("Error connecting to the webservice");
        }

        if (game == null || !model.exitGame(client)) {
            return getUnsuccessfulResponse(request, "Unable to exit the game");
        }

        BoardResponse boardResponse = MappingUtil.mapGameToBoardResponse(game);
        Response response = new ObjectFactory().createResponse();
        response.setId(request.getId());
        response.setSuccess(true);
        response.setBoardResponse(boardResponse);

        broadcastResponse(response, client.id(), gameId);

        // send this response back to the client which sent us the request.
        return response;
    }
}
