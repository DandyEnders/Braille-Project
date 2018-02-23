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
public class CreateCommandPopUpBox {

	// control.
	CreateCommandPopUpBoxController control;
	
	private List<Phrase> phraseList;
	private String pos;
	private int selectedIndex;
	
	// List of pane, scene, stage
	private AnchorPane root;
	private Scene scene;
	private Stage window;

	
	public CreateCommandPopUpBox(List<Phrase> phraseList, String pos, int selectedIndex) {
		this.phraseList = phraseList;
		this.pos = pos;
		this.selectedIndex = selectedIndex;
	}
	
	public List<Phrase> display() {
		try {
			// Instantiates new window
			window = new Stage();
			
			// Window should not be re-sizeable ( else destroys our layout )
			window.setResizable(false);
			
			// Make this box a pop up and stops any input until this pop up is resolved ( closed )
			window.initModality(Modality.APPLICATION_MODAL);
			
			// Get the FXML loader.
			FXMLLoader loader = new FXMLLoader(getClass().getResource(Language.createCommandPopUpBoxFxml));
			
			// Loading the format from FXML file
			root = (AnchorPane)loader.load();
			
			// Get controller.
			control = loader.getController();
			
			control.setList(phraseList);
			control.setPos(pos);
			control.setIndex(selectedIndex);
			control.setComboBoxItems();
			control.loadSoundFiles();
			
			// Instantiate a new scene
			scene = new Scene(root);
			window.setTitle(Language.createCommandPopUpBoxTitle);
			window.setScene(scene);
			
			// If close button (red X button) is pressed, hide the window instead of destroy
			window.setOnCloseRequest(e -> control.close());
			
			window.showAndWait();
			
		}catch (IOException e1) {
			e1.printStackTrace(); // This happens if scenarioEditor.fxml changes its name.
		}
		
		return control.getAnswer();
		
	}
	
}
