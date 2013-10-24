package ch.bfh.ti.projekt1.sokoban.editor;

import ch.bfh.ti.projekt1.sokoban.xml.Level;

/**
 * Provides certain functionalities for the level editor
 *
 * @author svennyffenegger
 * @since 24/10/13 14:40
 */
public interface LevelService {

    /**
     * Creates a plain new level
     *
     * @return the created level
     */
    Level createPlainLevel();

    /**
     * @param columns
     * @param rows
     * @return
     */
    Level createPlainLevel(int columns, int rows);

    /**
     * @param existingLevel
     * @param columns
     * @param rows
     * @return
     */
    Level changeLevelDimension(Level existingLevel, int columns, int rows);

}
