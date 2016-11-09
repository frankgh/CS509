package com.wordsweeper.service.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 * Created by francisco on 9/13/16.
 * @author francisco
 */
@Embeddable
public class Letter {

    /**
     * The Character.
     */
    @Column(name = "character")
    @NotNull
    char character;
    /**
     * The Score.
     */
    @Column(name = "score")
    @NotNull
    int score;

    /**
     * Instantiates a new Letter.
     */
    protected Letter() {
    }

    /**
     * Instantiates a new Letter.
     *
     * @param character the character
     * @param score     the score
     */
    Letter(char character, int score) {
        this.score = score;
        this.character = character;
    }

    /**
     * Sets score.
     *
     * @param score the score
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Gets character.
     *
     * @return the character
     */
    public char getCharacter() {
        return character;
    }

    /**
     * Gets score.
     *
     * @return the score
     */
    public int getScore() {
        return score;
    }
}
