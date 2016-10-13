package com.wordsweeper.server.model;

import java.util.List;

/**
 * Created by francisco on 10/12/16.
 */
public class Board {

    private int columns;

    private List<Cell> cellList;

    private int rows;

    private Location bonusCellLocation;

    public int getColumns() {
        return columns;
    }

    public List<Cell> getCellList() {
        return cellList;
    }

    public int getRows() {
        return rows;
    }

    public Location getBonusCellLocation() {
        return bonusCellLocation;
    }
}
