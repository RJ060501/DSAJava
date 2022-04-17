package assign06;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SinglyLinkedList<T> implements List<T> {
	private Node head;
	private int lastItemIndex;

	// 0 parameter constructor (default)
	public SinglyLinkedList() {
		head = null;
		lastItemIndex = -1;
	}

	// nested node class which stores an instance of T and the next Node in the
	// list.
	private class Node {
		T data;
		Node next;

		Node(T d, Node next) {
			this.data = d;
			this.next = next;
		}
	}

	@Override
	public void insertFirst(T element) {

		Node newNode = new Node(element, head);
		head = newNode;
		lastItemIndex++;
	}

	/**
	 * Inserts element at desired index.
	 */
	@Override
	public void insert(int index, T element) throws IndexOutOfBoundsException {

		if (index > lastItemIndex) {
			throw new IndexOutOfBoundsException();
		}

		LinkedIterator pos = new LinkedIterator();

		while (pos.currentPos < index)
			pos.next();

		Node newNode = new Node(element, pos.currentNode);
		pos.prevNode.next = newNode;

		lastItemIndex++;
	}

	/**
	 * Retrieves the first element of the SinglyLinkedList.
	 */
	@Override
	public T getFirst() throws NoSuchElementException {

		if (head == null) {
			throw new NoSuchElementException();
		}

		return head.data;
	}

	/**
	 * Retrieves the element at the desired index.
	 */
	@Override
	public T get(int index) throws IndexOutOfBoundsException {

		if (index > lastItemIndex || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		LinkedIterator pos = new LinkedIterator();

		while (pos.currentPos < index)
			pos.next();
		return pos.currentNode.data;
	}

	/**
	 * Deletes first item in SinglyLinkedList.
	 */
	@Override
	public T deleteFirst() throws NoSuchElementException {
		if (head == null) {
			throw new NoSuchElementException();
		}
		Node temp = head;
		head = head.next;
		lastItemIndex--;
		return temp.data;
	}

	/**
	 * Deletes the element at the desired index.
	 */
	@Override
	public T delete(int index) throws IndexOutOfBoundsException {
		if (index > lastItemIndex || index < 0) {
			throw new IndexOutOfBoundsException();
		}

		LinkedIterator pos = new LinkedIterator();

		while (pos.currentPos < index)
			pos.next();

		T removedItem = pos.currentNode.data;

		pos.remove();

		return removedItem;
	}

	/**
	 * Retrieves element of iterated index.
	 */
	@Override
	public int indexOf(T element) {

		LinkedIterator pos = new LinkedIterator();

		while (pos.hasNext()) {
			if (pos.next() == element) {
				return pos.currentPos;
			}
		}
		return -1;
	}

	@Override
	public int size() {
		return lastItemIndex + 1;
	}

	@Override
	public boolean isEmpty() {
		return head == null;
	}

	@Override
	public void clear() {
		head = null;
		lastItemIndex = -1;
	}

	@Override
	public Object[] toArray() {

		Object[] copyArray = new Object[size()];
		LinkedIterator pos = new LinkedIterator();
		int index = 0;
		while (pos.hasNext())
			copyArray[index++] = pos.next();

		return copyArray;
	}

	/**
	 * Traverse through elements head to tail using Iterator. Includes remove(),
	 * getNext(), hasNext().
	 */
	@Override
	public Iterator<T> iterator() {

		return new LinkedIterator();
	}

	private class LinkedIterator implements Iterator<T> {
		private Node prevNode;
		private Node currentNode;
		private int currentPos;
		private boolean canRemove;

		public LinkedIterator() {
			currentPos = -1;
			currentNode = head;

		}

		@Override
		public boolean hasNext() {

			return currentPos < lastItemIndex;
		}

		@Override
		public T next() {

			if (!hasNext()) {
				throw new NoSuchElementException();
			}

			if (currentPos < 0) {
				currentPos++;
				canRemove = true;
				prevNode = currentNode;
				return currentNode.data;
			}

			// updated for use in remove()
			prevNode = currentNode;
			// advances current position
			currentNode = currentNode.next;

			currentPos++;
			canRemove = true;
			return currentNode.data;
		}

		@Override
		public void remove() {
			if (!canRemove)
				throw new IllegalStateException();

			if (currentNode == head) {

				head = head.next;
				currentNode = head.next;
				prevNode = head;

				currentPos--;
				lastItemIndex--;
				canRemove = false;

			} else {
				// this eliminates the link from previous to current
				prevNode.next = currentNode.next;
				// moves current to prevNode.next
				currentNode = currentNode.next;
				// decrement size of linked list
				currentPos--;
				lastItemIndex--;
				canRemove = false;
			}
		}

	}

}
