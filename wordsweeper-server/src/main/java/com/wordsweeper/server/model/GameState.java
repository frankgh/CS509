package com.wordsweeper.server.model;

import java.util.ArrayList;
import java.util.List;

public class GameState {
	String gameId;
	List<String> serverThreadIDs;  
	
	public GameState(String gameId, String serverThreadID) {
		this.gameId = gameId;
		this.serverThreadIDs = new ArrayList<String>();
		serverThreadIDs.add(serverThreadID);
			}
	
		
}
