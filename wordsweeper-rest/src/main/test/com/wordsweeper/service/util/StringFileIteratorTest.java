package com.wordsweeper.service.util;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.junit.Test;

public class StringFileIteratorTest {

	@Test
	public void constructor() throws Exception {
		File f  = new File("src/main/java/com/wordsweeper/service/util/test.txt");
		StringFileIterator iterator1 = new StringFileIterator(f);
		
		StringFileIterator iterator = new StringFileIterator();
		assertFalse(iterator.hasNext());
		
		boolean exceptions = true;
		try{
			iterator.remove();
			exceptions = false;
		} catch (UnsupportedOperationException e) {}

		try{
			iterator = new StringFileIterator(new File("test.txt"));
			exceptions = false;
		} catch (FileNotFoundException fe) {}
		
		try{
			iterator.next();
			exceptions = false;
		} catch (NoSuchElementException ne) {}
		
		assertTrue(exceptions);
	}	

}