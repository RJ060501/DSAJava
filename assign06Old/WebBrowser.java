package assign06Old;

import java.net.URL;
import java.util.NoSuchElementException;

import assign06.LinkedListStack;

public class WebBrowser {

	private LinkedListStack<URL> forward;
	private LinkedListStack<URL> back;
	// The only way of keeping track?
	private URL currentPage;
//	SinglyLinkedList<URL> history;

	public WebBrowser() {
		forward = new LinkedListStack<>();
		back = new LinkedListStack<>();
		currentPage = null;
//		history = new SinglyLinkedList<>();
	}

	public WebBrowser(SinglyLinkedList<URL> history) {

		forward = new LinkedListStack<>();
		back = new LinkedListStack<>();
		currentPage = history.getFirst();

		int index = history.size() - 1;

		// If the array list contains elements
		// No modification
		while (index > 0)
			back.push(history.get(index--));

	}

	public void visit(URL webPage) {
		if (currentPage == null) {
			currentPage = webPage;
		} else {
			back.push(currentPage);
			currentPage = webPage;
		}

		forward.clear();
	}

	public URL back() throws NoSuchElementException {
		if (back.isEmpty())
			throw new NoSuchElementException();
		// current page + 1

		// Add current URL to stack forward
		forward.push(currentPage);
		// Sets variable equal to top of stack
		currentPage = back.pop();
		// Inserts variable to bottom of stack

		// Shouldn't just return this? Since, there will be no history attached to the
		// element popped?
		return currentPage;
	}

	public URL forward() throws NoSuchElementException {
		// Checks if current URL is already on top of forward stack
		// Is this the same check?
		if (forward.isEmpty() || currentPage == forward.peek()) {
			throw new NoSuchElementException();
		}
		// Push to back stack
		back.push(currentPage);
		// Set current to top of forward stack
		currentPage = forward.pop();

		return currentPage;

	}

	// Must include current

	/**
	 * This method generates a history of URLs visited, as a list of URL objects
	 * ordered from most recently visited to least recently visited (including the
	 * "current" webpage visited)
	 * 
	 * The behavior of the method must be O(N), where N is the number of URLs.
	 * 
	 * The history method returns a list of URL's, with the "current" webpage as the
	 * first item.
	 * 
	 * @return
	 */

	// Traverse back stack and add to history stack
	public SinglyLinkedList<URL> history() {
		// [First inserted, ... recently inserted] (naturally ordered)
		// For url in back stack, insert to history stack
		// Pop the back stack and push it into the visited list

		LinkedListStack<URL> backTemp = new LinkedListStack<URL>();
		SinglyLinkedList<URL> result = new SinglyLinkedList<URL>();

		// Constant N O(1);
		// While URL is not in back stack
		while (!back.isEmpty()) {
			// FIX: Get: currentNode.data
			// Don't want to modify back or front
			// This temp linkedliststack may be backwards now when we want to access it... {
			// while im pushing to the temp stack I need to copy the variable into the
			// singly list.
			URL var = back.pop();
			// Add element to top of temp stack and head of result list
			// MOST RECENT -->> BEGINNING (HEAD)
			backTemp.push(var);
			// Pop element from back stack (recent) and Push () back stack temp

			// Add end to beginning
			// push var into vistedList
		}
		// Add current page to result end.
		// Back should be empty and backTemp

		// If the array list contains elements
		// FIX: history.isEmpty()
		// No modification
		while (!backTemp.isEmpty()) {
			result.insertFirst(backTemp.peek());
			back.push(backTemp.pop());
		}

		result.insertFirst(currentPage);
		return result;
	}

}
