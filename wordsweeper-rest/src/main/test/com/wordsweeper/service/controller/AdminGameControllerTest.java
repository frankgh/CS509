package com.wordsweeper.service.controller;

import static org.junit.Assert.assertNotNull;

import javax.ws.rs.core.Response;
import org.junit.Test;

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
