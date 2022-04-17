package assign07;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * This class represents a vertex (AKA node) in a directed graph. The vertex is
 * not generic, assumes that a string name is stored there.
 * 
 * @author Erin Parker
 * @version March 3, 2022
 */
public class Vertex<Type> {

	// used to id the Vertex
	private Type name;

	public double distance;
	public int indegree;

	// adjacency list
	private LinkedList<Edge<Type>> adj;

	/**
	 * Creates a new Vertex object, using the given name.
	 * 
	 * @param name - string used to identify this Vertex
	 */
	public Vertex(Type name) {
		this.name = name;
		this.adj = new LinkedList<Edge<Type>>();
		distance = Double.POSITIVE_INFINITY;
	}

	/**
	 * @return the string used to identify this Vertex
	 */
	public Type getName() {
		return name;
	}

	/**
	 * Adds a directed edge from this Vertex to another.
	 * 
	 * @param otherVertex - the Vertex object that is the destination of the edge
	 */
	public void addEdge(Vertex<Type> otherVertex) {
		adj.add(new Edge<Type>(otherVertex));
	}

	/**
	 * @return a iterator for accessing the edges for which this Vertex is the
	 *         source
	 */
	public Iterator<Edge<Type>> edges() {
		return adj.iterator();
	}

	/**
	 * Generates and returns a textual representation of this Vertex.
	 */
	public String toString() {
		String s = "Vertex " + name.toString() + " adjacent to vertices ";
		Iterator<Edge<Type>> itr = adj.iterator();
		while (itr.hasNext())
			s += itr.next() + "  ";
		return s;
	}
}