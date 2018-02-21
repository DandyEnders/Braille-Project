package gui.controllers;

import java.io.File;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ErrorListReportPopUpBoxController {

    @FXML
    private Button closeButton;

    @FXML
    private Label errorLabel;

    @FXML
    private ListView<String> errorList;
    
    @FXML
    private AnchorPane root;
    
    ObservableList<String> obsErrorList;
    
    public void setErrorList(List<File> errorList) {
    	
    	obsErrorList = FXCollections.observableArrayList();
    	
    	for(File error : errorList) {
    		obsErrorList.add(error.getName());
    	}
    	
    	this.errorList.setItems(obsErrorList);
    	
    }
    
    public void setLabel(String errorString) {
    	this.errorLabel.setText(errorString);
    }
    
    public void close() {
    	this.obsErrorList.clear();
    	Stage stage = (Stage) root.getScene().getWindow();
    	stage.close();
    	
    }
    
    
}

