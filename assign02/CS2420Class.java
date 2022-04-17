package assign02;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This Java class represents an unordered collection of University of Utah students enrolled in CS 2420.
 * 
 * NOTE: The word "Class" in the name of this Java class means a collection of students and should not 
 *       be confused with the Java term class, which is a blueprint for making objects.
 * 
 * @author Erin Parker and Solon Grover and Ryan Russel
 * @version January 20, 2022
 */
public class CS2420Class {

	private ArrayList<CS2420Student> studentList;
	
	/**
	 * Creates an empty CS 2420 class.
	 */
	public CS2420Class() {
		this.studentList = new ArrayList<CS2420Student>();
	}
	
	/**
	 * Adds the given student to the collection of students in CS 2420, avoiding duplicates.
	 * 
	 * @param student - student to be added to this CS 2420 class
	 * @return true if the student was added, 
	 *         false if the student was not added because they already exist in the collection
	 */
	public boolean addStudent(CS2420Student student) {
		// Loops through student list to find duplicates, if so returns false
		for (int i = 0; i < studentList.size(); i++) {
			if (studentList.get(i).equals(student))
				return false;
		}
		
		// Adds student to student list and returns true
		studentList.add(student);
		return true;
	}
	
	/**
	 * Retrieves the CS 2420 student with the given uNID.
	 * 
	 * @param uNID - uNID of student to be retrieved
	 * @return the CS 2420 student with the given uNID, or null if no such student exists in the collection
	 */
	public CS2420Student lookup(int uNID) {
		// Loops through each student list to find one that matches the uNID
		for (int i = 0; i < studentList.size(); i++) {
			if (studentList.get(i).getUNID() == uNID)
				return studentList.get(i);
		}
		return null;
	}
	
	/**
	 * Retrieves the CS 2420 student(s) with the given contact information.
	 * 
	 * @param contactInfo - contact information of student(s) to be retrieved
	 * @return a list of the CS 2420 student(s) with the given contact information (in any order), 
	 * 	     or an empty list if no such students exist in the collection
	 */
	public ArrayList<CS2420Student> lookup(EmailAddress contactInfo) {
		// Creates empty list of students
		ArrayList<CS2420Student> students = new ArrayList<CS2420Student>();
		
		// Adds students to list if they have the desired contactInfo
		for (int i = 0; i < studentList.size(); i++) {
			if (studentList.get(i).getContactInfo().equals(contactInfo))
				students.add(studentList.get(i));
		}
		
		// returns list of students, empty or not
		return students;
	}
	
	/**
	 * Adds an assignment, exam, lab, or quiz score for the CS 2420 student with the given uNID.
	 * 
	 * NOTE: If the category string is not one of "assignment", "exam", "lab", or "quiz", or
	 *       if no student with the uNID exists in the collection, then this method has no effect.
	 * 
	 * @param uNID - uNID of student whose score is to be added
	 * @param score - the score to be added
	 * @param category - the category in which to add the score
	 */
	public void addScore(int uNID, double score, String category) {
		// Checks to make sure student with uNID exists, then makes sure category entry is valid
		if (lookup(uNID) != null)
			if (category.equals("assignment") || category.equals("exam") || category.equals("lab") || category.equals("quiz"))
				// Adds score to student
				lookup(uNID).addScore(score, category);
	}
	
	/**
	 * Computes the average score of all CS 2420 students in the collection.
	 * 
	 * @return the average score, or 0 if there are no students in the collection
	 */
	public double computeClassAverage() {
		// Returns 0 if class size is 0 to prevent dividing by 0 later on
		if (studentList.size() == 0)
			return 0;
		
		// Adds all final scores of each student and divides by class size for average
		double finalScoresSum = 0;
		
		for (int i = 0; i < studentList.size(); i++) {
			finalScoresSum += studentList.get(i).computeFinalScore();
		}
		
		return finalScoresSum / studentList.size();
	}
	
	/**
	 * Creates a list of contact information for all CS 2420 students in the collection.
	 *
	 * @return the duplicate-free list of contact information, in any order
	 */
	public ArrayList<EmailAddress> getContactList() {
		// Creates empty list of emails
		ArrayList<EmailAddress> emails = new ArrayList<EmailAddress>();
		
		// Adds emails to list
		for (int i = 0; i < studentList.size(); i++) {
			emails.add(studentList.get(i).getContactInfo());
		}
		
		// Creates another list of emails to remove so that the first list of emails size isn't changing while we're removing things
		ArrayList<EmailAddress> thingsToRemove = new ArrayList<EmailAddress>();
		
		// Loops through email list to find duplicates and adds them to the things to remove list
		for (int i = 0; i < emails.size(); i++) {
			// Checks to make sure we are in bounds
			if(i + 1 < emails.size())
				for (int j = i + 1; j < emails.size(); j++) {
					if (emails.get(i).equals(emails.get(j)))
						thingsToRemove.add(emails.get(j));
				}
		}
		
		for (int i = 0; i < thingsToRemove.size(); i++) {
			emails.remove(thingsToRemove.get(i));
		}
		
		// returns list of emails
		return emails;
	}
	
	/**
	 * Adds the students specified by the input file to the collection of students in CS 2420.
	 * 
	 * Assumes a very strict file format:
	 *     -- first line: FirstName LastName (u0123456) userName@domainName
	 *     -- second line: assignment scores, separated by blank spaces
	 *     -- third line: exam scores, separated by blank spaces
	 *     -- fourth line: lab scores, separated by blank spaces
	 *     -- fifth line: quiz scores, separated by blank spaces
	 *     -- sixth line ... : repeat of lines 1-5 for another student
	 *     
	 * Also assumes there are no duplicate students in the file.
	 * 
	 * @param filename - full or relative path to file containing student data
	 */
	public void addAll(String filename) {
		String[] categories = { "assignment", "exam", "lab", "quiz" };
		
		try {
			Scanner fileIn = new Scanner(new File(filename));
			int lineNumber = 0;

			while(fileIn.hasNextLine()) {
				
				// first line: FirstName LastName (u0123456) userName@domainName
				String line = fileIn.nextLine();
				lineNumber++;
				CS2420Student student = parseStudent(line, lineNumber);
				
				// second-fifth lines: assignment, exam, lab, and quiz scores
				for(int i = 0; i < 4; i++) {
					// make sure there is a next line ...
					if(!fileIn.hasNextLine()) {
						fileIn.close();
						throw new ParseException(categories[i] + " scores ", lineNumber + 1);
					}
					
					line = fileIn.nextLine();
					lineNumber++;
					parseScores(line, categories[i], student);
				}
				
				addStudent(student);
			}  // repeat for more students on sixth+ lines
			
			fileIn.close();
		}
		catch(FileNotFoundException e) {
			System.err.println(e.getMessage() + " No students added to CS 2420 class.");
		}
		catch(ParseException e) {
			System.err.println(e.getLocalizedMessage() + " formatted incorrectly at line " + e.getErrorOffset()
					+ ". No students added to CS 2420 class.");
		}
	}
	
	/**
	 * Helper method for parsing the information about a student from file.
	 * 
	 * @param line - string to be parsed
	 * @param lineNumber - line number in file, used for error reporting (see above)
	 * @return the CS2420Student constructed from the information
	 * @throws ParseException if file containing line is not properly formatted (see above)
	 */
	private CS2420Student parseStudent(String line, int lineNumber) throws ParseException {
		Scanner lineIn = new Scanner(line);
		lineIn.useDelimiter(" ");

		if(!lineIn.hasNext()) {
			lineIn.close();
			throw new ParseException("First name", lineNumber);
		}
		String firstName = lineIn.next();

		if(!lineIn.hasNext()) {
			lineIn.close();
			throw new ParseException("Last name", lineNumber);
		}
		String lastName = lineIn.next();

		if(!lineIn.hasNext()) {
			lineIn.close();
			throw new ParseException("uNID", lineNumber);
		}
		String uNIDString = lineIn.next();
		int uNID = Integer.parseInt(uNIDString.substring(2, 9));

		if(!lineIn.hasNext()) {
			lineIn.close();
			throw new ParseException("Email address", lineNumber);
		}
		String[] emailAddress = lineIn.next().split("@");
		
		lineIn.close();

		return new CS2420Student(firstName, lastName, uNID, new EmailAddress(emailAddress[0], emailAddress[1]));
	}
	
	/**
	 * Helper method for parsing the scores for a student from file.
	 * 
	 * @param line - string to be parsed
	 * @param category - "assignment", "exam", "lab", or "quiz"
	 * @param student - the student for which to add the scores
	 */
	private void parseScores(String line, String category, CS2420Student student) {
		Scanner lineIn = new Scanner(line);
		lineIn.useDelimiter(" ");
		while(lineIn.hasNextDouble()) 
			student.addScore(lineIn.nextDouble(), category);
		lineIn.close();
	}
}
