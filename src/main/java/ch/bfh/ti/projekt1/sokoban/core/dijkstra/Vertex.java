package ch.bfh.ti.projekt1.sokoban.core.dijkstra;

public class Vertex implements Comparable<Vertex> {
	private final String name;
	private int xValue;
	private int yValue;
	private Edge[] adjacencies;
	private double minDistance = Double.POSITIVE_INFINITY;
	private Vertex previous;

	public Vertex(String argName, int xValue, int yValue) {
		this.name = argName;
		this.xValue = xValue;
		this.yValue = yValue;
	}

	public int getX() {
		return xValue;
	}

	public int getY() {
		return yValue;
	}

	public String toString() {
		return name;
	}

	public int compareTo(Vertex other) {
		return Double.compare(minDistance, other.minDistance);
	}

	public int getxValue() {
		return xValue;
	}

	public void setxValue(int xValue) {
		this.xValue = xValue;
	}

	public int getyValue() {
		return yValue;
	}

	public void setyValue(int yValue) {
		this.yValue = yValue;
	}

	public Edge[] getAdjacencies() {
		return adjacencies;
	}

	public void setAdjacencies(Edge[] adjacencies) {
		this.adjacencies = adjacencies;
	}

	public double getMinDistance() {
		return minDistance;
	}

	public void setMinDistance(double minDistance) {
		this.minDistance = minDistance;
	}

	public Vertex getPrevious() {
		return previous;
	}

	public void setPrevious(Vertex previous) {
		this.previous = previous;
	}

	public String getName() {
		return name;
	}

}