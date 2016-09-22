package com.wordsweeper.service.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by francisco on 9/13/16.
 */
public class Game {

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
        this.board = new Board(DEFAULT_BOARD_SIZE);
        this.playerList = new ArrayList<Player>();
        this.playerList.add(player);
    }
}
