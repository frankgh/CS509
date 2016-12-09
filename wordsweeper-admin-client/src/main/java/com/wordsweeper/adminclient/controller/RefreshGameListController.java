package com.wordsweeper.adminclient.controller;

import xml.Message;
import com.wordsweeper.adminclient.model.AdminClientModel;
import com.wordsweeper.adminclient.view.AdminClientApplication;

/**
 * This class introduces refreshing game list controller.
 */
public class RefreshGameListController {
	
	AdminClientApplication app;
	AdminClientModel model;

    /**
     *
     * @param app admin client application.
     * @param model admin client model.
     */
	
	public RefreshGameListController(AdminClientApplication app, AdminClientModel model) {
		this.app = app;
		this.model = model;
	}

//	/** Make the request on the server and wait for response. */
 
	public void process() {
		// send the request to create the game.
//		String xmlString = Message.requestHeader() + "<createGameRequest name='samplePlayer'/></request>";
		String xmlString = Message.requestHeader() + "<listGamesRequest/></request>";
		Message m = new Message (xmlString);

		// Request the lock (this might not succeed).
		app.getServerAccess().sendRequest(m);
	}

}
