package com.wordsweeper.server.controller;

import com.wordsweeper.server.api.model.Game;
import com.wordsweeper.server.model.ClientState;
import com.wordsweeper.server.model.ServerModel;
import com.wordsweeper.server.util.MappingUtil;
import com.wordsweeper.server.xml.BoardResponse;
import com.wordsweeper.server.xml.ObjectFactory;
import com.wordsweeper.server.xml.Request;
import com.wordsweeper.server.xml.Response;
import retrofit2.Call;

import java.io.IOException;
import java.util.List;

/**
 * ControllerChain is in charge of chaining up all the controllers.
 *
 * @author heineman
 * @author francisco
 */
public abstract class ControllerChain implements IProtocolHandler {

    /**
     * The Model.
     */
    ServerModel model;

    /**
     * Next one in the chain. Once null is reached, done.
     */
    IProtocolHandler next = null;

    /**
     * The Object factory.
     */
    private ObjectFactory objectFactory = null;

    /**
     * Terminal entry in the chain.
     */
    protected ControllerChain() {
    }

    /**
     * Chain together.
     *
     * @param next the next
     */
    protected ControllerChain(ControllerChain next) {
        this.next = next;
    }

    /**
     * Determine if the request can be processed by the handler
     *
     * @param request the request
     * @return true if the handler can process the request, false otherwise
     */
    public abstract boolean canProcess(Request request);

    /**
     * Process the request
     *
     * @param state   the ClientState
     * @param request the request
     * @return the response
     */
    public abstract Response process(ClientState state, Request request);

    /**
     * Get the object factory for the WordSweeper XML Protocol
     *
     * @return the object factory
     */
    protected ObjectFactory getObjectFactory() {
        if (objectFactory == null) {
            synchronized (ControllerChain.class) {
                if (objectFactory == null) {
                    objectFactory = new ObjectFactory();
                }
            }
        }

        return objectFactory;
    }

    /**
     * Get a generic unsuccessful response
     *
     * @param request the request
     * @param reason  the reason
     * @return the empty unsuccessful response
     */
    protected Response getUnsuccessfulResponse(Request request, String reason) {
        Response response = getObjectFactory().createResponse();
        response.setId(request.getId());
        response.setSuccess(false); /* success to false */
        response.setReason(reason);
        return response;
    }

    /**
     * Handle API errors and return an unsuccessful response to the client
     *
     * @param request  the request
     * @param response the response
     * @return the response
     */
    protected Response handleAPIError(Request request, retrofit2.Response<?> response) {
        return getUnsuccessfulResponse(request, MappingUtil.parseError(response).message());
    }

    /**
     * Broadcast response to all the clients in the game except for the requesting client
     *
     * @param response the response to be broadcast
     * @param clientId the clientId of the requesting client
     * @param game     the game
     */
    protected void broadcastResponse(Response response, String clientId, Game game) {
        // all other players on game (excepting this particular client) need to be told of this
        // same response. Note this is inefficient and should be replaced by more elegant functioning
        // hint: rely on your game to store player names...

        List<ClientState> clientStateList = model.idsByGameId(game.getUniqueId());

        if (clientStateList != null) {
            for (ClientState state : clientStateList) {
                if (!state.id().equals(clientId)) {
                    state.sendMessage(response);
                }
            }
        }
        model.updateGame(game);
    }

    /**
     * Process internal response.
     *
     * @param client  the client
     * @param request the request
     * @param call    the call
     * @return the response
     */
    protected Response processInternal(ClientState client, Request request, Call<Game> call) {
        Game game = null;
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

        /* The request failed, return unsuccessful response */
        if (game == null) {
            return getUnsuccessfulResponse(request, "Unable to join the game");
        }

        /* Execute controller specific command */
        if (this instanceof IControllerCommand) {
            Response response = ((IControllerCommand) this).execute(client, request, game);

            if (response != null) {
                return response;
            }
        }

        /* Map the game to a BoardResponse object */
        BoardResponse boardResponse = MappingUtil.mapGameToBoardResponse(game);

        /* Create the response object */
        Response response = getObjectFactory().createResponse();
        response.setId(request.getId());
        response.setSuccess(true);
        response.setBoardResponse(boardResponse);

        broadcastResponse(response, client.id(), game);

        // send this response back to the client which sent us the request.
        return response;
    }
}
