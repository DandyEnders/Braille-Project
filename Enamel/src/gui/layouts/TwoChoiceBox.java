package gui.layouts;

import gui.controllers.TwoChoiceBoxController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import utility.Language;

/**
 * Box with two choices.
 * @author Jinho Hwang
 *
 */

public class TwoChoiceBox extends ReturnableView<TwoChoiceBoxController,Boolean> {
	
	private String label;
	private String leftButtonLabel, rightButtonLabel;
	
	public TwoChoiceBox(String title, String label, String leftButtonLabel, String rightButtonLabel) {
		super();
		this.title = title;
		this.label = label;
		this.leftButtonLabel = leftButtonLabel;
		this.rightButtonLabel = rightButtonLabel;
	}

	@Override
	protected void initialize() {
		// Allow resize, but set width fixed, set height resizeable with a minimum.
		window.setResizable(false);
		
		// until ScenarioMaker closes
		window.initModality(Modality.APPLICATION_MODAL);
		
		// Set label.
		control.setMiddleLabel(label);
		control.setLeftButtonLabel(leftButtonLabel);
		control.setRightButtonLabel(rightButtonLabel);
		
		// Close the whole thing when red X is pressed.
		window.setOnCloseRequest(e -> e.consume());
		
		window.showAndWait();
	}
	

	
}
