package assign07;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * This class tests the GraphUtility class.
 * 
 * @author Ryan Russell and Austin Morris
 * @version March 14, 2022
 */
class GraphTest {

	Graph<String> sample;
	List<String> sources;
	List<String> destinations;

	List<String> sources1;
	List<String> destinations1;

	List<String> cyclicSources;
	List<String> cyclicDestinations;

	@Before
	void setUp() {
		sample = new Graph<String>();
		sample.addEdge("a", "b");
		sample.addEdge("b", "c");
		sample.addEdge("c", "d");
		sample.addEdge("b", "d");
		sample.addEdge("c", "f");
		sample.addEdge("d", "e");

		sources = new ArrayList<String>();
		sources.add("h");
		sources.add("i");
		sources.add("j");
		sources.add("i");
		sources.add("j");
		sources.add("g");

		destinations = new ArrayList<String>();
		destinations.add("g");
		destinations.add("j");
		destinations.add("k");
		destinations.add("k");
		destinations.add("l");
		destinations.add("i");

		sources1 = new ArrayList<String>();
		sources1.add("h");
		sources1.add("i");
		sources1.add("j");
		sources1.add("i");
		sources1.add("j");
		sources1.add("y");
		sources1.add("u");

		destinations1 = new ArrayList<String>();
		destinations1.add("g");
		destinations1.add("j");
		destinations1.add("k");
		destinations1.add("k");
		destinations1.add("l");

		cyclicSources = new ArrayList<String>();
		cyclicSources.add("h");
		cyclicSources.add("i");
		cyclicSources.add("j");
		cyclicSources.add("i");
		cyclicSources.add("j");

		cyclicDestinations = new ArrayList<String>();
		cyclicDestinations.add("h");
		cyclicDestinations.add("j");
		cyclicDestinations.add("k");
		cyclicDestinations.add("k");
		cyclicDestinations.add("l");
	}

	@Test
	void smallAreConnectedTest() {
		sample.DFSHelper("a", "f");
		assertTrue(GraphUtility.areConnected(sources, destinations, "h", "l"));
		assertTrue(sample.connected("f"));
	}

	@Test
	void smallAreConnectedTestFail() {
		assertFalse(GraphUtility.areConnected(cyclicSources, cyclicDestinations, "h", "l"));
	}

	@Test
	void smallShortestPathTest() {
//		 Throws when there is no vertex in graph with
		String[] expected = { "h", "g", "i", "j" };
//		System.out.println(GraphUtility.shortestPath(sources, destinations, "h", "j").toString());
		assertArrayEquals(expected, GraphUtility.shortestPath(sources, destinations, "h", "j").toArray());
	}

	@Test
	void smallTopSort() {
		String[] expected = { "h", "g", "i", "j", "k", "l" };
		assertArrayEquals(expected, GraphUtility.sort(sources, destinations).toArray());
	}

	@Test
	void smallShortestPathThrows() {
		assertThrows(IllegalArgumentException.class, () -> {
			GraphUtility.shortestPath(sources1, destinations1, "u", "y");
		});
		LinkedList<String> test = (LinkedList<String>) GraphUtility.shortestPath(sources, destinations, "i", "l");
		assertEquals("i", test.get(0));
	}

	@Test
	void smallAreConnectedThrows() {
		assertThrows(IllegalArgumentException.class, () -> {
			GraphUtility.areConnected(sources1, destinations1, "u", "y");
		});
	}

	@Test
	void smallAreConnectedThrowsNoSources() {
		assertThrows(IllegalArgumentException.class, () -> {
			GraphUtility.areConnected(sources, destinations, "a", "j");
		});
		LinkedList<String> test = (LinkedList<String>) GraphUtility.shortestPath(sources, destinations, "i", "l");
		assertEquals("i", test.get(0));
	}

	@Test
	void smallAreConnectedThrowsNoDestination() {
		assertThrows(IllegalArgumentException.class, () -> {
			GraphUtility.areConnected(sources, destinations, "h", "y");
		});
		LinkedList<String> test = (LinkedList<String>) GraphUtility.shortestPath(sources, destinations, "i", "l");
		assertEquals("i", test.get(0));
	}

	@Test
	void smallSortThrows() {
		assertThrows(IllegalArgumentException.class, () -> {
			GraphUtility.sort(sources1, destinations1);
		});
		LinkedList<String> test = (LinkedList<String>) GraphUtility.sort(sources, destinations);
		assertEquals("h", test.get(0));
	}

	@Test
	void smallCyclicSortThrows() {
		assertThrows(IllegalArgumentException.class, () -> {
			GraphUtility.sort(cyclicSources, cyclicDestinations);
		});
		LinkedList<String> test = (LinkedList<String>) GraphUtility.sort(sources, destinations);
		assertEquals("h", test.get(0));
	}

	@Test
	void smallShortestPathThrowsNoDestination() {
		assertThrows(IllegalArgumentException.class, () -> {
			GraphUtility.shortestPath(sources, destinations, "h", "y");
		});
		LinkedList<String> test = (LinkedList<String>) GraphUtility.shortestPath(sources, destinations, "i", "l");
		assertEquals("i", test.get(0));
	}

	@Test
	void smallShortestPathThrowsNoSources() {
		assertThrows(IllegalArgumentException.class, () -> {
			GraphUtility.shortestPath(sources, destinations, "a", "j");
		});
		LinkedList<String> test = (LinkedList<String>) GraphUtility.shortestPath(sources, destinations, "i", "l");
		assertEquals("i", test.get(0));
	}

	@Test
	void smallShortestPathThrowsNoPath() {
		sources.add("T");
		destinations.add("X");
		assertThrows(IllegalArgumentException.class, () -> {
			GraphUtility.shortestPath(sources, destinations, "h", "T");
		});
		LinkedList<String> test = (LinkedList<String>) GraphUtility.shortestPath(sources, destinations, "i", "l");
		assertEquals("i", test.get(0));
	}

}
