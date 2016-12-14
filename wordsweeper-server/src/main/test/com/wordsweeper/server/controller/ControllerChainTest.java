package com.wordsweeper.server.controller;

import com.wordsweeper.core.xml.Request;
import com.wordsweeper.server.ServerThread;
import com.wordsweeper.server.api.model.Game;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertTrue;


public class ControllerChainTest {

    @Test
    public void constructor() throws Exception {
        EmptyHandler testHand = new EmptyHandler();
        Request testReq = new Request();
        Game testGame = new Game();
        ServerThread client = Mockito.mock(ServerThread.class);
        assertTrue(testHand.getObjectFactory() != null);

//		assertTrue(testHand.handleAPIError(testReq, null) == null);
    }

}
