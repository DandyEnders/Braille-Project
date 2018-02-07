package startProgram;

import enamel.ScenarioParser;
import mainMenu.MainMenuController;
import mainMenu.MainMenuModel;
import mainMenu.MainMenuView;

/**
 * This class starts the program execution by creating view, model, and a controller for
 * mainMenu.
 * @author Jinho Hwang
 *
 */


public class BrailleProject  {

    public static void main(String[] args) { 	  	
    	
    	MainMenuModel mainMenuModel = new MainMenuModel();
    	MainMenuView mainMenuView = new MainMenuView();
    	MainMenuController mainMenuController = new MainMenuController(mainMenuModel, mainMenuView);
    	
    	
    		
		/*
	    ScenarioParser s = new ScenarioParser(true);
	    s.setScenarioFile("FactoryScenarios/Scenario_" + 1 + ".txt");
	    */
    	    
    	  
    }
}