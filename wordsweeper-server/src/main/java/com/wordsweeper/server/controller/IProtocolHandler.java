package com.wordsweeper.server.controller;

import com.wordsweeper.server.model.ClientState;
import com.wordsweeper.server.xml.Request;
import com.wordsweeper.server.xml.Response;

/**
 * Contract for protocol handler
 *
 * @author heineman
 * @author francisco
 */
public interface IProtocolHandler {

    /**
     * Determine if the request can be processed by this handler
     *
     * @param request the request
     * @return true if the handler can process the request, false otherwise
     */
    boolean canProcess(Request request);

    /**
     * Process the Requests to the server
     *
     * @param state   the ClientState
     * @param request the request
     * @return the response
     */
    Response process(ClientState state, Request request);

}
