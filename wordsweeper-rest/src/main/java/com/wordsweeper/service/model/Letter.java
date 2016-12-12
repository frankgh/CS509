package com.wordsweeper.service.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 * Class letter from WordSweeper board game.
 *
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
     * Instantiates a new Letter.
     */
    protected Letter() {
    }

    /**
     * Instantiates a new Letter.
     *
     * @param character the character
     */
    Letter(char character) {
        this.character = character;
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
     * Print the character for this Letter. If the character is Q
     * we return `Qu`
     *
     * @return the character
     */
    public String printCharacter() {
        if (character == 'Q') {
            return "Qu";
        }
        return Character.toString(character);
    }
}
