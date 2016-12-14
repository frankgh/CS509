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
import com.wordsweeper.server.xml.Response;

import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;



public class LockGameRequestControllerTest {

	@Test
	public void constructor() throws Exception {
		ServerModel server = new ServerModel();
		LockGameRequestController lockCon = new LockGameRequestController(server);
		
		Request testReq = Mockito.mock(Request.class);
		
		assertFalse(lockCon.canProcess(null));
		
		Mockito.when(testReq.getLockGameRequest()).thenReturn(null);
		assertFalse(lockCon.canProcess(testReq));
		
		Mockito.when(testReq.getLockGameRequest()).thenReturn(new LockGameRequest());
		assertTrue(lockCon.canProcess(testReq));
		
		assertFalse(lockCon.setOnSuccessResponse(testReq,new Response()));
		lockCon.setOnFailureResponse(testReq, new Response());
		
		Game testGame = new Game();
		ServerThread client = Mockito.mock(ServerThread.class);
		CreateGameRequest createReq = Mockito.mock(CreateGameRequest.class);
		Mockito.when(createReq.getPassword()).thenReturn(null);
		Mockito.when(testReq.getCreateGameRequest()).thenReturn(createReq);
		assertTrue(lockCon.execute(client, testReq, testGame) instanceof Response);
		
		
		
		ServerModel mockServer = Mockito.mock(ServerModel.class);
		lockCon = new LockGameRequestController(mockServer);
		Mockito.when(mockServer.isClientInGame(client)).thenReturn(false);
		assertTrue(lockCon.process(client, testReq) instanceof Response);
		
		Mockito.when(createReq.getPassword()).thenReturn("testPass");
		Mockito.when(createReq.getName()).thenReturn("testPlayer");
		Mockito.when(mockServer.isClientInGame(client)).thenReturn(true);
		Mockito.when(mockServer.isManagingPlayer(client)).thenReturn(false);
		assertTrue(lockCon.process(client, testReq) instanceof Response);

	}
	
	

	
}
