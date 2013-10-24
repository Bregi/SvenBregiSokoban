package ch.bfh.ti.projekt1.sokoban.model;

import ch.bfh.ti.projekt1.sokoban.xml.FieldType;

/**
 * @author svennyffenegger
 * @since 11.10.13 13:32
 *        <p/>
 *        All the possible states for a field on the board
 */
public enum FieldState {
    EMPTY, PLAYER, DIAMOND, GOAL, COMPLETED, WALL;

    /**
     * Helper Method for transforming XML Enum to model enum
     *
     * @param fieldType
     * @return
     */
    public static FieldState parseXmlFieldType(FieldType fieldType) {
        switch (fieldType) {
            case COMPLETED:
                return COMPLETED;
            case DIAMOND:
                return DIAMOND;
            case EMPTY:
                return EMPTY;
            case GOAL:
                return GOAL;
            case PLAYER:
                return PLAYER;
            case WALL:
                return WALL;
            default:
                return EMPTY;
        }
    }
}
