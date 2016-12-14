package com.wordsweeper.service.model;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * This class test the cell in a board.
 */
public class CellTest {
	@Test
	public void constructor() throws Exception {
		Cell cell = new Cell();
		Letter l1 = cell.getLetter();
		assertFalse(l1 == null);
		}
}