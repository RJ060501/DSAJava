package assign05;

import java.util.ArrayList;
//import java.util.Random;

/**
 * This class collects running times for methods of ArrayListSorter
 * 
 * @author Erin Parker and Austin Morris
 * @version February 21, 2022
 */
public class ArrayListSorterTimer {

	static ArrayList<Integer> ascendingTester = ArrayListSorter.generateAscending(1000);
	static ArrayList<Integer> descendingTester = ArrayListSorter.generateDescending(1000);

	public static void main(String[] args) {
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
				ArrayListSorter.mergesort(ArrayListSorter.generateAscending(probSize));
			}

			midpointTime = System.nanoTime();

			// Capture the cost of running the loop and any other operations done
			// above that are not the essential method call being timed.
			for (int i = 0; i < timesToLoop; i++) {
				ArrayListSorter.generateAscending(probSize);

			}

			stopTime = System.nanoTime();

			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and searching.
			// Average it over the number of runs.
			double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / (double) timesToLoop;
			System.out.println(probSize + "  " + averageTime);
		}
	}

}
