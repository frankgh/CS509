package com.wordsweeper.core.xml;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class BoardResponseTest {

    @Test
    public void constructor() throws Exception {
        BoardResponse res = new BoardResponse();
        assertEquals(res.getPlayer().size(), 0);

        res.setGameId("testID");
        assertEquals(res.getGameId(), "testID");

        res.setSize(7);
        assertTrue(res.getSize() == 7);

        res.setManagingUser("testUser");
        assertEquals(res.getManagingUser(), "testUser");

        res.setContents("testContents");
        assertEquals(res.getContents(), "testContents");

        res.setBonus("testBonus");
        assertEquals(res.getBonus(), "testBonus");

    }
}
