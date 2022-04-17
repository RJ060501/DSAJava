
package assign05;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

/**
 * This class tests the method in the ArrayListSorter class
 * 
 * @author Erin Parker and Ryan Russell
 * @version February 22, 2022
 */

class ArrayListSorterTest {
	private ArrayList<Integer> emptyArray;

	private ArrayList<Integer> unsortedArray;
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

	private ArrayList<Integer> generatePermutedSmall;
	Random rnd;

	@Before
	void setUp() throws Exception {
		emptyArray = new ArrayList<Integer>(5);

		unsortedArray = new ArrayList<Integer>();
		unsortedArray.add(1);
		unsortedArray.add(3);
		unsortedArray.add(14);
		unsortedArray.add(7);
		unsortedArray.add(2);
		unsortedArray.add(19);

		mediumUnsortedArray = ArrayListSorter.generatePermuted(50);
		largeUnsortedArray = ArrayListSorter.generatePermuted(1000);
		veryLargeUnsortedArray = ArrayListSorter.generatePermuted(1000000);

		ascendingArrayList = new ArrayList<Integer>();
		ascendingArrayList.add(6);
		ascendingArrayList.add(7);
		ascendingArrayList.add(8);
		ascendingArrayList.add(9);
		ascendingArrayList.add(10);
		ascendingArrayList.add(11);

		mediumAscendingArray = ArrayListSorter.generateAscending(50);
		largeAscendingArray = ArrayListSorter.generateAscending(1000);
		veryLargeAscendingArray = ArrayListSorter.generateAscending(1000000);

		descendingArrayList = new ArrayList<Integer>();
		descendingArrayList.add(19);
		descendingArrayList.add(14);
		descendingArrayList.add(7);
		descendingArrayList.add(3);
		descendingArrayList.add(2);
		descendingArrayList.add(1);

		mediumDescendingArray = ArrayListSorter.generateDescending(50);
		largeDescendingArray = ArrayListSorter.generateDescending(1000);
		veryLargeDescendingArray = ArrayListSorter.generateDescending(1000000);

		generatePermutedSmall = ArrayListSorter.generatePermuted(10);
	}

	@Test
	void testQuickSortEmpty() {
		ArrayListSorter.quicksort(emptyArray);
		assertEquals(0, emptyArray.size());
	}

	// Quick Sort Tests unsorted array list
	@Test
	void testQuickSortUnsorted() {
		ArrayListSorter.quicksort(unsortedArray);
		assertEquals(1, unsortedArray.get(0));
		assertEquals(2, unsortedArray.get(1));
		assertEquals(3, unsortedArray.get(2));
		assertEquals(7, unsortedArray.get(3));
		assertEquals(14, unsortedArray.get(4));
		assertEquals(19, unsortedArray.get(5));
	}

	@Test
	void testQuickSortUnsortedMedium() {
		ArrayListSorter.quicksort(mediumUnsortedArray);
		assertEquals(7, mediumAscendingArray.get(6));
		assertEquals(8, mediumAscendingArray.get(7));
		assertEquals(9, mediumAscendingArray.get(8));
		assertEquals(10, mediumAscendingArray.get(9));
		assertEquals(11, mediumAscendingArray.get(10));
		assertEquals(12, mediumAscendingArray.get(11));
	}

	@Test
	void testQuickSortUnsortedLarge() {
		ArrayListSorter.quicksort(largeUnsortedArray);
		assertEquals(1, largeUnsortedArray.get(0));
		assertEquals(2, largeUnsortedArray.get(1));
		assertEquals(998, largeUnsortedArray.get(997));
		assertEquals(272, largeUnsortedArray.get(271));
	}

	@Test
	void testQuickSortUnsortedVeryLarge() {
		ArrayListSorter.quicksort(veryLargeUnsortedArray);
		assertEquals(1, veryLargeUnsortedArray.get(0));
		assertEquals(2, veryLargeUnsortedArray.get(1));
		assertEquals(998, veryLargeUnsortedArray.get(997));
		assertEquals(272, veryLargeUnsortedArray.get(271));
		assertEquals(2712, veryLargeUnsortedArray.get(2711));
	}

	// Ascending

	@Test
	void testQuickSortAscending() {
		ArrayListSorter.quicksort(ascendingArrayList);
		assertEquals(6, ascendingArrayList.get(0));
		assertEquals(7, ascendingArrayList.get(1));
		assertEquals(8, ascendingArrayList.get(2));
		assertEquals(9, ascendingArrayList.get(3));
		assertEquals(10, ascendingArrayList.get(4));
		assertEquals(11, ascendingArrayList.get(5));
	}

	@Test
	void testQuickSortMediumAscending() {
		ArrayListSorter.quicksort(mediumAscendingArray);
		assertEquals(7, mediumAscendingArray.get(6));
		assertEquals(8, mediumAscendingArray.get(7));
		assertEquals(9, mediumAscendingArray.get(8));
		assertEquals(10, mediumAscendingArray.get(9));
		assertEquals(11, mediumAscendingArray.get(10));
		assertEquals(12, mediumAscendingArray.get(11));
	}

	@Test
	void testQuickSortLargeAscending() {
		ArrayListSorter.quicksort(largeAscendingArray);
		assertEquals(7, largeAscendingArray.get(6));
		assertEquals(43, largeAscendingArray.get(42));
		assertEquals(9, largeAscendingArray.get(8));
		assertEquals(999, largeAscendingArray.get(998));
		assertEquals(272, largeAscendingArray.get(271));
	}

	@Test
	void testQuickSortVeryLargeAscending() {
		ArrayListSorter.quicksort(veryLargeAscendingArray);
		assertEquals(7, veryLargeAscendingArray.get(6));
		assertEquals(43, veryLargeAscendingArray.get(42));
		assertEquals(9, veryLargeAscendingArray.get(8));
		assertEquals(998, veryLargeAscendingArray.get(997));
		assertEquals(272, veryLargeAscendingArray.get(271));
		assertEquals(27112, veryLargeAscendingArray.get(27111));
	}

	// Descending

	@Test
	void testQuickSortDescending() {
		ArrayListSorter.quicksort(descendingArrayList);
		assertEquals(1, descendingArrayList.get(0));
		assertEquals(2, descendingArrayList.get(1));
		assertEquals(3, descendingArrayList.get(2));
		assertEquals(7, descendingArrayList.get(3));
		assertEquals(14, descendingArrayList.get(4));
		assertEquals(19, descendingArrayList.get(5));
	}

	@Test
	void testQuickSortMediumDescending() {
		ArrayListSorter.quicksort(mediumDescendingArray);
		assertEquals(7, mediumDescendingArray.get(6));
		assertEquals(8, mediumDescendingArray.get(7));
		assertEquals(9, mediumDescendingArray.get(8));
		assertEquals(10, mediumDescendingArray.get(9));
		assertEquals(11, mediumDescendingArray.get(10));
		assertEquals(12, mediumDescendingArray.get(11));
	}

	@Test
	void testQuickSortLargeDescending() {
		ArrayListSorter.quicksort(largeDescendingArray);
		assertEquals(7, largeDescendingArray.get(6));
		assertEquals(702, largeDescendingArray.get(701));
		assertEquals(989, largeDescendingArray.get(988));
		assertEquals(11, largeDescendingArray.get(10));
		assertEquals(646, largeDescendingArray.get(645));
		assertEquals(13, largeDescendingArray.get(12));
	}

	@Test
	void testQuickSortVeryLargeDescending() {
		ArrayListSorter.quicksort(veryLargeDescendingArray);
		assertEquals(7, veryLargeDescendingArray.get(6));
		assertEquals(702, veryLargeDescendingArray.get(701));
		assertEquals(989, veryLargeDescendingArray.get(988));
		assertEquals(11, veryLargeDescendingArray.get(10));
		assertEquals(646, veryLargeDescendingArray.get(645));
		assertEquals(13, veryLargeDescendingArray.get(12));
	}

	// Merge Sort Tests unsorted array list
	@Test
	void testMergeSortUnsortedMedium() {
		ArrayListSorter.mergesort(mediumUnsortedArray);
		System.out.println(mediumUnsortedArray.get(1));
		assertEquals(2, mediumUnsortedArray.get(1));
		assertEquals(3, mediumUnsortedArray.get(2));
		assertEquals(4, mediumUnsortedArray.get(3));
		assertEquals(9, mediumUnsortedArray.get(8));
		assertEquals(15, mediumUnsortedArray.get(14));
		assertEquals(21, mediumUnsortedArray.get(20));
	}

	@Test
	void testMergeSortUnsortedLarge() {
		ArrayListSorter.mergesort(largeUnsortedArray);
		System.out.println(largeUnsortedArray.get(271));
		assertEquals(1, largeUnsortedArray.get(0));
		assertEquals(2, largeUnsortedArray.get(1));
		assertEquals(998, largeUnsortedArray.get(997));
		assertEquals(272, largeUnsortedArray.get(271));
	}

	@Test
	void testMergeSortUnsortedVeryLarge() {
		ArrayListSorter.mergesort(veryLargeUnsortedArray);
		assertEquals(1, veryLargeUnsortedArray.get(0));
		assertEquals(2, veryLargeUnsortedArray.get(1));
		assertEquals(999, veryLargeUnsortedArray.get(998));
		assertEquals(272, veryLargeUnsortedArray.get(271));
		assertEquals(2712, veryLargeUnsortedArray.get(2711));
	}

	// Merge Sort Tests ascending array list

	@Test
	void testMergeSortMediumAscending() {
		ArrayListSorter.mergesort(mediumAscendingArray);
		assertEquals(2, mediumAscendingArray.get(1));
		assertEquals(3, mediumAscendingArray.get(2));
		assertEquals(4, mediumAscendingArray.get(3));
		assertEquals(8, mediumAscendingArray.get(7));
		assertEquals(15, mediumAscendingArray.get(14));
		assertEquals(20, mediumAscendingArray.get(19));
	}

	@Test
	void testMergeSortLargeAscending() {
		ArrayListSorter.mergesort(largeAscendingArray);
		assertEquals(7, largeAscendingArray.get(6));
		assertEquals(43, largeAscendingArray.get(42));
		assertEquals(9, largeAscendingArray.get(8));
		assertEquals(999, largeAscendingArray.get(998));
		assertEquals(272, largeAscendingArray.get(271));
	}

	@Test
	void testMergeSortVeryLargeAscending() {
		ArrayListSorter.mergesort(veryLargeAscendingArray);
		System.out.println(veryLargeAscendingArray.get(42));
		assertEquals(7, veryLargeAscendingArray.get(6));
		assertEquals(43, veryLargeAscendingArray.get(42));
		assertEquals(9, veryLargeAscendingArray.get(8));
		assertEquals(999, veryLargeAscendingArray.get(998));
		assertEquals(272, veryLargeAscendingArray.get(271));
		assertEquals(27112, veryLargeAscendingArray.get(27111));
	}

	// Merge Sort Tests descending array list

	@Test
	void testMergeSortMediumDescending() {
		ArrayListSorter.mergesort(mediumDescendingArray);
		assertEquals(7, mediumDescendingArray.get(6));
		assertEquals(8, mediumDescendingArray.get(7));
		assertEquals(9, mediumDescendingArray.get(8));
		assertEquals(10, mediumDescendingArray.get(9));
		assertEquals(11, mediumDescendingArray.get(10));
		assertEquals(12, mediumDescendingArray.get(11));
	}

	@Test
	void testMergeSortLargeDescending() {
		ArrayListSorter.mergesort(largeDescendingArray);
		assertEquals(7, largeDescendingArray.get(6));
		assertEquals(702, largeDescendingArray.get(701));
		assertEquals(989, largeDescendingArray.get(988));
		assertEquals(11, largeDescendingArray.get(10));
		assertEquals(646, largeDescendingArray.get(645));
		assertEquals(13, largeDescendingArray.get(12));
	}

	@Test
	void testMergeSortVeryLargeDescending() {
		ArrayListSorter.mergesort(veryLargeDescendingArray);
		assertEquals(7, veryLargeDescendingArray.get(6));
		assertEquals(702, veryLargeDescendingArray.get(701));
		assertEquals(989, veryLargeDescendingArray.get(988));
		assertEquals(11, veryLargeDescendingArray.get(10));
		assertEquals(646, veryLargeDescendingArray.get(645));
		assertEquals(13, veryLargeDescendingArray.get(12));
	}

	// Generate Permuted called for size 10
	@Test
	void testGeneratePermutedSmall() {
		System.out.println(generatePermutedSmall.get(3));
		assertNotEquals(4, generatePermutedSmall.get(3));
		ArrayList<Integer> ascending = ArrayListSorter.generateAscending(10);
		assertFalse(Arrays.equals(generatePermutedSmall.toArray(), ascending.toArray()));
	}

	// Generate Descending called for size 3
	@Test
	void testGenerateDescendingSmall() {

	}
}
