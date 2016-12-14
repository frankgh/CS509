package com.wordsweeper.server.controller;

import com.wordsweeper.server.api.WordSweeperServiceFactory;
import com.wordsweeper.server.api.model.Game;
import com.wordsweeper.server.model.ClientState;
import com.wordsweeper.server.model.ServerModel;
import com.wordsweeper.server.util.MappingUtil;
import com.wordsweeper.core.xml.ListGamesResponse;
import com.wordsweeper.core.xml.Request;
import com.wordsweeper.core.xml.Response;
import retrofit2.Call;

import java.io.IOException;
import java.util.List;

/**
 * Controller on server in charge of relaying listGames requests
 * to the API, and packaging up the API response
 *
 * @author Celia
 * @since 2016 -11-08
 */
public class ListGamesRequestController extends ControllerChain implements IAdminController {

    /**
     * Instantiates a new List games request.
     *
     * @param model the model
     */
    public ListGamesRequestController(ServerModel model) {

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

        return request != null && request.getListGamesRequest() != null;
    }

    /* (non-Javadoc)
     * @see com.wordsweeper.server.controller.IProtocolHandler#process(com.wordsweeper.server.model.ClientState, com.wordsweeper.core.xml.Request)
	 */

    /**
     * List game request status.
     *
     * @param state client state,
     * @param request the request.
     *
     *
     */

    public Response process(ClientState state, Request request) {

        Call<List<Game>> call = WordSweeperServiceFactory.getService().listGames();
        List<Game> gameList = null;

        try {
            gameList = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (gameList == null) {
            return getUnsuccessfulResponse(request, "Unable to load list of games");
        }

        ListGamesResponse listGamesResponse = MappingUtil.mapGameListToListGamesResponse(gameList);
        Response response = getObjectFactory().createResponse();
        response.setId(request.getId());
        response.setSuccess(true);
        response.setListGamesResponse(listGamesResponse);

        return response;
    }

    /* (non-Javadoc)
     * @see com.wordsweeper.server.controller.ControllerChain#execute(com.wordsweeper.server.model.ClientState, com.wordsweeper.core.xml.Request, com.wordsweeper.server.api.model.Game)
	 */
    protected Response execute(ClientState client, Request request, Game game) {
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
    } // DO NOTHING
}
