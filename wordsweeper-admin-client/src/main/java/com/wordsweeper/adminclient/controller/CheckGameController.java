package com.wordsweeper.adminclient.controller;


import xml.Message;
import com.wordsweeper.adminclient.model.AdminClientModel;
import com.wordsweeper.adminclient.view.AdminClientBoardApplication;
import com.wordsweeper.adminclient.view.AdminClientApplication;
public class CheckGameController {
	AdminClientApplication app;
	AdminClientModel model;
	
	public CheckGameController(AdminClientApplication app, AdminClientModel model) {
		this.app = app;
		this.model = model;
	}

	
//	for GUI test, use the join the game request
	public void process() {
//		AdminClientBoardApplication appl = new AdminClientBoardApplication(model);
		String game_id = app.getGameID();
		// send the request to check a existing game.
		String xmlString = Message.requestHeader() + "<showGameStateRequest gameId='"+game_id+"'/></request>";
//		String xmlString = Message.requestHeader() + "<joinGameRequest gameId='"+game_id+"' name='nextOne'/></request>";
		Message m = new Message (xmlString);

		app.getServerAccess().sendRequest(m);
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
