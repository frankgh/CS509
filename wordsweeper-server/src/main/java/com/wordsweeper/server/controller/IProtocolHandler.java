package com.wordsweeper.server.controller;

import com.wordsweeper.server.xml.Request;
import com.wordsweeper.server.xml.Response;
import com.wordsweeper.server.model.ClientState;

/**
 * Created by francisco on 10/27/16.
 */
public interface IProtocolHandler {

    /**
     * Process the Requests to the server
     *
     * @param state   the ClientState
     * @param request the request
     */
    Response process(ClientState state, Request request);

}
