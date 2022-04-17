package assign02;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.util.ArrayList;

/**
 * This class contains tests for CS2420Class.
 * 
 * @author Erin Parker and Solon Grover and Ryan Russel
 * @version January 20, 2022
 */
public class CS2420ClassTester {

	private CS2420Class emptyClass, verySmallClass, smallClass, largeClass, onePersonClass;
	
	@Before
	void setUp() throws Exception {
		emptyClass = new CS2420Class();
		
		verySmallClass = new CS2420Class();
		verySmallClass.addStudent(new CS2420Student("Jane", "Doe", 1010101, new EmailAddress("hi", "gmail.com")));
		verySmallClass.addStudent(new CS2420Student("Drew", "Hall", 2323232, new EmailAddress("howdy", "gmail.com")));
		verySmallClass.addStudent(new CS2420Student("Riley", "Nguyen", 4545454, new EmailAddress("hello", "gmail.com")));

		smallClass = new CS2420Class();
		smallClass.addAll("src/assign02/a_small_2420_class.txt");
		
		largeClass = new CS2420Class();
		for (int i = 0; i < 30; i++) {
			String first = (char)('A' + i % 26) + "" + (char)('b' + i % 26);
			String last = (char)('C' + i % 26) + "" + (char)('d' + i % 26);
			String email = (char)('E' + i % 26) + "" + (char)('f' + i % 26);
			int uNID = 1000000 + i;
			CS2420Student student = new CS2420Student(first, last, uNID, new EmailAddress(email, "gmail.com"));
			largeClass.addStudent(student);
			student.addScore(80 + i % 20, "assignment");
			student.addScore(75, "exam");
			student.addScore(90 + i % 10, "lab");
			student.addScore(80, "lab");
			student.addScore(80 + i % 20, "quiz");
			student.addScore(70, "quiz");
		}
		
		onePersonClass = new CS2420Class();
		onePersonClass.addStudent(new CS2420Student("Ligma", "Deez", 3, new EmailAddress("howdy", "gmail.com")));
		onePersonClass.addScore(3, 100, "nothing");
		
//		CS2420Student student = new CS2420Student("Gilbert", "Hill", 6666666, new EmailAddress("gilhill303", "yemail"));
//		student.addScore(80 % 20, "assignment");
//		student.addScore(75, "exam");
//		student.addScore(90 % 10, "lab");
//		student.addScore(80, "lab");
//		student.addScore(80 % 20, "quiz");
//		student.addScore(70, "quiz");
		largeClass.addStudent(new CS2420Student("Gilbert", "Hill", 6666666, new EmailAddress("gilhill303", "yemail")));
		largeClass.addStudent(new CS2420Student("Chase", "Lawrence", 7777777, new EmailAddress("lawforchase8", "yemail")));
	}
	
	// Empty CS 2420 class tests --------------------------------------------------------------------------

	@Test
	public void testEmptyLookupUNID() {
		assertNull(emptyClass.lookup(1234567));
	}
	
	// Test if we added the score...
	
	@Test
	public void testEmptyAddScore() {
		// ensure no exceptions thrown
		emptyClass.addScore(1234567, 100, "assignment");
	}
	
	@Test
	public void testAddScoreValidCategory() {
		// ensure no exceptions thrown
		onePersonClass.addScore(3, 100, "exam");
		onePersonClass.addScore(3, 100, "assignment");
		onePersonClass.addScore(3, 100, "lab");
		onePersonClass.addScore(3, 100, "quiz");
	}
	
	@Test
	public void testEmptyAddScoreInvalidCategory() {
		// ensure no exceptions thrown
		onePersonClass.addScore(3, 100, "nothing");
	}

	@Test
	public void testEmptyClassAverage() {
		assertEquals(0, emptyClass.computeClassAverage(), 0);
	}
	
	@Test
	public void testEmptyContactList() {
		ArrayList<EmailAddress> contactList = emptyClass.getContactList();
		assertEquals(0, contactList.size());
	}

	// Very small CS 2420 class tests --------------------------------------------------------------------

	@Test
	public void testVerySmallLookupUNID() {
		UofUStudent expected = new UofUStudent("Drew", "Hall", 2323232);
		CS2420Student actual = verySmallClass.lookup(2323232);
		assertEquals(expected, actual);
	}
	
	@Test
	public void testVerySmallLookupContactInfo() {
		UofUStudent expectedStudent = new UofUStudent("Riley", "Nguyen", 4545454);
		ArrayList<CS2420Student> actualStudents = verySmallClass.lookup(new EmailAddress("hello", "gmail.com"));
		assertEquals(1, actualStudents.size());
		assertEquals(expectedStudent, actualStudents.get(0));
	}
	
	@Test
	public void testVerySmallAddDuplicateStudent() {
		boolean actual = verySmallClass.addStudent(new CS2420Student("Jane", "Doe", 1010101, 
				new EmailAddress("hi", "gmail.com")));
		assertFalse(actual);
	}
	
	@Test
	public void testVerySmallAddNewStudent() {
		boolean actual = verySmallClass.addStudent(new CS2420Student("Jane", "Doe", 1010100, 
				new EmailAddress("hi", "gmail.com")));
		assertTrue(actual);		
	}

	@Test
	public void testVerySmallStudentFinalScore0() {
		CS2420Student student = verySmallClass.lookup(2323232);
		student.addScore(86.5, "assignment");
		student.addScore(75, "exam");
		student.addScore(89.2, "quiz");
		assertEquals(0, student.computeFinalScore(), 0);
	}
	
	@Test
	public void testVerySmallStudentFinalGradeNA() {
		CS2420Student student = verySmallClass.lookup(2323232);
		student.addScore(86.5, "assignment");
		student.addScore(75, "exam");
		student.addScore(100, "lab");
		assertEquals("N/A", student.computeFinalGrade());
	}
	
	@Test
	public void testVerySmallStudentFinalScore() {
		CS2420Student student = verySmallClass.lookup(2323232);
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
		CS2420Student student = verySmallClass.lookup(2323232);
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
		CS2420Student student = verySmallClass.lookup(2323232);
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
		CS2420Student student = verySmallClass.lookup(2323232);
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
		CS2420Student student = verySmallClass.lookup(2323232);
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
		CS2420Student student = verySmallClass.lookup(2323232);
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
		CS2420Student student = verySmallClass.lookup(2323232);
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
		CS2420Student student = verySmallClass.lookup(2323232);
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
		CS2420Student student = verySmallClass.lookup(2323232);
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
		CS2420Student student = verySmallClass.lookup(2323232);
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
		CS2420Student student = verySmallClass.lookup(2323232);
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
		CS2420Student student = verySmallClass.lookup(2323232);
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
		CS2420Student student = verySmallClass.lookup(2323232);
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
		CS2420Student student = verySmallClass.lookup(2323232);
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
		ArrayList<CS2420Student> students = verySmallClass.lookup(new EmailAddress("hi", "gmail.com"));
		assertEquals("John", students.get(0).getFirstName());
		assertEquals("Doe", students.get(0).getLastName());
	}	
	
	// Small CS 2420 class tests -------------------------------------------------------------------------

	@Test
	public void testSmallLookupContactInfo() {
		UofUStudent expectedStudent1 = new UofUStudent("Kennedy", "Miller", 888888);
		UofUStudent expectedStudent2 = new UofUStudent("Taylor", "Miller", 999999);

		ArrayList<CS2420Student> actualStudents = smallClass.lookup(new EmailAddress("we_love_puppies", "geemail.com"));
		assertEquals(2, actualStudents.size());
		assertTrue(actualStudents.contains(expectedStudent1));
		assertTrue(actualStudents.contains(expectedStudent2));
	}
	
	@Test
	public void testSmallGetContactList() {
		ArrayList<EmailAddress> actual = smallClass.getContactList();
		assertEquals(9, actual.size());
	}
		
	@Test
	public void testSmallStudentFinalScore() {
		CS2420Student student = smallClass.lookup(333333);
		assertEquals(95.5345, student.computeFinalScore(), 0.001);
	}
		
	@Test
	public void testSmallComputeClassAverage() {
		assertEquals(78.356, smallClass.computeClassAverage(), 0.001);
	}
	
	// Large CS 2420 class tests -------------------------------------------------------------------------
	
	@Test
	public void testLargeLookupUNID() {
		UofUStudent expected = new UofUStudent("Chase", "Lawrence", 7777777);
		CS2420Student actual = largeClass.lookup(7777777);
		assertEquals(expected, actual);
	}
//	@Test
//	public void testLargeLookupContactInfo() {
//		UofUStudent expectedStudent1 = new UofUStudent("Gilbert", "Hill", 6666666);
//		UofUStudent expectedStudent2 = new UofUStudent("Chase", "Lawrence", 7777777);
//
//		ArrayList<CS2420Student> actualStudents = largeClass.lookup(new EmailAddress("gilhill303", "yemail.com"));
//		assertEquals(0, actualStudents.size());
//		assertTrue(actualStudents.contains(expectedStudent1));
//		assertTrue(actualStudents.contains(expectedStudent2));
//	}
//	@Test
//	public void testLargeLookupContactInfo() {
//		UofUStudent expectedStudent = new UofUStudent("Riley", "Nguyen", 4545454);
//		ArrayList<CS2420Student> actualStudents = largeClass.lookup(new EmailAddress("hello", "gmail.com"));
//		System.out.println(actualStudents.size());
//		assertEquals(1, actualStudents.size());
//		assertEquals(expectedStudent, actualStudents.get(0));
//	}
	
	@Test
	public void testLargeGetContactList() {
		ArrayList<EmailAddress> actual = largeClass.getContactList();
		assertEquals(28, actual.size());
	}
	
	@Test
	public void testLargeAddDuplicateStudent() {
		boolean actual = largeClass.addStudent(new CS2420Student("Gilbert", "Hill", 6666666, 
				new EmailAddress("gilhill303", "yemail.com")));
		assertFalse(actual);
	}
	
	@Test
	public void testLargeAddNewStudent() {
		boolean actual = largeClass.addStudent(new CS2420Student("Connor", "Rugg", 6686696, 
				new EmailAddress("yesrugg", "ysemail.com")));
		assertTrue(actual);		
	}
	
	@Test
	public void testLargeStudentFinalScore0() {
		CS2420Student student = largeClass.lookup(6666666);
		student.addScore(86.5, "assignment");
		student.addScore(75, "exam");
		student.addScore(89.2, "quiz");
		assertEquals(0, student.computeFinalScore(), 0);
	}
	
	@Test
	public void testLargeStudentFinalGradeNA() {
		CS2420Student student = largeClass.lookup(6666666);
		student.addScore(86.5, "assignment");
		student.addScore(75, "exam");
		student.addScore(100, "lab");
		assertEquals("N/A", student.computeFinalGrade());
	}
	
	@Test
	public void testLargeStudentFinalScore() {
		CS2420Student student = largeClass.lookup(6666666);
		student.addScore(86.5, "assignment");
		student.addScore(75, "exam");
		student.addScore(89.2, "quiz");
		assertEquals(0, student.computeFinalScore(), 0);
	}
	
	@Test
	public void testLargeStudentFinalGrade() {
		CS2420Student student = largeClass.lookup(6666666);
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
	public void testLargeStudentComputeScoreTwice() {
		CS2420Student student = largeClass.lookup(6666666);
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
	public void testLargeComputeClassAverage() {
		assertEquals(76.6406, largeClass.computeClassAverage(), 0.001);
	}
	
	@Test
	public void testLargeUpdateName() {
		largeClass.lookup(6666666).updateName("Brugma", "Cat");
		assertEquals("Brugma", largeClass.lookup(6666666).getFirstName());
		assertEquals("Cat", largeClass.lookup(6666666).getLastName());
	}	
	
//	@Test
//	public void missingLines() {
//		Throwable thrown = assertThrows(ParseException.class, () -> { verySmallClass.parseStudent("Samantha Smith (u0111111)", 6); });
//	    assertEquals("Attempted to parse null", thrown.getMessage());
//		assertThrows(ParseException.class, () -> { verySmallClass.parseStudent("Samantha Smith", 6); });
//		assertThrows(ParseException.class, () -> { verySmallClass.parseStudent("Samantha", 6); });
//		assertThrows(ParseException.class, () -> { verySmallClass.parseStudent("", 6); });
//	}
	
}