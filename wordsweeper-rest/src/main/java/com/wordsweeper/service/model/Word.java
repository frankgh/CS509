package com.wordsweeper.service.model;

import java.util.List;

/**
 * Created by francisco on 9/13/16.
 */
public class Word {

    String word;
    List<Cell> cellList;

    public Word(String word, List<Cell> cellList) {
        this.word = word;
        this.cellList = cellList;
    }
}
