package gui.layouts;
	

import java.util.List;
import java.util.logging.Logger;

import gui.controllers.MainMenuController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import utility.Language;
import utility.LoggerUtil;
import utility.ViewUtil;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.fxml.FXMLLoader;

 /**
  * Main menu view class. It is controlled by MainMenuController.
  * This class is responsible for showing the mainMenu window.
  * @author Jinho Hwang
  *
  */

public class MainMenu extends Application {
	
	MainMenuController control;
	
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
	public void start(Stage window) {
		try {
			
			// Window should not be resizeable ( else destroys our layout )
			window.setResizable(false);
			
			System.out.println(Language.fxmlPath(this.getClass().getSimpleName()));
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource(Language.fxmlPath(this.getClass().getSimpleName())));
			
			// Set the base panel ( root ) from fxml file
			root = (AnchorPane)loader.load();
			
			control = loader.getController();
			
			// Scene is built using the base panel
			scene = new Scene(root,windowWidth,windowHeight);
			
			// Adds CSS formatting into scene. ( it is empty file now )
			scene.getStylesheets().add(getClass().getResource(Language.mainMenuCss).toExternalForm());
			
			
			// Set scene to the window, title, and show it
			window.setScene(scene);
			window.setTitle(Language.mainMenuTitle);
			window.show();
			
			List<Node> childrenList = ViewUtil.getAllNodes(root);
			
			for(Node child : childrenList) {
				if(!(child instanceof Pane) && !(child instanceof Text)) {
					ViewUtil.setLogger(child);
				}
			}
			
			// Close the whole thing when red X is pressed.
			window.setOnCloseRequest(e -> {
				LoggerUtil.close();
				Platform.exit();
			});
			
			
			
		} catch(Exception e) {
			e.printStackTrace(); // only happens when mainMenu.fxml changes its name
		}
	}
	
	


}
