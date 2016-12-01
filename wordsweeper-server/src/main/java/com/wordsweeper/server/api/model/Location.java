package com.wordsweeper.server.api.model;

/**
 * Created by francisco on 10/12/16.
 *
 * @author francisco
 */
public class Location {

    /**
     * The Column.
     */
    int column;

    /**
     * The Row.
     */
    int row;

    /**
     * Gets column.
     *
     * @return the column
     */
    public int getColumn() {
        return column;
    }

    /**
     * Gets row.
     *
     * @return the row
     */
    public int getRow() {
        return row;
    }

    @Override
    public String toString() {
        return (column + 1) + "," + (row + 1);
    }
}
