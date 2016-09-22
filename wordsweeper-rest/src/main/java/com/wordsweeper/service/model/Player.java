package com.wordsweeper.service.model;

import java.util.HashMap;

/**
 * Created by francisco on 9/13/16.
 */
public class Player {

    private String name;
    private int offsetX;
    private int offsetY;
    private int score;

    public Player(String playerName) {
        this.name = playerName;
        this.score = 0;
        this.offsetX = 0;
        this.offsetY = 0;
    }

    public String getName() {
        return name;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
