package com.wordsweeper.adminclient.controller;


import xml.Message;
import com.wordsweeper.adminclient.model.AdminClientModel;
import com.wordsweeper.adminclient.view.AdminClientApplication;

/**
 * This is the controller for admin client that can check game status timely..
 */
public class CheckGameController {
	AdminClientApplication app;
	AdminClientModel model;

    /**
     *
     * @param app the admin client application.
     * @param model the admin client model.
     */
	public CheckGameController(AdminClientApplication app, AdminClientModel model) {
		this.app = app;
		this.model = model;
	}

    /**
     * Create a GUI for admin client board application.
     */
	
//	for GUI test, use the join the game request
	public void process() {
//		AdminClientBoardApplication appl = new AdminClientBoardApplication(model);
		String game_id = app.getGameID();
		
		if (game_id!=null){
		// send the request to check a existing game.
		String xmlString = Message.requestHeader() + "<showGameStateRequest gameId='"+game_id+"'/></request>";
//		String xmlString = Message.requestHeader() + "<joinGameRequest gameId='"+game_id+"' name='nextOne'/></request>";
		Message m = new Message (xmlString);

		app.getServerAccess().sendRequest(m);
		}
	}
	
	
//	/** Make the request on the server and wait for response. */
//	public void process() {
//		String game_id = app.getGameID();
//		// send the request to check a existing game.
//		String xmlString = Message.requestHeader() + "<checkGameRequest gameId='"+game_id+"/></request>";
//		Message m = new Message (xmlString);
//
//		app.getServerAccess().sendRequest(m);
//	}

}
