package assign03;

import java.util.Collection;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class SimplePriorityQueue<E> implements PriorityQueue<E> {
	// Backing array
	private E[] arr;
	
	// Is null if no custom comparator is passed
	private Comparator<? super E> cmp;
	
	// Number of elements in the array
	private int numSize;
	
	/**
	 * Constructor for queues using natural ordering
	 */
	@SuppressWarnings("unchecked")
	public SimplePriorityQueue() {
		this.arr = (E[]) new Object[16];
		this.cmp = null;
		this.numSize = 0;
	}
	
	/**
	 * Constructor for queues using custom comparators / unnatural ordering
	 * @param cmp
	 */
	@SuppressWarnings("unchecked")
	public SimplePriorityQueue(Comparator<? super E> cmp) {
		this.arr = (E[]) new Object[16];
		this.cmp = cmp;
		this.numSize = 0;
	}

	/**
	 * Retrieves, but does not remove, the maximum element in this priority
	 * queue.
	 * 
	 * @return the maximum element
	 * @throws NoSuchElementException if the priority queue is empty
	 */
	@Override
	public E findMax() throws NoSuchElementException {
		// If arr size is greater than zero, returns max
		if (numSize > 0) {
			return arr[numSize - 1];
		} else {
			throw new NoSuchElementException();
		}
	}

	/**
	 * Retrieves and removes the maximum element in this priority queue.
	 * 
	 * @return the maximum element
	 * @throws NoSuchElementException if the priority queue is empty
	 */
	@Override
	public E deleteMax() throws NoSuchElementException {
		// If arr size is greater than zero, nullifies max, returns max before the nullification
		if (numSize > 0) {
			E max = arr[numSize - 1];
			// Not necessary? --> 	arr[numSize - 1] = null;
			
			// Decrease size
			numSize--;
			return max;
		} else {
			throw new NoSuchElementException();
		}
	}
	
	/**
	 * Takes the index of the middle object in the array and the new item and compares them
	 * using the custom comparator or the natural comparison
	 * @param mid - the index of the middle item
	 * @param item - the object we're trying to find the position of in the array
	 * @return the "difference" either positive or negative. Result depends on the method of comparison
	 */
	private int useCurrentCmp(int mid, E item) {
		if (cmp == null) {
			return ((Comparable<? super E>) arr[mid]).compareTo((E)item);
		} else {
			return cmp.compare(arr[mid], (E)item);
		}
	}
	
	/**
	 * Returns the integer index in the place where the item should go
	 * @param item - the object we're trying to find the position of in the array
	 * @return
	 */
	private int binarySearch (E item) {
		int low = 0, high = numSize - 1, mid = 0;
		while(low <= high) {
			mid = (low + high) / 2;

			// diffMidAndItem depends on which constructor is used
			int diffMidAndItem = useCurrentCmp(mid, item);
			
			// If we skip forwards past the desired location for item
			if (low == mid && diffMidAndItem > 0)
				return low;
			
			// If we skip backwards past the desired location for item
			if (high == mid && diffMidAndItem < 0)
				return high + 1;
			
			// If item is the same as one in the array
			if (diffMidAndItem == 0) 
				return mid;
			// If mid comes after item
			else if (diffMidAndItem > 0) 
				high = mid - 1;
			// If mid comes before item
			else if (diffMidAndItem < 0) 
				low = mid + 1;
		}

		return numSize;
	}

	/**
	 * Inserts the specified element into this priority queue.
	 * 
	 * @param item - the element to insert
	 */
	@Override
	public void insert(E item) {
		// If size is great enough to replace items behind it and item doesn't come first
		if (numSize > 0) {
			// If the number of elements in the old array plus one is greater than it's length (if there is no more empty space) 
			// we create a new array twice the length of the old one
			if (numSize + 1 > arr.length) {
				// Creates new array that we will use to replace old array
				E[] newArr = (E[]) new Object[arr.length*2];
				
				// Loop through each element preceding arr[i] and assign them to newArr
				int itemIndex = binarySearch(item);
				for (int i = 0; i < itemIndex; i++) {
					newArr[i] = arr[i];
				}

				// Put item in array
				newArr[itemIndex] = (E)item;
				
				// Loop through each element coming after where the item was placed and assign them to newArr
				for (int i = itemIndex + 1; i < numSize + 1; i++) {
					newArr[i] = arr[i - 1];
				}
				
				// Writes the newArr to arr
				arr = newArr;
				
				// Increase size
				numSize++;
			// If we don't need to resize the array
			} else {
				// The index of where the item should go
				int itemIndex = binarySearch(item);

				// Place to put displaced object
				E nextObjGoingIn = arr[itemIndex];
				
				// Put item in array
				arr[itemIndex] = (E)item;
				
				// Loop through each element coming after where the item was placed and assign them to newArr
				for (int i = itemIndex + 1; i < numSize + 1; i++) {
					E temp = arr[i];
					arr[i] = nextObjGoingIn;
					nextObjGoingIn = temp;
				}
				
				// Increase size
				numSize++;
			}
		// If the array is new
		} else {
			arr[0] = (E)item;
			
			// Increase size
			numSize++;
		}
	}

	/**
	 * Inserts the specified elements into this priority queue.
	 * 
	 * @param coll - the collection of elements to insert
	 */
	@Override
	public void insertAll(Collection<? extends E> coll) {
		for (E element : coll) {
			this.insert(element);
		}
	}

	/**
	 * Indicates whether this priority queue contains the specified element.
	 * 
	 * @param item - the element to be checked for containment in this priority queue
	 */
	@Override
	public boolean contains(E item) {
		// Calls binarySearch and receives the index where the item should go, checks if the place in the array is equal to item in question
		if (arr[binarySearch(item)] != null && arr[binarySearch(item)].equals(item))
			return true;
		
		return false;
	}
	
	/**
	 * @return the number of elements in this priority queue
	 */
	@Override
	public int size() {
		return numSize;	
	}

	/**
	 * @return true if this priority queue contains no elements, false otherwise
	 */
	@Override
	public boolean isEmpty() {
		if (numSize == 0)
			return true;
		
		return false;
	}
	
	/**
	 * Removes all of the elements from this priority queue. The queue will be
	 * empty when this call returns.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void clear() {
		numSize = 0;
		this.arr = (E[]) new Object[16];
	}
}
