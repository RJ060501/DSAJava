package assign05Old;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ArrayListSorterTest1 {
	Random rnd;
	private ArrayList<Integer> unsortedArrayList;
	private ArrayList<Integer> mediumUnsortedArray;
	private ArrayList<Integer> largeUnsortedArray;
	private ArrayList<Integer> veryLargeUnsortedArray;

	private ArrayList<Integer> ascendingArrayList;
	private ArrayList<Integer> mediumAscendingArray;
	private ArrayList<Integer> largeAscendingArray;
	private ArrayList<Integer> veryLargeAscendingArray;

	private ArrayList<Integer> descendingArrayList;
	private ArrayList<Integer> mediumDescendingArray;
	private ArrayList<Integer> largeDescendingArray;
	private ArrayList<Integer> veryLargeDescendingArray;
//    private final ArrayList<Rectangle> unsortedRectangleArray = new ArrayList<Rectangle>();
//	private final ArrayList<Rectangle> rectangleList = new ArrayList<Rectangle>();

	@BeforeEach
	void setUp() throws Exception {
		unsortedArrayList = new ArrayList<Integer>();
		unsortedArrayList.add(1);
		unsortedArrayList.add(3);
		unsortedArrayList.add(14);
		unsortedArrayList.add(7);
		unsortedArrayList.add(2);
		unsortedArrayList.add(19);

		mediumUnsortedArray = ArrayListSorter.generatePermuted(100);
		largeUnsortedArray = ArrayListSorter.generatePermuted(1000);
		veryLargeUnsortedArray = ArrayListSorter.generatePermuted(1000000);

		ascendingArrayList = new ArrayList<Integer>();
		ascendingArrayList.add(1);
		ascendingArrayList.add(2);
		ascendingArrayList.add(3);
		ascendingArrayList.add(7);
		ascendingArrayList.add(14);
		ascendingArrayList.add(19);

		mediumAscendingArray = ArrayListSorter.generateAscending(100);
		largeAscendingArray = ArrayListSorter.generateAscending(1000);
		veryLargeAscendingArray = ArrayListSorter.generateAscending(1000000);

		descendingArrayList = new ArrayList<Integer>();
		descendingArrayList.add(19);
		descendingArrayList.add(14);
		descendingArrayList.add(7);
		descendingArrayList.add(3);
		descendingArrayList.add(2);
		descendingArrayList.add(1);

		mediumDescendingArray = ArrayListSorter.generateDescending(100);
		largeDescendingArray = ArrayListSorter.generateDescending(1000);
		veryLargeDescendingArray = ArrayListSorter.generateDescending(1000000);
	}

	// Quick Sort unsorted array tests

	@Test
	void testQuickSort() {
		ArrayListSorter.quicksort(unsortedArrayList);
		System.out.println(Arrays.toString(unsortedArrayList.toArray()));
		assertEquals(1, unsortedArrayList.get(0));
		assertEquals(2, unsortedArrayList.get(1));
		assertEquals(3, unsortedArrayList.get(2));
		assertEquals(7, unsortedArrayList.get(3));
		assertEquals(14, unsortedArrayList.get(4));
		assertEquals(19, unsortedArrayList.get(5));
	}

	@Test
	void testQuickSort1() {
		ArrayListSorter.quicksort(mediumUnsortedArray);
		System.out.println(Arrays.toString(mediumUnsortedArray.toArray()));
	}

	@Test
	void testQuickSortLarge() {
		System.out.println(largeUnsortedArray.get(0).toString());
		ArrayListSorter.quicksort(largeUnsortedArray);
		assertEquals(0, largeUnsortedArray.get(0));
		assertEquals(1, largeUnsortedArray.get(1));
		assertEquals(999, largeUnsortedArray.get(999));
		assertEquals(271, largeUnsortedArray.get(271));
	}

	@Test
	void testQuickSortVeryLarge() {
		System.out.println(veryLargeUnsortedArray.get(0).toString());
		ArrayListSorter.quicksort(veryLargeUnsortedArray);
		assertEquals(0, veryLargeUnsortedArray.get(0));
		assertEquals(1, veryLargeUnsortedArray.get(1));
		assertEquals(999, veryLargeUnsortedArray.get(999));
		assertEquals(271, veryLargeUnsortedArray.get(271));
		assertEquals(2711, veryLargeUnsortedArray.get(2711));

	}

	// Merge sort on unsorted array tests

	@Test
	void mergeSortTest() {
		ArrayListSorter.mergesort(unsortedArrayList);
		assertEquals(2, unsortedArrayList.get(1));
		assertEquals(1, unsortedArrayList.get(0));
		assertEquals(3, unsortedArrayList.get(2));
		assertEquals(7, unsortedArrayList.get(3));
		assertEquals(14, unsortedArrayList.get(4));
		assertEquals(19, unsortedArrayList.get(5));
		System.out.println(Arrays.toString(mediumUnsortedArray.toArray()));
	}

	@Test
	void mergeSortTestLarge() {
		ArrayListSorter.mergesort(largeUnsortedArray);
		assertEquals(1, largeUnsortedArray.get(1));
		assertEquals(0, largeUnsortedArray.get(0));
		assertEquals(2, largeUnsortedArray.get(2));
		assertEquals(3, largeUnsortedArray.get(3));
		assertEquals(4, largeUnsortedArray.get(4));
		assertEquals(5, largeUnsortedArray.get(5));

	}

	@Test
	void mergeSortTestVeryLarge() {
		ArrayListSorter.mergesort(veryLargeUnsortedArray);
		assertEquals(1, veryLargeUnsortedArray.get(1));
		assertEquals(0, veryLargeUnsortedArray.get(0));
		assertEquals(2, veryLargeUnsortedArray.get(2));
		assertEquals(3, veryLargeUnsortedArray.get(3));
		assertEquals(4, veryLargeUnsortedArray.get(4));
		assertEquals(5, veryLargeUnsortedArray.get(5));
		assertEquals(5137, veryLargeUnsortedArray.get(5137));

	}

	@Test
	void testPermutedQuickSort() {
//		System.out.println(Arrays.toString(unsortedArrayList.toArray()));
		ArrayListSorter.quicksort(unsortedArrayList);
		assertEquals(unsortedArrayList.size(), 6);
		assertEquals(1, unsortedArrayList.get(0));
		assertEquals(2, unsortedArrayList.get(1));
		assertEquals(3, unsortedArrayList.get(2));
		assertEquals(7, unsortedArrayList.get(3));
		assertEquals(14, unsortedArrayList.get(4));
		assertEquals(19, unsortedArrayList.get(5));
		System.out.println(Arrays.toString(unsortedArrayList.toArray()));
	}

	@Test
	void testDescendingQuickSort() {
		System.out.println(Arrays.toString(descendingArrayList.toArray()));
		ArrayListSorter.quicksort(descendingArrayList);
		ArrayList<Integer> sortedArrayList = new ArrayList<>(Arrays.asList(1, 2, 3, 7, 8, 14, 19));
		System.out.println(Arrays.toString(descendingArrayList.toArray()));
		assertArrayEquals("equal ", sortedArrayList.toArray(), descendingArrayList.toArray());
	}

	@Test
	void testAscendingQuickSort() {
		ArrayListSorter.quicksort(unsortedArrayList);
		ArrayList<Integer> sortedIntegerArray = new ArrayList<>(Arrays.asList(1, 3, 7, 8, 14));
//		assertArrayEquals("equal " ,sortedIntegerArray.toArray(), rectangleList.toArray());
//		System.out.println(Arrays.toString(unsortedIntegerArray.toArray()));
		assertArrayEquals("equal ", sortedIntegerArray.toArray(), unsortedArrayList.toArray());
	}

//	@Test
//	void testMergeSort() {
//		ArrayListSorter.mergesort(unsortedArrayList);
//		ArrayList<Integer> sortedArrayList = new ArrayList<>(Arrays.asList(1, 3, 7, 8, 14));
//		//assertArrayEquals(sortedIntegerArray , unsortedIntegerArray );
//		System.out.println(Arrays.toString(unsortedArrayList.toArray()));
////		assertEquals(7, unsortedIntegerArray.get(2));
//		assertArrayEquals("equal " ,sortedArrayList.toArray(), unsortedArrayList.toArray());
//	}

//	@Test
//	void testInsertionSortIntegerArray() {
//		ArrayListSorter.quicksort(rectangleList, new OrderByArea());
////		System.out.println(Arrays.toString(rectangleArray));
//		ArrayList<Rectangle> sortedRecArray = new ArrayList<Rectangle>() {new Rectangle(0, 0, 9, 9), new Rectangle(0, 0, 7, 7), new Rectangle(0, 0, 6, 6), new Rectangle(0, 0, 5, 5), new Rectangle(0, 0, 4, 4), new Rectangle(0, 0, 3, 3)};
//		assertArrayEquals("equal " ,sortedRecArray, rectangleArray);
//	}
//
//	protected class OrderByArea implements Comparator<Rectangle> {
//		/**
//		 * Returns difference of first rectangle and second rectangle area
//		 */
//		@Override
//		public int compare(Rectangle o1, Rectangle o2) {
//			return o1.height*o1.width - o2.height*o2.width;
//		}
//	}

}
