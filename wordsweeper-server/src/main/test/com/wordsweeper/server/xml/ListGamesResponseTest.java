package com.wordsweeper.server.xml;

import com.wordsweeper.server.ServerThread;
import com.wordsweeper.server.api.model.Board;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;



public class ListGamesResponseTest {

	@Test
	public void constructor() throws Exception {
		ListGamesResponse res = new ListGamesResponse();
		assertEquals(res.getGameBrief().size(), 0);
		
		}
}
