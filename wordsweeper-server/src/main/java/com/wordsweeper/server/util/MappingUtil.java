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
     * @param source the source
     * @return the board response
     */
    public static BoardResponse mapGameToBoardResponse(Game source) {

        BoardResponse boardResponse = new ObjectFactory().createBoardResponse();

        boardResponse.setGameId(source.getUniqueId());
        boardResponse.setSize(source.getBoard().getRows() * source.getBoard().getColumns());
        boardResponse.setManagingUser(source.getManagingPlayerName());
        boardResponse.setBonus(source.getBoard().getBonusCellLocation().toString());

        List<String> word = new ArrayList<String>();

        for (Cell cell : source.getBoard().getCellList()) {
            word.add(cell.printCharacter());
        }

        boardResponse.setContents(StringUtils.join(word, ","));

        for (Player player : source.getPlayerList()) {
            com.wordsweeper.server.xml.Player mPlayer = new com.wordsweeper.server.xml.Player();
            mPlayer.setName(player.getName());
            mPlayer.setScore(player.getScore());
            mPlayer.setPosition(player.getOffset().toString());

            word = new ArrayList<String>();

            for (int i = player.getOffset().getColumn(); i < player.getOffset().getColumn() + 4; i++) {
                for (int j = player.getOffset().getRow(); j < player.getOffset().getRow() + 4; j++) {

                    int index = (i * source.getBoard().getColumns()) + j;

                    word.add(source.getBoard().getCellList().get(index).printCharacter());
                }
            }

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
        ObjectFactory factory =new ObjectFactory();
        ListGamesResponse listGamesResponse = factory.createListGamesResponse();

        if (gameList == null) {
            return listGamesResponse;
        }

        for (Game game : gameList){
            GameBrief gameBrief = factory.createGameBrief();
            gameBrief.setGameId(game.getUniqueId());
            gameBrief.setPlayers(game.getPlayerList().size());

            listGamesResponse.getGameBrief().add(gameBrief);
        }

        return listGamesResponse;
    }
}
