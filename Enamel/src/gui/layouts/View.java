package gui.layouts;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import gui.controllers.Controller;
import javafx.event.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import utility.Language;
import utility.LoggerUtil;
import utility.ViewUtil;

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
		this();
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
		
		List<Node> childrenList = ViewUtil.getAllNodes(root);
		for(Node child : childrenList) {
			if(!(child instanceof Pane)) {
				ViewUtil.setLogger(child);
			}
		}
		

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
