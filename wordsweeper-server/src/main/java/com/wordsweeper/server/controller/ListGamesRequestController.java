package com.wordsweeper.server.controller;

import com.wordsweeper.server.api.WordSweeperServiceFactory;
import com.wordsweeper.server.api.model.Game;
import com.wordsweeper.server.model.ClientState;
import com.wordsweeper.server.model.ServerModel;
import com.wordsweeper.server.util.MappingUtil;
import com.wordsweeper.server.xml.ListGamesResponse;
import com.wordsweeper.server.xml.Request;
import com.wordsweeper.server.xml.Response;
import retrofit2.Call;

import java.io.IOException;
import java.util.List;

/**
 * Controller on server in charge of relaying listGames requests
 * to the API, and packaging up the API response
 *
 * @author Celia
 */
public class ListGamesRequestController extends ControllerChain {

    /**
     * Instantiates a new List games request.
     *
     * @param model the model
     */
    public ListGamesRequestController(ServerModel model) {
        this.model = model;
    }

    /* (non-Javadoc)
     * @see com.wordsweeper.server.controller.IProtocolHandler#canProcess(com.wordsweeper.server.xml.Request)
	 */
    public boolean canProcess(Request request) {
        return request != null && request.getListGamesRequest() != null;
    }

    /* (non-Javadoc)
     * @see com.wordsweeper.server.controller.IProtocolHandler#process(com.wordsweeper.server.model.ClientState, com.wordsweeper.server.xml.Request)
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
     * @see com.wordsweeper.server.controller.ControllerChain#execute(com.wordsweeper.server.model.ClientState, com.wordsweeper.server.xml.Request, com.wordsweeper.server.api.model.Game)
	 */
    protected Response execute(ClientState client, Request request, Game game) {
        return null;
    }
}
