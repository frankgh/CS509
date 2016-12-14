
package com.wordsweeper.core.xml;

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
 *       &lt;attribute name="gameId" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="players" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * The game entering description.
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "gameBrief")
public class GameBrief {

    /**
     * The Game id.
     */
    @XmlAttribute(name = "gameId", required = true)
    protected String gameId;
    /**
     * The Players.
     */
    @XmlAttribute(name = "players", required = true)
    protected int players;

    /**
     * Gets the value of the gameId property.
     *
     * @return possible object is     {@link String }
     */
    public String getGameId() {
        return gameId;
    }

    /**
     * Sets the value of the gameId property.
     *
     * @param value allowed object is     {@link String }
     */
    public void setGameId(String value) {
        this.gameId = value;
    }

    /**
     * Gets the value of the players property.
     *
     * @return the players
     */
    public int getPlayers() {
        return players;
    }

    /**
     * Sets the value of the players property.
     *
     * @param value the value
     */
    public void setPlayers(int value) {
        this.players = value;
    }

}
