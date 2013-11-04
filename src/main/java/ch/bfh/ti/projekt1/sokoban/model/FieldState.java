package ch.bfh.ti.projekt1.sokoban.model;

import ch.bfh.ti.projekt1.sokoban.xml.FieldType;

/**
 * @author svennyffenegger
 * @since 11.10.13 13:32
 *        <p/>
 *        All the possible states for a field on the board
 */
public enum FieldState {
    EMPTY, PLAYER, DIAMOND, GOAL, COMPLETED, WALL, FLOOR;

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

    /**
     * Helper Method for converting Model Objects to XML Objects
     *
     * @param state
     * @return
     */
    public static FieldType convertToXMLFieldType(FieldState state) {
        switch (state) {
            case EMPTY:
                return FieldType.EMPTY;
            case WALL:
                return FieldType.WALL;
            case DIAMOND:
                return FieldType.DIAMOND;
            case PLAYER:
                return FieldType.PLAYER;
            case COMPLETED:
                return FieldType.COMPLETED;
            case GOAL:
                return FieldType.GOAL;
            default:
                return FieldType.EMPTY;
        }
    }
}
