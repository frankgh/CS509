package com.wordsweeper.server.controller;

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
    	String alphabet = 
    	        new String("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz");
    	int n = alphabet.length(); 

    	String result = new String(); 
    	Random r = new Random(); 

    	for (int i=0; i<length; i++) 
    	    result = result + alphabet.charAt(r.nextInt(n)); 

    	return result;
    	}
    
    public static String generatePlayerBoard(int length){
    	String alphabet = 
    	        new String("ABCDEFGHIJKLMNOPQRSTUVWXYZ"); 
    	int n = alphabet.length(); 

    	String result = new String(); 
    	Random r = new Random(); 

    	for (int i=0; i<length; i++) 
    	    result = result + alphabet.charAt(r.nextInt(n)); 

    	return result;
    	}
    
    
    public Response process(ClientState client, Request request) {

        model.joinGame();  // HACK.

        Player player = new Player();
        player.setName(request.getCreateGameRequest().getName());
        player.setScore(0);
        player.setPosition("4,6");
        player.setBoard(generatePlayerBoard(16));

        BoardResponse boardResponse = new BoardResponse();
        boardResponse.setGameId(generateGameId(7));
        boardResponse.setManagingUser(request.getCreateGameRequest().getName());
        boardResponse.setBonus("4,3");
        boardResponse.setContents("ABCGBCJDH...HDJHJD");
        boardResponse.getPlayer().add(player);

        // send this response back to the client which sent us the request.
        return new Response(boardResponse, request.getId());
    }
}
