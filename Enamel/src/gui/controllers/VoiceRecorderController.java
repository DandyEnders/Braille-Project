package gui.controllers;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

import org.assertj.core.util.Files;

import gui.layouts.TextAnswerBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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
    			if(!soundFile.isDirectory()) {
    				if(soundFile.getName().substring(soundFile.getName().length()-4, soundFile.getName().length()).equals(".wav")) {
    					voiceList.add(soundFile);
    				}
    			}
    		}
    	}
    	
    	listUpdate();
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
    		
    		playButton.setDisable(true);
    		exitButton.setDisable(true);
    		voiceNameList.setDisable(true);
    		deleteButton.setDisable(true);
    		
    	// Toggle was disabled.
    	}else {
    		
    		stopSoundDataLineAndSave();
    		
    		playButton.setDisable(false);
    		exitButton.setDisable(false);
    		voiceNameList.setDisable(false);
    		deleteButton.setDisable(false);

    	}
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
    
    
    
    
}