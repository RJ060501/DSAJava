package assign06;

import java.util.NoSuchElementException;

public class LinkedListStack<T> implements Stack<T> {

	private SinglyLinkedList<T> backupList;

	public LinkedListStack() {
		backupList = new SinglyLinkedList<>();
	}

	@Override
	public void clear() {
		backupList.clear();
	}

	@Override
	public boolean isEmpty() {
		return backupList.isEmpty();
	}

	@Override
	public T peek() throws NoSuchElementException {
		if (isEmpty())
			throw new NoSuchElementException();

		return backupList.getFirst();
	}

	@Override
	public T pop() throws NoSuchElementException {

		if (isEmpty())
			throw new NoSuchElementException();

		return backupList.deleteFirst();
	}

	@Override
	public void push(T element) {
		backupList.insertFirst(element);

	}

	@Override
	public int size() {
		return backupList.size();
	}

}
