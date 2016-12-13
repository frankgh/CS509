package com.wordsweeper.server.xml;

import com.wordsweeper.server.ServerThread;
import com.wordsweeper.server.api.model.Board;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;



public class RepositionBoardRequestTest {

	@Test
	public void constructor() throws Exception {
		RepositionBoardRequest req = new RepositionBoardRequest();
		
		req.setGameId("testID");
		assertEquals(req.getGameId(), "testID");
		
		req.setName("testPlayer");
		assertEquals(req.getName(), "testPlayer");
		
		req.setColChange(1);
		assertTrue(req.getColChange() == 1);
		
		req.setRowChange(2);
		assertTrue(req.getRowChange() == 2);
		
	}
}
