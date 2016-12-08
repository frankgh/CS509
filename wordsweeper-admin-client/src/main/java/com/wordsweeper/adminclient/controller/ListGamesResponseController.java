package com.wordsweeper.adminclient.controller;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.wordsweeper.adminclient.model.AdminClientModel;
import com.wordsweeper.adminclient.view.AdminClientApplication;

import xml.Message;


/**
 * This is a class that listing response from game.
 */
public class ListGamesResponseController {
	
	public AdminClientApplication app;
	public AdminClientModel model;

    /**
     *
     * @param a the admin client application
     * @param m the admin client model
     */
	public ListGamesResponseController(AdminClientApplication a, AdminClientModel m) {
		this.app = a;
		this.model = m;
	}

    /**
     *
     * @param response the respond from list game controller.
     */
	
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