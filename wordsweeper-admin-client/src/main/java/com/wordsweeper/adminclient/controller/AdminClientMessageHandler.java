package com.wordsweeper.adminclient.controller;
import xml.Message;
import com.wordsweeper.adminclient.IMessageHandler;
import com.wordsweeper.adminclient.view.AdminClientApplication;

/**
 * This class specified admin client handler.
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
	 * @param response the respond from the admin client message controller..
	 */
	public void process(Message response) {
		String type = response.contents.getFirstChild().getLocalName();

		// list game response is to display the existing game IDs and player numbers.
		if (type.equals ("listGamesResponse")) {
			/** process the ListGameResponseController to handel the game list info */
			new ListGamesResponseController(app, app.model).process(response);
		/** connect response is to show the connection info */
		} else if (type.equals ("connectResponse")) {
			app.getConnection().setText(response.toString());
		/** board response is to display the board content and relevant players' info */
		} else if (type.equals("boardResponse")) {
			new BoardResponseController(app, app.model).process(response);
		}
		
		System.out.println(response);
	}

}
