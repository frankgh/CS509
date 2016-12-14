package com.wordsweeper.core.xml;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class ListGamesRequestTest {

    @Test
    public void constructor() throws Exception {
        ListGamesResponse res = new ListGamesResponse();
        assertEquals(res.getGameBrief().size(), 0);


    }
}
