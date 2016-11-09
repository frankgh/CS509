package client.controller;


import xml.Message;
import client.model.Model;
import client.view.Application;

public class JoinGameController {

	Application app;
	Model model;

	public JoinGameController(Application app, Model model) {
		this.app = app;
		this.model = model;
	}

	/** Make the request on the server and wait for response. */
	public void process() {
		String name = app.getName();
		String password = app.getPassword();
		String game_id = app.getGameID();
		String xmlString = "";
 		// send the request to create the game.
		if(password.length()>0)
			xmlString = Message.requestHeader() + "<joinGameRequest gameId='"+game_id+"' name='"+name+"' password='"+password+"'/></request>";
		else
			xmlString = Message.requestHeader() + "<joinGameRequest gameId='"+game_id+"' name='"+name+"'/></request>";
		
		Message m = new Message (xmlString);

		// Request the lock (this might not succeed).
		app.getRequestArea().append(m.toString());
		app.getRequestArea().append("\n");
		app.getServerAccess().sendRequest(m);
	}
}