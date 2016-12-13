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



public class CreateGameRequestControllerTest {

	@Test
	public void constructor() throws Exception {
		ServerModel server = new ServerModel();
		CreateGameRequestController createCon = new CreateGameRequestController(server);
		
		Request testReq = Mockito.mock(Request.class);
		
		assertFalse(createCon.canProcess(null));
		
		Mockito.when(testReq.getCreateGameRequest()).thenReturn(null);
		assertFalse(createCon.canProcess(testReq));
		
		Mockito.when(testReq.getCreateGameRequest()).thenReturn(new CreateGameRequest());
		assertTrue(createCon.canProcess(testReq));
		
		assertFalse(createCon.setOnSuccessResponse(testReq,new Response()));
		createCon.setOnFailureResponse(testReq, new Response());
		
		Game testGame = new Game();
		ServerThread client = Mockito.mock(ServerThread.class);
		CreateGameRequest createReq = Mockito.mock(CreateGameRequest.class);
		Mockito.when(createReq.getPassword()).thenReturn(null);
		Mockito.when(testReq.getCreateGameRequest()).thenReturn(createReq);
		assertNull(createCon.execute(client, testReq, testGame));
		
		
		
		ServerModel mockServer = Mockito.mock(ServerModel.class);
		Mockito.when(mockServer.isClientInGame(client)).thenReturn(true);
		createCon = new CreateGameRequestController(mockServer);
		assertTrue(createCon.execute(client, testReq, testGame) instanceof Response);
		assertTrue(createCon.process(client, testReq) instanceof Response);
		
		Mockito.when(createReq.getPassword()).thenReturn("testPass");
		Mockito.when(createReq.getName()).thenReturn("testPlayer");
		Mockito.when(mockServer.isClientInGame(client)).thenReturn(false);
		
//		
//		assertTrue(createCon.process(client, testReq).getSuccess());
//		Mockito.when(createReq.getPassword()).thenReturn("1234");
//		assertTrue(createCon.process(client, testReq).getSuccess());
	}
	
	

	
}
