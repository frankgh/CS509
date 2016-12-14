package com.wordsweeper.server.controller;

import com.wordsweeper.core.xml.Request;
import com.wordsweeper.core.xml.Response;
import com.wordsweeper.server.ServerThread;
import com.wordsweeper.server.api.model.Game;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;


public class EmptyHandlerTest {

    @Test
    public void constructor() throws Exception {
        EmptyHandler testHand = new EmptyHandler();
        Request testReq = new Request();
        Game testGame = new Game();
        ServerThread client = Mockito.mock(ServerThread.class);
        Response testRes = Mockito.mock(Response.class);

        assertTrue(testHand.canProcess(testReq));
        assertFalse(testHand.process(client, testReq).isSuccess());
        assertNull(testHand.execute(client, testReq, testGame));
        assertFalse(testHand.setOnSuccessResponse(testReq, testRes));
        testHand.setOnFailureResponse(testReq, testRes);
    }
}
