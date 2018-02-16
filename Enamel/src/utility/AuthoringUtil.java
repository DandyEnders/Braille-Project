package utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


/**
 * This method will be responsible with Parsing the scenarioFile.
 * 
 * @author Jinho Hwang
 *
 */
public class AuthoringUtil {
	
	private final static String PCE = "PHRASE_CHECK_ERROR";
	private final static String[] typeList = {"/~sound:","/~skip:","/~pause:","/~repeat-button:","/~repeat","/~endrepeat","/~reset-buttons","/~skip-button:"
											,"/~disp-clearAll","/~disp-cell-pins:","/~disp-string:","/~disp-cell-char:","/~disp-cell-raise:","/~disp-cell-lower:"
											,"/~disp-cell-clear:","/~disp-cell-lowerPins","/~user-input"};

	/**
	 * Letting constructor private so this class can be a utility class.
	 * 
	 * @author Jinho Hwang
	 */
	private AuthoringUtil() {
		// Meh
	}

	/**
	 * Extension overload version of isCorrectlyParsed.
	 * 
	 * @author Jinho Hwang
	 * @param file
	 * @return List\<Phrase\> if given file is correctly parsed
	 * @return null if given file has incorrect syntax
	 */
	public static List<Phrase> isCorrectlyParsed(File file) {
		String scenarioFile = AuthoringUtil.fileToString(file);
		return isCorrectlyParsed(scenarioFile);
	}

	/**
	 * This method is responsible to determine if scenario is ready-to-run.
	 * 
	 * This method does not guarantee if the file argument can be executed or not.
	 *   For example, a phrase /~sound:bark.wav has an argument of bark.wav,
	 *   but this method do not check if bark.wav is executable. 
	 *   
	 * Correct phrase is available at
	 * https://wiki.eecs.yorku.ca/course_archive/2017-18/W/2311/_media/scenarioformat.pdf
	 * 
	 * @author Jinho Hwang
	 * @param scenarioFile
	 * @return List\<Phrase\> if the file was correctly phrased, return a Pair
	 *         of true and list of correct List of Phrases.
	 * @return null if given file failed to phrase correctly.
	 */
	public static List<Phrase> isCorrectlyParsed(String scenarioFile) {
		
		// -----------------------------------------------------------------/

		// Initialization of first two numbers.
		int cellNumber = 0;
		int buttonNumber = 0;
		
		// List of phrases
		List<Phrase> phraseList = new LinkedList<Phrase>();
		
		// Get scanner to read each line of String
		Scanner scan = new Scanner(scenarioFile);

		// Get validation check of two first line and phrase it------------//
		try {
			// Get 1st and 2nd line
			String[] firstLine = scan.nextLine().split("\\s");
			String[] secondLine = scan.nextLine().split("\\s");
			
			// Separtion of words
			String cell = firstLine[0];
			String button = secondLine[0];
			cellNumber = Integer.parseInt(firstLine[1]);
			buttonNumber = Integer.parseInt(secondLine[1]);
			
			// Throw exception if cell is not "Cell" or button is not "Button"
			if(!cell.equals("Cell")){
				scan.close();
				throw new IllegalArgumentException("First word of first line is not \"Cell\".");
			}
			if(!button.equals("Button")){
				scan.close();
				throw new IllegalArgumentException("First word of second line is not \"Button\".");
			}
			
			// Validation of first two line completed here;
			// Submit the first two line in the phraseList as phrases
			phraseList.add(new Phrase(cell,cellNumber + ""));
			phraseList.add(new Phrase(button,buttonNumber + ""));
			
			
		} catch (Exception e) {
			// In case where word "Cell" or "Button" or number after cell and button fails, deal with exception
			errorLog("Exception error: " + e.toString(),
					"Expected format: Cell num1 \n Button num2 \n "
							+ "as the first two lines of the scenarion file, and where num1 and num2 are positive integers. \n"
							+ "Did not receive such a format in the scenario file and program had to end due to the incorrect"
							+ "file format.",PCE);
			// Exception was thrown, which means phrase was wrong, so return false
			return null;
		}
		
		scan.close();
		
		// Got validation check of two first line and phrase it---------------//
		
		// From the third line of scenario, translate all lines into phrases.
		while (scan.hasNextLine()) {
			phraseList.add(phraseThisLine(scan.nextLine()));
		}
		
		// So far to this line, all scenario file must have phrased. 
		// But validation of argument of phrases must be determined.

		// Get iterator so we can traverse through phrases to see if they are valid.
		Iterator<Phrase> listIterator = phraseList.iterator();
		
		// While iterator traverses...
		while(listIterator.hasNext()) {
			
			// Get the current phrase.
			Phrase currentPhrase = listIterator.next();
			
			// Checks validity. This cannot be done in a
			// separate method because the class is utility
			// class and non-static list cannot be stated
			// globally that some of types need for checking
			// validity. ( like skip:abc /
			if(currentPhrase.getType().equals("pause"))
			
			
		}
		
		
		
		
		
		
		
		
		return null; // mummy returns 
	}
	
	
	
	
	
	/**
	 * This method is responsible to change input string to phrase.
	 * 
	 * This method do check :
	 * 		1. if argument of the scenario line is not 1 or 2.
	 * 			( Throws illegalARgumentException if the line contains 
	 * 			 more than three or less than two.)
	 * 
	 * This method does not check :
	 * 		1. if the argument is valid.
	 * 		   ( For example, on a "sound" type line, the method do not check
	 * 			 if the audio file actually work or exist. )
	 * 			
	 * @author Jinho Hwang
	 * @param line
	 * 			A line in a scenario file.
	 * @return Phrase
	 * 			Phrase that was parsed.
	 */
	private static Phrase phraseThisLine(String line){
		
		// Setting up output phrase.
		Phrase phrase = null;
		
		
		// try, because there is a case where a phrase might have 3 or more.
		try{
			
			// Traverse through all the type list to compare type of the line.
			for(String type : typeList){
				
				// The first few characters of the line determines if it is a type of string.
				String typeString = line.substring(0, type.length());
				
				// If the front few character matches type,
				if(type.equals(typeString)){

					// Get the argument
					String argument = line.substring(type.length(), line.length());
					
					// Splits with pivot = space
					String[] multipleArguments = argument.split(" ");
					
					// If argument is 3 or more, throw exception ( no phrase has 3 arguments)
					if(multipleArguments.length >= 3){
						throw new IllegalArgumentException("Phrase parsing failed. the line " + line + " hass 3 or more arguments.");
					}
					
					// Make phrase = type String and the argument.
					phrase = new Phrase(typeString, multipleArguments);
					break;//get out of the for loop since the job is done.
				}
			}
			
			// If for loop could not find a type match, set it as a speak phrase or goto phrase.
			if(phrase == null) {
				if(line.substring(0, "/~".length()).equals("/~")) {
					phrase = new Phrase("goto", line.substring("/~".length(), line.length()));
				}else {
					phrase = new Phrase("speak", line) ;
				}
				
			}
			
		}catch(Exception e){
			errorLog("Exception error: " + e.toString(),
					e.toString(),PCE);
		}
		
		return phrase;
	}

	/**
	 * Writes an error and store in a text file.
	 * @author ScenarioParser writer
	 * @param exception
	 *            the exception message
	 * @param message
	 *            the specific error message
	 * @param errorLoggerName
	 *            the name of error file and logger's name
	 */
	public static void errorLog(String exception, String message, String errorLoggerName) {
		Logger logger = Logger.getLogger(errorLoggerName);
		FileHandler fh;

		System.out.println(message);

		// speak("Error! Something went wrong in the program! Please consult a
		// teacher "
		// + "or administrator for assistance! Also please view the ERROR_LOG
		// file for more details");
		// The try-catch block is to format the Logger class so that the error
		// log file is easier to understand.
		try {
			File f = new File(errorLoggerName + ".txt");
			fh = new FileHandler(f.toString());

			logger.addHandler(fh);
			logger.setUseParentHandlers(false);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);

			logger.warning(exception);
			logger.info(message);
			fh.close();

		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// exit();
	}

	/**
	 * A method that scans through input file and return String.
	 * 
	 * @author Jinho Hwang
	 * @param file
	 *            Input File.
	 * @return fileContent Output String.
	 */
	public static String fileToString(File file) {
		String fileContent = "";
		if (file.isFile()) {
			try {
				Scanner scan = new Scanner(file);
				while (scan.hasNextLine()) {
					fileContent += scan.nextLine() + "\n";
				}
				scan.close();
			} catch (FileNotFoundException e) {
				System.out.println("File parsing error. File name: " + file.getName());
				e.printStackTrace();
			}
		} else {
			throw new IllegalArgumentException(file.getName() + " is not a file!");
		}

		return fileContent;

	}
}
