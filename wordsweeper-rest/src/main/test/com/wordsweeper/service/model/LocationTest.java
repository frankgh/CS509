package com.wordsweeper.service.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * This class test location with row and column.
 * @author francisco
 */
public class LocationTest {
    @Test
    public void constructor() throws Exception {
        Location location = new Location(3, 5);
        assertEquals(3, location.getRow());
        assertEquals(5, location.getColumn());

        location.setRow(2);
        assertEquals(2, location.getRow());
        
        location.setColumn(1);
        assertEquals(1, location.getColumn());
        
        Location location1 = new Location();
        location1.setRow(1);
        location1.setColumn(1);
        assertEquals(1, location1.getRow());
        assertEquals(1, location1.getColumn());
        
    }

}