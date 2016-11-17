package com.wordsweeper.service.model;

import java.util.List;

/**
 * Represents a word
 *
 * @author francisco
 */
public class Word {

    /**
     * The Word.
     */
    String word;
    /**
     * The Location list.
     */
    List<Location> locations;

    /**
     * Instantiates a new Word.
     *
     * @param word      the word
     * @param locations the list of locations
     */
    public Word(String word, List<Location> locations) {
        this.word = word;
        this.locations = locations;
    }
    
    public int getWordLength(){
    	return this.locations.size();
    }
}
