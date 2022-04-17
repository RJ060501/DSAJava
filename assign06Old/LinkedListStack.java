package assign06Old;

import java.util.EmptyStackException;
import java.util.NoSuchElementException;

public class LinkedListStack<T> implements Stack<T> {
	private int countSize;
	private SinglyLinkedList<T> stack;

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return countSize == 0;
	}

	@Override
	public T peek() throws NoSuchElementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T pop() throws NoSuchElementException {
		// TODO Auto-generated method stub
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		return stack.deleteFirst();
	}

	@Override
	public void push(T element) {
		// TODO Auto-generated method stub
		stack.insertFirst(element);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

}
