package com.wordsweeper.server.model;

import java.util.List;

/**
 * Created by francisco on 10/12/16.
 */
public class Game {

    private String managingPlayerName;

    private String locked;

    private Board board;

    private String password;

    private List<Player> playerList;

    private String uniqueId;
    
    public String getManagingPlayerName() {
        return managingPlayerName;
    }

    public void setManagingPlayerName(String managingPlayerName) {
        this.managingPlayerName = managingPlayerName;
    }

    public String getLocked() {
        return locked;
    }

    public void setLocked(String locked) {
        this.locked = locked;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }
}
