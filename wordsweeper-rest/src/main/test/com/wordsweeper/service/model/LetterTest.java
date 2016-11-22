package com.wordsweeper.service.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by francisco on 10/4/16.
 */
public class LetterTest {
    @Test
    public void constructor() throws Exception {
        Letter letter = new Letter('A', 5);
        assertEquals("A", letter.printCharacter());
        assertEquals(5, letter.getScore());

        letter = new Letter('Q', 10);
        assertEquals(10, letter.getScore());
        letter.setScore(5);
        assertEquals('Q', letter.getCharacter());
        assertEquals("Qu", letter.printCharacter());
        assertEquals(5, letter.getScore());

    }

}