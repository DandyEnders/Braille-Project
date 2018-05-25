package gui.layouts;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import gui.controllers.Controller;
import javafx.event.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utility.Language;

public abstract class View<T extends Controller> {
	
	final static protected Logger logger = Logger.getLogger("viewLogger");
	
	
	private FileHandler fileHandler;
	private File log;
	
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
		
		try {
			
			log = Language.userLog;
			fileHandler = new FileHandler(log.toString());
			
			logger.addHandler(fileHandler);
			logger.setUseParentHandlers(false);
			SimpleFormatter formatter = new SimpleFormatter();
			fileHandler.setFormatter(formatter);
		}catch(IOException e) {
			logger.warning("Logger File handler thrown exception: " + e.getMessage());
			e.printStackTrace();
		}catch(Exception e) {
			logger.warning("Logger thrown exception: " + e.getMessage());
			e.printStackTrace();
		}
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
		
		this.keyLogger(scene);
		this.mouseLogger(scene);
		
		// Set scene to the window, title, and show it
		window.setScene(scene);
		window.setTitle(title);
		
		isDisplayed = true;
		
		initialize();
		
		try {
			window.setOnCloseRequest(e->{
				fileHandler.flush();
				fileHandler.close();
			});
		}catch(Exception e) {
			
		}
	}
	
	private void keyLogger(Scene scene) {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>(){

			@Override
			public void handle(KeyEvent event) {
				try {
					logger.info(new Date().toString() + " : " + "Key pressed : " + event.getText());
					System.out.println("Key Logged");
				}catch (SecurityException e) {
					e.printStackTrace();
				}
				
			}
			
			
			
		});
	}
	
	private void mouseLogger(Scene scene) {
		scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				try {
					logger.info(new Date().toString() + " : " + "Mouse pressed : " + event.getSource().toString());
					System.out.println("Mouse Logged");
				}catch (SecurityException e) {
					e.printStackTrace();
				}
				
			}
			
			
		});
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
