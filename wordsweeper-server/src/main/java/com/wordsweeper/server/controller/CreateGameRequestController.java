package com.wordsweeper.server.controller;

<<<<<<< HEAD
import java.util.Random;

import com.wordsweeper.server.model.*;
=======
import com.wordsweeper.server.api.WordSweeperServiceFactory;
import com.wordsweeper.server.api.model.Game;
import com.wordsweeper.server.model.ClientState;
import com.wordsweeper.server.model.ServerModel;
<<<<<<< HEAD
import com.wordsweeper.server.util.MappingUtil;
>>>>>>> refs/remotes/origin/master
import com.wordsweeper.server.xml.BoardResponse;
=======
>>>>>>> refs/remotes/origin/master
import com.wordsweeper.server.xml.Request;
import com.wordsweeper.server.xml.Response;
import retrofit2.Call;

/**
 * Controller on server in charge of relaying createGame requests
 * to the API, and packaging up the API response to send it to
 * the joining player.
 *
 * @author francisco
 */
public class CreateGameRequestController extends ControllerChain {

    /**
     * Instantiates a new Create game request controller.
     *
     * @param model the model
     */
    public CreateGameRequestController(ServerModel model) {
        this.model = model;
    }

<<<<<<< HEAD
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
    
    
=======
    /* (non-Javadoc)
     * @see com.wordsweeper.server.controller.IProtocolHandler#canProcess(com.wordsweeper.server.xml.Request)
	 */
    public boolean canProcess(Request request) {
        return request != null && request.getCreateGameRequest() != null;
    }

    /* (non-Javadoc)
     * @see com.wordsweeper.server.controller.IProtocolHandler#process(com.wordsweeper.server.model.ClientState, com.wordsweeper.server.xml.Request)
	 */
>>>>>>> refs/remotes/origin/master
    public Response process(ClientState client, Request request) {

        if (model.isClientInGame(client)) {
            // Rogue client wants to create a game without exiting his previous game
            return getUnsuccessfulResponse(request, "The player is already in a game");
        }

<<<<<<< HEAD
<<<<<<< HEAD
        Player player = new Player();
        player.setName(request.getCreateGameRequest().getName());
        player.setScore(0);
        player.setPosition("4,6");
        player.setBoard(generatePlayerBoard(16));
=======
        Game game = null;
=======
>>>>>>> refs/remotes/origin/master
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

        return processInternal(client, request, call);
    }

    /* (non-Javadoc)
     * @see com.wordsweeper.server.controller.ControllerChain#execute(com.wordsweeper.server.model.ClientState, com.wordsweeper.server.xml.Request, com.wordsweeper.server.api.model.Game)
	 */
    public Response execute(ClientState client, Request request, Game game) {
        client.setData(request.getCreateGameRequest().getName());
        if (!model.createGame(client, game.getUniqueId())) { /* associate a clientState to the game */
            client.setData(null);
            return getUnsuccessfulResponse(request, "Unable to create the game");
        }

<<<<<<< HEAD
        /* Map the game to a BoardResponse object */
        BoardResponse boardResponse = MappingUtil.mapGameToBoardResponse(game);

        /* Create the response object */
        Response response = getObjectFactory().createResponse();
        response.setId(request.getId());
        response.setSuccess(true);
        response.setBoardResponse(boardResponse);
>>>>>>> refs/remotes/origin/master

        // send this response back to the client which sent us the request.
        return response;
=======
        return null;
>>>>>>> refs/remotes/origin/master
    }

    /* (non-Javadoc)
     * @see com.wordsweeper.server.controller.ControllerChain#setOnSuccessResponse(com.wordsweeper.server.xml.Request, com.wordsweeper.server.xml.Response)
	 */
    protected boolean setOnSuccessResponse(Request request, Response response) {
        return false; // DO NOTHING
    }

    /* (non-Javadoc)
     * @see com.wordsweeper.server.controller.ControllerChain#setOnFailureResponse(com.wordsweeper.server.xml.Request, com.wordsweeper.server.xml.Response)
	 */
    protected void setOnFailureResponse(Request request, Response response) {
    } // DO NOTHING
}
