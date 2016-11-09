package com.wordsweeper.server.controller;

import com.wordsweeper.server.model.ClientState;
import com.wordsweeper.server.xml.Request;
import com.wordsweeper.server.xml.Response;

/**
 * Empty Handler
 *
 * @author heineman
 * @author francisco
 */
public class EmptyHandler extends ControllerChain {

    public Response process(ClientState state, Request request) {
        System.out.println("Not handled");
        return getUnsuccessfulResponse(request, "Not handled");
    }

    public boolean canProcess(Request request) {
        return true;
    }
}
