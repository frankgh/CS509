package com.wordsweeper.server.api.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.wordsweeper.service.model.Board;

public class APIBoardTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void constructor() throws Exception {
		Board board = new Board (7)
	    assertEquals(getColumns(7));
		
	}

	@Test
	public void testGetCellList() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetRows() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetBonusCellLocation() {
		fail("Not yet implemented");
	}

}
