package ch.bfh.ti.projekt1.sokoban.core.dijkstra;


/**
 * Defines an vertex of the dijkstra graph
 * @author marcoberger
 * @since 01.11.2014 13:20:22
 */
public class Vertex implements Comparable<Vertex> {
	private final String name;
	private int xValue;
	private int yValue;
	private Edge[] adjacencies;
	private double minDistance = Double.POSITIVE_INFINITY;
	private Vertex previous;

	/**
	 * @param argName
	 * @param xValue
	 * @param yValue
	 */
	public Vertex(String argName, int xValue, int yValue) {
		this.name = argName;
		this.xValue = xValue;
		this.yValue = yValue;
	}

	/**
	 * @return int
	 */
	public int getX() {
		return xValue;
	}
	/**
	 * @return int
	 */
	public int getY() {
		return yValue;
	}
	/**
	 * @return String
	 */
	public String toString() {
		return name;
	}

	/**
	 * @return int
	 */
	public int compareTo(Vertex other) {
		return Double.compare(minDistance, other.minDistance);
	}
	/**
	 * @return int
	 */
	public int getxValue() {
		return xValue;
	}
	/**
	 * @param xValue
	 */
	public void setxValue(int xValue) {
		this.xValue = xValue;
	}

	/**
	 * @return int
	 */
	public int getyValue() {
		return yValue;
	}

	/**
	 * @param yValue
	 */
	public void setyValue(int yValue) {
		this.yValue = yValue;
	}

	/**
	 * @return Edge[]
	 */
	public Edge[] getAdjacencies() {
		return adjacencies;
	}

	/**
	 * @param adjacencies
	 */
	public void setAdjacencies(Edge[] adjacencies) {
		this.adjacencies = adjacencies;
	}

	/**
	 * @return double
	 */
	public double getMinDistance() {
		return minDistance;
	}

	/**
	 * @param minDistance
	 */
	public void setMinDistance(double minDistance) {
		this.minDistance = minDistance;
	}

	/**
	 * @return Vertex
	 */
	public Vertex getPrevious() {
		return previous;
	}

	/**
	 * @param previous
	 */
	public void setPrevious(Vertex previous) {
		this.previous = previous;
	}

	/**
	 * @return String
	 */
	public String getName() {
		return name;
	}

}