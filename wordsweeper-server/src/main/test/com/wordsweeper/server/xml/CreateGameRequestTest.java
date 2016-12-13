package com.wordsweeper.server.xml;

import com.wordsweeper.server.ServerThread;
import com.wordsweeper.server.api.model.Board;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;



public class CreateGameRequestTest {

	@Test
	public void constructor() throws Exception {
		CreateGameRequest res = new CreateGameRequest();
		
		res.setName("testPlayer");
		assertEquals(res.getName(), "testPlayer");
		
		res.setPassword("testPass");
		assertEquals(res.getPassword(), "testPass");
		
	}
}
