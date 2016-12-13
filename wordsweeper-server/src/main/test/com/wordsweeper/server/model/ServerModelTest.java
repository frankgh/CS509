package com.wordsweeper.server.model;

import com.wordsweeper.server.ServerThread;
import com.wordsweeper.server.api.model.Board;
import com.wordsweeper.server.api.model.Game;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;



public class ServerModelTest {

	@Test
	public void constructor() throws Exception {
		ServerModel serModel = new ServerModel();
		ServerThread client = Mockito.mock(ServerThread.class);
		Mockito.when(client.getData()).thenReturn("testClient");
		Mockito.when(client.id()).thenReturn("1");
		assertTrue(serModel.createGame(client, "testID"));
		assertFalse(serModel.createGame(client, "testID"));
		
		ServerThread client1 = Mockito.mock(ServerThread.class);
		Mockito.when(client1.getData()).thenReturn("testClient1");
		Mockito.when(client1.id()).thenReturn("2");
		Game game = new Game();
		game.setManagingPlayerName("testClient");
		game.setUniqueId("testID");
		assertFalse(serModel.joinGame(client, game));
		assertFalse(serModel.exitGame(client1));
		assertEquals(serModel.idsByGameId("testID").size(), 1);
		assertNull(serModel.idsByGameId("testID1"));
		assertTrue(serModel.joinGame(client1, game));
		
		
		
		assertTrue(serModel.exitGame(client1));
		assertFalse(serModel.isClientInGame(client1));
		
		assertNull(serModel.getGameId(client1));
		assertEquals(serModel.getGameId(client), "testID");
		
		assertTrue(serModel.isManagingPlayer(client));
		assertFalse(serModel.isManagingPlayer(client1));
		
		assertTrue(serModel.exitGame(client));
		
		assertFalse(serModel.updateGame(game));
		game.setManagingPlayerName("testWrong");
		assertTrue(serModel.createGame(client, "testID"));
		assertTrue(serModel.updateGame(game));
		assertFalse(serModel.updateGame(game));
		
		game.setUniqueId("testWrong");
		assertFalse(serModel.updateGame(game));
		
	}
}
