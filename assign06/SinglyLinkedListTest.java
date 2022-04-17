package assign06;

import static org.junit.Assert.*;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

class SinglyLinkedListTest {

	private SinglyLinkedList<Integer> smallIntegerList;
	private SinglyLinkedList<Integer> mediumIntegerList;

	@Before
	public void setUp() {
		// System.out.println("setUp");
		smallIntegerList = new SinglyLinkedList<Integer>();

		smallIntegerList.insertFirst(5); // Least Recent (End)
		smallIntegerList.insertFirst(4);
		smallIntegerList.insertFirst(3);
		smallIntegerList.insertFirst(2);
		smallIntegerList.insertFirst(1); // Most Recent (Begenning)

		mediumIntegerList = new SinglyLinkedList<Integer>();
		for (int i = 50; i > 0; i--) {
			mediumIntegerList.insertFirst(i);
		}
	}

	// Small Integer list tests
	@Test
	void testGetFirstSmall() {
		int first = smallIntegerList.getFirst();
		assertEquals(1, first);
	}

	@Test
	void testIsEmptySmall() {
		smallIntegerList.clear();
		assertTrue(smallIntegerList.isEmpty());
	}

	@Test
	void testGetFirstEmptyListSmall() {
		try {
			smallIntegerList.clear();
//			Integer first = list.getFirst();
//			assertEquals(null, first);
			assertTrue(smallIntegerList.isEmpty());
		} catch (NoSuchElementException e) {
			fail("No such element");
		}
	}

	@Test
	void testInsertSmall() {
		try {
			smallIntegerList.insert(4, 47);
			Integer var = smallIntegerList.get(4);
			assertEquals(47, var);
		} catch (IndexOutOfBoundsException e) {
			fail("Index out of bounds");
		}
	}

	@Test
	void testDeleteFirstSmall() {
		try {
			smallIntegerList.deleteFirst();
			int first = smallIntegerList.getFirst();
			assertEquals(2, first);
		} catch (NoSuchElementException e) {
			assertTrue(true);
		}
	}

	@Test
	void testDeleteInboundsSmall() {
		try {
			smallIntegerList.delete(3);
			int var = smallIntegerList.get(3);
			assertEquals(5, var);
		} catch (IndexOutOfBoundsException e) {
			fail("Out of int bounds");
		}
	}

	@Test
	void testDeleteOutOfBoundsSmall() {
		try {
			smallIntegerList.clear();
			smallIntegerList.delete(17);
		} catch (IndexOutOfBoundsException e) {
			assertTrue(true);
		}
	}

	@Test
	void testGetIndexSmall() {
		int testLast = smallIntegerList.get(smallIntegerList.size() - 1);
		int testNextToLast = smallIntegerList.get(smallIntegerList.size() - 2);

		assertEquals(5, testLast);
		assertEquals(4, testNextToLast);
	}

	@Test
	void testToArraySmall() {
		Object[] expected = new Object[5];
		for (int i = 0; i < expected.length; i++) {
			expected[i] = i + 1;
		}
		System.out.println(Arrays.toString(expected));
		assertArrayEquals(expected, smallIntegerList.toArray());
	}

	// Tests clear on list and tests remove on list
	@Test
	public void testEmptyRemoveSmall() {
		try {
			smallIntegerList.clear();
			smallIntegerList.iterator().remove();
			assertTrue(smallIntegerList.isEmpty());
		} catch (IllegalStateException e) {
			assertTrue(true);
		}
	}

	// Medium Integer list tests

	@Test
	void testGetFirstMedium() {
		int first = mediumIntegerList.getFirst();
		assertEquals(1, first);
	}

	@Test
	void testIsEmptyMedium() {
		mediumIntegerList.clear();
		assertTrue(mediumIntegerList.isEmpty());
	}

	@Test
	void testGetFirstEmptyListMedium() {
		try {
			mediumIntegerList.clear();
//			Integer first = list.getFirst();
//			assertEquals(null, first);
			assertTrue(mediumIntegerList.isEmpty());
		} catch (NoSuchElementException e) {
			fail("No such element");
		}
	}

	@Test
	void testInsertMedium() {
		try {
			mediumIntegerList.insert(4, 47);
			Integer var = mediumIntegerList.get(4);
			assertEquals(47, var);
		} catch (IndexOutOfBoundsException e) {
			fail("Index out of bounds");
		}
	}

	@Test
	void testDeleteFirstMedium() {
		try {
			mediumIntegerList.deleteFirst();
			int first = mediumIntegerList.getFirst();
			assertEquals(2, first);
		} catch (NoSuchElementException e) {
			assertTrue(true);
		}
	}

	@Test
	void testDeleteInboundsMedium() {
		try {
			mediumIntegerList.delete(3);
			int var = mediumIntegerList.get(3);
			assertEquals(5, var);
		} catch (IndexOutOfBoundsException e) {
			fail("Out of int bounds");
		}
	}
	
	@Test
    public void testEmptyRemove() {
        
            smallIntegerList.clear();
            assertThrows(IllegalStateException.class, () -> {
            	smallIntegerList.iterator().remove();
            });
          assertTrue(smallIntegerList.isEmpty());
       
        Iterator<Integer> name =  smallIntegerList.iterator();
        assertThrows(NoSuchElementException.class, () -> {
            name.next();
        });
    
        assertThrows(IllegalStateException.class, () -> {
            name.remove();
        });
    }

    @Test
    public void testRemoveSmall() {
    
        int var = smallIntegerList.get(2);
        smallIntegerList.delete(var);
        assertFalse(smallIntegerList.isEmpty());
       
        assertEquals(3, smallIntegerList.delete(2));
        assertEquals(3, smallIntegerList.size());
        Object[] name = smallIntegerList.toArray();
        Integer test = (Integer)name[2];
        assertEquals(5, test);
    }
    @Test
    public void testDeleteLast() {
    	assertEquals(5, smallIntegerList.delete(smallIntegerList.size() - 1));
    }
    
    @Test
    public void testDeleteFirst() {
    	assertEquals(1, smallIntegerList.delete(0));
    	assertEquals(2, smallIntegerList.getFirst());
    	assertEquals(4, smallIntegerList.size());
    	Integer[] test = {2, 3, 4, 5};
    	assertArrayEquals(test, smallIntegerList.toArray());
    }
    @Test
    public void testRemove() {
    	Iterator<Integer> itr = smallIntegerList.iterator();
    	
    	itr.next();
    	itr.remove();
    	assertEquals(2, smallIntegerList.getFirst());
    	
    	
    	assertEquals(4, smallIntegerList.size());
    	assertThrows(IllegalStateException.class, () -> {
            itr.remove();
        });
    	itr.next();
    	itr.remove();
    	
    	assertEquals(3, smallIntegerList.size());
    }

}
