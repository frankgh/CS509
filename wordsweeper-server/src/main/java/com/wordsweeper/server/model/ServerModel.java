package com.wordsweeper.server.model;

import java.util.ArrayList;
import java.util.Random;

/**
 * HACK: Replace with actual functionality!
 */

public class ServerModel {
	
    int numPlayers = 0;
    long seed;
    Random generator;
    ArrayList<GameState> games;
    public void joinGame() {
        numPlayers++;
    }
    
    public ArrayList<String> createGame(String name, String id) {
    	ArrayList<String> outputRandData = new ArrayList<String>();
    	int boardSize = 7;
    	
    	 // randomly generate the column of the player
        String randColP = String.valueOf(generator.nextInt(boardSize-3));
        outputRandData.add(randColP);
        
        // randomly generate the row of the player
        String randRowP = String.valueOf(generator.nextInt(boardSize-3));
        outputRandData.add(randRowP);
        
        // randomly generate the game Id
        String alphanumeric = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvxyz";
        String gameID = "";
        int IDSize = 7;
        for(int i = 0; i < IDSize; i ++){
        	gameID += alphanumeric.charAt(generator.nextInt(alphanumeric.length()));
        }
        outputRandData.add(gameID);
        System.out.println("The gameID is: " + gameID); // FOR TESTING
        
        // randomly generate the column of the x10 bonus
        String randColB = String.valueOf(generator.nextInt(boardSize));
        outputRandData.add(randColB);
        
        // randomly generate the row of the x10 bonus
        String randRowB = String.valueOf(generator.nextInt(boardSize));
        outputRandData.add(randRowB);
        
        // randomly generate the letters of the board
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String boardContent = "";
        for(int i = 0; i < boardSize*boardSize; i ++){
        	boardContent += alphabet.charAt(generator.nextInt(alphabet.length()));
        }
        outputRandData.add(boardContent);
        System.out.println("The board is: " + boardContent); // FOR TESTING
        
        // add new game to list of games
        GameState newGame = new GameState(gameID, "");
        games.add(newGame);
        
        return outputRandData;
    }
    
     public int getNumPlayers() {
        return numPlayers;
    }
    
    public ServerModel () {
    	// Generate a random seed
        this.seed = (long) Math.random() * Long.MAX_VALUE;
        // Initialize random generator with seed
        this.generator = new Random(seed);
        // Initialize array list of games
        this.games = new ArrayList<GameState>();
    }
}
