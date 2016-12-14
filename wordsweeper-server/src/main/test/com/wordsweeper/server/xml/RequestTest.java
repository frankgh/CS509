package com.wordsweeper.server.xml;

import com.wordsweeper.server.ServerThread;
import com.wordsweeper.server.api.model.Board;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;



public class RequestTest {

	@Test
	public void constructor() throws Exception {
		Request req = new Request();
		
		assertNull(req.getConnectRequest());
		Object conReq = new Object();
		req.setConnectRequest(conReq);
		assertEquals(req.getConnectRequest(), conReq);
		
		CreateGameRequest createReq = new CreateGameRequest();
		req.setCreateGameRequest(createReq);
		assertEquals(req.getCreateGameRequest(), createReq);
		
		JoinGameRequest joinReq = new JoinGameRequest();
		req.setJoinGameRequest(joinReq);
		assertEquals(req.getJoinGameRequest(), joinReq);
		
		ExitGameRequest exitReq = new ExitGameRequest();
		req.setExitGameRequest(exitReq);
		assertEquals(req.getExitGameRequest(), exitReq);
		
		LockGameRequest lockReq = new LockGameRequest();
		req.setLockGameRequest(lockReq);
		assertEquals(req.getLockGameRequest(), lockReq);
		
		ResetGameRequest resetReq = new ResetGameRequest();
		req.setResetGameRequest(resetReq);
		assertEquals(req.getResetGameRequest(), resetReq);
		
		FindWordRequest findReq = new FindWordRequest();
		req.setFindWordRequest(findReq);
		assertEquals(req.getFindWordRequest(), findReq);
		
		RepositionBoardRequest reposReq = new RepositionBoardRequest();
		req.setRepositionBoardRequest(reposReq);
		assertEquals(req.getRepositionBoardRequest(), reposReq);
		
		Object getReq = new Object();
		req.setListGamesRequest(getReq);
		assertEquals(req.getListGamesRequest(), getReq);
		
		ShowGameStateRequest showReq = new ShowGameStateRequest();
		req.setShowGameStateRequest(showReq);
		assertEquals(req.getShowGameStateRequest(), showReq);
		
		req.setId("testID");
		assertEquals(req.getId(), "testID");
		
		assertEquals(req.getVersion(), "1.0");
		req.setVersion("1.1");
		assertEquals(req.getVersion(), "1.1");
	}
}
