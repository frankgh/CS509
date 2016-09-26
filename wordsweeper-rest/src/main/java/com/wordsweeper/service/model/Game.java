package com.wordsweeper.service.model;

import com.wordsweeper.service.util.RandomUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by francisco on 9/13/16.
 */
public class Game {

    private static final int STATUS_ACTIVE = 0;
    private static final int STATUS_INACTIVE = 1;
    private static final int DEFAULT_BOARD_SIZE = 7;

    Board board;
    List<Player> playerList;
    Player managingPlayer;
    private boolean locked;
    private String password;
    private String uniqueId;

    /*
    0: Active
    1: Inactive
     */
    int status;

    public Game(Player player) {
        this(player, null);
    }

    public Game(Player player, String password) {
        this.status = STATUS_ACTIVE;
        this.board = new Board(DEFAULT_BOARD_SIZE);
        this.playerList = new ArrayList<>();
        this.playerList.add(player);
        this.managingPlayer = player;
        this.password = password;
        this.uniqueId = RandomUtil.nextUniqueId();
    }

    /**
     * Add a new player given a player name to the current game
     *
     * @param playerName the new player name
     * @return false if there is a player with the same game in the game, true otherwise
     */
    public boolean addPlayer(String playerName) {
        return addPlayer(new Player(playerName));
    }

    /**
     * Add a new player given a player name to the current password protected game
     *
     * @param playerName the new player name
     * @param password   the game password
     * @return false if there is a player with the same game in the game or the password doesn't match, true otherwise
     */
    public boolean addPlayer(String playerName, String password) {
        return addPlayer(new Player(playerName), password);
    }

    /**
     * Adds a new player to the current game
     *
     * @param player the new player
     * @return false if there is a player with the same game in the game, true otherwise
     */
    public boolean addPlayer(Player player) {
        return addPlayer(player, null);
    }


    /**
     * Add a new player to the current password protected game
     *
     * @param player   the new player
     * @param password the game password
     * @return false if there is a player with the same game in the game or the password doesn't match, true otherwise
     */
    public boolean addPlayer(Player player, String password) {

        if (StringUtils.isNotBlank(password) && StringUtils.isNotBlank(this.password)) {
            if (!StringUtils.equals(password, this.password)) {
                return false;
            }
        }

        if (status == STATUS_INACTIVE) {
            return false; /* unable to add players to a finished game */
        }

        if (isLocked()) {
            return false; /* game is locked */
        }

        if (containsPlayer(player)) {
            return false; /* game already contains a player with that name */
        }

        playerList.add(player);
        return true;
    }

    /**
     * Removes a player from the game. If the player being removed
     * is the managing player, a new manager player is assigned if there
     * are players left
     *
     * @param playerName the name of the player to be removed
     * @return true if the player exists, false otherwise
     */
    public boolean removePlayer(String playerName) {
        for (Player p : playerList) {
            if (StringUtils.equals(p.getName(), playerName)) {

                playerList.remove(p); /* Remove player from the game */

                if (p.equals(managingPlayer) && !playerList.isEmpty()) {
                    // If the removed player was the managing player
                    // assign a new random managing player to the game
                    managingPlayer = playerList.get(RandomUtil.nextInt(playerList.size()));
                }

                return true;
            }
        }

        return false;
    }

    /**
     * Resets the status of the board
     * and all the player scores.
     */
    public void reset() {
        this.board.reset();

        for (Player player : playerList) {
            player.setScore(0);
        }
    }

    /**
     * Determine if there's a Player with the same name already joined to the game
     *
     * @param player
     * @return true if there is a user with the same name in the game, false otherwise
     */
    boolean containsPlayer(Player player) {
        for (Player p : playerList) {
            if (StringUtils.equals(p.getName(), player.getName())) {
                return true;
            }
        }

        return false;
    }

    /**
     * Determine if there are any players in the game left
     *
     * @return false if the game has no players, true otherwise
     */
    public boolean isEmpty() {
        return playerList.isEmpty();
    }

    /**
     * Determine if the game is locked
     *
     * @return true if locked, false otherwise
     */
    public boolean isLocked() {
        return locked;
    }

    /**
     * Locks the game if the locking user is the managing user
     *
     * @param currentPlayerName the name of the manager user
     * @return true if the game was locked, false otherwise
     */
    public boolean lock(String currentPlayerName) {
        if (managingPlayer != null && StringUtils.equals(managingPlayer.getName(), currentPlayerName)) {
            this.locked = true;
            return true;
        }
        return false;
    }

    /**
     * Ends the current game
     */
    public void end() {
        this.locked = true;
        this.status = STATUS_INACTIVE;
    }

    /**
     * Whether the game has ended
     *
     * @return true if the game ended, false otherwise
     */
    public boolean ended() {
        return status == STATUS_INACTIVE;
    }

    public Board getBoard() {
        return board;
    }
}
