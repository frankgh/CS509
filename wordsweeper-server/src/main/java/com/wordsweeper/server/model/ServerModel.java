package com.wordsweeper.server.model;

import com.wordsweeper.server.api.model.Game;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The server model that holds information about connections and games
 *
 * @author francisco
 */
public class ServerModel {

    /**
     * The Game id to game session map.
     */
    Map<String, GameSession> gameIdToGameSessionMap = new HashMap<String, GameSession>();
    /**
     * The Client state id to game session map.
     */
    Map<String, GameSession> clientStateIdToGameSessionMap = new HashMap<String, GameSession>();

    /**
     * Create a game
     *
     * @param client the ClientState
     * @param gameId the gameId
     * @return true if successfully added, false otherwise
     */
    public boolean createGame(ClientState client, String gameId) {

        if (isClientInGame(client)) {
            return false;
        }

        synchronized (gameIdToGameSessionMap) {
            GameSession gameSession = new GameSession(gameId);
            gameSession.managingPlayer = (String) client.getData();
            gameSession.addClientState(client);

            gameIdToGameSessionMap.put(gameId, gameSession);
            clientStateIdToGameSessionMap.put(client.id(), gameSession);
        }

        return true;
    }

    /**
     * Join game boolean.
     *
     * @param client the client
     * @param game   the game
     * @return the boolean
     */
    public boolean joinGame(ClientState client, Game game) {

        /* client is already in a game */
        if (isClientInGame(client)) {
            return false;
        }

        synchronized (gameIdToGameSessionMap) {
            GameSession gameSession = gameIdToGameSessionMap.get(game.getUniqueId());
            gameSession.addClientState(client);
            gameSession.managingPlayer = game.getManagingPlayerName();

            clientStateIdToGameSessionMap.put(client.id(), gameSession);
        }

        return true;
    }

    /**
     * Ids by game id list.
     *
     * @param gameId the game id
     * @return the list
     */
    public List<ClientState> idsByGameId(String gameId) {
        return gameIdToGameSessionMap.get(gameId).clientStateList;
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

        synchronized (gameIdToGameSessionMap) {
            GameSession gameSession = clientStateIdToGameSessionMap.get(client.id());

            if (gameSession.removeClientState(client)) {
                clientStateIdToGameSessionMap.remove(client.id());
                client.setData(null);

                if (gameSession.isEmpty()) {
                    gameIdToGameSessionMap.remove(gameSession.gameId);
                }
            } else {
                return false;
            }
        }

        return true;
    }

    /**
     * Determine if a ClientState is already associated to a game
     *
     * @param client the ClientState
     * @return true if the client is in a game, false otherwise
     */
    public boolean isClientInGame(ClientState client) {
        return clientStateIdToGameSessionMap.containsKey(client.id());
    }

    /**
     * Get the game ID for the user
     *
     * @param client the ClientState
     * @return the game ID for the user
     */
    public String getGameId(ClientState client) {
        return clientStateIdToGameSessionMap.containsKey(client.id())
                ? clientStateIdToGameSessionMap.get(client.id()).gameId
                : null;
    }

    /**
     * Determine if ClientState is the managing player
     *
     * @param client the client
     * @return true if client is the managing player, false otherwise
     */
    public boolean isManagingPlayer(ClientState client) {

        if (!isClientInGame(client)) {
            return false;
        }

        GameSession gameSession = clientStateIdToGameSessionMap.get(client.id());
        return StringUtils.equals(gameSession.managingPlayer, (String) client.getData());
    }

    /**
     * Update game with the latest data from the server
     *
     * @param game the game
     * @return true if the game has updates, false otherwise
     */
    public boolean updateGame(Game game) {

        synchronized (gameIdToGameSessionMap) {
            GameSession gameSession = gameIdToGameSessionMap.get(game.getUniqueId());

            if (gameSession == null) {
                return false;
            }

            boolean hasUpdates = false;

            if (!StringUtils.equals(gameSession.managingPlayer, game.getManagingPlayerName())) {
                gameSession.managingPlayer = game.getManagingPlayerName();
                hasUpdates = true;
            }

            if (gameSession.isEmpty()) {
                gameIdToGameSessionMap.remove(game.getUniqueId());
                hasUpdates = true;
            }

            return hasUpdates;
        }
    }
}
