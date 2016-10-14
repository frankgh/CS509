package com.wordsweeper.server.model;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by francisco on 10/12/16.
 */
public class Cell {
    Letter letter;

    public String printCharacter() {
        return letter.printCharacter();
    }
}
