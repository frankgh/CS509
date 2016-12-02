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
			String player = n.getAttributes().getNamedItem("players").getNodeValue();
			Object[] info = new Object[]{gameId,player};
			app.SetGameIDInfo(info);
//			app.getResponseArea().addElement(gameId);
		}
			
		}
		
		
		
	}

}