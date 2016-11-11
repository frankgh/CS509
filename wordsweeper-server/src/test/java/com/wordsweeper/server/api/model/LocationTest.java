package com.wordsweeper.server.api.model;

import org.junit.Test;

import com.wordsweeper.server.api.model.Letter;

import static org.junit.Assert.assertEquals;

/**
 * Created by george on 11/11/16.
 */
public class LocationTest {
    @Test
    public void constructor() throws Exception {
        Location loc = new Location();
        loc.column = 3;
        loc.row = 5;
        
        assertEquals(5, loc.getRow());
        assertEquals(3, loc.getColumn());
        
        assertEquals("6,4", loc.toString());
    }

}