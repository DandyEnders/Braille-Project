package gui.controllers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

import org.assertj.core.util.Files;

import gui.layouts.ErrorListReportPopUpBox;
import gui.layouts.TextAnswerBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import utility.Language;

public class VoiceRecorderController {

	@FXML
    private AnchorPane root;

    @FXML
    private ToggleButton recordVoiceButton;

    @FXML
    private Button playButton;

    @FXML
    private Button exitButton;
    
    @FXML
    private Button deleteButton;
    
    @FXML
    private Button loadSoundButton;

    @FXML
    private ListView<String> voiceNameList;

    private ObservableList<String> voiceNameListObs;
    
    private ObservableList<File> voiceList; 

    private TargetDataLine targetDataLine;
    private File waveFile;
    private AudioInputStream audioStream;
    Thread record;
    
    public VoiceRecorderController() {
    	voiceNameListObs = FXCollections.observableArrayList();
    	voiceList = FXCollections.observableArrayList();
    	
    }
    
    public void loadFileOn(String dir) {
    	File audioFileFolder = new File(dir);
    	
    	
    	
    	if(audioFileFolder.isDirectory()) {
    		for(File soundFile : audioFileFolder.listFiles()) {
    			
    			if(soundFile.length() < 4) {
    				continue;
    			}
    			
    			if(!soundFile.isDirectory()) {
    				if(soundFile.getName().substring(soundFile.getName().length()-4, soundFile.getName().length()).equals(".wav")) {
    					voiceList.add(soundFile);
    				}
    			}
    		}
    	}
    	
    	listUpdate();
    }
    
    public void loadSoundFile() {
    	
    	try {
	    	Stage window = new Stage();
			
			// Create fileChooser and set its title
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle(Language.voiceRecorderFileChooserTitle);
			
			// Set starting directory ( the directory you start to select from )
			fileChooser.setInitialDirectory(new File(Language.audioPath).getCanonicalFile());
			
			// Open fileChooser, get multiple files
			List<File> inputFiles = fileChooser.showOpenMultipleDialog(window);
			
			List<File> failInput = new ArrayList<File>();
			
			if(inputFiles != null){
				
				for(File file : inputFiles) {
					
					if(!file.isDirectory()) {
						if(file.length() >= 4) {
							if(!voiceNameListObs.contains(file.getName())) {
								if(file.getName().substring(file.getName().length()-4, file.getName().length()).equals(".wav")) {
			    					voiceList.add(file);
			    					continue;
								}
							}
	    				}
					}
					
					failInput.add(file);
				}
				
				if(failInput.size() != 0){
					new ErrorListReportPopUpBox("Failed to import", "Following list of files were failed to be imported.", failInput);
				}
				
				listUpdate();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}	
	
    }
    
    
    public void exit() {
    	Stage window = (Stage)root.getScene().getWindow();
    	window.close();
    }
    
    private int getSelectedIndex() {
    	return voiceNameList.getSelectionModel().getSelectedIndex();
    }
    
    private File getSelectedFile() {
    	return voiceList.get(getSelectedIndex());
    }
    
    
    private boolean isSelected() {
    	return voiceNameList.getSelectionModel().getSelectedIndex() != -1;
    }
    
    @FXML
    public void recordVoiceButtonClicked() {
    	
    	// Toggle was enabled.
    	if(recordVoiceButton.isSelected()) {
    		
    		Stage window = new Stage();
    		
    		TextAnswerBox box = new TextAnswerBox("Name the sound recording...", "Type the name of the voice recording.");
    		String fileName = box.display(window);
    		
    		startSoundDataLine(fileName);
    		// Record.
    		
    		disableAllOthers(true);
    		
    	// Toggle was disabled.
    	}else {
    		
    		stopSoundDataLineAndSave();
    		
    		disableAllOthers(false);

    	}
    }
    
    private void disableAllOthers(boolean isDisable) {
    	
    	playButton.setDisable(isDisable);
    	exitButton.setDisable(isDisable);
    	deleteButton.setDisable(isDisable);
    	loadSoundButton.setDisable(isDisable);
    	exitButton.setDisable(isDisable);
    	voiceNameList.setDisable(isDisable);
    	
    	
    	
    	/*for(Node node : root.getChildren()) {
    		if(!node.getId().equals(recordVoiceButton.getId())) {
    			node.setDisable(isDisable);
    		}
    	}*/
    }
  
    
    private void startSoundDataLine(String fileName) {
    	try {
	    	AudioFormat format = new AudioFormat(44100, 16 , 2, true, true);
	    	
	    	DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
	    	
	    	if(!AudioSystem.isLineSupported(info)) {
	    		
				throw new LineUnavailableException();
				
	    	}
	    	
	    	targetDataLine = (TargetDataLine) AudioSystem.getLine(info);
	    	
	    	targetDataLine.open();
	    	
	    	targetDataLine.start();
	    	
	    	audioStream = new AudioInputStream(targetDataLine);

			waveFile = new File("./FactoryScenarios/AudioFiles/" + fileName + ".wav");
			
			Thread record = new Thread( new Runnable(){
				public void run() {
					try {
						AudioSystem.write(audioStream, AudioFileFormat.Type.WAVE, waveFile);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			record.start();
			
			
			
			
	    	
    	} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
    	
    }
    
    private void stopSoundDataLineAndSave() {
    	
    	try {
    		
    		
			audioStream.close();

	    	targetDataLine.stop();
	    	
	    	targetDataLine.close();
	    	
	    	voiceList.add(waveFile);
	    	listUpdate();
	    	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
    	
    }
    
    private void listUpdate() {
    	voiceNameListObs.clear();
    	
    	for(File file : voiceList) {
    		voiceNameListObs.add(file.getName());
    	}
    	
    	voiceNameList.setItems(voiceNameListObs);
    }
    
    @FXML
    public void deleteSelected() {
    	if(isSelected()) {
    		Files.delete(voiceList.get(getSelectedIndex()));
    		voiceList.remove(getSelectedIndex());
    		listUpdate();
    	}
    }
    
    @FXML
    public void playSelected() {
    	if(isSelected()) {
    		
    		System.out.println("yep!" + getSelectedFile().getName());
    		
    		try {
	    		Clip clip = AudioSystem.getClip();
				clip.open(AudioSystem.getAudioInputStream(getSelectedFile()));
				clip.start();
    		}catch(Exception e) {
    			e.printStackTrace();
    		}

    		
    	}
    }
    
    @FXML
    void keyPressed(KeyEvent event) {
    	if(event.getCode().equals(Language.openKey)) {
    		if(event.getSource().equals(recordVoiceButton)) {
    			recordVoiceButtonClicked();
    		}else if(event.getSource().equals(deleteButton)) {
    			deleteSelected();
    		}else if(event.getSource().equals(playButton)) {
				playSelected();
    		}else if(event.getSource().equals(loadSoundButton)) {
    			loadSoundFile();
    		}else if(event.getSource().equals(exitButton)) {
    			exit();
    		}
    	}
    }
    
    
}