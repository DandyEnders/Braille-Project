package mainMenuJavaFX;
import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import utility.Language;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

/**
 * Scenario Editor gui class.
 * 
 * @author Jinho Hwang
 *
 */

public class ScenarioEditor {

	public Stage window;
	
	public ScenarioEditor(){
		 display();
	}
	
	private void display() {
		try {
			window = new Stage();
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource(Language.scenarioEditorFxml));
			Scene scene = new Scene(root);
			
			window.setScene(scene);
			window.setTitle(Language.scenarioEditorTitle);
			
			window.setOnCloseRequest(e -> hide());
			
			
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public void show() {
		window.show();
	}
	
	public void hide() {
		window.hide();
	}
	
}
