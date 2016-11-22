
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
 *       &lt;attribute name="gameId" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="score" use="required" type="{http://www.w3.org/2001/XMLSchema}long" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "findWordResponse")
public class FindWordResponse {

    /**
     * The Game id.
     */
    @XmlAttribute(name = "gameId", required = true)
    protected String gameId;
    /**
     * The Name.
     */
    @XmlAttribute(name = "name", required = true)
    protected String name;
    /**
     * The Score.
     */
    @XmlAttribute(name = "score", required = true)
    protected long score;

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
     * Gets the value of the name property.
     *
     * @return possible object is     {@link String }
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     *
     * @param value allowed object is     {@link String }
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the score property.
     *
     * @return the score
     */
    public long getScore() {
        return score;
    }

    /**
     * Sets the value of the score property.
     *
     * @param value the value
     */
    public void setScore(long value) {
        this.score = value;
    }

}
