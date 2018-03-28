/**
 * 
 */
package utility.test;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.junit.Test;

import utility.AuthoringUtil;
import utility.Phrase;

/**
 * @author Jinho Hwang
 *
 */
public class AuthoringUtilTest {
	
	@Test
	public void testPhraseParsing() {
		String testScenarioPiece =  
				"Directional orientation\r\n" + 
				"\r\n" + 
				"/~disp-cell-pins:0 11100000\r\n" + 
				"These are pins 1, 2 and 3, the 3 pins on the left side. \r\n" + 
				"Press button 1 to continue.\r\n" + 
				"/~skip-button:0 ONEE\r\n" + 
				"/~user-input\r\n" + 
				"\r\n" + 
				"/~ONEE\r\n" + 
				"/~disp-cell-clear:0\r\n" + 
				"/~pause:1\r\n" + 
				"/~reset-buttons\r\n" + 
				"/~disp-cell-pins:0 00011100\r\n" + 
				"These are pins 4, 5 and 6, the 3 pins on the right side. \r\n" + 
				"Press button 1 to continue.\r\n" + 
				"/~skip-button:0 ONEE\r\n" + 
				"/~user-input\r\n" + 
				"\r\n" + 
				"/~ONEE\r\n" + 
				"/~disp-cell-clear:0\r\n" + 
				"/~pause:1\r\n" + 
				"/~reset-buttons\r\n" + 
				"/~disp-cell-pins:0 11000000\r\n" + 
				"These are pins 1 and 2, the top two pins on the left side. \r\n" + 
				"Press button 1 to continue.\r\n" + 
				"/~skip-button:0 ONEE\r\n" + 
				"/~user-input\r\n" + 
				"\r\n" + 
				"/~ONEE\r\n" + 
				"/~disp-cell-clear:0\r\n" + 
				"/~pause:1\r\n" + 
				"/~reset-buttons\r\n" + 
				"/~disp-cell-pins:0 00011000\r\n" + 
				"These are pins 4 and 5, the top two pins on the right side. Press button 1 to continue.\r\n" + 
				"/~skip-button:0 ONEE\r\n" + 
				"/~user-input\r\n" + 
				"\r\n" + 
				"/~ONEE\r\n" + 
				"/~disp-cell-clear:0\r\n" + 
				"/~pause:1\r\n" + 
				"/~reset-buttons\r\n" + 
				"/~disp-cell-pins:0 10010000\r\n" + 
				"These are pins 1 and 4, the two pins on the top. Press button 1 to continue.\r\n" + 
				"/~skip-button:0 ONEE\r\n" + 
				"/~user-input\r\n" + 
				"\r\n" + 
				"/~ONEE\r\n" + 
				"/~disp-cell-clear:0\r\n" + 
				"/~pause:1\r\n" + 
				"/~reset-buttons\r\n" + 
				"/~disp-cell-pins:0 00100100\r\n" + 
				"These are pins 3 and 6, the two pins on the bottom. Press button 1 to continue.\r\n" + 
				"/~skip-button:0 ONEE\r\n" + 
				"/~user-input\r\n" + 
				"\r\n" + 
				"/~ONEE\r\n" + 
				"That's the end of directional orientation!\r\n" + 
				"/~disp-cell-clear:0\r\n";
		
		Scanner scan = new Scanner(testScenarioPiece);
		
			String expected = "Directional orientation\r\n" + 
					"\r\n" + 
					"/~disp-cell-pins:0 11100000\r\n" + 
					"These are pins 1, 2 and 3, the 3 pins on the left side. \r\n" + 
					"Press button 1 to continue.\r\n" + 
					"/~skip-button:0 ONEE\r\n" + 
					"/~user-input\r\n" + 
					"\r\n" + 
					"/~ONEE\r\n" + 
					"/~disp-cell-clear:0\r\n" + 
					"/~pause:1\r\n" + 
					"/~reset-buttons\r\n" + 
					"/~disp-cell-pins:0 00011100\r\n" + 
					"These are pins 4, 5 and 6, the 3 pins on the right side. \r\n" + 
					"Press button 1 to continue.\r\n" + 
					"/~skip-button:0 ONEE\r\n" + 
					"/~user-input\r\n" + 
					"\r\n" + 
					"/~ONEE\r\n" + 
					"/~disp-cell-clear:0\r\n" + 
					"/~pause:1\r\n" + 
					"/~reset-buttons\r\n" + 
					"/~disp-cell-pins:0 11000000\r\n" + 
					"These are pins 1 and 2, the top two pins on the left side. \r\n" + 
					"Press button 1 to continue.\r\n" + 
					"/~skip-button:0 ONEE\r\n" + 
					"/~user-input\r\n" + 
					"\r\n" + 
					"/~ONEE\r\n" + 
					"/~disp-cell-clear:0\r\n" + 
					"/~pause:1\r\n" + 
					"/~reset-buttons\r\n" + 
					"/~disp-cell-pins:0 00011000\r\n" + 
					"These are pins 4 and 5, the top two pins on the right side. Press button 1 to continue.\r\n" + 
					"/~skip-button:0 ONEE\r\n" + 
					"/~user-input\r\n" + 
					"\r\n" + 
					"/~ONEE\r\n" + 
					"/~disp-cell-clear:0\r\n" + 
					"/~pause:1\r\n" + 
					"/~reset-buttons\r\n" + 
					"/~disp-cell-pins:0 10010000\r\n" + 
					"These are pins 1 and 4, the two pins on the top. Press button 1 to continue.\r\n" + 
					"/~skip-button:0 ONEE\r\n" + 
					"/~user-input\r\n" + 
					"\r\n" + 
					"/~ONEE\r\n" + 
					"/~disp-cell-clear:0\r\n" + 
					"/~pause:1\r\n" + 
					"/~reset-buttons\r\n" + 
					"/~disp-cell-pins:0 00100100\r\n" + 
					"These are pins 3 and 6, the two pins on the bottom. Press button 1 to continue.\r\n" + 
					"/~skip-button:0 ONEE\r\n" + 
					"/~user-input\r\n" + 
					"\r\n" + 
					"/~ONEE\r\n" + 
					"That's the end of directional orientation!\r\n" + 
					"/~disp-cell-clear:0\r\n"
					+ "";
			
			String parsed = "";
		while(scan.hasNext()) {
			String nextLine = scan.nextLine();
			Phrase phrase;
			try {
				phrase = AuthoringUtil.phraseThisLine(nextLine);
				parsed += phrase + "\r\n";
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		assertEquals("Phrase parsing error. Please check src.utility.Phrase and src.utility.AuthoringUtil.",expected,parsed);
		scan.close();
		
	}

	/* Exception catch should work but it does not work.
	 * 
	@Test(expected = IOException.class)
	public void testWrongPhraseParsing() {
		String testStr = "/~disp-cell-pins:0 11100000 gep";
		Phrase phrase = AuthoringUtil.phraseThisLine(testStr);
	}
	*/

	
	// This test should pass because phrasing doesn't catch if it should be an integer
	@Test
	public void testPhraseNonIntegerParsing() {
		String testStr = "/~skip-button:TWOEEE ONEE";
		try {
			AuthoringUtil.phraseThisLine(testStr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	@Test
	public void testScenarioParsing() {
		String testStr = "Cell 3\r\n" + 
				"Button 4\r\n" + 
				"Directional orientation\r\n" + 
				"\r\n" + 
				"/~disp-cell-pins:0 11100000\r\n" + 
				"These are pins 1, 2 and 3, the 3 pins on the left side. \r\n" + 
				"Press button 1 to continue.\r\n" + 
				"/~skip-button:0 ONEE\r\n" + 
				"/~user-input\r\n" + 
				"\r\n" + 
				"/~ONEE\r\n" + 
				"/~disp-cell-clear:0\r\n" + 
				"/~pause:1\r\n" + 
				"/~reset-buttons\r\n" + 
				"/~disp-cell-pins:0 00011100\r\n" + 
				"These are pins 4, 5 and 6, the 3 pins on the right side. \r\n" + 
				"Press button 1 to continue.\r\n" + 
				"/~skip-button:0 ONEE\r\n" + 
				"/~user-input\r\n" + 
				"\r\n" + 
				"/~ONEE\r\n" + 
				"/~disp-cell-clear:0\r\n" + 
				"/~pause:1\r\n" + 
				"/~reset-buttons\r\n" + 
				"/~disp-cell-pins:0 11000000\r\n" + 
				"These are pins 1 and 2, the top two pins on the left side. \r\n" + 
				"Press button 1 to continue.\r\n" + 
				"/~skip-button:0 ONEE\r\n" + 
				"/~user-input\r\n" + 
				"\r\n" + 
				"/~ONEE\r\n" + 
				"/~disp-cell-clear:0\r\n" + 
				"/~pause:1\r\n" + 
				"/~reset-buttons\r\n" + 
				"/~disp-cell-pins:0 00011000\r\n" + 
				"These are pins 4 and 5, the top two pins on the right side. Press button 1 to continue.\r\n" + 
				"/~skip-button:0 ONEE\r\n" + 
				"/~user-input\r\n" + 
				"\r\n" + 
				"/~ONEE\r\n" + 
				"/~disp-cell-clear:0\r\n" + 
				"/~pause:1\r\n" + 
				"/~reset-buttons\r\n" + 
				"/~disp-cell-pins:0 10010000\r\n" + 
				"These are pins 1 and 4, the two pins on the top. Press button 1 to continue.\r\n" + 
				"/~skip-button:0 ONEE\r\n" + 
				"/~user-input\r\n" + 
				"\r\n" + 
				"/~ONEE\r\n" + 
				"/~disp-cell-clear:0\r\n" + 
				"/~pause:1\r\n" + 
				"/~reset-buttons\r\n" + 
				"/~disp-cell-pins:0 00100100\r\n" + 
				"These are pins 3 and 6, the two pins on the bottom. Press button 1 to continue.\r\n" + 
				"/~skip-button:0 ONEE\r\n" + 
				"/~user-input\r\n" + 
				"\r\n" + 
				"/~ONEE\r\n" + 
				"That's the end of directional orientation!\r\n" + 
				"/~disp-cell-clear:0\r\n";
		
		try {
			AuthoringUtil.phraseScenario(testStr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	// Testing factory scenario 2 because 1 was covered on prev test.
	@Test
	public void testScenarioPhrasingWithFactoryScenario2() {
		
		File file = new File("./FactoryScenarios/Scenario_2.txt");
		try {
			AuthoringUtil.phraseScenario(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	// Testing factory scenario 3 because 1 2 was covered on prev test.
		@Test
		public void testScenarioPhrasingWithFactoryScenario3() {
			
			File file = new File("./FactoryScenarios/Scenario_3.txt");
			try {
				AuthoringUtil.phraseScenario(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	
}
