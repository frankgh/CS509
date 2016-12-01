
package com.wordsweeper.server.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element ref="{}connectResponse"/>
 *         &lt;element ref="{}boardResponse"/>
 *         &lt;element ref="{}lockGameResponse"/>
 *         &lt;element ref="{}resetGameResponse"/>
 *         &lt;element ref="{}joinGameResponse"/>
 *         &lt;element ref="{}exitGameResponse"/>
 *         &lt;element ref="{}findWordResponse"/>
 *         &lt;element ref="{}listGamesResponse"/>
 *       &lt;/choice>
 *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="success" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="reason" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="version" type="{http://www.w3.org/2001/XMLSchema}string" default="1.0" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "connectResponse",
    "boardResponse",
    "lockGameResponse",
    "resetGameResponse",
    "joinGameResponse",
    "exitGameResponse",
    "findWordResponse",
    "listGamesResponse"
})
@XmlRootElement(name = "response")
public class Response {

    protected ConnectResponse connectResponse;
    protected BoardResponse boardResponse;
    protected LockGameResponse lockGameResponse;
    protected ResetGameResponse resetGameResponse;
    protected JoinGameResponse joinGameResponse;
    protected ExitGameResponse exitGameResponse;
    protected FindWordResponse findWordResponse;
    protected ListGamesResponse listGamesResponse;
    @XmlAttribute(name = "id", required = true)
    protected String id;
    @XmlAttribute(name = "success", required = true)
    protected boolean success;
    @XmlAttribute(name = "reason")
    protected String reason;
    @XmlAttribute(name = "version")
    protected String version;

    /**
     * Gets the value of the connectResponse property.
     * 
     * @return
     *     possible object is
     *     {@link ConnectResponse }
     *     
     */
    public ConnectResponse getConnectResponse() {
        return connectResponse;
    }

    /**
     * Sets the value of the connectResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConnectResponse }
     *     
     */
    public void setConnectResponse(ConnectResponse value) {
        this.connectResponse = value;
    }

    /**
     * Gets the value of the boardResponse property.
     * 
     * @return
     *     possible object is
     *     {@link BoardResponse }
     *     
     */
    public BoardResponse getBoardResponse() {
        return boardResponse;
    }

    /**
     * Sets the value of the boardResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link BoardResponse }
     *     
     */
    public void setBoardResponse(BoardResponse value) {
        this.boardResponse = value;
    }

    /**
     * Gets the value of the lockGameResponse property.
     * 
     * @return
     *     possible object is
     *     {@link LockGameResponse }
     *     
     */
    public LockGameResponse getLockGameResponse() {
        return lockGameResponse;
    }

    /**
     * Sets the value of the lockGameResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link LockGameResponse }
     *     
     */
    public void setLockGameResponse(LockGameResponse value) {
        this.lockGameResponse = value;
    }

    /**
     * Gets the value of the resetGameResponse property.
     * 
     * @return
     *     possible object is
     *     {@link ResetGameResponse }
     *     
     */
    public ResetGameResponse getResetGameResponse() {
        return resetGameResponse;
    }

    /**
     * Sets the value of the resetGameResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResetGameResponse }
     *     
     */
    public void setResetGameResponse(ResetGameResponse value) {
        this.resetGameResponse = value;
    }

    /**
     * Gets the value of the joinGameResponse property.
     * 
     * @return
     *     possible object is
     *     {@link JoinGameResponse }
     *     
     */
    public JoinGameResponse getJoinGameResponse() {
        return joinGameResponse;
    }

    /**
     * Sets the value of the joinGameResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link JoinGameResponse }
     *     
     */
    public void setJoinGameResponse(JoinGameResponse value) {
        this.joinGameResponse = value;
    }

    /**
     * Gets the value of the exitGameResponse property.
     * 
     * @return
     *     possible object is
     *     {@link ExitGameResponse }
     *     
     */
    public ExitGameResponse getExitGameResponse() {
        return exitGameResponse;
    }

    /**
     * Sets the value of the exitGameResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link ExitGameResponse }
     *     
     */
    public void setExitGameResponse(ExitGameResponse value) {
        this.exitGameResponse = value;
    }

    /**
     * Gets the value of the findWordResponse property.
     * 
     * @return
     *     possible object is
     *     {@link FindWordResponse }
     *     
     */
    public FindWordResponse getFindWordResponse() {
        return findWordResponse;
    }

    /**
     * Sets the value of the findWordResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link FindWordResponse }
     *     
     */
    public void setFindWordResponse(FindWordResponse value) {
        this.findWordResponse = value;
    }

    /**
     * Gets the value of the listGamesResponse property.
     * 
     * @return
     *     possible object is
     *     {@link ListGamesResponse }
     *     
     */
    public ListGamesResponse getListGamesResponse() {
        return listGamesResponse;
    }

    /**
     * Sets the value of the listGamesResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link ListGamesResponse }
     *     
     */
    public void setListGamesResponse(ListGamesResponse value) {
        this.listGamesResponse = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the success property.
     * 
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * Returns whether request was successful
     * 
     */
    public Boolean getSuccess() {
        return this.success;
    }
    
    /**
     * Sets the value of the success property.
     * 
     */
    public void setSuccess(boolean value) {
        this.success = value;
    }
    
    /**
     * Gets the value of the reason property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReason() {
        return reason;
    }

    /**
     * Sets the value of the reason property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReason(String value) {
        this.reason = value;
    }

    /**
     * Gets the value of the version property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
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
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersion(String value) {
        this.version = value;
    }

}
