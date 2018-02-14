package gui.controllers;

import java.io.File;

import javax.activation.CommandMap;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 * Scenario maker controller.
 * @author Jinho Hwang
 *
 */
public class ScenarioMakerController {

	// FXML for radio buttons
    @FXML
    private ToggleGroup CommandPosGroup;
    
    // FXML for scenario name textField
 	@FXML
 	TextField scenarioNameField;
 	
 	// FXML for # of cell text
 	@FXML
 	TextField numCellTextField;
 	
 	// FXML for # of buttons text
 	@FXML
 	TextField numButtonTextField;
 	
 	File scenarioFile;
 	
 	public ScenarioMakerController(){
 	}
 	
 	public void setFile(File file) {
 		this.scenarioFile = file;
 		scenarioNameField.setText(file.getName());
 	}

}