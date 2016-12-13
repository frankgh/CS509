package com.wordsweeper.server.xml;

import com.wordsweeper.server.ServerThread;
import com.wordsweeper.server.api.model.Board;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;



public class CellTest {

	@Test
	public void constructor() throws Exception {
		Cell testCell = new Cell();
		
		testCell.setLetter("Qu");
		assertEquals(testCell.getLetter(), "Qu");
		
		testCell.setPosition("testPos");
		assertEquals(testCell.getPosition(), "testPos");
		
	}
}
