package ch.bfh.ti.projekt1.sokoban.controller;

import ch.bfh.ti.projekt1.sokoban.model.Field;

/**
 * @author svennyffenegger
 * @since 11.10.13 13:30
 */
public interface BoardService {

    BoardDimension getBoardDimension(Field field);

}
