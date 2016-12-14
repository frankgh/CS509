package com.wordsweeper.service.model;

import com.wordsweeper.service.util.RandomUtil;

import javax.persistence.*;

/**
 * This class specifying cells' attributes in a board.
 * @author francisco
 */
@Entity
@Table(name = "cell")
public class Cell {

    /**
     * The Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id; /* The id of the cell */

    /**
     * The Letter.
     */
    @Embedded
    Letter letter;

    /**
     * Instantiates a new Cell.
     */
    public Cell() {
        this.letter = new Letter(RandomUtil.getRandomCharacter());
    }

    /**
     * Gets letter.
     *
     * @return the letter
     */
    public Letter getLetter() {
        return letter;
    }
}
