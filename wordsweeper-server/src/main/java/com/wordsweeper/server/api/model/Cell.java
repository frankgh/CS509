package com.wordsweeper.server.api.model;

/**
 * Created by francisco on 10/12/16.
 * @author francisco
 */
public class Cell {
    /**
     * The Letter.
     */
    Letter letter;

    /**
     * Print character string.
     *
     * @return the string
     */
    public String printCharacter() {
        return letter.printCharacter();
    }
}
