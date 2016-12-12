package com.wordsweeper.service.controller;

import com.wordsweeper.service.repository.GameDao;
import com.wordsweeper.service.repository.GameDaoImpl;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Testing the game controller from admin client side.
 * @author George
 */
public class AdminGameControllerTest {
    @Test
    public void constructor() throws Exception {
    	AdminGameController adminCon = new AdminGameController(); 
    	Response res = adminCon.list();
    	assertNotNull(res);
    }

}
