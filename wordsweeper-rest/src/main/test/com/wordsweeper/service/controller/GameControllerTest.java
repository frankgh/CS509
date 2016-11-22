package com.wordsweeper.service.controller;

import com.wordsweeper.service.model.Game;
import com.wordsweeper.service.model.Player;
import com.wordsweeper.service.model.RequestError;
import com.wordsweeper.service.repository.GameDao;
import com.wordsweeper.service.repository.GameDaoImpl;
import org.apache.commons.lang3.StringUtils;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by george on 11/11/16.
 */
public class GameControllerTest {
    @Test
    public void constructor() throws Exception {
    	// create a game that is locked
    	// create a game with password
    	// create a game with no password
    	GameController gameCont = new GameController();
    	Response result;
    	result = gameCont.create("test", "123");
    	Object obj = result.getEntity();
    	Game g = new Game(new Player());
    	if(obj instanceof Game)
    		g = (Game) result.getEntity();
    	
    	assertEquals(g.getManagingPlayerName(), "test");	
    	
    }

}
