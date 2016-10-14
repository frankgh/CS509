package com.wordsweeper.server.model;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by francisco on 10/12/16.
 */
public class Letter {

    private String score;

    private String character;

    public String getScore() {
        return score;
    }

    public String getCharacter() {
        return character;
    }

    public String printCharacter() {
        String c = new String(getCharacter());

        if (StringUtils.equalsIgnoreCase(c, "Q")) {
            return "Qu";
        }
        return c;
    }
}
