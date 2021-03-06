package com.wordsweeper.server.api.model;

import java.util.List;

/**
 * The type Location.
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
     * 
     * Constructor
     */
    public Location(int column, int row) {
        this.column = column;
        this.row = row;
    }
    
    /**
     * 
     * Empty constructor
     */
    public Location() {
    }
    
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
