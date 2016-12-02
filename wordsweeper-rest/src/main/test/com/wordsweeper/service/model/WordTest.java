package com.wordsweeper.service.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

/**
 * Created by francisco on 10/4/16.
 */
public class WordTest {
	@Test
	public void constructor() throws Exception {
		String content = "test";

		ArrayList<Location> locsArr = new ArrayList<Location>();
		Location tempLoc = new Location();

		for(int i=0; i< content.length(); i++){
			tempLoc = new Location();
			tempLoc.setRow(i);
			tempLoc.setColumn(i);
			locsArr.add(tempLoc);
		}


		Word word = new Word("test", locsArr);
		assertEquals(word.getWordLength(), "test".length());
		assertFalse(word.containsDuplicateCells());

		tempLoc = new Location();
		tempLoc.setRow(0);
		tempLoc.setColumn(0);
		locsArr.add(tempLoc);
		word = new Word("test", locsArr);
		assertTrue(word.containsDuplicateCells());
	}

}