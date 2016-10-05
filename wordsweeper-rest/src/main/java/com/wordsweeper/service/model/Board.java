package com.wordsweeper.service.model;

import com.wordsweeper.service.util.RandomUtil;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by francisco on 9/13/16.
 */
@Entity
@Table(name = "board")
public class Board {

    @Id
    @GeneratedValue
    @Column(name = "id")
    int id; /* The id of the board */

    @Column(name = "rows")
    int rows; /* The number of rows in the board */

    @Column(name = "columns")
    int columns; /* The number of columns in the board */

    @OneToMany(cascade = CascadeType.ALL)
    List<Cell> cellList; /* A list of all the cells that make up the board */

    protected Board() {
    }

    public Board(int size) {
        this(size, size);
    }

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
    public int getLetterCount() {
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
     * Determine whether a word is valid
     *
     * @param word the word to validate
     * @return true if the word is valid, false if not
     */
    public boolean validateWord(Word word) {
        // TODO: implement this method
        return true;
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

        for (int i = 0; i < cellList.size(); i++) {
            if (i == multiplierCellIndex) {
                cellList.get(i).setMultiplier(Cell.MAX_CELL_MULTIPLIER);
            } else {
                cellList.get(i).setMultiplier(Cell.DEFAULT_CELL_MULTIPLIER);
            }
        }
    }

    private int getCellIndexJustBelow(int row, int column) {
        return (columns * row) + column;
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

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }
}
