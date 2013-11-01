package ch.bfh.ti.projekt1.sokoban.editor;

import ch.bfh.ti.projekt1.sokoban.model.Board;
import ch.bfh.ti.projekt1.sokoban.model.Field;
import ch.bfh.ti.projekt1.sokoban.model.FieldState;
import ch.bfh.ti.projekt1.sokoban.xml.Column;
import ch.bfh.ti.projekt1.sokoban.xml.Level;
import ch.bfh.ti.projekt1.sokoban.xml.Row;
import ch.bfh.ti.projekt1.sokoban.xml.StartPosition;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author svennyffenegger
 * @since 31/10/13 20:41
 */
public class LevelServiceImpl implements LevelService {
    @Override
    public Level createPlainLevel() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Level createPlainLevel(int columns, int rows) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Level changeLevelDimension(Level existingLevel, int columns, int rows) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Level saveLevel(Board board) {
        Level level = new Level();

        Field[][] grid = board.getGrid();

        level.setName("Level" + board.getLevelName());

        //TODO
        StartPosition startPosition = new StartPosition();
        startPosition.setColumn(0);
        startPosition.setRow(0);

        level.setStartPosition(startPosition);

        //TODO ist noch verkehrt rum
        for (int i = 0; i < grid.length; i++) {
            Row row = new Row();
            row.setId(i);
            level.getRow().add(row);

            for (int j = 0; j < grid[i].length; j++) {
                Column column = new Column();
                column.setId(j);
                column.setType(FieldState.convertToXMLFieldType(grid[i][j].getState()));
                row.getColumn().add(column);
            }
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");


        File file = new File("src/test/resources/ch/bfh/ti/projekt1/sokoban/generated/" + simpleDateFormat.format(new Date()) + ".xml");
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Level.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jaxbMarshaller.marshal(level, file);
        } catch (JAXBException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }


        return level;  //To change body of implemented methods use File | Settings | File Templates.
    }


}
