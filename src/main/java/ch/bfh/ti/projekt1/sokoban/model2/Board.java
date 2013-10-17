package ch.bfh.ti.projekt1.sokoban.model2;


import ch.bfh.ti.projekt1.sokoban.controller2.AbstractController;

public class Board extends AbstractModel {

    private Position position;
    private Field[][] grid;

    public Board(int width, int height, Position startPosition) {
        this.position = startPosition;
        grid = new Field[width][height];
    }

    public void setField(int xPos, int yPos, Field field) throws IllegalArgumentException {
        if (xPos < 0 || yPos < 0) {
            throw new IllegalArgumentException("Parameters given are invalid!");
        }

        if (xPos >= grid.length || yPos >= grid[0].length) {
            throw new IllegalArgumentException("Position out of bounds!");
        }

        grid[xPos][yPos] = field;

    }

    private void setPosition(int x, int y) {

        firePropertyChange(AbstractController.PROPERTY_POSITION, null, this);
    }

    public Position getPosition() {
        return position;
    }

    public void setNextField(Direction direction) {
        System.out.println("Try goto:" + direction.name());
        Position oldPosition = position;

        switch (direction) {
            case DOWN:
                if (grid[position.getX()].length > position.getY() + 1 && grid[position.getX()][position.getY() + 1] != null) {
                    this.position = new Position(position.getX(), position.getY() + 1);
                }
                break;
            case UP:
                if (0 < position.getY() && grid[position.getX()][position.getY() - 1] != null) {
                    this.position = new Position(position.getX(), position.getY() - 1);
                }
                break;
            case LEFT:
                if (0 < position.getX() && grid[position.getX() - 1][position.getY()] != null) {
                    this.position = new Position(position.getX() - 1, position.getY());
                }
                break;
            case RIGHT:
                if (grid.length > position.getX() + 1 && grid[position.getX() + 1][position.getY()] != null) {
                    this.position = new Position(position.getX() + 1, position.getY());
                }
                break;
        }

        if (oldPosition != position) {
            firePropertyChange(AbstractController.PROPERTY_POSITION, oldPosition, position);
        }
    }
}
