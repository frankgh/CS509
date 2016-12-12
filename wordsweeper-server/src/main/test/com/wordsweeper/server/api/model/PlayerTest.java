package com.wordsweeper.server.api.model;

import org.junit.Test;

import com.wordsweeper.server.api.model.Letter;

import static org.junit.Assert.assertEquals;

/**
 * This class test player's status in a game.
 * @author george.
 */
public class PlayerTest {
    @Test
    public void constructor() throws Exception {
    	Player p = new Player();
    	p.name = "test";
    	p.score = 100;
    	Location l = new Location();
    	l.column = 3;
    	l.row = 4;
    	p.offset = l;
    	
    	assertEquals("test", p.getName());
    	assertEquals(3, p.getOffset().getColumn());
    	assertEquals(4, p.getOffset().getRow());
    	assertEquals(100, p.getScore());
    }

}