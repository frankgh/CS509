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
	
	public String getGameID(){
		return this.gameId;
	}
	
	public List<String> getServerThreadIDs(){
		return this.serverThreadIDs;
	}
	
	public void addPlayer(String serverThreadID){
		this.serverThreadIDs.add(serverThreadID);
	}
	
}
