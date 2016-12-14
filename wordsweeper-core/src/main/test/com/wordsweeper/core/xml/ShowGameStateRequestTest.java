package com.wordsweeper.core.xml;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class ShowGameStateRequestTest {

    @Test
    public void constructor() throws Exception {
        ShowGameStateRequest req = new ShowGameStateRequest();

        req.setGameId("testID");
        assertEquals(req.getGameId(), "testID");


    }
}
