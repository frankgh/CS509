package com.wordsweeper.core.xml;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class ResetGameRequestTest {

    @Test
    public void constructor() throws Exception {
        ResetGameRequest req = new ResetGameRequest();

        req.setGameId("testID");
        assertEquals(req.getGameId(), "testID");


    }
}
