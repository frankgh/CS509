package com.wordsweeper.server.controller;

import com.wordsweeper.server.xml.Request;
import com.wordsweeper.server.xml.Response;
import com.wordsweeper.server.model.ClientState;

/**
 * Created by francisco on 10/27/16.
 */
public class EmptyHandler extends ControllerChain {

    public Response process(ClientState state, Request request) {
        System.out.println("Not handled");
        return null;
    }

    public boolean canProcess(Request request) {
        return true;
    }
}
