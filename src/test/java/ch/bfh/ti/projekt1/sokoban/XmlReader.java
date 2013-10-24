package ch.bfh.ti.projekt1.sokoban;

import ch.bfh.ti.projekt1.sokoban.xml.LevelType;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: svennyffenegger
 * Date: 10/23/13
 * Time: 9:49 AM
 * To change this template use File | Settings | File Templates.
 */
public class XmlReader {

    public static void main(String[] args) {

        try {

            File file = new File("src/test/resources/ch/bfh/ti/projekt1/sokoban/level1.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(LevelType.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            LevelType level = (LevelType) jaxbUnmarshaller.unmarshal(file);
            System.out.println(level);

        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }
}
