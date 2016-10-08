package com.wordsweeper.server.controller;

import java.util.ArrayList;

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

    public Response process(ClientState client, Request request) {

        ArrayList<String> randGenData = model.createGame(request.getCreateGameRequest().getName(), request.getId());  // HACK.
        // randGenData[0] - random column of new player
        // randGenData[1] - random row of new player
        // randGenData[2] - random gameID of new game
        // randGenData[3] - random column of x10 bonus
        // randGenData[4] - random row of x10 bonus
         // randGenData[5] - random letters of the new board
        Player player = new Player();
        player.setName(request.getCreateGameRequest().getName());
        player.setScore(0);	//initialize score
        player.setPosition(randGenData.get(0) + "," + randGenData.get(1));
        System.out.println("/" + randGenData.get(0) + "," + randGenData.get(1) + "\\"); // FOR TESTING
        
        // FROM big board , get Small board
        // smaller board is 4x4 = 16 letters
        // if init posi
        String smallBoard = "";
        int readIndex = Integer.valueOf(randGenData.get(0)) + 7 * Integer.valueOf(randGenData.get(1));
        for(int i = 0; i < 16; i++){
        	// every four steps jump a row (i.e. jump 4 letters)
        	smallBoard += randGenData.get(5).charAt(readIndex);
        	System.out.println("WE ARE AT: " + i);
        	if((i+1) % 4 == 0) 
        		readIndex += 3;
        	else
        		readIndex ++;
        }

        player.setBoard(smallBoard);

        BoardResponse boardResponse = new BoardResponse();
        boardResponse.setGameId(randGenData.get(2));
        boardResponse.setManagingUser(request.getCreateGameRequest().getName());
        boardResponse.setBonus(randGenData.get(3) + "," + randGenData.get(4));
        boardResponse.setContents(randGenData.get(5));
        boardResponse.getPlayer().add(player);

        // send this response back to the client which sent us the request.
        return new Response(boardResponse, request.getId());
    }
}
