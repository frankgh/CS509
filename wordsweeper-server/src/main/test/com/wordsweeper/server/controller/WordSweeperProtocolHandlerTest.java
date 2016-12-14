package com.wordsweeper.server.controller;

import com.wordsweeper.core.xml.Request;
import com.wordsweeper.server.ServerThread;
import com.wordsweeper.server.model.ServerModel;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;


public class WordSweeperProtocolHandlerTest {

    @Test
    public void constructor() throws Exception {
        ServerModel server = new ServerModel();
        WordSweeperProtocolHandler testHand = new WordSweeperProtocolHandler(server);
        Request testReq = Mockito.mock(Request.class);

        assertNull(testHand.chain.next);
        EmptyHandler test = new EmptyHandler();
        testHand.registerHandler(test);
        assertTrue(testHand.chain.next instanceof ControllerChain);
        assertFalse(testHand.canProcess(testReq));
        ServerThread client = Mockito.mock(ServerThread.class);
        testHand.chain = new EmptyHandler();
        testHand.logout(client);
        assertNull(testHand.chain.next);
        testHand.chain = null;
        assertNull(testHand.process(client, testReq));


    }


}
