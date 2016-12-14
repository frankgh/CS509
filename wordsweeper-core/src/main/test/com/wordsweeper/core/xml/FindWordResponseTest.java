package com.wordsweeper.core.xml;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class FindWordResponseTest {

    @Test
    public void constructor() throws Exception {
        FindWordResponse res = new FindWordResponse();

        res.setGameId("testID");
        assertEquals(res.getGameId(), "testID");

        res.setName("testPlayer");
        assertEquals(res.getName(), "testPlayer");

        res.setScore((long) 123);
        assertEquals(res.getScore(), (long) 123);

    }
}
