package startProgram;

import enamel.ScenarioParser;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import mainMenuJavaFX.MainMenu;
import mainMenuSwing.MainMenuController;
import mainMenuSwing.MainMenuModel;
import mainMenuSwing.MainMenuView;

/**
 * This class starts the program execution by creating view, model, and a controller for
 * mainMenu.
 * @author Jinho Hwang
 *
 */

public class BrailleProject{

    public static void main(String[] args) { 	  	
    	
    	
    	//MainMenuView mainMenuView = new MainMenuView();
    	
       // ScenarioParser s = new ScenarioParser(true);
	    //s.setScenarioFile("./FactoryScenarios/Scenario_" + 1 + ".txt");
    	
      System.out.println("LOL");
     
      new Thread() {
          @Override
          public void run() {
        	  Application.launch(MainMenu.class, args);
          }
      }.start();
	  
    	   
    	  
    }
}