package com.wordsweeper.service.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by francisco on 9/13/16.
 */
@Entity
@Table(name = "letter")
public class Letter {

    @Id
    @GeneratedValue
    @Column(name = "id")
    int id; /* The id of the board */

    @Column(name = "character")
    @NotNull
    char character;
    @Column(name = "score")
    int score;

    Letter(char character, int score) {
        this.score = score;
        this.character = character;
    }
}
