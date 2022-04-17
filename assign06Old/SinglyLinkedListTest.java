package assign06Old;

import static org.junit.Assert.assertEquals;

import org.junit.api.Test;

class SinglyLinkedListTest {

	SinglyLinkedList<Integer> list = new SinglyLinkedList<>();

	@Test
	void testInsertFirst() {
		list.insertFirst(2);
		list.insertFirst(1);
		list.insertFirst(0);

	}

	@Test
	void testInsertIndex() {
		list.insertFirst(2);
		list.insertFirst(1);
		list.insertFirst(0);

		list.insert(1, 3);

	}

	@Test
	void testDeleteFirst() {
		list.insertFirst(2);
		list.insertFirst(1);
		list.insertFirst(0);

		list.deleteFirst();

		int first = list.getFirst();
		assertEquals(1, first);

	}

	@Test
	void testDelete() {
		list.insertFirst(2);
		list.insertFirst(1);
		list.insertFirst(0);

		list.delete(1);

	}

	@Test
	void testGetIndex() {
		list.insertFirst(2);
		list.insertFirst(1);
		list.insertFirst(0);

		int testLast = list.get(list.size() - 1);
		int testNextToLast = list.get(list.size() - 2);

		assertEquals(2, testLast);
		assertEquals(1, testNextToLast);

	}

}
