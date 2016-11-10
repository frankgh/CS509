package com.wordsweeper.server.controller;

import com.wordsweeper.server.api.WordSweeperServiceFactory;
import com.wordsweeper.server.api.model.Game;
import com.wordsweeper.server.model.ClientState;
import com.wordsweeper.server.model.ServerModel;
import com.wordsweeper.server.xml.Request;
import com.wordsweeper.server.xml.Response;
import retrofit2.Call;

/**
 * Controller on server in charge of relaying exitGame requests
 * to the API, and packaging up the API response to send to all
 * the players joined to the game. Additionally, this controller
 * implements IShutdownHandler and makes sure that the state of
 * the game is up-to-date
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

    /* (non-Javadoc)
     * @see com.wordsweeper.server.controller.IShutdownHandler#logout(com.wordsweeper.server.model.ClientState)
	 */
    public void logout(ClientState state) {

        /* If the client is still joined to game, we need to remove him */
        if (model.isClientInGame(state)) {
            Request request = getObjectFactory().createRequest();
            request.setId("CLIENT_SHUTDOWN");
            process(state, request);
        }
    }

    /* (non-Javadoc)
     * @see com.wordsweeper.server.controller.IProtocolHandler#canProcess(com.wordsweeper.server.xml.Request)
	 */
    public boolean canProcess(Request request) {
        return request != null && request.getExitGameRequest() != null;
    }

    /* (non-Javadoc)
     * @see com.wordsweeper.server.controller.IProtocolHandler#process(com.wordsweeper.server.model.ClientState, com.wordsweeper.server.xml.Request)
	 */
    public Response process(ClientState client, Request request) {
        String gameId = model.getGameId(client);
        String playerName = (String) client.getData();

        Call<Game> call = WordSweeperServiceFactory.getService().exitGame(gameId, playerName);

        return processInternal(client, request, call);
    }

    /* (non-Javadoc)
     * @see com.wordsweeper.server.controller.ControllerChain#execute(com.wordsweeper.server.model.ClientState, com.wordsweeper.server.xml.Request, com.wordsweeper.server.api.model.Game)
	 */
    public Response execute(ClientState client, Request request, Game game) {
        if (!model.exitGame(client)) {
            return getUnsuccessfulResponse(request, "Unable to exit the game");
        }

        return null;
    }
}
