package assign03;

public class SimplePriorityQueueFindMaxTimer {

	public static void main(String[] args) {
		// Do 10000 lookups and use the average running time
		int timesToLoop = 10000;
		
		// Create queue outside of loops so you can just add on to old one each time
		SimplePriorityQueue<Integer> queue = new SimplePriorityQueue<Integer>();
		
		// For each problem size n . . .
		for(int n = 100000; n <= 2000000; n += 100000) {
			// Generate a queue of n items
			
			for(int i = n; i > n - 100000 + 1; i--) 
				queue.insert(i);

			long startTime, midpointTime, stopTime;

			// First, spin computing stuff until one second has gone by
			// This allows this thread to stabilize
			startTime = System.nanoTime();
			while(System.nanoTime() - startTime < 1000000000) { // empty block
			}

			// Now, run the test
			startTime = System.nanoTime();

			// Run findMax on given population size for timesToLoop to get average
			for(int i = 0; i < timesToLoop; i++)
				queue.findMax();

			midpointTime = System.nanoTime();

			// Run a loop to capture the cost of running the "timesToLoop" loop
			for(int i = 0; i < timesToLoop; i++) { // empty block
			}

			stopTime = System.nanoTime();

			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and doing the lookups
			// Average it over the number of runs
			double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / 
					(double) timesToLoop;

			System.out.println(n + "\t" + averageTime);
		}
	}
}
