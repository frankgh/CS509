package com.wordsweeper.server.api.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * This class test letter in board.
 * @author francisco
 */
public class LetterTest {
    @Test
    public void constructor() throws Exception {
        Letter let = new Letter();
        let.character = "Q";

        assertEquals("Qu", let.printCharacter());
        assertEquals("Q", let.getCharacter());
    }

}