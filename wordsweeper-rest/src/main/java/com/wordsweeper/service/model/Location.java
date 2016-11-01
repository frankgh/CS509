package com.wordsweeper.service.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;



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
}
