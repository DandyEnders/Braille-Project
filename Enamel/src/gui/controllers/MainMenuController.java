package gui.controllers;

import gui.layouts.ScenarioEditor;
import gui.layouts.TwoChoiceBox;
import gui.layouts.VoiceRecorder;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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
	
	private VoiceRecorder voiceRecorder;
	
	// Scenario initialization
	public MainMenuController(){
		scenarioEditor = new ScenarioEditor();
		voiceRecorder = new VoiceRecorder();
		
	}
	
	
	// Close the program
	public void exitProgram() {
		Platform.exit();
	}
	
	// Show up the scenarioEditor
	@FXML
	public void openScenarioEditor() {
		scenarioEditor.show();
	}
	
	@FXML
	public void openVoiceRecorder() {
		voiceRecorder.show();
	}
	
	
}
