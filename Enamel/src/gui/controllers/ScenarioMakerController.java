package gui.controllers;

import java.io.File;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utility.AuthoringUtil;
import utility.Phrase;
/**
 * Scenario maker controller.
 * @author Jinho Hwang
 *
 */
public class ScenarioMakerController {

    @FXML
    private AnchorPane root;

    @FXML
    private ListView<Phrase> listOfCommands;

    @FXML
    private TextField scenarioNameField;

    @FXML
    private TextField numCellTextField;

    @FXML
    private TextField numButtonTextField;

    @FXML
    private Button saveButton;

    @FXML
    private ToggleButton toggleTextViewButton;

    @FXML
    private Button createCommandButton;

    @FXML
    private ToggleGroup CommandPosGroup;

    @FXML
    private Button removeCommandButton;

    @FXML
    private Button moveUpButton;

    @FXML
    private Button moveDownButton;
 	
 	File scenarioFile;
 	
 	List<Phrase> phraseList;
 	
 	ObservableList<Phrase> phraseListObs;
 	
 	ObservableList<String> numberOfCellAndButton;
 	
 	public ScenarioMakerController(){
 		phraseListObs = FXCollections.observableArrayList();
 		numberOfCellAndButton = FXCollections.observableArrayList();
 	}
 	
 	public void setFile(File file) {
 		// Set this scenario maker with this file.
 		this.scenarioFile = file;
 		
 		// Set the scenario name.
 		scenarioNameField.setText(file.getName());
 		
 		// Bring the list of Phrases.
 		List<Phrase> lawPhraseList = AuthoringUtil.phraseScenario(file);
 		
 		// If scenario load was successful,
 		if( lawPhraseList != null) {
 			
	 		// Gets first two phrase containing num cell and buttons.
	 		Phrase cellPhrase = lawPhraseList.get(0);
	 		Phrase buttonPhrase = lawPhraseList.get(1);
	 		
	 		// Add those cell # and button # to obsList for text fields.
	 		numberOfCellAndButton.add(cellPhrase.getArguments()[0]);
	 		numberOfCellAndButton.add(buttonPhrase.getArguments()[0]);
	 		
	 		// Set cell # and button # on text fields.
	 		numCellTextField.setText(numberOfCellAndButton.get(0));
	 		numButtonTextField.setText(numberOfCellAndButton.get(1));
	 		
	 		// Set phraseList to be rest of phrase list other then first two.
	 		phraseListObs.addAll(lawPhraseList.subList(2, lawPhraseList.size()));
	 		
	 		// Set the gui list.
	 		listOfCommands.setItems(phraseListObs);
 		}
 		
 	}
 	
 	public void exit() {
 		Stage window = (Stage)root.getScene().getWindow();
 		window.close();
 	}
 	
 	public void createCommand() {
 		
 	}
 	
}