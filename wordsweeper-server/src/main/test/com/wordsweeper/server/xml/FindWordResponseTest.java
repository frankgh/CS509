package com.wordsweeper.server.xml;

import com.wordsweeper.server.ServerThread;
import com.wordsweeper.server.api.model.Board;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;



public class FindWordResponseTest {

	@Test
	public void constructor() throws Exception {
		FindWordResponse res = new FindWordResponse();
		
		res.setGameId("testID");
		assertEquals(res.getGameId(), "testID");
		
		res.setName("testPlayer");
		assertEquals(res.getName(), "testPlayer");
		
		res.setScore((long) 123);
		assertEquals(res.getScore(), (long) 123);
		
	}
}
