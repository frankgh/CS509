package com.wordsweeper.service.util;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.wordsweeper.service.model.Location;

public class UtilTest {

	@Test
	public void testParseCellPositions1() {
		String cellPositions = "3,1";
		List<Location> locations = Util.parseCellPositions(cellPositions);

		assertNotNull(locations);
		assertEquals(locations.size(), 1);

		assertEquals(locations.get(0).getColumn(), 2);
		assertEquals(locations.get(0).getRow(), 0);
	}

	@Test
	public void testParseCellPositions2() {
		String cellpositions = "5,3|4,6|2,3";
		List<Location> locations = Util.parseCellPositions(cellpositions);
		assertNotNull(locations);
		assertEquals(3, locations.size());

		assertEquals(4, locations.get(0).getColumn());
		assertEquals(3, locations.get(1).getColumn());
		assertEquals(1, locations.get(2).getColumn());

		assertEquals(2, locations.get(0).getRow());
		assertEquals(5, locations.get(1).getRow());
		assertEquals(2, locations.get(2).getRow());
	}

	@Test
	public void testParseInteger() {
		String parseInt = "5";
		Integer val = Util.parseInteger(parseInt);

		assertNotNull(val);
		assertEquals(val.intValue(), 5);

	}

	@Test
	public void testParseIntegerNull() {
		String parseInt = "a";
		Integer val = Util.parseInteger(parseInt);

		assertNull(val);

	}
}
