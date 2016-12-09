package com.wordsweeper.service.model;

import com.wordsweeper.service.util.RandomUtil;

import javax.persistence.*;

/**
 * Created by francisco on 9/13/16.
 *
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
