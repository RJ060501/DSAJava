package assign07;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class collects running times for methods of GraphUtility
 * 
 * @author Ryan Russell and Austin Morris
 * @version March 14, 2022
 */
public class GraphTimer {

	public static void main(String[] args) {
		Graph<String> graph = new Graph<>();
		List<Integer> src = new ArrayList<>();
		List<Integer> dest = new ArrayList<>();

		int count = 10;
		generateData(count, src, dest);
		for (int i = 0; i < src.size(); i++) {
			graph.addEdge(src.get(i).toString(), dest.get(i).toString());
			GraphUtility.areConnected(src, dest, src.get(i), dest.get(i));
		}
		// Random rng = new Random();

		int timesToLoop = 10000;

		int incr = 100;
		for (int probSize = 1000; probSize <= 2000; probSize += incr) {

			// First, spin computing stuff until one second has gone by.
			// This allows this thread to stabilize.

			long stopTime, midpointTime, startTime = System.nanoTime();

			while (System.nanoTime() - startTime < 1000000000) { // empty block
			}

			// Collect running times.
			startTime = System.nanoTime();
			for (int i = 0; i < timesToLoop; i++) {
				GraphUtility.shortestPath(src, dest, src.get(0), dest.get(10));
			}

			midpointTime = System.nanoTime();

			// Capture the cost of running the loop and any other operations done
			// above that are not the essential method call being timed.
			for (int i = 0; i < timesToLoop; i++) {
			}

			stopTime = System.nanoTime();

			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and searching.
			// Average it over the number of runs.
			double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / (double) timesToLoop;
			System.out.println(probSize + "  " + averageTime);
		}
	}
	// Generate cyclic and acyclic

	public static void generateData(int vertexCount, List<Integer> src, List<Integer> dest) {
		Random rng = new Random();

		// Generates acyclic graph
		// Randomly connect the vertices using 2 * |V| edges
//		List<Integer> sources = new ArrayList<>();
		while (src.size() < vertexCount) {
			for (int j = 0; j < vertexCount; j++) {
				int value = rng.nextInt(vertexCount);
				if (src.contains(value)) {
					continue;
				} else {
					src.add(value);
				}
			}
		}

		while (dest.size() < vertexCount) {
			for (int j = vertexCount * 2; j < 4 * vertexCount; j++) {
				dest.add(rng.nextInt(vertexCount));
			}
		}

		// Sustain acyclic balance by using 4 * |V| edges
//		List<Integer> destinations = new ArrayList<>();

		// Generates cyclic graph (A's distance from A is 0)
		// randomly connect the vertices using 2 * |V| edges
		// Add cycles if we want.
//		for (int i = 0; i < vertexCount - 1; i++) {
		// cyclic line
//			dest.add(src.get(i));
//		}

//		src = sources;
//		dest = destinations;
	}
}
