package gui.controllers;

import gui.layouts.ScenarioEditor;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

/**
 * Main menu controller.
 * This class is responsible for controlling view and model.
 * @author Jinho Hwang
 *
 */

public class MainMenuController {
	
	// FXML component; Base panel
	@FXML
	private AnchorPane root;
	
	// Scenario Editor
	private ScenarioEditor scenarioEditor;
	
	// Scenario initialization
	public MainMenuController(){
		scenarioEditor = new ScenarioEditor();
		
	}
	
	// Close the program
	public void exitProgram() {
		Platform.exit();
	}
	
	// Show up the scenarioEditor
	public void openScenarioEditor() {
		scenarioEditor.show();
	}
	
	
}
