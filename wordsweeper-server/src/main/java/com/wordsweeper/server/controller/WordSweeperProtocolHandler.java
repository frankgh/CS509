package com.wordsweeper.server.controller;

import com.wordsweeper.server.model.ServerModel;
import com.wordsweeper.server.util.JAXBUtil;
import com.wordsweeper.server.xml.Request;
import com.wordsweeper.server.xml.Response;
import server.ClientState;
import server.IShutdownHandler;

/**
 * WordSweeper implementation of a protocol handler to respond to messages received from clients.
 * You should follow this template when designing YOUR protocol handler.
 * <p>
 * To avoid issues with multiple clients submitting requests concurrently,
 * notice that the {@link #process(ClientState, Request)} method is synchronized.
 * This will ensure that no more than one server thread executes this method
 * at a time.
 * <p>
 * Also extended to support detection of client disconnects so these can release the lock
 * if indeed the client was the one locking the model.
 */
public class WordSweeperProtocolHandler implements IShutdownHandler {

    ServerModel model;

    public WordSweeperProtocolHandler(ServerModel model) {
        this.model = model;
    }

    public synchronized Response process(ClientState st, Request request) {

        System.out.println("Request Received:");
        JAXBUtil.prettyPrintln(request);

        if (request.getCreateGameRequest() != null) {
            return new CreateGameRequestController(model).process(st, request);
        } else if (request.getJoinGameRequest() != null) {
            return new JoinGameRequestController(model).process(st, request);
        }

        // unknown? no idea what to do
        System.err.println("Unable to handle message");
        return null;
    }

    public void logout(ClientState st) {
        new ClientDisconnectController(model).process(st);
    }
}
