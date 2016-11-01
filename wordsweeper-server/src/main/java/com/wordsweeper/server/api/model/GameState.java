package com.wordsweeper.server.api.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Game state.
 */
public class GameState {
    /**
     * The Game id.
     */
    String gameId;
    /**
     * The Server thread i ds.
     */
    List<String> serverThreadIDs;

    /**
     * Instantiates a new Game state.
     *
     * @param gameId         the game id
     * @param serverThreadID the server thread id
     */
    public GameState(String gameId, String serverThreadID) {
        this.gameId = gameId;
        this.serverThreadIDs = new ArrayList<String>();
        serverThreadIDs.add(serverThreadID);
    }
}
