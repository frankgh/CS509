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

    List<Letter> letterList; /* A list of all letters available in the board */

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

    public void reset() {
        this.letterList = new ArrayList<Letter>(rows * columns);

        for (int i = 0; i < getLetterCount(); i++) {
            this.letterList.add(new Letter(RandomUtil.getRandomCharacter(), 1));
        }
    }

}
