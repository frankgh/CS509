package com.wordsweeper.server.xml;

import com.wordsweeper.server.ServerThread;
import com.wordsweeper.server.api.model.Board;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;



public class ResponseTest {

	@Test
	public void constructor() throws Exception {
		Response res = new Response();
		
		assertNull(res.getConnectResponse());
		ConnectResponse conRes = new ConnectResponse();
		res.setConnectResponse(conRes);
		assertEquals(res.getConnectResponse(), conRes);
		
		BoardResponse boardRes = new BoardResponse();
		res.setBoardResponse(boardRes);
		assertEquals(res.getBoardResponse(), boardRes);
		
		LockGameResponse lockRes = new LockGameResponse();
		res.setLockGameResponse(lockRes);
		assertEquals(res.getLockGameResponse(), lockRes);
		
		ResetGameResponse resetRes = new ResetGameResponse();
		res.setResetGameResponse(resetRes);
		assertEquals(res.getResetGameResponse(), resetRes);
		
		JoinGameResponse joinRes = new JoinGameResponse();
		res.setJoinGameResponse(joinRes);
		assertEquals(res.getJoinGameResponse(), joinRes);
		
		FindWordResponse findRes = new FindWordResponse();
		res.setFindWordResponse(findRes);
		assertEquals(res.getFindWordResponse(), findRes);
		
		ExitGameResponse exitReq = new ExitGameResponse();
		res.setExitGameResponse(exitReq);
		assertEquals(res.getExitGameResponse(), exitReq);
		
		ListGamesResponse getReq = new ListGamesResponse();
		res.setListGamesResponse(getReq);
		assertEquals(res.getListGamesResponse(), getReq);
		
		res.setSuccess(false);
		assertFalse(res.isSuccess());
		assertFalse(res.getSuccess());
		
		res.setReason("test");
		assertEquals(res.getReason(), "test");
		
		res.setId("testID");
		assertEquals(res.getId(), "testID");
		
		assertEquals(res.getVersion(), "1.0");
		res.setVersion("1.1");
		assertEquals(res.getVersion(), "1.1");
	}
}
