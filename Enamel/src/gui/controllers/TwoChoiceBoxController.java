package gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class TwoChoiceBoxController {

    @FXML
    private AnchorPane root;

    @FXML
    private Button leftButton;

    @FXML
    private Button rightButton;

    @FXML
    private Label label;
    
    private boolean isLeft;
    
    @FXML
    public void leftButtonClicked() {
    	isLeft = true;
    	close();
    }
    
    @FXML
    public void rightButtonClicked() {
		isLeft = false;
		close();
    }
    
    public void setLeftButtonLabel(String str) {
    	this.leftButton.setText(str);
    }
    
    public void setRightButtonLabel(String str) {
    	this.rightButton.setText(str);
    }
    
    public void setMiddleLabel(String str) {
    	this.label.setText(str);
    }
    
    public boolean getAnswer() {
    	return isLeft;
    }
    
    public void close() {
    	Stage window = (Stage) root.getScene().getWindow();
    	window.close();
    }

}
