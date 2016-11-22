package com.wordsweeper.server.api.model;
import com.wordsweeper.server.api.model.Letter;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CellTest {
	
	@Test
	public void constructor() throws Exception {
	Letter cellLet = new Letter();
	cellLet.character = "A";
	Cell cell1 = new Cell ();
	cell1.letter = cellLet;
	assertEquals(cell1.printCharacter(),"A");
	
	
	
	
	}	
	

}
