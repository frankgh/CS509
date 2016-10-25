package com.wordsweeper.service.model;

import com.wordsweeper.service.util.RandomUtil;

import javax.persistence.*;

/**
 * Created by francisco on 9/13/16.
 */
@Entity
@Table(name = "cell")
public class Cell {

    public static final int DEFAULT_CELL_MULTIPLIER = 1;
    public static final int MAX_CELL_MULTIPLIER = 10;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id; /* The id of the cell */

    @Embedded
    Letter letter;

    public Cell() {
        this.letter = new Letter(RandomUtil.getRandomCharacter(), 1);
    }

    public Letter getLetter() {
        return letter;
    }
}
