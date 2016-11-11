package com.wordsweeper.server.controller;

import com.wordsweeper.server.api.WordSweeperServiceFactory;
import com.wordsweeper.server.api.model.Game;
import com.wordsweeper.server.model.ClientState;
import com.wordsweeper.server.model.ServerModel;
import com.wordsweeper.server.xml.Request;
import com.wordsweeper.server.xml.Response;
import retrofit2.Call;

/**
 * Controller on server in charge of relaying createGame requests
 * to the API, and packaging up the API response to send it to
 * the joining player.
 *
 * @author francisco
 */
public class CreateGameRequestController extends ControllerChain {

    /**
     * Instantiates a new Create game request controller.
     *
     * @param model the model
     */
    public CreateGameRequestController(ServerModel model) {
        this.model = model;
    }

    /* (non-Javadoc)
     * @see com.wordsweeper.server.controller.IProtocolHandler#canProcess(com.wordsweeper.server.xml.Request)
*/
    public boolean canProcess(Request request) {
        return request != null && request.getCreateGameRequest() != null;
    }

    /* (non-Javadoc)
     * @see com.wordsweeper.server.controller.IProtocolHandler#process(com.wordsweeper.server.model.ClientState, com.wordsweeper.server.xml.Request)
*/
    public Response process(ClientState client, Request request) {

        if (model.isClientInGame(client)) {
            // Rogue client wants to create a game without exiting his previous game
            return getUnsuccessfulResponse(request, "The player is already in a game");
        }

        Call<Game> call;

        if (request.getCreateGameRequest().getPassword() != null) {
            call = WordSweeperServiceFactory.getService().createGameWithPassword(
                    request.getCreateGameRequest().getName(),
                    request.getCreateGameRequest().getPassword());
        } else {
            call = WordSweeperServiceFactory.getService().createGame(request.getCreateGameRequest().getName());
        }

        return processInternal(client, request, call);
    }

    /* (non-Javadoc)
     * @see com.wordsweeper.server.controller.ControllerChain#execute(com.wordsweeper.server.model.ClientState, com.wordsweeper.server.xml.Request, com.wordsweeper.server.api.model.Game)
*/
    public Response execute(ClientState client, Request request, Game game) {
        client.setData(request.getCreateGameRequest().getName());
        if (!model.createGame(client, game.getUniqueId())) { /* associate a clientState to the game */
            client.setData(null);
            return getUnsuccessfulResponse(request, "Unable to create the game");
        }

        return null;
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
    } // DO NOTHING
}