package assign07;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Represents a sparse, unweighted, directed graph (a set of vertices and a set
 * of edges). The graph is not generic and assumes that a string name is stored
 * at each vertex.
 * 
 * @author Erin Parker
 * @version March 3, 2022
 */
public class Graph<Type> {

	// the graph -- a set of vertices (String name mapped to Vertex instance)
	private HashMap<Type, Vertex<Type>> vertices;

	/**
	 * Constructs an empty graph.
	 */
	public Graph() {
		vertices = new HashMap<Type, Vertex<Type>>();
	}

	public List<Type> BFS(Type start, Type destination) throws IllegalArgumentException {
		if (!vertices.containsKey(start) || !vertices.containsKey(destination)) {
			throw new IllegalArgumentException();
		}
		Queue<Vertex<Type>> queue = new LinkedList<Vertex<Type>>();
		LinkedList<Type> path = new LinkedList<Type>();

		queue.offer(vertices.get(start));
		path.addFirst(vertices.get(start).getName());
		vertices.get(start).distance = 0;

		while (!queue.isEmpty()) {
			Vertex<Type> temp = queue.poll();
			Iterator<Edge<Type>> itr = temp.edges();
			while (itr.hasNext()) {
				Vertex<Type> next = itr.next().getOtherVertex();
				// Checking if not visited
				if (next.equals(vertices.get(destination))) {
					vertices.get(destination).distance = temp.distance + 1;
					path.add(next.getName());
					return path;
				}
				if (next.distance == Double.POSITIVE_INFINITY) {
					next.distance = temp.distance + 1;

					if (queue.isEmpty())
						path.add(next.getName());
					queue.offer(next);

					// Adds to end of list
				}
			}
		}
		if (path.contains(vertices.get(destination))) {
			return path;
		} else {
			throw new IllegalArgumentException();
		}
	}

	public void DFS(Vertex<Type> next) {
		Iterator<Edge<Type>> itr = next.edges();

		// base case
		while (itr.hasNext()) {
			Vertex<Type> temp = itr.next().getOtherVertex();
			// Checking if not visited
			if (temp.distance == Double.POSITIVE_INFINITY) {
				// mark as visited
				temp.distance = next.distance + 1;
				// call DFS on temp
				DFS(temp);
			}
		}
		return;
	}

	public void DFSHelper(Type start, Type destination) throws IllegalArgumentException {
		if (!vertices.containsKey(start) || !vertices.containsKey(destination)) {
			throw new IllegalArgumentException();
		}
		vertices.get(start).distance = 0;
		DFS(vertices.get(start));
	}

	public List<Type> topSort() throws IllegalArgumentException {
		// copy graph into list sorted order
		Queue<Vertex<Type>> queue = new LinkedList<Vertex<Type>>();
		LinkedList<Type> sorted = new LinkedList<Type>();

		for (Vertex<Type> v : vertices.values()) {
			if (v.indegree == 0) {
				queue.offer(v);
			}
		}

		while (!queue.isEmpty()) {
			Vertex<Type> temp = queue.poll();
			sorted.add(temp.getName());
			Iterator<Edge<Type>> itr = temp.edges();
			while (itr.hasNext()) {
				Vertex<Type> w = itr.next().getOtherVertex();
				w.indegree -= 1;
				if (w.indegree == 0) {
					queue.offer(w);
				}
			}
		}
		if (sorted.containsAll(vertices.keySet())) {
			return sorted;
		} else {
			throw new IllegalArgumentException("Negative cycle detected");
		}

	}

	/**
	 * Adds to the graph a directed edge from the vertex with name "name1" to the
	 * vertex with name "name2". (If either vertex does not already exist in the
	 * graph, it is added.)
	 * 
	 * @param name1 - string name for source vertex
	 * @param name2 - string name for destination vertex
	 */
	public void addEdge(Type name1, Type name2) {
		Vertex<Type> vertex1;
		// if vertex already exists in graph, get its object
		if (vertices.containsKey(name1))
			vertex1 = vertices.get(name1);
		// else, create a new object and add to graph
		else {
			vertex1 = new Vertex<Type>(name1);
			vertices.put(name1, vertex1);
		}

		Vertex<Type> vertex2;
		if (vertices.containsKey(name2))
			vertex2 = vertices.get(name2);
		else {
			vertex2 = new Vertex<Type>(name2);
			vertices.put(name2, vertex2);
		}

		// add new directed edge from vertex1 to vertex2
		vertex1.addEdge(vertex2);
		vertex2.indegree += 1;
	}

	/**
	 * Generates the DOT encoding of this graph as string, which can be pasted into
	 * http://www.webgraphviz.com to produce a visualization.
	 */
	public String generateDot() {
		StringBuilder dot = new StringBuilder("digraph d {\n");

		// for every vertex
		for (Vertex<Type> v : vertices.values()) {
			// for every edge
			Iterator<Edge<Type>> edges = v.edges();
			while (edges.hasNext())
				dot.append("\t\"" + v.getName() + "\" -> \"" + edges.next() + "\"\n");

		}

		return dot.toString() + "}";
	}

	/**
	 * Generates a simple textual representation of this graph.
	 */
	public String toString() {
		StringBuilder result = new StringBuilder();

		for (Vertex<Type> v : vertices.values())
			result.append(v + "\n");

		return result.toString();
	}

	public boolean connected(Type destination) {
		// Check if vertex got visited during DFS
		return vertices.get(destination).distance != Double.POSITIVE_INFINITY;

	}

}