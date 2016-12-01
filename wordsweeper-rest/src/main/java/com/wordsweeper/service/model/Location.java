package com.wordsweeper.service.model;

import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Column;
import javax.persistence.Embeddable;


/**
 * The type Location.
 *
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

    /**
     * Sets row.
     *
     * @param row the row
     */
    protected void setRow(int row) {
        this.row = row;
    }

    /**
     * Sets column.
     *
     * @param column the column
     */
    protected void setColumn(int column) {
        this.column = column;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
	 */
    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(column)
                .append(row)
                .hashCode();
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals()
	 */
    @Override
    public boolean equals(Object obj) {
        return obj != null &&
                obj instanceof Location &&
                obj.hashCode() == hashCode();
    }
}
