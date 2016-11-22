package com.wordsweeper.service.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by george on 11/11/16.
 */
public class RequestErrorTest {
    @Test
    public void constructor() throws Exception {
        RequestError reqErr = new RequestError(404, "Error: Content not found");
        assertEquals(404, reqErr.getStatusCode());
        assertEquals("Error: Content not found", reqErr.getMessage());
    }

}