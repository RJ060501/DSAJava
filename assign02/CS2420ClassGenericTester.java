package assign02;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * This class contains tests for CS2420ClassGeneric.
 * 
 * @author Erin Parker and Solon Grover and Ryan Russel
 * @version January 20, 2022
 */
public class CS2420ClassGenericTester {

	private CS2420ClassGeneric<String> emptyClass;
	private CS2420ClassGeneric<MailingAddress> verySmallClass;
	private CS2420ClassGeneric<PhoneNumber> largeClass;
	private CS2420ClassGeneric<Integer> phase3Class;
	private CS2420ClassGeneric<Integer> sameLastNameClass;
	private CS2420ClassGeneric<Integer> onePersonClass;

	@Before
	public void setUp() throws Exception {
		emptyClass = new CS2420ClassGeneric<String>();
		
		verySmallClass = new CS2420ClassGeneric<MailingAddress>();
		verySmallClass.addStudent(new CS2420StudentGeneric<MailingAddress>("Jane", "Doe", 1010101, 
				new MailingAddress("101 Cherry St.", "Lebanon", "OH", 45036)));
		verySmallClass.addStudent(new CS2420StudentGeneric<MailingAddress>("Drew", "Hall", 2323232, 
				new MailingAddress("156 Main St.", "Lebanon", "VA", 24266)));
		verySmallClass.addStudent(new CS2420StudentGeneric<MailingAddress>("Riley", "Nguyen", 4545454, 
				new MailingAddress("2044 State St.", "Lebanon", "PA", 17042)));

		largeClass = new CS2420ClassGeneric<PhoneNumber>();
		PhoneNumber[] sharedNums = new PhoneNumber[5]; 
		sharedNums[0] = new PhoneNumber("801-555-1234");
		sharedNums[1] = new PhoneNumber("801-555-5678");
		sharedNums[2] = new PhoneNumber("801-555-9012");
		sharedNums[3] = new PhoneNumber("801-555-3456");
		sharedNums[4] = new PhoneNumber("801-555-7890");
		for(int i = 0; i < 500; i++) {
			String first = (char)('A' + i % 26) + "" + (char)('b' + i % 26);
			String last = (char)('C' + i % 26) + "" + (char)('d' + i % 26);
			int uNID = 1000000 + i;
			CS2420StudentGeneric<PhoneNumber> student = new CS2420StudentGeneric<PhoneNumber>(first, last, uNID, sharedNums[i % 5]);
			largeClass.addStudent(student);
			student.addScore(80 + i % 20, "assignment");
			student.addScore(75, "exam");
			student.addScore(90 + i % 10, "lab");
			student.addScore(80, "lab");
			student.addScore(80 + i % 20, "quiz");
			student.addScore(70, "quiz");
		}
		
		phase3Class = new CS2420ClassGeneric<Integer>();
		phase3Class.addStudent(new CS2420StudentGeneric<Integer>("A", "C", 3, 3));
		phase3Class.addStudent(new CS2420StudentGeneric<Integer>("A", "B", 2, 2));
		phase3Class.addStudent(new CS2420StudentGeneric<Integer>("A", "C", 1, 1));
		phase3Class.addStudent(new CS2420StudentGeneric<Integer>("D", "E", 4, 4));
		phase3Class.addScore(1, 70, "assignment");
		phase3Class.addScore(1, 70, "exam");
		phase3Class.addScore(1, 70, "lab");   phase3Class.addScore(1, 70, "lab");
		phase3Class.addScore(1, 70, "quiz");  phase3Class.addScore(1, 70, "quiz");
		phase3Class.addScore(2, 30, "assignment");
		phase3Class.addScore(2, 30, "exam");
		phase3Class.addScore(2, 30, "lab");   phase3Class.addScore(2, 30, "lab");
		phase3Class.addScore(2, 30, "quiz");  phase3Class.addScore(2, 30, "quiz");
		phase3Class.addScore(3, 70, "assignment");
		phase3Class.addScore(3, 70, "exam");
		phase3Class.addScore(3, 70, "lab");   phase3Class.addScore(3, 70, "lab");
		phase3Class.addScore(3, 70, "quiz");  phase3Class.addScore(3, 70, "quiz");
		phase3Class.addScore(4, 90, "assignment");
		phase3Class.addScore(4, 90, "exam");
		phase3Class.addScore(4, 90, "lab");   phase3Class.addScore(4, 90, "lab");
		phase3Class.addScore(4, 90, "quiz");  phase3Class.addScore(4, 90, "quiz");
		
		sameLastNameClass = new CS2420ClassGeneric<Integer>();
		sameLastNameClass.addStudent(new CS2420StudentGeneric<Integer>("A", "Bruh", 3, 3));
		sameLastNameClass.addStudent(new CS2420StudentGeneric<Integer>("M", "Bruh", 2, 2));
		sameLastNameClass.addStudent(new CS2420StudentGeneric<Integer>("A", "C", 1, 1));
		sameLastNameClass.addStudent(new CS2420StudentGeneric<Integer>("D", "E", 4, 4));
		
		onePersonClass = new CS2420ClassGeneric<Integer>();
		onePersonClass.addStudent(new CS2420StudentGeneric<Integer>("Ligma", "Deez", 3, 3));
		onePersonClass.addScore(3, 100, "nothing");
	}
	
	// Empty CS 2420 class tests --------------------------------------------------------------------------

	@Test
	public void testEmptyLookupUNID() {
		assertNull(emptyClass.lookup(1234567));
	}
	
	@Test
	public void testEmptyLookupContactInfo() {
		ArrayList<CS2420StudentGeneric<String>> students = emptyClass.lookup("hello");
		assertEquals(0, students.size());
	}
	
	@Test
	public void testEmptyAddScore() {
		// ensure no exceptions thrown
		emptyClass.addScore(1234567, 100, "assignment");
	}
	
	@Test
	public void testAddScoreInvalidCategory() {
		// ensure no exceptions thrown
		onePersonClass.addScore(3, 100, "nothing");
	}

	@Test
	public void testEmptyClassAverage() {
		assertEquals(0, emptyClass.computeClassAverage(), 0);
	}
	
	@Test
	public void testEmptyContactList() {
		ArrayList<String> contactList = emptyClass.getContactList();
		assertEquals(0, contactList.size());
	}

	// Very small CS 2420 class tests --------------------------------------------------------------------

	@Test
	public void testVerySmallLookupUNID() {
		UofUStudent expected = new UofUStudent("Drew", "Hall", 2323232);
		CS2420StudentGeneric<MailingAddress> actual = verySmallClass.lookup(2323232);
		assertEquals(expected, actual);
	}
	
	@Test
	public void testVerySmallLookupContactInfo() {
		UofUStudent expectedStudent = new UofUStudent("Riley", "Nguyen", 4545454);
		ArrayList<CS2420StudentGeneric<MailingAddress>> actualStudents = verySmallClass.lookup(
				new MailingAddress("2044 State St.", "Lebanon", "PA", 17042));
		assertEquals(1, actualStudents.size());
		assertEquals(expectedStudent, actualStudents.get(0));
	}
	
	@Test
	public void testVerySmallAddDuplicateStudent() {
		boolean actual = verySmallClass.addStudent(new CS2420StudentGeneric<MailingAddress>("Jane", "Doe", 1010101, 
				new MailingAddress("101 Cherry St.", "Lebanon", "OH", 45036)));
		assertFalse(actual);
	}
	
	@Test
	public void testVerySmallAddNewStudent() {
		boolean actual = verySmallClass.addStudent(new CS2420StudentGeneric<MailingAddress>("Jane", "Doe", 1010100, 
				new MailingAddress("101 Cherry St.", "Lebanon", "OH", 45036)));
		assertTrue(actual);		
	}

	@Test
	public void testVerySmallStudentFinalScore0() {
		CS2420StudentGeneric<MailingAddress> student = verySmallClass.lookup(2323232);
		student.addScore(86.5, "assignment");
		student.addScore(75, "exam");
		student.addScore(89.2, "quiz");
		assertEquals(0, student.computeFinalScore(), 0);
	}
	
	@Test
	public void testVerySmallStudentFinalGradeNA() {
		CS2420StudentGeneric<MailingAddress> student = verySmallClass.lookup(2323232);
		student.addScore(86.5, "assignment");
		student.addScore(75, "exam");
		student.addScore(100, "lab");
		assertEquals("N/A", student.computeFinalGrade());
	}
	
	@Test
	public void testVerySmallStudentFinalScore() {
		CS2420StudentGeneric<MailingAddress> student = verySmallClass.lookup(2323232);
		student.addScore(86.5, "assignment");
		student.addScore(55, "exam");
		student.addScore(90, "lab");
		student.addScore(89.2, "quiz");
		student.addScore(99, "assignment");
		student.addScore(80, "lab");
		student.addScore(77.7, "quiz");
		assertEquals(55, student.computeFinalScore(), 0.001);
	}
	
	@Test
	public void testVerySmallStudentFinalGradeA() {
		CS2420StudentGeneric<MailingAddress> student = verySmallClass.lookup(2323232);
		student.addScore(100, "assignment");
		student.addScore(100, "exam");
		student.addScore(100, "lab");
		student.addScore(100, "quiz");
		student.addScore(100, "assignment");
		student.addScore(100, "lab");
		student.addScore(100, "quiz");
		assertEquals("A", student.computeFinalGrade());
	}
	
	@Test
	public void testVerySmallStudentFinalGradeAMinus() {
		CS2420StudentGeneric<MailingAddress> student = verySmallClass.lookup(2323232);
		student.addScore(92, "assignment");
		student.addScore(93, "exam");
		student.addScore(90, "lab");
		student.addScore(90, "quiz");
		student.addScore(92, "assignment");
		student.addScore(93, "lab");
		student.addScore(92, "quiz");
		assertEquals("A-", student.computeFinalGrade());
	}
	
	@Test
	public void testVerySmallStudentFinalGradeBPlus() {
		CS2420StudentGeneric<MailingAddress> student = verySmallClass.lookup(2323232);
		student.addScore(87.5, "assignment");
		student.addScore(88, "exam");
		student.addScore(90, "lab");
		student.addScore(89.2, "quiz");
		student.addScore(87.3, "assignment");
		student.addScore(89, "lab");
		student.addScore(89.5, "quiz");
		assertEquals("B+", student.computeFinalGrade());
	}
	
	@Test
	public void testVerySmallStudentFinalGradeB() {
		CS2420StudentGeneric<MailingAddress> student = verySmallClass.lookup(2323232);
		student.addScore(86.5, "assignment");
		student.addScore(75, "exam");
		student.addScore(90, "lab");
		student.addScore(89.2, "quiz");
		student.addScore(99, "assignment");
		student.addScore(80, "lab");
		student.addScore(77.7, "quiz");
		assertEquals("B", student.computeFinalGrade());
	}
	
	@Test
	public void testVerySmallStudentFinalGradeBMinus() {
		CS2420StudentGeneric<MailingAddress> student = verySmallClass.lookup(2323232);
		student.addScore(80, "assignment");
		student.addScore(83.5, "exam");
		student.addScore(81.5, "lab");
		student.addScore(79, "quiz");
		student.addScore(81, "assignment");
		student.addScore(82.5, "lab");
		student.addScore(83, "quiz");
		assertEquals("B-", student.computeFinalGrade());
	}
	
	@Test
	public void testVerySmallStudentFinalGradeCPlus() {
		CS2420StudentGeneric<MailingAddress> student = verySmallClass.lookup(2323232);
		student.addScore(77.5, "assignment");
		student.addScore(80, "exam");
		student.addScore(78, "lab");
		student.addScore(79.5, "quiz");
		student.addScore(79, "assignment");
		student.addScore(80, "lab");
		student.addScore(77, "quiz");
		assertEquals("C+", student.computeFinalGrade());
	}
	
	@Test
	public void testVerySmallStudentFinalGradeC() {
		CS2420StudentGeneric<MailingAddress> student = verySmallClass.lookup(2323232);
		student.addScore(77.5, "assignment");
		student.addScore(72, "exam");
		student.addScore(75, "lab");
		student.addScore(79.5, "quiz");
		student.addScore(73, "assignment");
		student.addScore(73.5, "lab");
		student.addScore(74, "quiz");
		assertEquals("C", student.computeFinalGrade());
	}
	
	@Test
	public void testVerySmallStudentFinalGradeCMinus() {
		CS2420StudentGeneric<MailingAddress> student = verySmallClass.lookup(2323232);
		student.addScore(70, "assignment");
		student.addScore(71.5, "exam");
		student.addScore(72, "lab");
		student.addScore(73.5, "quiz");
		student.addScore(69, "assignment");
		student.addScore(74, "lab");
		student.addScore(74, "quiz");
		assertEquals("C-", student.computeFinalGrade());
	}
	
	@Test
	public void testVerySmallStudentFinalGradeDPlus() {
		CS2420StudentGeneric<MailingAddress> student = verySmallClass.lookup(2323232);
		student.addScore(67, "assignment");
		student.addScore(67.5, "exam");
		student.addScore(70, "lab");
		student.addScore(68, "quiz");
		student.addScore(69, "assignment");
		student.addScore(70, "lab");
		student.addScore(78.5, "quiz");
		assertEquals("D+", student.computeFinalGrade());
	}
	
	@Test
	public void testVerySmallStudentFinalGradeD() {
		CS2420StudentGeneric<MailingAddress> student = verySmallClass.lookup(2323232);
		student.addScore(63, "assignment");
		student.addScore(67.5, "exam");
		student.addScore(64.5, "lab");
		student.addScore(64, "quiz");
		student.addScore(67.5, "assignment");
		student.addScore(62, "lab");
		student.addScore(65, "quiz");
		assertEquals("D", student.computeFinalGrade());
	}
	
	@Test
	public void testVerySmallStudentFinalGradeDMinus() {
		CS2420StudentGeneric<MailingAddress> student = verySmallClass.lookup(2323232);
		student.addScore(63, "assignment");
		student.addScore(62, "exam");
		student.addScore(61, "lab");
		student.addScore(62.5, "quiz");
		student.addScore(63.5, "assignment");
		student.addScore(60, "lab");
		student.addScore(60, "quiz");
		assertEquals("D-", student.computeFinalGrade());
	}
	
	@Test
	public void testVerySmallStudentFinalGradeE() {
		CS2420StudentGeneric<MailingAddress> student = verySmallClass.lookup(2323232);
		student.addScore(55, "assignment");
		student.addScore(55.5, "exam");
		student.addScore(59, "lab");
		student.addScore(58, "quiz");
		student.addScore(55, "assignment");
		student.addScore(54, "lab");
		student.addScore(59, "quiz");
		assertEquals("E", student.computeFinalGrade());
	}
	
	@Test
	public void testVerySmallStudentComputeScoreTwice() {
		CS2420StudentGeneric<MailingAddress> student = verySmallClass.lookup(2323232);
		student.addScore(86.5, "assignment");
		student.addScore(75, "exam");
		student.addScore(90, "lab");
		student.addScore(89.2, "quiz");
		student.addScore(99, "assignment");
		student.addScore(80, "lab");
		student.addScore(77.7, "quiz");
		student.computeFinalScore(); 
		student.addScore(70, "lab");
		student.addScore(54.5, "exam");				
		assertEquals(64.75, student.computeFinalScore(), 0.001);
	}

	@Test
	public void testVerySmallUpdateName() {
		verySmallClass.lookup(1010101).updateName("John", "Doe");
		ArrayList<CS2420StudentGeneric<MailingAddress>> students = verySmallClass.lookup(
				new MailingAddress("101 Cherry St.", "Lebanon", "OH", 45036));
		assertEquals("John", students.get(0).getFirstName());
		assertEquals("Doe", students.get(0).getLastName());
	}	
	
	// Large CS 2420 class tests -------------------------------------------------------------------------

	@Test
	public void testLargeLookupContactInfo() {
		ArrayList<CS2420StudentGeneric<PhoneNumber>> actualStudents = largeClass.lookup(new PhoneNumber("801-555-1234"));
		assertEquals(100, actualStudents.size());
	}
	
	@Test
	public void testLargeGetContactList() {
		ArrayList<PhoneNumber> actual = largeClass.getContactList();
		assertEquals(5, actual.size());
		assertTrue(actual.contains(new PhoneNumber("801-555-1234")));
		assertTrue(actual.contains(new PhoneNumber("801-555-5678")));
		assertTrue(actual.contains(new PhoneNumber("801-555-9012")));
		assertTrue(actual.contains(new PhoneNumber("801-555-3456")));
		assertTrue(actual.contains(new PhoneNumber("801-555-7890")));
	}
		
	@Test
	public void testLargeStudentFinalScore() {
		CS2420StudentGeneric<PhoneNumber> student = largeClass.lookup(1000000);
		assertEquals(78, student.computeFinalScore(), 0.001);
	}
		
	@Test
	public void testLargeComputeClassAverage() {
		assertEquals(82.5, largeClass.computeClassAverage(), 0.001);
	}
	
	// Phase 3 tests -----------------------------------------------------------------------------------

	@Test
	public void testOrderedByUNID() {
		ArrayList<CS2420StudentGeneric<Integer>> actual = phase3Class.getOrderedByUNID();
		assertEquals(4, actual.size());
		assertEquals(new CS2420StudentGeneric<Integer>("A", "C", 3, 3), actual.get(2));
		assertEquals(new CS2420StudentGeneric<Integer>("A", "B", 2, 2), actual.get(1));
		assertEquals(new CS2420StudentGeneric<Integer>("A", "C", 1, 1), actual.get(0));
		assertEquals(new CS2420StudentGeneric<Integer>("D", "E", 4, 4), actual.get(3));
	}
	
	@Test
	public void testOrderedByName() {
		ArrayList<CS2420StudentGeneric<Integer>> actual = phase3Class.getOrderedByName();
		assertEquals(4, actual.size());
		assertEquals(new CS2420StudentGeneric<Integer>("A", "C", 3, 3), actual.get(2));
		assertEquals(new CS2420StudentGeneric<Integer>("A", "B", 2, 2), actual.get(0));
		assertEquals(new CS2420StudentGeneric<Integer>("A", "C", 1, 1), actual.get(1));
		assertEquals(new CS2420StudentGeneric<Integer>("D", "E", 4, 4), actual.get(3));
	}
	
	@Test
	public void testOrderedByScore() {
		ArrayList<CS2420StudentGeneric<Integer>> actual = phase3Class.getOrderedByScore(0);
		assertEquals(4, actual.size());
		assertEquals(new CS2420StudentGeneric<Integer>("A", "C", 3, 3), actual.get(2));
		assertEquals(new CS2420StudentGeneric<Integer>("A", "B", 2, 2), actual.get(3));
		assertEquals(new CS2420StudentGeneric<Integer>("A", "C", 1, 1), actual.get(1));
		assertEquals(new CS2420StudentGeneric<Integer>("D", "E", 4, 4), actual.get(0));
	}
	
	@Test
	public void testAboveCuttOffScore() {
		ArrayList<CS2420StudentGeneric<Integer>> actual = phase3Class.getOrderedByScore(0);
		assertEquals(4, actual.size());
		assertTrue("No one should have been disqualified, the minimum is 0", actual.size() == 4);
	}
	
	@Test
	public void testBelowCuttOffScore() {
		ArrayList<CS2420StudentGeneric<Integer>> actual = phase3Class.getOrderedByScore(100);
		assertEquals(0, actual.size());
		assertTrue("Everyone should have been disqualified, the minimum is 100", actual.size() == 0);
	}
	
	@Test
	public void testOrderedByNameWithSameLastName() {
		ArrayList<CS2420StudentGeneric<Integer>> actual = sameLastNameClass.getOrderedByName();
		assertEquals(4, actual.size());
		assertEquals(new CS2420StudentGeneric<Integer>("A", "Bruh", 3, 3), actual.get(0));
		assertEquals(new CS2420StudentGeneric<Integer>("M", "Bruh", 2, 2), actual.get(1));
		assertEquals(new CS2420StudentGeneric<Integer>("A", "C", 1, 1), actual.get(2));
		assertEquals(new CS2420StudentGeneric<Integer>("D", "E", 4, 4), actual.get(3));
	}
}