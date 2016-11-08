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
 * Controller on server in charge of relying createGame requests
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

        Game game = null;
        Call<Game> call;

        if (request.getCreateGameRequest().getPassword() != null) {
            call = WordSweeperServiceFactory.getService().createGameWithPassword(
                    request.getCreateGameRequest().getName(),
                    request.getCreateGameRequest().getPassword());
        } else {
            call = WordSweeperServiceFactory.getService().createGame(request.getCreateGameRequest().getName());
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
            return getUnsuccessfulResponse(request, "Unable to create the game");
        }

        client.setData(request.getCreateGameRequest().getName());
        if (!model.createGame(client, game.getUniqueId())) { /* associate a clientState to the game */
            client.setData(null);
            return getUnsuccessfulResponse(request, "Unable to create the game");
        }

        /* Map the game to a BoardResponse object */
        BoardResponse boardResponse = MappingUtil.mapGameToBoardResponse(game);

        /* Create the response object */
        Response response = getObjectFactory().createResponse();
        response.setId(request.getId());
        response.setSuccess(true);
        response.setBoardResponse(boardResponse);

        // send this response back to the client which sent us the request.
        return response;
    }
}
