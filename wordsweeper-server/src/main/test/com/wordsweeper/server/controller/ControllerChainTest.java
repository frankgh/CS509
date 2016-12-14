package com.wordsweeper.server.controller;

import com.wordsweeper.core.xml.Request;
import com.wordsweeper.core.xml.Response;
import com.wordsweeper.server.ServerThread;
import com.wordsweeper.server.api.model.Game;
import com.wordsweeper.server.model.ServerModel;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;


public class ControllerChainTest {

    @Test
    public void constructor() throws Exception {
        ControllerChain chain = Mockito.mock(ControllerChain.class, Mockito.CALLS_REAL_METHODS);
        Request req = Mockito.mock(Request.class);
        
        chain.model = Mockito.mock(ServerModel.class);
        Mockito.when(chain.model.updateGame(Mockito.any(Game.class))).thenReturn(true);
        
        Response res = Mockito.mock(Response.class);
        Game game = Mockito.mock(Game.class);
        Mockito.when(game.getUniqueId()).thenReturn("newTestID");
        Mockito.when(chain.model.idsByGameId(Mockito.any(String.class))).thenReturn(null);
        ServerThread client = Mockito.mock(ServerThread.class);
        chain.broadcastResponse(res, "testID", game);
        ArrayList<ServerThread> clientList = new ArrayList<ServerThread>();
        clientList.add(client);
        Mockito.doReturn(clientList).when(chain.model).idsByGameId(Mockito.any(String.class));
        chain.broadcastResponse(res, "testID", game);
    }

}
