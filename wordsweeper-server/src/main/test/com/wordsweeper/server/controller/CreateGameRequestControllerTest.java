package com.wordsweeper.server.controller;

import com.wordsweeper.server.Server;
import com.wordsweeper.server.ServerThread;
import com.wordsweeper.server.api.model.Board;
import com.wordsweeper.server.model.ClientState;
import com.wordsweeper.server.model.ServerModel;
import com.wordsweeper.server.xml.CreateGameRequest;
import com.wordsweeper.server.xml.ExitGameRequest;
import com.wordsweeper.server.xml.Request;
import com.wordsweeper.server.xml.Response;

import static org.junit.Assert.*;

import java.util.ArrayList;
import mockit.*;
import org.junit.Before;
import org.junit.Test;



public class CreateGameRequestControllerTest {

	@Test
	public void constructor() throws Exception {
		ServerModel server = new ServerModel();
		CreateGameRequestController createCon = new CreateGameRequestController(server);
		CreateGameRequest createReq = new CreateGameRequest();
		ExitGameRequest exitReq = new ExitGameRequest();
		// TO BE REMOVED vvvvvvvvvvvvvvvvvvvvvvvvv
		createReq.setName("testRequest");
		createReq.setPassword("testPassword");
		assertEquals("testRequest", createReq.getName());
		assertEquals("testPassword", createReq.getPassword());
		// TO BE REMOVED ^^^^^^^^^^^^^^^^^^^^^^^^^^^
		Request req1 = new Request();
		req1.setCreateGameRequest(createReq);
		assertTrue(createCon.canProcess(req1));
		Request req2 = new Request();
		
		req2.setExitGameRequest(exitReq);
		assertFalse(createCon.canProcess(req2));
		ClientState mock = Mockit.newEmptyProxy(ClientState.class);

		Response res1 = createCon.process(mock, req1);
		assertTrue(res1.getSuccess());
		
		Response res2 = createCon.process(mock, req2);
		assertFalse(res2.getSuccess());
	}
	
	

	
}
