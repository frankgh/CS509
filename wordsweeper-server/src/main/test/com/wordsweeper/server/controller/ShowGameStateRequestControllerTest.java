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



public class ShowGameStateRequestControllerTest {

	@Test
	public void constructor() throws Exception {
		ServerModel server = new ServerModel();
		ShowGameStateRequestController showCon = new ShowGameStateRequestController(server);
		
		Request resetReq = Mockito.mock(Request.class);
		
		assertFalse(showCon.canProcess(null));
		
		Mockito.when(resetReq.getShowGameStateRequest()).thenReturn(null);
		assertFalse(showCon.canProcess(resetReq));
		
		Mockito.when(resetReq.getShowGameStateRequest()).thenReturn(new ShowGameStateRequest());
		assertTrue(showCon.canProcess(resetReq));
		
		assertFalse(showCon.setOnSuccessResponse(resetReq,new Response()));
		showCon.setOnFailureResponse(resetReq, new Response());
		
		Game testGame = new Game();
		ServerThread client = Mockito.mock(ServerThread.class);
		assertNull(showCon.execute(client, resetReq, testGame));
		

	}
	
	

	
}
