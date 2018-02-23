package gui.controllers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.assertj.core.util.Files;

import enamel.ScenarioParser;
import gui.layouts.ErrorListReportPopUpBox;
import gui.layouts.ScenarioMaker;
import gui.layouts.TwoChoiceBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import utility.AuthoringUtil;
import utility.Language;
/**
 * Scenario editor controller.
 * @author Jinho Hwang
 *
 */

public class ScenarioEditorController {
	
    @FXML
    private Button createScenarioButton;

    @FXML
    private Button editScenarioButton;

    @FXML
    private Button saveScenarioButton;

    @FXML
    private Button loadScenarioButton;

    @FXML
    private Button runScenarioButton;

    @FXML
    private Button removeScenarioButton;
    
    @FXML
    private Button exitButton;

	
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
	
	// ScenarioMaker
	ScenarioMaker scenarioMaker;
	
	private boolean isVisual;
	
	// Initialize the fileList.
	public ScenarioEditorController(){
		obsFileList = FXCollections.observableArrayList();
		fileList = new ArrayList<File>();
	}
	
	
	 public void loadFileOn(String dir) {
    	File scenarioFileFolder = new File(dir);
    	
    	if(scenarioFileFolder.isDirectory()) {
    		for(File scenarioFile : scenarioFileFolder.listFiles()) {
    			if(!scenarioFile.isDirectory()) {
    				if(scenarioFile.getName().length() >= 4) {
	    				if(scenarioFile.getName().substring(scenarioFile.getName().length()-4, scenarioFile.getName().length()).equals(".txt")) {
	    					if(AuthoringUtil.phraseScenario(scenarioFile) != null) {
	    						fileList.add(scenarioFile);
	    					}
	    				}
    				}
    			}
    		}
    	}
    	
    	listUpdate();
    }
	 
	private int getSelectedIndex() {
		return scenarioList.getSelectionModel().getSelectedIndex();
	}
	
	private File getSelectedFile() {
		return fileList.get(getSelectedIndex());
	}
	
	private boolean isSelected() {
		return getSelectedIndex() != -1;
	}
	 
	 
	public void removeScenario() {
		if(isSelected()) {
			
			Files.delete(fileList.remove(getSelectedIndex()));
			
			listUpdate();
		}
	}
	 
	private void listUpdate() {
		this.obsFileList.clear();
		
		for(File file : fileList) {
			obsFileList.add(file.getName());
		}
		
		scenarioList.setItems(obsFileList);
	}
	
	/**
	 * Loads up scenario files into the fileList and set view of names of file in the fileList.
	 * This method uses FileChooser to pickup multiple scenario files.
	 * @author Jinho Hwang
	 * @throws IOException
	 */
	public void loadScenario() {
		Stage window = new Stage();
		
		// Create fileChooser and set its title
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle(Language.scenarioEditorLoadFileChooserTitle);
		
		try {
		// Set starting directory ( the directory you start to select from )
		fileChooser.setInitialDirectory(new File(Language.scenarioPath).getCanonicalFile());
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		// Open fileChooser, get multiple files
		List<File> inputFiles = fileChooser.showOpenMultipleDialog(window);
		
		// List of phraseFailFiles
		List<File> phrasingFailedFiles = new ArrayList<File>();
		
		if(inputFiles != null){
			// Add files into fileList
			fileList.addAll(inputFiles);
			
			// Initialize observable file lists
			obsFileList = FXCollections.observableArrayList();
			
			// Transfer all the file names into observable file lists
			for(File file : fileList) {
				
				// If the loaded Files can be phrased
				if(AuthoringUtil.phraseScenario(file) != null && !obsFileList.contains(file.getName()) ) {
					
					// Add it on the list
					obsFileList.add(file.getName());
					
				// If loadedFiles cannot be phrased ( syntax error or something )
				}else {
					// Add the un-phrase-able file into FailList
					phrasingFailedFiles.add(file);
				}
			}
			
			// If loading was done without any failures,
			if(phrasingFailedFiles.size() == 0) {
				
				// Set listItems = phraseable file list
				scenarioList.setItems(obsFileList);
				
			// If loading was done with all failures,
			}else if(phrasingFailedFiles.size() == fileList.size()) {
				
				new ErrorListReportPopUpBox("Failed to load all of the files.",
						"The editor has failed to load all the files\nselected. The reasons will be stated"
						+ "on /error/ folder.", phrasingFailedFiles);
			}else {
				new ErrorListReportPopUpBox("Failed to load some of the files.",
						"The editor has failed to load some of the files\nselected. The reasons will be stated "
						+ "on /error/ folder.", phrasingFailedFiles);
				
				// Set listItems = phraseable file list
				scenarioList.setItems(obsFileList);
				
			}
			
			
			// For fileList to remove all the excessive files.
			for(File file : phrasingFailedFiles) {
				if(fileList.contains(file)) {
					fileList.remove(file);
				}
			}
			
		}
	
	}
	
	/**
	 * This method runs selected scenario from the list.
	 * If nothing was selected or list is empty, nothing happens when button is clicked.
	 * @author Jinho Hwang
	 */
	public void runScenario() {
		
		// If the list is empty or not elected then
		if(!scenarioList.getSelectionModel().isEmpty()){
			
			Stage window = new Stage();
			
			TwoChoiceBox choiceBox = new TwoChoiceBox("Player type", "Which one do you want to run as?", "Visual Player", "Audio Player");
			isVisual = choiceBox.display(window);
			
			
			// Get the selected item index from the list
			int index = scenarioList.getSelectionModel().getSelectedIndex();
					
			// debug
/*			System.out.println("running : " + fileList.get(index));
			System.out.println("index : " + scenarioList.getSelectionModel().getSelectedIndex());*/
			
			if( index != -1) {
				// Start a Thread to start enamel program. Thread is needed to run without an error.
				Thread starterCodeThread = new Thread("Starter Code Thread") {
				    public void run(){    
				    	//Open Scenario simulator, allow visual
				        ScenarioParser s = new ScenarioParser(isVisual);
				        
				        //Set Scenario simulator to run the file from the fileList. (not from GUI list)
						s.setScenarioFile(fileList.get(index).getPath());
				    }
				};
				starterCodeThread.start();
					
			}
		}
	}
	
	/**
	 * This method is for user to save a list-selected scenario from
	 * scenario list on the scenario editor.
	 * @author Jinho Hwang
	 */
	public void saveScenario() {
		// If the list is empty or not elected then
		if(!scenarioList.getSelectionModel().isEmpty()) {
			
			try {
				
				// New window other then scenario Editor
				Stage window = new Stage();
				
				// Get the selected item index from the list
				int index = scenarioList.getSelectionModel().getSelectedIndex();
				
				// Create fileChooser and set its title
				FileChooser fileChooser = new FileChooser();
				fileChooser.setTitle(Language.scenarioEditorSaveFileChooserTitle);
				
				// Set its initial name as the scenario file name on the list.
				fileChooser.setInitialFileName(scenarioList.getSelectionModel().getSelectedItem());
				
				// Set starting directory ( the directory you start to select from )
				fileChooser.setInitialDirectory(new File("./FactoryScenarios").getCanonicalFile());
				
				// Create a dummy variable for customized file.
				//( selectedFile can be null if user decides to not to make a file )
				File writtenFile = fileChooser.showSaveDialog(window);
				
				// If user pressed save after write its name,
				if(writtenFile != null ) {
					
					// Create fileWriter and scanner to read selected file from the fileList.
					Writer fileWriter = new FileWriter(writtenFile);
					Scanner scenarioFileString = new Scanner(fileList.get(index));
					
					// Create a String to write.
					String scenarioScript = "";
					
					// Copy all the contents from the selected file to scenarioScript.
					while(scenarioFileString.hasNextLine()) {
						scenarioScript += scenarioFileString.nextLine() + "\n";
					}
					
					// Writes file with user written property with list-selected scenario
					fileWriter.write(scenarioScript);
					
					// Closes IOStream.
					scenarioFileString.close();
					fileWriter.close();
				}
					
			} catch (IOException e) {
				e.printStackTrace(); // Happens when factory Scenarios folder do not exit.
			}
			
			
		}
	}
	
	/**
	 * This method opens a ScenarioMaker, with all empty fields.
	 * @Author Jinho Hwang
	 */
	public void createScenario() {
		// Create scenario maker with empty args and show it; 
		// empty name and zero cells / buttons
		scenarioMaker = new ScenarioMaker(fileList,obsFileList);
		scenarioMaker.show();  
	}
	
	/**
	 * This method opens a Scenemaker, with file selected.
	 */
	public void editScenario() {
		// The index of scenario selected
		int selectedIndex = scenarioList.getSelectionModel().getSelectedIndex();
		
		// If scenario is selected
		if(selectedIndex != -1){
			
			// Get the selected file
			File selectedFile = fileList.get(selectedIndex);
			
			// Create scenario maker with the File;
			// name of the file / commands / # of cell / button will be filled.
			scenarioMaker = new ScenarioMaker(selectedFile,fileList,obsFileList);
			scenarioMaker.show();
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
	
	@FXML
	void keyPressed(KeyEvent event) {
    	if(event.getCode().equals(Language.openKey)) {
    		if(event.getSource().equals(createScenarioButton)) {
    			createScenario();
    		}else if(event.getSource().equals(editScenarioButton)) {
    			editScenario();
    		}else if(event.getSource().equals(saveScenarioButton)) {
    			saveScenario();
    		}else if(event.getSource().equals(loadScenarioButton)) {
    			loadScenario();
    		}else if(event.getSource().equals(runScenarioButton)) {
    			runScenario();
    		}else if(event.getSource().equals(removeScenarioButton)) {
    			removeScenario();
    		}else if(event.getSource().equals(exitButton)) {
    			hideScenarioEditor();
    		}
    	}
    }
	

	
	
}
