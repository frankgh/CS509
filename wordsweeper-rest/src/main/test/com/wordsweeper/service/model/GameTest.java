package com.wordsweeper.service.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class GameTest {
	@Test
	public void constructor() throws Exception {
        Game game = new Game();
        assertNull(game.getManagingPlayerName());
        
        Player player = new Player("testPlayer");
        Game game1 = new Game(player);
        assertEquals("testPlayer", game1.getManagingPlayerName());
        assertTrue(game1.addPlayer("test123"));
                
        player = new Player("testPlayer1");
        Game game2 = new Game(player, "password");
        assertTrue(game2.addPlayer("test123", "password"));
        assertFalse(game2.addPlayer("test123", "password"));
        assertFalse(game2.addPlayer("test12", "passw1rd"));
        assertEquals("password", game2.getPassword());
        assertEquals(player, game2.getPlayer("testPlayer1"));
        
        
        game2.removePlayer("testPlayer1");
        assertNull(game2.getPlayer("testPlayer1"));
        assertEquals(game2.getManagingPlayerName(), "test123");
        
        game2.addPlayer("testPlayer2", "password");
        Location temp = game2.getPlayer("testPlayer2").offset;
        game2.getPlayer("testPlayer2").setScore(5);
        assertEquals(5, game2.getPlayer("testPlayer2").getScore());
        game2.reset();
        assertEquals(0, game2.getPlayer("testPlayer2").getScore());
        
        
        assertFalse(game2.isLocked());
        game2.lock();
        assertFalse(game2.addPlayer("test1", "password"));
        assertTrue(game2.isLocked());
        
        game2 = new Game(player, "password");
        assertTrue(game2.removePlayer(player.getName()));
        assertFalse(game2.removePlayer(player.getName()));
        assertTrue(game2.isEmpty());
        assertFalse(game2.ended());
        game2.end();
        assertFalse(game2.addPlayer("test1", "password"));
	}

}
