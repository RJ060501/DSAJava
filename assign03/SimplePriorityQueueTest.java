package assign03;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import assign02.CS2420ClassGeneric;
import assign02.MailingAddress;
import assign03.SimplePriorityQueue;

class SimplePriorityQueueTest {
	//Comparable queues
	private SimplePriorityQueue<Integer> emptyIntegerQueue;
	private SimplePriorityQueue<String> emptyStringQueue;
	private SimplePriorityQueue<Integer> smallIntegerQueue;
	private SimplePriorityQueue<String> smallStringQueue;
	private SimplePriorityQueue<Integer> largeIntegerQueue;
	private SimplePriorityQueue<String> largeStringQueue;
	
	//Comparator queues
	private SimplePriorityQueue<Rectangle> emptyRectangleQueue;
	private SimplePriorityQueue<Rectangle> smallRectangleQueue;
	private SimplePriorityQueue<Rectangle> largeRectangleQueue;
	
	@BeforeEach
	void setUp() throws Exception {
		emptyStringQueue = new SimplePriorityQueue<String>();
		emptyIntegerQueue = new SimplePriorityQueue<Integer>();
		
		smallStringQueue = new SimplePriorityQueue<String>();
		smallStringQueue.insert("Provolone");
		smallStringQueue.insert("Swiss");
		smallStringQueue.insert("American");
		smallStringQueue.insert("Cheddar");
		
		smallIntegerQueue = new SimplePriorityQueue<Integer>();
		smallIntegerQueue.insert(400);
		smallIntegerQueue.insert(330);
		smallIntegerQueue.insert(112);
		smallIntegerQueue.insert(671);
		
		largeStringQueue = new SimplePriorityQueue<String>();
		largeIntegerQueue = new SimplePriorityQueue<Integer>();
		for (int i = 0; i < 100; i++) {
			largeStringQueue.insert("" + i);
			largeIntegerQueue.insert(i);
		}
		
		// SimplePriorityQueue's with comparator
		emptyRectangleQueue = new SimplePriorityQueue<Rectangle>(new OrderByArea());
		
		smallRectangleQueue = new SimplePriorityQueue<Rectangle>(new OrderByArea());
		smallRectangleQueue.insert(new Rectangle(0, 0, 4, 5));
		smallRectangleQueue.insert(new Rectangle(0, 0, 6, 8));
		smallRectangleQueue.insert(new Rectangle(0, 0, 9, 16));
		smallRectangleQueue.insert(new Rectangle(0, 0, 10, 32));
		
		largeRectangleQueue = new SimplePriorityQueue<Rectangle>(new OrderByArea());
		int max = 1000;
		for (int i = max; i > 0; i--) {
			Rectangle newRect = new Rectangle(0, 0, i, i);
			largeRectangleQueue.insert(newRect);
		}
	}
	
	// Empty simplePriorityQueue (Rectangle) class tests -----------------------------------------------------------------------------
	
	@Test
	void testEmptyRectangleQueueSize() {
		assertEquals(0, emptyRectangleQueue.size());
	}
	
	@Test
	void testEmptyRectangleQueueContains() {
		assertEquals(false, emptyRectangleQueue.contains(new Rectangle(0, 0, 7, 7)));
	}
	
	@Test
	void testEmptyRectangleQueueIsEmpty() {
		assertEquals(true, emptyRectangleQueue.isEmpty());
	}
	
	@Test
	void testEmptyRectangleQueueFindMax() {
		try {
			assertEquals(null, emptyRectangleQueue.findMax());
		} catch (NoSuchElementException e) {
			System.out.println("Cannot find max on empty queue");
		}
	}
	
	@Test
	void testEmptyRectangleQueueDeleteMax() {
		try { 
			assertEquals(null, emptyRectangleQueue.deleteMax());
		} catch (NoSuchElementException e) {
			System.out.println("Cannot delete max on empty queue");
		}
	}
	
	// Empty simplePriorityQueue (String) class tests -----------------------------------------------------------------------------
	
	@Test
	void testEmptyStringQueueSize() {
		assertEquals(0, emptyStringQueue.size());
	}
	
	@Test
	void testEmptyStringQueueContains() {
		assertEquals(false, emptyStringQueue.contains("Moldy"));
	}
	
	@Test
	void testEmptyStringQueueIsEmpty() {
		assertEquals(true, emptyStringQueue.isEmpty());
	}
	
	@Test
	void testEmptyStringQueueFindMax() {
		try {
			assertEquals(null, emptyStringQueue.findMax());
		} catch (NoSuchElementException e) {
			System.out.println("Cannot find max on empty queue");
		}
	}
	
	@Test
	void testEmptyStringQueueDeleteMax() {
		try { 
			assertEquals(null, emptyStringQueue.deleteMax());
		} catch (NoSuchElementException e) {
			System.out.println("Cannot delete max on empty queue");
		}
	}
	
	// Empty simplePriorityQueue (Integer) class tests -----------------------------------------------------------------------------
	
	@Test
	void testEmptyIntegerQueueSize() {
		assertEquals(0, emptyIntegerQueue.size());
	}
	
	@Test
	void testEmptyIntegerQueueContains() {
		assertEquals(false, smallIntegerQueue.contains(463));
	}
	
	@Test
	void testEmptyIntegerQueueIsEmpty() {
		assertEquals(true, emptyIntegerQueue.isEmpty());
	}
	
//	@Test
//	void testEmptyQueueClear() {
//		try {
//			emptyQueue.clear();
//			assertEquals(0, emptyQueue.size());
//		} catch (NoSuchElementException e) {
//			System.out.println("Cannot find max on empty queue");
//		}
//	}
	
	@Test
	void testEmptyIntegerQueueFindMax() {
		try {
			assertEquals(null, emptyIntegerQueue.findMax());
		} catch (NoSuchElementException e) {
			System.out.println("Cannot find max on empty queue");
		}
	}
	
	@Test
	void testEmptyIntegerQueueDeleteMax() {
		try { 
			assertEquals(null, emptyIntegerQueue.deleteMax());
		} catch (NoSuchElementException e) {
			System.out.println("Cannot delete max on empty queue");
		}
	}
	
	
	// Small simplePriorityQueue (String) class tests --------------------------------------------------------------------
	
	
	@Test
	void testSmallStringQueueInsertAll() {
		SimplePriorityQueue<String> queue = new SimplePriorityQueue<String>();
		Collection<String> thing = new ArrayList<String>();
		thing.add("F");
		thing.add("A");
		thing.add("B");
		thing.add("C");
		thing.add("G");
		thing.add("D");
		thing.add("E");
		queue.insertAll(thing);
		assertEquals(7, queue.size());
	}
	
	@Test
	void testSmallStringQueueSize() {
		assertEquals(4, smallStringQueue.size());
	}
	
	@Test
	void testSmallStringQueueContains() {
		assertEquals(true, smallStringQueue.contains("Cheddar"));
		assertEquals(false, smallStringQueue.contains("Moldy"));
	}
	
	@Test
	void testSmallStringQueueIsEmpty() {
		assertEquals(false, smallStringQueue.isEmpty());
	}
	
	@Test
	void testSmallStringQueueClear() {
		smallStringQueue.clear();
		assertEquals(0, smallStringQueue.size());
	}
	
	@Test
	void testSmallStringQueueFindMax() {
		assertEquals("Swiss", smallStringQueue.findMax());
	}
	
	@Test
	void testSmallStringQueueDeleteMax() {
		assertEquals("Swiss", smallStringQueue.deleteMax());
		assertEquals(3, smallStringQueue.size());
	}
	
	// Small simplePriorityQueue (Integer) class tests --------------------------------------------------------------------
	
	@Test
	void testSmallIntegerInsertAll() {
		SimplePriorityQueue<Integer> queue = new SimplePriorityQueue<Integer>();
		Collection<Integer> thing = new ArrayList<Integer>();
		thing.add(1);
		thing.add(2);
		thing.add(3);
		thing.add(4);
		thing.add(5);
		thing.add(6);
		thing.add(7);
		queue.insertAll(thing);
		assertEquals(7, queue.size());
	}
	
	
	@Test
	void testSmallIntegerQueueSize() {
		assertEquals(4, smallIntegerQueue.size());
	}
	
	@Test
	void testSmallIntegerContains() {
		assertEquals(true, smallIntegerQueue.contains(400));
		assertEquals(false, smallIntegerQueue.contains(92));
	}
	
	@Test
	void testSmallIntegerQueueIsEmpty() {
		assertEquals(false, smallIntegerQueue.isEmpty());
	}
	
	@Test
	void testSmallIntegerQueueClear() {
		smallIntegerQueue.clear();
		assertEquals(0, smallIntegerQueue.size());
	}
	
	@Test
	void testSmallIntegerFindMax() {
		assertEquals(671, smallIntegerQueue.findMax());
	}
	
	@Test
	void testSmallIntegerDeleteMax() {
		assertEquals(671, smallIntegerQueue.deleteMax());
		assertEquals(3, smallIntegerQueue.size());
	}
	
	// Small simplePriorityQueue (Rectangle) class tests --------------------------------------------------------------------
	
	@Test
	void testSmallRectangleInsertAll() {
		SimplePriorityQueue<Rectangle> queue = new SimplePriorityQueue<Rectangle>();
		Collection<Rectangle> thing = new ArrayList<Rectangle>();
		thing.add(new Rectangle(0, 0, 1, 1));
		thing.add(new Rectangle(0, 0, 2, 2));
		thing.add(new Rectangle(0, 0, 3, 3));
		thing.add(new Rectangle(0, 0, 4, 4));
		thing.add(new Rectangle(0, 0, 5, 5));
		thing.add(new Rectangle(0, 0, 6, 6));
		thing.add(new Rectangle(0, 0, 7, 7));
		System.out.println(thing);
		queue.insertAll(thing);
		assertEquals(7, queue.size());
	}
	
	@Test
	void testSmallRectangleQueueSize() {
		assertEquals(4, smallRectangleQueue.size());
	}
	
	@Test
	void testSmallRectangleContains() {
		assertEquals(true, smallRectangleQueue.contains(new Rectangle(0, 0, 4, 5)));
		assertEquals(false, smallRectangleQueue.contains(new Rectangle(0, 0, 2, 7)));
	}
	
	@Test
	void testSmallRectangleQueueIsEmpty() {
		assertEquals(false, smallRectangleQueue.isEmpty());
	}
	
	@Test
	void testSmallRectangleQueueClear() {
		smallRectangleQueue.clear();
		assertEquals(0, smallRectangleQueue.size());
	}
	
	@Test
	void testSmallRectangleFindMax() {
		Rectangle max = smallRectangleQueue.findMax();
		assertEquals(max, smallRectangleQueue.findMax());
	}
	
	@Test
	void testSmallRectangleDeleteMax() {
		Rectangle max = smallRectangleQueue.findMax();
		assertEquals(max, smallRectangleQueue.deleteMax());
		assertEquals(3, smallRectangleQueue.size());
	}
	
	// Large simplePriorityQueue (String) class tests --------------------------------------------------------------------
	
	@Test
	void testLargeStringQueueSize() {
		assertEquals(100, largeStringQueue.size());
	}
	
	@Test
	void testLargeStringQueueContains() {
		assertEquals(true, largeStringQueue.contains("1"));
		assertEquals(false, largeStringQueue.contains("200"));
	}
	
	@Test
	void testLargeStringQueueIsEmpty() {
		assertEquals(false, largeStringQueue.isEmpty());
	}
	
	@Test
	void testLargeStringQueueClear() {
		largeStringQueue.clear();
		assertEquals(0, largeStringQueue.size());
	}
	
	@Test
	void testLargeStringFindMax() {
		assertEquals("99", largeStringQueue.findMax());
	}
	
	@Test
	void testLargeStringDeleteMax() {
		assertEquals("99", largeStringQueue.deleteMax());
		assertEquals(99, largeStringQueue.size());
	}
	
	// Large simplePriorityQueue (Integer) class tests --------------------------------------------------------------------
	
	@Test
	void testLargeIntegerQueueSize() {
		assertEquals(100, largeIntegerQueue.size());
	}
	
	@Test
	void testLargeIntegerContains() {
		assertEquals(true, largeIntegerQueue.contains(3));
		assertEquals(false, largeIntegerQueue.contains(-700));
	}
	
	@Test
	void testLargeIntegerQueueIsEmpty() {
		assertEquals(false, largeIntegerQueue.isEmpty());
	}
	
	@Test
	void testLargeIntegerQueueClear() {
		largeIntegerQueue.clear();
		assertEquals(0, largeIntegerQueue.size());
	}
	
	@Test
	void testLargeIntegerFindMax() {
		assertEquals(99, largeIntegerQueue.findMax());
	}
	
	@Test
	void testLargeIntegerDeleteMax() {
		assertEquals(99, largeIntegerQueue.deleteMax());
		assertEquals(99, largeIntegerQueue.size());
	}
	
	// Large simplePriorityQueue (Rectangle) class tests --------------------------------------------------------------------
	
	@Test
	void testLargeRectangleQueueSize() {
		System.out.println(largeRectangleQueue.size());
		assertEquals(1000, largeRectangleQueue.size());
	}
	
	@Test
	void testLargeRectangleContains() {
		assertEquals(true, largeRectangleQueue.contains(new Rectangle(0, 0, 1, 1)));
		assertEquals(false, largeRectangleQueue.contains(new Rectangle(0, 0, 10002, 1002)));
	}
	
	@Test
	void testLargeRectangleQueueIsEmpty() {
		assertEquals(false, largeRectangleQueue.isEmpty());
	}
	
	@Test
	void testLargeRectangleQueueClear() {
		largeRectangleQueue.clear();
		assertEquals(0, largeRectangleQueue.size());
	}
	
	@Test
	void testLargeRectangleFindMax() {
		Rectangle max = largeRectangleQueue.findMax();
		assertEquals(max, largeRectangleQueue.findMax());
	}
	
	@Test
	void testLargeRectangleDeleteMax() {
		Rectangle max = largeRectangleQueue.findMax();
		assertEquals(max, largeRectangleQueue.deleteMax());
		assertEquals(999, largeRectangleQueue.size());
	}
	
	
	// OTHER -----------------------------------------------------------------------------------
	
	// Compares different rectangles by area
	
	protected class OrderByArea implements Comparator<Rectangle> {
		/**
		 * Returns difference of first rectangle and second rectangle area
		 */
		@Override
		public int compare(Rectangle o1, Rectangle o2) {
			return o1.height*o1.width - o2.height*o2.width;
		}
	}
}
