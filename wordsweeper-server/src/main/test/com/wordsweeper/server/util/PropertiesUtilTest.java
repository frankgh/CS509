package com.wordsweeper.server.util;

import static org.junit.Assert.*;

import org.junit.Test;


public class PropertiesUtilTest {

    @Test
    public void constructor() throws Exception {
        PropertiesUtil testUt = new PropertiesUtil();
        assertTrue(testUt.getPathDelim().equals("\\") || testUt.getPathDelim().equals("/"));
        }
}
