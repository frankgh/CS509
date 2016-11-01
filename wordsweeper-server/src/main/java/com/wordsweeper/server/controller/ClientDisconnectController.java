package com.wordsweeper.server.controller;

import com.wordsweeper.server.model.ServerModel;
import server.ClientState;

/**
 * Controller to detect when terminating client has something to be cleaned up.
 * That is, the exit appears to be system-related, not by user action.
 */
public class ClientDisconnectController {

    /**
     * The Model.
     */
    ServerModel model;

    /**
     * Instantiates a new Client disconnect controller.
     *
     * @param model the model
     */
    public ClientDisconnectController(ServerModel model) {
        this.model = model;
    }

    /**
     * Process.
     *
     * @param state the state
     */
    public void process(ClientState state) {

        model.exitGame(state);
        System.out.println("Client disconnected for client:" + state.id());
    }
}
