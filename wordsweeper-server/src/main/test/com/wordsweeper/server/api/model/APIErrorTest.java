package com.wordsweeper.server.api.model;

import org.junit.Test;

import com.wordsweeper.server.api.model.Letter;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

/**
 * Created by george on 11/11/16.
 */
public class APIErrorTest {
    @Test
    public void constructor() throws Exception {
    	APIError error = new APIError();
    	error.statusCode = 404;
    	error.message = "Content not found";
    	
    	assertEquals(404, error.status());
    	assertEquals("Content not found", error.message());
		
    }

}