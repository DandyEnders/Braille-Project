package startProgram;

import enamel.ScenarioParser;
import gui.mainMenu.MainMenu;
import gui.mainMenu.MainMenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


/**
 * This class starts the program execution by creating view, model, and a controller for
 * mainMenu.
 * @author Jinho Hwang
 *
 */

public class BrailleProject{

    public static void main(String[] args) { 	  	
    	
      
      
      new Thread() {
          @Override
          public void run() {
        	  Application.launch(MainMenu.class, args);
          }
      }.start();
	  
     
    	   
    	  
    }
}