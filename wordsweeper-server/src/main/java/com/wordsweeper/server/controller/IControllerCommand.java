package com.wordsweeper.server.controller;

import com.wordsweeper.server.api.model.Game;
import com.wordsweeper.server.model.ClientState;
import com.wordsweeper.server.xml.Request;
import com.wordsweeper.server.xml.Response;

/**
 * If you want to to process specific functionality within a controller
 * implement {@link IControllerCommand}.
 *
 * @author francisco
 */
public interface IControllerCommand {

    /**
     * Execute the command in the Controller
     *
     * @param client  the client
     * @param request the request
     * @param game    the game
     * @return the response
     */
    Response execute(ClientState client, Request request, Game game);
}
