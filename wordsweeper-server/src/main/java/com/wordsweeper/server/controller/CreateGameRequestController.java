package com.wordsweeper.server.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import com.wordsweeper.server.model.*;
import com.wordsweeper.server.xml.BoardResponse;
import com.wordsweeper.server.xml.Player;
import com.wordsweeper.server.xml.Request;
import com.wordsweeper.server.xml.Response;
import server.ClientState;
import server.IProtocolHandler;

/**
 * Controller on server to package up the current state of the model
 * as an updateResponse message and send it back to the client.
 */
public class CreateGameRequestController implements IProtocolHandler {

    ServerModel model;

    public CreateGameRequestController(ServerModel model) {
        this.model = model;
    }
    
    public static String generateGameId(int length){
    	String alphabet = new String("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"); //9
    	int n = alphabet.length(); //10

    	String result = new String(); 
    	Random r = new Random(); //11

    	for (int i=0; i<length; i++) //12
    	    result = result + alphabet.charAt(r.nextInt(n)); //13

    	return result;
    	}
    
    public static String generateAlphabet(int length){
    	String alphabet = new String("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
    	int n = alphabet.length(); 

    	String result = new String(); 
    	Random r = new Random(); 

    	for (int i=0; i<length; i++) 
    	    result = result + alphabet.charAt(r.nextInt(n)); 

    	return result;
    	}
    
    public static char[][] stringToArray(String alphabets){
		char[] alpha = alphabets.toCharArray();
		int n=(int) Math.sqrt(alpha.length);
		char[][] cipher = new char[n][n];
		int k=0;
		for(int i= 0;i<n;i++){
		       for(int j=0;j<n;j++){
		    	   cipher[i][j] = alpha[k];
		    	   k++;
		    	   }
		       }
		return cipher; 
	}
	
	public static char[][] ClientBoard(char[][] Board, String coords){
		ArrayList aList= new ArrayList(Arrays.asList(coords.split(",")));
		int Row=Integer.valueOf((String) aList.get(0));
		int Col=Integer.valueOf((String) aList.get(1));
		char[][] ClientB = new char[4][4];
		for(int i= 0;i<4;i++){
		       for(int j=0;j<4;j++){
		    	   ClientB[i][j]=Board[Row-1+i][Col-1+j];
		       }
		}
		
		return ClientB;
		
	}
	
	public static void printArray(char[][] Board){
		int length=Board.length;
		for(int i= 0;i<length;i++){
			System.out.println(Board[i]);
		}
	}
    
    public static String generateCoord(int num){
    	String result = new String(); 
    	Random randInt = new Random(); 
    	result=(randInt.nextInt(num)+1) + "" + "," + (randInt.nextInt(num)+1) + "";
//    	result=randInt.nextInt(num) + "" + "," + randInt.nextInt(num) + "";
    	return result;
    	}
    
    public static String arrayToString(char[][] array){
    	int length=array.length;
    	String result = new String(); 
    	for(int i= 0;i<length;i++){
		       for(int j=0;j<length;j++){
		    	   result=result+array[i][j];
		       }
    	}
		return result;
    }
    

    public Response process(ClientState client, Request request) {

    	int intialCooordRange=4;
    	int boardSize=49;
    	String initialCoords=generateCoord(intialCooordRange);
		String stringBoardAlp=new String(generateAlphabet(boardSize));
		char[][] WholeBoard=stringToArray(stringBoardAlp);
		char[][] ClinetBoard=ClientBoard(WholeBoard,initialCoords);
    	
    	model.joinGame(); 
        Player player = new Player();
        player.setName(request.getCreateGameRequest().getName());
        player.setScore(0);
        player.setPosition(initialCoords);
        player.setBoard(arrayToString(ClinetBoard));

        BoardResponse boardResponse = new BoardResponse();
        boardResponse.setGameId(generateGameId(7));
        boardResponse.setManagingUser(request.getCreateGameRequest().getName());
        boardResponse.setBonus(generateCoord(7));
        boardResponse.setContents(arrayToString(WholeBoard));
        boardResponse.getPlayer().add(player);

        // send this response back to the client which sent us the request.
        return new Response(boardResponse, request.getId());
    }
}
