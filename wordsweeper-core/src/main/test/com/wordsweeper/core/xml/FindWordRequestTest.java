package com.wordsweeper.core.xml;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class FindWordRequestTest {

    @Test
    public void constructor() throws Exception {
        FindWordRequest res = new FindWordRequest();
        assertEquals(res.getCell().size(), 0);

        res.setGameId("testID");
        assertEquals(res.getGameId(), "testID");

        res.setName("testPlayer");
        assertEquals(res.getName(), "testPlayer");

        res.setWord("testWord");
        assertEquals(res.getWord(), "testWord");


    }
}
