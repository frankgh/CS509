package com.wordsweeper.server.controller;

import com.wordsweeper.server.util.JAXBUtil;
import com.wordsweeper.server.xml.Request;
import com.wordsweeper.server.xml.Response;
import server.ClientState;

/**
 * Created by francisco on 10/27/16.
 */
public class WordSweeperProtocolHandler implements IProtocolHandler {

    ControllerChain chain = new EmptyHandler();

    /**
     * Register new controller chain as occurring before existing chain.
     */
    public void registerHandler(ControllerChain handler) {
        handler.next = chain;
        chain = handler;
    }

    /**
     * Process the request through the chain
     *
     * @param state   the ClientState
     * @param request the request
     */
    public Response process(ClientState state, Request request) {

        System.out.println("Request Received:");
        JAXBUtil.prettyPrintln(request);
        ControllerChain handler = chain;

        while (handler != null) {
            if (handler.canProcess(request)) {
                return handler.process(state, request);
            }
            handler = (ControllerChain) handler.next;
        }

        System.err.println("Unable to handle message");
        return null;
    }
}
