package com.wordsweeper.server.controller;

import com.wordsweeper.server.model.*;
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

        model.joinGame();  // HACK.

        Player player = new Player();
        player.setName(request.getCreateGameRequest().getName());
        player.setScore(392489038);
        player.setPosition("4,6");
        player.setBoard("AFERKSOEROIERPOR");

        BoardResponse boardResponse = new BoardResponse();
        boardResponse.setGameId("hg12jhd");
        boardResponse.setManagingUser(request.getCreateGameRequest().getName());
        boardResponse.setBonus("4,3");
        boardResponse.setContents("ABCGBCJDH...HDJHJD");
        boardResponse.getPlayer().add(player);

        // send this response back to the client which sent us the request.
        return new Response(boardResponse, request.getId());
    }
}
