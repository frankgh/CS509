
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
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="position" use="required" type="{}commaSeparatedPair" />
 *       &lt;attribute name="board" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="score" use="required" type="{http://www.w3.org/2001/XMLSchema}long" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * The player's attributes from the server side.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "player")
public class Player {

    /**
     * The Name.
     */
    @XmlAttribute(name = "name", required = true)
    protected String name;
    /**
     * The Position.
     */
    @XmlAttribute(name = "position", required = true)
    protected String position;
    /**
     * The Board.
     */
    @XmlAttribute(name = "board", required = true)
    protected String board;
    /**
     * The Score.
     */
    @XmlAttribute(name = "score", required = true)
    protected long score;

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
     * Gets the value of the position property.
     *
     * @return possible object is     {@link String }
     */
    public String getPosition() {
        return position;
    }

    /**
     * Sets the value of the position property.
     *
     * @param value allowed object is     {@link String }
     */
    public void setPosition(String value) {
        this.position = value;
    }

    /**
     * Gets the value of the board property.
     *
     * @return possible object is     {@link String }
     */
    public String getBoard() {
        return board;
    }

    /**
     * Sets the value of the board property.
     *
     * @param value allowed object is     {@link String }
     */
    public void setBoard(String value) {
        this.board = value;
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
