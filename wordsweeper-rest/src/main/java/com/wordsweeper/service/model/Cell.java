package com.wordsweeper.service.model;

import com.wordsweeper.service.util.RandomUtil;

/**
 * Created by francisco on 9/13/16.
 */
public class Cell {

    public static final int DEFAULT_CELL_MULTIPLIER = 1;
    public static final int MAX_CELL_MULTIPLIER = 10;

    Letter letter;
    int multiplier;

    public Cell() {
        this.letter = new Letter(RandomUtil.getRandomCharacter(), 1);
        this.multiplier = DEFAULT_CELL_MULTIPLIER;
    }

    public void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }
}
