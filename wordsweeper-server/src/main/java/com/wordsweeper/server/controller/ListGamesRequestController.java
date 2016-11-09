package com.wordsweeper.server.controller;

import com.wordsweeper.server.model.ClientState;
import com.wordsweeper.server.model.ServerModel;
import com.wordsweeper.server.xml.Request;
import com.wordsweeper.server.xml.Response;

/**
 * Created by francisco on 11/8/16.
 */
public class ListGamesRequestController extends ControllerChain {

    /**
     * Instantiates a new List games request.
     *
     * @param model the model
     */
    public ListGamesRequestController(ServerModel model) {
        this.model = model;
    }

    /* (non-Javadoc)
     * @see com.wordsweeper.server.controller.IProtocolHandler#canProcess(com.wordsweeper.server.xml.Request)
	 */
    public boolean canProcess(Request request) {
        return request != null && request.getListGamesRequest() != null;
    }

    /* (non-Javadoc)
     * @see com.wordsweeper.server.controller.IProtocolHandler#process(com.wordsweeper.server.model.ClientState, com.wordsweeper.server.xml.Request)
	 */
    public Response process(ClientState state, Request request) {

        return null;
    }
}
