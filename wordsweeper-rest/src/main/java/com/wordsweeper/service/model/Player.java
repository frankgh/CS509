package com.wordsweeper.service.model;

import javax.persistence.*;

/**
 * Model class for Player
 *
 * Created by francisco on 9/13/16.
 * @author francisco
 */
@Entity
@Table(name = "player")
public class Player {

    /**
     * The Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    /**
     * The Name.
     */
    @Column(name = "name")
    String name;

    /**
     * The Offset.
     */
    @Embedded
    Location offset;

    /**
     * The Score.
     */
    @Column(name = "score")
    int score;

    /**
     * Instantiates a new Player.
     */
    public Player() {
    }

    /**
     * Instantiates a new Player.
     *
     * @param playerName the player name
     */
    public Player(String playerName) {
        this.name = playerName;
        this.score = 0;
        this.offset = new Location(0, 0);
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets score.
     *
     * @param score the score
     */
    protected void setScore(int score) {
        this.score = score;
    }

    /**
     * Sets offset.
     *
     * @param location the location
     */
    public void setOffset(Location location) {
        this.offset = location;
    }

    /**
     * Gets offset.
     *
     * @return the offset
     */
    public Location getOffset() {
        return offset;
    }

    /**
     * Gets score.
     *
     * @return the score
     */
    public int getScore() {
        return score;
    }
}
