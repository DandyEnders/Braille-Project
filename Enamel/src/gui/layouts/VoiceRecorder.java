package gui.layouts;

import gui.controllers.VoiceRecorderController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import utility.Language;

public class VoiceRecorder extends View<VoiceRecorderController> {
	
	public VoiceRecorder() {
		super();
	}

	@Override
	protected void initialize() {
		// Allow resize, but set width fixed, set height resizeable with a minimum.
		window.setResizable(false);
		
		// Set focus on ScenarioMaker 
		// i.e. you cannot do any other tasks on other windows
		// until ScenarioMaker closes
		window.initModality(Modality.APPLICATION_MODAL);
		
		// Load files from AudioFile dir.
		control.loadFileOn(Language.audioPath);
		
		// Close the whole thing when red X is pressed.
		window.setOnCloseRequest(e -> window.close());
		
		window.show();
		
	}
}
