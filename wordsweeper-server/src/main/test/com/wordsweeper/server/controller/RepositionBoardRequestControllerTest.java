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
import com.wordsweeper.server.xml.JoinGameRequest;
import com.wordsweeper.server.xml.RepositionBoardRequest;
import com.wordsweeper.server.xml.Request;
import com.wordsweeper.server.xml.Response;

import retrofit2.Retrofit;

import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;



public class RepositionBoardRequestControllerTest {

	@Test
	public void constructor() throws Exception {
		ServerModel mockServer = Mockito.mock(ServerModel.class);
		RepositionBoardRequestController listCon = new RepositionBoardRequestController(mockServer);
		
		Request testReq = Mockito.mock(Request.class);
		
		assertFalse(listCon.canProcess(null));
		
		Mockito.when(testReq.getRepositionBoardRequest()).thenReturn(null);
		assertFalse(listCon.canProcess(testReq));
		
		Mockito.when(testReq.getRepositionBoardRequest()).thenReturn(new RepositionBoardRequest());
		assertTrue(listCon.canProcess(testReq));
		
		assertFalse(listCon.setOnSuccessResponse(testReq,new Response()));
		listCon.setOnFailureResponse(testReq, new Response());
		
		Game testGame = new Game();
		ServerThread client = Mockito.mock(ServerThread.class);
		assertNull(listCon.execute(client, testReq, testGame));
		Mockito.when(mockServer.isClientInGame(client)).thenReturn(false);
		assertTrue(listCon.process(client, testReq) instanceof Response);
//		
//		retrofit2.Response res = Mockito.mock(retrofit2.Response.class);
//		Mockito.when(res.code()).thenReturn(304);
//		assertNull(listCon.handleAPIError(testReq, res));
	}
	
	

	
}
