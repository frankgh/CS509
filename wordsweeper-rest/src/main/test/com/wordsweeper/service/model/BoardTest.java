package com.wordsweeper.service.model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

/**
 * This class testing the gmae board.
 */

public class BoardTest {
	@Test
	public void constructor() throws Exception {
        Board board = new Board (7);
        assertEquals(7,board.getColumns());
        assertEquals(7,board.getRows());
//        assertEquals(49,board.getLetterCount());
        assertEquals(31,board.getCellIndexJustBelow(3,3));
        
        
        
	}
	
	

}