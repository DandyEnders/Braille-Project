package utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


/**
 * This method will be responsible with Parsing the scenarioFile.
 * @author Jinho Hwang
 *
 */
public class AuthoringUtil {
	
	/**
	 * Letting constructor private so this class can be a
	 * utility class.
	 * @author Jinho Hwang
	 */
	private AuthoringUtil() {
		// Meh
	}
	
	/**
	 * Extension overload version of isCorrectlyParsed.
	 * @author Jinho Hwang
	 * @param file
	 * @return true
	 * 			if given file is correctly parsed
	 * @return false
	 * 			if given file has incorrect syntax
	 */
	public static boolean isCorrectlyParsed(File file) {
		String scenarioFile = AuthoringUtil.fileToString(file);
		return isCorrectlyParsed(scenarioFile);
	}
	
	/**
	 * This method is responsible to determine if lines of Strings
	 * are correctly formatted. ( Correct syntax is available at 
	 * https://wiki.eecs.yorku.ca/course_archive/2017-18/W/2311/_media/scenarioformat.pdf
	 * 
	 * @author Jinho Hwang
	 * @param scenarioFile
	 * @return true
	 * 			if given file is correctly parsed
	 * @return false
	 * 			if given file has incorrect syntax
	 */
	public static boolean isCorrectlyParsed(String scenarioFile) {
		
		Scanner scan = new Scanner(scenarioFile);
		
		boolean isRepeat = false;
		
		
		while(scan.hasNextLine()) {
			Pair<Boolean,String> answerPair = isThisLineSyntacticallyCorrect(scan.nextLine());
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		return true; // dummy return
	}
	
	public static Pair<Boolean,String> isThisLineSyntacticallyCorrect(String fileLine) {
		
		Pair<Boolean,String> answerPair = new Pair<Boolean,String>(false,"");
			
		// The key phrase to indicate to play a sound file.
		if (fileLine.length() >= 8 && fileLine.substring(0, 8).equals("/~sound:")) {
			answerPair.set(true, "sound");
		}
		// The key phrase to indicate to skip to another part of the
		// scenario.
		else if (fileLine.length() >= 7 && fileLine.substring(0, 7).equals("/~skip:")) {
			answerPair.set(true, "skip");
		}
		// The key phrase to indicate to pause for a specified number of
		// seconds.
		else if (fileLine.length() >= 8 && fileLine.substring(0, 8).equals("/~pause:")) {
			answerPair.set(true, "pause");
		}
		// The key phrase to assign a button to repeat text.
		else if (fileLine.length() >= 16 && fileLine.substring(0, 16).equals("/~repeat-button:")) {
			answerPair.set(true, "repeat-button");
		}
		// The key phrase to signal that everything after that key phrase
		// will be repeated.
		else if (fileLine.length() >= 8 && fileLine.substring(0, 8).equals("/~repeat")) {
			answerPair.set(true, "repeat");
		}
		// The key phrase to reset the action listeners of all of the
		// JButtons.
		else if (fileLine.length() >= 15 && fileLine.substring(0, 15).equals("/~reset-buttons")) {
			answerPair.set(true, "reset-buttons");
		}
		// The key phrase to assign a button to skip to another part of the
		// scenario.
		else if (fileLine.length() >= 14 && fileLine.substring(0, 14).equals("/~skip-button:")) {
			answerPair.set(true, "skip-button");
		}
		// The key phrase to clear the display of all of the braille cells.
		else if (fileLine.length() >= 15 && fileLine.substring(0, 15).equals("/~disp-clearAll")) {
			answerPair.set(true, "disp-clearAll");
		}
		// The key phrase to set a Braille cell to a string.
		else if (fileLine.length() >= 17 && fileLine.substring(0, 17).equals("/~disp-cell-pins:")) {
			answerPair.set(true, "disp-cell-pins:");
		}
		// The key phrase to represent a string in Braille.
		else if (fileLine.length() >= 14 && fileLine.substring(0, 14).equals("/~disp-string:")) {
			answerPair.set(true, "disp-string");
		}
		// The key phrase to change the cell to represent a character in
		// Braille.
		else if (fileLine.length() >= 17 && fileLine.substring(0, 17).equals("/~disp-cell-char:")) {
			answerPair.set(true, "disp-cell-char");
		}
		// The key phrase to raise a pin of the specified Braille cell.
		else if (fileLine.length() >= 18 && fileLine.substring(0, 18).equals("/~disp-cell-raise:")) {
			answerPair.set(true, "disp-cell-raise");
		}
		// The key phrase to lower a pin of the specified Braille cell.
		else if (fileLine.length() >= 18 && fileLine.substring(0, 18).equals("/~disp-cell-lower:")) {
			answerPair.set(true, "disp-cell-lower");
		}
		// The key phrase to clear a Braille cell.
		else if (fileLine.length() >= 18 && fileLine.substring(0, 18).equals("/~disp-cell-clear:")) {
			answerPair.set(true, "disp-cell-clear");
		}
		// The key phrase to lower pins of the Braille cell.
		else if (fileLine.length() >= 21 && fileLine.substring(0, 21).equals("/~disp-cell-lowerPins")) {
			answerPair.set(true, "disp-cell-lowerPins");
		}
		// The key phrase to wait for the program to receive a user's input.
		else if (fileLine.length() >= 12 && fileLine.substring(0, 12).equals("/~user-input")) {
			answerPair.set(true, "user-input");
		}
		// Anything other than the specified commands above, is to be
		// interpreted as text that
		// will be spoken for the user to hear.
		else {
			answerPair.set(true, "speak");
		}
		
		return answerPair;
	}
		
	
	
	
	
	
	
	
	
	

	/**
	 * A method that scans through input file and
	 * return String.
	 * @author Jinho Hwang
	 * @param file
	 * 			Input File.
	 * @return fileContent
	 * 			Output String.
	 */
	public static String fileToString(File file) {
		String fileContent = "";
		if(file.isFile()) {
			try {
				Scanner scan = new Scanner(file);
				while(scan.hasNextLine()) {
					fileContent += scan.nextLine() + "\n";
				}
			} catch (FileNotFoundException e) {
				System.out.println("File parsing error. File name: " + file.getName());
				e.printStackTrace();
			}
		}else {
			throw new IllegalArgumentException(file.getName() + " is not a file!");
		}
		
		return fileContent;
			
		
	}
}
