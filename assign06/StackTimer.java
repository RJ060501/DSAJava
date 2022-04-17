package assign06;

/**
 * This class collects running times for methods of SinglyLinkedList
 * 
 * @author Austin Morris and Ryan Russell
 * @version February 21, 2022
 */
public class StackTimer {

//	static WebBrowser main;
	public static void main(String[] args) {

		int timesToLoop = 10000;

		int incr = 10000;
		for (int probSize = 10000; probSize <= 200000; probSize += incr) {

//			ArrayStack<Integer> timerStack1 = new ArrayStack<>();
			LinkedListStack<Integer> timerStack1 = new LinkedListStack<>();

			for (int j = 0; j < probSize; j++)
				timerStack1.push(j);

			long stopTime, midpointTime, startTime = System.nanoTime();

			while (System.nanoTime() - startTime < 1000000000) { // empty block
			}

			// Collect running times.
			startTime = System.nanoTime();
			for (int i = 0; i < timesToLoop; i++) {

				timerStack1.pop();

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

}
