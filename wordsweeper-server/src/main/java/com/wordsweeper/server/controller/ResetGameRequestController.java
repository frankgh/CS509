package com.wordsweeper.server.controller;

import com.wordsweeper.server.api.WordSweeperServiceFactory;
import com.wordsweeper.server.api.model.Game;
import com.wordsweeper.server.model.ClientState;
import com.wordsweeper.server.model.ServerModel;
import com.wordsweeper.server.util.MappingUtil;
import com.wordsweeper.server.xml.BoardResponse;
import com.wordsweeper.server.xml.Request;
import com.wordsweeper.server.xml.Response;

import java.io.IOException;

/**
 * Controller on server in charge of relying resetGame requests
 * to the API, and packaging up the API response to send to all
 * the players joined to the game
 *
 * @author francisco
 */
public class ResetGameRequestController extends ControllerChain {

    /**
     * Instantiates a new Reset game request controller.
     *
     * @param model the model
     */
    public ResetGameRequestController(ServerModel model) {
        this.model = model;
    }

    /* (non-Javadoc)
     * @see com.wordsweeper.server.controller.IProtocolHandler#canProcess(com.wordsweeper.server.xml.Request)
	 */
    public boolean canProcess(Request request) {
        return request != null && request.getResetGameRequest() != null;
    }

    /* (non-Javadoc)
     * @see com.wordsweeper.server.controller.IProtocolHandler#process(com.wordsweeper.server.model.ClientState, com.wordsweeper.server.xml.Request)
	 */
    public Response process(ClientState client, Request request) {

        /* If the client is not in a game return an unsuccessful response */
        if (!model.isClientInGame(client)) {
            return getUnsuccessfulResponse(request, "The player has not joined a game"); /* Return empty response */
        }

        /* Only the managing player can reset the game */
        if (!model.isManagingPlayer(client)) {
            return getUnsuccessfulResponse(request, "Only the managing player is allowed to reset the game"); /* Return empty response */
        }

        Game game = null;
        String gameId = model.getGameId(client);
        String playerName = (String) client.getData();

        try {
            retrofit2.Response<Game> apiResponse = WordSweeperServiceFactory.getService()
                    .resetGame(gameId, playerName)
                    .execute();

            if (apiResponse.isSuccessful()) {
                game = apiResponse.body();
            } else {
                return handleAPIError(request, apiResponse);
            }
        } catch (IOException e) {
            System.err.println("Error connecting to the webservice");
        }

        /* The request failed, return unsuccessful response */
        if (game == null) {
            return getUnsuccessfulResponse(request, "Unable to reset game");
        }

        BoardResponse boardResponse = MappingUtil.mapGameToBoardResponse(game);
        Response response = getObjectFactory().createResponse();
        response.setId(request.getId());
        response.setSuccess(true);
        response.setBoardResponse(boardResponse);

        broadcastResponse(response, client.id(), game);

        return response;
    }
}
