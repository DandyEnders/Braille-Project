package mainMenuJavaFX;

import java.io.File;
import java.io.IOException;
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
	
	List<File> fileList;
	
	@FXML
	ListView<File> scenarioList;
	
	@FXML
	AnchorPane root;
	
	ObservableList<File> obsFileList;
	
	public void loadScenario() throws IOException {
		Stage window = new Stage();

		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle(Language.scenarioEditorLoadFileChooserTitle);
		fileChooser.setInitialDirectory(new File("./FactoryScenarios").getCanonicalFile());
		fileList = fileChooser.showOpenMultipleDialog(window);
		
		
		obsFileList = FXCollections.observableList(fileList);
		
		scenarioList.setItems(obsFileList);
	
	}
	
	public void runScenario() {
		
		System.out.println("running : " + scenarioList.getSelectionModel().getSelectedItem().getPath());
		if(scenarioList.getSelectionModel().getSelectedIndex() != -1){
			Thread starterCodeThread = new Thread("Starter Code Thread") {
			    public void run(){    
			        ScenarioParser s = new ScenarioParser(true);
					s.setScenarioFile(scenarioList.getSelectionModel().getSelectedItem().getPath().toString());
			    }
			};
			starterCodeThread.start();
				
			
		}
	}
	
	public void hideScenarioEditor() {
		root.getScene().getWindow().hide();
	}
	
	
	

	
	
}
