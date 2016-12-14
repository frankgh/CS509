package com.wordsweeper.server.api.model;

import java.util.List;

/**
 * This class introduced board attributes.
 * @author francisco
 */
public class Board {

    /**
     * The Columns.
     */
    int columns;

    /**
     * The Cell list.
     */
    List<Cell> cellList;

    /**
     * The Rows.
     */
    int rows;

    /**
     * The Bonus cell location.
     */
    Location bonusCellLocation;
    
    /**
     * 
     * Constructor
     */
    public Board(int columns, int rows, List<Cell> cellList, Location bonus) {
        this.columns = columns;
        this.rows = rows;
        this.cellList = cellList;
        this.bonusCellLocation = bonus;
    }
    
    /**
     * 
     * Empty constructor
     */
    public Board() {
    }
    
    /**
     * Gets columns.
     *
     * @return the columns
     */
    public int getColumns() {
        return columns;
    }

    /**
     * Gets cell list.
     *
     * @return the cell list
     */
    public List<Cell> getCellList() {
        return cellList;
    }

    /**
     * Gets rows.
     *
     * @return the rows
     */
    public int getRows() {
        return rows;
    }

    /**
     * Gets bonus cell location.
     *
     * @return the bonus cell location
     */
    public Location getBonusCellLocation() {
        return bonusCellLocation;
    }
}
