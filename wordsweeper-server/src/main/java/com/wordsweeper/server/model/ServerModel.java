package com.wordsweeper.server.model;

import server.ClientState;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The server model that holds information about connections and games
 */
public class ServerModel {

    Map<String, String> clientIdToGameIdMap = new HashMap<String, String>();
    Map<String, List<String>> usersInGameMap = new HashMap<String, List<String>>();

    /**
     * Create a game
     *
     * @param client the ClientState
     * @param game   the game
     * @return true if successfully added, false otherwise
     */
    public boolean createGame(ClientState client, Game game) {

        if (isClientInGame(client)) {
            return false;
        }

        synchronized (usersInGameMap) {
            /* Create a list of ClientState IDs that are associated to the unique game ID */
            List<String> userList = new ArrayList<String>();
            userList.add(client.id());

            /* Map a client id to a game */
            clientIdToGameIdMap.put(client.id(), game.getUniqueId());

            /* Add a list of users to the gameId map */
            usersInGameMap.put(game.getUniqueId(), userList);
        }

        return true;
    }

    public boolean joinGame(ClientState client, Game game) {

        if (!isClientInGame(client)) {
            return false;
        }

        synchronized (usersInGameMap) {
            List<String> userList = usersInGameMap.get(game.getUniqueId());

        /* Add user to the list of users in the game */
            userList.add(client.id());

        /* Map a client id to a game */
            clientIdToGameIdMap.put(client.id(), game.getUniqueId());
        }

        return true;
    }

    public List<String> idsByGameId(String gameId) {
        return usersInGameMap.get(gameId);
    }

    /**
     * Exit a game
     *
     * @param client the ClientState object
     * @return true if successfully removed, false otherwise
     */
    public boolean exitGame(ClientState client) {

        if (!isClientInGame(client)) {
            return false;
        }

        synchronized (usersInGameMap) {
            String gameId = clientIdToGameIdMap.get(client.id());

            if (!usersInGameMap.containsKey(gameId)) {
                return false;
            }

            List<String> usersInGame = usersInGameMap.get(gameId);

            for (int i = 0; i < usersInGame.size(); i++) {
                if (client.id().equals(usersInGame.get(i))) {
                    usersInGame.remove(i);
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Determine if a ClientState is already associated to a game
     *
     * @param client the ClientState
     * @return true if the client is in a game, false otherwise
     */
    public boolean isClientInGame(ClientState client) {
        return clientIdToGameIdMap.containsKey(client.id());
    }
}
