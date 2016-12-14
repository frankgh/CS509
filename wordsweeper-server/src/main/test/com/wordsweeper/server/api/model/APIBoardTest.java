package com.wordsweeper.server.api.model;

import com.wordsweeper.server.api.model.Board;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

/**
 * API testing for the game board.
 */

public class APIBoardTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void constructor() throws Exception {
		Board b = new Board ();
	    b.columns = 7 ;
		assertEquals(7, b.getColumns());
		b.rows = 7;
		assertEquals(7, b.getRows());
		
		Location Loc = new Location () ;
		Loc.column = 1;
		Loc.row = 1;
		b.bonusCellLocation = Loc;
		assertEquals(b.getBonusCellLocation(), Loc);
		
		ArrayList<Cell> listCell = new ArrayList<Cell>();
		listCell.add(new Cell());
		b.cellList = listCell;
		assertEquals(b.getCellList(), listCell);


}}
