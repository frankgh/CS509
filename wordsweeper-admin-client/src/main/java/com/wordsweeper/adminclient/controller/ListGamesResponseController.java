package com.wordsweeper.adminclient.controller;

import com.wordsweeper.adminclient.view.AdminClientApplication;
import com.wordsweeper.core.xml.GameBrief;
import com.wordsweeper.core.xml.Response;

/**
 * Controller on admin-client in charge of relaying list game response
 * to the API, and packaging up the API response to send to all
 * the players joined to the game.
 */
public class ListGamesResponseController {

    public AdminClientApplication app;

    /**
     * @param a the admin client application
     */
    public ListGamesResponseController(AdminClientApplication a) {
        this.app = a;
    }

    /**
     * @param response the respond from list game controller.
     */
    public void process(Response response) {

        if (!response.isSuccess()) {
            return;
        }

        if (response.getListGamesResponse().getGameBrief() == null) {
            return;
        }

        for (GameBrief gameBrief : response.getListGamesResponse().getGameBrief()) {
            String gameId = gameBrief.getGameId();
            String player = String.valueOf(gameBrief.getPlayers());
            Object[] info = new Object[]{gameId, player};
            app.SetGameIDInfo(info);
        }
    }

}