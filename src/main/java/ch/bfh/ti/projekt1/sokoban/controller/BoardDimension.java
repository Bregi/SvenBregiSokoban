package ch.bfh.ti.projekt1.sokoban.controller;

/**
 * Handels the dimension of the board
 * 
 * @author svennyffenegger
 * @since 11.10.13 13:32
 */
public class BoardDimension {
    int width, height, horizontalCount, verticalCount;

    /**
     * @return int
     */
    public int getWidth() {
        return width;
    }

    /**
     * @param width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * @return int
     */
    public int getHeight() {
        return height;
    }

    /**
     * @param height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * @return int
     */
    public int getHorizontalCount() {
        return horizontalCount;
    }

    /**
     * @param horizontalCount
     */
    public void setHorizontalCount(int horizontalCount) {
        this.horizontalCount = horizontalCount;
    }

    /**
     * @return int
     */
    public int getVerticalCount() {
        return verticalCount;
    }

    /**
     * @param verticalCount
     */
    public void setVerticalCount(int verticalCount) {
        this.verticalCount = verticalCount;
    }
}
