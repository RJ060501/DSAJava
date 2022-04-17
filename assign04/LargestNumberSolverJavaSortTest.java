/**
 * LargestNumberSolver test class
 * Solon Grover and Ryan Russell
 * 2/5/22
 */

package assign04;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Rectangle;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class LargestNumberSolverJavaSortTest {
	
	private Integer[] smallArray;
	private Integer[] emptyArray;
	private Rectangle[] rectangleArray;
	private Integer[] outOfBoundIntegerArray;
//	private List<Integer[]> outBoundsList;
	private List<Integer[]> list;
	
	@BeforeEach
	void setUp() throws Exception {
		emptyArray = new Integer[0];
		smallArray = new Integer[] {11, 2, 32, 44, 22};
		rectangleArray = new Rectangle[] {new Rectangle(0, 0, 9, 9), new Rectangle(0, 0, 3, 3), new Rectangle(0, 0, 4, 4), new Rectangle(0, 0, 5, 5), new Rectangle(0, 0, 6, 6), new Rectangle(0, 0, 7, 7)};
		outOfBoundIntegerArray = new Integer[] {11, 6545, 54, 300, 4342, 76, 11, 6545, 54, 300, 4342, 76};
		
		list = new ArrayList<Integer[]>();
		Integer[] arrIn1 = new Integer[] {39 , 85, 27, 64};
		Integer[] arrIn2 = new Integer[] {150 , 85, 87, 54};
		Integer[] arrIn3 = new Integer[] {143 , 245, 822};
		list.add(arrIn1);
		list.add(arrIn2);
		list.add(arrIn3);
	}
	
	@Test
	void testInsertionSortIntegerArray() {
		LargestNumberSolverJavaSort.insertionSort(rectangleArray, new OrderByArea());
//		System.out.println(Arrays.toString(rectangleArray));
		Rectangle[] sortedRecArray = new Rectangle[] {new Rectangle(0, 0, 9, 9), new Rectangle(0, 0, 7, 7), new Rectangle(0, 0, 6, 6), new Rectangle(0, 0, 5, 5), new Rectangle(0, 0, 4, 4), new Rectangle(0, 0, 3, 3)};
		assertArrayEquals("equal " ,sortedRecArray, rectangleArray);
	}

	protected class OrderByArea implements Comparator<Rectangle> {
		/**
		 * Returns difference of first rectangle and second rectangle area
		 */
		@Override
		public int compare(Rectangle o1, Rectangle o2) {
			return o1.height*o1.width - o2.height*o2.width;
		}
	}
	
	
	@Test
	void testFindLargestNumber1() {
		assertEquals(new BigInteger("443222211"), LargestNumberSolverJavaSort.findLargestNumber(smallArray));
	}
	
	@Test
	void testFindLargestNumberEmpty() {
		assertEquals(new BigInteger("0"), LargestNumberSolverJavaSort.findLargestNumber(emptyArray));
	}
	
	// Integers
	
	@Test
	void testFindLargestIntOutOfBounds() {
		try { 
			LargestNumberSolverJavaSort.findLargestInt(outOfBoundIntegerArray);
		} catch (OutOfRangeException e) {
			assertTrue(true);
		}
	}
	
	@Test
	void testFindLargestIntInBounds() {
		try { 
			//System.out.println(LargestNumberSolver.findLargestInt(arr));
			LargestNumberSolverJavaSort.findLargestInt(smallArray);
		} catch (OutOfRangeException e) {
			fail("Out of int bounds");
		}
	}
	
	// Longs
	
	@Test
	void testFindLargestLongOutOfBounds() {
		try { 
			LargestNumberSolverJavaSort.findLargestLong(outOfBoundIntegerArray);
		} catch (OutOfRangeException e) {
			assertTrue(true);
		}
	}
	
	@Test
	void testFindLargestLongInBounds() {
		try { 
			LargestNumberSolverJavaSort.findLargestLong(smallArray);
		} catch (OutOfRangeException e) {
			fail("Out of long bounds");
		}
	}
	
	// Other
	
	@Test
	void testBigIntegerSum() {
		BigInteger result = (LargestNumberSolverJavaSort.sum(list));
		assertEquals(BigInteger.valueOf(1786443220), result);
	}
	
	@Test
	void testFindKthLargest1() {
		// Returns Kth-1 item
		try {
			Integer[] result = LargestNumberSolverJavaSort.findKthLargest(list, 2);
//			System.out.println(Arrays.toString(result));
			assertTrue(Arrays.equals(new Integer[] {143, 245, 822}, result));
		} catch (IllegalArgumentException e){
			assertTrue(true);
		}
	}
	
	@Test
	void testFindKthLargest2() {
		// Returns Kth largest element
		try {
			Integer[] result = LargestNumberSolverJavaSort.findKthLargest(list, 0);
//			System.out.println(Arrays.toString(result));
			assertTrue(Arrays.equals(new Integer[] {39, 85, 27, 64}, result));
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
	}
	
	@Test
	void testFindKthLargestOutOfBounds() {
		// If the kth array is out of BigInteger range
		try {
			Integer[] result = LargestNumberSolverJavaSort.findKthLargest(list, -1);
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
	}
	
	@Test
	void testFindKthLargestOutOfBounds2() {
		try {
			Integer[] result = LargestNumberSolverJavaSort.findKthLargest(list, 69);
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
	}
	
	@Test
	void testReadFile() {
		List<Integer[]> list =  LargestNumberSolverJavaSort.readFile("src/assign04/integers.txt");
		
		// Prints copy of integers.txt
//		for(Integer[] arr : list) {
//			for (Integer i : arr)
//				System.out.print(i + " ");
//			
//			System.out.print("\n");
//		}
	}
	
	@Test
	void testReadFileBadName() {
		List<Integer[]> list =  LargestNumberSolverJavaSort.readFile("bruh");
		assertTrue(true);
	}
}
