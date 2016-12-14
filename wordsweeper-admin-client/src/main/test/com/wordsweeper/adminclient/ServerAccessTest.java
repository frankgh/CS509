package com.wordsweeper.adminclient;

import com.wordsweeper.adminclient.ServerAccess;
import com.wordsweeper.adminclient.ServerAccess.Tuple;
import com.wordsweeper.adminclient.controller.AdminClientMessageHandler;
import com.wordsweeper.adminclient.controller.ListGamesResponseController;
import com.wordsweeper.adminclient.view.AdminClientApplication;
import com.wordsweeper.adminclient.view.AdminClientApplication.ColorPlayerListCellRenderer;
import com.wordsweeper.adminclient.view.AdminClientApplication.ColorTableCellRenderer;
import com.wordsweeper.core.util.JAXBUtil;
import com.wordsweeper.core.xml.CreateGameRequest;
import com.wordsweeper.core.xml.Request;
import com.wordsweeper.core.xml.Response;
import org.junit.Test;
import org.mockito.Mockito;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Hashtable;

import static org.junit.Assert.*;


/**
 * This is the class for admin client model.
 */
public class ServerAccessTest {
    @Test
    public void constructor() throws Exception {
    	String host = "cs509.frankgh.com";
    	ServerAccess sAccess = new ServerAccess(host);
    	String id = "testID";
    	Request testReq = Mockito.mock(Request.class);
    	Tuple tuple = sAccess.new Tuple(null, testReq, id);
    	AdminClientApplication app = new AdminClientApplication();
        assertFalse(sAccess.isActive);
        
        testReq.setCreateGameRequest(new CreateGameRequest());
        ServerAccess sa = new ServerAccess(host, 11425);
        sa.server = Mockito.mock(Socket.class);
        sa.toServer = Mockito.mock(PrintWriter.class);
        sa.disconnect();
        sa.isActive = true;
        sa.disconnect();
        assertFalse(sAccess.isActive);
        
        assertFalse(sa.sendRequest(testReq));
        sa.isActive = true;        
        
        assertEquals(sa.toString(), "Connected to:" + host);
        
        sa.pending = new Hashtable<String, Tuple>();
        assertFalse(sa.isWaiting());
        sa.pending.put(id, tuple);
        assertTrue(sa.isWaiting());
        
        assertEquals(sa.getHost(), host);
        
    }
}
