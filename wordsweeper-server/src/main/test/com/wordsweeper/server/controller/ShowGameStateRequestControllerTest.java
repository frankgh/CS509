package com.wordsweeper.server.controller;

import com.wordsweeper.server.ServerThread;
import com.wordsweeper.server.api.model.Game;
import com.wordsweeper.server.model.ServerModel;
import com.wordsweeper.core.xml.Request;
import com.wordsweeper.core.xml.Response;
import com.wordsweeper.core.xml.ShowGameStateRequest;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;


public class ShowGameStateRequestControllerTest {

    @Test
    public void constructor() throws Exception {
        ServerModel server = new ServerModel();
        ShowGameStateRequestController showCon = new ShowGameStateRequestController(server);

        Request resetReq = Mockito.mock(Request.class);

        assertFalse(showCon.canProcess(null));

        Mockito.when(resetReq.getShowGameStateRequest()).thenReturn(null);
        assertFalse(showCon.canProcess(resetReq));

        Mockito.when(resetReq.getShowGameStateRequest()).thenReturn(new ShowGameStateRequest());
        assertTrue(showCon.canProcess(resetReq));

        assertFalse(showCon.setOnSuccessResponse(resetReq, new Response()));
        showCon.setOnFailureResponse(resetReq, new Response());

        Game testGame = new Game();
        ServerThread client = Mockito.mock(ServerThread.class);
        assertNull(showCon.execute(client, resetReq, testGame));


    }


}
