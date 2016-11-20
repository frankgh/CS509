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

    /**
     * Determine if the word contains duplicate cells in the list
     *
     * @return true if there are duplicate cells, false otherwise
     */
    public boolean containsDuplicateCells() {

        for (int i = 0; i < locations.size() - 1; i++) {
            for (int j = i + 1; j < locations.size(); j++) {

                if (locations.get(j).equals(locations.get(i))) {
                    return true;
                }
            }
        }

        return false;
    }
}
