package com.wordsweeper.server.util;

import com.wordsweeper.core.xml.BoardResponse;
import com.wordsweeper.core.xml.ListGamesResponse;
import com.wordsweeper.server.api.model.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;


public class MappingUtilTest {

    @Test
    public void constructor() throws Exception {
        MappingUtil testUt = new MappingUtil();

        Game game = new Game();
        ArrayList<Cell> cellList = new ArrayList<Cell>();
        Cell cell = new Cell();
        Letter letter = new Letter();
        letter.setCharacter("a");
        cell.setLetter(letter);
        for (int i = 0; i < 50; i++)
            cellList.add(cell);
        Location loc = new Location(1, 1);
        Board board = new Board(4, 4, (List) cellList, loc);
        game.setBoard(board);
        game.setUniqueId("testID");
        game.setPassword("123");
        game.setManagingPlayerName("testPlayer");
        ArrayList<Player> playerList = new ArrayList<Player>();
        Player testPlayer = new Player("testPlayer", loc, 50, 10);
        playerList.add(testPlayer);
        game.setPlayerList(playerList);

        assertTrue(testUt.mapGameToBoardResponse(game, true) instanceof BoardResponse);
        assertTrue(testUt.mapGameToBoardResponse(game, false) instanceof BoardResponse);

        ArrayList<Game> listGames = new ArrayList<Game>();
        listGames.add(game);
        assertTrue(testUt.mapGameListToListGamesResponse(null) instanceof ListGamesResponse);
        assertTrue(testUt.mapGameListToListGamesResponse(listGames) instanceof ListGamesResponse);

    }
}
