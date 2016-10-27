package com.wordsweeper.service.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Created by Francisco Guerrero on 9/27/2016.
 */
@Embeddable
public class Location {

    @Column(name = "column")
    int column;

    @Column(name = "row")
    int row;

    public Location() {
    }

    public Location(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }
    
    protected void setRow(int row) {
    	this.row = row;
    }
    
    protected void setColumn(int column) {
    	this.column = column;
    }
}
