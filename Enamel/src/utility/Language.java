/**
 * 
 */
package utility;

import javafx.scene.input.KeyCode;

/**
 * This class contains all the possible strings used by classes and fxml.
 * @author Jinho Hwang
 *
 */
public final class Language {
	
	public static final String viewPath = "/gui/resources/view/";
	public static final String scenarioPath = "./FactoryScenarios/";
	public static final String audioPath = scenarioPath + "AudioFiles/";
	
	public static final KeyCode openKey = KeyCode.SPACE;
	//public static final KeyCode sayCurrentWindowKey = KeyCode.BACK_QUOTE;
	
	// Fxml directory
	public static final String mainMenuFxml = viewPath + "MainMenu.fxml";
	public static final String scenarioEditorFxml = viewPath + "ScenarioEditor.fxml";
	public static final String scenarioMakerFxml = viewPath + "ScenarioMaker.fxml";
	public static final String errorListReportPopUpBoxFxml = viewPath + "ErrorListReportPopUpBox.fxml";
	public static final String createCommandPopUpBoxFxml = viewPath + "CreateCommandPopUpBox.fxml";
	public static final String voiceRecorderFxml = viewPath + "VoiceRecorder.fxml";
	public static final String textAnswerBoxFxml = viewPath + "TextAnswerBox.fxml";
	public static final String twoChoiceBoxFxml = viewPath + "TwoChoiceBox.fxml";
	
	
	// CSS directory
	public static final String mainMenuCss = "/gui/resources/css/MainMenu.css";
	 
	// Main menu Strings
	public static final String mainMenuTitle = "Authoring App";
	
	// Scenario editor Strings
	public static final String scenarioEditorTitle = "Scenario Editor";
	public static final String scenarioEditorLoadFileChooserTitle = "Open Scenario";
	public static final String scenarioEditorSaveFileChooserTitle = "Save Scenario";

	
	// Scanerio maker Strings
	public static final String scanerioMakerTitle = "Scanerio Maker";
	
	// PopupBox Strings
	//public static final String errorListReportPopUpBoxTitle = "";
	public static final String createCommandPopUpBoxTitle = "Create Scenario";
	
	// Voice Recorder Strings
	public static final String voiceRecorderTitle = "Voice Recorder";
	public static final String voiceRecorderFileChooserTitle = "Choose sound files";
	
	// Text answerbox title
	//public static final String textAnswerBoxTitle = "";
	
	
	//These are unused so far due to imcompatibility of scenebuilder to the variable String.
	
	// Main menu Strings
	public static final String mainMenuExitButton = "Exit";
	public static final String mainMenuScenarioEditorButton = "Scenario Editor";
	public static final String mainMenuBraileProjectLabel = "Braile Project";
	//? Main menu Strings

	
	// Scenario editor Strings
	
	public static final String scenarioEditorCreateScenario = "Create a new Scenario";
	public static final String scenarioEditorEditScenario = "Edit selected Scenario";
	public static final String scenarioEditorSaveScenario = "Save selected Scenario";
	public static final String scenarioEditorLoadScenario = "Load Scenario";
	public static final String scenarioEditorRunScenario = "Run Selected Scenario";
	public static final String scenarioEditorExit = "Exit";
	//? Scenario editor Strings
	
	//? unused
}
