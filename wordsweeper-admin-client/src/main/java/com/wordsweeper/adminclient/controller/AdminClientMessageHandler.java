package com.wordsweeper.adminclient.controller;
import xml.Message;
import com.wordsweeper.adminclient.IMessageHandler;
import com.wordsweeper.adminclient.view.AdminClientApplication;

/**
 * This class implement admin-client handler.
 */

public class AdminClientMessageHandler implements IMessageHandler{
	
	AdminClientApplication app;

	/**
	 *
	 * @param app the user interface application.
	 */
	
	public AdminClientMessageHandler(AdminClientApplication app) {
		this.app = app;
	}

	/**
	 *
	 * @param response the respond from the adminclient message controller..
	 */
	public void process(Message response) {
		String type = response.contents.getFirstChild().getLocalName();

		// process each response that comes in with its own controller.
		if (type.equals ("listGamesResponse")) {
			// What happens now that we are connected?
			new ListGamesResponseController(app, app.model).process(response);
		} else if (type.equals ("connectResponse")) {
			app.getConnection().setText(response.toString());
		} else if (type.equals("boardResponse")) {
//			???????????????????????
			new BoardResponseController(app, app.model).process(response);
		}
		

		// only here to show messages as they are received by the client
		// this isn't needed.
		System.out.println(response);
	}
	

}
