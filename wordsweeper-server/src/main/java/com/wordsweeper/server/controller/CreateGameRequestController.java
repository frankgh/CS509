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
        // randGenData[1] - random column of new player
        // randGenData[2] - random row of new player
        // randGenData[3] - random gameID of new game
        // randGenData[4] - random column of x10 bonus
        // randGenData[5] - random row of x10 bonus
         // randGenData[6] - random letters of the new board
        Player player = new Player();
        player.setName(request.getCreateGameRequest().getName());
        player.setScore(0);	//initialize score

        player.setPosition(randGenData.get(0) +","+ randGenData.get(1));
        System.out.println("/" + randGenData.get(1)+","+randGenData.get(1) + "\\"); // FOR TESTING
        player.setBoard("AFERKSOEROIERPOR"); // what is that??

        BoardResponse boardResponse = new BoardResponse();
        boardResponse.setGameId("hg12jhd");
        boardResponse.setManagingUser(request.getCreateGameRequest().getName());
        boardResponse.setBonus(randGenData.get(2) +","+ randGenData.get(3));
        boardResponse.setContents("ABCGBCJDH...HDJHJD");
        boardResponse.getPlayer().add(player);

        // send this response back to the client which sent us the request.
        return new Response(boardResponse, request.getId());
    }
}
