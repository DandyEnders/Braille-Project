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
public class ScenarioMaker extends ReturnableView<ScenarioMakerController,File> {
	
	// The integer window wiedth / height. Added for convenience.
	private final static Integer windowWidth = 614;
	private final static Integer windowHeight = 750;
	private final static Integer windowMinHeight = windowHeight;
	private final static Integer windowMinWidth = windowWidth;
	private final static Integer windowMaxWidth = windowWidth;
	
	// Input file
	private File scenarioFile;
	
	public ScenarioMaker(){
		this(new File(""));
	}
	
	public ScenarioMaker(File scenarioFile){
		super();
		this.scenarioFile = scenarioFile;
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
		control.setFile(scenarioFile);
	
		
		// Close the whole thing when red X is pressed.
		window.setOnCloseRequest(e -> window.close());
		window.showAndWait();
	}
	
}


