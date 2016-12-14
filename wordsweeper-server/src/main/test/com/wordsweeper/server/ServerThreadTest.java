package com.wordsweeper.server;

import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.internal.matchers.*;
import org.mockito.stubbing.Answer;

import com.wordsweeper.server.controller.WordSweeperProtocolHandler;
import com.wordsweeper.server.model.ServerModel;
import com.wordsweeper.core.xml.ObjectFactory;
import com.wordsweeper.core.xml.Request;
import com.wordsweeper.core.xml.Response;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * Created by george on 11/11/16.
 */
public class ServerThreadTest {
	@Test
	public void constructor() throws Exception {
		ServerModel serverModel = new ServerModel();
		WordSweeperProtocolHandler handler = new WordSweeperProtocolHandler(serverModel);
		Server server = new Server(handler, 17005);

		Socket sock = Mockito.mock(Socket.class);
		InputStream instream = Mockito.mock(InputStream.class);
		OutputStream outstream = Mockito.mock(OutputStream.class);
		Mockito.when(sock.getInputStream()).thenReturn(instream);
		Mockito.when(sock.getOutputStream()).thenReturn(outstream);

		ServerThread testThread = new ServerThread(server, sock, handler);
		assertTrue(testThread.id() != null);

		assertNull(testThread.data);
		testThread.setData(new String("as"));
		assertFalse(testThread.getData() == null);

		SocketAddress testAddr = Mockito.mock(SocketAddress.class);
		Mockito.when(testAddr.toString()).thenReturn("test");
		Mockito.when(sock.getRemoteSocketAddress()).thenReturn(testAddr);
		assertEquals(testThread.getRemoteSocketAddress(), "test");

		assertTrue(testThread.sendMessage(new Response()));

		assertNull(testThread.getRequest());

		testThread.run();

		testThread = Mockito.spy(new ServerThread(server, sock, handler));
		Request testReq = Mockito.mock(Request.class);
		Mockito.doReturn(testReq).when(testThread).getRequest();
		Mockito.when(testReq.getConnectRequest()).thenReturn(null);
		testThread.run();
		Mockito.when(testReq.getConnectRequest()).thenReturn("");
		Mockito.doReturn(false).when(testThread).sendMessage(null);
		testThread.run();
		ObjectFactory objectFactory = new ObjectFactory();
		Response responseWrapper = objectFactory.createResponse();
		responseWrapper.setId(null);
		responseWrapper.setSuccess(true);
		responseWrapper.setConnectResponse(objectFactory.createConnectResponse());
		responseWrapper.getConnectResponse().setId(testThread.id);
		Mockito.when(testThread.id()).thenReturn("testID");
//		Mockito.doReturn(true).when(testThread).sendMessage(Mockito.any(Response.class));
		testThread.run();
	}	


}