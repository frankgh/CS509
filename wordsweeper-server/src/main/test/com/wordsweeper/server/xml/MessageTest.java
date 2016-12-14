package com.wordsweeper.server.xml;

import com.wordsweeper.server.ServerThread;
import com.wordsweeper.server.api.model.Board;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;



public class MessageTest {

	@Test
	public void constructor() throws Exception {
		Message testMes = new Message();
		
		Request testReq = Mockito.mock(Request.class);
		testMes.setRequest(testReq);
		assertEquals(testReq, testMes.getRequest());
			
		Response testRes = Mockito.mock(Response.class);
		testMes.setResponse(testRes);
		assertEquals(testRes, testMes.getResponse());
		
	}
}
