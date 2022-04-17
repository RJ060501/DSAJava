package assign06Old;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URL;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WebBrowserTest {

	WebBrowser testURLBrowser;
	WebBrowser testStringBrowser;
	URL link1;
	URL link2;
	URL link3;
	URL link4;
	SinglyLinkedList<URL> smallHistory;

	@BeforeEach
	void setUp() throws Exception {

		testURLBrowser = new WebBrowser();
		link1 = new URL("https://a");
		link2 = new URL("https://b");
		link3 = new URL("https://c");
		link4 = new URL("https://d");

		smallHistory = new SinglyLinkedList<>();
		smallHistory.insertFirst(link4);
		smallHistory.insertFirst(link3);
		smallHistory.insertFirst(link2);
		smallHistory.insertFirst(link1);

	}

	@Test
	void testVisitWithHistory() {
		testURLBrowser.visit(link1);
		testURLBrowser.visit(link2);
		testURLBrowser.visit(link3);
		testURLBrowser.visit(link4);

//		System.out.println(Arrays.toString(testURLBrowser.history().toArray()));
//		System.out.println(testURLBrowser.history().getFirst() + "4");
		assertEquals(link4.toString(), testURLBrowser.history().getFirst().toString());
	}

	@Test
	void testGetFirstHistory() {
		// We can pass in Singly Linked List as parameter?
		testURLBrowser = new WebBrowser(smallHistory);
//		System.out.println(Arrays.toString(testURLBrowser.history().toArray()));
//		System.out.println(smallURLBrowser.size());
//		System.out.println(testURLBrowser.history().getFirst().toString() + "getfirsth");
		// Do not get first but get whole history
		URL var = smallHistory.getFirst();
		// D at bottom of stack

		assertEquals(var.toString(), testURLBrowser.history().getFirst().toString());

	}

	@Test
	void testBack() {
		testURLBrowser = new WebBrowser(smallHistory);
		testURLBrowser.back();

		System.out.println(testURLBrowser.history().toString());
		System.out.println(link2.toString());

		assertEquals(link1.toString(), testURLBrowser.forward().toString());
	}

	// current page is on the top of forward stack, forward() test

}
