package assign05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * ArrayListSorter class, implementing quick sort and merge sort, methods for
 * creating ordered/unordered array lists and for choosing our pivot strategies.
 * 
 * @author Austin Morris and Ryan Russell
 * @version February 22, 2022
 */

public class ArrayListSorter {

	// Final for code readability.
	private final static int INSERTION_CUT_OFF = 8;

	// NOTE: Each sort method below is implemented as a "driver" method, which calls
	// the
	// private recursive method that contains the full implementation of each sort,
	// as appropriate.
	/**
	 * Performs a merge sort on generic ArrayList given as input.
	 * 
	 * @param <T>
	 * @param arr
	 */
	public static <T extends Comparable<? super T>> void mergesort(ArrayList<T> arr) {
		// Exception
		if (arr == null)
			throw new NoSuchElementException("List is null");

		// Creates temporary ArrayList to store data.
		ArrayList<T> storage = new ArrayList<>();
		for (int i = 0; i < arr.size(); i++) {
			storage.add(null);
		}

		divide(arr, storage, 0, arr.size() - 1);
		return;
	}

	private static <T extends Comparable<? super T>> void divide(ArrayList<T> arr, ArrayList<T> storage, int start,
			int end) {
		// Must switch over to insertion sort when the size of the sublist to meets a
		// certain threshold.
		if ((end - start) > INSERTION_CUT_OFF) {
			int middle = (start + end) / 2;
			divide(arr, storage, start, middle);
			divide(arr, storage, middle + 1, end);
			merge(arr, storage, start, middle + 1, middle, end);
		}
		insertionSort(arr, start, end);
		return;
	}

	/**
	 * This generic method sorts the input array using an insertion sort and the
	 * input Comparator object.
	 * 
	 * @param <T>
	 * @param arr
	 * @param start
	 * @param end
	 */
	private static <T extends Comparable<? super T>> void insertionSort(ArrayList<T> arr, int start, int end) {

		for (int itemSorted = start + 1; itemSorted < end; itemSorted++) {
			T temp = arr.get(itemSorted);
			int loc = itemSorted - 1;

			while (loc >= start && arr.get(loc).compareTo(temp) > 0) {
				arr.set(loc + 1, arr.get(loc));
				loc--;
			}
			arr.set(loc + 1, temp);
		}
	}

	/**
	 * Helper method that is used when all element of array list are of size '1'
	 * 
	 * @param <T>
	 * @param arr
	 * @param storage
	 * @param startLeft
	 * @param startRight
	 * @param endLeft
	 * @param endRight
	 */
	private static <T extends Comparable<? super T>> void merge(ArrayList<T> arr, ArrayList<T> storage, int startLeft,
			int startRight, int endLeft, int endRight) {

		// Creates two cursors to for left and right sections we are checking
		// Left cursor is at index at which we want to set the element into our storage
		// array.
		int leftCursor = startLeft, rightCursor = startRight;
		int nextPos = startLeft;
		// While the cursors are in the portion of the ArrayList we are checking
		while (leftCursor <= endLeft && rightCursor <= endRight) {
			if (arr.get(leftCursor).compareTo(arr.get(rightCursor)) <= 0) {
				// Set next position of storage list to be element at left cursor
				storage.set(nextPos++, arr.get(leftCursor++));
			} else {
				storage.set(nextPos++, arr.get(rightCursor++));
			}
		}
		// At least one of the sub lists is empty at this point...

		// Adds all remaining elements from the other sub list.
		// Sets next positions of temp list to be these elements.
		while (leftCursor <= endLeft)
			storage.set(nextPos++, arr.get(leftCursor++));

		while (rightCursor <= endRight)
			storage.set(nextPos++, arr.get(rightCursor++));

		// Copy elements from temp list back to sublist
		for (int i = startLeft; i <= endRight; i++)
			arr.set(i, storage.get(i));

	}

	/**
	 * This method performs a quicksort on the generic ArrayList given as input
	 * Return
	 * 
	 * @param <T>
	 * @param arr
	 */

	public static <T extends Comparable<? super T>> void quicksort(ArrayList<T> arr) {
		if (arr.size() == 0) {
			return;
		}

		quickSort(arr, 0, arr.size() - 1);
	}

	private static <T extends Comparable<? super T>> void quickSort(ArrayList<T> arr, int left, int right) {
		// Base case
		if ((right - left) > 0) {
			// The far most value determines pivot strategy. 1 = random pivot, 2 = middle,
			// Default is median of three
			int pivotIndex = pivotIndexHelper(arr, right, left, 2);
			// Moves pivot out of the way(to the end of the List
			swap(arr, pivotIndex, right);
			// Sets indices to correct values to iterate over unsorted values.
			int lowIndex = left, highIndex = right - 1;

			while (lowIndex <= highIndex) {

				while (arr.get(lowIndex).compareTo(arr.get(right)) <= 0 && lowIndex < right)
					lowIndex++;

				while (arr.get(highIndex).compareTo(arr.get(right)) > 0 && highIndex > left)
					highIndex--;

				// Only makes the swap if lowIndex < highIndex
				// If they are equal decrements highIndex as a means of escaping loop
				if (lowIndex < highIndex)
					swap(arr, lowIndex++, highIndex--);
				else if (lowIndex == highIndex)
					highIndex--;

			}

			// Restore pivot
			swap(arr, lowIndex, right);
			// Sorts the values <= pivot
			quickSort(arr, left, lowIndex - 1);
			// Sorts values > pivot
			quickSort(arr, lowIndex + 1, right);
		}
		return;
	}

	/**
	 * Helper method for pivot selection strategy. Random pivot, middle pivot, and
	 * median of 3.
	 * 
	 * @param <T>
	 * @param arr
	 * @param high
	 * @param low
	 * @param stratNum
	 * @return
	 */

	private static <T extends Comparable<? super T>> int pivotIndexHelper(ArrayList<T> arr, int high, int low,
			int stratNum) {

		Random rnd = new Random();
		// Switch statement for implementing the three strategies.
		switch (stratNum) {
		case (1):
			int randIndex = low + rnd.nextInt(high);

			if (randIndex <= high)
				return randIndex;

			return high; // This is how to ensure random value is [low, high]
		case (2):
			return (low + high) / 2;
		default:
			return arr.indexOf(medianOfThree(arr.get(low), arr.get((low + high) / 2), arr.get(high)));
		}

	}

	/**
	 * Helper method for identifying the median of the three provided elements
	 * 
	 * @param <T>
	 * @param t1
	 * @param t2
	 * @param t3
	 * @return
	 */
	private static <T extends Comparable<? super T>> T medianOfThree(T t1, T t2, T t3) {

		if (t1.compareTo(t2) > 0 && t1.compareTo(t3) <= 0 || t1.compareTo(t2) <= 0 && t1.compareTo(t3) > 0)
			return t1;
		else if (t2.compareTo(t1) > 0 && t2.compareTo(t3) <= 0 || t2.compareTo(t1) <= 0 && t2.compareTo(t3) > 0)
			return t2;

		return t3;

	}

	private static <T> void swap(ArrayList<T> arr, int left, int right) {
		T temp = arr.get(left);
		arr.set(left, arr.get(right));
		arr.set(right, temp);
	}

	/**
	 * Generates an ArrayList of Integers from [1-size).
	 * 
	 * @param size
	 * @return
	 */
	public static ArrayList<Integer> generateAscending(int size) {

		ArrayList<Integer> ascending = new ArrayList<>();

		for (int i = 1; i < size + 1; i++)
			ascending.add(i);

		return ascending;
	}

	/**
	 * Adds elements [1-size) by setting random indices to each value.
	 * 
	 * @param size
	 * @return
	 */
	public static ArrayList<Integer> generatePermuted(int size) {

		ArrayList<Integer> permuted = generateAscending(size);
		Collections.shuffle(permuted);
		return permuted;
	}

	/**
	 * Generates ArrayList from [size-0)
	 * 
	 * @param size
	 * @return
	 */
	public static ArrayList<Integer> generateDescending(int size) {

		ArrayList<Integer> descending = new ArrayList<>();

		for (int i = size; i > 0; i--)
			descending.add(i);

		return descending;
	}

	public static void shuffle(Integer[] arr) {
		Random rng = new Random();

		for (int i = 0; i < arr.length; i++) {
			int randIndex = rng.nextInt(arr.length);
			int temp = arr[i];
			arr[i] = arr[randIndex];
			arr[randIndex] = temp;
		}
	}
}
