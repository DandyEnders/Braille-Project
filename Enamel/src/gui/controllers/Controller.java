/**
 * 
 */
package gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * @author Jinho Hwang
 *
 */
public abstract class Controller {
	
	// FXML component; Base panel
	@FXML
	protected AnchorPane root;
	
	protected Stage window;

	public Controller() {
	}
	
	public void setWindow(Stage window) {
		this.window = window;
	}
	
	@FXML
	public void show() {
		this.window.show();
	}
	
	@FXML
	public void hide() {
		this.window.hide();
	}
	
	@FXML
	public void close() {
    	this.window.close();
    }
	
	@FXML
	protected abstract void keyPressed(KeyEvent event);
}
