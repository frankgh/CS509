package com.wordsweeper.server;

import org.junit.Test;

import com.wordsweeper.server.controller.IProtocolHandler;
import com.wordsweeper.server.controller.WordSweeperProtocolHandler;
import com.wordsweeper.server.model.ServerModel;
import org.mockito.*;

import static org.junit.Assert.*;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by george on 11/11/16.
 */
public class ServerTest {
    @Test
    public void constructor() throws Exception {
    	ServerModel serverModel = new ServerModel();
    	WordSweeperProtocolHandler handler = new WordSweeperProtocolHandler(serverModel);
    	Server server = new Server(handler, 17005);
    	assertEquals(server.state, 0);
    	server.bind();
    	assertEquals(server.state, 1);
    	
    	assertEquals(server.ids().size(), 0);
    	ServerThread sThread = Mockito.mock(ServerThread.class);
    	server.register("1", sThread);
    	assertEquals(server.ids().size(), 1);
    	assertFalse(server.register("1", sThread));
    	
    	assertTrue(server.getState("1") != null);
    	server.unregister("1");
    	assertNull(server.getState("1"));
    	server.shutdown();
    	assertEquals(server.state, 0);
    	server.state = 2;
    	server.process();
    	assertEquals(server.state, 2); //shutdown wasn't called
    }

}