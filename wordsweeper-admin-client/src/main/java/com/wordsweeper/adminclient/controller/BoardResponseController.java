package com.wordsweeper.adminclient.controller;

import java.awt.Color;
import java.util.Arrays;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import xml.Message;
import com.wordsweeper.adminclient.model.AdminClientModel;
import com.wordsweeper.adminclient.view.AdminClientApplication;

/**
 * This is a class for Board response controller.
 */

public class BoardResponseController {
	
	public AdminClientApplication app;
	public AdminClientModel model;

	/**
	 *
	 * @param a the admin client application
	 * @param m the admin client model
	 */
	
	public BoardResponseController(AdminClientApplication a, AdminClientModel m) {
		this.app = a;
		this.model = m;
	}

	/**
	 *
	 * @param response the respond from the board controller
	 */
	
	public void process(Message response) {
		// this refers to the outer node of the Message DOM (in this case, updateResponse).
		Node boardResponse = response.contents.getFirstChild();
		
		NamedNodeMap map = boardResponse.getAttributes();
		String boardcontents = map.getNamedItem("contents").getNodeValue();
		app.setContent(boardcontents);
		
		
//		The actual get gameID list loop
		NodeList list = boardResponse.getChildNodes();
		for (int i = 0; i < list.getLength(); i++) {
			Node n = list.item(i);
			String pname = n.getAttributes().getNamedItem("name").getNodeValue();
			String score = n.getAttributes().getNamedItem("score").getNodeValue();
			String position = n.getAttributes().getNamedItem("position").getNodeValue();
			String index = Integer.toString(i+1);
			Object[] info = new Object[]{index,pname,score,position};
			app.setPlayerInfo(info);
			
			String[] parts = position.split(",");
			int c= Integer.parseInt(parts[0]);
			int r= Integer.parseInt(parts[1]);

			app.addcolor(r, c, i);
			
			
		}
		app.setBoardColor();
		

		
		
	}

}
