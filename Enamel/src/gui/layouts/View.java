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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utility.Language;
import utility.LoggerUtil;

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
		
		List<Node> childrenList = getAllNodes(root);
		for(Node child : childrenList) {
			setLogger(child);
		}
		

		// Set scene to the window, title, and show it
		window.setScene(scene);
		window.setTitle(title);
		
		isDisplayed = true;
		
		initialize();
		
	}
	
	public static List<Node> getAllNodes(Parent root) {
	    List<Node> nodes = new ArrayList<Node>();
	    addAllDescendents(root, nodes);
	    return nodes;
	}

	private static void addAllDescendents(Parent parent, List<Node> nodes) {
	    for (Node node : parent.getChildrenUnmodifiable()) {
	        nodes.add(node);
	        if (node instanceof Parent)
	            addAllDescendents((Parent)node, nodes);
	    }
	}
	
	
	private void setLogger(Node node) {
		keyLogger(node);
		mouseLogger(node);
	}
	
	
	private void keyLogger(Node node) {
		node.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>(){

			@Override
			public void handle(KeyEvent event) {
				try {
					
					Node node = (Node)event.getSource();
					String tostr = "";
					
					if(node instanceof Button) {
						tostr = ((Button) node).getText();
					}else if(node instanceof Label) {
						tostr = ((Label) node).getText();
					}else if(node instanceof ListView) {
						
					}else {
						tostr = event.getSource().toString();
					}
					
					LoggerUtil.log(tostr, "Key pressed at : " + tostr + " \"" + event.getText() + "\"");
					System.out.println("Key Logged");
				}catch (SecurityException e) {
					e.printStackTrace();
				}
				
			}
			
			
			
		});
	}
	
	private void mouseLogger(Node node) {
		
		node.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				try {
					
					Node node = (Node)event.getSource();
					String tostr = "";
					if(node instanceof Button) {
						tostr = ((Button) node).getText();
					}else if(node instanceof Label) {
						tostr = ((Label) node).getText();
					}else {
						tostr = event.getSource().toString();
					}
					
					
					LoggerUtil.log(tostr, "Mouse pressed at : " + tostr);
					System.out.println("Mouse Logged");
				}catch (SecurityException e) {
					e.printStackTrace();
				}
				
			}
		});
		
		
		/*node.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				try {
					logger.info("Mouse pressed : " + event.getSource().toString());
					System.out.println("Mouse Logged");
				}catch (SecurityException e) {
					e.printStackTrace();
				}
				
			}
			
			
		});*/
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
