package com.wordsweeper.adminclient.controller;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import xml.Message;
import com.wordsweeper.adminclient.model.AdminClientModel;
import com.wordsweeper.adminclient.view.AdminClientApplication;

public class BoardResponseController {
	
	public AdminClientApplication app;
	public AdminClientModel model;
	
	public BoardResponseController(AdminClientApplication a, AdminClientModel m) {
		this.app = a;
		this.model = m;
	}
	
	public void process(Message response) {
		// this refers to the outer node of the Message DOM (in this case, updateResponse).
		Node boardResponse = response.contents.getFirstChild();
		
		NamedNodeMap map = boardResponse.getAttributes();
		String boardcontents = map.getNamedItem("contents").getNodeValue();
		app.setContent(boardcontents);

//		String gameId = map.getNamedItem("gameId").getNodeValue();
//		app.setGameIDNumber().setText(gameId);
		
//		The actual get gameID list loop
//		NodeList list = boardResponse.getChildNodes();
//		for (int i = 0; i < list.getLength(); i++) {
//			Node n = list.item(i);
//			String gameId = n.getAttributes().getNamedItem("gameId").getNodeValue();
//			app.getResponseArea().addElement(gameId);
//		}
		

		
		
	}

}
