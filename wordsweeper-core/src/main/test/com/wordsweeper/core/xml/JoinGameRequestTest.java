package com.wordsweeper.core.xml;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class JoinGameRequestTest {

    @Test
    public void constructor() throws Exception {
        JoinGameRequest res = new JoinGameRequest();

        res.setGameId("testID");
        assertEquals(res.getGameId(), "testID");

        res.setName("testPlayer");
        assertEquals(res.getName(), "testPlayer");

        res.setPassword("testPass");
        assertEquals(res.getPassword(), "testPass");

    }
}
