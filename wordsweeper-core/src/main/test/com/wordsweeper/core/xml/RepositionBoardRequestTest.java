package com.wordsweeper.core.xml;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class RepositionBoardRequestTest {

    @Test
    public void constructor() throws Exception {
        RepositionBoardRequest req = new RepositionBoardRequest();

        req.setGameId("testID");
        assertEquals(req.getGameId(), "testID");

        req.setName("testPlayer");
        assertEquals(req.getName(), "testPlayer");

        req.setColChange(1);
        assertTrue(req.getColChange() == 1);

        req.setRowChange(2);
        assertTrue(req.getRowChange() == 2);

    }
}
