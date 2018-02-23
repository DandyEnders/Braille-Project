package gui.layouts;

import gui.controllers.VoiceRecorderController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import utility.Language;

public class VoiceRecorder {
	
	VoiceRecorderController control;
	
	// List of pane, scene, stage
	private AnchorPane root;
	private Scene scene;
	private Stage window;
	
	
	public VoiceRecorder() {
		display();
	}
	
	public void display() {
		try {
			// Initialize the window
			window = new Stage();
			
			// Allow resize, but set width fixed, set height resizeable with a minimum.
			window.setResizable(false);
			
			// Set focus on ScenarioMaker 
			// i.e. you cannot do any other tasks on other windows
			// until ScenarioMaker closes
			window.initModality(Modality.APPLICATION_MODAL);
			FXMLLoader loader = new FXMLLoader(getClass().getResource(Language.voiceRecorderFxml));
			
			// Set the base panel ( root ) from fxml file
			root = (AnchorPane)loader.load();
			
			// Gets the controller so I can pass scenarioFile
			control = loader.getController();
			
			// Load files from AudioFile dir.
			control.loadFileOn("./FactoryScenarios/AudioFiles");
			// Scene is built using the base panel
			scene = new Scene(root);
			
			/*// Adds CSS formatting into scene. 
			 * activate this once we format things.
			scene.getStylesheets().add(getClass().getResource(Language.scanerioMaker.Css).toExternalForm());
			*/
			
			// Set scene to the window, title, and show it
			window.setScene(scene);
			window.setTitle(Language.voiceRecorderTitle);
			
			// Close the whole thing when red X is pressed.
			window.setOnCloseRequest(e -> window.close());
		}catch(Exception e) {
			e.printStackTrace(); // only happens when mainMenu.fxml changes its name
		}
	}
	
	/**
	 * Method for making the window visible.
	 * @Author Jinho Hwang
	 */
	public void show() {
		window.show();
	}
	
	/**
	 * Method for making the window invisible.
	 * @Author Jinho Hwang
	 */
	public void hide() {
		window.hide();
	}
}
