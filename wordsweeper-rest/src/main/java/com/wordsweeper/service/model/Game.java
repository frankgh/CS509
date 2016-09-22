package com.wordsweeper.service.model;

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

    /*
    0: Active
    1: Inactive
     */
    int status;

    public Game(Player player) {
        this.status =STATUS_ACTIVE;
        this.board = new Board(DEFAULT_BOARD_SIZE);
        this.playerList = new ArrayList<>();
        this.playerList.add(player);
        this.managingPlayer = player;
    }

    /**
     * Adds a new player to the current game
     *
     * @param player the new player
     * @return false if there is a player with the same game in the game, true otherwise
     */
    public boolean addPlayer(Player player) {

if (containsPlayer(player)) {
    return false;
}

playerList.add(player);
        return true;
    }

    /**
     * Resets the status of the board
     * and all the player scores.
     */
    public void reset () {
        this.board.reset();

        for (Player player:playerList) {
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
        for(Player p:playerList) {
            if (StringUtils.equals(p.getName(), player.getName())) {
                return true;
            }
        }

        return false;
    }
}
