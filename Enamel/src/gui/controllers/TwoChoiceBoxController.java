package gui.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utility.Language;

public class TwoChoiceBoxController extends Controller implements Returnable<Boolean>{

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
    
    public Boolean getReturn() {
    	return isLeft;
    }
    
    @FXML
    protected void keyPressed(KeyEvent event) {
    	label.focusTraversableProperty().bind(Platform.accessibilityActiveProperty());
    	if(event.getCode().equals(Language.openKey)) {
    		if(event.getSource().equals(leftButton)) {
    			leftButtonClicked();
    		}else if(event.getSource().equals(rightButton)) {
    			rightButtonClicked();
    		}
    	}
    }



}
