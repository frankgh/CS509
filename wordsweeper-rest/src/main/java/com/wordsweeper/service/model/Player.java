package com.wordsweeper.service.model;

/**
 * Created by francisco on 9/13/16.
 */
public class Player {

    private String name;
    private Location offset;
    private int score;

    public Player(String playerName) {
        this.name = playerName;
        this.score = 0;
        this.offset = new Location(0, 0);
    }

    public String getName() {
        return name;
    }

    protected void setScore(int score) {
        this.score = score;
    }
}
