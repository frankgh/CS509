package client.controller;


import xml.Message;
import client.model.Model;
import client.view.Application;

/**
 * This class implemented a controller that creating game: WordSweeper
 */
public class CreateGameController {

	Application app;
	Model model;


	/**
	 * This constructs a game controller with a specified app and model.
	 * @param app app for client
	 * @param model model for this game.
	 */

	public CreateGameController(Application app, Model model) {
		this.app = app;
		this.model = model;
	}

	/**
	 * This process makes the request on the server and wait for response with clients' name, password.
	 *
	 */
	public void process() {
		String name = app.getName();
		String password = app.getPassword();
		String xmlString = "";
		// send the request to create the game.
		if(password.length()>0)
			xmlString = Message.requestHeader() + "<createGameRequest name='"+name+"' password='"+password+"'/></request>";
		else
			xmlString = Message.requestHeader() + "<createGameRequest name='"+name+"'/></request>";
			
		Message m = new Message (xmlString);

		// Request the lock (this might not succeed).
		app.getRequestArea().append(m.toString());
		app.getRequestArea().append("\n");
		app.getServerAccess().sendRequest(m);
	}
}