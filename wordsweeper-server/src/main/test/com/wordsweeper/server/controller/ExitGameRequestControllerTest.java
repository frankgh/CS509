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



public class ExitGameRequestControllerTest {

	@Test
	public void constructor() throws Exception {
		ServerModel mockServer = Mockito.mock(ServerModel.class);
		ExitGameRequestController exitCon = new ExitGameRequestController(mockServer);
		
		ServerThread client = Mockito.mock(ServerThread.class);
		Mockito.when(mockServer.isClientInGame(client)).thenReturn(false);
		exitCon.logout(client);
		
//		Mockito.when(mockServer.isClientInGame(client)).thenReturn(true);
//		exitCon.logout(client);
		
		Request testReq = Mockito.mock(Request.class);
		
		assertFalse(exitCon.canProcess(null));
		
		Mockito.when(testReq.getExitGameRequest()).thenReturn(null);
		assertFalse(exitCon.canProcess(testReq));
		
		Mockito.when(testReq.getExitGameRequest()).thenReturn(new ExitGameRequest());
		assertTrue(exitCon.canProcess(testReq));
		
		assertTrue(exitCon.setOnSuccessResponse(testReq,new Response()));
		
		Mockito.when(testReq.getExitGameRequest()).thenReturn(null);
		assertTrue(exitCon.setOnSuccessResponse(testReq,new Response()));
		exitCon.setOnFailureResponse(testReq, new Response());
		

		Mockito.when(mockServer.isClientInGame(client)).thenReturn(false);
		assertTrue(exitCon.process(client, testReq) instanceof Response);
		
		Game testGame = new Game();
		Mockito.when(mockServer.exitGame(client)).thenReturn(false);
		assertTrue(exitCon.execute(client, testReq, testGame) instanceof Response);
		
		Mockito.when(mockServer.exitGame(client)).thenReturn(true);
		assertNull(exitCon.execute(client, testReq, testGame));

	}		
}
