package com.wordsweeper.core.xml;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;


public class MessageTest {

    @Test
    public void constructor() throws Exception {
        Message testMes = new Message();

        Request testReq = Mockito.mock(Request.class);
        testMes.setRequest(testReq);
        assertEquals(testReq, testMes.getRequest());

        Response testRes = Mockito.mock(Response.class);
        testMes.setResponse(testRes);
        assertEquals(testRes, testMes.getResponse());

    }
}
