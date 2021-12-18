package week10;

import week10.AbstractGraph;

import java.util.*;

public class UnweightedGraph<V> extends AbstractGraph<V> {
	/** Construct an empty graph */
	public UnweightedGraph() {
	}

	/** Construct a graph from vertices and edges stored in arrays */
	public UnweightedGraph(V[] vertices, int[][] edges) {
		super(vertices, edges);
	}

	/** Construct a graph from vertices and edges stored in List */
	public UnweightedGraph(List<V> vertices, List<Edge> edges) {
		super(vertices, edges);
	}

	/** Construct a graph for integer vertices 0, 1, 2 and edge list */
	public UnweightedGraph(List<Edge> edges, int numberOfVertices) {
		super(edges, numberOfVertices);
	}

	/** Construct a graph from integer vertices 0, 1, and edge array */
	public UnweightedGraph(int[][] edges, int numberOfVertices) {
		super(edges, numberOfVertices);
	}

	@Override
	public AbstractGraph<V>.Tree dfs(int v) {
		return super.dfs(v);
	}

	@Override
	public AbstractGraph<V>.Tree bfs(int v) {
		return super.bfs(v);
	}
	
	@Override
	public List getPath(int u, int v) {
		return super.getPath(u, v);
	}
}