package com.wordsweeper.service.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by francisco on 10/4/16.
 */
public class PlayerTest {
    @Test
    public void constructor() throws Exception {
        Player player = new Player("testPlayer");
        assertEquals("testPlayer", player.getName());
        assertEquals(0, player.getScore());
        assertEquals(0, player.getOffset().getColumn());
        assertEquals(0, player.getOffset().getRow());
        
        player.setOffset(new Location(1,2));
        assertEquals(2, player.getOffset().getColumn());
        assertEquals(1, player.getOffset().getRow());
       
        player.setScore(3);
        assertEquals(3, player.getScore());
        
        player.setOffset(new Location(0,0));
        Location location1 = new Location();
        location1.setRow(3);
        location1.setColumn(3);
        assertTrue(player.isLocationInPlayerBoard(location1));
        
        location1.setRow(4);
        location1.setColumn(4);
        assertFalse(player.isLocationInPlayerBoard(location1));
        
    }	

}