package ch.bfh.ti.projekt1.sokoban.model2;

/**
 * @author svennyffenegger
 * @since 11.10.13 16:12
 */
public class Position {
    int y, x;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public String toString() {
        return "Position{" +
                "y=" + y +
                ", x=" + x +
                '}';
    }
}
