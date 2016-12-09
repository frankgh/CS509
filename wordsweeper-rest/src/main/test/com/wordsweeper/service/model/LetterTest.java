package com.wordsweeper.service.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by francisco on 10/4/16.
 */
public class LetterTest {
    @Test
    public void constructor() throws Exception {
        Letter letter = new Letter();
        letter.character = 'A';
        assertEquals("A", letter.printCharacter());

        letter = new Letter('Q');
        assertEquals('Q', letter.getCharacter());
        assertEquals("Qu", letter.printCharacter());

    }

}