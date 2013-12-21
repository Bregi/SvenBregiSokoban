package ch.bfh.ti.projekt1.sokoban.core;

import ch.bfh.ti.projekt1.sokoban.model.Board;
import ch.bfh.ti.projekt1.sokoban.xml.Level;
import ch.bfh.ti.projekt1.sokoban.xml.Row;

import java.io.File;
import java.util.List;

/**
 * Provides services for reading and writing to xml
 *
 * @author svennyffenegger
 * @since 04/11/13 19:22
 */
public interface XmlService {

    Level getLevelFromFile(File path);

    Level getLevelFromPath(String path);

    /**
     * @param existingLevel
     * @param columns
     * @param rows
     * @return
     */
    Level changeLevelDimension(Level existingLevel, int columns, int rows);

    void saveLevel(Board board) throws LevelMisconfigurationException;

    int getMaxColumnCount(List<Row> list);
}
