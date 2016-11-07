package com.wordsweeper.service.model;

import java.util.List;

/**
 * Created by francisco on 9/13/16.
 * @author francisco
 */
public class Word {

    /**
     * The Word.
     */
    String word;
    /**
     * The Cell list.
     */
    List<Cell> cellList;

    /**
     * Instantiates a new Word.
     *
     * @param word     the word
     * @param cellList the cell list
     */
    public Word(String word, List<Cell> cellList) {
        this.word = word;
        this.cellList = cellList;
    }
}
