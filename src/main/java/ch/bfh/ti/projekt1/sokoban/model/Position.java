package ch.bfh.ti.projekt1.sokoban.model;

/**
 * @author svennyffenegger
 * @since 11.10.13 16:12
 *        <p/>
 *        represents the position
 */
public class Position {
	int y, x;

	/**
	 * @param x
	 * @param y
	 */
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Gets the y pos.
	 * 
	 * @return int
	 */
	public int getY() {
		return y;
	}

	/**
	 * Sets the y pos
	 * 
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Gets the y pos.
	 * 
	 * @return int
	 */
	public int getX() {
		return x;
	}


	/**
	 * Sets the x pos
	 * 
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;
	}

	@Override
	public String toString() {
		return "Position{" + "y=" + y + ", x=" + x + '}';
	}
}
