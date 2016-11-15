package client.controller;


import client.model.Model;
import client.view.Application;
import xml.Message;

public class CreateGameController {

    Application app;
    Model model;

    public CreateGameController(Application app, Model model) {
        this.app = app;
        this.model = model;
    }

    /**
     * Make the request on the server and wait for response.
     */
    public void process() {
        String name = app.getName();
        String password = app.getPassword();
        String xmlString = "";
        // send the request to create the game.
        if (password.length() > 0)
            xmlString = Message.requestHeader() + "\n\t<createGameRequest name='" + name + "' password='" + password + "'/>\n</request>";
        else
            xmlString = Message.requestHeader() + "\n\t<createGameRequest name='" + name + "'/>\n</request>";

        Message m = new Message(xmlString);

        // Request the lock (this might not succeed).
        app.getRequestArea().append(m.toString());
        app.getRequestArea().append("\n");
        app.getServerAccess().sendRequest(m);
    }
}