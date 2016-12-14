package com.wordsweeper.server.controller;

import com.wordsweeper.server.api.WordSweeperServiceFactory;
import com.wordsweeper.server.api.model.Game;
import com.wordsweeper.server.model.ClientState;
import com.wordsweeper.server.model.ServerModel;
import com.wordsweeper.core.xml.JoinGameResponse;
import com.wordsweeper.core.xml.Request;
import com.wordsweeper.core.xml.Response;
import retrofit2.Call;

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
     * @see com.wordsweeper.server.controller.IProtocolHandler#canProcess(com.wordsweeper.core.xml.Request)
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
     * @see com.wordsweeper.server.controller.IProtocolHandler#process(com.wordsweeper.server.model.ClientState, com.wordsweeper.core.xml.Request)
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

        return processInternal(client, request, call);
    }

    /* (non-Javadoc)
     * @see com.wordsweeper.server.controller.ControllerChain#execute(com.wordsweeper.server.model.ClientState, com.wordsweeper.core.xml.Request, com.wordsweeper.server.api.model.Game)
	 */
    public Response execute(ClientState client, Request request, Game game) {
        client.setData(request.getJoinGameRequest().getName());
        if (!model.joinGame(client, game)) {
            client.setData(null);
            return getUnsuccessfulResponse(request, "Unable to join the game");
        }
        return null;
    }

    /* (non-Javadoc)
     * @see com.wordsweeper.server.controller.ControllerChain#setOnSuccessResponse(com.wordsweeper.core.xml.Request, com.wordsweeper.core.xml.Response)
	 */
    protected boolean setOnSuccessResponse(Request request, Response response) {
        return false; // DO NOTHING
    }

    /* (non-Javadoc)
         * @see com.wordsweeper.server.controller.ControllerChain#setOnFailureResponse(com.wordsweeper.core.xml.Request, com.wordsweeper.core.xml.Response)
         */
    protected void setOnFailureResponse(Request request, Response response) {
        JoinGameResponse joinGameResponse = getObjectFactory().createJoinGameResponse();
        joinGameResponse.setGameId(request.getJoinGameRequest().getGameId());
        response.setJoinGameResponse(joinGameResponse);
    }
}
