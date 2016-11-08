package com.wordsweeper.server.api.model;

import java.util.List;

/**
 * Created by francisco on 10/12/16.
 * @author francisco
 */
public class Game {

    /**
     * The Managing player name.
     */
    String managingPlayerName;

    /**
     * The Locked.
     */
    String locked;

    /**
     * The Board.
     */
    Board board;

    /**
     * The Password.
     */
    String password;

    /**
     * The Player list.
     */
    List<Player> playerList;

    /**
     * The Unique id.
     */
    String uniqueId;

    /**
     * Gets managing player name.
     *
     * @return the managing player name
     */
    public String getManagingPlayerName() {
        return managingPlayerName;
    }

    /**
     * Sets managing player name.
     *
     * @param managingPlayerName the managing player name
     */
    public void setManagingPlayerName(String managingPlayerName) {
        this.managingPlayerName = managingPlayerName;
    }

    /**
     * Gets locked.
     *
     * @return the locked
     */
    public String getLocked() {
        return locked;
    }

    /**
     * Sets locked.
     *
     * @param locked the locked
     */
    public void setLocked(String locked) {
        this.locked = locked;
    }

    /**
     * Gets board.
     *
     * @return the board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Sets board.
     *
     * @param board the board
     */
    public void setBoard(Board board) {
        this.board = board;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets player list.
     *
     * @return the player list
     */
    public List<Player> getPlayerList() {
        return playerList;
    }

    /**
     * Sets player list.
     *
     * @param playerList the player list
     */
    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }

    /**
     * Gets unique id.
     *
     * @return the unique id
     */
    public String getUniqueId() {
        return uniqueId;
    }

    /**
     * Sets unique id.
     *
     * @param uniqueId the unique id
     */
    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }
}
