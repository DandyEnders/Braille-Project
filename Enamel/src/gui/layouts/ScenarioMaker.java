package gui.layouts;
import java.io.File;

import gui.controllers.ScenarioMakerController;
import javafx.stage.Modality;
import javafx.stage.Stage;
import utility.Language;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;

/**
 * 
 * @author Jinho Hwang
 *
 */
public class ScenarioMaker {
	// The integer window wiedth / height. Added for convenience.
	private final static Integer windowWidth = 800;
	private final static Integer windowHeight = 750;
	private final static Integer windowMinHeight = windowHeight;
	private final static Integer windowMinWidth = windowWidth;
	private final static Integer windowMaxWidth = windowWidth;
	
	// Controller
	ScenarioMakerController control;
	
	// Input file
	private File scenarioFile;
	
	// List of pane, scene, stage
	private AnchorPane root;
	private Scene scene;
	private Stage window;
	
	public ScenarioMaker(){
		this(new File("./temp/"));
	}
	
	public ScenarioMaker(File scenarioFile){
		this.scenarioFile = scenarioFile;
		display();
	}
	
	public void display() {
		try {
			
			// Initialize the window
			window = new Stage();
			
			// Allow resize, but set width fixed, set height resizeable with a minimum.
			window.setResizable(true);
			window.setMinWidth(windowMinWidth);
			window.setMaxWidth(windowMaxWidth);
			window.setMinHeight(windowMinHeight);
			
			// Set focus on ScenarioMaker 
			// i.e. you cannot do any other tasks on other windows
			// until ScenarioMaker closes
			window.initModality(Modality.APPLICATION_MODAL);
			FXMLLoader loader = new FXMLLoader(getClass().getResource(Language.scenarioMakerFxml));
			
			// Set the base panel ( root ) from fxml file
			root = (AnchorPane)loader.load();
			
			// Gets the controller so I can pass scenarioFile
			control = loader.getController();
			
			// Pass scenarioFile to Control
			control.setFile(scenarioFile);
			
			// Scene is built using the base panel
			scene = new Scene(root,windowWidth,windowHeight);
			
			/*// Adds CSS formatting into scene. 
			 * activate this once we format things.
			scene.getStylesheets().add(getClass().getResource(Language.scanerioMaker.Css).toExternalForm());
			*/
			
			// Set scene to the window, title, and show it
			window.setScene(scene);
			window.setTitle(Language.scanerioMakerTitle);
			
			// Close the whole thing when red X is pressed.
			window.setOnCloseRequest(e -> window.close());
			
			
			
		} catch(Exception e) {
			e.printStackTrace(); // only happens when mainMenu.fxml changes its name
		}

	}
	
	/**
	 * Method for making the window visible.
	 * @Author Jinho Hwang
	 */
	public void show() {
		window.show();
	}
	
	/**
	 * Method for making the window invisible.
	 * @Author Jinho Hwang
	 */
	public void hide() {
		window.hide();
	}
}


