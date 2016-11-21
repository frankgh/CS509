package com.wordsweeper.server.controller;

import com.wordsweeper.server.api.WordSweeperServiceFactory;
import com.wordsweeper.server.api.model.Game;
import com.wordsweeper.server.model.ClientState;
import com.wordsweeper.server.model.ServerModel;
import com.wordsweeper.server.util.MappingUtil;
import com.wordsweeper.server.xml.*;
import org.apache.commons.lang3.StringUtils;
import retrofit2.Call;

import java.util.Iterator;
import java.util.List;

/**
 * Created by francisco on 11/10/16.
 */
public class FindWordRequestController extends ControllerChain {

    /**
     * Instantiates a new Find word request controller.
     *
     * @param model the model
     */
    public FindWordRequestController(ServerModel model) {
        this.model = model;
    }

    /* (non-Javadoc)
     * @see com.wordsweeper.server.controller.IProtocolHandler#canProcess(com.wordsweeper.server.xml.Request)
	 */
    public boolean canProcess(Request request) {
        return request != null && request.getFindWordRequest() != null;
    }

    /* (non-Javadoc)
     * @see com.wordsweeper.server.controller.IProtocolHandler#process(com.wordsweeper.server.model.ClientState, com.wordsweeper.server.xml.Request)
	 */
    public Response process(ClientState client, Request request) {

        /* If the client is not in a game return an unsuccessful response */
        if (!model.isClientInGame(client)) {
            return getUnsuccessfulResponse(request, "The player has not joined a game"); /* Return empty response */
        }

        if (StringUtils.isBlank(request.getFindWordRequest().getWord())) {
            return getUnsuccessfulResponse(request, "Invalid word"); /* Return empty response */
        }

        if (request.getFindWordRequest().getCell() == null ||
                request.getFindWordRequest().getCell().isEmpty()) {
            return getUnsuccessfulResponse(request, "Invalid word"); /* Return empty response */
        }

        String gameId = model.getGameId(client);
        String playerName = (String) client.getData();
        String cellPositions = getCellPositions(request.getFindWordRequest().getCell());

        Call<Game> call = WordSweeperServiceFactory.getService()
                .findWord(gameId, playerName, request.getFindWordRequest().getWord(), cellPositions);

        return processInternal(client, request, call);
    }

    /* (non-Javadoc)
     * @see com.wordsweeper.server.controller.ControllerChain#execute(com.wordsweeper.server.model.ClientState, com.wordsweeper.server.xml.Request, com.wordsweeper.server.api.model.Game)
	 */
    protected Response execute(ClientState client, Request request, Game game) {
        /* Map the game to a BoardResponse object */
        BoardResponse boardResponse = MappingUtil.mapGameToBoardResponse(game, false);

        /* Create the response object */
        Response response = getBasicResponse(request, boardResponse);

        /* Broadcast the response to all the players in the game */
        broadcastResponse(response, StringUtils.EMPTY, game);

        String playerName = (String) client.getData();
        FindWordResponse findWordResponse = getObjectFactory().createFindWordResponse();
        findWordResponse.setGameId(game.getUniqueId());
        findWordResponse.setName(playerName);
        // TODO: find a way to get the score
        findWordResponse.setScore(0);

        response.setFindWordResponse(findWordResponse);
        response.setBoardResponse(null);

        // send this response back to the client which sent us the request.
        return response;
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
        FindWordResponse findWordResponse = getObjectFactory().createFindWordResponse();
        findWordResponse.setGameId(request.getFindWordRequest().getGameId());
        findWordResponse.setName(request.getFindWordRequest().getName());
        findWordResponse.setScore(0);

        response.setFindWordResponse(findWordResponse);
    }

    /**
     * Convert the list of cells to a string representing the list
     *
     * @param cellList the list of cells
     * @return the string representing the cell positions
     */
    private String getCellPositions(List<Cell> cellList) {
        StringBuilder sb = new StringBuilder(256);
        final Iterator<Cell> iterator = cellList.iterator();
        final Cell first = iterator.next();

        sb.append(first.getPosition());

        while (iterator.hasNext()) {
            sb.append("|");
            final Cell cell = iterator.next();
            sb.append(cell.getPosition());
        }

        return sb.toString();
    }
}
