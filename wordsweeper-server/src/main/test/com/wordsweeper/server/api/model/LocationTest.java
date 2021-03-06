package com.wordsweeper.server.api.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * This class test location with row and column.
 * @author george
 */
public class LocationTest {
    @Test
    public void constructor() throws Exception {
        Location loc = new Location();
        loc.column = 3;
        loc.row = 5;

        assertEquals(5, loc.getRow());
        assertEquals(3, loc.getColumn());

        assertEquals((loc.column + 1) + "," + (loc.row + 1), loc.toString());
    }

}