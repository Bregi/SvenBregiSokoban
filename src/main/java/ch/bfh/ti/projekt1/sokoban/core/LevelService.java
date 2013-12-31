package ch.bfh.ti.projekt1.sokoban.core;

import ch.bfh.ti.projekt1.sokoban.controller.BoardController;
import ch.bfh.ti.projekt1.sokoban.model.Board;
import ch.bfh.ti.projekt1.sokoban.model.Direction;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * @author svennyffenegger
 * @since 04/11/13 21:16
 */
public interface LevelService {

    public BoardController getLevel(File file);

    public void saveLevelMoves(List<Direction> directionList, String levelName);

    public Map<String, String> getLevelNameUUIDMap();
    
    public List<String> getProfiles();
    
    public void saveLevelProgress(Board board, String player) throws LevelMisconfigurationException;
}
