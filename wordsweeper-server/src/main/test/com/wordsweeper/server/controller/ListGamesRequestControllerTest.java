package com.wordsweeper.server.controller;

import com.wordsweeper.core.xml.Request;
import com.wordsweeper.core.xml.Response;
import com.wordsweeper.server.ServerThread;
import com.wordsweeper.server.api.model.Game;
import com.wordsweeper.server.model.ServerModel;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;


public class ListGamesRequestControllerTest {

    @Test
    public void constructor() throws Exception {
        ServerModel mockServer = Mockito.mock(ServerModel.class);
        ListGamesRequestController listCon = new ListGamesRequestController(mockServer);

        Request testReq = Mockito.mock(Request.class);

        assertFalse(listCon.canProcess(null));

        Mockito.when(testReq.getListGamesRequest()).thenReturn(null);
        assertFalse(listCon.canProcess(testReq));

        Mockito.when(testReq.getListGamesRequest()).thenReturn("");
        assertTrue(listCon.canProcess(testReq));

        assertFalse(listCon.setOnSuccessResponse(testReq, new Response()));
        listCon.setOnFailureResponse(testReq, new Response());

        Game testGame = new Game();
        ServerThread client = Mockito.mock(ServerThread.class);
        assertNull(listCon.execute(client, testReq, testGame));

        assertTrue(listCon.process(client, testReq) instanceof Response);

    }


}
