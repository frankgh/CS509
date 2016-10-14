package com.wordsweeper.server.controller;

import com.wordsweeper.server.api.WordSweeperServiceFactory;
import com.wordsweeper.server.model.Game;
import com.wordsweeper.server.model.ServerModel;
import com.wordsweeper.server.util.MappingUtil;
import com.wordsweeper.server.xml.BoardResponse;
import com.wordsweeper.server.xml.ObjectFactory;
import com.wordsweeper.server.xml.Request;
import com.wordsweeper.server.xml.Response;
import retrofit2.Call;
import server.ClientState;
import server.IProtocolHandler;
import server.Server;

import java.io.IOException;

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

        if (model.isClientInGame(client)) {
            // TODO: handle this case
            return null;
        }

        Game game = null;
        Call<Game> call;

        if (request.getJoinGameRequest().getPassword() != null) {
            call = WordSweeperServiceFactory.getService().joinGameWithPassword(
                    request.getJoinGameRequest().getGameId(),
                    request.getJoinGameRequest().getName(),
                    request.getJoinGameRequest().getPassword());
        } else {
            call = WordSweeperServiceFactory.getService().joinGame(
                    request.getJoinGameRequest().getGameId(),
                    request.getJoinGameRequest().getName());
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

        if (!model.joinGame(client, game)) {
            // TODO: handle this condition
            return null;
        }

        BoardResponse boardResponse = MappingUtil.mapGameToBoardResponse(game);

        Response response = new ObjectFactory().createResponse();
        response.setId(request.getId());
        response.setSuccess(true);
        response.setBoardResponse(boardResponse);

        // all other players on game (excepting this particular client) need to be told of this
        // same response. Note this is inefficient and should be replaced by more elegant functioning
        // hint: rely on your game to store player names...
        for (String id : model.idsByGameId(game.getUniqueId())) {
            if (!id.equals(client.id())) {
                Server.getState(id).sendMessage(response);
            }
        }

        // send this response back to the client which sent us the request.
        return response;
    }
}
