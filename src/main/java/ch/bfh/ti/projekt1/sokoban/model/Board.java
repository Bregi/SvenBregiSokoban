package ch.bfh.ti.projekt1.sokoban.model;


import ch.bfh.ti.projekt1.sokoban.controller.AbstractController;

/**
 * @author svennyffenegger
 * @since 11.10.13 13:32
 *        <p/>
 *        Model for Game Board. It contains a grid with the fields and the current position of the player.
 */
public class Board extends AbstractModel {

    // the current position of the player
    private Position position;
    // grid of the fields
    private Field[][] grid;

    public Board(int width, int height, Position startPosition) {
        this.position = startPosition;
        grid = new Field[width][height];
    }

    /**
     * @param xPos  horizontal position of the field
     * @param yPos  vertical position of the field on the board
     * @param field
     * @throws IllegalArgumentException is thrown when the position is outside the board.
     */
    public void setField(int xPos, int yPos, Field field) throws IllegalArgumentException {
        if (xPos < 0 || yPos < 0) {
            throw new IllegalArgumentException("Parameters given are invalid!");
        }

        if (xPos >= grid.length || yPos >= grid[0].length) {
            throw new IllegalArgumentException("Position out of bounds!");
        }

        Field oldField = grid[xPos][yPos];

        grid[xPos][yPos] = field;

        // the view gets notified about the new field on this position
        firePropertyChange(AbstractController.PROPERTY_FIELD, oldField, grid[xPos][yPos]);
    }

    /**
     * @return the current position of the player
     */
    public Position getPosition() {
        return position;
    }

    /**
     * tries to move the player in a direction (up, down, left or right). the change on the model is only done,
     * if it is a valid move.
     *
     * @param direction
     */
    public void setNextField(Direction direction) {
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

        // check if the position has been changed and a property change needs to be fired
        if (oldPosition != position) {
            firePropertyChange(AbstractController.PROPERTY_POSITION, oldPosition, position);
        }
    }
}
