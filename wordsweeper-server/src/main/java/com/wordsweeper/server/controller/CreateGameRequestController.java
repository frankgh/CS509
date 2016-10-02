package com.wordsweeper.server.controller;

import com.wordsweeper.server.model.BoardResponse;
import com.wordsweeper.server.model.Player;
import com.wordsweeper.server.model.Response;
import com.wordsweeper.server.model.ServerModel;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import server.ClientState;
import server.IProtocolHandler;
import xml.Message;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

/**
 * Controller on server to package up the current state of the model
 * as an updateResponse message and send it back to the client.
 */
public class CreateGameRequestController implements IProtocolHandler {

    ServerModel model;

    public CreateGameRequestController(ServerModel model) {
        this.model = model;
    }

    public Message process(ClientState client, Message request) {

        model.joinGame();  // HACK.

        // note you can retrieve information from the request...
        Node createRequest = request.contents.getFirstChild();
        NamedNodeMap map = createRequest.getAttributes();

        String pname = map.getNamedItem("name").getNodeValue();

        Player player = new Player();
        player.setName(pname);
        player.setScore(392489038);
        player.setPosition("4,6");
        player.setBoard("AFERKSOEROIERPOR");

        BoardResponse boardResponse = new BoardResponse();
        boardResponse.setGameId("hg12jhd");
        boardResponse.setManagingUser(pname);
        boardResponse.setBonus("4,3");
        boardResponse.setContents("ABCGBCJDH...HDJHJD");
        boardResponse.getPlayer().add(player);

        Response boardResponseWrapper = new Response(request.id(), true);
        boardResponseWrapper.setBoardResponse(boardResponse);

        JAXBContext jaxbContext = null;
        try {
            jaxbContext = JAXBContext.newInstance(Response.class);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        Marshaller jaxbMarshaller = null;
        try {
            jaxbMarshaller = jaxbContext.createMarshaller();
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        StringWriter sw = new StringWriter();
        try {
            jaxbMarshaller.marshal(boardResponseWrapper, sw);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        String xmlString = sw.toString();


        // send this response back to the client which sent us the request.
        return new Message(xmlString);
    }
}
