package com.wordsweeper.service.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * This class implemented tests for request error.
 * @author george.
 */
public class RequestErrorTest {
    @Test
    public void constructor() throws Exception {
        RequestError reqErr = new RequestError(404, "Error: Content not found");
        assertEquals(404, reqErr.getStatusCode());
        assertEquals("Error: Content not found", reqErr.getMessage());
    }

}