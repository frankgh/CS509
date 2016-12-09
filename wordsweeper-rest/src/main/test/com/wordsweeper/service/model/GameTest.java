package com.wordsweeper.service.model;

import static org.junit.Assert.*;

import java.util.ArrayList;

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
        assertTrue(game2.ended());
        assertFalse(game2.addPlayer("test1", "password"));
        
        game2 = new Game(player, "password");
        System.out.println(game2.getPlayerList().size());
        assertEquals(game2.getBoard().getRows(), 7);
        game2.addPlayer("testPlayer2", "password");
        assertEquals(game2.getBoard().getRows(), 7);
        game2.addPlayer("testPlayer3", "password");
        assertEquals(game2.getBoard().getRows(), 7);
        game2.addPlayer("testPlayer4", "password");
        assertEquals(game2.getBoard().getRows(), 8);
        game2.addPlayer("testPlayer5", "password");
        assertEquals(game2.getBoard().getRows(), 9);
        
        Player playerTest = game2.getPlayer("testPlayer1");
        playerTest.setOffset(new Location(0,0));
        assertFalse(game2.repositionBoard(playerTest, -1, 0));
        assertFalse(game2.repositionBoard(playerTest, 0, -1));
        assertFalse(game2.repositionBoard(playerTest, 0, 0));
        assertTrue(game2.repositionBoard(playerTest, 0, 1));        
        assertTrue(game2.repositionBoard(playerTest, 1, 0));       
        assertEquals(playerTest.getOffset().getRow(),1);
        assertEquals(playerTest.getOffset().getRow(),1);
        game2 = new Game(player, "password");
        ArrayList<Cell> list = (ArrayList<Cell>) game2.getBoard().getCellList();
        list.get(0).getLetter().character = 'y';
        list.get(1).getLetter().character = 'e';
        list.get(2).getLetter().character = 's';
        playerTest.setOffset(new Location(0,0));
        ArrayList<Location> locTest = new ArrayList<Location>();
        locTest.add(new Location(0,0));
        locTest.add(new Location(0,1));
        locTest.add(new Location(0,2));
        Word wordTest = new Word("yes", locTest);
        assertTrue(game2.validateWord(playerTest, wordTest));
        assertEquals(game2.calculateWordScore(wordTest), 560);
        game2.getBoard().bonusCellLocation = new Location(0,0);
        assertEquals(game2.calculateWordScore(wordTest), 5600);
        
        
        
        
	}

}
