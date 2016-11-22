
package com.wordsweeper.server.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element ref="{}connectRequest"/>
 *         &lt;element ref="{}createGameRequest"/>
 *         &lt;element ref="{}joinGameRequest"/>
 *         &lt;element ref="{}exitGameRequest"/>
 *         &lt;element ref="{}lockGameRequest"/>
 *         &lt;element ref="{}resetGameRequest"/>
 *         &lt;element ref="{}findWordRequest"/>
 *         &lt;element ref="{}repositionBoardRequest"/>
 *         &lt;element ref="{}listGamesRequest"/>
 *         &lt;element ref="{}showGameStateRequest"/>
 *       &lt;/choice>
 *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="version" type="{http://www.w3.org/2001/XMLSchema}string" default="1.0" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "connectRequest",
    "createGameRequest",
    "joinGameRequest",
    "exitGameRequest",
    "lockGameRequest",
    "resetGameRequest",
    "findWordRequest",
    "repositionBoardRequest",
    "listGamesRequest",
    "showGameStateRequest"
})
@XmlRootElement(name = "request")
public class Request {

    /**
     * The Connect request.
     */
    protected Object connectRequest;
    /**
     * The Create game request.
     */
    protected CreateGameRequest createGameRequest;
    /**
     * The Join game request.
     */
    protected JoinGameRequest joinGameRequest;
    /**
     * The Exit game request.
     */
    protected ExitGameRequest exitGameRequest;
    /**
     * The Lock game request.
     */
    protected LockGameRequest lockGameRequest;
    /**
     * The Reset game request.
     */
    protected ResetGameRequest resetGameRequest;
    /**
     * The Find word request.
     */
    protected FindWordRequest findWordRequest;
    /**
     * The Reposition board request.
     */
    protected RepositionBoardRequest repositionBoardRequest;
    /**
     * The List games request.
     */
    protected Object listGamesRequest;
    /**
     * The Show game state request.
     */
    protected ShowGameStateRequest showGameStateRequest;
    /**
     * The Id.
     */
    @XmlAttribute(name = "id", required = true)
    protected String id;
    /**
     * The Version.
     */
    @XmlAttribute(name = "version")
    protected String version;

    /**
     * Gets the value of the connectRequest property.
     *
     * @return possible object is     {@link Object }
     */
    public Object getConnectRequest() {
        return connectRequest;
    }

    /**
     * Sets the value of the connectRequest property.
     *
     * @param value allowed object is     {@link Object }
     */
    public void setConnectRequest(Object value) {
        this.connectRequest = value;
    }

    /**
     * Gets the value of the createGameRequest property.
     *
     * @return possible object is     {@link CreateGameRequest }
     */
    public CreateGameRequest getCreateGameRequest() {
        return createGameRequest;
    }

    /**
     * Sets the value of the createGameRequest property.
     *
     * @param value allowed object is     {@link CreateGameRequest }
     */
    public void setCreateGameRequest(CreateGameRequest value) {
        this.createGameRequest = value;
    }

    /**
     * Gets the value of the joinGameRequest property.
     *
     * @return possible object is     {@link JoinGameRequest }
     */
    public JoinGameRequest getJoinGameRequest() {
        return joinGameRequest;
    }

    /**
     * Sets the value of the joinGameRequest property.
     *
     * @param value allowed object is     {@link JoinGameRequest }
     */
    public void setJoinGameRequest(JoinGameRequest value) {
        this.joinGameRequest = value;
    }

    /**
     * Gets the value of the exitGameRequest property.
     *
     * @return possible object is     {@link ExitGameRequest }
     */
    public ExitGameRequest getExitGameRequest() {
        return exitGameRequest;
    }

    /**
     * Sets the value of the exitGameRequest property.
     *
     * @param value allowed object is     {@link ExitGameRequest }
     */
    public void setExitGameRequest(ExitGameRequest value) {
        this.exitGameRequest = value;
    }

    /**
     * Gets the value of the lockGameRequest property.
     *
     * @return possible object is     {@link LockGameRequest }
     */
    public LockGameRequest getLockGameRequest() {
        return lockGameRequest;
    }

    /**
     * Sets the value of the lockGameRequest property.
     *
     * @param value allowed object is     {@link LockGameRequest }
     */
    public void setLockGameRequest(LockGameRequest value) {
        this.lockGameRequest = value;
    }

    /**
     * Gets the value of the resetGameRequest property.
     *
     * @return possible object is     {@link ResetGameRequest }
     */
    public ResetGameRequest getResetGameRequest() {
        return resetGameRequest;
    }

    /**
     * Sets the value of the resetGameRequest property.
     *
     * @param value allowed object is     {@link ResetGameRequest }
     */
    public void setResetGameRequest(ResetGameRequest value) {
        this.resetGameRequest = value;
    }

    /**
     * Gets the value of the findWordRequest property.
     *
     * @return possible object is     {@link FindWordRequest }
     */
    public FindWordRequest getFindWordRequest() {
        return findWordRequest;
    }

    /**
     * Sets the value of the findWordRequest property.
     *
     * @param value allowed object is     {@link FindWordRequest }
     */
    public void setFindWordRequest(FindWordRequest value) {
        this.findWordRequest = value;
    }

    /**
     * Gets the value of the repositionBoardRequest property.
     *
     * @return possible object is     {@link RepositionBoardRequest }
     */
    public RepositionBoardRequest getRepositionBoardRequest() {
        return repositionBoardRequest;
    }

    /**
     * Sets the value of the repositionBoardRequest property.
     *
     * @param value allowed object is     {@link RepositionBoardRequest }
     */
    public void setRepositionBoardRequest(RepositionBoardRequest value) {
        this.repositionBoardRequest = value;
    }

    /**
     * Gets the value of the listGamesRequest property.
     *
     * @return possible object is     {@link Object }
     */
    public Object getListGamesRequest() {
        return listGamesRequest;
    }

    /**
     * Sets the value of the listGamesRequest property.
     *
     * @param value allowed object is     {@link Object }
     */
    public void setListGamesRequest(Object value) {
        this.listGamesRequest = value;
    }

    /**
     * Gets the value of the showGameStateRequest property.
     *
     * @return possible object is     {@link ShowGameStateRequest }
     */
    public ShowGameStateRequest getShowGameStateRequest() {
        return showGameStateRequest;
    }

    /**
     * Sets the value of the showGameStateRequest property.
     *
     * @param value allowed object is     {@link ShowGameStateRequest }
     */
    public void setShowGameStateRequest(ShowGameStateRequest value) {
        this.showGameStateRequest = value;
    }

    /**
     * Gets the value of the id property.
     *
     * @return possible object is     {@link String }
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     *
     * @param value allowed object is     {@link String }
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the version property.
     *
     * @return possible object is     {@link String }
     */
    public String getVersion() {
        if (version == null) {
            return "1.0";
        } else {
            return version;
        }
    }

    /**
     * Sets the value of the version property.
     *
     * @param value allowed object is     {@link String }
     */
    public void setVersion(String value) {
        this.version = value;
    }

}
