package com.wordsweeper.server.xml;

import com.wordsweeper.server.ServerThread;
import com.wordsweeper.server.api.model.Board;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;



public class JoinGameRequestTest {

	@Test
	public void constructor() throws Exception {
		JoinGameRequest res = new JoinGameRequest();
		
		res.setGameId("testID");
		assertEquals(res.getGameId(), "testID");
		
		res.setName("testPlayer");
		assertEquals(res.getName(), "testPlayer");
		
		res.setPassword("testPass");
		assertEquals(res.getPassword(), "testPass");
		
	}
}
