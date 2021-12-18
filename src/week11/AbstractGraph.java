package week11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public abstract class AbstractGraph<V> implements Graph<V> {
	// Store vertices
	protected List<V> vertices = new ArrayList();
	// Adjacency lists
	protected List<List<Edge>> neighbors = new ArrayList();

	/** Construct an empty graph */
	protected AbstractGraph() {
	}

	/** Construct a graph from vertices and edges stored in arrays */
	protected AbstractGraph(V[] vertices, int[][] edges) {
		for (int i = 0; i < vertices.length; i++)
			addVertex(vertices[i]);
		createAdjacencyLists(edges, vertices.length);
	}

	/** Construct a graph from vertices and edges stored in List */
	protected AbstractGraph(List<V> vertices, List<Edge> edges) {
		for (int i = 0; i < vertices.size(); i++)
			addVertex(vertices.get(i));
		createAdjacencyLists(edges, vertices.size());
	}

	/** Edge inner class inside the week11.AbstractGraph class */
	public static class Edge {
		public int u; // Starting vertex of the edge
		public int v; // Ending vertex of the edge

		/** Construct an edge for (u, v) */
		public Edge(int u, int v) {
			this.u = u;
			this.v = v;
		}

		public boolean equals(Object o) {
			return u == ((Edge) o).u && v == ((Edge) o).v;
		}
	}

	/** Add an edge to the graph */
	protected boolean addEdge(Edge e) {
		if (e.u < 0 || e.u > getSize() - 1)
			throw new IllegalArgumentException("No such index: " + e.u);
		if (e.v < 0 || e.v > getSize() - 1)
			throw new IllegalArgumentException("No such index: " + e.v);
		if (!neighbors.get(e.u).contains(e)) {
			neighbors.get(e.u).add(e);
			return true;
		} else {
			return false;
		}
	}

	/** Construct a graph for integer vertices 0, 1, 2 and edge list */
	protected AbstractGraph(List<Edge> edges, int numberOfVertices) {
		for (int i = 0; i < numberOfVertices; i++)
			addVertex((V) (new Integer(i))); // vertices is {0, 1, ...}
		createAdjacencyLists(edges, numberOfVertices);
	}

	/** Construct a graph from integer vertices 0, 1, and edge array */
	protected AbstractGraph(int[][] edges, int numberOfVertices) {
		for (int i = 0; i < numberOfVertices; i++)
			addVertex((V) (new Integer(i))); // vertices is {0, 1, ...}
		createAdjacencyLists(edges, numberOfVertices);
	}

	/** Create adjacency lists for each vertex */
	private void createAdjacencyLists(int[][] edges, int numberOfVertices) {
		for (int i = 0; i < edges.length; i++) {
			addEdge(edges[i][0], edges[i][1]);
		}
	}

	/** Create adjacency lists for each vertex */
	private void createAdjacencyLists(List<Edge> edges, int numberOfVertices) {
		for (Edge edge : edges) {
			addEdge(edge.u, edge.v);
		}
	}

	@Override /** Add an edge to the graph */
	public boolean addEdge(int u, int v) {
		return addEdge(new Edge(u, v));
	}

	@Override /** Return the number of vertices in the graph */
	public int getSize() {
		return vertices.size();
	}

	@Override /** Return the vertices in the graph */
	public List<V> getVertices() {
		return vertices;
	}

	@Override /** Return the object for the specified vertex */
	public V getVertex(int index) {
		return vertices.get(index);
	}

	@Override /** Return the index for the specified vertex object */
	public int getIndex(V v) {
		return vertices.indexOf(v);
	}

	@Override /** Return the neighbors of the specified vertex */
	public List<Integer> getNeighbors(int index) {
		List<Integer> result = new ArrayList();
		for (Edge e : neighbors.get(index))
			result.add(e.v);
		return result;
	}

	@Override /** Return the degree for a specified vertex */
	public int getDegree(int v) {
		return neighbors.get(v).size();
	}

	@Override /** Clear the graph */
	public void clear() {
		vertices.clear();
		neighbors.clear();
	}

	@Override /** Print the edges */
	public void printEdges() {
		for (int u = 0; u < neighbors.size(); u++) {
			System.out.print(getVertex(u) + " (" + u + "): ");
			for (Edge e : neighbors.get(u)) {
				System.out.print("(" + getVertex(e.u) + ", " + getVertex(e.v) + ") ");
			}
			System.out.println();
		}
	}

	@Override /** Add a vertex to the graph */
	public boolean addVertex(V vertex) {
		if (!vertices.contains(vertex)) {
			vertices.add(vertex);
			neighbors.add(new ArrayList<Edge>());
			return true;
		} else {
			return false;
		}
	}

	public List<V> getPath(int u, int v) {
		ArrayList<V> path = new ArrayList<>();
		Queue<V> queue = new LinkedList<>();
		HashMap<V, V> map = new HashMap<>();
		HashSet<V> visited = new HashSet<>();
		V current = getVertex(u);

		queue.add(current);
		visited.add(current);

		while (!queue.isEmpty()) {
			current = queue.remove();
			if (getIndex(current) == v) {
				break;
			} else {
				List<Integer> neighbor = getNeighbors(getIndex(current));
				for (Integer i : neighbor) {
					if (!visited.contains(getVertex(i))) {
						queue.add(getVertex(i));
						visited.add(getVertex(i));
						map.put(getVertex(i), current);
					}

				}
			}
		}

		if (!current.equals(getVertex(v)))
			return null;

		while (current != null) {
			path.add(current);
			current = map.get(current);
		}
		Collections.reverse(path);

		return path;
	}

	@Override /** Obtain a DFS tree starting from vertex v */
	public Tree dfs(int v) {
		List<Integer> searchOrder = new ArrayList();
		int[] parent = new int[vertices.size()];
		for (int i = 0; i < parent.length; i++)
			parent[i] = -1; // Initialize parent[i] to -1
		// Mark visited vertices (default false)
		boolean[] isVisited = new boolean[vertices.size()];
		// Recursively search
		dfs(v, parent, searchOrder, isVisited);
		// Return a search tree
		return new Tree(v, parent, searchOrder);
	}

	public Tree dfs(int u, int[] parent, List<Integer> searchOrder, boolean[] isVisited, int start) {
		dfs(start, parent, searchOrder, isVisited);
		// Return a search tree
		return new Tree(start, parent, searchOrder);
	}

	/** Recursive method for DFS search */
	private void dfs(int u, int[] parent, List<Integer> searchOrder, boolean[] isVisited) {
		// Store the visited vertex
		searchOrder.add(u);
		isVisited[u] = true; // Vertex v visited
		for (Edge e : neighbors.get(u))
			if (!isVisited[e.v]) {
				parent[e.v] = u; // The parent of vertex e.v is u
				dfs(e.v, parent, searchOrder, isVisited); // Recursive search
			}
	}

	@Override /** Starting bfs search from vertex v */
	public Tree bfs(int v) {
		List<Integer> searchOrder = new ArrayList();
		int[] parent = new int[vertices.size()];
		for (int i = 0; i < parent.length; i++)
			parent[i] = -1; // Initialize parent[i] to -1
		java.util.LinkedList<Integer> queue = new java.util.LinkedList(); // list used as a queue
		queue.offer(v); // Enqueue v
		boolean[] isVisited = new boolean[vertices.size()];
		isVisited[v] = true; // Mark it visited
		while (!queue.isEmpty()) {
			int u = queue.poll(); // Dequeue to u
			searchOrder.add(u); // u searched
			for (Edge e : neighbors.get(u))
				if (!isVisited[e.v]) {
					queue.offer(e.v); // Enqueue v
					parent[e.v] = u; // The parent of w is u
					isVisited[e.v] = true; // Mark it visited
				}
		}
		return new Tree(v, parent, searchOrder);
	}

	public boolean isCyclic() {
		boolean[] isVisited = new boolean[vertices.size()];
		boolean[] recStack = new boolean[vertices.size()];

		for (int u = 0; u < vertices.size(); u++) {
			if (!isVisited[u]) {
				if (isSubgraphCyclic(u, isVisited, recStack))
					return true;
			}
		}

		return false;
	}

	public boolean isSubgraphCyclic(int n, boolean isVisited[], boolean recStack[]) {
		if (recStack[n])
			return true;
		if (isVisited[n])
			return false;

		isVisited[n] = true;
		recStack[n] = true;

		for (Edge e : neighbors.get(n)) {
			if (!isVisited[e.v]) {
				if (isSubgraphCyclic(e.v, isVisited, recStack))
					return true;
			} else {
				return true;
			}
		}
		recStack[n] = false;
		return false;
	}

	public List getACycle(int v) {
		return cycleEndNode(v);
	}

	public List cycleEndNode(int v) {
		List<Integer> searchOrder = new ArrayList<>();
		int[] parent = new int[vertices.size()];
		List<Integer> cycleEndNode = new ArrayList<>();
		for (int i = 0; i < parent.length; i++) {
			parent[i] = -1;
		}
		boolean[] isVisited = new boolean[vertices.size()];
		
		getACycleH(v, parent, searchOrder, isVisited, cycleEndNode, v);
		if (!cycleEndNode.isEmpty()) {
			return getPath(v, cycleEndNode.get(0));
		} else {
			return null;
		}
	}

	private void getACycleH(int u, int[] parent, List<Integer> searchOrder, boolean[] isVisited,
			List<Integer> cycleEndNode, int start) {
		searchOrder.add(u);
		isVisited[u] = true;

		for (Edge e : neighbors.get(u)) {
			if (!isVisited[e.v]) {
				parent[e.v] = u;
				getACycleH(e.v, parent, searchOrder, isVisited, cycleEndNode, start);
			} else if (e.v == start) {
				cycleEndNode.add(u);
			}
		}
	}

	public boolean isBipartite() {
		int[] colors = new int[vertices.size()];
		Arrays.fill(colors, -1);

		for (V src : vertices) {
			System.out.println("Testing " + src + " of color " + colors[(int) src]);
			if (colors[(int)src] == -1 && !bfs(colors, (int) src)) {
				return false;
			}
		}

		return true;
	}

	private boolean bfs(int[] colors, int src) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(src);
		colors[src] = 1;
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			int curColor = colors[cur];
			int nextColor = 1 - curColor;

			List<Edge> n = neighbors.get(cur);

			for (Edge e : n) {
				System.out.println("Checking colors of node " + e.u + ", which is " + colors[e.u] + " to node " + e.v + ", which is " + colors[e.v]);
				int v = e.v;

				if (colors[v] == -1) {
					colors[v] = nextColor;
					queue.add(v);
				} else if (colors[v] == colors[cur]) {
					return false;
				}
			}
		}
		return true;
	}

	public class Tree {
		private int root; // The root of the tree
		private int[] parent; // Store the parent of each vertex
		private List<Integer> searchOrder; // Store the search order

		/** Construct a tree with root, parent, and searchOrder */
		public Tree(int root, int[] parent, List<Integer> searchOrder) {
			this.root = root;
			this.parent = parent;
			this.searchOrder = searchOrder;
		}

		/** Return the root of the tree */
		public int getRoot() {
			return root;
		}

		/** Return the parent of vertex v */
		public int getParent(int v) {
			return parent[v];
		}

		/** Return an array representing search order */
		public List<Integer> getSearchOrder() {
			return searchOrder;
		}

		/** Return number of vertices found */
		public int getNumberOfVerticesFound() {
			return searchOrder.size();
		}

		/** Return the path of vertices from a vertex to the root */
		public List<V> getPath(int index) {
			ArrayList<V> path = new ArrayList();
			do {
				path.add(vertices.get(index));
				index = parent[index];
			} while (index != -1);
			return path;
		}

		/** Print a path from the root to vertex v */
		public void printPath(int index) {
			List<V> path = getPath(index);
			System.out.print("A path from " + vertices.get(root) + " to " + vertices.get(index) + ": ");
			for (int i = path.size() - 1; i >= 0; i--)
				System.out.print(path.get(i) + " ");
		}

		/** Print the whole tree */
		public void printTree() {
			System.out.println("Root is: " + vertices.get(root));
			System.out.print("Edges: ");
			for (int i = 0; i < parent.length; i++)
				if (parent[i] != -1) {
					// Display an edge
					System.out.print("(" + vertices.get(parent[i]) + ", " + vertices.get(i) + ") ");
				}
			System.out.println();
		}
	}
}