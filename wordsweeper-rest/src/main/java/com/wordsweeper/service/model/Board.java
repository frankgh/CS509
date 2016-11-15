package com.wordsweeper.service.model;

import com.wordsweeper.service.util.RandomUtil;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by francisco on 9/13/16.
 *
 * @author francisco
 */
@Entity
@Table(name = "board")
public class Board {

    /**
     * The Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id; /* The id of the board */

    /**
     * The Rows.
     */
    @Column(name = "rows")
    int rows; /* The number of rows in the board */

    /**
     * The Columns.
     */
    @Column(name = "columns")
    int columns; /* The number of columns in the board */

    /**
     * The Cell list.
     */
    @OneToMany
    List<Cell> cellList; /* A list of all the cells that make up the board */

    /**
     * The Bonus cell location.
     */
    @Embedded
    Location bonusCellLocation; /* The location of the bonus cell */

    /**
     * Instantiates a new Board.
     */
    protected Board() {
    }

    /**
     * Instantiates a new Board.
     *
     * @param size the size
     */
    public Board(int size) {
        this(size, size);
    }

    /**
     * Instantiates a new Board.
     *
     * @param rows    the rows
     * @param columns the columns
     */
    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;

        reset(); /* initializes the board */
    }

    /**
     * Grows the board by the specified amount of rows and columns
     *
     * @param amount the amount of columns and rows to increase
     */
    public void grow(int amount) {
        growX(amount);
        growY(amount);
    }

    /**
     * Reset the board
     */
    public void reset() {
        this.cellList = new ArrayList<>(size());

        for (int i = 0; i < size(); i++) {
            addCell();
        }

        generateMultiplierCell();
    }

    /**
     * Grows the board on the x direction by the specified amount
     * it adds columns to the right of the board
     *
     * @param amount the amount to grow
     */
    private void growX(int amount) {
        columns += amount;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < amount; j++) {
                addCell(((rows + amount) * i) + rows + j);
            }
        }
    }

    /**
     * Grows the board on the y direction by the specified amount.
     * It adds rows to the bottom of the board
     *
     * @param amount the amount to grow
     */
    private void growY(int amount) {
        rows += amount;

        for (int i = 0; i < amount * columns; i++) {
            addCell();
        }
    }

    /**
     * Chooses a single cell to be the multiplier cell
     */
    private void generateMultiplierCell() {
        int multiplierCellIndex = RandomUtil.nextInt(cellList.size());
        bonusCellLocation = new Location(getRow(multiplierCellIndex), getColumn(multiplierCellIndex));
    }

    /**
     * The column for a given index in the list of cells
     *
     * @param index a zero-based index of cells
     * @return the zero-based column for the given index
     */
    public int getColumn(int index) {
        return index % columns;
    }

    /**
     * The row for a given index in the list of cells
     *
     * @param index a zero-based index of the cells
     * @return the zero-based row for the given index
     */
    public int getRow(int index) {
        return index / rows;
    }

    /**
     * Map a row and column into an index for the cellList
     *
     * @param row    the row
     * @param column the column
     * @return the index of the array
     */
    private int getCellIndex(int row, int column) {
        return (columns * row) + column;
    }

    /**
     * Map a row and column into the index of the cellList right below
     * another cell
     *
     * @param row    the row
     * @param column the column
     * @return the index of the cell just below the given row and column
     */
    private int getCellIndexJustBelow(int row, int column) {
        return getCellIndex(row + 1, column);
    }

    /**
     * Adds a cell to the list of cells
     */
    private void addCell() {
        this.cellList.add(new Cell());
    }

    /**
     * Inserts a cell at the specified index
     *
     * @param index index at which the cell is to be inserted
     */
    private void addCell(int index) {
        this.cellList.add(index, new Cell());
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
     * Gets columns.
     *
     * @return the columns
     */
    public int getColumns() {
        return columns;
    }

    /**
     * The size of the board
     *
     * @return the size of the board
     */
    public int size() {
        return rows * columns;
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
     * Gets bonus cell location.
     *
     * @return the bonus cell location
     */
    public Location getBonusCellLocation() {
        return bonusCellLocation;
    }


    /**
     * Gets a letter at a given location
     *
     * @param location the letter location
     * @return the letter at the given location, or null if the location is invalid
     */
    public Letter getLetterAtLocation(Location location) {
        int index = getCellIndex(location.getRow(), location.getColumn());

        if (index >= 0 && index < cellList.size()) {
            return cellList.get(index).getLetter();
        }

        return null;
    }

    /**
     * Determine if two locations are adjacent in this board.
     *
     * @param location1 the first location
     * @param location2 the second location
     * @return true if the locations are adjacent, false otherwise
     */
    public boolean areLocationsAdjacent(Location location1, Location location2) {

        return isLocationInBoard(location1) && /* Make sure location1 is in the board */
                isLocationInBoard(location2) && /* Make sure location2 is in the board */
                Math.abs(location1.getRow() - location2.getRow()) <= 1 &&
                Math.abs(location1.getColumn() - location2.getColumn()) <= 1;
    }

    /**
     * Determine if a location is within the board's bounds
     *
     * @param location the location
     * @return true if the location is within the board's bound, false otherwise
     */
    public boolean isLocationInBoard(Location location) {
        return isLocationInBoard(location.getRow(), location.getColumn());
    }

    /**
     * Determine if a location is within the board's bounds
     *
     * @param row    the row
     * @param column the column
     * @return true if the location is within the board's bound, false otherwise
     */
    private boolean isLocationInBoard(int row, int column) {
        return row >= 0 && row < getRows() && column >= 0 && column < getColumns();
    }

    /**
     * Claim the word and replace with new cells
     *
     * @param word the word to claim
     */
    public void claimWord(Word word) {

        for (Location location : word.locations) {
            bubbleUp(location); /* move up all the cells below */
            cellList.remove(getCellIndex(location.getRow(), location.getColumn()));
        }

    }

    /**
     * Move up all the cells below the cell in location
     *
     * @param location the location
     */
    private void bubbleUp(Location location) {

        if (!isLocationInBoard(location)) {
            return;
        }

        if (!isLocationInBoard(location.getRow() + 1, location.getColumn())) {
            return;
        }

        bubbleUp(new Location(location.getRow() + 1, location.getColumn()));

        int belowIx = getCellIndexJustBelow(location.getRow(), location.getColumn());
        int index = getCellIndex(location.getRow(), location.getColumn());

        Cell cell = cellList.remove(belowIx);

        if (location.getRow() + 2 == getRows()) {
            addCell(belowIx);
        }

        cellList.add(index + 1, cell);
    }
}
