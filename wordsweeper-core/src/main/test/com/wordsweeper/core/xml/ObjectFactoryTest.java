package com.wordsweeper.core.xml;

import org.junit.Test;

import javax.xml.bind.JAXBElement;

import static org.junit.Assert.assertTrue;


public class ObjectFactoryTest {

    @Test
    public void constructor() throws Exception {
        ObjectFactory testFac = new ObjectFactory();

        Object testParam;

        testParam = testFac.createCell();
        assertTrue(testParam instanceof Cell);

        testParam = testFac.createBoardResponse();
        assertTrue(testParam instanceof BoardResponse);

        testParam = testFac.createConnectRequest(new String("test"));
        assertTrue(testParam instanceof JAXBElement);

        testParam = testFac.createConnectResponse();
        assertTrue(testParam instanceof ConnectResponse);

        testParam = testFac.createListGamesRequest(new String("test"));
        assertTrue(testParam instanceof JAXBElement);

        testParam = testFac.createCreateGameRequest();
        assertTrue(testParam instanceof CreateGameRequest);

        testParam = testFac.createExitGameRequest();
        assertTrue(testParam instanceof ExitGameRequest);

        testParam = testFac.createExitGameResponse();
        assertTrue(testParam instanceof ExitGameResponse);

        testParam = testFac.createFindWordRequest();
        assertTrue(testParam instanceof FindWordRequest);

        testParam = testFac.createFindWordResponse();
        assertTrue(testParam instanceof FindWordResponse);

        testParam = testFac.createGameBrief();
        assertTrue(testParam instanceof GameBrief);

        testParam = testFac.createJoinGameRequest();
        assertTrue(testParam instanceof JoinGameRequest);

        testParam = testFac.createJoinGameResponse();
        assertTrue(testParam instanceof JoinGameResponse);

        testParam = testFac.createListGamesResponse();
        assertTrue(testParam instanceof ListGamesResponse);

        testParam = testFac.createListGamesRequest("test");
        assertTrue(testParam instanceof JAXBElement);

        testParam = testFac.createLockGameRequest();
        assertTrue(testParam instanceof LockGameRequest);

        testParam = testFac.createLockGameResponse();
        assertTrue(testParam instanceof LockGameResponse);

        testParam = testFac.createMessage();
        assertTrue(testParam instanceof Message);

        testParam = testFac.createPlayer();
        assertTrue(testParam instanceof Player);

        testParam = testFac.createRepositionBoardRequest();
        assertTrue(testParam instanceof RepositionBoardRequest);

        testParam = testFac.createRequest();
        assertTrue(testParam instanceof Request);

        testParam = testFac.createResetGameRequest();
        assertTrue(testParam instanceof ResetGameRequest);

        testParam = testFac.createResetGameResponse();
        assertTrue(testParam instanceof ResetGameResponse);

        testParam = testFac.createResponse();
        assertTrue(testParam instanceof Response);

        testParam = testFac.createShowGameStateRequest();
        assertTrue(testParam instanceof ShowGameStateRequest);


    }
}
