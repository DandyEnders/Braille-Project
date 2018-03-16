package gui.layouts;
import java.io.IOException;

import gui.controllers.ScenarioEditorController;
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

public class ScenarioEditor extends View {
	
	ScenarioEditorController control;
	
	//When ScenarioEditor is called, it displays
	public ScenarioEditor(){
		super();
	}

	@Override
	protected void initialize() {
		// Window should not be resizeable ( else destroys our layout )
		window.setResizable(false);
		
		control.loadFileOn(Language.scenarioPath);
		
		// If close button (red X button) is pressed, hide the window instead of destroy
		window.setOnCloseRequest(e -> hide());
	}
	
	
	
}
