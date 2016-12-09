package com.wordsweeper.service.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by francisco on 10/4/16.
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
        
        int hash1 = location.hashCode();
        int hash2 = location1.hashCode();
        assertFalse(hash1 == hash2);
        
        location.setRow(1);
        assertTrue(location1.equals(location));
        assertFalse(location == location1);
        
        Location loc2 = null;
        assertFalse(location.equals(loc2));
        
    }

}