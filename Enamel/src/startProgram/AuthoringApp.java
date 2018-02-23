package startProgram;

import java.io.File;
import java.util.ArrayList;

import gui.layouts.MainMenu;
import javafx.application.Application;
import utility.Language;

/**
 * This class starts the program execution by creating view, model, and a
 * controller for mainMenu.
 * 
 * @author Jinho Hwang
 *
 */

public class AuthoringApp {

	public static void main(String[] args) {

		createDefaultFolders();
		
		new Thread() {
			@Override
			public void run() {
				Application.launch(MainMenu.class, args);
			}
		}.start();

		
	}
	
	private static void createDefaultFolders() {
		ArrayList<File> fileList = new ArrayList<File>();
		
		fileList.add(new File(Language.scenarioPath));
		fileList.add(new File(Language.audioPath));
		fileList.add(new File(Language.errorPath));
		
		for(File file : fileList) {
			if(!file.exists()) {
				file.mkdirs();
			}else {
				if(!file.isDirectory()) {
					file.mkdirs();
				}
			}
		}
		
		
	}
}