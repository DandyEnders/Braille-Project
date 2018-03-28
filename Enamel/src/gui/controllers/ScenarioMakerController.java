package gui.controllers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import gui.layouts.CreateCommandPopUpBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utility.AuthoringUtil;
import utility.ErrorUtil;
import utility.Language;
import utility.Phrase;
/**
 * Scenario maker controller.
 * @author Jinho Hwang
 *
 */
public class ScenarioMakerController extends Controller implements Returnable<File>{

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
    private Button createCommandButton;

    @FXML
    private ToggleGroup CommandPosGroup;

    @FXML
    private Button removeCommandButton;

    @FXML
    private Button moveUpButton;

    @FXML
    private Button moveDownButton;

    @FXML
    private RadioButton replace;

    @FXML
    private RadioButton below;
 	
    @FXML
    private RadioButton above;
    
    @FXML
    private Button exitButton;
    
 	File scenarioFile;
 	
 	List<Phrase> phraseList;
 	
 	ObservableList<Phrase> phraseListObs;
 	
 	ObservableList<String> numberOfCellAndButton;
 	
 	public ScenarioMakerController(){
 		phraseListObs = FXCollections.observableArrayList();
 		numberOfCellAndButton = FXCollections.observableArrayList();
 		phraseList = new ArrayList<Phrase>();
 	}
 	
 	public void setFile(File file) {
 		// Set this scenario maker with this file.
 		this.scenarioFile = file;
 		
 		// Set the scenario name.
 		scenarioNameField.setText(file.getName());
 		
 		List<Phrase> lawPhraseList = null;
 		try {
	 		// Bring the list of Phrases.
	 		lawPhraseList = AuthoringUtil.phraseScenario(file);
 		}catch(IOException e) {
 			ErrorUtil.alertMessageShowException("Formatting error!", "The following error occured.", e);
 		}
 		
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
	 		
	 		if(lawPhraseList.size() > 2) {
	 			// Set phraseList to be rest of phrase list other then first two.
	 			phraseList.addAll(lawPhraseList.subList(2, lawPhraseList.size()));
	 		}
	 		
	 		listUpdate();
	 		
	 		if(!phraseList.isEmpty())
	 			listOfCommands.getSelectionModel().select(0);
 		}
 		
 	}
 	
    private boolean isItemSelected() {
    	return selectedItemIndex() != -1;
    }
    
    private int selectedItemIndex() {
    	return listOfCommands.getSelectionModel().getSelectedIndex();
    }
    
    private Phrase selectedItem() {
    	return listOfCommands.getSelectionModel().getSelectedItem();
    }
 	
    private void listUpdate() {
    	this.phraseListObs.clear();
    	
    	for(Phrase phrase : phraseList) {
    		phraseListObs.add(phrase);
    	}
    	
    	listOfCommands.setItems(phraseListObs);
    }
    
 	public void createCommand() {
 		
 		Stage window = new Stage();
 		CreateCommandPopUpBox popup = new CreateCommandPopUpBox();
		popup.display(window);
		List<Phrase> returnPhrase = popup.getReturn();
 		
 		if(returnPhrase != null) {
 			int index = 0;
	 		if(!isItemSelected()) {
	 			phraseList.addAll(0, returnPhrase);
	 		}else {
	 			index = selectedItemIndex();
	 			if(above.isSelected()) {
	 				phraseList.addAll(index,returnPhrase);
	 			}else if (replace.isSelected()) {
	 				phraseList.set(index,returnPhrase.get(0));
	 				if(phraseList.size() > 1) {
	 					phraseList.addAll(index+1, returnPhrase.subList(1, returnPhrase.size()));
	 				}
	 			}else {
	 				phraseList.addAll(index+1,returnPhrase);
	 				index++;
	 			}
	 		}
	 		
	 		listUpdate();
	 		listOfCommands.getSelectionModel().select(index);
 		}
 	}
 	
 	
 	
 	public void removeCommand() {
 		if(isItemSelected()) {
 			int index = selectedItemIndex();
 			phraseList.remove(index);
 			listOfCommands.getSelectionModel().select(index);
 			listUpdate();
 		}
 	}
 	
 	private void swap(int pos1, int pos2) {
 		Phrase temp = phraseListObs.get(pos1);
 		phraseList.set(pos1, phraseListObs.get(pos2));
 		phraseList.set(pos2, temp);
 	}
 	
 	public void moveUp() {
 		if(isItemSelected()) {
 			if(selectedItemIndex() > 0) {
 				int index = selectedItemIndex();
 				swap(index, index-1);
 				listUpdate();
 				listOfCommands.getSelectionModel().select(index-1);
 			}
 		}
 	}
 	
 	public void moveDown() {
 		if(isItemSelected()) {
 			if(selectedItemIndex() < phraseListObs.size()-1) {
 				int index = selectedItemIndex();
 				swap(index, index+1);
 				listUpdate();
 				listOfCommands.getSelectionModel().select(index+1);
 			}
 		}
 	}
	
	private boolean isCellAndButtonFieldValid() {
		
		if(numCellTextField.getText().isEmpty()) {
 			ErrorUtil.alertMessageSimple("Empty cell number textfield", "Your cell number textfield is empty.");
 			numCellTextField.requestFocus();
 			return false;
 		}else{
 			
 			int cellNumber = 0;
 			
 			try {
 				cellNumber = Integer.parseInt(numCellTextField.getText());
 			} catch(NumberFormatException e) {
 				ErrorUtil.alertMessageSimple("Non integer cell number textfield", "Your cell number is not a number!");
 				numCellTextField.requestFocus();
 				return false;
 			}
 			
 			if(cellNumber < 1) {
 				ErrorUtil.alertMessageSimple("Non positive cell number", "Your cell number is not an positive number!");
 				numCellTextField.requestFocus();
 				return false;
 			}
 		}
		
		
		
		if(numButtonTextField.getText().isEmpty()) {
 			ErrorUtil.alertMessageSimple("Empty button number textfield", "Your button number textfield is empty.");
 			numButtonTextField.requestFocus();
 			return false;
 		}else {
 			
 			int buttonNumber = 0;

 			try {
 				buttonNumber = Integer.parseInt(numButtonTextField.getText());
 			} catch(NumberFormatException e) {
 				ErrorUtil.alertMessageSimple("Non integer button number textfield", "Your button number is not a number!");
 				numButtonTextField.requestFocus();
 				return false;
 			}
 			
 			if(buttonNumber < 1) {
 				ErrorUtil.alertMessageSimple("Non positive button number", "Your button number is not an positive number!");
 				numButtonTextField.requestFocus();
 				return false;
 			}
 			
 		}
		
		return true;
	}
 	
	
	
 	public void save() {
 		
 		if (isCellAndButtonFieldValid()){
 			
 			// The Output scenario
			String scenarioString = "";
			
			scenarioString = makeLawScenarioString(scenarioString);
			List<Phrase> scenario = null;
			
			try {
				scenario = AuthoringUtil.phraseScenario(scenarioString);
			} catch (IOException e) {
				ErrorUtil.alertMessageShowException("Invalid scenario syntax.", e.getLocalizedMessage(), e);
			}
			
			
			if(scenario != null) {
				
				String scenarioFileName = scenarioNameField.getText();
				try {
					scenarioFileName = formatScenarioName(scenarioFileName);
					
					scenarioFile = new File(Language.scenarioPath + scenarioFileName);
					
					Writer fileWriter = new FileWriter(scenarioFile);
					fileWriter.write(scenarioString);
					fileWriter.close();
	
					close();
				
				} catch (IOException e) {
					ErrorUtil.alertMessageSimple("File writing failed", "File writing failed! Contact technical staff for this.");
				} catch (Exception e) {
					ErrorUtil.alertMessageSimple("Missing Scenario name.", "Type the scenario name in the scenario name field.");
					scenarioNameField.requestFocus();
				}
				
			}
	 		
	 	}
 	}

	/**
	 * Adds .txt at the end.
	 * @param scenarioFileName
	 * @return formatted scneario name
	 * @throws Exception 
	 */
	private String formatScenarioName(String scenarioFileName) throws Exception {
		
		String scenarioFileFormat = Language.scenarioFileFormat;
		
		if(scenarioFileName.isEmpty() || scenarioFileName == null || scenarioFileName.equals(Language.emptyString)){
			throw new Exception("Empty scneario name");
		}
		
		// if the scenario file name is less than the file format ex : ab , ac, caa, a...
		if(scenarioFileName.length() <= scenarioFileFormat.length()) {
			if(!scenarioFileName.equals(scenarioFileFormat)) {
				// just add the file extension.
				scenarioFileName += scenarioFileFormat;
			}
		}else {
			// if the scenario file does not have extension at the end, add it.
			if(!scenarioFileName.substring(scenarioFileName.length()-scenarioFileFormat.length(), scenarioFileName.length()).equals(scenarioFileFormat)) {
				scenarioFileName += scenarioFileFormat;
			}
		}
		return scenarioFileName;
	}

	/**
	 * @param scenarioString
	 * @return lawScenarioString
	 */
	private String makeLawScenarioString(String scenarioString) {
		scenarioString = addCellAndButton(scenarioString);
		
		for(Phrase phrase : phraseList) {
			scenarioString += phrase + "\n";
		}
		return scenarioString;
	}

	/**
	 * @param scenarioString
	 * @return scnearioString 
	 * 			with button and text
	 */
	private String addCellAndButton(String scenarioString) {
		scenarioString += "Cell " + numCellTextField.getText() + "\n";
		scenarioString += "Button " + numButtonTextField.getText() + "\n";
		return scenarioString;
	}

	
 	
 	@FXML
 	protected void keyPressed(KeyEvent event) {
    	if(event.getCode().equals(Language.openKey)) {
    		if(event.getSource().equals(createCommandButton)) {
    			createCommand();
    		}else if(event.getSource().equals(removeCommandButton)) {
    			removeCommand();
    		}else if(event.getSource().equals(moveUpButton)) {
    			moveUp();
    		}else if(event.getSource().equals(moveDownButton)) {
    			moveDown();
    		}else if(event.getSource().equals(saveButton)) {
    			save();
    		}else if(event.getSource().equals(exitButton)) {
    			close();
    		}
    	}
    }

	@Override
	public File getReturn() {
		return this.scenarioFile;
	}
 	
 	
}