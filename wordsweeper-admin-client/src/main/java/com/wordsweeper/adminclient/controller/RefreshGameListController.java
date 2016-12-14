package com.wordsweeper.adminclient.controller;

import com.wordsweeper.adminclient.view.AdminClientApplication;
import com.wordsweeper.core.xml.Request;

import java.util.UUID;

/**
 * This class introduces refreshing game list controller.
 */
public class RefreshGameListController {

    AdminClientApplication app;

    /**
     * @param app admin client application.
     */
    public RefreshGameListController(AdminClientApplication app) {
        this.app = app;
    }

    /**
     * Make the request on the server and wait for response.
     */
    public void process() {
        Request request = new Request();
        request.setId(UUID.randomUUID().toString());
        request.setListGamesRequest(new Object());

        app.getServerAccess().sendRequest(request);
    }

}
