
package mainMenuJavaFX.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.Test;

import enamel.ScenarioParser;
import javafx.application.Application;
import mainMenuJavaFX.MainMenu;
import mainMenuJavaFX.MainMenuController;

/**
 * @author howden2
 *
 */
public class ScenarioEditorTest {
/*
	@Test
	public void MainMenuControlTest(){
		ScenarioParser SP = new ScenarioParser(true);
		SP.setScenarioFile("/eecs/home/howden2/git/Braille-Project/Enamel/FactoryScenarios/Scenario_1.txt");
		
	}*/
	
	@Test
	public void MainMenu(){
		System.out.println("abc");
		Application.launch(MainMenu.class);
		new Thread() {
	          @Override
	          public void run() {
	        	  
	          }
	      }.start();
	      
	}
	

}
