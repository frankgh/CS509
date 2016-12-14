package com.wordsweeper.core.xml;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class CreateGameRequestTest {

    @Test
    public void constructor() throws Exception {
        CreateGameRequest res = new CreateGameRequest();

        res.setName("testPlayer");
        assertEquals(res.getName(), "testPlayer");

        res.setPassword("testPass");
        assertEquals(res.getPassword(), "testPass");

    }
}
