package com.wordsweeper.server.model;

/**
 * Created by francisco on 10/12/16.
 */
public class Player {

    private String name;
    private Location offset;
    private int score;

    public String getName() {
        return name;
    }

    public Location getOffset() {
        return offset;
    }

    public int getScore() {
        return score;
    }
}
