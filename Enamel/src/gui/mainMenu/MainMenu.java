package gui.mainMenu;
	
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import utility.Language;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

 /**
  * Main menu view class. It is controlled by MainMenuController.
  * This class is responsible for showing the mainMenu window.
  * @author Jinho Hwang
  *
  */

public class MainMenu extends Application {
	
	// The integer window wiedth / height. Added for convenience.
	private final static Integer windowWidth = 600;
	private final static Integer windowHeight = 400;

	// List of pane, scene, stage
	private AnchorPane root;
	private Scene scene;
	private Stage window;
	
	
	/**
	 * This method starts the window and show it.
	 * 
	 * @author Jinho Hwang
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			//debug
			System.out.println("Braille Program executing...");
			
			// Initialize the window
			window = primaryStage;
			
			// Set the base panel ( root ) from fxml file
			root = (AnchorPane)FXMLLoader.load(getClass().getResource(Language.mainMenuFxml));
			
			// Scene is built using the base panel
			scene = new Scene(root,windowWidth,windowHeight);
			
			// Adds CSS formatting into scene. ( it is empty file now )
			scene.getStylesheets().add(getClass().getResource(Language.mainMenuCss).toExternalForm());
			
			// Set scene to the window, title, and show it
			window.setScene(scene);
			window.setTitle(Language.mainMenuTitle);
			window.show();
			
			// Close the whole thing when red X is pressed.
			window.setOnCloseRequest(e -> Platform.exit());
			
			
			
		} catch(Exception e) {
			e.printStackTrace(); // only happens when mainMenu.fxml changes its name
		}
	}
	
	


}
