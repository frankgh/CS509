package com.wordsweeper.adminclient.controller;

import static org.junit.Assert.*;

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
public class ListGamesResponseControllerTest {
	@Test
	public void constructor() throws Exception {

		AdminClientModel model = new AdminClientModel();
		AdminClientApplication app = new AdminClientApplication(model);
		ListGamesResponseController resCont = new ListGamesResponseController(app, model);
		Parser parser = new Parser();
		assertEquals(app.gamelist.getRowCount(),0);	
		BufferedReader bufferedReader = org.mockito.Mockito.mock(BufferedReader.class);
		String input = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><response id=\"cc667680-81d6-4d5b-a23b-3edc2cdf866f\" success=\"true\" version=\"1.0\"><listGamesResponse><gameBrief gameId=\"nc99qujnk002q3akkhpde400s0\" players=\"1\"/><gameBrief gameId=\"n65agh23rrusg7lcc4rm80fuj\" players=\"1\"/></listGamesResponse></response>";
		if (!Message.configure("wordsweeper.xsd") && !Message.configure("wordsweeper-admin-client/wordsweeper.xsd")) {
        System.exit(0);
		}
		Mockito.when(bufferedReader.readLine()).thenReturn(input).thenReturn(input);
		Message testMessage = Parser.extractResponse(bufferedReader);
		resCont.process(testMessage);
		
		assertEquals(app.gamelist.getRowCount(), 2);		
	}
}
