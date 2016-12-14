package com.wordsweeper.server.controller;

import com.wordsweeper.core.xml.FindWordRequest;
import com.wordsweeper.core.xml.Request;
import com.wordsweeper.core.xml.Response;
import com.wordsweeper.server.ServerThread;
import com.wordsweeper.server.api.model.*;
import com.wordsweeper.server.model.ServerModel;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class FindWordRequestControllerTest {

    @Test
    public void constructor() throws Exception {
        ServerModel mockServer = Mockito.mock(ServerModel.class);
        FindWordRequestController findCon = new FindWordRequestController(mockServer);

        ServerThread client = Mockito.mock(ServerThread.class);
        Mockito.when(mockServer.isClientInGame(client)).thenReturn(false);
        Request testReq = Mockito.mock(Request.class);

        assertFalse(findCon.canProcess(null));

        Mockito.when(testReq.getFindWordRequest()).thenReturn(null);
        assertFalse(findCon.canProcess(testReq));

        Mockito.when(testReq.getFindWordRequest()).thenReturn(new FindWordRequest());
        assertTrue(findCon.canProcess(testReq));

        findCon.setOnSuccessResponse(testReq, new Response());
        findCon.setOnFailureResponse(testReq, new Response());
        Mockito.when(testReq.getFindWordRequest()).thenReturn(null);


        Mockito.when(mockServer.isClientInGame(client)).thenReturn(false);
//		assertTrue(findCon.process(client, testReq) instanceof Response);

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
        Mockito.when(mockServer.exitGame(client)).thenReturn(false);
        assertTrue(findCon.execute(client, testReq, game) instanceof Response);

        com.wordsweeper.core.xml.Cell testCell = new com.wordsweeper.core.xml.Cell();
        testCell.setPosition("1,1");
        ArrayList<com.wordsweeper.core.xml.Cell> cells = new ArrayList<com.wordsweeper.core.xml.Cell>();
        cells.add(testCell);
        com.wordsweeper.core.xml.Cell testCell2 = new com.wordsweeper.core.xml.Cell();
        testCell2.setPosition("2,2");
        cells.add(testCell2);
        assertEquals(findCon.getCellPositions(cells), "1,1|2,2");

    }
}
