package com.wordsweeper.service.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by francisco on 10/4/16.
 */
public class LetterTest {

    @Test
    public void constructor() throws Exception {
        Letter letter = new Letter('A');
        assertEquals('A', letter.getCharacter());

        letter = new Letter('B');
        assertEquals('B', letter.getCharacter());
    }

    @Test
    public void setScore() throws Exception {

    }

    @Test
    public void getCharacter() throws Exception {

    }

    @Test
    public void getScore() throws Exception {

    }

}