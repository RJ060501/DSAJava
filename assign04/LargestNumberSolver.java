/**
 * LargestNumberSolver class, filled with static methods that can sort and find largest values given a comparator
 * Solon Grover and Ryan Russel
 * 2/5/22
 */
package assign04;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;


public class LargestNumberSolver<T> {
	
	/**
	 * This generic method sorts the input array using an insertion sort and the input Comparator object.
	 * @param <T> generic type being used
	 * @param arr the generic array being sorted
	 * @param cmp the comparator used in sorting
	 */
	public static <T> void insertionSort(T[] arr, Comparator<? super T> cmp) {
		// Insertion sort basically copied from Erin's slide
		for (int i = 1; i < arr.length; i++) {
			int j;
			T val = arr[i];
			for (j = i - 1; j >= 0 && (cmp.compare(val, arr[j])) > 0; j--) {
				arr[j + 1] = arr[j];
			}
			
			arr[j + 1] = val;
		}
	}
	
	/**
	 * This method returns the largest number that can be formed by arranging the integers of the given array, in any order.   
	 * If the array is empty, the largest number that can be formed is 0.
	 * @param arr the array which we will get the largest number from
	 * @return the largest number
	 */
	public static BigInteger findLargestNumber(Integer[] arr) {
		if (arr.length > 0) {
			// Clones parameter so we don't alter arr
			Integer[] copy = arr.clone();
			// Calls insertionSort and passes lambda expression with appropriate comparison
			insertionSort(copy, (o1, o2) -> {return (new BigInteger("" + o1 + o2)).compareTo(new BigInteger("" + o2 + o1));});
			
			// Concatenates the items in array to produce string result
			StringBuilder result = new StringBuilder();
			for (int i = 0; i < copy.length; i++) {
				result.append(copy[i]);
			}
			
			// Converts string result to integer
			return new BigInteger(result.toString());
		} else {
			return new BigInteger("0");
		}
	}
	
	/**
	 * This method returns the largest int value that can be formed by arranging the integers of the given array, in any order.  
	 * An OutOfRangeException is thrown if the largest number that can be formed is too large for the int data type.
	 * @param arr the array which we will get the largest int from
	 * @return the largest int
	 * @throws OutOfRangeException if value found exceeds range of an integer
	 */
	public static int findLargestInt(Integer[] arr) throws OutOfRangeException {
		// Sorts array
		BigInteger sortedArr = findLargestNumber(arr);
		
		// If concatenated value of sortedArr is greater than max threshold of int, throw exception
		if (sortedArr.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) > 0 ) {
			throw new OutOfRangeException("int");
		// If it doesn't overflow, return the int value of the BigInteger sortedArr
		} else {
			return sortedArr.intValue();
		}
	}
	
	/**
	 * This method behaves the same as the previous method, but for data type long instead of data type int.
	 * @param arr the array which we will get the largest long from
	 * @return the largest long
	 * @throws OutOfRangeException if the value found exceeds range of a long
	 */
	public static long findLargestLong(Integer[] arr) throws OutOfRangeException {
		// Sorts array
		BigInteger sortedArr = findLargestNumber(arr);
		
		// If concatenated value of sortedArr is greater than max threshold of long, throw exception
		if (sortedArr.compareTo(BigInteger.valueOf(Long.MAX_VALUE)) > 0 ) {
			throw new OutOfRangeException("long");
		// If it doesn't overflow, return the long value of the BigInteger sortedArr
		} else {
			return sortedArr.intValue();
		}
		
	}
	
	/**
	 * This method sums the largest numbers that can be formed by each array in the given list.  This method must not alter the given list.
	 * @param list is the list of integer arrays 
	 * @return the sum of the largest numbers in each integer array in the list
	 */
	public static BigInteger sum(List<Integer[]> list) {
		// Sum is initially zero
		BigInteger sum = new BigInteger("0");
		
		// Loops through each Integer[] in list and adds their largest number to sum
		for (int i = 0; i < list.size(); i++) {
			sum = sum.add(findLargestNumber(list.get(i)));
		}
		
		// Returns sum of largest numbers formed in each array
		return sum;
	}
	
	/**
	 * This method determines the kth largest number that can be formed by each array in the given list.
	 * @param list containing integer arrays
	 * @param k the desired position in the list assuming its contents are sorted
	 * @return the integer array of the largest number, unaltered
	 * @throws IllegalArgumentException if k is out of range of the list index
	 */
	public static Integer[] findKthLargest(List<Integer[]> list, int k) throws IllegalArgumentException {
		// Checks if k is within bounds
		if (k >= 0 && k <= list.size() - 1) {
			// Creates integer array array to contain integer arrays
			Integer[][] listOfArrs = new Integer[list.size()][];
			
			// Loops through the list and sorts each integer array then adds it to listOfArrs
			for (int i = 0; i < list.size(); i++) {
				//insertionSort(list.get(i), (o1, o2) -> {return (new BigInteger("" + o1 + o2)).compareTo(new BigInteger("" + o2 + o1));});
				listOfArrs[i] = list.get(i);
			}
			
			// Sorts listOfArrs
			insertionSort(listOfArrs, (o1, o2) -> {return findLargestInt(o1) - findLargestInt(o2);});
			
			return listOfArrs[k];
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * This method generates list of integer arrays from an input file, such that each line corresponds to one array of integers separated by 
	 * blank spaces, and returns an empty list if the file does not exist.
	 * @param filename the name of the file we wish to read
	 * @return the list of integer arrays containing the data we just read
	 */
	public static List<Integer[]> readFile(String filename) {
		try {
			// Setup scanner for file
			File filePath = new File(filename);
			Scanner file = new Scanner(filePath);
			
			// Creates list for each line array
			ArrayList<Integer[]> list = new ArrayList<Integer[]>();
			
			// Loops while there is another line in the file
			while (file.hasNextLine()) {
				// Grabs line
				String line = file.nextLine();
				
				// Splits line into string array
				String[] splitLine = line.split(" ");
				
				// Creates new integer array to place the numbers in
				Integer[] arr = new Integer[splitLine.length];
				
				// Converts strings to ints and dumps them in arr
				for(int i = 0; i < arr.length; i++) 
					arr[i] = Integer.parseInt(splitLine[i]);
				
				// Add arr to list
				list.add(arr);
			}
			
			// Closes file and returns list
			file.close();
			return list;
			
		// Throws exception if the file name can't be found
		} catch (IOException e) {
			System.out.println("File " + filename + " cannot be accessed.");
		}
		
		return null;
	}

}
