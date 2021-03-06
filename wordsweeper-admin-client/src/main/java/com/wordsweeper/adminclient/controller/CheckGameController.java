package com.wordsweeper.adminclient.controller;

import com.wordsweeper.adminclient.view.AdminClientApplication;
import com.wordsweeper.core.xml.Request;
import com.wordsweeper.core.xml.ShowGameStateRequest;

import java.util.UUID;

/**
 * This is the controller for admin client that can check game status timely..
 */
public class CheckGameController {
    AdminClientApplication app;

    /**
     * @param app the admin client application.
     */
    public CheckGameController(AdminClientApplication app) {
        this.app = app;
    }

    /**
     * Create a GUI for admin client board application.
     */
    public void process() {

        String gameId = app.getGameID();

        if (gameId == null) {
            return;
        }

        ShowGameStateRequest showGameStateRequest = new ShowGameStateRequest();
        showGameStateRequest.setGameId(gameId);

        Request request = new Request();
        request.setId(UUID.randomUUID().toString());
        request.setShowGameStateRequest(showGameStateRequest);

        app.getServerAccess().sendRequest(request);
    }

}
