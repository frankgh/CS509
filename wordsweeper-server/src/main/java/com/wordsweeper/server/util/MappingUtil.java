package com.wordsweeper.server.util;

import com.wordsweeper.server.model.Cell;
import com.wordsweeper.server.model.Game;
import com.wordsweeper.server.model.Player;
import com.wordsweeper.server.xml.BoardResponse;
import com.wordsweeper.server.xml.ObjectFactory;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by francisco on 10/12/16.
 */
public class MappingUtil {

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
}
