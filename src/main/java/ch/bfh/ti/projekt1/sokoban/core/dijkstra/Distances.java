package ch.bfh.ti.projekt1.sokoban.core.dijkstra;

import java.util.ArrayList;

public class Distances {
	private ArrayList<Vertex> distances = new ArrayList<Vertex>();
	private int startIndex = -1;
	private int endIndex = -1;

	public Distances() {}
	
	public Distances(ArrayList<Vertex> distances, int startIndex, int endIndex) {
		this.distances = distances;
		this.startIndex = startIndex;
		this.endIndex = endIndex;
	}

	public ArrayList<Vertex> getDistances() {
		return distances;
	}

	public void setDistances(ArrayList<Vertex> distances) {
		this.distances = distances;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}

}
