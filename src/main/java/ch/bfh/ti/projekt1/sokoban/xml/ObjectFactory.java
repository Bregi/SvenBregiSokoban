//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.10.23 at 09:43:09 AM CEST 
//


package ch.bfh.ti.projekt1.sokoban.xml;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ch.bfh.ti.projekt1.sokoban.xml package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Level_QNAME = new QName("http://bfh.ch", "level");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ch.bfh.ti.projekt1.sokoban.xml
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link LevelType }
     * 
     */
    public LevelType createLevelType() {
        return new LevelType();
    }

    /**
     * Create an instance of {@link RowType }
     * 
     */
    public RowType createRowType() {
        return new RowType();
    }

    /**
     * Create an instance of {@link ColumnType }
     * 
     */
    public ColumnType createColumnType() {
        return new ColumnType();
    }

    /**
     * Create an instance of {@link StartPositionType }
     * 
     */
    public StartPositionType createStartPositionType() {
        return new StartPositionType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LevelType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://bfh.ch", name = "level")
    public JAXBElement<LevelType> createLevel(LevelType value) {
        return new JAXBElement<LevelType>(_Level_QNAME, LevelType.class, null, value);
    }

}
