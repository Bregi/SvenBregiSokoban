package ch.bfh.ti.projekt1.sokoban.model;

import ch.bfh.ti.projekt1.sokoban.controller.AbstractController;

/**
 * @author svennyffenegger
 * @since 11.10.13 13:32
 *        <p/>
 *        Model for Game Board. It contains a grid with the fields and the
 *        current position of the player.
 */
public class Board extends AbstractModel {

	// the current position of the player
	private Position position;
	private Position oldDiamondPosition;
	private Position diamondPosition;

	// grid of the fields
	private Field[][] grid;
	private String levelName;
	private boolean diamondMove;

	// used for the editor mainly
	public Board(int width, int height) {
		this(width, height, null);
	}

	// used for the game
	public Board(int width, int height, Position startPosition) {
		this.position = startPosition;
		this.diamondMove = false;
		grid = new Field[width][height];
	}

	public String getLevelName() {
		return levelName;
	}

	public Field[][] getGrid() {
		return grid;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
		firePropertyChange(AbstractController.PROPERTY_LEVEL_NAME,
				this.levelName, this.levelName);
	}

	/**
	 * @param xPos
	 *            horizontal position of the field
	 * @param yPos
	 *            vertical position of the field on the board
	 * @param field
	 * @throws IllegalArgumentException
	 *             is thrown when the position is outside the board.
	 */
	public void setField(int xPos, int yPos, Field field)
			throws IllegalArgumentException {
		if (xPos < 0 || yPos < 0) {
			throw new IllegalArgumentException("Parameters given are invalid!");
		}

		if (xPos >= grid.length || yPos >= grid[0].length) {
			throw new IllegalArgumentException("Position out of bounds!");
		}

        /**
         * If the board already has a player, then a exception is thrown
         */
        if (field.getState() == FieldState.PLAYER && doesFieldContainType(FieldState.PLAYER)) {
            throw new IllegalArgumentException("Board can only have one player!");
        }


        Field oldField = grid[xPos][yPos];

		grid[xPos][yPos] = field;

		// the view gets notified about the new field on this position
		firePropertyChange(AbstractController.PROPERTY_FIELD, oldField,
				grid[xPos][yPos]);
	}

	/**
	 * @return the current position of the player
	 */
	public Position getPosition() {
		return position;
	}

	/**
	 * tries to move the player in a direction (up, down, left or right). the
	 * change on the model is only done, if it is a valid move.
	 * 
	 * @param direction
	 */
	public void setNextField(Direction direction) {
		Position oldPosition = position;

		switch (direction) {
		case DOWN:

			if (((grid[position.getX()][position.getY() + 1].getState() == FieldState.COMPLETED) && (grid[position
					.getX()][position.getY() + 2].getState() == FieldState.EMPTY))
					|| (grid[position.getX()].length > position.getY() + 1 && (grid[position
							.getX()][position.getY() + 1].getState() == FieldState.EMPTY || (grid[position
							.getX()][position.getY() + 1].getState() == FieldState.DIAMOND && (grid[position
							.getX()][position.getY() + 2].getState() == FieldState.EMPTY || grid[position
							.getX()][position.getY() + 2].getState() == FieldState.GOAL))))) {

				// PLAYER MOVES A DIAMOND
				if ((grid[position.getX()][position.getY() + 1].getState() == FieldState.DIAMOND)
						|| (grid[position.getX()][position.getY() + 1]
								.getState() == FieldState.COMPLETED)) {
					this.oldDiamondPosition = new Position(position.getX(),
							position.getY() + 1);
					this.diamondPosition = new Position(position.getX(),
							position.getY() + 2);
					diamondMove = true;
				}
				this.position = new Position(position.getX(),
						position.getY() + 1);
			}
			break;
		case UP:
			if (((grid[position.getX()][position.getY() - 1].getState() == FieldState.COMPLETED) && (grid[position
					.getX()][position.getY() - 2].getState() == FieldState.EMPTY))
					|| (0 < position.getY() && (grid[position.getX()][position
							.getY() - 1].getState() == FieldState.EMPTY || (grid[position
							.getX()][position.getY() - 1].getState() == FieldState.DIAMOND && (grid[position
							.getX()][position.getY() - 2].getState() == FieldState.EMPTY || grid[position
							.getX()][position.getY() - 2].getState() == FieldState.GOAL))))) {
				if (grid[position.getX()][position.getY() - 1].getState() == FieldState.DIAMOND) {
					this.oldDiamondPosition = new Position(position.getX(),
							position.getY() - 1);
					this.diamondPosition = new Position(position.getX(),
							position.getY() - 2);
					diamondMove = true;
				}
				this.position = new Position(position.getX(),
						position.getY() - 1);
			}
			break;
		case LEFT:
			if (((grid[position.getX() - 1][position.getY()].getState() == FieldState.COMPLETED) && (grid[position
					.getX() - 2][position.getY()].getState() == FieldState.EMPTY))
					|| (0 < position.getX() && (grid[position.getX() - 1][position
							.getY()].getState() == FieldState.EMPTY || (grid[position
							.getX() - 1][position.getY()].getState() == FieldState.DIAMOND && (grid[position
							.getX() - 2][position.getY()].getState() == FieldState.EMPTY || grid[position
							.getX() - 2][position.getY()].getState() == FieldState.GOAL))))) {
				if (grid[position.getX() - 1][position.getY()].getState() == FieldState.DIAMOND) {
					this.oldDiamondPosition = new Position(position.getX() - 1,
							position.getY());
					this.diamondPosition = new Position(position.getX() - 2,
							position.getY());
					diamondMove = true;
				}
				this.position = new Position(position.getX() - 1,
						position.getY());
			}
			break;
		case RIGHT:
			if (((grid[position.getX() + 1][position.getY()].getState() == FieldState.COMPLETED) && (grid[position
					.getX() + 2][position.getY()].getState() == FieldState.EMPTY))
					|| (grid.length > position.getX() + 1 && (grid[position
							.getX() + 1][position.getY()].getState() == FieldState.EMPTY || (grid[position
							.getX() + 1][position.getY()].getState() == FieldState.DIAMOND && (grid[position
							.getX() + 2][position.getY()].getState() == FieldState.EMPTY || grid[position
							.getX() + 2][position.getY()].getState() == FieldState.GOAL))))) {
				if (grid[position.getX() + 1][position.getY()].getState() == FieldState.DIAMOND) {
					this.oldDiamondPosition = new Position(position.getX() + 1,
							position.getY());
					this.diamondPosition = new Position(position.getX() + 2,
							position.getY());
					diamondMove = true;
				}
				this.position = new Position(position.getX() + 1,
						position.getY());
			}
			break;
		}

		// check if the position has been changed and a property change needs to
		// be fired
		if (oldPosition != position) {
			// move the diamond also if diamond was in the way
			if (diamondMove == true) {
				if (grid[diamondPosition.getX()][diamondPosition.getY()]
						.getState() == FieldState.GOAL) {
					grid[diamondPosition.getX()][diamondPosition.getY()]
							.setState(FieldState.COMPLETED);
				} else {
					grid[diamondPosition.getX()][diamondPosition.getY()]
							.setState(FieldState.DIAMOND);
				}
			}
			diamondMove = false;

			firePropertyChange(AbstractController.PROPERTY_POSITION,
					oldPosition, position);
		}
	}

    private boolean doesFieldContainType(FieldState state) {
        for (Field[] arr : grid) {
            for (Field field : arr) {
                if (state == field.getState()) {
                    return true;
                }
            }
        }
        return false;
    }
}
