package com.wordsweeper.server.controller;

import com.wordsweeper.server.api.WordSweeperServiceFactory;
import com.wordsweeper.server.api.model.Game;
import com.wordsweeper.server.model.ServerModel;
import com.wordsweeper.server.util.MappingUtil;
import com.wordsweeper.server.xml.BoardResponse;
import com.wordsweeper.server.xml.Request;
import com.wordsweeper.server.xml.Response;
import server.ClientState;

import java.io.IOException;

/**
 * Created by francisco on 10/25/16.
 */
public class ResetGameRequestController extends ControllerChain {

    public ResetGameRequestController(ServerModel model) {
        this.model = model;
    }

    /**
     * Can process boolean.
     *
     * @param request the request
     * @return true if the handler can process the request, false otherwise
     */
    public boolean canProcess(Request request) {
        return request != null && request.getResetGameRequest() != null;
    }

    public Response process(ClientState client, Request request) {

        /* If the client is not in a game return an unsuccessful response */
        if (!model.isClientInGame(client)) {
            return getUnsuccessfulResponse(request); /* Return empty response */
        }

        /* Only the managing player can reset the game */
        if (!model.isManagingPlayer(client)) {
            return getUnsuccessfulResponse(request); /* Return empty response */
        }

        Game game = null;
        String gameId = model.getGameId(client);

        try {
            game = WordSweeperServiceFactory.getService()
                    .resetGame(gameId, model.getPlayerName(client))
                    .execute().body();
        } catch (IOException e) {
            System.err.println("Error connecting to the webservice");
        }

        /* The request failed, return unsuccessful response */
        if (game == null) {
            return getUnsuccessfulResponse(request);
        }

        BoardResponse boardResponse = MappingUtil.mapGameToBoardResponse(game);
        Response response = getObjectFactory().createResponse();
        response.setId(request.getId());
        response.setSuccess(true);
        response.setBoardResponse(boardResponse);

        broadcastResponse(response, client.id(), gameId);

        return response;
    }
}
