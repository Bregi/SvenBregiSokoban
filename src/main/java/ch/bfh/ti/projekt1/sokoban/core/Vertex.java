package ch.bfh.ti.projekt1.sokoban.core;

public class Vertex implements Comparable<Vertex> {
	public final String name;
	public int xValue;
	public int yValue;
	public Edge[] adjacencies;
	public double minDistance = Double.POSITIVE_INFINITY;
	public Vertex previous;

	public Vertex(String argName, int xValue, int yValue) {
		name = argName;
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
}