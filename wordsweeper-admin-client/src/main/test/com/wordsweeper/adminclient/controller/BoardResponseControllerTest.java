package com.wordsweeper.adminclient.controller;

import static org.junit.Assert.*;

import java.awt.Color;
import java.io.BufferedReader;
import org.mockito.Mockito;
import static org.mockito.Matchers.*;
import org.junit.Test;
import xml.Message;
import xml.Parser;
	
import com.wordsweeper.adminclient.model.AdminClientModel;
import com.wordsweeper.adminclient.view.AdminClientApplication;
/**
 * This is the class for admin client model.
 */
public class BoardResponseControllerTest {
	@Test
	public void constructor() throws Exception {

		AdminClientModel model = new AdminClientModel();
		AdminClientApplication app = new AdminClientApplication(model);
		BoardResponseController boaCont = new BoardResponseController(app, model);
		Parser parser = new Parser();
		assertEquals(app.gamelist.getRowCount(), 0);	
		BufferedReader bufferedReader = org.mockito.Mockito.mock(BufferedReader.class);
		String input = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><response id=\"49b7d59c-87bd-42a0-bcfb-cb05c51beb33\" success=\"true\" version=\"1.0\"><boardResponse bonus=\"7,2\" contents=\"R,T,E,N,L,R,P,T,X,I,K,K,L,A,S,B,V,T,S,S,R,A,I,L,S,M,G,I,I,R,E,I,B,E,S,R,C,N,S,L,N,E,O,N,M,P,R,O,A\" gameId=\"n65agh23rrusg7lcc4rm80fuj\" managingUser=\"ye\" size=\"7\"><player board=\"R,T,E,N,T,X,I,K,S,B,V,T,A,I,L,S\" name=\"ye\" position=\"1,1\" score=\"0\"/></boardResponse></response>";
		if (!Message.configure("wordsweeper.xsd") && !Message.configure("wordsweeper-admin-client/wordsweeper.xsd")) {
        System.exit(0);
		}
		Mockito.when(bufferedReader.readLine()).thenReturn(input).thenReturn(input);
		Message testMessage = Parser.extractResponse(bufferedReader);
		boaCont.process(testMessage);
		
		int B_r = 2;
		int B_c = 7;
		assertEquals(app.ColorMap[B_r-1][B_c-1][4], Color.YELLOW);			
	}
}
