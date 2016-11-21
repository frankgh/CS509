package com.wordsweeper.server.util;

import com.wordsweeper.server.api.WordSweeperServiceFactory;
import com.wordsweeper.server.api.model.APIError;
import com.wordsweeper.server.api.model.Cell;
import com.wordsweeper.server.api.model.Game;
import com.wordsweeper.server.api.model.Player;
import com.wordsweeper.server.xml.BoardResponse;
import com.wordsweeper.server.xml.GameBrief;
import com.wordsweeper.server.xml.ListGamesResponse;
import com.wordsweeper.server.xml.ObjectFactory;
import okhttp3.ResponseBody;
import org.apache.commons.lang3.StringUtils;
import retrofit2.Converter;
import retrofit2.Response;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by francisco on 10/12/16.
 *
 * @author francisco
 */
public class MappingUtil {

    /**
     * Map game to board response board response.
     *
     * @param source               the source
     * @param includeBoardContents whether to include the board contents
     * @return the board response
     */
    public static BoardResponse mapGameToBoardResponse(Game source, boolean includeBoardContents) {

        BoardResponse boardResponse = new ObjectFactory().createBoardResponse();

        boardResponse.setGameId(source.getUniqueId());
        boardResponse.setSize(source.getBoard().getRows());
        boardResponse.setManagingUser(source.getManagingPlayerName());
        boardResponse.setBonus(source.getBoard().getBonusCellLocation().toString());

        if (includeBoardContents) {
            List<String> wordContents = new ArrayList<String>();

            for (Cell cell : source.getBoard().getCellList()) {
                wordContents.add(cell.printCharacter());
            }

            boardResponse.setContents(StringUtils.join(wordContents, ","));
        }

        for (Player player : source.getPlayerList()) {
            com.wordsweeper.server.xml.Player mPlayer = new com.wordsweeper.server.xml.Player();
            List<String> word = new ArrayList<String>();

            for (int j = player.getOffset().getRow(); j < player.getOffset().getRow() + 4; j++) {
                for (int i = player.getOffset().getColumn(); i < player.getOffset().getColumn() + 4; i++) {
                    int index = (j * source.getBoard().getColumns()) + i; /* index for the cell */
                    word.add(source.getBoard().getCellList().get(index).printCharacter());
                }
            }

            mPlayer.setName(player.getName());
            mPlayer.setScore(player.getScore());
            mPlayer.setPosition(player.getOffset().toString());
            mPlayer.setBoard(StringUtils.join(word, ","));
            boardResponse.getPlayer().add(mPlayer);
        }

        return boardResponse;
    }

    /**
     * Parse the API Error.
     *
     * @param response the response
     * @return the api error
     */
    public static APIError parseError(Response<?> response) {
        Converter<ResponseBody, APIError> converter =
                WordSweeperServiceFactory.retrofit()
                        .responseBodyConverter(APIError.class, new Annotation[0]);

        try {
            return converter.convert(response.errorBody());
        } catch (IOException e) {
            return new APIError();
        }
    }

    /**
     * Convert a list of games into a list game response objects
     *
     * @param gameList
     * @return a list game response objects
     */
    public static ListGamesResponse mapGameListToListGamesResponse(List<Game> gameList) {
        ObjectFactory factory = new ObjectFactory();
        ListGamesResponse listGamesResponse = factory.createListGamesResponse();

        if (gameList == null) {
            return listGamesResponse;
        }

        for (Game game : gameList) {
            GameBrief gameBrief = factory.createGameBrief();
            gameBrief.setGameId(game.getUniqueId());
            gameBrief.setPlayers(game.getPlayerList().size());

            listGamesResponse.getGameBrief().add(gameBrief);
        }

        return listGamesResponse;
    }
}
