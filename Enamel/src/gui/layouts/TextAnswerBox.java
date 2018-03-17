package gui.layouts;

import gui.controllers.TextAnswerBoxController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import utility.Language;

public class TextAnswerBox extends ReturnableView<TextAnswerBoxController,String> {
	
	private String title;
	private String label;
	
	
	public TextAnswerBox(String title, String label) {
		super();
		this.title = title;
		this.label = label;
	}

	@Override
	protected void initialize() {
		// Allow resize, but set width fixed, set height resizeable with a minimum.
		window.setResizable(false);
		
		// Set focus on ScenarioMaker 
		// i.e. you cannot do any other tasks on other windows
		// until ScenarioMaker closes
		window.initModality(Modality.APPLICATION_MODAL);
		
		// Set label.
		control.setLabel(label);
		
		// Close the whole thing when red X is pressed.
		window.setOnCloseRequest(e -> control.close());
		window.showAndWait();
		
		window.setTitle(title);
	}
	

	
}
