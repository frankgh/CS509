package com.wordsweeper.server.xml;

import com.wordsweeper.server.ServerThread;
import com.wordsweeper.server.api.model.Board;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;



public class ShowGameStateRequestTest {

	@Test
	public void constructor() throws Exception {
		ShowGameStateRequest req = new ShowGameStateRequest();
		
		req.setGameId("testID");
		assertEquals(req.getGameId(), "testID");
		
		
	}
}
