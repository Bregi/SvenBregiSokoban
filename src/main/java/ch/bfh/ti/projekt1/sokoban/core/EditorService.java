package ch.bfh.ti.projekt1.sokoban.core;

import ch.bfh.ti.projekt1.sokoban.editor.EditorController;
import ch.bfh.ti.projekt1.sokoban.model.Board;

import java.io.File;

/**
 * Provides certain functionalities for the level editor
 *
 * @author svennyffenegger
 * @since 24/10/13 14:40
 */
public interface EditorService {

    /**
     * @param columns
     * @param rows
     * @return
     */
    EditorController getNewLevel(int columns, int rows);

    EditorController getLevel(File file);

    void saveLevel(Board board);

}
