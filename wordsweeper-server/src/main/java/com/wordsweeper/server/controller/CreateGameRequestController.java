package com.wordsweeper.server.controller;

<<<<<<< HEAD
import java.util.Random;

import com.wordsweeper.server.model.*;
=======
import com.wordsweeper.server.api.WordSweeperServiceFactory;
import com.wordsweeper.server.model.Game;
import com.wordsweeper.server.model.ServerModel;
import com.wordsweeper.server.util.MappingUtil;
>>>>>>> refs/remotes/origin/master
import com.wordsweeper.server.xml.BoardResponse;
import com.wordsweeper.server.xml.ObjectFactory;
import com.wordsweeper.server.xml.Request;
import com.wordsweeper.server.xml.Response;
import retrofit2.Call;
import server.ClientState;
import server.IProtocolHandler;

import java.io.IOException;

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

        if (model.isClientInGame(client)) {
            // Rogue client wants to create a game without exiting his previous game
            Response response = new ObjectFactory().createResponse();
            response.setId(request.getId());
            response.setSuccess(false); /* success to false */
            return response;
        }

<<<<<<< HEAD
        Player player = new Player();
        player.setName(request.getCreateGameRequest().getName());
        player.setScore(0);
        player.setPosition("4,6");
        player.setBoard(generatePlayerBoard(16));
=======
        Game game = null;
        Call<Game> call;
>>>>>>> refs/remotes/origin/master

<<<<<<< HEAD
        BoardResponse boardResponse = new BoardResponse();
        boardResponse.setGameId(generateGameId(7));
        boardResponse.setManagingUser(request.getCreateGameRequest().getName());
        boardResponse.setBonus("4,3");
        boardResponse.setContents("ABCGBCJDH...HDJHJD");
        boardResponse.getPlayer().add(player);
=======
        if (request.getCreateGameRequest().getPassword() != null) {
            call = WordSweeperServiceFactory.getService().createGameWithPassword(
                    request.getCreateGameRequest().getName(),
                    request.getCreateGameRequest().getPassword());
        } else {
            call = WordSweeperServiceFactory.getService().createGame(request.getCreateGameRequest().getName());
        }

        try {
            game = call.execute().body();
        } catch (IOException e) {
            System.err.println("Error connecting to the webservice");
        }

        if (game == null) {
            // TODO: handle this request
            return null;
        }


        if (!model.createGame(client, game)) { /* associate a clientState to the game */
            return null;
        }

        /* Map the game to a BoardResponse object */
        BoardResponse boardResponse = MappingUtil.mapGameToBoardResponse(game);

        /* Create the response object */
        Response response = new ObjectFactory().createResponse();
        response.setId(request.getId());
        response.setSuccess(true);
        response.setBoardResponse(boardResponse);
>>>>>>> refs/remotes/origin/master

        // send this response back to the client which sent us the request.
        return response;
    }
}
