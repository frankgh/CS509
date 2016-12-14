package com.wordsweeper.adminclient.controller;
import xml.Message;
import com.wordsweeper.adminclient.IMessageHandler;
import com.wordsweeper.adminclient.view.AdminClientApplication;
import com.wordsweeper.adminclient.view.AdminClientBoardApplication;

/**
 * This class is for admin-client interface application.
 */

public class AdminClientBoardMessageHandler implements IMessageHandler{
	AdminClientBoardApplication app;

	/**
	 *
	 * @param app the application for user interface.
	 */
	
	public AdminClientBoardMessageHandler(AdminClientBoardApplication app) {
		this.app = app;
	}

	/**
	 *
	 * @param response
	 */

	public void process(Message response) {
		String type = response.contents.getFirstChild().getLocalName();

		// process each response that comes in with its own controller.
		if (type.equals ("boardResponse")) {
			// What happens now that we are connected?
			new BoardDetailResponseController(app, app.model).process(response);

		// only here to show messages as they are received by the client
		// this isn't needed.
		System.out.println(response);
		}

	}
}
