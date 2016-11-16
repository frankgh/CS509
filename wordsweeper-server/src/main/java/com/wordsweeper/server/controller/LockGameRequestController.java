package com.wordsweeper.server.controller;

import com.wordsweeper.server.api.WordSweeperServiceFactory;
import com.wordsweeper.server.api.model.Game;
import com.wordsweeper.server.model.ClientState;
import com.wordsweeper.server.model.ServerModel;
import com.wordsweeper.server.xml.LockGameResponse;
import com.wordsweeper.server.xml.Request;
import com.wordsweeper.server.xml.Response;
import retrofit2.Call;

/**
 * Controller on server in charge of relaying lockGame requests
 * to the API, and packaging up the API response
 *
 * @author francisco
 */
public class LockGameRequestController extends ControllerChain {

    /**
     * Instantiates a new Lock game request controller.
     *
     * @param model the model
     */
    public LockGameRequestController(ServerModel model) {
        this.model = model;
    }

    /* (non-Javadoc)
     * @see com.wordsweeper.server.controller.IProtocolHandler#canProcess(com.wordsweeper.server.xml.Request)
	 */
    public boolean canProcess(Request request) {
        return request != null && request.getLockGameRequest() != null;
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
            return getUnsuccessfulResponse(request, "Only the managing player is allowed to lock the game"); /* Return empty response */
        }

        String gameId = model.getGameId(client);
        String playerName = (String) client.getData();

        Call<Game> call = WordSweeperServiceFactory.getService()
                .lockGame(gameId, playerName);

        return processInternal(client, request, call);
    }

    /* (non-Javadoc)
     * @see com.wordsweeper.server.controller.ControllerChain#execute(com.wordsweeper.server.model.ClientState, com.wordsweeper.server.xml.Request, com.wordsweeper.server.api.model.Game)
	 */
    protected Response execute(ClientState client, Request request, Game game) {
        LockGameResponse lockGameResponse = getObjectFactory().createLockGameResponse();
        lockGameResponse.setGameId(game.getUniqueId());

        Response response = getBasicResponse(request, null);
        response.setLockGameResponse(lockGameResponse);
        return response;
    }

    /* (non-Javadoc)
     * @see com.wordsweeper.server.controller.ControllerChain#setOnSuccessResponse(com.wordsweeper.server.xml.Request, com.wordsweeper.server.xml.Response)
	 */
    protected boolean setOnSuccessResponse(Request request, Response response) {
        return false; // DO NOTHING
    }

    /* (non-Javadoc)
     * @see com.wordsweeper.server.controller.ControllerChain#setOnFailureResponse(com.wordsweeper.server.xml.Request, com.wordsweeper.server.xml.Response)
	 */
    protected void setOnFailureResponse(Request request, Response response) {
        LockGameResponse lockGameResponse = getObjectFactory().createLockGameResponse();
        lockGameResponse.setGameId(request.getLockGameRequest().getGameId());
        response.setLockGameResponse(lockGameResponse);
    }
}
