package com.wordsweeper.core.xml;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class CellTest {

    @Test
    public void constructor() throws Exception {
        Cell testCell = new Cell();

        testCell.setLetter("Qu");
        assertEquals(testCell.getLetter(), "Qu");

        testCell.setPosition("testPos");
        assertEquals(testCell.getPosition(), "testPos");

    }
}
