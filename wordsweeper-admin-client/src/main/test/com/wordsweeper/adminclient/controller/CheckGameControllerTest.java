package com.wordsweeper.adminclient.controller;

import static org.junit.Assert.*;
import org.junit.Test;
import org.mockito.Mockito;

import com.wordsweeper.adminclient.ServerAccess;
import com.wordsweeper.adminclient.view.AdminClientApplication;
import com.wordsweeper.core.xml.BoardResponse;
import com.wordsweeper.core.xml.ConnectResponse;
import com.wordsweeper.core.xml.ListGamesResponse;
import com.wordsweeper.core.xml.Response;
/**
 * This is the class for admin client model.
 */
public class CheckGameControllerTest {
	@Test
	public void constructor() throws Exception {
		AdminClientApplication app = Mockito.mock(AdminClientApplication.class);
		CheckGameController  checkCon = new CheckGameController(app);
		
		Mockito.when(app.getGameID()).thenReturn(null);
		checkCon.process();
		
		Mockito.when(app.getGameID()).thenReturn("testID");
		ServerAccess sa = Mockito.mock(ServerAccess.class);
		Mockito.when(app.getServerAccess()).thenReturn(sa);
		Mockito.when(sa.sendRequest(null)).thenReturn(true);
		checkCon.process();
		

	}
}
