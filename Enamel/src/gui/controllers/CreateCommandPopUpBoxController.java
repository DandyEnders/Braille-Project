package gui.controllers;

import java.io.File;
import java.util.List;

/**
 * Popup box controller.
 * @author Jinho Hwang
 */
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import utility.AuthoringUtil;
import utility.Language;
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
    
    @FXML
    private Label firstArgText;

    @FXML
    private Label secondArgText;
    
    @FXML
    private ComboBox<String> firstArgList;
    private ObservableList<String> firstArgListObs;
    private List<File> firstArgFileList;
    
    ObservableList<String> obsPhraseTypeList;
    
    List<Phrase> phraseList;
    
    String pos;
    
    int selectedIndex;
    
    Phrase returnPhrase;
    
    public CreateCommandPopUpBoxController(){
    	String[] typeList = AuthoringUtil.getTypeList();
    	obsPhraseTypeList = FXCollections.observableArrayList(typeList);
    	firstArgListObs = FXCollections.observableArrayList();
    	obsPhraseTypeList.add("emptyLine");
    	obsPhraseTypeList.add("speak");
    	
    	
    }
    
    @FXML
    public void close() {
		Stage window = (Stage)root.getScene().getWindow();
		window.close();
    }
    
    public void loadSoundFiles() {
    	firstArgFileList = AuthoringUtil.getAudioFiles(Language.audioPath);
		for(File file : firstArgFileList) {
			firstArgListObs.add(file.getName());
		}
    }
    
    @FXML
    public void comboSelected(ActionEvent event) {
    	String comboBoxText = phraseTypeComboBox.getSelectionModel().getSelectedItem();
    	
    	firstArgList.setVisible(false);
		firstArgList.setDisable(true);
		firstArgumentTextField.setVisible(true);
		firstArgumentTextField.setDisable(false);
    	
		if(comboBoxText != null) {
			
		}
		
    	if(comboBoxText.equals("/~sound:")) {
    		setArgumentText("Sound file name", "");
    		firstArgumentTextField.setVisible(false);
    		firstArgumentTextField.setDisable(true);
    		
    		firstArgList.setVisible(true);
    		firstArgList.setDisable(false);
    		
    		firstArgList.setItems(firstArgListObs);
    		
    	}else if(comboBoxText.equals("/~skip:")) {
    		setArgumentText("Jump to String", "");
    		
    	}else if(comboBoxText.equals("/~pause:")) {
    		setArgumentText("Seconds to pause", "");
    		
    	}else if(comboBoxText.equals("/~repeat-button:")) {
    		setArgumentText("Index of button", "");
    		
    	}else if(comboBoxText.equals("/~repeat")) {
    		setArgumentText("", "");
    		
    	}else if(comboBoxText.equals("/~endrepeat")) {
    		setArgumentText("", "");
    		
    	}else if(comboBoxText.equals("/~reset-buttons")) {
    		setArgumentText("", "");
    		
    	}else if(comboBoxText.equals("/~skip-button:")) {
    		setArgumentText("Index of button", "Jump to String");
    		
    	}else if(comboBoxText.equals("/~disp-clearAll")) {
    		setArgumentText("", "");
    		
    	}else if(comboBoxText.equals("/~disp-cell-pins:")) {
    		setArgumentText("Index of cell", "Cell pins (eight 0 or 1)");
    		
    	}else if(comboBoxText.equals("/~disp-string:")) {
    		setArgumentText("String to display", "");
    		
    	}else if(comboBoxText.equals("/~disp-cell-char:")) {
    		setArgumentText("Index of cell", "English alphabet");
    		
    	}else if(comboBoxText.equals("/~disp-cell-raise:")) {
    		setArgumentText("Index of cell", "Index of a pin of the cell");
    		
    	}else if(comboBoxText.equals("/~disp-cell-lower:")) {
    		setArgumentText("Index of cell", "Index of a pin of the cell");
    		
    	}else if(comboBoxText.equals("/~disp-cell-clear:")) {
    		setArgumentText("Index of cell", "");
    		
    	}else if(comboBoxText.equals("/~user-input")) {
    		setArgumentText("", "");
    		
    	}else if(comboBoxText.equals("emptyLine")) {
    		setArgumentText("", "");
    		
    	}else if(comboBoxText.equals("speak")) {
    		setArgumentText("Sentence to speak", "");
    		
    	}else {
    		setArgumentText("", "");
    	}
    	
    	
    }
    
    
    private void setArgumentText(String first, String second) {
    	firstArgText.setText(first);
    	secondArgText.setText(second);
    	
    	firstArgumentTextField.setPromptText(first);
    	secondArgumentTextField.setPromptText(second);
    	
    	firstArgList.setPromptText(first);
    	
    	boolean firstVisible = false;
    	boolean secondVisible = false;
    	
    	if(first.equals(""))
    		firstVisible = true;
    	if(second.equals(""))
    		secondVisible = true;
    
		setPaneDisable(firstVisible,secondVisible);
		
	
    }
    
    private void setPaneDisable(boolean first, boolean second) {
    	firstArgumentPane.setDisable(first);
    	secondArgumentPane.setDisable(second);
    }
    
    @FXML
    public void create() {
    	String firstArg, secondArg;
    	firstArg = firstArgumentTextField.getText() == null ? firstArg = null : (firstArg = firstArgumentTextField.getText());
    	
    	if(firstArgList.isVisible() && firstArgList.getSelectionModel().getSelectedIndex() != -1) {
    		firstArg = firstArgList.getSelectionModel().getSelectedItem();
    	}
    	
    	secondArg = secondArgumentTextField.getText() == null ? secondArg = null : (secondArg = secondArgumentTextField.getText());
    	
    	
		returnPhrase = AuthoringUtil.phraseThisLine(phraseTypeComboBox.getSelectionModel().getSelectedItem() + firstArg + " " + secondArg);//new Phrase(phraseTypeComboBox.getSelectionModel().getSelectedItem(),firstArg,secondArg);
		
		
		if(returnPhrase!= null && phraseTypeComboBox.getSelectionModel().getSelectedIndex() != -1 ||
			firstArgList.isVisible() && firstArgList.getSelectionModel().getSelectedIndex() != -1) {
			
			if(phraseList.size() == 0) {
				phraseList.add(returnPhrase);
			}else {
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
			}
			
			close();
		}
		// TODO : Make an error box pop up if it is invalid
		
		
    }
    
    public void setComboBoxItems() {

    	phraseTypeComboBox.setItems(obsPhraseTypeList);
    }
    
    public void setList(List<Phrase> phraseList) {
    	this.phraseList = phraseList;
    }
    public void setPos(String pos) {
    	this.pos = pos;
    }
    public void setIndex(int index) {
    	this.selectedIndex = index;
    }

	public List<Phrase> getAnswer() {
		
		return phraseList;
	}
	
	
	@FXML
	void keyPressed(KeyEvent event) {
    	if(event.getCode().equals(Language.openKey)) {
    		if(event.getSource().equals(createButton)) {
    			create();
    		}else if(event.getSource().equals(discardButton)) {
    			close();
    		}
    	}
    }
     
   
}
