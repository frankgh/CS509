package com.wordsweeper.server.controller;

import com.wordsweeper.server.api.model.Game;
import com.wordsweeper.server.model.ClientState;
import com.wordsweeper.server.model.ServerModel;
import com.wordsweeper.server.util.MappingUtil;
import com.wordsweeper.server.xml.BoardResponse;
import com.wordsweeper.server.xml.ObjectFactory;
import com.wordsweeper.server.xml.Request;
import com.wordsweeper.server.xml.Response;
import org.apache.commons.lang3.StringUtils;
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
     * Executes a per controller process during processInternal
     *
     * @param client  the client
     * @param request the request
     * @param game    the game
     * @return the response
     */
    protected abstract Response execute(ClientState client, Request request, Game game);

    /**
     * Instead of returning a BoardResponse to the client, you can customize the
     * response you want to send to your client. Use setOnSuccessResponse to customize
     * this response. Return true if the response was customized, false otherwise
     *
     * @param request  the request
     * @param response the response
     * @return true if the response was customized, false otherwise
     */
    protected abstract boolean setOnSuccessResponse(Request request, Response response);

    /**
     * Sets the response if the request failed
     *
     * @param request  the request
     * @param response the response
     */
    protected abstract void setOnFailureResponse(Request request, Response response);

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

        setOnFailureResponse(request, response);

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

        model.updateGame(game); /* Update game details */

        // all other players on game (excepting this particular client) need to be told of this
        // same response.
        List<ClientState> clientStateList = model.idsByGameId(game.getUniqueId());

        if (clientStateList == null) {
            return;
        }

        for (ClientState state : clientStateList) {
            if (!StringUtils.equals(state.id(), clientId)) {
                state.sendMessage(response);
            }
        }
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
            System.err.println("Error connecting to webservice");
        }

        /* The request failed, return unsuccessful response */
        if (game == null) {
            return getUnsuccessfulResponse(request, "Unable to process request");
        }

        /* Execute controller specific command */
        Response response = execute(client, request, game);
        if (response != null) {
            return response;
        }

        boolean isAdminClient = (this instanceof IAdminController);

        /* Map the game to a BoardResponse object */
        BoardResponse boardResponse = MappingUtil.mapGameToBoardResponse(game, isAdminClient);

        /* Create the response object */
        response = getBasicResponse(request, boardResponse);

        /* Broadcast the response to all the players in the game */
        broadcastResponse(response, client.id(), game);

        if (setOnSuccessResponse(request, response)) {
            response.setBoardResponse(null);
        }

        // send this response back to the client which sent us the request.
        return response;
    }

    /**
     * Get the basic Response with the response ID and success set to True
     *
     * @param request       the request
     * @param boardResponse the boardResponse
     * @return the basic response
     */
    protected Response getBasicResponse(Request request, BoardResponse boardResponse) {
        Response response = getObjectFactory().createResponse();
        response.setId(request.getId());
        response.setSuccess(true);
        response.setBoardResponse(boardResponse);
        return response;
    }
}
