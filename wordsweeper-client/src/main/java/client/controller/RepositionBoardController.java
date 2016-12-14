package client.controller;

import client.model.Model;
import client.view.Application;
import xml.Message;

/**
 * This class specifies reposition the game board controller.
 * @author Francisco
 */
public class RepositionBoardController {

    Application app;
    Model model;

    public RepositionBoardController(Application app, Model model) {
        this.app = app;
        this.model = model;
    }

    /**
     * Make the request on the server and wait for response.
     */
    public void process() {
        String name = app.getName();
        String game_id = app.getGameID();

        String rowChange = app.getRowChange();
        String colChange = app.getColChange();
        String xmlString = Message.requestHeader() +
                "<repositionBoardRequest name='" + name + "' gameId='" + game_id +
                "' rowChange='" + rowChange + "' colChange='" + colChange + "' /></request>";

        Message m = new Message(xmlString);

        // Request the lock (this might not succeed).
        app.getRequestArea().append(m.toString());
        app.getRequestArea().append("\n");
        app.getServerAccess().sendRequest(m);
    }
}
