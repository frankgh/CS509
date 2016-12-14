package com.wordsweeper.server.controller;

import com.wordsweeper.server.Server;
import com.wordsweeper.server.ServerThread;
import com.wordsweeper.server.api.model.Board;
import com.wordsweeper.server.api.model.Game;
import com.wordsweeper.server.api.model.Player;
import com.wordsweeper.server.model.ClientState;
import com.wordsweeper.server.model.ServerModel;
import com.wordsweeper.server.xml.CreateGameRequest;
import com.wordsweeper.server.xml.ExitGameRequest;
import com.wordsweeper.server.xml.Request;
import com.wordsweeper.server.xml.Response;

import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;



public class EmptyHandlerTest {

	@Test
	public void constructor() throws Exception {
		EmptyHandler testHand = new EmptyHandler();
		Request testReq = new Request();
		Game testGame = new Game();
		ServerThread client = Mockito.mock(ServerThread.class);
		Response testRes = Mockito.mock(Response.class);
		
		assertTrue(testHand.canProcess(testReq));
		assertFalse(testHand.process(client, testReq).isSuccess());
		assertNull(testHand.execute(client, testReq, testGame));
		assertFalse(testHand.setOnSuccessResponse(testReq, testRes));
		testHand.setOnFailureResponse(testReq, testRes);
	}		
}
