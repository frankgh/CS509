package com.wordsweeper.adminclient.controller;

import xml.Message;
import com.wordsweeper.adminclient.model.AdminClientModel;
import com.wordsweeper.adminclient.view.AdminClientApplication;

public class RefreshGameListController {
	
	AdminClientApplication app;
	AdminClientModel model;
	
	public RefreshGameListController(AdminClientApplication app, AdminClientModel model) {
		this.app = app;
		this.model = model;
	}

//	/** Make the request on the server and wait for response. */
//	public void process() {
//		// send the request to create the game.
//		String xmlString = Message.requestHeader() + "<refreshGameListRequest /></request>";
//		Message m = new Message (xmlString);
//
//		app.getServerAccess().sendRequest(m);
//	}
	
//	For Test GUI works, use the create game requests 
	public void process() {
		// send the request to create the game.
		String xmlString = Message.requestHeader() + "<createGameRequest name='samplePlayer'/></request>";
		Message m = new Message (xmlString);

		// Request the lock (this might not succeed).
		app.getServerAccess().sendRequest(m);
	}

}
