package com.wordsweeper.server.controller;

import com.wordsweeper.server.api.WordSweeperServiceFactory;
import com.wordsweeper.server.api.model.Game;
import com.wordsweeper.server.model.ClientState;
import com.wordsweeper.server.model.ServerModel;
import com.wordsweeper.server.xml.Cell;
import com.wordsweeper.server.xml.FindWordResponse;
import com.wordsweeper.server.xml.Request;
import com.wordsweeper.server.xml.Response;
import org.apache.commons.lang3.StringUtils;
import retrofit2.Call;

import java.util.Iterator;

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
        StringBuilder sb = new StringBuilder(256);

        final Iterable<Cell> iterable = request.getFindWordRequest().getCell();
        final Iterator<Cell> iterator = iterable.iterator();

        final Cell first = iterator.next();
        sb.append(first.getPosition());

        while (iterator.hasNext()) {
            sb.append("|");
            final Cell cell = iterator.next();
            sb.append(cell.getPosition());
        }

        Call<Game> call = WordSweeperServiceFactory.getService()
                .findWord(gameId, playerName, request.getFindWordRequest().getWord(), sb.toString());

        return processInternal(client, request, call);
    }

    /* (non-Javadoc)
     * @see com.wordsweeper.server.controller.ControllerChain#execute(com.wordsweeper.server.model.ClientState, com.wordsweeper.server.xml.Request, com.wordsweeper.server.api.model.Game)
	 */
    protected Response execute(ClientState client, Request request, Game game) {
        return null;
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
}
