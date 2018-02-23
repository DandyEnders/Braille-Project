package gui.layouts;

import gui.controllers.TextAnswerBoxController;
import gui.controllers.TwoChoiceBoxController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utility.Language;

/**
 * Box with two choices.
 * @author Jinho Hwang
 *
 */

public class TwoChoiceBox {

	TwoChoiceBoxController control;
	
	// List of pane, scene, stage
	private AnchorPane root;
	private Scene scene;
	private Stage window;
	
	private String title;
	private String label;
	private String leftButtonLabel, rightButtonLabel;
	
	
	public TwoChoiceBox(String title, String label, String leftButtonLabel, String rightButtonLabel) {
		this.title = title;
		this.label = label;
		this.leftButtonLabel = leftButtonLabel;
		this.rightButtonLabel = rightButtonLabel;
	}
	
	
	/**
	 * 
	 * @param window
	 * @return true if left was selected
	 * @return false if right was selected
	 */
	public boolean display(Stage window) {
		try {
			// Initialize the window
			this.window = window;
			
			// Allow resize, but set width fixed, set height resizeable with a minimum.
			window.setResizable(false);
			
			// Set focus on ScenarioMaker 
			// i.e. you cannot do any other tasks on other windows
			// until ScenarioMaker closes
			window.initModality(Modality.APPLICATION_MODAL);
			FXMLLoader loader = new FXMLLoader(getClass().getResource(Language.twoChoiceBoxFxml));
			
			// Set the base panel ( root ) from fxml file
			root = (AnchorPane)loader.load();
			
			// Gets the controller so I can pass scenarioFile
			control = loader.getController();
			
			// Set label.
			control.setMiddleLabel(label);
			control.setLeftButtonLabel(leftButtonLabel);
			control.setRightButtonLabel(rightButtonLabel);
			
			// Scene is built using the base panel
			scene = new Scene(root);
			
			// Set scene to the window, title, and show it
			window.setScene(scene);
			window.setTitle(title);
			
			// Close the whole thing when red X is pressed.
			window.setOnCloseRequest(e -> e.consume());
			window.showAndWait();
			
			
		}catch(Exception e) {
			e.printStackTrace(); // only happens when mainMenu.fxml changes its name
		}
		
		return control.getAnswer();
	}
	

	
}
