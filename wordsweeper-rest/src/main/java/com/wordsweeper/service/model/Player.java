package com.wordsweeper.service.model;

import javax.persistence.*;

/**
 * Created by francisco on 9/13/16.
 */
@Entity
@Table(name = "player")
public class Player {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Embedded
    private Location offset;

    @Column(name = "score")
    private int score;

    public Player() {
    }

    public Player(String playerName) {
        this.name = playerName;
        this.score = 0;
        this.offset = new Location(0, 0);
    }

    public String getName() {
        return name;
    }

    protected void setScore(int score) {
        this.score = score;
    }

    public void setOffset(Location location) {
        this.offset = location;
    }
}
