
package com.wordsweeper.core.xml;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
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
 *         &lt;element ref="{}cell" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="word" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="gameId" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * Server in charge of relaying finding word requests.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "cell"
})
@XmlRootElement(name = "findWordRequest")
/**
 * Get finding word request from the client.
 */
public class FindWordRequest {

    /**
     * The Cell.
     */
    @XmlElement(required = true)
    protected List<Cell> cell;
    /**
     * The Name.
     */
    @XmlAttribute(name = "name", required = true)
    protected String name;
    /**
     * The Word.
     */
    @XmlAttribute(name = "word", required = true)
    protected String word;
    /**
     * The Game id.
     */
    @XmlAttribute(name = "gameId", required = true)
    protected String gameId;

    /**
     * Gets the value of the cell property.
     * <p>
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cell property.
     * <p>
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCell().add(newItem);
     * </pre>
     * <p>
     * <p>
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Cell }
     *
     * @return the cell
     */
    public List<Cell> getCell() {
        if (cell == null) {
            cell = new ArrayList<Cell>();
        }
        return this.cell;
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
     * Gets the value of the word property.
     *
     * @return possible object is     {@link String }
     */
    public String getWord() {
        return word;
    }

    /**
     * Sets the value of the word property.
     *
     * @param value allowed object is     {@link String }
     */
    public void setWord(String value) {
        this.word = value;
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

}
