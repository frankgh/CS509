
package com.wordsweeper.core.xml;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element ref="{}gameBrief" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * This class specified list game respond from the server side.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "gameBrief"
})
@XmlRootElement(name = "listGamesResponse")
public class ListGamesResponse {

    /**
     * The Game brief.
     */
    protected List<GameBrief> gameBrief;

    /**
     * Gets the value of the gameBrief property.
     * <p>
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the gameBrief property.
     * <p>
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGameBrief().add(newItem);
     * </pre>
     * <p>
     * <p>
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GameBrief }
     *
     * @return the game brief
     */
    public List<GameBrief> getGameBrief() {
        if (gameBrief == null) {
            gameBrief = new ArrayList<GameBrief>();
        }
        return this.gameBrief;
    }

}