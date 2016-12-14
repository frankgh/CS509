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
import com.wordsweeper.server.xml.LockGameRequest;
import com.wordsweeper.server.xml.Request;
import com.wordsweeper.server.xml.ResetGameRequest;
import com.wordsweeper.server.xml.Response;
import com.wordsweeper.server.xml.ShowGameStateRequest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;



public class WordSweeperProtocolHandlerTest {

	@Test
	public void constructor() throws Exception {
		ServerModel server = new ServerModel();
		WordSweeperProtocolHandler testHand = new WordSweeperProtocolHandler(server);
		Request testReq = Mockito.mock(Request.class);
		
		assertNull(testHand.chain.next);
		EmptyHandler test = new EmptyHandler();
		testHand.registerHandler(test);
		assertTrue(testHand.chain.next instanceof ControllerChain);
		assertFalse(testHand.canProcess(testReq));
		ServerThread client = Mockito.mock(ServerThread.class);
		testHand.chain = new EmptyHandler();
		testHand.logout(client);
		assertNull(testHand.chain.next);
		testHand.chain = null;
		assertNull(testHand.process(client, testReq));
		
		
	}
	
	

	
}
