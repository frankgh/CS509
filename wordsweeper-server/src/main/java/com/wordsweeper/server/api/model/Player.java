package com.wordsweeper.server.api.model;

/**
 * This class introduced the WordSweeper Player
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
     * The score of the latest word
     */
    int latestScore;
    
     /**
     * 
     * Constructor
     */
    public Player(String name, Location offset, int score, int latestScore) {
        this.name = name;
        this.offset = offset;
        this.score = score;
        this.latestScore = latestScore;
    }
    
    /**
    * 
    * Empty constructor
    */
   public Player() {

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

    /**
     * Gets latest score.
     *
     * @return the latest score
     */
    public int getLatestScore() {
        return latestScore;
    }
}
