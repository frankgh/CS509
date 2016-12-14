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
 *
 * @author george
 */
public class RefreshGameListControllerTest {
	@Test
	public void constructor() throws Exception {
		AdminClientApplication app = Mockito.mock(AdminClientApplication.class);
		RefreshGameListController  refrCon = new RefreshGameListController(app);
		
		ServerAccess sa = Mockito.mock(ServerAccess.class);
		Mockito.when(app.getServerAccess()).thenReturn(sa);
		Mockito.when(sa.sendRequest(null)).thenReturn(true);
		refrCon.process();
		

	}
}
