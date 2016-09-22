package com.wordsweeper.service.model;

/**
 * Created by francisco on 9/13/16.
 */
public class Letter {
    char character;
    int score;

    Letter(char character, int score) {
        this.score = score;
        this.character = character;
    }
}
