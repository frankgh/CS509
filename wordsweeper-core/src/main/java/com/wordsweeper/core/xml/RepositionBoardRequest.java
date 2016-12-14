
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
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="gameId" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="rowChange" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="colChange" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * This class introduced reposition board request received from server side.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "repositionBoardRequest")
public class RepositionBoardRequest {

    /**
     * The Name.
     */
    @XmlAttribute(name = "name", required = true)
    protected String name;
    /**
     * The Game id.
     */
    @XmlAttribute(name = "gameId", required = true)
    protected String gameId;
    /**
     * The Row change.
     */
    @XmlAttribute(name = "rowChange")
    protected Integer rowChange;
    /**
     * The Col change.
     */
    @XmlAttribute(name = "colChange")
    protected Integer colChange;

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
     * Gets the value of the rowChange property.
     *
     * @return possible object is     {@link Integer }
     */
    public Integer getRowChange() {
        return rowChange;
    }

    /**
     * Sets the value of the rowChange property.
     *
     * @param value allowed object is     {@link Integer }
     */
    public void setRowChange(Integer value) {
        this.rowChange = value;
    }

    /**
     * Gets the value of the colChange property.
     *
     * @return possible object is     {@link Integer }
     */
    public Integer getColChange() {
        return colChange;
    }

    /**
     * Sets the value of the colChange property.
     *
     * @param value allowed object is     {@link Integer }
     */
    public void setColChange(Integer value) {
        this.colChange = value;
    }

}
