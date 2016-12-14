package com.wordsweeper.core.util;

import com.wordsweeper.core.xml.Response;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.BufferedReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by francisco on 12/13/16.
 */
public class JAXBUtilTest {
    @Test
    public void serialize() throws Exception {

    }

    @Test
    public void serialize1() throws Exception {

    }

    @Test
    public void deserialize() throws Exception {

        String input = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><response id=\"49b7d59c-87bd-42a0-bcfb-cb05c51beb33\" success=\"true\" version=\"1.0\"><boardResponse bonus=\"7,2\" contents=\"R,T,E,N,L,R,P,T,X,I,K,K,L,A,S,B,V,T,S,S,R,A,I,L,S,M,G,I,I,R,E,I,B,E,S,R,C,N,S,L,N,E,O,N,M,P,R,O,A\" gameId=\"n65agh23rrusg7lcc4rm80fuj\" managingUser=\"ye\" size=\"7\"><player board=\"R,T,E,N,T,X,I,K,S,B,V,T,A,I,L,S\" name=\"ye\" position=\"1,1\" score=\"0\"/></boardResponse></response>";
        Response response = JAXBUtil.deserialize(input, Response.class);

        assertNotNull(response);


        //managingUser="ye" size="7"><player board="R,T,E,N,T,X,I,K,S,B,V,T,A,I,L,S" name="ye" position="1,1" score="0"/></boardResponse></response>";
        assertEquals("49b7d59c-87bd-42a0-bcfb-cb05c51beb33", response.getId());
        assertEquals(true, response.isSuccess());
        assertEquals("1.0", response.getVersion());

        assertNotNull(response.getBoardResponse());

        assertEquals("7,2", response.getBoardResponse().getBonus());
        assertEquals("R,T,E,N,L,R,P,T,X,I,K,K,L,A,S,B,V,T,S,S,R,A,I,L,S,M,G,I,I,R,E,I,B,E,S,R,C,N,S,L,N,E,O,N,M,P,R,O,A", response.getBoardResponse().getContents());
        assertEquals("n65agh23rrusg7lcc4rm80fuj", response.getBoardResponse().getGameId());
        assertEquals("ye", response.getBoardResponse().getManagingUser());

        assertNotNull(response.getBoardResponse().getSize());
        assertEquals(7, response.getBoardResponse().getSize().intValue());

    }

    @Test
    public void getXmlStringFromReader() throws Exception {

        BufferedReader bufferedReader = org.mockito.Mockito.mock(BufferedReader.class);
        String input = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><response id=\"49b7d59c-87bd-42a0-bcfb-cb05c51beb33\" success=\"true\" version=\"1.0\"><boardResponse bonus=\"7,2\" contents=\"R,T,E,N,L,R,P,T,X,I,K,K,L,A,S,B,V,T,S,S,R,A,I,L,S,M,G,I,I,R,E,I,B,E,S,R,C,N,S,L,N,E,O,N,M,P,R,O,A\" gameId=\"n65agh23rrusg7lcc4rm80fuj\" managingUser=\"ye\" size=\"7\"><player board=\"R,T,E,N,T,X,I,K,S,B,V,T,A,I,L,S\" name=\"ye\" position=\"1,1\" score=\"0\"/></boardResponse></response>";
        Mockito.when(bufferedReader.readLine()).thenReturn(input).thenReturn(input);
        String xml = JAXBUtil.getXmlStringFromReader(bufferedReader, "</response>");

        assertEquals(input, xml);

    }

}