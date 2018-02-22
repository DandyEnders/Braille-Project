package gui.layouts;

import java.io.IOException;

import gui.controllers.CreateCommandPopUpBoxController;
import javafx.collections.ObservableList;
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
	
	// List of pane, scene, stage
	private AnchorPane root;
	private Scene scene;
	private Stage window;

	
	public CreateCommandPopUpBox(ObservableList<Phrase> phraseList, String pos, int selectedIndex) {
		display(phraseList, pos,selectedIndex);
	}
	
	public void display(ObservableList<Phrase> phraseList, String pos, int selectedIndex) {
		try {
			// Instantiates new window
			window = new Stage();
			
			// Window should not be re-sizeable ( else destroys our layout )
			window.setResizable(false);
			
			// Make this box a pop up and stops any input until this pop up is resolved ( closed )
			window.initModality(Modality.APPLICATION_MODAL);
			
			// Get the FXML loader.
			FXMLLoader loader = new FXMLLoader(getClass().getResource(Language.createCommandPopUpBox));
			
			// Loading the format from FXML file
			root = (AnchorPane)loader.load();
			
			// Get controller.
			control = loader.getController();
			
			control.setObsList(phraseList);
			control.setPos(pos);
			control.setIndex(selectedIndex);
			control.setComboBoxItems();
			
			// Instantiate a new scene
			scene = new Scene(root);
			window.setTitle(Language.createCommandPopUpBoxTitle);
			window.setScene(scene);
			
			// If close button (red X button) is pressed, hide the window instead of destroy
			window.setOnCloseRequest(e -> control.close());
			
			window.show();
			
		}catch (IOException e1) {
			e1.printStackTrace(); // This happens if scenarioEditor.fxml changes its name.
		}
		
	}
	
}
