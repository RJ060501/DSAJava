package assign02;

public class CS2420StudentGeneric<Type> extends UofUStudent {
	private Type t;
	private double[] assignments;
	private double[] exams;
	private double[] labs;
	private double[] quizzes;
	
	public CS2420StudentGeneric(String firstName, String lastName, int uNID, Type contactInfo) {
		super(firstName, lastName, uNID);
		this.t = contactInfo;
		
		assignments = new double[0];
		exams = new double[0];
		labs = new double[0];
		quizzes = new double[0];
	}
	
	/**
	 * Gets contact info
	 * @return student contact information
	 */
	public Type getContactInfo() {
		return t;
	}
	
	/**
	 * Adds new score to appropriate category
	 * @param score
	 * @param category
	 */
	public void addScore(double score, String category) {
		switch (category) {
			case "assignment":
				// Creates new array and copies old scores in and adds final score from the parameter
				double[] newAssignmentsArray = new double[assignments.length + 1];
				
				for (int i = 0; i < assignments.length; i++) {
					newAssignmentsArray[i] = assignments[i];
				}
				
				newAssignmentsArray[assignments.length] = score;
				assignments = newAssignmentsArray;
				break;
			case "exam":
				// Creates new array and copies old scores in and adds final score from the parameter
				double[] newExamsArray = new double[exams.length + 1];
				
				for (int i = 0; i < exams.length; i++) {
					newExamsArray[i] = exams[i];
				}
				
				newExamsArray[exams.length] = score;
				exams = newExamsArray;
				break;
			case "lab":
				// Creates new array and copies old scores in and adds final score from the parameter
				double[] newLabsArray = new double[labs.length + 1];
				
				for (int i = 0; i < labs.length; i++) {
					newLabsArray[i] = labs[i];
				}
				
				newLabsArray[labs.length] = score;
				labs = newLabsArray;
				break;
			case "quiz":
				// Creates new array and copies old scores in and adds final score from the parameter
				double[] newQuizzesArray = new double[quizzes.length + 1];
				
				for (int i = 0; i < quizzes.length; i++) {
					newQuizzesArray[i] = quizzes[i];
				}
				
				newQuizzesArray[quizzes.length] = score;
				quizzes = newQuizzesArray;
				break;
		}
	}
	
	/**
	 * Computes final score
	 * @return
	 */
	public double computeFinalScore() {
		// assignments 40%, exams 40%, labs 10%, Canvas quizzes 10%.
		// Checks if each score array has at least 1 entry, if not returns 0.0
		if (assignments.length < 1 || exams.length < 1 || quizzes.length < 1 || labs.length < 1)
			return 0.0;
		
		// Loops through each score array and adds to sum
		double sum = 0.0;
		double examSum = 0.0;
		
		for (int i = 0; i < assignments.length; i++) {
			sum += (.4 / assignments.length) * assignments[i];
		}
		for (int i = 0; i < exams.length; i++) { 
			sum += (.4 / exams.length) * exams[i];
			examSum += exams[i];
		}
		// Calculates exam average 
		double examAverage = examSum / exams.length;
		
		for (int i = 0; i < quizzes.length; i++) {
			sum += (.1 / quizzes.length) * quizzes[i];
		}
		for (int i = 0; i < labs.length; i++) {
			sum += (.1 / labs.length) * labs[i];
		}

		double averageScore = sum;
		
		// If the exam is under 65% then the final score is the exam average, otherwise it's the average score.
		if (examAverage < 65) {
			return examAverage;
		}
		return averageScore;
	}
	
	/**
	 * Gets letter grade based on final score
	 * @return letter grade
	 */
	public String computeFinalGrade() {
		// If not at least 1 score in each category, return N/A
		if (assignments.length < 1 || exams.length < 1 || quizzes.length < 1 || labs.length < 1)
			return "N/A";
		
		// gets final score and finds associated letter grade
		double finalScore = computeFinalScore();
		
		if(finalScore < 60)
			return "E";
		if(finalScore < 63)
			return "D-";
		if(finalScore < 67)
			return "D";
		if(finalScore < 70)
			return "D+";
		if(finalScore < 73)
			return "C-";
		if(finalScore < 77)
			return "C";
		if(finalScore < 80)
			return "C+";
		if(finalScore < 83)
			return "B-";
		if(finalScore < 87)
			return "B";
		if(finalScore < 90)
			return "B+";
		if(finalScore < 93)
			return "A-";
		if(finalScore <= 100)
			return "A";
		
		// To make compiler happy
		return null;
	}

}
