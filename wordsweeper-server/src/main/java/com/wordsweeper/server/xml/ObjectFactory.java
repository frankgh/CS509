
package com.wordsweeper.server.xml;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.wordsweeper.server.xml package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ConnectRequest_QNAME = new QName("", "connectRequest");
    private final static QName _ListGamesRequest_QNAME = new QName("", "listGamesRequest");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.wordsweeper.server.xml
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ResetGameResponse }
     * 
     */
    public ResetGameResponse createResetGameResponse() {
        return new ResetGameResponse();
    }

    /**
     * Create an instance of {@link Player }
     * 
     */
    public Player createPlayer() {
        return new Player();
    }

    /**
     * Create an instance of {@link JoinGameRequest }
     * 
     */
    public JoinGameRequest createJoinGameRequest() {
        return new JoinGameRequest();
    }

    /**
     * Create an instance of {@link Cell }
     * 
     */
    public Cell createCell() {
        return new Cell();
    }

    /**
     * Create an instance of {@link BoardResponse }
     * 
     */
    public BoardResponse createBoardResponse() {
        return new BoardResponse();
    }

    /**
     * Create an instance of {@link ListGamesResponse }
     * 
     */
    public ListGamesResponse createListGamesResponse() {
        return new ListGamesResponse();
    }

    /**
     * Create an instance of {@link GameBrief }
     * 
     */
    public GameBrief createGameBrief() {
        return new GameBrief();
    }

    /**
     * Create an instance of {@link FindWordRequest }
     * 
     */
    public FindWordRequest createFindWordRequest() {
        return new FindWordRequest();
    }

    /**
     * Create an instance of {@link Message }
     * 
     */
    public Message createMessage() {
        return new Message();
    }

    /**
     * Create an instance of {@link Response }
     * 
     */
    public Response createResponse() {
        return new Response();
    }

    /**
     * Create an instance of {@link ConnectResponse }
     * 
     */
    public ConnectResponse createConnectResponse() {
        return new ConnectResponse();
    }

    /**
     * Create an instance of {@link LockGameResponse }
     * 
     */
    public LockGameResponse createLockGameResponse() {
        return new LockGameResponse();
    }

    /**
     * Create an instance of {@link JoinGameResponse }
     * 
     */
    public JoinGameResponse createJoinGameResponse() {
        return new JoinGameResponse();
    }

    /**
     * Create an instance of {@link ExitGameResponse }
     * 
     */
    public ExitGameResponse createExitGameResponse() {
        return new ExitGameResponse();
    }

    /**
     * Create an instance of {@link FindWordResponse }
     * 
     */
    public FindWordResponse createFindWordResponse() {
        return new FindWordResponse();
    }

    /**
     * Create an instance of {@link Request }
     * 
     */
    public Request createRequest() {
        return new Request();
    }

    /**
     * Create an instance of {@link CreateGameRequest }
     * 
     */
    public CreateGameRequest createCreateGameRequest() {
        return new CreateGameRequest();
    }

    /**
     * Create an instance of {@link ExitGameRequest }
     * 
     */
    public ExitGameRequest createExitGameRequest() {
        return new ExitGameRequest();
    }

    /**
     * Create an instance of {@link LockGameRequest }
     * 
     */
    public LockGameRequest createLockGameRequest() {
        return new LockGameRequest();
    }

    /**
     * Create an instance of {@link ResetGameRequest }
     * 
     */
    public ResetGameRequest createResetGameRequest() {
        return new ResetGameRequest();
    }

    /**
     * Create an instance of {@link RepositionBoardRequest }
     * 
     */
    public RepositionBoardRequest createRepositionBoardRequest() {
        return new RepositionBoardRequest();
    }

    /**
     * Create an instance of {@link ShowGameStateRequest }
     * 
     */
    public ShowGameStateRequest createShowGameStateRequest() {
        return new ShowGameStateRequest();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "connectRequest")
    public JAXBElement<Object> createConnectRequest(Object value) {
        return new JAXBElement<Object>(_ConnectRequest_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "listGamesRequest")
    public JAXBElement<Object> createListGamesRequest(Object value) {
        return new JAXBElement<Object>(_ListGamesRequest_QNAME, Object.class, null, value);
    }

}
