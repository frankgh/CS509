package com.wordsweeper.server.controller;

import com.wordsweeper.server.model.ClientState;
import com.wordsweeper.server.model.ServerModel;
import com.wordsweeper.server.util.JAXBUtil;
import com.wordsweeper.server.xml.Request;
import com.wordsweeper.server.xml.Response;

/**
 * WordSweeperProtocolHandler is in charge of registering handlers and processing
 * requests, handing them off to the handler that is able to process such request.
 * WordSweeperProtocolHandler will also make sure that clients that disconnect
 * from the server are removed from the API and the local ServerModel state.
 * <p>
 * Created by francisco on 10/27/16.
 *
 * @author francisco
 */
public class WordSweeperProtocolHandler implements IProtocolHandler, IShutdownHandler {

    /**
     * The Model.
     */
    ServerModel model;
    /**
     * The Chain.
     */
    ControllerChain chain = new EmptyHandler();

    /**
     * Instantiates a new Word sweeper protocol handler.
     *
     * @param model the model
     */
    public WordSweeperProtocolHandler(ServerModel model) {
        this.model = model;
    }

    /**
     * Register new controller chain as occurring before existing chain.
     *
     * @param handler the handler
     */
    public void registerHandler(ControllerChain handler) {
        handler.next = chain;
        chain = handler;
    }

    /* (non-Javadoc)
     * @see com.wordsweeper.server.controller.IProtocolHandler#canProcess(com.wordsweeper.server.xml.Request)
*/
    public boolean canProcess(Request request) {
        return false;
    }

    /* (non-Javadoc)
     * @see com.wordsweeper.server.controller.IProtocolHandler#process(com.wordsweeper.server.model.ClientState, com.wordsweeper.server.xml.Request)
*/
    public Response process(ClientState state, Request request) {

        System.out.println("Request Received:");
        JAXBUtil.prettyPrintln(request);
        ControllerChain handler = chain;

        while (handler != null) {
            if (handler.canProcess(request))
                return handler.process(state, request);
            handler = (ControllerChain) handler.next;
        }

        System.err.println("Unable to handle message");
        return null;
    }

    /**
     * Find a suitable Handler that is able to Shutdown the client connection
     *
     * @param state
     */
    public void logout(ClientState state) {

        ControllerChain handler = chain;
        while (handler != null) {
            if (handler instanceof IShutdownHandler)
                ((IShutdownHandler) handler).logout(state);
            handler = (ControllerChain) handler.next;
        }
    }
}

