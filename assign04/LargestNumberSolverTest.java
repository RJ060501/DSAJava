/**
 * LargestNumberSolver test class]
 * Solon Grover and Ryan Russel
 * 2/5/22
 */

package assign04;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.Assert.*;

import java.awt.Rectangle;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

class LargestNumberSolverTest {

	private Integer[] smallArray;
	private Integer[] emptyArray;
	private Rectangle[] rectangleArray;
	private Integer[] outOfBoundIntegerArray;
//	private List<Integer[]> outBoundsList;
	private List<Integer[]> inBoundsList;
	private List<Rectangle[]> rectangleList;

	@Before
	void setUp() throws Exception {
		emptyArray = new Integer[0];
		smallArray = new Integer[] { 11, 2, 32, 44, 22 };
		outOfBoundIntegerArray = new Integer[] { 11, 6545, 54, 300, 4342, 76, 11, 6545, 54, 300, 4342, 76 };

		rectangleArray = new Rectangle[] { new Rectangle(0, 0, 9, 9), new Rectangle(0, 0, 3, 3),
				new Rectangle(0, 0, 4, 4), new Rectangle(0, 0, 5, 5), new Rectangle(0, 0, 6, 6),
				new Rectangle(0, 0, 7, 7) };
//		rectangleList = new ArrayList<Rectangle[]>();
//		Rectangle[] arrRec1 = new Rectangle[] {new Rectangle(0, 0, 2, 2), new Rectangle(0, 0, 3, 3), new Rectangle(0, 0, 4, 4), new Rectangle(0, 0, 5, 5), new Rectangle(0, 0, 6, 6), new Rectangle(0, 0, 7, 7)};
//		Rectangle[] arrRec2 = new Rectangle[] {new Rectangle(0, 0, 2, 3), new Rectangle(0, 0, 9, 7), new Rectangle(0, 0, 4, 3), new Rectangle(0, 0, 7, 1), new Rectangle(0, 0, 10, 4), new Rectangle(0, 0, 6, 3)};
//		Rectangle[] arrRec3 = new Rectangle[] {new Rectangle(0, 0, 8, 8), new Rectangle(0, 0, 1, 1), new Rectangle(0, 0, 11, 11), new Rectangle(0, 0, 2, 2), new Rectangle(0, 0, 3, 3), new Rectangle(0, 0, 7, 7)};
//		rectangleList.add(arrRec1);
//		rectangleList.add(arrRec2);
//		rectangleList.add(arrRec3);

		inBoundsList = new ArrayList<Integer[]>();
		Integer[] arrIn1 = new Integer[] { 39, 85, 27, 64 };
		Integer[] arrIn2 = new Integer[] { 150, 85, 87, 54 };
		Integer[] arrIn3 = new Integer[] { 143, 245, 822 };
		inBoundsList.add(arrIn1);
		inBoundsList.add(arrIn2);
		inBoundsList.add(arrIn3);

	}

	// Empty Array/List LargestNumberSolver class tests

	@Test
	void testInsertionSortIntegerArray() {
		LargestNumberSolver.insertionSort(rectangleArray, new OrderByArea());
//		System.out.println(Arrays.toString(rectangleArray));
		Rectangle[] sortedRecArray = new Rectangle[] { new Rectangle(0, 0, 9, 9), new Rectangle(0, 0, 7, 7),
				new Rectangle(0, 0, 6, 6), new Rectangle(0, 0, 5, 5), new Rectangle(0, 0, 4, 4),
				new Rectangle(0, 0, 3, 3) };
		assertArrayEquals("equal ", sortedRecArray, rectangleArray);
	}

	protected class OrderByArea implements Comparator<Rectangle> {
		/**
		 * Returns difference of first rectangle and second rectangle area
		 */
		@Override
		public int compare(Rectangle o1, Rectangle o2) {
			return o1.height * o1.width - o2.height * o2.width;
		}
	}

	@Test
	void testFindLargestNumber1() {
		assertEquals(new BigInteger("443222211"), LargestNumberSolver.findLargestNumber(smallArray));
	}

	@Test
	void testFindLargestNumberEmpty() {
		assertEquals(new BigInteger("0"), LargestNumberSolver.findLargestNumber(emptyArray));
	}

	// Integers
	@Test
	void testFindLargestIntOutOfBounds() {
		try {
			LargestNumberSolver.findLargestInt(outOfBoundIntegerArray);
		} catch (OutOfRangeException e) {
			assertTrue(true);
		}
	}

	@Test
	void testFindLargestIntInBounds() {
		try {
			// System.out.println(LargestNumberSolver.findLargestInt(arr));
			LargestNumberSolver.findLargestInt(smallArray);
		} catch (OutOfRangeException e) {
			fail("Out of int bounds");
		}
	}

	// Longs

	@Test
	void testFindLargestLongOutOfBounds() {
		try {
			LargestNumberSolver.findLargestLong(outOfBoundIntegerArray);
		} catch (OutOfRangeException e) {
			assertTrue(true);
		}
	}

	@Test
	void testFindLargestLongInBounds() {
		try {
			LargestNumberSolver.findLargestLong(smallArray);
		} catch (OutOfRangeException e) {
			fail("Out of long bounds");
		}
	}

	@Test
	void testBigIntegerSum() {
		BigInteger result = (LargestNumberSolver.sum(inBoundsList));
		assertEquals(BigInteger.valueOf(1786443220), result);
	}

	@Test
	void testBigIntegerRectangleSum() {
		BigInteger result = (LargestNumberSolver.sum(inBoundsList));
		assertEquals(BigInteger.valueOf(1786443220), result);
	}

	@Test
	void testFindKthLargest1() {
		// Returns Kth-1 item
		try {
			Integer[] result = LargestNumberSolver.findKthLargest(inBoundsList, 2);
			assertTrue(Arrays.equals(new Integer[] { 39, 85, 27, 64 }, result));
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
	}

	@Test
	void testFindKthLargest2() {
		// Returns Kth largest element
		try {
			Integer[] result = LargestNumberSolver.findKthLargest(inBoundsList, 0);
			assertTrue(Arrays.equals(new Integer[] { 150, 85, 87, 54 }, result));
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
	}

	@Test
	void testFindKthLargestOutOfBounds() {
		// If the kth array is out of BigInteger range
		try {
			Integer[] result = LargestNumberSolver.findKthLargest(inBoundsList, -1);
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
	}

	@Test
	void testFindKthLargestOutOfBounds2() {
		try {
			Integer[] result = LargestNumberSolver.findKthLargest(inBoundsList, 69);
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
	}

	@Test
	void testReadFile() {
		List<Integer[]> list = LargestNumberSolver.readFile("src/assign04/integers.txt");
	}

	@Test
	void testReadFileBadName() {
		List<Integer[]> list = LargestNumberSolver.readFile("bruh");
		assertTrue(true);
	}
}
