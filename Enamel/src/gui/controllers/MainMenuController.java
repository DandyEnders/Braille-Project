package gui.controllers;

import gui.layouts.ScenarioEditor;
import gui.layouts.VoiceRecorder;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utility.Language;
import utility.LoggerUtil;

/**
 * Main menu controller.
 * This class is responsible for controlling view and model.
 * @author Jinho Hwang
 *
 */

public class MainMenuController extends Controller{
	
    @FXML
    private Button exitButton;

    @FXML
    private Button voiceRecordButton;

    @FXML
    private Button scenarioEditButton;
    
    @FXML
    private Label titleLabel;
	
	// Scenario Editor
	private ScenarioEditor scenarioEditor;
	
	private VoiceRecorder voiceRecorder;
	
	// Scenario initialization
	public MainMenuController(){
		super();
		scenarioEditor = new ScenarioEditor();
		voiceRecorder = new VoiceRecorder();
		
	}
	
	
	// Close the program
	public void exitProgram() {
		LoggerUtil.close();
		Platform.exit();
		System.exit(0);
	}
	
	// Show up the scenarioEditor
	@FXML
	public void openScenarioEditor() {
		Stage editorWindow = new Stage();
		scenarioEditor.display(editorWindow);
	}
	
	@FXML
	public void openVoiceRecorder() {
		Stage voiceRecorderWindow = new Stage();
		voiceRecorder.display(voiceRecorderWindow);
	}
	
    @FXML
	protected void keyPressed(KeyEvent event) {
    	//titleLabel.focusTraversableProperty().bind(Platform.accessibilityActiveProperty());
    	if(event.getCode().equals(Language.openKey)) {
    		if(event.getSource().equals(scenarioEditButton)) {
    			openScenarioEditor();
    		}else if(event.getSource().equals(voiceRecordButton)) {
    			openVoiceRecorder();
    		}else if(event.getSource().equals(exitButton)) {
    			exitProgram();
    		}
    	}
    	
    	if(event.getCode().equals(Language.key_MainMenuScenarioEditor)) {
    		openScenarioEditor();
    	}else if(event.getCode().equals(Language.key_MainMenuVoiceRecorder)) {
    		openVoiceRecorder();
    	}else if(event.getCode().equals(Language.key_MainMenuExit)) {
    		exitProgram();
    	}
    	
    }
	
	
}
