package ch.bfh.ti.projekt1.sokoban.core;

import ch.bfh.ti.projekt1.sokoban.model.Board;
import ch.bfh.ti.projekt1.sokoban.model.Field;
import ch.bfh.ti.projekt1.sokoban.model.FieldState;
import ch.bfh.ti.projekt1.sokoban.xml.Column;
import ch.bfh.ti.projekt1.sokoban.xml.Level;
import ch.bfh.ti.projekt1.sokoban.xml.Row;
import ch.bfh.ti.projekt1.sokoban.xml.StartPosition;

import org.apache.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author svennyffenegger
 * @since 04/11/13 19:22
 */
public class XmlServiceImpl implements XmlService {

    private static final Logger LOG = Logger.getLogger(XmlServiceImpl.class);

    @Override
    public Level getLevelFromFile(File path) {
        if (path.exists()) {
            try {
                JAXBContext jaxbContext = JAXBContext.newInstance(Level.class);
                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                Level level = (Level) jaxbUnmarshaller.unmarshal(path);
                LOG.debug("Successfully read level from Xml:" + path.getName());

                return level;
            } catch (JAXBException e) {
                LOG.error(e);
                return null;
            }
        } else {
            LOG.error("The requested file doesn't exist: " + path);
            return null;
        }
    }

    @Override
    public Level getLevelFromPath(String path) {
        return getLevelFromFile(new File(path));
    }

    @Override
    public Level changeLevelDimension(Level existingLevel, int columns, int rows) {
        return null;
    }

    @Override
    public void saveLevel(Board board) throws LevelMisconfigurationException {
        Level level = new Level();

        Field[][] grid = board.getGrid();

        if (board.getUuid() == null || board.getUuid().isEmpty()) {
        	board.setUuid(UUID.randomUUID().toString());
        }
        
        level.setUuid(board.getUuid());
        level.setName("Level" + board.getLevelName());

        for (int i = 0; i < grid[0].length; i++) {
            Row row = new Row();
            row.setId(i);
            level.getRow().add(row);
            
        	for (int j = 0; j < grid.length; j++) {
        		Column column = new Column();
                column.setId(j);
                column.setType(FieldState.convertToXMLFieldType(grid[j][i].getState()));
                row.getColumn().add(column);
                if (grid[j][i].getState() == FieldState.PLAYER) {
                	StartPosition startPosition = new StartPosition();
                	startPosition.setColumn(j);
                	startPosition.setRow(i);
                    level.setStartPosition(startPosition);
                }
        	}
        }
        
        if (level.getStartPosition() == null) {
        	throw new LevelMisconfigurationException("No Startposition defined!");
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");


        File file = new File(CoreConstants.getProperty("editor.basepath") + simpleDateFormat.format(new Date()) + ".xml");
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Level.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jaxbMarshaller.marshal(level, file);
        } catch (JAXBException e) {
            LOG.error(e);
        }

    }


    @Override
    public int getMaxColumnCount(List<Row> list) {
        int count = 0;
        for (Row rowType : list) {
            if (rowType.getColumn().size() > count) {
                count = rowType.getColumn().size();
            }
        }
        return count;
    }
}
