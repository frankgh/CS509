package com.wordsweeper.server.api.model;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by francisco on 10/12/16.
 *
 * @author francisco
 */
public class Letter {

    /**
     * The Score.
     */
    String score;

    /**
     * The Character.
     */
    String character;

    /**
     * Gets score.
     *
     * @return the score
     */
    public String getScore() {
        return score;
    }

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
