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

import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;



public class ResetGameRequestControllerTest {

	@Test
	public void constructor() throws Exception {
		ServerModel server = new ServerModel();
		ResetGameRequestController lockCon = new ResetGameRequestController(server);
		
		Request resetReq = Mockito.mock(Request.class);
		
		assertFalse(lockCon.canProcess(null));
		
		Mockito.when(resetReq.getResetGameRequest()).thenReturn(null);
		assertFalse(lockCon.canProcess(resetReq));
		
		Mockito.when(resetReq.getResetGameRequest()).thenReturn(new ResetGameRequest());
		assertTrue(lockCon.canProcess(resetReq));
		
		assertFalse(lockCon.setOnSuccessResponse(resetReq,new Response()));
		lockCon.setOnFailureResponse(resetReq, new Response());
		
		Game testGame = new Game();
		ServerThread client = Mockito.mock(ServerThread.class);
		assertNull(lockCon.execute(client, resetReq, testGame));
		
		
		ServerModel mockServer = Mockito.mock(ServerModel.class);
		lockCon = new ResetGameRequestController(mockServer);
		Mockito.when(mockServer.isClientInGame(client)).thenReturn(false);
		assertTrue(lockCon.process(client, resetReq) instanceof Response);
		
		Mockito.when(mockServer.isClientInGame(client)).thenReturn(true);
		Mockito.when(mockServer.isManagingPlayer(client)).thenReturn(false);
		assertTrue(lockCon.process(client, resetReq) instanceof Response);

	}
	
	

	
}
