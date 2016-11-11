package com.wordsweeper.service.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

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
    }	

}