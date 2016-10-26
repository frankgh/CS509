package client.controller;


import xml.Message;
import client.model.Model;
import client.view.Application;

public class CreateGameController {

	Application app;
	Model model;

	public CreateGameController(Application app, Model model) {
		this.app = app;
		this.model = model;
	}

	/** Make the request on the server and wait for response. */
	public void process() {
		String name = app.getName();
		String password = app.getPassword();
		
		// send the request to create the game.
		String xmlString = Message.requestHeader() + "<createGameRequest name='"+name+"'/></request>";
		Message m = new Message (xmlString);

		// Request the lock (this might not succeed).
		app.getRequestArea().append(m.toString());
		app.getRequestArea().append("\n");
		app.getServerAccess().sendRequest(m);
	}
}
