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

import gui.layouts.ErrorListReportPopUpBox;
import gui.layouts.TextAnswerBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import utility.ErrorUtil;
import utility.Language;
import utility.ViewUtil;

public class VoiceRecorderController extends Controller {

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
	
	private boolean isRecording = false;

	public VoiceRecorderController() {
		voiceNameListObs = FXCollections.observableArrayList();
		voiceList = FXCollections.observableArrayList();

	}

	public void loadFileOn(String dir) {
		File audioFileFolder = new File(dir);

		if (audioFileFolder.isDirectory()) {
			for (File soundFile : audioFileFolder.listFiles()) {

				if (soundFile.length() < Language.audioFileFormat.length()) {
					continue;
				}

				if (!soundFile.isDirectory()) {
					if (soundFile.getName().substring(soundFile.getName().length() - Language.audioFileFormat.length(), soundFile.getName().length())
							.equals(Language.audioFileFormat)) {
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

			if (inputFiles != null) {

				for (File file : inputFiles) {

					if (!file.isDirectory()) {
						if (file.length() >= Language.audioFileFormat.length()) {
							if (!voiceNameListObs.contains(file.getName())) {
								if (file.getName().substring(file.getName().length() - Language.audioFileFormat.length(), file.getName().length())
										.equals(Language.audioFileFormat)) {
									voiceList.add(file);
									continue;
								}
							}
						}
					}

					failInput.add(file);
				}

				if (!failInput.isEmpty()) {
					new ErrorListReportPopUpBox("Failed to import",
							"Following list of files were failed to be imported.", failInput);
				}

				listUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

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
		if (recordVoiceButton.isSelected()) {
			
			if(isRecordingPossible()) {
				Stage window = new Stage();

				TextAnswerBox box = new TextAnswerBox("Name the audio",
						"Type the name of the voice recording.");
				box.display(window);
				String fileName = (String) box.getReturn();

				
				startSoundDataLine(fileName);
				disableAllOthers(true);
				isRecording = true;
		
				// Toggle was disabled.
			}else {
				recordVoiceButton.setSelected(false);
			}

			
		} else {
			if(isRecording) {
				stopSoundDataLineAndSave();
	
				disableAllOthers(false);
			}
		}
	}

	private void disableAllOthers(boolean isDisable) {

		List<Node> allNodes = ViewUtil.getAllNodes(root);
		
		for(Node node : allNodes) {
			node.setDisable(true);
		}
		
		recordVoiceButton.setDisable(false);

	}
	
	
	
	private boolean isRecordingPossible() {
		try {
			AudioFormat format = new AudioFormat(44100, 16, 2, true, true);

			DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);

			if (!AudioSystem.isLineSupported(info)) {
				throw new LineUnavailableException();
			}
			return true;
			
		} catch (LineUnavailableException e) {
			ErrorUtil.alertMessageShowException("Recorder Unavailable.",
					"Voice Record cannot be initiated because of a missing recording device.\n"
					+ "Please make sure you have recording devices plugged in the computer and ready "
					+ "to record.",
					new LineUnavailableException());
			return false;
		}
	}
	
	private void startSoundDataLine(String fileName){
		
		try {
			AudioFormat format = new AudioFormat(44100, 16, 2, true, true);
	
			DataLine.Info info = new DataLine.Info(TargetDataLine.class, format)
					;
			targetDataLine = (TargetDataLine) AudioSystem.getLine(info);
	
			targetDataLine.open();
	
			targetDataLine.start();
	
			audioStream = new AudioInputStream(targetDataLine);
	
			waveFile = new File(Language.audioPath + fileName + ".wav");
	
			Thread record = new Thread(new Runnable() {
				public void run() {
					try {
						AudioSystem.write(audioStream, AudioFileFormat.Type.WAVE, waveFile);
					} catch (IOException e) {
						ErrorUtil.alertMessageShowException("Error occured while recording!",
								"Error occured! please report the detail to tech staff.", e);
					}
				}
			});
			record.start();
		}catch(Exception e) {
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
			ErrorUtil.alertMessageShowException("Error occured while saving recording!",
					"Error occured! please report the detail to tech staff.", e);
		}

	}

	private void listUpdate() {
		voiceNameListObs.clear();

		for (File file : voiceList) {
			voiceNameListObs.add(file.getName());
		}

		voiceNameList.setItems(voiceNameListObs);
	}

	@FXML
	public void deleteSelected() {
		if (isSelected()) {
			voiceList.get(getSelectedIndex()).delete();
			voiceList.remove(getSelectedIndex());
			listUpdate();
		}else {
			ErrorUtil.alertMessageSimple("Error!", "Please select the audio to delete before pressing delete audio.");
		}
	}

	@FXML
	public void playSelected() {
		if (isSelected()) {

			try {
				Clip clip = AudioSystem.getClip();
				clip.open(AudioSystem.getAudioInputStream(getSelectedFile()));
				clip.start();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}else {
			ErrorUtil.alertMessageSimple("Error!", "Please select the audio to play before pressing play audio.");
		}
	}

	@FXML
	protected void keyPressed(KeyEvent event) {
		if (event.getCode().equals(Language.openKey)) {
			if (event.getSource().equals(recordVoiceButton)) {
				recordVoiceButtonClicked();
			} else if (event.getSource().equals(deleteButton)) {
				deleteSelected();
			} else if (event.getSource().equals(playButton)) {
				playSelected();
			} else if (event.getSource().equals(loadSoundButton)) {
				loadSoundFile();
			} else if (event.getSource().equals(exitButton)) {
				close();
			}
		}
	}

}