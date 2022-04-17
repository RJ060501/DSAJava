package assign06;

import java.util.NoSuchElementException;

/**
 * Representation of a stack ADT (backed by a basic array).
 * 
 * @author Erin Parker
 * @version February 24, 2022
 *
 * @param <E> - the type of elements contained in the stack
 */
public class ArrayStack<E> implements Stack<E> {

	private E[] arr;
	private int top;

	/**
	 * Creates a new stack.
	 */
	@SuppressWarnings("unchecked")
	public ArrayStack() {
		arr = (E[]) new Object[100];
		top = -1;
	}

	/**
	 * Returns, but does not remove, the element at the top of the stack.
	 * 
	 * @return the element at the top of the stack
	 * @throws NoSuchElementException if the stack is empty
	 */
	public E peek() throws NoSuchElementException {
		if (top == -1)
			throw new NoSuchElementException();
		return arr[top];
	}

	/**
	 * Returns and removes the item at the top of the stack.
	 * 
	 * @return the element at the top of the stack
	 * @throws NoSuchElementException if the stack is empty
	 */
	public E pop() throws NoSuchElementException {
		if (top == -1)
			throw new NoSuchElementException();
		return arr[top--];
	}

	/**
	 * Adds a given element to the stack, putting it at the top of the stack.
	 * 
	 * @param element - the element to be added
	 */
	@SuppressWarnings("unchecked")
	public void push(E element) {
		// expand the backing array, as needed
		if (top + 1 == arr.length) {
			Object[] temp = new Object[arr.length * 2];
			for (int i = 0; i < arr.length; i++)
				temp[i] = arr[i];
			arr = (E[]) temp;
		}

		arr[++top] = element;
	}

	/**
	 * @return the number of elements in the stack
	 */
	public int size() {
		return top + 1;
	}

	/**
	 * Removes all of the elements from the stack.
	 */
	public void clear() {
		top = -1;
	}

	/**
	 * @return true if the stack contains no elements; false, otherwise.
	 */
	public boolean isEmpty() {
		return top == -1;
	}
}