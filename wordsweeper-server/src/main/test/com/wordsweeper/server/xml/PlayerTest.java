package com.wordsweeper.server.xml;

import com.wordsweeper.server.ServerThread;
import com.wordsweeper.server.api.model.Board;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;



public class PlayerTest {

	@Test
	public void constructor() throws Exception {
		Player player = new Player();
		
		player.setBoard("abcd");
		assertEquals(player.getBoard(), "abcd");
		
		player.setName("testPlayer");
		assertEquals(player.getName(), "testPlayer");
		
		player.setPosition("1,1");
		assertEquals(player.getPosition(), "1,1");
		
		player.setScore((long) 123);
		assertEquals(player.getScore(), (long) 123);
		
	}
}
