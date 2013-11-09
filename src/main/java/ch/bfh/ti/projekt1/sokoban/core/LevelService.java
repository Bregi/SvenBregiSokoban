package ch.bfh.ti.projekt1.sokoban.core;

import ch.bfh.ti.projekt1.sokoban.controller.BoardController;

import java.io.File;

/**
 * @author svennyffenegger
 * @since 04/11/13 21:16
 */
public interface LevelService {

    public BoardController getLevel(File file);

}
