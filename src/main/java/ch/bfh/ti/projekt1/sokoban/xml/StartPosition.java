//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.10.24 at 11:25:34 AM CEST 
//


package ch.bfh.ti.projekt1.sokoban.xml;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for startPosition complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="startPosition">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *       &lt;attribute name="row" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="column" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "startPosition", propOrder = {
        "value"
})
public class StartPosition {

    @XmlValue
    protected String value;
    @XmlAttribute(name = "row")
    protected Integer row;
    @XmlAttribute(name = "column")
    protected Integer column;

    /**
     * Gets the value of the value property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Gets the value of the row property.
     *
     * @return possible object is
     *         {@link Integer }
     */
    public Integer getRow() {
        return row;
    }

    /**
     * Sets the value of the row property.
     *
     * @param value allowed object is
     *              {@link Integer }
     */
    public void setRow(Integer value) {
        this.row = value;
    }

    /**
     * Gets the value of the column property.
     *
     * @return possible object is
     *         {@link Integer }
     */
    public Integer getColumn() {
        return column;
    }

    /**
     * Sets the value of the column property.
     *
     * @param value allowed object is
     *              {@link Integer }
     */
    public void setColumn(Integer value) {
        this.column = value;
    }

}
