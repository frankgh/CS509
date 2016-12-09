package com.wordsweeper.service.util;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.wordsweeper.service.model.Location;

public class UtilTest {
	
	@Test
	public void constructor() throws Exception {
		Util util = new Util();
		List<Location> locationList = new ArrayList<>();
		locationList = util.parseCellPositions("2,3");
		assertEquals(locationList.size(), 1);
		
		locationList = util.parseCellPositions("2,3|3,4");
		assertEquals(locationList.size(), 2);
		
		locationList = util.parseCellPositions("2,2,3");
		assertEquals(locationList, new ArrayList<>());
	}
	
	

}