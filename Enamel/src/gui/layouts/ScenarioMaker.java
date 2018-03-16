package gui.layouts;
import java.io.File;
import java.util.List;

import gui.controllers.ScenarioMakerController;
import javafx.stage.Modality;
import javafx.stage.Stage;
import utility.Language;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;

/**
 * Scenario maker java
 * @author Jinho Hwang
 *
 */
public class ScenarioMaker extends View {
	// The integer window wiedth / height. Added for convenience.
	private final static Integer windowWidth = 614;
	private final static Integer windowHeight = 750;
	private final static Integer windowMinHeight = windowHeight;
	private final static Integer windowMinWidth = windowWidth;
	private final static Integer windowMaxWidth = windowWidth;
	
	// Controller
	ScenarioMakerController control;
	
	// Input file
	private File scenarioFile;
	private List<File> scenarioList;
	private List<String> scenarioNameList;
	
	public ScenarioMaker(List<File> scenarioList, List<String> scenarioNameList){
		this(new File(""),scenarioList,scenarioNameList);
	}
	
	public ScenarioMaker(File scenarioFile, List<File> scenarioList, List<String> scenarioNameList){
		super();
		this.scenarioList = scenarioList;
		this.scenarioFile = scenarioFile;
		this.scenarioNameList = scenarioNameList;
	}

	@Override
	protected void initialize() {
		// Allow resize, but set width fixed, set height resizeable with a minimum.
		window.setResizable(true);
		window.setMinWidth(windowMinWidth);
		window.setMaxWidth(windowMaxWidth);
		window.setMinHeight(windowMinHeight);
		
		window.initModality(Modality.APPLICATION_MODAL);
		
		// Pass scenarioFile to Control
		if(!scenarioFile.getName().equals("")) {
			control.setFile(scenarioFile);
		}
		control.setScenarioList(scenarioList);
		control.setScenarioNameList(scenarioNameList);
		
		// Close the whole thing when red X is pressed.
		window.setOnCloseRequest(e -> window.close());
	}
	
}


