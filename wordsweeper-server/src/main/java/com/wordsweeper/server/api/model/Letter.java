package com.wordsweeper.server.api.model;

import org.apache.commons.lang3.StringUtils;

/**
 * Class letter from WordSweeper board game.
 *
 * @author francisco
 */
public class Letter {

    /**
     * The Character.
     */
    String character;

    /**
     * Gets character.
     *
     * @return the character
     */
    public String getCharacter() {
        return character;
    }

    /**
     * Print character string.
     *
     * @return the string
     */
    public String printCharacter() {
        if (StringUtils.equalsIgnoreCase(character, "Q")) {
            return "Qu";
        }
        return character;
    }
}
