package com.wordsweeper.server.controller;

import com.wordsweeper.server.api.WordSweeperServiceFactory;
import com.wordsweeper.server.api.model.Game;
import com.wordsweeper.server.model.ClientState;
import com.wordsweeper.server.model.ServerModel;
import com.wordsweeper.server.xml.Request;
import com.wordsweeper.server.xml.Response;
import retrofit2.Call;

/**
 * Controller on server in charge of relaying joinGame requests
 * to the API, and packaging up the API response to send to all
 * the players joined to the game
 *
 * @author francisco
 */
public class JoinGameRequestController extends ControllerChain implements IControllerCommand {

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
    public boolean canProcess(Request request) {
        return request != null && request.getJoinGameRequest() != null;
    }

    /* (non-Javadoc)
     * @see com.wordsweeper.server.controller.IProtocolHandler#process(com.wordsweeper.server.model.ClientState, com.wordsweeper.server.xml.Request)
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
     * @see com.wordsweeper.server.controller.IControllerCommand#execute(com.wordsweeper.server.model.ClientState, com.wordsweeper.server.xml.Request, com.wordsweeper.server.api.model.Game)
	 */
    public Response execute(ClientState client, Request request, Game game) {
        client.setData(request.getJoinGameRequest().getName());
        if (!model.joinGame(client, game)) {
            client.setData(null);
            return getUnsuccessfulResponse(request, "Unable to join the game");
        }
        return null;
    }
}
