package com.wordsweeper.service.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Feed the Letter Frequency given with words from a dictionary.
 * This class will generate the Letter Frequency and keep an ordered
 * list of frequency probabilities for each letter
 *
 * @author francisco
 */
class LetterFrequency {

    /**
     * The Character count map.
     */
    Map<Character, Double> characterCountMap;

    /**
     * Instantiates a new Letter frequency.
     */
    public LetterFrequency() {
        characterCountMap = new HashMap<>();
    }

    /**
     * Feed word to the letter frequency.
     *
     * @param word the word
     */
    void feedWord(final String word) {
        final String s = word.toUpperCase();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch >= 'A' && ch <= 'Z') {
                double count = 0;
                if (characterCountMap.containsKey(ch)) {
                    count = characterCountMap.get(ch);
                }
                count++;
                characterCountMap.put(ch, count);
            }
        }
    }

    /**
     * Build the letter frequency model
     */
    void build() {
        double total = Math.max(1, MapUtil.sumValues(characterCountMap)); // Do not allow division by 0
        Map<Character, Double> sortedMap = MapUtil.sortByValue(characterCountMap, MapUtil.DESCENDING);

        for (Map.Entry<Character, Double> entry : sortedMap.entrySet()) {
            double entryValue = entry.getValue() / total;
            entry.setValue(entryValue);
        }

        characterCountMap = sortedMap;
    }

    /**
     * Get the character in the specified value range
     *
     * @param value the value
     * @return the letter in that value
     */
    char getCharacter(double value) {
        double previousValue = 0;
        for (Map.Entry<Character, Double> entry : characterCountMap.entrySet()) {

            if (value <= (entry.getValue() + previousValue)) {
                return entry.getKey();
            }

            previousValue += entry.getValue();
        }

        System.err.println("Unable to return letter for value " + value);
        return 'E';
    }
}
