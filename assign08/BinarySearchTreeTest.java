package assign08;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

class BinarySearchTreeTest {
	BinarySearchTree<Integer> smallTest;
	BinarySearchTree<Integer> mediumTest;
	ArrayList<Integer> list;
	Random rng = new Random();

	@Before
	void setUp() {
		smallTest = new BinarySearchTree<Integer>();
		smallTest.add(20);
		smallTest.add(14);
		smallTest.add(5);
		smallTest.add(16);
		smallTest.add(7);
		smallTest.add(1);
		smallTest.add(4);
		smallTest.add(6);

//		smallTest.add(10);
//		smallTest.add(5);
//		smallTest.add(15);
//		smallTest.add(3);
//		smallTest.add(7);
//		smallTest.add(12);
//		smallTest.add(17);

//		smallTest.add(26);
//		smallTest.add(35);
//		smallTest.add(24);
//		smallTest.add(33);
//		smallTest.add(39);

		list = new ArrayList<Integer>();

		mediumTest = new BinarySearchTree<Integer>();
		for (int i = 0; i < 100; i++) {
			mediumTest.add(rng.nextInt());
		}
		mediumTest.add(340);
		mediumTest.add(-20);
	}

	@Test
	void testFindMin() {
		assertEquals(1, smallTest.first());
	}

	@Test
	void testFindMax() {
		assertEquals(20, smallTest.last());
	}

	@Test
	void testSearch() {
		smallTest.last();
	}

	@Test
	public void testSize() {
		assertEquals(smallTest.size(), 7);
		for (int i = 21; i < 32; i++) {
			smallTest.add(i);
		}
		assertEquals(smallTest.size(), 18);
	}

	@Test
	void testRemoveNoChild() {
		assertFalse(smallTest.remove(99));
		// Testing node with no children
		assertEquals(smallTest.size(), 7);

		assertTrue(smallTest.remove(16));
		assertTrue(!smallTest.contains(16));
		assertEquals(smallTest.size(), 6);

		smallTest.add(16);

		assertEquals(smallTest.size(), 7);
	}

	@Test
	void testRemoveOneChild() {
		assertFalse(smallTest.remove(99));
		// Tests node with one child
		assertEquals(smallTest.size(), 7);

		assertTrue(smallTest.remove(1));
		assertTrue(!smallTest.contains(1));
		assertEquals(smallTest.size(), 6);
	}

	@Test
	void testRemoveTwoChildren() {
		assertFalse(smallTest.remove(99));
		// Tests node with two children
		assertEquals(smallTest.size(), 7);

		assertTrue(smallTest.remove(14));
		assertTrue(!smallTest.contains(14));
		assertEquals(smallTest.size(), 6);
	}

	@Test
	void testAddDuplicate() {
		assertFalse(smallTest.add(14));
	}

	@Test
	void testAddAll() {
		for (int i = 21; i < 32; i++) {
			list.add(i);
		}

		smallTest.addAll(list);

		assertEquals(smallTest.size(), 18);
		for (int i : list) {
			assertTrue(smallTest.contains(i));
		}
	}

	@Test
	void testContains() {
		assertTrue(smallTest.contains(1));
		assertFalse(smallTest.contains(2));
	}

	@Test
	void testContainsAll() {
		smallTest.addAll(list);
		assertTrue(smallTest.containsAll(list));
	}

	@Test
	void testClear() {
		assertEquals(smallTest.size(), 7);
		smallTest.clear();
		assertEquals(smallTest.size(), 0);
	}

	@Test
	void testIsEmpty() {
		assertTrue(!smallTest.isEmpty());
		smallTest.clear();
		assertTrue(smallTest.isEmpty());
	}

}
