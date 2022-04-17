package assign04;

import java.util.Arrays;
import java.util.List;

public class FindKthLargestTimer {

	public static void main(String[] args) {
		// number of times we will loop
		int timesToLoop = 1;
		
		
		// For each problem size n . . .
		for(int n = 1000; n <= 20000; n += 1000) {
			
			// Generate a list of size n
			Integer[][] arr = new Integer[n][];
			for (int i = 0; i < n; i++) 
				// Each Integer[] in the Integer[][] has two entries
				arr[i] = new Integer[] {i/100, i/100};
			
			// Converts Integer[][] to list
			List<Integer[]> list = Arrays.asList(arr);
			
			long startTime, midpointTime, stopTime;

			// First, spin computing stuff until one second has gone by
			// This allows this thread to stabilize
			startTime = System.nanoTime();
			while(System.nanoTime() - startTime < 1000000000) { // empty block
			}

			// Now, run the test
			startTime = System.nanoTime();

			// Run findKthLargest on given n size for timesToLoop to get average
			for(int i = 0; i < timesToLoop; i++)
				LargestNumberSolver.findKthLargest(list, 0);

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
