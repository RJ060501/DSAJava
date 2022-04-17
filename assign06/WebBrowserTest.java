package assign06;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URL;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WebBrowserTest {

	WebBrowser testBrowser;
	WebBrowser testBrowserWithHistory;

	URL link1;
	URL link2;
	URL link3;
	URL link4;
	URL link5;
	URL urlLink;
	SinglyLinkedList<URL> smallHistory;
	SinglyLinkedList<URL> mediumHistory;

	@BeforeEach
	void setUp() throws Exception {
		testBrowser = new WebBrowser();

		link1 = new URL("https://a");
		link2 = new URL("https://b");
		link3 = new URL("https://c");
		link4 = new URL("https://d");
		link5 = new URL("https://e");

		smallHistory = new SinglyLinkedList<>();
		smallHistory.insertFirst(link4);
		smallHistory.insertFirst(link3);
		smallHistory.insertFirst(link2);
		smallHistory.insertFirst(link1);

		testBrowserWithHistory = new WebBrowser(smallHistory);

	}

	// Test back when history loaded

	@Test
	void testVisitWithHistory() {
		testBrowser.visit(link1);
		testBrowser.visit(link2);
		testBrowser.visit(link3);
		testBrowser.visit(link4);

		assertEquals(link4.toString(), testBrowser.history().getFirst().toString());
	}

	@Test
	void testGetFirstHistory() {
		assertEquals(smallHistory.getFirst().toString(), testBrowserWithHistory.history().getFirst().toString());
	}

	@Test
	void testBack() {
		testBrowser.visit(link2);
		testBrowser.visit(link1);

		assertEquals(link2.toString(), testBrowser.back().toString());
	}

	@Test
	void testBackThrows() {
		testBrowser.visit(link1);

		assertThrows(NoSuchElementException.class, () -> {
			testBrowser.back();
		});
	}

	@Test
	void testBackHistory() {
		assertEquals(link2.toString(), testBrowserWithHistory.back().toString());
	}

	@Test
	void testForward() {
		testBrowser.visit(link2);
		testBrowser.visit(link1);
		testBrowser.back();

		assertEquals(link1.toString(), testBrowser.forward().toString());
	}

	@Test
	void testForwardThrows() {
		testBrowser.visit(link1);
		testBrowser.visit(link2);

		assertThrows(NoSuchElementException.class, () -> {
			testBrowser.forward();
		});
	}

	@Test
	void testHistoryAfterBack() {
		testBrowser.visit(link1);
		testBrowser.visit(link2);
		testBrowser.visit(link3);
		testBrowser.visit(link4);
		testBrowser.back();

		assertEquals(link3.toString(), testBrowser.history().getFirst().toString());
		assertEquals(link2.toString(), testBrowser.history().get(1).toString());
	}

}
