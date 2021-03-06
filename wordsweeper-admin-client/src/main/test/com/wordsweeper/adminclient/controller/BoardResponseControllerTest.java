package com.wordsweeper.adminclient.controller;

import com.wordsweeper.adminclient.view.AdminClientApplication;
import com.wordsweeper.core.util.JAXBUtil;
import com.wordsweeper.core.xml.Response;
import org.junit.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.io.BufferedReader;

import static org.junit.Assert.assertEquals;

/**
 * This is the class for admin client model.
 */
public class BoardResponseControllerTest {
    @Test
    public void constructor() throws Exception {

        AdminClientApplication app = new AdminClientApplication();
        BoardResponseController boaCont = new BoardResponseController(app);
        assertEquals(app.gamelist.getRowCount(), 0);
        BufferedReader bufferedReader = org.mockito.Mockito.mock(BufferedReader.class);
        String input = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><response id=\"49b7d59c-87bd-42a0-bcfb-cb05c51beb33\" success=\"true\" version=\"1.0\"><boardResponse bonus=\"7,2\" contents=\"R,T,E,N,L,R,P,T,X,I,K,K,L,A,S,B,V,T,S,S,R,A,I,L,S,M,G,I,I,R,E,I,B,E,S,R,C,N,S,L,N,E,O,N,M,P,R,O,A\" gameId=\"n65agh23rrusg7lcc4rm80fuj\" managingUser=\"ye\" size=\"7\"><player board=\"R,T,E,N,T,X,I,K,S,B,V,T,A,I,L,S\" name=\"ye\" position=\"1,1\" score=\"0\"/></boardResponse></response>";
        Mockito.when(bufferedReader.readLine()).thenReturn(input).thenReturn(input);
        String xml = JAXBUtil.getXmlStringFromReader(bufferedReader, "</response>");
        Response response = JAXBUtil.deserialize(xml, Response.class);
        boaCont.process(response);

        int B_r = 2;
        int B_c = 7;
        assertEquals(app.ColorMap[B_r - 1][B_c - 1][4], Color.YELLOW);
    }
}
