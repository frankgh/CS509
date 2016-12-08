package com.wordsweeper.service.util;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * Tests the MapUtil class
 *
 * @author francisco
 */
public class MapUtilTest {

    Map<String, Integer> testMap = null;

    @Before
    public void setUp() throws Exception {

        Random random = new Random(System.currentTimeMillis());
        testMap = new HashMap<>(1000);
        for (int i = 0; i < 1000; ++i) {
            testMap.put("SomeString" + random.nextInt(), random.nextInt());
        }
    }

    /**
     * Test that the map is sorted by value in ascending order
     */
    @Test
    public void testSortByValueAscending() {

        testMap = MapUtil.sortByValue(testMap);
        assertEquals(1000, testMap.size());

        Integer previous = null;
        for (Map.Entry<String, Integer> entry : testMap.entrySet()) {
            assertNotNull(entry.getValue());
            if (previous != null) {
                assertTrue(entry.getValue() >= previous);
            }
            previous = entry.getValue();
        }
    }

    /**
     * Test that the map is sorted by value in descending order
     */
    @Test
    public void testSortByValueDescending() {

        testMap = MapUtil.sortByValue(testMap, MapUtil.DESCENDING);
        assertEquals(1000, testMap.size());

        Integer previous = null;
        for (Map.Entry<String, Integer> entry : testMap.entrySet()) {
            assertNotNull(entry.getValue());
            if (previous != null) {
                assertTrue(previous >= entry.getValue());
            }
            previous = entry.getValue();
        }
    }

    /**
     * Test the sum of all values in the map
     */
    @Test
    public void testSumValues1() {
        Map<String, Double> testMap = new HashMap<>(10);
        testMap.put("A", 1.0);
        testMap.put("B", 1.0);
        testMap.put("C", 1.0);
        testMap.put("D", 1.0);
        testMap.put("E", 1.0);
        testMap.put("F", 1.0);
        testMap.put("G", 1.0);
        testMap.put("H", 1.0);
        testMap.put("I", 1.0);
        testMap.put("J", 1.0);

        assertEquals(10.0, MapUtil.sumValues(testMap), 0);
    }

    /**
     * Test the sum of all values in the map
     */
    @Test
    public void testSumValues2() {
        Map<String, Double> testMap = new HashMap<>(10);
        testMap.put("A", 2.0);
        testMap.put("B", 2.0);
        testMap.put("C", 2.0);
        testMap.put("D", 2.0);
        testMap.put("E", 2.0);
        testMap.put("F", 2.0);
        testMap.put("G", 2.0);
        testMap.put("H", 2.0);
        testMap.put("I", 2.0);
        testMap.put("J", 2.0);

        assertEquals(20.0, MapUtil.sumValues(testMap), 0);
    }
}