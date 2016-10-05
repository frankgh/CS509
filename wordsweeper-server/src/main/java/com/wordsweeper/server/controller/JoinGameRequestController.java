package com.wordsweeper.server.controller;

import com.wordsweeper.server.model.*;
import com.wordsweeper.server.xml.BoardResponse;
import com.wordsweeper.server.xml.Player;
import com.wordsweeper.server.xml.Request;
import com.wordsweeper.server.xml.Response;
import server.ClientState;
import server.IProtocolHandler;
import server.Server;

/**
 * Controller on server to package up the current state of the model
 * as an updateResponse message and send it back to the client.
 */
public class JoinGameRequestController implements IProtocolHandler {

    ServerModel model;

    public JoinGameRequestController(ServerModel model) {
        this.model = model;
    }

    public synchronized Response process(ClientState client, Request request) {

        model.joinGame();

        BoardResponse boardResponse = new BoardResponse();
        boardResponse.setGameId("hg12jhd");
        boardResponse.setManagingUser("player2");
        boardResponse.setBonus("4,3");
        boardResponse.setContents("ABCGBCJDH...HDJHJD");

        for (int i = 0; i < model.getNumPlayers(); i++) {
            Player player = new Player();
            player.setName("player" + i);
            player.setScore(38974);
            player.setPosition("2,2");
            player.setBoard("ECDRFTGOUIGERPRT");
            boardResponse.getPlayer().add(player);
        }

        Response response = new Response(boardResponse, request.getId());

        // all other players on game (excepting this particular client) need to be told of this
        // same response. Note this is inefficient and should be replaced by more elegant functioning
        // hint: rely on your game to store player names...
        for (String id : Server.ids()) {
            if (!id.equals(client.id())) {
                Server.getState(id).sendMessage(response);
            }
        }

        // send this response back to the client which sent us the request.
        return response;
    }
}
