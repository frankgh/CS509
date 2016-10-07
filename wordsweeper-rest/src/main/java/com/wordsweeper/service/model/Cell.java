package com.wordsweeper.service.model;

import com.wordsweeper.service.util.RandomUtil;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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

    @NotNull
    @Column(name = "multiplier")
    int multiplier;

    public Cell() {
        this.letter = new Letter(RandomUtil.getRandomCharacter(), 1);
        this.multiplier = DEFAULT_CELL_MULTIPLIER;
    }

    public void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }

    public int getId() {
        return id;
    }

    public Letter getLetter() {
        return letter;
    }

    public int getMultiplier() {
        return multiplier;
    }
}
