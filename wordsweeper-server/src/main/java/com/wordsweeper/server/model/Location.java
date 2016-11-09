package com.wordsweeper.server.model;

/**
 * Created by francisco on 10/12/16.
 */
public class Location {

    private int column;

    private int row;

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    @Override
    public String toString() {
        return (getRow() + 1) + "," + (getColumn() + 1);
    }
}
