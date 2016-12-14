
package com.wordsweeper.core.xml;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the com.wordsweeper.core.xml package.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation
 * for XML content. The Java representation of XML
 * content can consist of schema derived interfaces
 * and classes representing the binding of schema
 * type definitions, element declarations and model
 * groups.  Factory methods for each of these are
 * provided in this class.
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ConnectRequest_QNAME = new QName("", "connectRequest");
    private final static QName _ListGamesRequest_QNAME = new QName("", "listGamesRequest");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.wordsweeper.core.xml
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ResetGameResponse }
     *
     * @return the reset game response
     */
    public ResetGameResponse createResetGameResponse() {
        return new ResetGameResponse();
    }

    /**
     * Create an instance of {@link Player }
     *
     * @return the player
     */
    public Player createPlayer() {
        return new Player();
    }

    /**
     * Create an instance of {@link JoinGameRequest }
     *
     * @return the join game request
     */
    public JoinGameRequest createJoinGameRequest() {
        return new JoinGameRequest();
    }

    /**
     * Create an instance of {@link Cell }
     *
     * @return the cell
     */
    public Cell createCell() {
        return new Cell();
    }

    /**
     * Create an instance of {@link BoardResponse }
     *
     * @return the board response
     */
    public BoardResponse createBoardResponse() {
        return new BoardResponse();
    }

    /**
     * Create an instance of {@link ListGamesResponse }
     *
     * @return the list games response
     */
    public ListGamesResponse createListGamesResponse() {
        return new ListGamesResponse();
    }

    /**
     * Create an instance of {@link GameBrief }
     *
     * @return the game brief
     */
    public GameBrief createGameBrief() {
        return new GameBrief();
    }

    /**
     * Create an instance of {@link FindWordRequest }
     *
     * @return the find word request
     */
    public FindWordRequest createFindWordRequest() {
        return new FindWordRequest();
    }

    /**
     * Create an instance of {@link Message }
     *
     * @return the message
     */
    public Message createMessage() {
        return new Message();
    }

    /**
     * Create an instance of {@link Response }
     *
     * @return the response
     */
    public Response createResponse() {
        return new Response();
    }

    /**
     * Create an instance of {@link ConnectResponse }
     *
     * @return the connect response
     */
    public ConnectResponse createConnectResponse() {
        return new ConnectResponse();
    }

    /**
     * Create an instance of {@link LockGameResponse }
     *
     * @return the lock game response
     */
    public LockGameResponse createLockGameResponse() {
        return new LockGameResponse();
    }

    /**
     * Create an instance of {@link JoinGameResponse }
     *
     * @return the join game response
     */
    public JoinGameResponse createJoinGameResponse() {
        return new JoinGameResponse();
    }

    /**
     * Create an instance of {@link ExitGameResponse }
     *
     * @return the exit game response
     */
    public ExitGameResponse createExitGameResponse() {
        return new ExitGameResponse();
    }

    /**
     * Create an instance of {@link FindWordResponse }
     *
     * @return the find word response
     */
    public FindWordResponse createFindWordResponse() {
        return new FindWordResponse();
    }

    /**
     * Create an instance of {@link Request }
     *
     * @return the request
     */
    public Request createRequest() {
        return new Request();
    }

    /**
     * Create an instance of {@link CreateGameRequest }
     *
     * @return the create game request
     */
    public CreateGameRequest createCreateGameRequest() {
        return new CreateGameRequest();
    }

    /**
     * Create an instance of {@link ExitGameRequest }
     *
     * @return the exit game request
     */
    public ExitGameRequest createExitGameRequest() {
        return new ExitGameRequest();
    }

    /**
     * Create an instance of {@link LockGameRequest }
     *
     * @return the lock game request
     */
    public LockGameRequest createLockGameRequest() {
        return new LockGameRequest();
    }

    /**
     * Create an instance of {@link ResetGameRequest }
     *
     * @return the reset game request
     */
    public ResetGameRequest createResetGameRequest() {
        return new ResetGameRequest();
    }

    /**
     * Create an instance of {@link RepositionBoardRequest }
     *
     * @return the reposition board request
     */
    public RepositionBoardRequest createRepositionBoardRequest() {
        return new RepositionBoardRequest();
    }

    /**
     * Create an instance of {@link ShowGameStateRequest }
     *
     * @return the show game state request
     */
    public ShowGameStateRequest createShowGameStateRequest() {
        return new ShowGameStateRequest();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     *
     * @param value the value
     * @return the jaxb element
     */
    @XmlElementDecl(namespace = "", name = "connectRequest")
    public JAXBElement<Object> createConnectRequest(Object value) {
        return new JAXBElement<Object>(_ConnectRequest_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     *
     * @param value the value
     * @return the jaxb element
     */
    @XmlElementDecl(namespace = "", name = "listGamesRequest")
    public JAXBElement<Object> createListGamesRequest(Object value) {
        return new JAXBElement<Object>(_ListGamesRequest_QNAME, Object.class, null, value);
    }

}
