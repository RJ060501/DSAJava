package assign05Old;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Random;

// import insertion sort

public class ArrayListSorter<T> {

	// final for code readability.
	private final static int INSERTION_CUT_OFF = 8;
	//	private final Comparator<? super T> cmp;
	// Investigate different choices.
	// Quicksort to Mergesort
	
	public static <T extends Comparable<? super T>>void mergesort(ArrayList<T> arr) {
        // exception
        if (arr==null) throw new NoSuchElementException("List is null");
		// Performs a merge sort on generic ArrayList given as input
		// Must switch over to insertion sort when the size of the sublist to be
		//meets a certain threshold
		// Make threshold value a private static variable that you can easily change
		// Create temporary ArrayList for merge sort (use add method)
		ArrayList<T> storage = new ArrayList<>();
		for (int i = 0; i < arr.size(); i++) {
			storage.add(null);
		}
		
		divide(arr, storage, 0, arr.size()- 1);
		return;
	}
	
	private static <T extends Comparable<? super T>> void divide(ArrayList<T> arr, ArrayList<T> storage, int start, int end) {
		if (end - start > 1) {
			int middle = (start + end) / 2;
			divide(arr, storage, start, middle);
			divide(arr, storage, middle + 1, end);
			merge(arr, storage, start, middle + 1, middle, end);
		}
		insertionSort(arr);
	}
	
	// start and end insertion parameters
	
	private static <T extends Comparable<? super T>> void merge(ArrayList<T> arr, ArrayList<T> storage, int startLeft, int startRight, int endLeft, int endRight) {
		// Base Case if the portion we are checking is the size(or length?) of 1, return
		if (endRight - startLeft < INSERTION_CUT_OFF) return;
		
		// Creates two cursors to for left and right sections we are checking
		// Left cursor is at index at which we want to set the element into our storage array.
		int leftCursor = startLeft, rightCursor = startRight;
		int nextPos = startLeft;
		// While the cursors are in the portion of the ArrayList we are checking
		while (leftCursor <= endLeft && rightCursor <= endRight) {
			// Why are we checking if the left cursor is greater than the right? This will never be the case if we are splitting the original array in half each time.
			if (arr.get(leftCursor).compareTo(arr.get(rightCursor)) <= 0) {
				// Set next position of temp list to be element at left cursor
				storage.set(nextPos++, arr.get(leftCursor++));
			}
			else {
				storage.set(nextPos++, arr.get(rightCursor++));
			}
		}
		// handle elements with null in them to get rid of gaps!
		// make case for when to start adding 
		// find min excluding the one we just took.
		// exclude 7 for the next iteration of finding the minimum
		
		// At least one of the sub lists is empty at this point.
		if(rightCursor > endRight) {
			// Adds all remaining elements from the other sub list.
			// sets next positions of temp list to be these elements
			while(leftCursor <= endLeft) {
				storage.set(nextPos++, arr.get(leftCursor++));
				
			}
		} else {
			while(rightCursor <= endRight) {
				storage.set(nextPos++, arr.get(rightCursor++));
			}
		}
		// Copy elements from temp list back to sublist
		for(int i = startLeft; i <= endRight; i++) {
			arr.set(i, storage.get(i));
		}
	}
	
	public static <T extends Comparable<? super T>> void quicksort(ArrayList<T> arr) {
		if (arr.size() == 0) {
			return;
		}

		quickSort(arr, 0, arr.size() - 1);
//		partition(arr, 0, arr.size() - 1);

		// This method performs a quicksort on the generic ArrayList given as input
		// Must implement three different strategies for determining the pivot.
		// Implementation should be able to EASILY SWITCH AMONG THESE STRATEGIES.
		// Return size of Object
	}
	
//	private static<T extends Comparable<? super T>> void partition(ArrayList<T> arr, int low, int high) {
//		if (low >= high) {
//			return;
//		}
//		
//		int pivotIndex = partitionHelper(arr, low, high);
//		partition(arr, pivotIndex++, high);
//		partition(arr, low, pivotIndex--);
//
//	}
//	
//	private static<T extends Comparable<? super T>> int partitionHelper(ArrayList<T> arr, int low, int high) {
//			// Strategy
//			int pivotIndex = (low + high) / 2;
//			swap(arr, pivotIndex, high);
//			
//			// Chooses the low value of the array list section we are considering
//			int left = low - 1;
//			for(int var = low; var < high; var++) {
//				if (arr.get(var).compareTo(arr.get(pivotIndex)) <= 0) {
//					left ++;
//					swap(arr, left, var);
//				}
//			}
//			
//			left ++;
//			swap(arr, high, low);
//			return left;
//	}
	
	private static<T extends Comparable<? super T>> void quickSort(ArrayList<T> arr, int left, int right) {
		if((right - left) > 1) {
			int pivotIndex = (left + right) / 2;
			swap(arr, pivotIndex, --right);
			
			int lowIndex = left, highIndex = right - 1;
			
			while( lowIndex <= highIndex) { 
				while(arr.get(lowIndex).compareTo(arr.get(right)) <= 0 && lowIndex < right) 
					lowIndex++;
						
				while(arr.get(highIndex).compareTo(arr.get(right)) > 0 && highIndex > left) 
					highIndex--;
				
				if(lowIndex < highIndex)
					swap(arr, lowIndex++, highIndex--);
			}
			
			swap(arr, lowIndex, right);
			
			quickSort(arr, left, lowIndex);
			quickSort(arr, ++lowIndex, --right);
		}
		return;
	}
		
	// How can we test all together?
	
//	private static<T extends Comparable<? super T>> int pivotIndexHelper(ArrayList<T> arr, int high, int low, int n) {
//		//Switch case
//		Random rnd = new Random();
//		switch(n) {
//			case (1):
//				return rnd.nextInt(high);
//			case (2):
//				return (low + high) / 2;
//		}
//	}
	
	/**
	 * This generic method sorts the input array using an insertion sort and the input Comparator object.
	 * @param <T> generic type being used
	 * @param arr the generic array being sorted
	 * @param cmp the comparator used in sorting
	 */
	private static<T extends Comparable<? super T>> void insertionSort(ArrayList<T> arr) {
		// Insertion sort basically copied from Erin's slide
		for (int i = 1; i < arr.size(); i++) {
			int j;
			T val = arr.get(i);
			for (j = i - 1; j >= 0 && val.compareTo(arr.get(j)) < 0; j--) {
				swap(arr, j+1, j);
			}
			
			arr.set(j + 1, val);
		}
	}
	
	private static <T> void swap(ArrayList<T> arr, int left, int right) {
		T temp = arr.get(left);
		arr.set(left, arr.get(right));
		arr.set(right, temp);
	}
	
	// Full Implementation of each sort (where we partition) Can and should use the add method
//		insertionSort(listOfArrs, (o1, o2) -> {return findLargestInt(o1) - findLargestInt(o2);});
	
	private static <T extends Comparable<? super T>> T medianOfThree(T t1, T t2, T t3) {
		
		if(t1.compareTo(t2) > 0 && t1.compareTo(t3) <= 0 || 
				t1.compareTo(t2) <= 0 && t1.compareTo(t3) > 0 )
			return t1;
		else if(t2.compareTo(t1) > 0 && t2.compareTo(t3) <= 0 || 
				t2.compareTo(t1) <= 0 && t2.compareTo(t3) > 0)
			return t2;
		
		
		return t3;
		
	}

	// generates an ArrayList of Integers from [0 - size). 
	public static ArrayList<Integer> generateAscending(int size) {
		
		ArrayList<Integer> ascending = new ArrayList<>();
		
		for(int i = 0; i < size; i++)
			ascending.add(i);
		
		return ascending;
	}
	// adds elements [0-size) by setting random indices to each value.
	public static ArrayList<Integer> generatePermuted(int size) {
		
		ArrayList<Integer> permuted = new ArrayList<>();
		Random rnd = new Random();
		
		for(int i = 0; i < size; i++)
			permuted.add(null);
		
		for(int i = 0; i < size; i++)
			permuted.set(rnd.nextInt(size - 1), i);
		return null;
	}
	//generates ArrayList from [size-0)
	public static ArrayList<Integer> generateDescending(int size) {
		
		ArrayList<Integer> descending = new ArrayList<>();
		
		for(int i = size; i > 0; i--)
			descending.add(i);
		
		return descending;
	}
	// (Consider using a few private helper methods for your different pivot-selection strategies.)
	
	// Create recursive Partition private static method - static for Junit testing
	// NOTE:  Each sort method above must be a "driver" method, which calls the
	// private recursive method that contains the full implementation each sort, 
	// as appropriate.
	 
	
	// Timing experiments to determine which strategy is best.
	
	// Preserving randomness is key for Hoare Partition Scheme
	// The key and value pairs are unordered – this is why associative arrays do not support sorting as an operation
	
//	public static <T> int chooseRandomPivot(ArrayList<T> arr) {
//		Random rng = new Random();
//		
//		for(int i = 0; i < arr.size(); i++) {
//			int randIndex = rng.nextInt(arr.size());
//			int temp = arr[i];
//			arr[i] = arr[randIndex];
//			arr[randIndex] = temp;
//	}
	
    public static<T extends Comparable<? super T>> boolean isSorted(ArrayList<T> arr) {
        for (int i = 1; i < arr.size(); i++)
            if (arr.get(i).compareTo(arr.get(i-1)) < 0) return false;
        return true;
    }
	
}
