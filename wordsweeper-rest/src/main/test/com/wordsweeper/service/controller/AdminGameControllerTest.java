package com.wordsweeper.service.controller;

import static org.junit.Assert.assertNotNull;

import javax.ws.rs.core.Response;
import org.junit.Test;

/**
 * Created by george on 11/11/16.
 */
public class AdminGameControllerTest {
    @Test
    public void constructor() throws Exception {
    	AdminGameController adminCon = new AdminGameController(); 
    	Response res = adminCon.list();
    	assertNotNull(res);
    }

}
