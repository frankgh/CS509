package com.wordsweeper.service.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by francisco on 10/4/16.
 */
public class WordTest {
    @Test
    public void constructor() throws Exception {
        Letter letter = new Letter('A', 5);
        assertEquals('A', letter.getCharacter());
        assertEquals(5, letter.getScore());

        letter = new Letter('B', 10);
        assertEquals('B', letter.getCharacter());
        assertEquals(10, letter.getScore());
    }

}