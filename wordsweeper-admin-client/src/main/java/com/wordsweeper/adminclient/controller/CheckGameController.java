package com.wordsweeper.adminclient.controller;

import com.wordsweeper.adminclient.model.AdminClientModel;
import com.wordsweeper.adminclient.view.AdminClientApplication;
import com.wordsweeper.core.xml.Request;
import com.wordsweeper.core.xml.ShowGameStateRequest;

import java.util.UUID;

/**
 * This is the controller for admin client that can check game status timely..
 */
public class CheckGameController {
    AdminClientApplication app;
    AdminClientModel model;

    /**
     * @param app   the admin client application.
     * @param model the admin client model.
     */
    public CheckGameController(AdminClientApplication app, AdminClientModel model) {
        this.app = app;
        this.model = model;
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
