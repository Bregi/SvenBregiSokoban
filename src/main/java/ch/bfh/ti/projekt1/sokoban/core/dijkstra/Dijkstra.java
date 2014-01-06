package ch.bfh.ti.projekt1.sokoban.core.dijkstra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

import ch.bfh.ti.projekt1.sokoban.model.Field;
import ch.bfh.ti.projekt1.sokoban.model.FieldState;
import ch.bfh.ti.projekt1.sokoban.model.Position;

public class Dijkstra {
	private List<Vertex> path;
	private Field[][] model;

	public Dijkstra(Field[][] model) {
		this.model = model;
	}

	private void computePaths(Vertex source) {
		source.setMinDistance(0.0);
		PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
		vertexQueue.add(source);

		while (!vertexQueue.isEmpty()) {
			Vertex u = vertexQueue.poll();

			// Visit each edge exiting u
			for (Edge e : u.getAdjacencies()) {
				Vertex v = e.target;
				double weight = e.weight;
				double distanceThroughU = u.getMinDistance() + weight;
				if (distanceThroughU < v.getMinDistance()) {
					vertexQueue.remove(v);
					v.setMinDistance(distanceThroughU);
					v.setPrevious(u);
					vertexQueue.add(v);
				}
			}
		}
	}

	private List<Vertex> getShortestPathTo(Vertex target) {
		List<Vertex> path = new ArrayList<Vertex>();
		for (Vertex vertex = target; vertex != null; vertex = vertex
				.getPrevious())
			path.add(vertex);
		Collections.reverse(path);
		return path;
	}

	public List<Vertex> getPath(Position start, Position end) {
		Distances dijkstraObject = getDistances(start.getX(), start.getY(),
				end.getX(), end.getY());

		Vertex vStart = dijkstraObject.getDistances().get(
				dijkstraObject.getStartIndex());
		Vertex vEnd = dijkstraObject.getDistances().get(
				dijkstraObject.getEndIndex());

		return getPath(vStart, vEnd);
	}

	private List<Vertex> getPath(Vertex start, Vertex end) {

		computePaths(start);
		this.path = getShortestPathTo(end);
		return this.path;

	}

	/**
	 * Method that gives the distances each available field
	 * 
	 * @return ArrayList<Vertex>
	 */
	private Distances getDistances(int nStart, int iStart, int nEnd, int iEnd) {
		Distances distance = new Distances();

		// Iterate through all the fields to check the distances
		int nextRow = model.length;
		int position = 0;

		// First initialize with all the vertices
		for (int i = 0; i < model[0].length; i++) { // i = y achse [][][][][]
			for (int n = 0; n < model.length; n++) { // n = x achse

				String vertexName = "" + n + ":" + i;
				Vertex v = new Vertex(vertexName, n, i);
				distance.getDistances().add(v);

				if ((i == iStart) && (n == nStart)) {
					distance.setStartIndex(position);
				}
				if ((i == iEnd) && (n == nEnd)) {
					distance.setEndIndex(position);
				}
				position++;
			}
		}

		position = 0;

		// Then set the adjacences
		for (int i = 0; i < model[0].length; i++) { // i = y achse [][][][][]
			for (int n = 0; n < model.length; n++) { // n = x achse
				ArrayList<Edge> edges = new ArrayList<Edge>();

				if (n + 1 < model.length) {
					if (isWalkable(model[n + 1][i].getState())) {
						edges.add(new Edge(distance.getDistances().get(
								position + 1), 1));
					}
				}
				if (n > 0) {
					if (isWalkable(model[n - 1][i].getState())) {
						edges.add(new Edge(distance.getDistances().get(
								position - 1), 1));
					}
				}
				if (i + 1 < model[0].length) {
					if (isWalkable(model[n][i + 1].getState())) {
						edges.add(new Edge(distance.getDistances().get(
								position + nextRow), 1));
					}
				}
				if (i > 0) {
					if (isWalkable(model[n][i - 1].getState())) {
						edges.add(new Edge(distance.getDistances().get(
								position - nextRow), 1));
					}
				}

				Edge[] edgees = edges.toArray(new Edge[edges.size()]);
				distance.getDistances().get(position).setAdjacencies(edgees);
				position++;
			}

		}
		return distance;

	}

	/**
	 * Checks if a given field can be walked on
	 * 
	 * @param state
	 * @return boolean
	 */
	private boolean isWalkable(FieldState state) {
		if (state == FieldState.EMPTY || state == FieldState.GOAL) {
			return true;
		} else {
			return false;
		}
	}

	public List<Vertex> getPath() {
		return path;
	}

	public void setPath(List<Vertex> path) {
		this.path = path;
	}
}