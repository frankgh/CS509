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
     * Returns the number of rows times the number of columns
     *
     * @return the number of letters in the board
     */
    int getLetterCount() {
        return rows * columns;
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
        this.cellList = new ArrayList<>(getLetterCount());

        for (int i = 0; i < getLetterCount(); i++) {
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

        for (int i = 0; i < (amount * columns); i++) {
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

        for (int i = 0; i < amount * rows; i++) {
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

    int getCellIndexJustBelow(int row, int column) {
        return (columns * (row+1)) + column;
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
}
