package com.wordsweeper.adminclient.controller;

import com.wordsweeper.adminclient.IMessageHandler;
import com.wordsweeper.adminclient.view.AdminClientApplication;
import com.wordsweeper.core.xml.Response;

/**
 * This class specified admin client handler.
 */

public class AdminClientMessageHandler implements IMessageHandler {

    AdminClientApplication app;

    /**
     * @param app the user interface application.
     */

    public AdminClientMessageHandler(AdminClientApplication app) {
        this.app = app;
    }

    /**
     * @param response the respond from the admin client message controller..
     */
    public void process(Response response) {

        if (response.getListGamesResponse() != null) {
            /** process the ListGameResponseController to handel the game list info */
            new ListGamesResponseController(app, app.model).process(response);
        } else if (response.getConnectResponse() != null) {
            /** connect response is to show the connection info */
            app.getConnection().setText(response.toString());
        } else if (response.getBoardResponse() != null) {
            /** board response is to display the board content and relevant players' info */
            new BoardResponseController(app, app.model).process(response);
        }

        System.out.println(response);
    }

}
