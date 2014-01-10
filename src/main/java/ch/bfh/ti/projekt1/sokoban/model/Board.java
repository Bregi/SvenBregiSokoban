package ch.bfh.ti.projekt1.sokoban.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import ch.bfh.ti.projekt1.sokoban.controller.AbstractController;
import ch.bfh.ti.projekt1.sokoban.core.dijkstra.Dijkstra;
import ch.bfh.ti.projekt1.sokoban.core.dijkstra.Vertex;

/**
 * @author svennyffenegger
 * @since 11.10.13 13:32
 *        <p/>
 *        Model for Game Board. It contains a grid with the fields and the
 *        current position of the player.
 */
public class Board extends AbstractModel {
	private static final Logger LOG = Logger.getLogger(Board.class);

	// the current position of the player
	private Position position;
	// grid of the fields
	private Field[][] grid;
	private String levelName;

	private ArrayList<String> playerPath;

	private List<Direction> moves;
	private String uuid;
	private int diamondMoveCounter;

	public int getDiamondMoveCounter() {
		return diamondMoveCounter;
	}

	/**
	 * Sets the number of times a diamond has been moved
	 * @param diamondMoveCounter
	 */
	public void setDiamondMoveCounter(int diamondMoveCounter) {
		this.diamondMoveCounter = diamondMoveCounter;
	}

	/**
	 * gets the board Uuid
	 * @return int
	 */
	public String getUuid() {
		return uuid;
	}

	/**
	 * Sets the boards Uuid
	 * @param uuid
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	/**
	 * Used for the editor
	 * @param width
	 * @param height
	 */
	public Board(int width, int height) {
		this(width, height, null);
	}

	/**
	 * Used for the game
	 * @param width
	 * @param height
	 * @param startPosition
	 */
	public Board(int width, int height, Position startPosition) {
		this.position = startPosition;
		this.playerPath = new ArrayList<String>();
		diamondMoveCounter = 0;
		grid = new Field[width][height];
		moves = new LinkedList<Direction>();
	}

	/**
	 * The levelName
	 * @return String
	 */
	public String getLevelName() {
		return levelName;
	}

	/**
	 * Set the levelName
	 * @param levelName
	 */
	public void setLevelName(String levelName) {
		this.levelName = levelName;
		
		// If it has been changed, notify the listener
		firePropertyChange(AbstractController.PROPERTY_LEVEL_NAME,
				this.levelName, this.levelName);
	}

	/**
	 * Returns the Path the player has made so far.
	 * 
	 * @return String
	 */
	public String getPlayerPath() {
		return playerPath.toString();
	}

	/**
	 * Get the field
	 * @return Field[][]
	 */
	public Field[][] getGrid() {
		return grid;
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
		if (field.getState() == FieldState.PLAYER
				&& doesFieldContainType(FieldState.PLAYER)) {
			throw new IllegalArgumentException(
					"Board can only have one player!");
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
	 * Gets the next row
	 * @param currentPos
	 * @param height
	 * @return int
	 */
	public int getIndexOfNextRow(int currentPos, int height) {
		return currentPos + height;
	}

	/**
	 * Walks the distance with the shortest available path (using the dijkstra
	 * algorithm)
	 * 
	 * @param positionWalkTo
	 */
	public void setWalk(Position positionWalkTo) {

		// New dijkstra algorithm with the given grid from the level
		Dijkstra findShortestPath = new Dijkstra(grid);

		// Find the shortest path
		List<Vertex> path = findShortestPath.getPath(this.position,
				positionWalkTo);

		// führt die aktionen in einem eigenen thread aus
		// der thread macht nach jedem vertex element x eine pause
		// hier wird der thread effektiv erzeugt und gestartet
		new Thread(new WalkRunnable(path)).start();

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
			if (isMoveAllowed(direction)) {

				playerPath.add("DOWN");

				// Player moves a diamond
				if ((grid[position.getX()][position.getY() + 1].getState() == FieldState.DIAMOND)) {
					if ((grid[position.getX()][position.getY() + 2].getState() == FieldState.GOAL)) {
						grid[position.x][position.y + 2]
								.setState(FieldState.COMPLETED);
					} else {
						grid[position.x][position.y + 2]
								.setState(FieldState.DIAMOND);
					}
					diamondMoveCounter++;
					// Player moves a diamond which is alredy on a goal
				} else if ((grid[position.getX()][position.getY() + 1]
						.getState() == FieldState.COMPLETED)) {

					if(grid[position.x][position.y+2].getState() == FieldState.GOAL){
					grid[position.x][position.y + 2]
							.setState(FieldState.COMPLETED);
					}else{
						grid[position.x][position.y + 2]
								.setState(FieldState.DIAMOND);
					}
					grid[position.x][position.y + 1]
							.setState(FieldState.PLAYER_ON_GOAL);
					diamondMoveCounter++;
					// Player enters  a goal
				} else if ((grid[position.getX()][position.getY() + 1]
						.getState() == FieldState.GOAL)) {
					grid[position.x][position.y + 1]
							.setState(FieldState.PLAYER_ON_GOAL);
				}
				if (grid[oldPosition.x][oldPosition.y].getState() == FieldState.PLAYER_ON_GOAL) {
					grid[oldPosition.x][oldPosition.y]
							.setState(FieldState.GOAL);
				} else {
					grid[oldPosition.x][oldPosition.y]
							.setState(FieldState.EMPTY);
				}
				if (grid[position.x][position.y + 1].getState() != FieldState.PLAYER_ON_GOAL) {
					grid[position.x][position.y + 1]
							.setState(FieldState.PLAYER);
				}
				this.position = new Position(position.getX(),
						position.getY() + 1);
			}
			break;
		case UP:
			if (isMoveAllowed(direction)) {
				playerPath.add("UP");
				// Player moves a diamond
				if ((grid[position.getX()][position.getY() - 1].getState() == FieldState.DIAMOND)) {
					if ((grid[position.getX()][position.getY() - 2].getState() == FieldState.GOAL)) {
						grid[position.x][position.y - 2]
								.setState(FieldState.COMPLETED);
					} else {
						grid[position.x][position.y - 2]
								.setState(FieldState.DIAMOND);
					}
					diamondMoveCounter++;
				} else if ((grid[position.getX()][position.getY() - 1]
						.getState() == FieldState.COMPLETED)) {

					if(grid[position.x][position.y-2].getState() == FieldState.GOAL){
						grid[position.x][position.y -2]
								.setState(FieldState.COMPLETED);
						}else{
							grid[position.x][position.y - 2]
									.setState(FieldState.DIAMOND);
						}
					grid[position.x][position.y - 1]
							.setState(FieldState.PLAYER_ON_GOAL);
					diamondMoveCounter++;
				} else if ((grid[position.getX()][position.getY() - 1]
						.getState() == FieldState.GOAL)) {
					grid[position.x][position.y - 1]
							.setState(FieldState.PLAYER_ON_GOAL);
				}
				if (grid[oldPosition.x][oldPosition.y].getState() == FieldState.PLAYER_ON_GOAL) {
					grid[oldPosition.x][oldPosition.y]
							.setState(FieldState.GOAL);
				} else {
					grid[oldPosition.x][oldPosition.y]
							.setState(FieldState.EMPTY);
				}
				if (grid[position.x][position.y - 1].getState() != FieldState.PLAYER_ON_GOAL) {
					grid[position.x][position.y - 1]
							.setState(FieldState.PLAYER);
				}
				this.position = new Position(position.getX(),
						position.getY() - 1);
			}
			break;
		case LEFT:
			if (isMoveAllowed(direction)) {
				playerPath.add("LEFT");
				// Player moves a diamond
				if ((grid[position.getX() - 1][position.getY()].getState() == FieldState.DIAMOND)) {
					if ((grid[position.getX() - 2][position.getY()].getState() == FieldState.GOAL)) {
						grid[position.x - 2][position.y]
								.setState(FieldState.COMPLETED);
					} else {
						grid[position.x - 2][position.y]
								.setState(FieldState.DIAMOND);
					}
					diamondMoveCounter++;
				} else if ((grid[position.getX() - 1][position.getY()]
						.getState() == FieldState.COMPLETED)) {

					if(grid[position.x-2][position.y].getState() == FieldState.GOAL){
					grid[position.x-2][position.y]
							.setState(FieldState.COMPLETED);
					}else{
						grid[position.x-2][position.y]
								.setState(FieldState.DIAMOND);
					}
					grid[position.x-1][position.y ]
							.setState(FieldState.PLAYER_ON_GOAL);
					diamondMoveCounter++;
				} else if ((grid[position.getX() - 1][position.getY()]
						.getState() == FieldState.GOAL)) {
					grid[position.x - 1][position.y]
							.setState(FieldState.PLAYER_ON_GOAL);
				}
				if (grid[oldPosition.x][oldPosition.y].getState() == FieldState.PLAYER_ON_GOAL) {
					grid[oldPosition.x][oldPosition.y]
							.setState(FieldState.GOAL);
				} else {
					grid[oldPosition.x][oldPosition.y]
							.setState(FieldState.EMPTY);
				}
				if (grid[position.x - 1][position.y].getState() != FieldState.PLAYER_ON_GOAL) {
					grid[position.x - 1][position.y]
							.setState(FieldState.PLAYER);
				}
				this.position = new Position(position.getX() - 1,
						position.getY());
			}
			break;
		case RIGHT:
			if (isMoveAllowed(direction)) {
				this.playerPath.add("RIGHT");
				// Player moves a diamond
				if ((grid[position.getX() + 1][position.getY()].getState() == FieldState.DIAMOND)) {
					if ((grid[position.getX() + 2][position.getY()].getState() == FieldState.GOAL)) {
						grid[position.x + 2][position.y]
								.setState(FieldState.COMPLETED);
					} else {
						grid[position.x + 2][position.y]
								.setState(FieldState.DIAMOND);
					}
					diamondMoveCounter++;
				} else if ((grid[position.getX() + 1][position.getY()]
						.getState() == FieldState.COMPLETED)) {

					if(grid[position.x+2][position.y].getState() == FieldState.GOAL){
					grid[position.x+ 2][position.y ]
							.setState(FieldState.COMPLETED);
					}else{
						grid[position.x+ 2][position.y ]
								.setState(FieldState.DIAMOND);
					}
					grid[position.x+ 1][position.y ]
							.setState(FieldState.PLAYER_ON_GOAL);
					diamondMoveCounter++;
				} else if ((grid[position.getX() + 1][position.getY()]
						.getState() == FieldState.GOAL)) {
					grid[position.x + 1][position.y]
							.setState(FieldState.PLAYER_ON_GOAL);
				}
				if (grid[oldPosition.x][oldPosition.y].getState() == FieldState.PLAYER_ON_GOAL) {
					grid[oldPosition.x][oldPosition.y]
							.setState(FieldState.GOAL);
				} else {
					grid[oldPosition.x][oldPosition.y]
							.setState(FieldState.EMPTY);
				}
				if (grid[position.x + 1][position.y].getState() != FieldState.PLAYER_ON_GOAL) {
					grid[position.x + 1][position.y]
							.setState(FieldState.PLAYER);
				}
				this.position = new Position(position.getX() + 1,
						position.getY());
			}
			break;
		}

		// check if the position has been changed and a property change needs to
		// be fired
		if (oldPosition != position) {
			checkLevelStatus();
			moves.add(direction);
			firePropertyChange(AbstractController.PROPERTY_POSITION,
					oldPosition, position);
		}
	}

	/**
	 * Checks if the Level is finished
	 */
	public void checkLevelStatus() {
		boolean finished = true;
		for (int i = 0; i < grid.length; i++) {
			for (int n = 0; n < grid[0].length; n++) {
				if ((grid[i][n].getState() == FieldState.GOAL)
						|| (grid[i][n].getState() == FieldState.PLAYER_ON_GOAL)) {
					finished = false;
				}
			}
		}
		// If there are no goals left, notifiy the view.
		if (finished) {
			firePropertyChange(AbstractController.PROPERTY_LEVEL_SCORE, 0,
					diamondMoveCounter);
			firePropertyChange(AbstractController.PROPERTY_LEVEL_STATUS, false,
					true);
		}
	}

	/**
	 * Checks a field if it has a certain state
	 * @param state
	 * @return boolean
	 */
	private boolean doesFieldContainType(FieldState state) {
		for (Field[] arr : grid) {
			for (Field field : arr) {

				if ((field != null) && (state == field.getState())) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Checks if a move the player intends to do is allowed
	 * @param direction
	 * @return boolean
	 */
	private boolean isMoveAllowed(Direction direction) {
		if (isMoveInsideBorders(direction) == false) {
			return false;
		}

		switch (direction) {
		case DOWN:
			return (grid[position.getX()][position.getY() + 1].getState() == FieldState.GOAL)
					|| ((grid[position.getX()][position.getY() + 1].getState() == FieldState.COMPLETED) && (grid[position
							.getX()][position.getY() + 2].getState() == FieldState.GOAL))
					|| (((grid[position.getX()][position.getY() + 1].getState() == FieldState.COMPLETED) && (grid[position
							.getX()][position.getY() + 2].getState() == FieldState.EMPTY)) || (grid[position
							.getX()].length > position.getY() + 1 && (grid[position
							.getX()][position.getY() + 1].getState() == FieldState.EMPTY || (grid[position
							.getX()][position.getY() + 1].getState() == FieldState.DIAMOND && (grid[position
							.getX()][position.getY() + 2].getState() == FieldState.EMPTY || grid[position
							.getX()][position.getY() + 2].getState() == FieldState.GOAL)))));
		case UP:
			return (grid[position.getX()][position.getY() - 1].getState() == FieldState.GOAL)
					|| ((grid[position.getX()][position.getY() - 1].getState() == FieldState.COMPLETED) && (grid[position
							.getX()][position.getY() - 2].getState() == FieldState.GOAL))
					|| (((grid[position.getX()][position.getY() - 1].getState() == FieldState.COMPLETED) && (grid[position
							.getX()][position.getY() - 2].getState() == FieldState.EMPTY)) || (0 < position
							.getY() && (grid[position.getX()][position.getY() - 1]
							.getState() == FieldState.EMPTY || (grid[position
							.getX()][position.getY() - 1].getState() == FieldState.DIAMOND && (grid[position
							.getX()][position.getY() - 2].getState() == FieldState.EMPTY || grid[position
							.getX()][position.getY() - 2].getState() == FieldState.GOAL)))));
		case LEFT:
			return (grid[position.getX() - 1][position.getY()].getState() == FieldState.GOAL)
					|| ((grid[position.getX() - 1][position.getY()].getState() == FieldState.COMPLETED) && (grid[position
							.getX() - 2][position.getY()].getState() == FieldState.GOAL))
					|| ((grid[position.getX() - 1][position.getY()].getState() == FieldState.COMPLETED) && (grid[position
							.getX() - 2][position.getY()].getState() == FieldState.GOAL))
					|| (((grid[position.getX() - 1][position.getY()].getState() == FieldState.COMPLETED) && (grid[position
							.getX() - 2][position.getY()].getState() == FieldState.EMPTY)) || (0 < position
							.getX() && (grid[position.getX() - 1][position
							.getY()].getState() == FieldState.EMPTY || (grid[position
							.getX() - 1][position.getY()].getState() == FieldState.DIAMOND && (grid[position
							.getX() - 2][position.getY()].getState() == FieldState.EMPTY || grid[position
							.getX() - 2][position.getY()].getState() == FieldState.GOAL)))));
		case RIGHT:
			return (grid[position.getX() + 1][position.getY()].getState() == FieldState.GOAL)
					|| ((grid[position.getX() + 1][position.getY()].getState() == FieldState.COMPLETED) && (grid[position
							.getX() + 2][position.getY()].getState() == FieldState.GOAL))
					|| (((grid[position.getX() + 1][position.getY()].getState() == FieldState.COMPLETED) && (grid[position
							.getX() + 2][position.getY()].getState() == FieldState.EMPTY)) || (grid.length > position
							.getX() + 1 && (grid[position.getX() + 1][position
							.getY()].getState() == FieldState.EMPTY || (grid[position
							.getX() + 1][position.getY()].getState() == FieldState.DIAMOND && (grid[position
							.getX() + 2][position.getY()].getState() == FieldState.EMPTY || grid[position
							.getX() + 2][position.getY()].getState() == FieldState.GOAL)))));
		}
		return false;
	}

	/**
	 * Checks if the move doesn't leave the grid
	 * @param direction
	 * @return boolean
	 */
	private boolean isMoveInsideBorders(Direction direction) {
		switch (direction) {
		case UP:
			return position.y > 0;
		case DOWN:
			return position.y < grid[0].length - 1;
		case LEFT:
			return position.x > 0;
		case RIGHT:
			return position.x < grid.length - 1;
		}
		return false;
	}

	/**
	 * gets the list of directions already made in the level
	 * @return List<Direction>
	 */
	public List<Direction> getMoves() {
		return moves;
	}
	
	/**
	 * @param state
	 * @return List<Position>
	 */
	public List<Position> getPositionsOfType(FieldState state) {
		List<Position> list = new ArrayList<>();
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j].getState() == state) {
					list.add(new Position(i, j));
				}
			}
		}
		return list;
	}

	/**
	 * Thread for the dijkstra walk
	 * @author svennyffenegger
	 */
	class WalkRunnable implements Runnable {

		List<Vertex> path;

		WalkRunnable(List<Vertex> path) {
			this.path = path;
		}

		@Override
		public void run() {
			for (Vertex x : path) {
				if (x.getX() > position.getX()) {
					setNextField(Direction.RIGHT);
				} else if (x.getY() > position.getY()) {
					setNextField(Direction.DOWN);
				} else if (x.getX() < position.getX()) {
					setNextField(Direction.LEFT);
				} else if (x.getY() < position.getY()) {
					setNextField(Direction.UP);
				}

				try {
					// Waits half a second before next step
					Thread.sleep(500L);
				} catch (InterruptedException e) {
					LOG.error(e);
				}
			}
		}

	}

}
