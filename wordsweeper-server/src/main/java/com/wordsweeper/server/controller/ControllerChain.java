package com.wordsweeper.server.controller;

import com.wordsweeper.server.model.ClientState;
import com.wordsweeper.server.model.ServerModel;
import com.wordsweeper.server.util.MappingUtil;
import com.wordsweeper.server.xml.ObjectFactory;
import com.wordsweeper.server.xml.Request;
import com.wordsweeper.server.xml.Response;

/**
 * Created by francisco on 10/27/16.
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
     * @param gameId   the gameId
     */
    protected void broadcastResponse(Response response, String clientId, String gameId) {
        // all other players on game (excepting this particular client) need to be told of this
        // same response. Note this is inefficient and should be replaced by more elegant functioning
        // hint: rely on your game to store player names...
        for (ClientState state : model.idsByGameId(gameId)) {
            if (!state.id().equals(clientId)) {
                state.sendMessage(response);
            }
        }
    }
}
