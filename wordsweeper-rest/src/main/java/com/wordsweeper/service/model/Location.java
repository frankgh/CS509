package com.wordsweeper.service.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;


/**
 * The type Location.
 * @author francisco
 */
@Embeddable
public class Location {

    /**
     * The Column.
     */
    @Column(name = "column")
    int column;

    /**
     * The Row.
     */
    @Column(name = "row")
    int row;

    /**
     * Instantiates a new Location.
     */
    public Location() {
    }

    /**
     * Instantiates a new Location.
     *
     * @param row    the row
     * @param column the column
     */
    public Location(int row, int column) {
        this.row = row;
        this.column = column;
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
}
