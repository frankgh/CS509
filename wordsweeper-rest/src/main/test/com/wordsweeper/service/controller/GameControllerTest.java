package com.wordsweeper.service.controller;

import com.wordsweeper.service.model.Game;
import com.wordsweeper.service.model.Location;
import com.wordsweeper.service.model.Player;
import com.wordsweeper.service.model.RequestError;
import com.wordsweeper.service.repository.GameDao;
import com.wordsweeper.service.repository.GameDaoImpl;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.util.List;

/**
 * Created by george on 11/11/16.
 */
public class GameControllerTest {
    @Test
    public void constructor() throws Exception {
    	GameController gameCont = new GameController();
    	GameDao gameDao = new GameDaoImpl();
    	List<Game> games = gameDao.findAll();
    	assertEquals(games.size(), 0);
    	
    	gameCont.create("testPlayer1", "password");
    	games = gameDao.findAll();
    	assertEquals(games.size(), 1);
    	
    	Response res = gameCont.join(games.get(0).getUniqueId(), "testPlayer2", "password");
    	assertEquals(res.getStatusInfo(), Response.Status.OK);
    	
    	res = gameCont.join(games.get(0).getUniqueId(), "testPlayer2", "password");
    	assertEquals(res.getStatusInfo(), Response.Status.BAD_REQUEST);
    	
    	res = gameCont.join(games.get(0).getUniqueId(), "testPlayer3", "passw1rd");
    	assertEquals(res.getStatusInfo(), Response.Status.FORBIDDEN);
    	
    	res = gameCont.join(games.get(0).getUniqueId()+1, "testPlayer3", "password");
    	assertEquals(res.getStatusInfo(), Response.Status.NOT_FOUND);
    	
    	res = gameCont.lock(games.get(0).getUniqueId(), "testPlayer1");
    	assertEquals(res.getStatusInfo(), Response.Status.OK);
    	
    	res = gameCont.lock(games.get(0).getUniqueId(), "testPlayer1");
    	assertEquals(res.getStatusInfo(), Response.Status.FORBIDDEN);
    	
    	res = gameCont.join(games.get(0).getUniqueId(), "testPlayer3", "password");
    	assertEquals(res.getStatusInfo(), Response.Status.FORBIDDEN);
    	
    	res = gameCont.lock(games.get(0).getUniqueId()+1, "testPlayer1");
    	assertEquals(res.getStatusInfo(), Response.Status.NOT_FOUND);
    	
    	gameCont.create("testPlayer3", "password");
    	games = gameDao.findAll();
    	res = gameCont.lock(games.get(1).getUniqueId(), "testPlayer4");
    	assertEquals(res.getStatusInfo(), Response.Status.UNAUTHORIZED);
    	
    	res = gameCont.exit(games.get(0).getUniqueId(), "testPlayer4");
    	assertEquals(res.getStatusInfo(), Response.Status.NOT_FOUND);
    	
    	res = gameCont.exit(games.get(0).getUniqueId()+3, "testPlayer4");
    	assertEquals(res.getStatusInfo(), Response.Status.NOT_FOUND);
    	
    	assertEquals(games.size(), 2);
    	res = gameCont.exit(games.get(1).getUniqueId(), "testPlayer3");
    	games = gameDao.findAll();
    	assertEquals(games.size(), 1);
    	
    	res = gameCont.reset(games.get(0).getUniqueId(), "testPlayer2");
    	assertEquals(res.getStatusInfo(), Response.Status.UNAUTHORIZED);
    	
    	res = gameCont.reset(games.get(0).getUniqueId()+1, "testPlayer2");
    	assertEquals(res.getStatusInfo(), Response.Status.NOT_FOUND);
    	
    	res = gameCont.reset(games.get(0).getUniqueId(), "testPlayer1");
    	assertEquals(res.getStatusInfo(), Response.Status.OK);
    	
    	res = gameCont.findWord(games.get(0).getUniqueId(), "testPlayer1", "yos", "11|22");
    	assertEquals(res.getStatusInfo(), Response.Status.NOT_FOUND);
    	
    	res = gameCont.findWord(games.get(0).getUniqueId(), "testPlayer1", "yes", "11|22");
    	assertEquals(res.getStatusInfo(), Response.Status.NOT_FOUND);
    	
    	res = gameCont.findWord(games.get(0).getUniqueId()+1, "testPlayer1", "yes", "11|22|33");
    	assertEquals(res.getStatusInfo(), Response.Status.NOT_FOUND);
    	
    	Location loc = games.get(0).getPlayer("testPlayer1").getOffset();
    	String locStr1 = String.valueOf(loc.getColumn()+1)+String.valueOf(loc.getRow()+1);
    	String locStr2 = String.valueOf(loc.getColumn()+1)+String.valueOf(loc.getRow()+2);
    	String locStr3 = String.valueOf(loc.getColumn()+1)+String.valueOf(loc.getRow()+3);
    	System.out.println(locStr1+ "||" + locStr2 + "||" + locStr3);
    	
    	res = gameCont.findWord(games.get(0).getUniqueId(), "testPlayer1", "yes", locStr1+ "|" + locStr2 + "|" + locStr3);
    	assertEquals(res.getStatusInfo(), Response.Status.NOT_FOUND);
    	
    }

}
