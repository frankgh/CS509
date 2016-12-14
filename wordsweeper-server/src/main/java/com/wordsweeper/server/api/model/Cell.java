package com.wordsweeper.server.api.model;

/**
 * This class specifying cells' attributes in a board.
 *
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
    
    public void setLetter(Letter letter){
    	this.letter = letter;
    }
}
