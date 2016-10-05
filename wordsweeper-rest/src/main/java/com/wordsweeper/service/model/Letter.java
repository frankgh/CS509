package com.wordsweeper.service.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 * Created by francisco on 9/13/16.
 */
@Embeddable
public class Letter {

    @Column(name = "character")
    @NotNull
    char character;
    @Column(name = "score")
    @NotNull
    int score;

    protected Letter() {
    }

    Letter(char character, int score) {
        this.score = score;
        this.character = character;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public char getCharacter() {
        return character;
    }

    public int getScore() {
        return score;
    }
}
