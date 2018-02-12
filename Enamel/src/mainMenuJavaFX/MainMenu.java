package mainMenuJavaFX;
	
import javafx.application.Application;
import javafx.stage.Stage;
import utility.Language;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

 /**
  * Main menu view class. It is controlled by MainMenu.fxml and MainMenuController. 
  * @author Jinho Hwang
  *
  */

public class MainMenu extends Application {
	
	private final static Integer windowWidth = 600;
	private final static Integer windowHeight = 400;

	public AnchorPane root;
	public Scene scene;
	public Stage window;
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			window = primaryStage;
			
			root = (AnchorPane)FXMLLoader.load(getClass().getResource(Language.mainMenuFxml));
			
			scene = new Scene(root,windowWidth,windowHeight);
			scene.getStylesheets().add(getClass().getResource(Language.mainMenuCss).toExternalForm());
			
			window.setScene(scene);
			window.setTitle(Language.mainMenuTitle);
			
			window.show();
			
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	


}
