package com.wordsweeper.server.controller;

import com.wordsweeper.server.api.WordSweeperServiceFactory;
import com.wordsweeper.server.api.model.Game;
import com.wordsweeper.server.model.ClientState;
import com.wordsweeper.server.model.ServerModel;
import com.wordsweeper.server.util.MappingUtil;
import com.wordsweeper.server.xml.BoardResponse;
import com.wordsweeper.server.xml.Request;
import com.wordsweeper.server.xml.Response;
import retrofit2.Call;

import java.io.IOException;

/**
 * Controller on server in charge of relaying joinGame requests
 * to the API, and packaging up the API response to send to all
 * the players joined to the game
 *
 * @author francisco
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

    /* (non-Javadoc)
     * @see com.wordsweeper.server.controller.IProtocolHandler#canProcess(com.wordsweeper.server.xml.Request)
	 */

    /**
     * This is the method that verifying values in request.
     *
     * @return boolean This returns yes or no based on values in request.
     * @param request the request.
     *
     */

    public boolean canProcess(Request request) {
        return request != null && request.getJoinGameRequest() != null;
    }

    /* (non-Javadoc)
     * @see com.wordsweeper.server.controller.IProtocolHandler#process(com.wordsweeper.server.model.ClientState, com.wordsweeper.server.xml.Request)
	 */


    /**
     * This is the method that verifying clients' join game status.
     * @param client the client.
     * @param request the request.
     * @return a boardResponse wrapped in a Response object if successful, or unsuccessful response otherwise
     *
     */


    public Response process(ClientState client, Request request) {

        if (model.isClientInGame(client)) {
            return getUnsuccessfulResponse(request, "The player is already in a game"); /* Return empty response */
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
            retrofit2.Response<Game> apiResponse = call.execute();

            if (apiResponse.isSuccessful()) {
                game = apiResponse.body();
            } else {
                return handleAPIError(request, apiResponse);
            }
        } catch (IOException e) {
            System.err.println("Error connecting to the webservice");
        }

        if (game == null) {
            return getUnsuccessfulResponse(request, "Unable to join the game");
        }

        client.setData(request.getJoinGameRequest().getName());
        if (!model.joinGame(client, game)) {
            client.setData(null);
            return getUnsuccessfulResponse(request, "Unable to join the game");
        }

        BoardResponse boardResponse = MappingUtil.mapGameToBoardResponse(game);
        Response response = getObjectFactory().createResponse();
        response.setId(request.getId());
        response.setSuccess(true);
        response.setBoardResponse(boardResponse);

        broadcastResponse(response, client.id(), game);

        // send this response back to the client which sent us the request.
        return response;
    }
}
