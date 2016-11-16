package com.wordsweeper.service.model;

import static org.junit.Assert.*;


import org.junit.Test;


public class BoardTest {
	@Test
	public void constructor() throws Exception {
        Board board = new Board (7);
        assertEquals(7,board.getColumns());
        assertEquals(7,board.getRows());
        assertEquals(49,board.getLetterCount());
        assertEquals(31,board.getCellIndexJustBelow(3,3));
        
        Location Loc = new Location () ;
		Loc.column = 2;
		Loc.row = 5;
		board.bonusCellLocation = Loc;
		assertEquals(board.getBonusCellLocation(), Loc);
        
	}
	
	

}