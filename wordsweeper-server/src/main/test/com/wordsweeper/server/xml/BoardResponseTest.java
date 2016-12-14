package com.wordsweeper.server.xml;

import com.wordsweeper.server.ServerThread;
import com.wordsweeper.server.api.model.Board;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;



public class BoardResponseTest {

	@Test
	public void constructor() throws Exception {
		BoardResponse res = new BoardResponse();
		assertEquals(res.getPlayer().size(), 0);
		
		res.setGameId("testID");
		assertEquals(res.getGameId(), "testID");
		
		res.setSize(7);
		assertTrue(res.getSize() == 7);
		
		res.setManagingUser("testUser");
		assertEquals(res.getManagingUser(), "testUser");
		
		res.setContents("testContents");
		assertEquals(res.getContents(), "testContents");
		
		res.setBonus("testBonus");
		assertEquals(res.getBonus(), "testBonus");
		
	}
}
