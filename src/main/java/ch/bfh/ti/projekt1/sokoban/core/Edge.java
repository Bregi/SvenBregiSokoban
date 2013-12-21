package ch.bfh.ti.projekt1.sokoban.core;

public class Edge {
	public final Vertex target;
	public final double weight;

	public Edge(Vertex argTarget, int argWeight) {
		target = argTarget;
		weight = argWeight;
	}
}