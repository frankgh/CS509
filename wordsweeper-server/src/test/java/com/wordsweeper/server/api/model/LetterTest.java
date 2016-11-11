package com.wordsweeper.server.api.model;

import org.junit.Test;

import com.wordsweeper.server.api.model.Letter;

import static org.junit.Assert.assertEquals;

/**
 * Created by francisco on 10/4/16.
 */
public class LetterTest {
    @Test
    public void constructor() throws Exception {
        Letter let = new Letter();
        let.character = "Q";
        let.score = "10";
        
        assertEquals("10", let.getScore());
        assertEquals("Qu", let.printCharacter());
        
    }

}