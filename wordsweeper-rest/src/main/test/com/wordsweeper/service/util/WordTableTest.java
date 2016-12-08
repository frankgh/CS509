package com.wordsweeper.service.util;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.wordsweeper.service.model.Location;

public class WordTableTest {
	
	@Test
	public void constructor() throws Exception {
		WordTable wordTable = new WordTable();
		assertTrue(wordTable.isWord("yes")); // also calls loadWordTable since table is null
		assertEquals(wordTable.table.size(), 160232);
		assertTrue(wordTable.isWord("yes"));
		
	}
	
	

}