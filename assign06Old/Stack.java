package assign06Old;

import java.util.NoSuchElementException;

public interface Stack<T> {
	/**
	 * Removes all of the elements from the stack.
	 */
	public void clear();

	/**
	 * @return true if the stack contains no elements; false, otherwise.
	 */
	public boolean isEmpty();

	/**
	 * Returns, but does not remove, the element at the top of the stack.
	 * 
	 * @return the element at the top of the stack
	 * @throws NoSuchElementException if the stack is empty
	 */
	public T peek() throws NoSuchElementException;

	/**
	 * Returns and removes the item at the top of the stack.
	 * 
	 * @return the element at the top of the stack
	 * @throws NoSuchElementException if the stack is empty
	 */
	public T pop() throws NoSuchElementException;

	/**
	 * Adds a given element to the stack, putting it at the top of the stack.
	 * 
	 * @param element - the element to be added
	 */
	public void push(T element);

	/**
	 * @return the number of elements in the stack
	 */
	public int size();
}