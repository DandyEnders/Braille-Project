package gui.layouts;

import gui.controllers.TextAnswerBoxController;
import gui.controllers.VoiceRecorderController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import utility.Language;

public class TextAnswerBox {

	TextAnswerBoxController control;
	
	// List of pane, scene, stage
	private AnchorPane root;
	private Scene scene;
	private Stage window;
	
	private String title;
	private String label;
	
	
	public TextAnswerBox(String title, String label) {
		this.title = title;
		this.label = label;
	}
	
	public String display(Stage window) {
		try {
			// Initialize the window
			this.window = window;
			
			// Allow resize, but set width fixed, set height resizeable with a minimum.
			window.setResizable(false);
			
			// Set focus on ScenarioMaker 
			// i.e. you cannot do any other tasks on other windows
			// until ScenarioMaker closes
			window.initModality(Modality.APPLICATION_MODAL);
			FXMLLoader loader = new FXMLLoader(getClass().getResource(Language.textAnswerBoxFxml));
			
			// Set the base panel ( root ) from fxml file
			root = (AnchorPane)loader.load();
			
			// Gets the controller so I can pass scenarioFile
			control = loader.getController();
			
			// Set label.
			control.setLabel(label);
			// Scene is built using the base panel
			scene = new Scene(root);
			
			// Set scene to the window, title, and show it
			window.setScene(scene);
			window.setTitle(title);
			
			// Close the whole thing when red X is pressed.
			window.setOnCloseRequest(e -> control.finish());
			window.showAndWait();
			
			
		}catch(Exception e) {
			e.printStackTrace(); // only happens when mainMenu.fxml changes its name
		}
		
		return control.getAnswer();
	}
	

	
}
