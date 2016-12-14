package com.wordsweeper.core.xml;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class JoinGameResponseTest {

    @Test
    public void constructor() throws Exception {
        JoinGameResponse res = new JoinGameResponse();

        res.setGameId("testID");
        assertEquals(res.getGameId(), "testID");

    }
}
