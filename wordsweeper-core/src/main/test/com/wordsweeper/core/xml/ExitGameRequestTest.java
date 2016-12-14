package com.wordsweeper.core.xml;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class ExitGameRequestTest {

    @Test
    public void constructor() throws Exception {
        ExitGameRequest res = new ExitGameRequest();

        res.setName("testPlayer");
        assertEquals(res.getName(), "testPlayer");

        res.setGameId("testID");
        assertEquals(res.getGameId(), "testID");

    }
}
