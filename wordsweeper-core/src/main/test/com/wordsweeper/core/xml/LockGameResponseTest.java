package com.wordsweeper.core.xml;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class LockGameResponseTest {

    @Test
    public void constructor() throws Exception {
        LockGameResponse res = new LockGameResponse();

        res.setGameId("testID");
        assertEquals(res.getGameId(), "testID");


    }
}
