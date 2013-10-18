package ch.bfh.ti.projekt1.sokoban.controller;

import ch.bfh.ti.projekt1.sokoban.model.Field;

/**
 * @author svennyffenegger
 * @since 11.10.13 14:10
 */
public class BoardServiceImpl implements BoardService {

    @Override
    public BoardDimension getBoardDimension(Field field) {
        //TODO: implement

        BoardDimension dim = new BoardDimension();
        dim.setHeight(5 * 100);
        dim.setWidth(4 * 100);
        dim.setHorizontalCount(4);
        dim.setVerticalCount(5);
        return dim;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
