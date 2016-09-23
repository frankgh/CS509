package com.wordsweeper.service.model;

import com.wordsweeper.service.util.RandomUtil;

/**
 * Created by francisco on 9/13/16.
 */
public class Cell {

    public static final int MAX_CELL_MULTIPLIER = 10;

    Letter letter;
    int row;
    int column;
    int multiplier;

    public Cell() {
        this.letter = new Letter(RandomUtil.getRandomCharacter(), 1);
    }

    public void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }
}
