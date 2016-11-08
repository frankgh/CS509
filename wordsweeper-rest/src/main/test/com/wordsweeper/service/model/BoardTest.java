package com.wordsweeper.service.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by francisco on 11/1/16.
 */
public class BoardTest {
    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void getLetterCount() throws Exception {

    }

    @Test
    public void grow() throws Exception {
        Board board = new Board();
        board.grow(1);

        assertEquals(8, board.getColumns());
        assertEquals(8, board.getRows());
    }

    @Test
    public void validateWord() throws Exception {

    }

    @Test
    public void reset() throws Exception {

    }

    @Test
    public void getColumn() throws Exception {

    }

    @Test
    public void getRow() throws Exception {

    }

    @Test
    public void getRows() throws Exception {

    }

    @Test
    public void getColumns() throws Exception {

    }

    @Test
    public void getCellList() throws Exception {

    }

    @Test
    public void getBonusCellLocation() throws Exception {

    }

}