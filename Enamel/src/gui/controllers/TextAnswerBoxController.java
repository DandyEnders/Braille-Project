package gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utility.Language;

public class TextAnswerBoxController extends Controller implements Returnable<String>{

    @FXML
    private Button finishButton;

    @FXML
    private Label label;

    @FXML
    private TextField answer;
    
   
    @Override
    public String getReturn() {
    	return answer.getText();
    }
    
    public void setLabel(String str) {
    	this.label.setText(str);
    }
    
    @FXML
    protected void keyPressed(KeyEvent event) {
    	if(event.getCode().equals(Language.openKey)) {
    		if(event.getSource().equals(finishButton)) {
    			close();
    		}
    	}
    }



}