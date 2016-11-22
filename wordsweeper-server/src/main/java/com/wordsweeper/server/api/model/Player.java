package com.wordsweeper.server.api.model;

/**
 * Created by francisco on 10/12/16.
 *
 * @author francisco
 */
public class Player {

    /**
     * The Name.
     */
    String name;

    /**
     * The Offset.
     */
    Location offset;

    /**
     * The Score.
     */
    int score;

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
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
