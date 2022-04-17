package assign08;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class collects running times for methods of GraphUtility By testing the
 * methods in Graph which are called.
 * 
 * @author Ryan Russell and Austin Morris
 * @version March 14, 2022
 */
public class BSTTimer {

	public static void main(String[] args) {

		int timesToLoop = 100;

		int incr = 10000;
		for (int probSize = 10000; probSize <= 200000; probSize += incr) {

			// First, spin computing stuff until one second has gone by.
			// This allows this thread to stabilize.

			long stopTime, midpointTime, startTime = System.nanoTime();

			while (System.nanoTime() - startTime < 1000000000) { // empty block
			}

			// Collect running times.
			startTime = System.nanoTime();
			for (int i = 0; i < timesToLoop; i++) {
				BinarySearchTreeA<Integer> test = new BinarySearchTreeA<>();
				// Generate list

//				generateNumsForRandom()
				for (int j = 0; j < probSize; j++)
					test.add(j);
				// value/N^2, should converge to posotive. N^3 generally converges to 0.
				// insertion N, therefore:
				// remove(0) (the first) from the list

			}

			midpointTime = System.nanoTime();

			// Capture the cost of running the loop and any other operations done
			// above that are not the essential method call being timed.
			for (int i = 0; i < timesToLoop; i++) {
				BinarySearchTreeA<Integer> test = new BinarySearchTreeA<>();
				for (int j = 0; j < probSize; j++) {
				}
			}

			stopTime = System.nanoTime();

			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and searching.
			// Average it over the number of runs.
			double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / (double) timesToLoop;
			System.out.println(probSize + "  " + averageTime);
		}
	}

	public static List<Integer> generateNumsForRandom(int num) {

		List<Integer> numsForAdding = new ArrayList<>();

		for (int i = 0; i < num; i++)
			numsForAdding.add(i);

		Collections.shuffle(numsForAdding);

		return numsForAdding;
	}
}
