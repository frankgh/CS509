package com.wordsweeper.service.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author francisco
 * This class test letter in board.
 */
public class LetterTest {
    @Test
    public void constructor() throws Exception {
        Letter letter = new Letter('A');
        assertEquals("A", letter.printCharacter());

        letter = new Letter('Q');
        assertEquals('Q', letter.getCharacter());
        assertEquals("Qu", letter.printCharacter());

    }

}