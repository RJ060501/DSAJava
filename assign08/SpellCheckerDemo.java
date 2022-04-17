package assign08;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * A small demonstration of the SpellChecker class.
 * 
 * @author Erin Parker
 * @version March 17, 2022
 */
public class SpellCheckerDemo {

	public static void main(String[] args) {

//		SpellChecker mySC = new SpellChecker(new File("src/assign08/dictionary.txt"));
//
//		runSpellCheck(mySC, "src/assign08/hello_world.txt");
//		runSpellCheck(mySC, "src/assign08/good_luck.txt");
//		runSpellCheck(mySC, "src/assign08/dictionary.txt");
		File dictionary = null;
		File document = null;
		String choice = "";

		if (args.length < 2 || args.length > 3) {
			System.out.println("The amount of arguement entered are incorrect");
			return;
		}

		dictionary = new File(args[0]);
		if (!dictionary.isFile()) {
			System.out.println("The dictionary file selected cannot be used");
			return;
		}

		document = new File(args[1]);
		if (!document.isFile()) {
			System.out.println("The dictionary file selected cannot be used");
			return;
		}

		// If a third parameter was passed for the options, check its validity
		if (args.length >= 3)
			if (args[2].equalsIgnoreCase("-A") || args[2].equalsIgnoreCase("-R")) {
				choice = args[2];
			} else {
				System.out.println("Invalid printing or filing option argument!");
				return;
			}
		runSpellCheck(dictionary, document, choice);
	}

	/**
	 * Runs the given spell checker (with dictionary already added) on the specified
	 * file.
	 * 
	 * @param sc               - the given spell checker
	 * @param documentFilename - name of the file to be spell checked
	 */
	private static void runSpellCheck(File dic, File doc, String choice) {
		SpellChecker mySC = new SpellChecker(dic);

		// A list of misspelled words generated after spellchecking the document
		List<String> misspelledWords = mySC.spellCheck(doc);
		if (misspelledWords.size() == 0)
			System.out.println("There are no misspelled words in file " + doc + ".");
		else {
			if (choice.equals("-R")) {
				try {
					FileWriter file = new FileWriter("misspelled.txt");

					for (String word : misspelledWords) {
						file.write(word + "\n");
					}

					file.close();

					System.out.println("Please see misspelled.txt for a list of the words.");
				} catch (IOException e) {
					System.out.println("Unable to create a file for the misspelled words");
					return;
				}
			}
//			System.out.println("The misspelled words in file " + doc + " are:");
		}
	}
}
