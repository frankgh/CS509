package com.wordsweeper.core.xml;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class ConnectResponseTest {

    @Test
    public void constructor() throws Exception {
        ConnectResponse conRes = new ConnectResponse();

        conRes.setId("testID");
        assertEquals(conRes.getId(), "testID");

    }
}
