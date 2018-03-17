package gui.layouts;

import java.io.File;

import gui.controllers.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utility.Language;

public abstract class View<T extends Controller> {
	
	protected T control;
	
	// List of pane, scene, stage
	protected AnchorPane root;
	protected Scene scene;
	protected Stage window;
	
	protected FXMLLoader loader;
	
	protected boolean isDisplayed;
	
	protected final String className = this.getClass().getSimpleName();
	
	protected String title = Language.titleName(className);
	
	protected Object returnObject;
	
	public View() {
		this.window = null;
	}
	
	public View(Stage window) {
		isDisplayed = false;
		this.window = window;
	}
	
	public void display(Stage window) {
		// FXML loader
		
		this.window = window;
		
		loader = new FXMLLoader(getClass().getResource(Language.fxmlPath(className)));
		
		try {
			// Set the base panel ( root ) from fxml file
			root = (AnchorPane)loader.load();
		}catch(Exception e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Unable to load fxml file."); // only happens when mainMenu.fxml changes its name
		}
		
		// Gets the controller so I can pass scenarioFile
		control = loader.getController();
		
		control.setWindow(window);
		// Scene is built using the base panel
		scene = new Scene(root);
		
		// Set scene to the window, title, and show it
		window.setScene(scene);
		window.setTitle(title);
		
		isDisplayed = true;
		
		initialize();
		
	}
	
	protected abstract void initialize();
	
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
