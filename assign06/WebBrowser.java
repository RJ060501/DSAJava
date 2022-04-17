package assign06;

import java.net.URL;
import java.util.NoSuchElementException;

public class WebBrowser {

	private LinkedListStack<URL> forward;
	private LinkedListStack<URL> back;
	private URL currentPage;

	public WebBrowser() {
		forward = new LinkedListStack<>();
		back = new LinkedListStack<>();
		currentPage = null;

	}

	/**
	 * Construtor that creates a new web browser with a preloaded history of visited
	 * webpages
	 * 
	 * @param history
	 */
	public WebBrowser(SinglyLinkedList<URL> history) {

		forward = new LinkedListStack<>();
		back = new LinkedListStack<>();
		// the current page will be the first item in history
		currentPage = history.getFirst();

		// this will push items onto the back stack from least recent -> most recently
		// visited
		// by retrieving them from the end of history to the beginning.
		int index = history.size() - 1;
		while (index > 0)
			back.push(history.get(index--));

	}

	/**
	 * Method that simulates visiting a webpage, given as a URL.
	 * 
	 * @param webPage
	 */
	public void visit(URL webPage) {
		if (currentPage == null) {
			currentPage = webPage;
		} else {
			back.push(currentPage);
			currentPage = webPage;
		}

		forward.clear();
	}

	/**
	 * Method that simulates using the back button, returning the URL visited
	 * 
	 * @return
	 * @throws NoSuchElementException
	 */
	public URL back() throws NoSuchElementException {
		if (back.isEmpty())
			throw new NoSuchElementException();

		// Add current URL to stack forward
		forward.push(currentPage);
		// Sets variable equal to top of stack
		currentPage = back.pop();
		// Inserts variable to bottom of stack

		return currentPage;
	}

	/**
	 * Method that simulates using the forward button, returning the URL visited.
	 * 
	 * @return
	 * @throws NoSuchElementException
	 */
	public URL forward() throws NoSuchElementException {
		// checks if forward is empty(i.e. is its backing SinglyLinkedLists head==null)
		if (forward.isEmpty()) {
			throw new NoSuchElementException();
		}
		// Push onto back stack
		back.push(currentPage);
		// Set current to top of forward stack
		currentPage = forward.pop();

		return currentPage;

	}

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

	// Pop each URL off back and push it onto backTemp.
	// pop each URL off backTemp and insert each to the beginning of history
	// and push it onto back.
	public SinglyLinkedList<URL> history() {

		LinkedListStack<URL> backTemp = new LinkedListStack<URL>();
		SinglyLinkedList<URL> history = new SinglyLinkedList<URL>();

		while (!back.isEmpty()) {

			// backTemp will be in reverse order of back
			// back will be empty once the loop is exited.
			backTemp.push(back.pop());

		}

		// since backTemp is in reverse order to back this will add them to history
		// in order from most recently visited to least recently visited.
		// since we don't want to make changes to the back stack this will restore
		// back to its original state as backTemp is emptied.
		while (!backTemp.isEmpty()) {
			history.insertFirst(backTemp.peek());
			back.push(backTemp.pop());
		}
		// this adds our current page to our history as the most recently visited URL
		history.insertFirst(currentPage);
		return history;
	}

}
