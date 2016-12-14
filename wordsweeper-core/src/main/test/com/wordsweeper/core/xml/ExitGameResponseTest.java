package com.wordsweeper.core.xml;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class ExitGameResponseTest {

    @Test
    public void constructor() throws Exception {
        ExitGameResponse res = new ExitGameResponse();

        res.setGameId("testID");
        assertEquals(res.getGameId(), "testID");


    }
}
