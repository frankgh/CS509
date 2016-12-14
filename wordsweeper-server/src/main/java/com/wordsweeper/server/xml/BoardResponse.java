
package com.wordsweeper.server.xml;

import java.util.ArrayList;
import java.util.List;
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
 *       &lt;sequence>
 *         &lt;element ref="{}player" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="gameId" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="size" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="managingUser" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="contents" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="bonus" use="required" type="{}commaSeparatedPair" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * This class will return board response from client.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "player"
})
@XmlRootElement(name = "boardResponse")

public class BoardResponse {

    /**
     * The Player.
     */
    protected List<Player> player;
    /**
     * The Game id.
     */
    @XmlAttribute(name = "gameId", required = true)
    protected String gameId;
    /**
     * The Size.
     */
    @XmlAttribute(name = "size")
    protected Integer size;
    /**
     * The Managing user.
     */
    @XmlAttribute(name = "managingUser", required = true)
    protected String managingUser;
    /**
     * The Contents.
     */
    @XmlAttribute(name = "contents")
    protected String contents;
    /**
     * The Bonus.
     */
    @XmlAttribute(name = "bonus", required = true)
    protected String bonus;

    /**
     * Gets the value of the player property.
     * <p>
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the player property.
     * <p>
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPlayer().add(newItem);
     * </pre>
     * <p>
     * <p>
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Player }
     *
     * @return the player
     */
    public List<Player> getPlayer() {
        if (player == null) {
            player = new ArrayList<Player>();
        }
        return this.player;
    }

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
     * Gets the value of the size property.
     *
     * @return possible object is     {@link Integer }
     */
    public Integer getSize() {
        return size;
    }

    /**
     * Sets the value of the size property.
     *
     * @param value allowed object is     {@link Integer }
     */
    public void setSize(Integer value) {
        this.size = value;
    }

    /**
     * Gets the value of the managingUser property.
     *
     * @return possible object is     {@link String }
     */
    public String getManagingUser() {
        return managingUser;
    }

    /**
     * Sets the value of the managingUser property.
     *
     * @param value allowed object is     {@link String }
     */
    public void setManagingUser(String value) {
        this.managingUser = value;
    }

    /**
     * Gets the value of the contents property.
     *
     * @return possible object is     {@link String }
     */
    public String getContents() {
        return contents;
    }

    /**
     * Sets the value of the contents property.
     *
     * @param value allowed object is     {@link String }
     */
    public void setContents(String value) {
        this.contents = value;
    }

    /**
     * Gets the value of the bonus property.
     *
     * @return possible object is     {@link String }
     */
    public String getBonus() {
        return bonus;
    }

    /**
     * Sets the value of the bonus property.
     *
     * @param value allowed object is     {@link String }
     */
    public void setBonus(String value) {
        this.bonus = value;
    }

}
