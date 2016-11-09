package com.wordsweeper.adminclient.controller;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import xml.Message;
import com.wordsweeper.adminclient.model.AdminClientModel;
import com.wordsweeper.adminclient.view.AdminClientBoardApplication;

public class BoardDetailResponseController {
	
	public AdminClientBoardApplication app;
	public AdminClientModel model;
	
	public BoardDetailResponseController(AdminClientBoardApplication a, AdminClientModel m) {
		this.app = a;
		this.model = m;
	}
	
	public void process(Message response) {
		// this refers to the outer node of the Message DOM (in this case, updateResponse).
		Node boardResponse = response.contents.getFirstChild();
		NamedNodeMap map = boardResponse.getAttributes();
		
		String gameId = map.getNamedItem("gameId").getNodeValue();
		app.setGameIDNumber().setText(gameId);
		
		String boardcontents = map.getNamedItem("contents").getNodeValue();
		app.setBoard().setText(boardcontents);

		NodeList list = boardResponse.getChildNodes();
		for (int i = 0; i < list.getLength(); i++) {
			Node n = list.item(i);
			String pname = n.getAttributes().getNamedItem("name").getNodeValue();
			app.getPlayerList().append("  " + pname  + "\n");
		}
		
	}

}
