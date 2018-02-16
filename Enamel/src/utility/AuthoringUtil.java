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
	 * 1. Output of this method does not guarantee the file argument executability.
	 *   For example, a phrase /~sound:bark.wav has an argument of bark.wav,
	 *   but this method do not check if bark.wav is executable. 
	 *   
	 * 2. Output of this method does not guarantee if user-input is between skip-button - goto.
	 *   For example, a scenario is following:
	 *   Cell 1
	 *   Button 2
	 *   
	 *   /~user-input
	 *   This method does not check if /~user-input will cause a freeze or not.
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
		LinkedList<Phrase> phraseList = new LinkedList<Phrase>();
		
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
			
			scan.close();
			
		} catch (Exception e) {
			// In case where word "Cell" or "Button" or number after cell and button fails, deal with exception
			errorLog("Exception error: " + e.toString(),
					"Expected format: Cell num1 \n Button num2 \n "
							+ "as the first two lines of the scenarion file, and where num1 and num2 are positive integers. \n"
							+ "Did not receive such a format in the scenario file and program had to end due to the incorrect"
							+ "file format.",PCE);
			// Exception was thrown, which means phrase was wrong, so return false
			scan.close();
			return null;
		}
		
		
		
		// Got validation check of two first line and phrase it---------------//^^^^^
		
		// From the third line of scenario, translate all lines into phrases.
		while (scan.hasNextLine()) {
			phraseList.add(phraseThisLine(scan.nextLine()));
		}
		
		// So far to this line, all scenario file must have phrased. 
		// But validation of argument of phrases must be determined.

		// Get iterator so we can traverse through phrases to see if they are valid.
		//Iterator<Phrase> listIterator = phraseList.iterator();
		
		// While iterator traverses...
		for(int i = 0 ; i < phraseList.size(); i ++) {
			
			// Get the current phrase.
			Phrase currentPhrase = phraseList.get(i);
			
			String[] arguments = currentPhrase.getArguments();
			
			// Checks validity. This cannot be done in a
			// separate method because the class is utility
			// class and non-static list cannot be stated
			// globally that some of types need for checking
			// validity. ( like skip:abc /
			
			// For later use.
			LinkedList<Phrase> remainingList;
			
			switch(currentPhrase.getType()){
			
				// Case of pause
				case "/~pause:":
					try{
						int pauseLength = Integer.parseInt(arguments[0]);
					}catch(Exception e){
						errorLog("Phrasing pause length error on line" + i + ": " + e.toString(),
								"Expected format: /~pause: number \n "
								+ "Where the number is the number of seconds the program "
								+ "waits before continuing. \n"
								+ "Program received : " + currentPhrase
										,PCE);
					}
				
				break;
					
				//case "/~disp-string:": // nothing to check.
				//break;
				
				
				case "/~repeat":
					// Make a composition sublist from current+1 to end. It will be used to test if there is
					// a matching pair of endrepeat.
					remainingList = (LinkedList<Phrase>)phraseList.subList(i+1, phraseList.size());
					
					// Make a for loop that goes through all the remaining list finding unmatched endrepeat.
					for(int j = 0; j < remainingList.size(); j++){
						
						// Get the current remaining phrase.
						Phrase currentPivotPhrase = remainingList.get(j);
						
						// If the phrase is endrepeat,
						if(currentPivotPhrase.getType() == "/~endrepeat"){
							
							// and if the phrase is unmatched,
							if(currentPivotPhrase.getFlag() == null){
								
								// matching each other.
								phraseList.get(j).setFlag(currentPhrase);
								currentPhrase.setFlag(phraseList.get(j));
								break;
							} // end of if
						} // end of if
					} // end of for
					
					// At this point, repeat could not find any matching endrepeat, so print error
					errorLog("Repeat no match endrepeat error on "+ i, "the repeat on line " + i + " do not have a matching endrepeat "
							+ "for the rest of the scenario.",PCE);
					
				break;
				
				
				case "/~repeat-button":
					try{
						// 
						int repeatbutton = Integer.parseInt(arguments[0]);
						if(buttonNumber < repeatbutton){
							throw new IllegalArgumentException("Repeat button index out of bounds. Max button num: " + buttonNumber);
						}
					}catch(Exception e){
						errorLog("Phrasing repeat button error on "+ i + " " + e.toString(),
								"Expected format: /~repeat-button:number \n "
								+ "Where the number is the index of button to repeat. \n"
								+ "Program received : " + currentPhrase,PCE);
					}
				break;
				
				
				case "/~skip-button:":
					try{
						int skipbutton = Integer.parseInt(arguments[0]);
						if(buttonNumber < skipbutton){
							throw new IllegalArgumentException("Skip button index out of bounds. MaxBtnNum / given: " + buttonNumber + " / " + skipbutton);
						}
						
						// Make a composition sublist from current+1 to end. It will be used to test if there is
						// a matching pair of skip.
						remainingList = (LinkedList<Phrase>)phraseList.subList(i+1, phraseList.size());
						
						// Make a for loop that goes through all the remaining list finding for place to skip to.
						for(int j = 0; j < remainingList.size(); j++){
							
							// Get the current remaining phrase.
							Phrase currentPivotPhrase = remainingList.get(j);
							
							// If the phrase is skip-button,
							if(currentPivotPhrase.getType() == "goto"){
								
								// and if the phrase is unmatched,
								if(currentPivotPhrase.getArguments()[0].equals(currentPhrase.getArguments()[1])){
									
									// matching each other.
									phraseList.get(j).setFlag(currentPhrase);
									currentPhrase.setFlag(phraseList.get(j));
									break;
								} // end of if
							} // end of if
						} // end of for
						
						// At this point, skip-button could not find any matching skip to, so print error
						errorLog("Skip-button no match goto error on "+ i, 
								"the skip-button on line " + i + " do not have a matching endrepeat "
								+ "for the rest of the scenario.",PCE);
						
					}catch(Exception e){
						errorLog("Phrasing skip button error on "+ i + " " + e.toString(),
								"Expected format: /~skip-button:number String \n "
								+ "Where the number is the index of button to skip. \n"
								+ "Where the String is the line to skip to.\n"
								+ "Program received : " + currentPhrase,PCE);
					}
				break;
				
				//case "goto": nothing to check.
				//break;
				
				//case "/~user-input": do not check
				//break;
				
				
				case "/~sound":
					File soundfile = new File("./FactoryScenarios/AudioFiles/" + currentPhrase.getArguments()[0]);
					if(!soundfile.exists()){
						errorLog("Sound file mssing error: " + soundfile.getAbsolutePath(),
								"Expected format: /~sound:String \n "
								+ "Where the String is the name of soundfile.\n"
								+ "Program received : " + currentPhrase,PCE);
					}
				break;
				
				//case "/~reset-buttons": nothing to check.
				//break;
				
				
				case "/~skip:":
					// Make a composition sublist from current+1 to end. It will be used to test if there is
					// a matching pair of skip.
					remainingList = (LinkedList<Phrase>)phraseList.subList(i+1, phraseList.size());
					
					// Make a for loop that goes through all the remaining list finding for place to skip to.
					for(int j = 0; j < remainingList.size(); j++){
						
						// Get the current remaining phrase.
						Phrase currentPivotPhrase = remainingList.get(j);
						
						// If the phrase is skip-button,
						if(currentPivotPhrase.getType() == "goto"){
							
							// and if the phrase is unmatched,
							if(currentPivotPhrase.getArguments()[0].equals(currentPhrase.getArguments()[0])){
								
								// matching each other.
								phraseList.get(j).setFlag(currentPhrase);
								currentPhrase.setFlag(phraseList.get(j));
								break;
							} // end of if
						} // end of if
					} // end of for
					
					errorLog("Phrasing skip unmatch error on "+ i,
							"Expected format: /~skip:String \n "
							+ "Where the String is the place to jump to. \n"
							+ "Program received : " + currentPhrase,PCE);
					
				break;
				
				
				//case "/~disp-clearAll":
				//break;
				
				
				case "/~disp-clear-cell:":
					try{
						int dispclearcellbutton = Integer.parseInt(arguments[0]);
						if(cellNumber < dispclearcellbutton || 0 > dispclearcellbutton){
							throw new IllegalArgumentException("dispClearCell button index out of bounds. Range of button index: 0 ~ " + cellNumber);
						}
					}catch(Exception e){
						errorLog("Phrasing dispclearcell error on "+ i + " " + e.toString(),
								"Expected format: /~disp-clear-cell:number \n "
								+ "Where the number is the index of button to display clear cell. \n"
								+ "Program received : " + currentPhrase,PCE);
					}
				break;
				
				
				case "/~disp-cell-pins:":
					try{
						int dispcellpinsbutton = Integer.parseInt(arguments[0]);
						if(buttonNumber < dispcellpinsbutton){
							throw new IllegalArgumentException("dispCellPins button index out of bounds. Max button num: " + buttonNumber);
						}
						
						if(currentPhrase.getArguments()[1].length() != 8){
							throw new IllegalArgumentException("dispCellPins has wrong number of pins.");
						}
						
						for(int j = 0; j < 8 ; j ++){
							if(currentPhrase.getArguments()[1].charAt(j) != '1' || currentPhrase.getArguments()[1].charAt(j) != '0'){
								throw new IllegalArgumentException("dispCellPins has other number/things other than 1 or 0");
							}
						}
					}catch(Exception e){
						errorLog("Phrasing dispCellPins error on "+ i + " " + e.toString(),
								"Expected format: /~disp-clear-cell:number1 number2 \n "
								+ "Where the number1 is the index of button to display cell pins. \n"
								+ "Where the number2 8 1 or 0 's representing cell pins. \n"
								+ "Program received : " + currentPhrase,PCE);
					}
				break;
					
				
				case "/~disp-cell-char:":
					try{
						int dispcellcharbutton = Integer.parseInt(arguments[0]);
						if(cellNumber < dispcellcharbutton || 0 > dispcellcharbutton){
							throw new IllegalArgumentException("dispCellChar button index out of bounds. Range of button index: 0 ~ " + cellNumber);
						}
						
						if(currentPhrase.getArguments()[1].charAt(0) < 65 ||
						   currentPhrase.getArguments()[1].charAt(0) > 90 && currentPhrase.getArguments()[1].charAt(0) < 97 ||
						   currentPhrase.getArguments()[1].charAt(0) > 122){
							throw new IllegalArgumentException("dispCellChar argument is not an English alphabet.");
						}
						
					}catch(Exception e){
						errorLog("Phrasing dispCellChar error on "+ i + " " + e.toString(),
								"Expected format: /~disp-clear-cell:number char \n "
								+ "Where the number is the index of button to display a character in a braille cell. \n"
								+ "Where the char representing a character for a braille cell to read. \n"
								+ "Program received : " + currentPhrase,PCE);
					}
				break;
					
				
				case "/~disp-cell-raise:":
					try{
						int dispcellraisebutton = Integer.parseInt(arguments[0]);
						if(cellNumber < dispcellraisebutton || 0 > dispcellraisebutton){
							throw new IllegalArgumentException("dispCellRaise button index out of bounds. Range of button index: 0 ~ " + cellNumber);
						}
						
						int dispcellraisepins = Integer.parseInt(arguments[1]);
						if(dispcellraisepins > 8 || dispcellraisepins < 0){
							throw new IllegalArgumentException("dispCellRaise braille cell pins out of bounds. Allowed pin num: 1 ~ 8");
						}
						
						
					}catch(Exception e){
						errorLog("Phrasing dispCellRaise error on "+ i + " " + e.toString(),
								"Expected format: /~disp-cell-raise:number1 number2 \n "
								+ "Where the number1 is the index of button to raise cell. \n"
								+ "Where the number2 is the number of pin to raise. \n"
								+ "Program received : " + currentPhrase,PCE);
					}
				break;
					
				
				case "/~disp-cell-lower:":
					try{
						int dispcelllowerbutton = Integer.parseInt(arguments[0]);
						if(cellNumber < dispcelllowerbutton || 0 > dispcelllowerbutton){
							throw new IllegalArgumentException("dispCellLower button index out of bounds. Range of button index: 0 ~ " + cellNumber);
						}
						
						int dispcellraisepins = Integer.parseInt(arguments[1]);
						if(dispcellraisepins > 8 || dispcellraisepins < 0){
							throw new IllegalArgumentException("dispCellLower braille cell pins out of bounds. Allowed pin num: 1 ~ 8");
						}
						
						
					}catch(Exception e){
						errorLog("Phrasing dispCellLower error on "+ i + " " + e.toString(),
								"Expected format: /~disp-cell-lower:number1 number2 \n "
								+ "Where the number1 is the index of button to raise cell. \n"
								+ "Where the number2 is the number of pin to lower.  \n"
								+ "Program received : " + currentPhrase,PCE);
					}
				break;
				
				
				
			}
			
		
			
			
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
