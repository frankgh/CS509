package com.wordsweeper.server.controller;

import com.wordsweeper.server.model.ClientState;

/**
 * If you want to be told when a client exits, make sure your ProtocolHandler
 * implements {@link IShutdownHandler}.
 * <p>
 *
 * @author heineman
 * @author francisco
 */
public interface IShutdownHandler {

    /**
     * When client terminates connection, this method is invoked, but only if
     * the ProtocolHandler also implements IShutdownHandler.
     * <p>
     * Parameter is the state of the thread being terminated.
     *
     * @param state the state
     */
    void logout(ClientState state);
}