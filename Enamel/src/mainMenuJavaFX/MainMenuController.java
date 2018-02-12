package mainMenuJavaFX;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

/**
 * Main menu controller.
 * @author Jinho Hwang
 *
 */

public class MainMenuController {
	
	@FXML
	AnchorPane root;
	
	private ScenarioEditor scenarioEditor;
	
	/*public MainMenu mainMenu;
	
	public MainMenuController(MainMenu mainMenu){
		
		this.mainMenu = mainMenu;
		this.mainMenu.setController(this);
		scenarioEditor = new ScenarioEditor();
		
	}*/
	
	public MainMenuController(){
		
		scenarioEditor = new ScenarioEditor();
		
	}
	
	public void printYay() {
		System.out.println("yay");
	}
	
	public void exitProgram() {
		Platform.exit();
	}
	
	public void openScenarioEditor() {
		scenarioEditor.show();
	}
	
	
}
