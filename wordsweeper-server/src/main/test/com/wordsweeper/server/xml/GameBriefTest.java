package com.wordsweeper.server.xml;

import com.wordsweeper.server.ServerThread;
import com.wordsweeper.server.api.model.Board;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;



public class GameBriefTest {

	@Test
	public void constructor() throws Exception {
		GameBrief testBrief = new GameBrief();
		
		testBrief.setGameId("testID");
		assertEquals(testBrief.getGameId(), "testID");
		
		testBrief.setPlayers(3);
		assertTrue(testBrief.getPlayers() == 3);
		
		}
}
