package gui.layouts;

import java.io.IOException;
import java.util.List;

import gui.controllers.CreateCommandPopUpBoxController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import utility.Language;
import utility.Phrase;

/**
 * Command creating popup box.
 * @author Jinho Hwang
 *
 */
public class CreateCommandPopUpBox extends View {

	// control.
	CreateCommandPopUpBoxController control;
	
	private List<Phrase> phraseList;
	private String pos;
	private int selectedIndex;

	
	public CreateCommandPopUpBox(List<Phrase> phraseList, String pos, int selectedIndex) {
		super();
		this.phraseList = phraseList;
		this.pos = pos;
		this.selectedIndex = selectedIndex;
	}

	@Override
	protected void initialize() {
		// Window should not be re-sizeable ( else destroys our layout )
		window.setResizable(false);
		
		// Make this box a pop up and stops any input until this pop up is resolved ( closed )
		window.initModality(Modality.APPLICATION_MODAL);
		
		control.setList(phraseList);
		control.setPos(pos);
		control.setIndex(selectedIndex);
		control.setComboBoxItems();
		control.loadSoundFiles();
		
		// If close button (red X button) is pressed, hide the window instead of destroy
		window.setOnCloseRequest(e -> control.close());
		
		window.showAndWait();
		
		
	}
	
}
