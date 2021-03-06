package utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * This method will be responsible with Parsing the scenarioFile.
 * 
 * @author Jinho Hwang
 *
 */
public class AuthoringUtil {

	private final static String PCE = "PHRASE_CHECK_ERROR";
	
	public static String[] getTypeList() {return Language.typeList;}
	
	/**
	 * Letting constructor private so this class can be a utility class.
	 * 
	 * @author Jinho Hwang
	 */
	private AuthoringUtil() {
		// Meh
	}
	
	private static void errorLog(String exception, String message) throws IOException {
		ErrorUtil.errorLog(exception, message, PCE);
		throw new IOException(message);
	}
	
	
	public static List<File> getAudioFiles(String str){
		List<File> voiceList = new ArrayList<File>();
		File audioFileFolder = new File(str);

    	if(audioFileFolder.isDirectory()) {
    		for(File soundFile : audioFileFolder.listFiles()) {
    			
    			if(soundFile.length() < 4) {
    				continue;
    			}
    			
    			if(!soundFile.isDirectory()) {
    				if(soundFile.getName().substring(soundFile.getName().length()-4, soundFile.getName().length()).equals(".wav")) {
    					voiceList.add(soundFile);
    				}
    			}
    		}
    	}
    	return voiceList;
	}
	

	/**
	 * Extension overload version of isCorrectlyParsed.
	 * 
	 * @author Jinho Hwang
	 * @param file
	 * @return List\<Phrase\> if given file is correctly parsed
	 * @throws IOException if parsing goes wrong
	 */
	public static List<Phrase> phraseScenario(File file) throws IOException {
		String scenarioFile = AuthoringUtil.fileToString(file);
		return phraseScenario(scenarioFile);
	}

	/**
	 * This method is responsible to determine if scenario is ready-to-run.
	 * 
	 * 1. Output of this method does not guarantee the file argument executability.
	 * For example, a phrase /~sound:bark.wav has an argument of bark.wav, but this
	 * method do not check if bark.wav is executable.
	 * 
	 * 2. Output of this method does not guarantee if user-input is between
	 * skip-button - goto. For example, a scenario is following: Cell 1 Button 2
	 * 
	 * /~user-input This method does not check if /~user-input will cause a freeze
	 * or not.
	 * 
	 * Correct phrase is available at
	 * https://wiki.eecs.yorku.ca/course_archive/2017-18/W/2311/_media/scenarioformat.pdf
	 * 
	 * @author Jinho Hwang
	 * @param scenarioFile
	 * @return List\<Phrase\> if the file was correctly phrased, return a Pair of
	 *         true and list of correct List of Phrases.
	 * @throws IOException if parsing goes wrong
	 */
	@SuppressWarnings("resource")
	public static List<Phrase> phraseScenario(String scenarioFile) throws IOException {

		// -----------------------------------------------------------------/

		// Initialization of first two numbers.
		int cellNumber = -1;
		int buttonNumber = -1;

		// List of phrases
		LinkedList<Phrase> phraseList = new LinkedList<Phrase>();

		// Get scanner to read each line of String
		Scanner scan = new Scanner(scenarioFile);

		String[] firstLine = new String[2];
		String[] secondLine = new String[2];

		// Get validation check of two first line and phrase it------------//
		try {
		if(!scan.hasNextLine()) {
			throw new IOException("The file is empty.");
		}

		// Get 1st and 2nd line
		String firstLawLine = scan.nextLine();
		String secondLawLine = scan.nextLine();

		// Check if they have 2 arguments (cell & num or button & num) or not.
		if (firstLawLine.split("\\s").length > 2 || firstLawLine.split("\\s").length < 2) {
			throw new IOException("First line contains more or less than 2 arguments.");
		}

		if (secondLawLine.split("\\s").length > 2 || secondLawLine.split("\\s").length < 2) {
			throw new IOException("Second line contains more or less than 2 arguments.");
		}

		// Sub them in
		firstLine[0] = firstLawLine.split("\\s")[0];
		secondLine[0] = secondLawLine.split("\\s")[0];

		firstLine[1] = firstLawLine.split("\\s")[1];
		secondLine[1] = secondLawLine.split("\\s")[1];

		// Separation of words
		String cell = firstLine[0]; // should be "Cell"
		String button = secondLine[0]; // should be "Button"

		// "catch" will catch if the numeric argument is parse-able or not.
		cellNumber = Integer.parseInt(firstLine[1]);
		buttonNumber = Integer.parseInt(secondLine[1]);

		// Throw exception if cell is not "Cell" or button is not "Button"
		// or # of cell or button is less than 1 ( not positive )
		if (!cell.equals("Cell")) {
			throw new IOException("First word of first line is not \"Cell\".");
		}
		if (!button.equals("Button")) {
			throw new IOException("First word of second line is not \"Button\".");
		}
		if (cellNumber < 1) {
			throw new IOException("A number of cell is not a positive number. ( < 1 )");
		}
		if (buttonNumber < 1) {
			throw new IOException("A number of button is not a positive number. ( < 1 )");
		}

		// Validation of first two line completed here;
		// Submit the first two line in the phraseList as phrases
		phraseList.add(new Phrase(cell, cellNumber + ""));
		phraseList.add(new Phrase(button, buttonNumber + ""));

		} catch (IOException e) {
			scan.close();
			// In case where word "Cell" or "Button" or number after cell and button fails,
			// deal with exception
			errorLog("Exception error: " + e.getMessage(), "Expected format: \nCell num1 \n Button num2 \n"
					+ "as the first two lines of the scenarion file, and where num1 and num2 are positive integers. \n"
					+ "Did not receive such a format in the scenario file and program had to end due to the incorrect"
					+ "file format. ");
		} 

		// Got validation check of two first line and phrase it---------------//^^^^^

		// From the third line of scenario, translate all lines into phrases.
		while (scan.hasNextLine()) {
			phraseList.add(phraseThisLine(scan.nextLine()));
			// phraseThisLine ensures that there are no phrases with 3 or more arguments.
		}

		// So far to this line, all scenario file must have phrased.
		// But validation of argument of phrases is needed.

		// While iterator traverses...
		for (int i = 0; i < phraseList.size(); i++) {

			int currentLine = i - 1;

			// Get the current phrase.
			Phrase currentPhrase = phraseList.get(i);

			// Get arguments.
			String[] arguments = currentPhrase.getArguments();

			// Checks validity. This cannot be done in a
			// separate method because the class is utility
			// class and non-static list cannot be stated
			// globally that some of types need for checking
			// validity. ( like skip:abc /

			// For later use.
			LinkedList<Phrase> remainingList;

			switch (currentPhrase.getType()) {

			case "/~pause:":
				try {
					Integer.parseInt(arguments[0]);
				} catch (Exception e) {
					String exception = "Phrasing pause length error on line " + currentLine + ": " + e.getMessage();
					String message = "Expected format: /~pause: number \n"
							+ "Where the number is the number of seconds the program "
							+ "\nwaits before continuing on line " + currentLine + "\nProgram received : "
							+ currentPhrase;
					
					errorLog(exception,message);
				}

				break;

			case "/~disp-string:": 
			try {
				
				int stringLength = currentPhrase.getArguments()[0].length();
				
				if (stringLength-1 > cellNumber-1 || stringLength < 0){
					throw new IOException("Input string out of bound. String length = " + stringLength + " maxCell range = 0 ~ " + (cellNumber-1));
				}
				
				for (int j = 0; j < stringLength; j++) {

					// If the number is not either 1 or 0,
					if ((currentPhrase.getArguments()[0].charAt(j) < 65
							|| currentPhrase.getArguments()[0].charAt(j) > 90
							&& currentPhrase.getArguments()[0].charAt(j) < 97
					|| currentPhrase.getArguments()[0].charAt(j) > 122)) {

						// throw an exception
						throw new IOException("dispstring is not formatted with proper English alphabet. Found it on " + j + "th letter.");
					}
				}
			} catch (Exception e) {
				errorLog("Phrasing disp-string error on " + currentLine + " " + e.getMessage(),
						e.getMessage() + " Expected format: /~disp-string:string \n "
								+ "Where the string is the string to display on cells. \n"
								+ "Program received : " + currentPhrase);
				;
			}
			break;

			case "/~repeat":
				// Make a composition sublist from current+1 to end. It will be used to test if
				// there is
				// a matching pair of endrepeat.
				remainingList = new LinkedList<Phrase>(phraseList.subList(i + 1, phraseList.size()));
				
				if(!remainingList.isEmpty()) {
					// Make a for loop that goes through all the remaining list finding unmatched
					// endrepeat.
					for (int j = 0; j < remainingList.size(); j++) {
	
						// Get the current remaining phrase.
						Phrase currentPivotPhrase = remainingList.get(j);
	
						// If the phrase is endrepeat,
						if (currentPivotPhrase.getType().equals("/~endrepeat")) {
	
							// and if the phrase is unmatched,
							if (currentPivotPhrase.getFlag() == null) {
	
								// matching each other.
								currentPivotPhrase.setFlag(currentPhrase);
								currentPhrase.setFlag(currentPivotPhrase);
								break;
							} // end of if
						} // end of if
					} // end of for
				}
				// At this point, repeat could not find any matching endrepeat, so print error
				if (currentPhrase.getFlag() == null || remainingList.isEmpty()) {
					errorLog(
							"One of the repeat no match endrepeat error on " + currentLine, "Repeat command on line "
									+ currentLine + " do not have a matching endrepeat for the rest of the scenario.");
					;
				}

				break;
				
			case "/~endrepeat":
				remainingList = new LinkedList<Phrase>(phraseList.subList(0, i));
				
				if(!remainingList.isEmpty()) {
					
					
					for (int j = i-1; j > 0 ; j--) {
	
						Phrase currentPivotPhrase = remainingList.get(j);
	
						// If the phrase is endrepeat,
						if (currentPivotPhrase.getType().equals("/~repeat")) {
	
							// and if the phrase is unmatched,
							if (currentPivotPhrase.getFlag() == null) {
	
								// matching each other.
								currentPivotPhrase.setFlag(currentPhrase);
								currentPhrase.setFlag(currentPivotPhrase);
								break;
							} // end of if
						} // end of if
					} // end of for
				}
				// At this point, repeat could not find any matching endrepeat, so print error
				if (currentPhrase.getFlag() == null || remainingList.isEmpty()) {
					errorLog(
							"One of the endrepeat no match repeat error on " + currentLine, "Endrepeat command on line "
									+ currentLine + " do not have a matching repeat before the line.");
					;
				}
			break;

			case "/~repeat-button":
				try {
					//
					int repeatbutton = Integer.parseInt(arguments[0]);
					if (buttonNumber - 1 < repeatbutton || repeatbutton < 0) {
						throw new IOException(
								"Repeat button index out of bounds. Index range of : 0 ~ " + (buttonNumber - 1));
					}
				} catch (Exception e) {
					errorLog("Phrasing repeat button error on " + currentLine + " " + e.getMessage(),
							"Expected format: /~repeat-button:number \n "
									+ "Where the number is the index of button to repeat. \n" + "Program received : "
									+ currentPhrase);
					;
				}
				break;

			case "/~skip-button:":
				try {
					int skipbutton = Integer.parseInt(arguments[0]);
					if (buttonNumber - 1 < skipbutton || skipbutton < 0) {
						throw new IOException(
								"Skip button index out of bounds. Index range of : 0 ~ " + (buttonNumber - 1));
					}

					// Make a composition sublist from current+1 to end. It will be used to test if
					// there is
					// a matching pair of skip.
					remainingList = new LinkedList<Phrase>(phraseList.subList(i + 1, phraseList.size()));

					// Make a for loop that goes through all the remaining list finding for place to
					// skip to.
					
					if(!remainingList.isEmpty()) {
						for (int j = 0; j < remainingList.size(); j++) {
	
							// Get the current remaining phrase.
							Phrase currentPivotPhrase = remainingList.get(j);
	
							if (currentPivotPhrase.getFlag() != null) {
								continue;
							}
	
							// If the phrase is goto,
							if (currentPivotPhrase.getType().equals(Language.commandPrefix)) {
								
								// and if the phrase is unmatched before,
								if (currentPivotPhrase.getArguments()[0].equals(currentPhrase.getArguments()[1])) {
									
									// matching each other.
									currentPivotPhrase.setFlag(currentPhrase);
									currentPhrase.setFlag(currentPivotPhrase);
									break;
								} // end of if
							} // end of if
						} // end of for
					}
					// At this point, skip-button could not find any matching skip to, so print
					// error
					if (currentPhrase.getFlag() == null || remainingList.isEmpty()) {
						errorLog("Skip-button no match goto error somewhere. Last one found on " + currentLine,
								" The skip-button on line " + currentLine + " do not have a matching goto "
										+ "for the rest of the scenario.");
						;
					}
				} catch (Exception e) {
					errorLog("Phrasing skip button error on " + currentLine + " " + e.getMessage(),
							e.getMessage() + " : Expected format: /~skip-button:number String \n "
									+ "Where the number is the index of button to skip.\n"
									+ "Where the String is the line to skip to.\n" + "Program received "+currentLine + " : "
									+ currentPhrase);
					;
				}
				break;

			// case "/~": nothing to check.
			// break;

			// case "/~user-input": do not check
			// break;

			case "/~sound:":
				File soundfile = new File(Language.audioPath + currentPhrase.getArguments()[0]);
				if (!soundfile.exists()) {
					errorLog("Sound file missing error: " + soundfile.getAbsolutePath(),
							"Expected format: /~sound:String \n " + "Where the String is the name of soundfile.\n"
									+ "Program received : " + currentPhrase);
					;
				}
				break;

			// case "/~reset-buttons": nothing to check.
			// break;

			case "/~skip:":

				//try {
					// Make a composition sublist from current+1 to end. It will be used to test if
					// there is
					// a matching pair of skip.
					remainingList = new LinkedList<Phrase>(phraseList.subList(i + 1, phraseList.size()));

					// Make a for loop that goes through all the remaining list finding for place to
					// skip to.
					if(!remainingList.isEmpty()) {
						for (int j = 0; j < remainingList.size(); j++) {
	
							// Get the current remaining phrase.
							Phrase currentPivotPhrase = remainingList.get(j);
	
							// If the phrase is goto,
							if (currentPivotPhrase.getType().equals(Language.commandPrefix)) {
	/*
								// If the pivot has a flag
								if (currentPivotPhrase.getFlag() != null) {
									continue;
								}*/
	
								// and if the phrase is unmatched before,
								if (currentPivotPhrase.getArguments()[0].equals(currentPhrase.getArguments()[0])) {
	
									// matching each other.
									currentPivotPhrase.setFlag(currentPhrase);
									currentPhrase.setFlag(currentPivotPhrase);
									break;
								} // end of if
							} // end of if
						} // end of for
					}

					if (currentPhrase.getFlag() == null || remainingList.isEmpty()) {
						errorLog(
								"Skip unmatched goto error on " + currentLine, "The skip on line " + currentLine
										+ " do not have a matching skip goto to for the rest of the scenario.");
						;
					}
				/*} catch (Exception e) {
					errorLog("Phrasing skip error on " + currentLine,
							e.getMessage() + " Expected format: /~skip:String \n "
									+ "Where the String is the place to jump to. Error found on " + currentLine + "\n"
									+ "Program received : " + currentPhrase);
					;
				}*/
				break;

			// case "/~disp-clearAll":
			// break;

			case "/~disp-clear-cell:":
				try {
					// Check for invalid indexing of the cell by parsing the first argument.
					int dispclearcellCell = Integer.parseInt(arguments[0]);

					// If the parsed cell index is less than 0 or bigger than given number of cells,
					if (cellNumber - 1 < dispclearcellCell || 0 > dispclearcellCell) {

						// then throw an exception out of index bound.
						throw new IOException(
								"dispClearCell cell index out of bounds. Range of cell index: 0 ~ " + (cellNumber - 1));
					}
				} catch (Exception e) {
					errorLog("Phrasing dispclearcell error on " + currentLine + " " + e.getMessage(),
							"Expected format: /~disp-clear-cell:number \n "
									+ "Where the number is the index of cell to display clear cell. \n"
									+ "Program received : " + currentPhrase);
					;
				}
				break;

			case "/~disp-cell-pins:":
				try {
					// Check for invalid indexing of the cell by parsing the first argument.
					int dispcellpinscell = Integer.parseInt(arguments[0]);

					// If the parsed cell index is less than 0 or bigger than given number of cells,
					if (cellNumber - 1 < dispcellpinscell || dispcellpinscell < 0) {
						throw new IOException(

								// then throw an exception out of index bound.
								"dispCellPins cell index out of bounds. Range of cell index: 0 ~ " + (cellNumber - 1));
					}

					// If the cell composition is not 8, (ensures that number of character is 8 if
					// passes)
					if (currentPhrase.getArguments()[1].length() != 8) {

						// Throw exception
						throw new IOException("dispCellPins has more or less pins than 8.");
					}

					// Check 8 numbers
					for (int j = 0; j < 8; j++) {

						// If the number is not either 1 or 0,
						if (currentPhrase.getArguments()[1].charAt(j) != '1'
								&& currentPhrase.getArguments()[1].charAt(j) != '0') {

							// throw an exception
							throw new IOException("dispCellPins has other things other than 1 or 0");
						}
					}
				} catch (Exception e) {
					errorLog("Phrasing dispCellPins error on " + currentLine + " " + e.getMessage(),
							"Expected format: /~disp-cell-pins:number1 number2 \n "
									+ "Where the number1 is the index of cell to display cell pins. \n"
									+ "Where the number2 represents 8 cell pins, either 0 or 1. \n"
									+ "Program received : " + currentPhrase);
					;
				}
				break;

			case "/~disp-cell-char:":
				try {
					// Check for invalid indexing of the cell by parsing the first argument.
					int dispcellcharcell = Integer.parseInt(arguments[0]);

					// If the parsed cell index is less than 0 or bigger than given number of cells,
					if ((cellNumber - 1) < dispcellcharcell || 0 > dispcellcharcell) {

						// then throw an exception out of index bound.
						throw new IOException(
								"dispCellChar cell index out of bounds. Range of cell index: 0 ~ " + (cellNumber - 1));
					}

					// If the second argument isn't English alphabet ( in ASCII )
					if (currentPhrase.getArguments()[1].charAt(0) < 65
							|| currentPhrase.getArguments()[1].charAt(0) > 90
									&& currentPhrase.getArguments()[1].charAt(0) < 97
							|| currentPhrase.getArguments()[1].charAt(0) > 122) {

						// then throw an exception not an English alphabet.
						throw new IOException("dispCellChar argument is not an English alphabet.");
					}

				} catch (Exception e) {
					errorLog("Phrasing dispCellChar error on " + currentLine + " " + e.getMessage(),
							"Expected format: /~disp-cell-char:number char \n "
									+ "Where the number is the index of cell to display a character in a braille cell. \n"
									+ "Where the char representing a character for a braille cell to read. \n"
									+ "Program received : " + currentPhrase);
					;
				}
				break;

			case "/~disp-cell-raise:":
				try {
					// Check for invalid indexing of the cell by parsing the first argument.
					int dispcellraisecell = Integer.parseInt(arguments[0]);

					// If the parsed cell index is less than 0 or bigger than given number of cells,
					if ((cellNumber - 1) < dispcellraisecell || 0 > dispcellraisecell) {

						// then throw an exception out of index bound.
						throw new IOException(
								"dispCellRaise cell index out of bounds. Range of cell index: 0 ~ " + (cellNumber - 1));
					}

					// Parse the pin to raise. If it is not a number, it will throw an exception.
					int dispcellraisepins = Integer.parseInt(arguments[1]);

					// Check if the pin is between 1 to 8.
					if (dispcellraisepins > 8 || dispcellraisepins < 1) {
						throw new IOException("dispCellRaise braille cell pins out of bounds. Allowed pin num: 1 ~ 8");
					}

				} catch (Exception e) {
					errorLog("Phrasing dispCellRaise error on " + currentLine + " " + e.getMessage(),
							"Expected format: /~disp-cell-raise:number1 number2 \n "
									+ "Where the number1 is the index of cell to raise cell. \n"
									+ "Where the number2 is the number of pin to raise. \n" + "Program received : "
									+ currentPhrase);
					;
				}
				break;

			case "/~disp-cell-lower:":
				try {
					// Check for invalid indexing of the cell by parsing the first argument
					int dispcelllowercell = Integer.parseInt(arguments[0]);

					// If the parsed cell index is less than 0 or bigger than given number of cells,
					if ((cellNumber - 1) < dispcelllowercell || 0 > dispcelllowercell) {
						// then throw an exception out of index bound.
						throw new IOException(
								"dispCellLower cell index out of bounds. Range of cell index: 0 ~ " + (cellNumber - 1));
					}

					// Parse the pin to lower. If it is not a number, it will throw an exception.
					int dispcelllowerpins = Integer.parseInt(arguments[1]);

					// Check if the pin is between 1 to 8.
					if (dispcelllowerpins > 8 || dispcelllowerpins < 1) {
						throw new IOException("dispCellLower braille cell pins out of bounds. Allowed pin num: 1 ~ 8");
					}

				} catch (Exception e) {
					errorLog("Phrasing dispCellLower error on " + currentLine + " " + e.getMessage(),
							"Expected format: /~disp-cell-lower:number1 number2 \n "
									+ "Where the number1 is the index of cell to lower cell. \n"
									+ "Where the number2 is the number of pin to lower.  \n" + "Program received : "
									+ currentPhrase);
					;
				}
				break;
				
			case "/~":
				remainingList = new LinkedList<Phrase>(phraseList.subList(0, i));
				
				if(!remainingList.isEmpty()) {
					
					
					for (int j = i-1; j > 0 ; j--) {
	
						Phrase currentPivotPhrase = remainingList.get(j);
	
						if (currentPivotPhrase.getType().equals("/~skip") 
							&& currentPhrase.getArguments()[0].equals(currentPivotPhrase.getArguments()[0])) {
	
							// and if the phrase is unmatched,
							if (currentPivotPhrase.getFlag() == null) {
	
								// matching each other.
								currentPivotPhrase.setFlag(currentPhrase);
								currentPhrase.setFlag(currentPivotPhrase);
								break;
							} // end of if
						} // end of if
					} // end of for
				}
				// At this point, repeat could not find any matching endrepeat, so print error
				if (currentPhrase.getFlag() == null || remainingList.isEmpty()) {
					errorLog(
							"One of the skip goto no match repeat error on " + currentLine, currentPhrase + " command on line "
									+ currentLine + " do not have a matching skip before the line.");
					;
				}
			break;
				
			// case "emptyLine":
			// break;

			}

		}

		return phraseList;
	}

	/**
	 * This method is responsible to change input string to phrase.
	 * 
	 * This method do check : 1. if argument of the scenario line is not 1 or 2. (
	 * Throws IOException if the line contains more than three or less than 0.) 2.
	 * if a phrase that does not need an argument has an argument ( Ex.
	 * /~reset-buttons has no arguments, but if /~reset-buttons1234 were given,
	 * throw IOException.)
	 * 
	 * This method does not check : 1. if the argument is valid. ( For example, on a
	 * "sound" type line, the method do not check if the audio file actually work or
	 * exist. )
	 * 
	 * @author Jinho Hwang
	 * @param line
	 *            A line in a scenario file.
	 * @return Phrase Phrase that was parsed.
	 */
	public static Phrase phraseThisLine(String line) throws IOException {
		
		// Setting up output phrase.
		Phrase phrase = null;

		// try, because there is a case where a phrase might have 3 or more.
		try {
			

			if (isEmptyLine(line)) {
				phrase = new Phrase("emptyLine");
			} else if (isSpeakLine(line)) {
				phrase = new Phrase("speak", line);
			} else {

				// Traverse through all the type list to compare type of the line.
				for (String type : Language.typeList) {

					// If the length of type is greater then line length, continue.
					if (type.length() > line.length()) {
						continue;
					}

					// The first few characters of the line determines if it is a type of string.
					String typeString = line.substring(0, type.length());

					String[] splitArgument = new String[2];

					// If the front few character matches type,
					if (type.equals(typeString)) {

						// If a phrase has argument/s then it has ":" at the end.
						if (typeString.charAt(type.length() - 1) == ':') {

							// Get the argument.
							String inputLineArgument = line.substring(type.length(), line.length());

							// Split the argument.
							String[] splitedInputLineArguments = inputLineArgument.split(" ");
							
							for(String twoArgsType : Language.typeListTwoArguments) {
								if(twoArgsType.equals(typeString)) {
									if(splitedInputLineArguments.length != 2 ) {
										throw new IOException(type + " must have two arugments.");
									}
								}
							}
							
							for(String oneArgsType : Language.typeListOneArgument) {
								if(oneArgsType.equals(typeString)) {
									if(splitedInputLineArguments.length != 1) {
										throw new IOException(type + " must have one argument.");
									}
								}
							}
							
							
							for(String firstArgNumberType : Language.typeListFirstArgNumber) {
								if(firstArgNumberType.equals(typeString)){
									try {
										Integer.parseInt(splitedInputLineArguments[0]);
									}catch(NumberFormatException e) {
										throw new IOException(type + " must have the first argument as a number.");
									}
								}
							}
							
							for(String secondArgNumberType : Language.typeListSecondArgNumber) {
								if(secondArgNumberType.equals(typeString)){
									try {
										Integer.parseInt(splitedInputLineArguments[1]);
									}catch(NumberFormatException e) {
										throw new IOException(type + " must have the second argument as a number.");
									}
								}
							}
							
							
							// Splits with pivot = space
							if (splitedInputLineArguments.length == 0) {
								// Types with ":" has to have an argument at least.
								throw new IOException(type + " needs an argument.");
							} else if (splitedInputLineArguments.length == 1) {
								// If the phrase has only one argument, fill the other with null.
								splitArgument[0] = splitedInputLineArguments[0];
								splitArgument[1] = null;
							} else if (splitedInputLineArguments.length == 2) {
								// If the phrase has two arguments, fill both arguments
								splitArgument[0] = splitedInputLineArguments[0];
								splitArgument[1] = splitedInputLineArguments[1];
							} else {
								// If argument is 3 or more, throw exception ( no phrase has 3 arguments)
								throw new IOException(
										"Phrase parsing failed. " + line + " has 3 or more arguments.");
							}

							// If a phrase do not have arguments,
						} else {

							// The phrase should not have any arguments.
							if (line.length() < type.length()) {
								throw new IOException(type + " should not have any arugments.");
							}

							// Set both element of array null.
							splitArgument[0] = null;
							splitArgument[1] = null;
						}

						// Make phrase = type String and the argument.

						phrase = new Phrase(typeString, splitArgument);
						// System.out.println("Made Phrase :" + phrase);
						break;// get out of the for loop since the job is done.
					}
				}

				// If for loop could not find a type match, set it as a goto phrase.
				if (phrase == null) {
					if (line.substring(0, Language.commandPrefix.length()).equals(Language.commandPrefix)) {
						phrase = new Phrase(Language.commandPrefix, line.substring(Language.commandPrefix.length(), line.length()));
					}
				}
			}

			

		} catch (IOException e) {
			errorLog("Line Phrase error: " + e.getLocalizedMessage(), e.getMessage());
		}
		return phrase;

	}

	private static boolean isSpeakLine(String line) {
		return !line.substring(0, Language.commandPrefix.length()).equals(Language.commandPrefix);
	}

	private static boolean isEmptyLine(String line) {
		return line.trim().isEmpty();
	}
	

	/**
	 * A method that scans through input file and return String.
	 * 
	 * @author Jinho Hwang
	 * @param file
	 *            Input File.
	 * @return fileContent Output String.
	 */
	public static String fileToString(File file) throws FileNotFoundException {
		String fileContent = "";
		
		if (file.isFile()) {
			Scanner scan = new Scanner(file);
			while (scan.hasNextLine()) {
				fileContent += scan.nextLine() + "\n";
			}
			scan.close();
		} else {
			throw new FileNotFoundException(file.getName() + " is not a file!");
		}

		return fileContent;

	}
}
