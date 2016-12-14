package com.wordsweeper.adminclient.controller;

import com.wordsweeper.adminclient.model.AdminClientModel;
import com.wordsweeper.adminclient.view.AdminClientApplication;
import com.wordsweeper.core.util.JAXBUtil;
import com.wordsweeper.core.xml.Response;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.BufferedReader;

import static org.junit.Assert.assertEquals;

/**
 * This is the class for admin client model.
 */
public class ListGamesResponseControllerTest {
    @Test
    public void constructor() throws Exception {

        AdminClientModel model = new AdminClientModel();
        AdminClientApplication app = new AdminClientApplication(model);
        ListGamesResponseController resCont = new ListGamesResponseController(app, model);
        assertEquals(app.gamelist.getRowCount(), 0);
        BufferedReader bufferedReader = org.mockito.Mockito.mock(BufferedReader.class);
        String input = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><response id=\"cc667680-81d6-4d5b-a23b-3edc2cdf866f\" success=\"true\" version=\"1.0\"><listGamesResponse><gameBrief gameId=\"nc99qujnk002q3akkhpde400s0\" players=\"1\"/><gameBrief gameId=\"n65agh23rrusg7lcc4rm80fuj\" players=\"1\"/></listGamesResponse></response>";

        Mockito.when(bufferedReader.readLine()).thenReturn(input).thenReturn(input);
        String xml = JAXBUtil.getXmlStringFromReader(bufferedReader, "</response>");
        Response response = JAXBUtil.deserialize(xml, Response.class);
        resCont.process(response);

        assertEquals(app.gamelist.getRowCount(), 2);
    }
}
