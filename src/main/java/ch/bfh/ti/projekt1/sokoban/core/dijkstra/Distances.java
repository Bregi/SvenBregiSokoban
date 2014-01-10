package ch.bfh.ti.projekt1.sokoban.core.dijkstra;

import java.util.ArrayList;

/**
 * Defines a distance
 * @author marcoberger
 * @since 01.11.2014 08:24:08
 */
public class Distances {
	private ArrayList<Vertex> distances = new ArrayList<Vertex>();
	private int startIndex = -1;
	private int endIndex = -1;

	public Distances() {
	}

	/**
	 * @param distances
	 * @param startIndex
	 * @param endIndex
	 */
	public Distances(ArrayList<Vertex> distances, int startIndex, int endIndex) {
		this.distances = distances;
		this.startIndex = startIndex;
		this.endIndex = endIndex;
	}

	/**
	 * Gets the distances
	 * @return ArrayList<Vertex>
	 */
	public ArrayList<Vertex> getDistances() {
		return distances;
	}

	/**
	 * sets the distances
	 * @param distances
	 */
	public void setDistances(ArrayList<Vertex> distances) {
		this.distances = distances;
	}

	/**
	 * Gets the startIndex (position of arraylist)
	 * @return int
	 */
	public int getStartIndex() {
		return startIndex;
	}

	/**
	 * Sets the startindex
	 * @param startIndex
	 */
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	/**
	 * Gets the endIndex (position of arraylist)
	 * @return int
	 */
	public int getEndIndex() {
		return endIndex;
	}

	/**
	 * Set the endIndex
	 * @param endIndex
	 */
	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}

}
