package gui.layouts;
import java.io.IOException;

import javafx.stage.Stage;
import utility.Language;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;

/**
 * Scenario Editor GUI class.
 * This class is responsible for drawing the buttons, panes, scenes, etc.
 * FXML file is used to format the layout.
 * 
 * @author Jinho Hwang
 *
 */

public class ScenarioEditor {
	
	//The main window
	private Stage window;
	
	//The basic root panel
	private AnchorPane root;
	
	//When ScenarioEditor is called, it displays
	public ScenarioEditor(){
		 display();
	}
	
	/**
	 * This method instantiates window, relate the root panel ( the very basic panel )
	 * with FXML, creates scene with root panel included. Then window is set with the scene
	 * with title given from Language file.
	 * 
	 * @Author Jinho Hwang
	 */
	private void display() {
		
		try {
			// Instantiates new window
			window = new Stage();
			
			// Window should not be resizeable ( else destroys our layout )
			window.setResizable(false);
			
			// Loading the format from FXML file
			root = (AnchorPane)FXMLLoader.load(getClass().getResource(Language.scenarioEditorFxml));
			
			// Instantiate a new scene
			Scene scene = new Scene(root);
			
			// Set window the scene and title
			window.setScene(scene);
			window.setTitle(Language.scenarioEditorTitle);
			
			// If close button (red X button) is pressed, hide the window instead of destroy
			window.setOnCloseRequest(e -> hide());
			
		} catch (IOException e1) {
			e1.printStackTrace(); // This happens if scenarioEditor.fxml changes its name.
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
