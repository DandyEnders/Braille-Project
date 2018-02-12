package gui.scenarioEditor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;

import enamel.ScenarioParser;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import utility.Language;
/**
 * Scenario menu controller.
 * @author Jinho Hwang
 *
 */

public class ScenarioEditorController {
	
	// List of file loaded.
	List<File> fileList;
	
	// List of FXML components
	// List
	@FXML
	ListView<String> scenarioList;
	
	// Main panel
	@FXML
	AnchorPane root;
	
	// OberservableList for List items.
	ObservableList<String> obsFileList;
	
	// Initialize the fileList.
	// TODO : make a model for scenario Editor to keep track of.
	public ScenarioEditorController(){
		fileList = new ArrayList<File>();
		
	}
	
	
	/**
	 * Loads up scenario files into the fileList and set view of names of file in the fileList.
	 * This method uses FileChooser to pickup multiple scenario files.
	 * @author Jinho Hwang
	 * @throws IOException
	 */
	public void loadScenario() throws IOException {
		Stage window = new Stage();
		
		// Create fileChooser and set its title
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle(Language.scenarioEditorLoadFileChooserTitle);
		
		// Set starting directory ( the directory you start to select from )
		fileChooser.setInitialDirectory(new File("./FactoryScenarios").getCanonicalFile());
		
		// Open up fileChooser, let user pick multiple files, add it into fileList
		fileList.addAll(fileChooser.showOpenMultipleDialog(window));
		
		// Initialize observable file lists
		obsFileList = FXCollections.observableArrayList();
		
		// Transfer all the file names into observable file lists
		for(File file : fileList) {
			obsFileList.add(file.getName());
			System.out.println(obsFileList.get(obsFileList.size()-1));
		}
		
		// Set the view of List to the list of File names.
		scenarioList.setItems(obsFileList);
	
	}
	
	/**
	 * This method runs selected scenario from the list.
	 * If nothing was selected or list is empty, nothing happens when button is clicked.
	 * @author Jinho Hwang
	 */
	public void runScenario() {
		
		// If the list is empty or not elected then
		if(!scenarioList.getSelectionModel().isEmpty()){
			
			// Get the selected item index from the list
			int index = scenarioList.getSelectionModel().getSelectedIndex();
					
			// debug
/*			System.out.println("running : " + fileList.get(index));
			System.out.println("index : " + scenarioList.getSelectionModel().getSelectedIndex());*/
			
			// Start a Thread to start enamel program. Thread is needed to run without an error.
			Thread starterCodeThread = new Thread("Starter Code Thread") {
			    public void run(){    
			    	//Open Scenario simulator, allow visual
			        ScenarioParser s = new ScenarioParser(true);
			        
			        //Set Scenario simulator to run the file from the fileList. (not from GUI list)
					s.setScenarioFile(fileList.get(index).getPath());
			    }
			};
			starterCodeThread.start();
				
			
		}
	}
	
	/**
	 * This method is used for Exiting ( hiding ) editor.
	 * May change it later by closing it completely.
	 * @Author Jinho Hwang
	 */
	public void hideScenarioEditor() {
		root.getScene().getWindow().hide();
	}
	
	
	

	
	
}
