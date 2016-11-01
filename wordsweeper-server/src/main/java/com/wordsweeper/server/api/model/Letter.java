package com.wordsweeper.server.api.model;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by francisco on 10/12/16.
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
        String c = new String(getCharacter());

        if (StringUtils.equalsIgnoreCase(c, "Q")) {
            return "Qu";
        }
        return c;
    }
}
