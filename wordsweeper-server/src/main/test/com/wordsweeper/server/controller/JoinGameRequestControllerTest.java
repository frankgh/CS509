package com.wordsweeper.server.controller;

import com.wordsweeper.core.xml.JoinGameRequest;
import com.wordsweeper.core.xml.Request;
import com.wordsweeper.core.xml.Response;
import com.wordsweeper.server.ServerThread;
import com.wordsweeper.server.api.model.Game;
import com.wordsweeper.server.model.ServerModel;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;


public class JoinGameRequestControllerTest {

    @Test
    public void constructor() throws Exception {
        ServerModel mockServer = Mockito.mock(ServerModel.class);
        JoinGameRequestController joinCon = new JoinGameRequestController(mockServer);

        Request testReq = Mockito.mock(Request.class);

        assertFalse(joinCon.canProcess(null));

        Mockito.when(testReq.getJoinGameRequest()).thenReturn(null);
        assertFalse(joinCon.canProcess(testReq));

        Mockito.when(testReq.getJoinGameRequest()).thenReturn(new JoinGameRequest());
        assertTrue(joinCon.canProcess(testReq));

        assertFalse(joinCon.setOnSuccessResponse(testReq, new Response()));
        joinCon.setOnFailureResponse(testReq, new Response());

        Game testGame = new Game();
        ServerThread client = Mockito.mock(ServerThread.class);
        JoinGameRequest joinReq = Mockito.mock(JoinGameRequest.class);
        Mockito.when(joinReq.getPassword()).thenReturn(null);
        Mockito.when(testReq.getJoinGameRequest()).thenReturn(joinReq);
        Mockito.when(mockServer.joinGame(client, testGame)).thenReturn(true);
        assertNull(joinCon.execute(client, testReq, testGame));


        Mockito.when(mockServer.joinGame(client, testGame)).thenReturn(false);
        joinCon = new JoinGameRequestController(mockServer);
        assertTrue(joinCon.execute(client, testReq, testGame) instanceof Response);
        Mockito.when(mockServer.isClientInGame(client)).thenReturn(true);
        assertTrue(joinCon.process(client, testReq) instanceof Response);

        Mockito.when(joinReq.getPassword()).thenReturn("testPass");
        Mockito.when(joinReq.getName()).thenReturn("testPlayer");
        Mockito.when(mockServer.isClientInGame(client)).thenReturn(false);

//		
//		assertTrue(joinCon.process(client, testReq).getSuccess());
//		Mockito.when(joinReq.getPassword()).thenReturn("1234");
//		assertTrue(joinCon.process(client, testReq).getSuccess());
    }


}
