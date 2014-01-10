package ch.bfh.ti.projekt1.sokoban.core.dijkstra;


/**
 * Defines an edge of the dijkstra graph
 * @author marcoberger
 * @since 01.11.2014 08:24:08
 */
public class Edge {
	public final Vertex target;
	public final double weight;

	/**
	 * @param argTarget
	 * @param argWeight
	 */
	public Edge(Vertex argTarget, int argWeight) {
		target = argTarget;
		weight = argWeight;
	}
}