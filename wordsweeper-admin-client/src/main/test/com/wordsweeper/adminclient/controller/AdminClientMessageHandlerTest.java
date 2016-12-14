package com.wordsweeper.adminclient.controller;

import com.wordsweeper.adminclient.view.AdminClientApplication;
import com.wordsweeper.core.xml.BoardResponse;
import com.wordsweeper.core.xml.ConnectResponse;
import com.wordsweeper.core.xml.ListGamesResponse;
import com.wordsweeper.core.xml.Response;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * This is the class for admin client model.
 *
 * @author george
 */
public class AdminClientMessageHandlerTest {
    @Test
    public void constructor() throws Exception {
        AdminClientApplication app = new AdminClientApplication();
        AdminClientMessageHandler handler = new AdminClientMessageHandler(app);

        Response res = Mockito.mock(Response.class);
        handler.process(res);
        Mockito.when(res.getBoardResponse()).thenReturn(new BoardResponse());
        handler.process(res);

        Mockito.when(res.getConnectResponse()).thenReturn(new ConnectResponse());
        handler.process(res);

        Mockito.when(res.getListGamesResponse()).thenReturn(new ListGamesResponse());
        handler.process(res);

    }
}
