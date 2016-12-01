package com.wordsweeper.adminclient.controller;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.wordsweeper.adminclient.model.AdminClientModel;
import com.wordsweeper.adminclient.view.AdminClientApplication;

import xml.Message;

public class ListGamesResponseController {
	
	public AdminClientApplication app;
	public AdminClientModel model;
	
	public ListGamesResponseController(AdminClientApplication a, AdminClientModel m) {
		this.app = a;
		this.model = m;
	}
	
	public void process(Message response) {
		// this refers to the outer node of the Message DOM (in this case, updateResponse).
		Node listGamesResponse = response.contents.getFirstChild();
		
		NodeList gameBriefNodeList = listGamesResponse.getChildNodes();
		
		
		if (gameBriefNodeList != null){
			
			for (int i = 0; i < gameBriefNodeList.getLength(); i++) {
			Node n = gameBriefNodeList.item(i);
			String gameId = n.getAttributes().getNamedItem("gameId").getNodeValue();
			String players = n.getAttributes().getNamedItem("players").getNodeValue();
			app.getResponseArea().addElement(gameId);
		}
			
		}
		
//		for demo purpose
//		NamedNodeMap map = boardResponse.getAttributes();
//		String boardcontents = map.getNamedItem("contents").getNodeValue();
//		app.getConnection().setText(boardcontents);
//		String gameId = map.getNamedItem("gameId").getNodeValue();
//		app.getResponseArea().addElement(gameId);
		
//		The actual get gameID list loop
//		NodeList list = boardResponse.getChildNodes();
//		for (int i = 0; i < list.getLength(); i++) {
//			Node n = list.item(i);
//			String gameId = n.getAttributes().getNamedItem("gameId").getNodeValue();
//			app.getResponseArea().addElement(gameId);
//		}
		

		
		
	}

}
