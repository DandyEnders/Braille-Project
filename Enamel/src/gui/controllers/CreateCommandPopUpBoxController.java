package gui.controllers;

/**
 * Popup box controller.
 * @author Jinho Hwang
 */
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import utility.AuthoringUtil;
import utility.Phrase;

public class CreateCommandPopUpBoxController {

	@FXML
    private AnchorPane root;

    @FXML
    private Button createButton;

    @FXML
    private Button discardButton;

    @FXML
    private ComboBox<String> phraseTypeComboBox;

    @FXML
    private Pane firstArgumentPane;

    @FXML
    private Pane secondArgumentPane;
    
    @FXML
    private TextField firstArgumentTextField;
    
    @FXML
    private TextField secondArgumentTextField;
    
    ObservableList<String> obsPhraseTypeList;
    
    ObservableList<Phrase> phraseList;
    
    String pos;
    
    int selectedIndex;
    
    Phrase returnPhrase;
    
    public CreateCommandPopUpBoxController(){
    	String[] typeList = AuthoringUtil.getTypeList();
    	obsPhraseTypeList = FXCollections.observableArrayList(typeList);
    	obsPhraseTypeList.add("emptyLine");
    	obsPhraseTypeList.add("speak");
    }
    
    @FXML
    public void close() {
		Stage window = (Stage)root.getScene().getWindow();
		window.close();
    }
    
    @FXML
    public void comboSelected() {
    	if(phraseTypeComboBox.getSelectionModel().getSelectedItem().equals("/~sound:")) {
    		setPaneDisable(false,true);
    	}else {
    		setPaneDisable(true,true);
    	}
    }
    
    private void setPaneDisable(boolean first, boolean second) {
    	firstArgumentPane.setDisable(first);
    	secondArgumentPane.setDisable(second);
    }
    
    @FXML
    public void create() {
    	String firstArg, secondArg;
    	firstArg = firstArgumentTextField.getText() == null ? firstArg = null : (firstArg = firstArgumentTextField.getText());
    	secondArg = secondArgumentTextField.getText() == null ? secondArg = null : (secondArg = secondArgumentTextField.getText());
    	
		returnPhrase = AuthoringUtil.phraseThisLine(phraseTypeComboBox.getSelectionModel().getSelectedItem() + firstArg + " " + secondArg);//new Phrase(phraseTypeComboBox.getSelectionModel().getSelectedItem(),firstArg,secondArg);
		
		if(returnPhrase!= null) {
			if(pos.equals("above")) {
				phraseList.add(selectedIndex , returnPhrase);
	 			
	 		}else if(pos.equals("replace")) {
	 			phraseList.set(selectedIndex, returnPhrase);
	 			
	 		}else if(pos.equals("below")) {
	 			
	 			if(selectedIndex+1 == phraseList.size()) {
	 				phraseList.add(returnPhrase);
	 				
	 			}else {
	 				phraseList.add(selectedIndex+1, returnPhrase);
	 			}
	 			
	 		}
			close();
		}
		// TODO : Make an error box pop up if it is invalid
		
		
    }
    
    public void setComboBoxItems() {

    	phraseTypeComboBox.setItems(obsPhraseTypeList);
    }
    
    public void setObsList(ObservableList<Phrase> phraseList) {
    	this.phraseList = phraseList;
    }
    public void setPos(String pos) {
    	this.pos = pos;
    }
    public void setIndex(int index) {
    	this.selectedIndex = index;
    }
     
   
}
