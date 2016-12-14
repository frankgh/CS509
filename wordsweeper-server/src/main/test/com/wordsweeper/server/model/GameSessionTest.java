package com.wordsweeper.server.model;

import com.wordsweeper.server.ServerThread;
import com.wordsweeper.server.api.model.Board;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;



public class GameSessionTest {

	@Test
	public void constructor() throws Exception {
		GameSession gameSes = new GameSession("gameID");
		assertTrue(gameSes.isEmpty());
		
		ServerThread sThread = Mockito.mock(ServerThread.class);
		assertFalse(gameSes.removeClientState(sThread));
		
		gameSes.addClientState(sThread);
		assertTrue(gameSes.removeClientState(sThread));
	}
}
