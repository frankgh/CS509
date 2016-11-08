package com.wordsweeper.server.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by francisco on 10/29/16.
 */
public class GameSession {

    /**
     * The Game id.
     */
    String gameId;

    /**
     * The Client state list.
     */
    List<ClientState> clientStateList = new ArrayList<ClientState>();

    /**
     * The Managing player.
     */
    String managingPlayer;

    /**
     * Instantiates a new Game session.
     *
     * @param gameId the game id
     */
    public GameSession(String gameId) {
        this.gameId = gameId;
    }

    /**
     * Add client state.
     *
     * @param client the client
     */
    public void addClientState(ClientState client) {
        clientStateList.add(client);
    }

    /**
     * Remove client state.
     *
     * @param client the client
     */
    public boolean removeClientState(ClientState client) {
        if (!clientStateList.contains(client)) {
            return false;
        }

        clientStateList.remove(client);
        return true;
    }

    /**
     * Is empty boolean.
     *
     * @return the boolean
     */
    public boolean isEmpty() {
        return clientStateList.isEmpty();
    }
}
