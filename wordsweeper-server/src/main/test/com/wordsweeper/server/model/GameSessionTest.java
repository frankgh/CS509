package com.wordsweeper.server.model;

import com.wordsweeper.server.api.model.Board;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;



public class GameSessionTest {

	@Test
	public void constructor() throws Exception {
		GameSession gameSes = new GameSession("gameID");
		assertTrue(gameSes.isEmpty());
	}
}
