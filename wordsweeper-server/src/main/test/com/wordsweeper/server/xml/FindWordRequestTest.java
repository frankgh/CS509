package com.wordsweeper.server.xml;

import com.wordsweeper.server.ServerThread;
import com.wordsweeper.server.api.model.Board;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;



public class FindWordRequestTest {

	@Test
	public void constructor() throws Exception {
		FindWordRequest res = new FindWordRequest();
		assertEquals(res.getCell().size(), 0);
		
		res.setGameId("testID");
		assertEquals(res.getGameId(), "testID");
		
		res.setName("testPlayer");
		assertEquals(res.getName(), "testPlayer");
		
		res.setWord("testWord");
		assertEquals(res.getWord(), "testWord");
		
		
	}
}
