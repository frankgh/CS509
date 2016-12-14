package com.wordsweeper.core.xml;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class ResetGameResponseTest {

    @Test
    public void constructor() throws Exception {
        ResetGameResponse req = new ResetGameResponse();

        req.setGameId("testID");
        assertEquals(req.getGameId(), "testID");


    }
}
