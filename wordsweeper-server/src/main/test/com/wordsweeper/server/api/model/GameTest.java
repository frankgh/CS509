package com.wordsweeper.server.api.model;

import org.junit.Test;

import com.wordsweeper.server.api.model.Letter;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

/**
 * Created by george on 11/11/16.
 */
public class GameTest {
    @Test
    public void constructor() throws Exception {
        Game g = new Game();
        g.setManagingPlayerName("testManager");
        assertEquals("testManager", g.getManagingPlayerName());
        
        g.setLocked("locked");
        assertEquals("locked", g.getLocked());
        
        Board b = new Board();
        b.columns = 7;
        g.setBoard(b);
        assertEquals(b, g.getBoard());
        
        g.setPassword("12345");
		assertEquals(g.getPassword(), "12345");
		
		g.setUniqueId("t1e2s3t4");
		assertEquals(g.getUniqueId(), "t1e2s3t4");
		
		ArrayList<Player> playerList = new ArrayList<Player>();
		playerList.add(new Player());
		g.setPlayerList(playerList);
		assertEquals(g.getPlayerList(), playerList);

		
    }

}