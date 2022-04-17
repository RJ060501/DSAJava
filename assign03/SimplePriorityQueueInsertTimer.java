package assign03;

import java.util.NoSuchElementException;

public class SimplePriorityQueueInsertTimer {

	public static void main(String[] args) {
		// Do 10000 times and use the average running time
		int timesToLoop = 10000;
		
		// For each problem size n . . .
		for(int n = 100000; n <= 2000000; n += 100000) {
			SimplePriorityQueue<Integer> queue = new SimplePriorityQueue<Integer>();
			
			long startTime, midpointTime, stopTime;

			// First, spin computing stuff until one second has gone by
			// This allows this thread to stabilize
			startTime = System.nanoTime();
			while(System.nanoTime() - startTime < 1000000000) { // empty block
			}

			// Now, run the test
			startTime = System.nanoTime();

			// Run insert on given population size for timesToLoop to get average
			for(int i = 0; i < timesToLoop; i++)
				for (int j = 0; j < n; j++) {
					queue.insert(j);
					queue.deleteMax();
				}
			midpointTime = System.nanoTime();

			// Run a loop to capture the cost of running the "timesToLoop" loop
			for(int i = 0; i < timesToLoop; i++) {
				try {
					queue.deleteMax();
				} catch (NoSuchElementException e) {
					
				}
			}

			stopTime = System.nanoTime();

			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and doing the lookups
			// Average it over the number of runs
			double averageTime = (((midpointTime - startTime) - (stopTime - midpointTime)) / 
					(double) timesToLoop);

			System.out.println(n + "\t" + averageTime);
		}
	}
}
