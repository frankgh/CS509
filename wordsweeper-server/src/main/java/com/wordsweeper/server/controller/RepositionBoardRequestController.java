package com.wordsweeper.server.controller;

import com.wordsweeper.server.api.WordSweeperServiceFactory;
import com.wordsweeper.server.api.model.Game;
import com.wordsweeper.server.model.ClientState;
import com.wordsweeper.server.model.ServerModel;
import com.wordsweeper.core.xml.Request;
import com.wordsweeper.core.xml.Response;
import retrofit2.Call;

/**
 * Controller on server in charge of relaying repositionBoard requests
 * to the API, and packaging up the API response to send to all
 * the players joined to the game
 *
 * @author francisco
 */
public class RepositionBoardRequestController extends ControllerChain {

    /**
     * Instantiates a new Reposition board request controller.
     *
     * @param model the model
     */
    public RepositionBoardRequestController(ServerModel model) {
        this.model = model;
    }

    /* (non-Javadoc)
     * @see com.wordsweeper.server.controller.IProtocolHandler#canProcess(com.wordsweeper.core.xml.Request)
	 */
    public boolean canProcess(Request request) {
        return request != null && request.getRepositionBoardRequest() != null;
    }

    /* (non-Javadoc)
     * @see com.wordsweeper.server.controller.IProtocolHandler#process(com.wordsweeper.server.model.ClientState, com.wordsweeper.core.xml.Request)
	 */
    public Response process(ClientState client, Request request) {

        /* If the client is not in a game return an unsuccessful response */
        if (!model.isClientInGame(client)) {
            return getUnsuccessfulResponse(request, "The player has not joined a game"); /* Return empty response */
        }

        String gameId = model.getGameId(client);
        String playerName = (String) client.getData();

        Call<Game> call = WordSweeperServiceFactory.getService().repositionBoard(gameId, playerName,
                request.getRepositionBoardRequest().getRowChange(), request.getRepositionBoardRequest().getColChange());

        return processInternal(client, request, call);
    }

    /* (non-Javadoc)
     * @see com.wordsweeper.server.controller.ControllerChain#execute(com.wordsweeper.server.model.ClientState, com.wordsweeper.core.xml.Request, com.wordsweeper.server.api.model.Game)
	 */
    protected Response execute(ClientState client, Request request, Game game) {
        return null; /* DO NOTHING */
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
    } // DO NOTHING

    /* (non-Javadoc)
     * @see com.wordsweeper.server.controller.ControllerChain#handleAPIError(com.wordsweeper.core.xml.Request, retrofit2.Response<?>)
	 */
    @Override
    public Response handleAPIError(Request request, retrofit2.Response<?> response) {
        if (response.code() == 304) {
            return null;
        }
        return super.handleAPIError(request, response);
    }
}
