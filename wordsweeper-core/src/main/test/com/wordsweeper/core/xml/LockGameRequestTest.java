package com.wordsweeper.core.xml;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class LockGameRequestTest {

    @Test
    public void constructor() throws Exception {
        LockGameRequest res = new LockGameRequest();

        res.setGameId("testID");
        assertEquals(res.getGameId(), "testID");


    }
}
