package com.wordsweeper.server;

import org.junit.Test;
import org.mockito.Mockito;

import com.wordsweeper.server.controller.WordSweeperProtocolHandler;
import com.wordsweeper.server.model.ServerModel;
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
    	
//    	BufferedReader buffTest = Mockito.mock(BufferedReader.class);
//    	Mockito.when(buffTest.readLine()).thenReturn("test");
//    	assertEquals(testThread.getXmlStringFromReader(buffTest, ""), "test");
//    	
//    	Mockito.when(buffTest.readLine()).thenReturn(null);
//    	assertNull(testThread.getXmlStringFromReader(buffTest, "!"));
//    	
//    	Mockito.when(buffTest.readLine()).thenReturn("test///");
//    	assertEquals(testThread.getXmlStringFromReader(buffTest, "///"), "test///");
//    	
    	testThread.run();
//    	assertFalse(testThread.client.isClosed());
    	
//    	ServerThread testThread2 = Mockito.mock(ServerThread.class);
//    	Mockito.when(testThread2.run()).thenCallRealMethod();	
}

}