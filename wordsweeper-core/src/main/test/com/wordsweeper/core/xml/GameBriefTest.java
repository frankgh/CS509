package com.wordsweeper.core.xml;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class GameBriefTest {

    @Test
    public void constructor() throws Exception {
        GameBrief testBrief = new GameBrief();

        testBrief.setGameId("testID");
        assertEquals(testBrief.getGameId(), "testID");

        testBrief.setPlayers(3);
        assertTrue(testBrief.getPlayers() == 3);

    }
}
