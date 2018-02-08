package scenarioEditorMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFileChooser;

import enamel.*;
import mainMenu.MainMenuModel;
import mainMenu.MainMenuView;

public class EditorMenuController  implements ActionListener {
	
	private EditorMenuModel model;
	private EditorMenuView view;
	
	public EditorMenuController(EditorMenuModel m, EditorMenuView v){
		this.model = m;
		this.view = v;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand().toString()) {
		case "Load scenario": 
			System.out.println("You pressed a "+ e.getActionCommand().toString() + " button!");
			
			try {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File("./FactoryScenarios").getCanonicalFile());
				if( fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION ){
					System.out.println(fileChooser.getSelectedFile().getAbsolutePath());
					File loadedFile = fileChooser.getSelectedFile();
					this.addFileToGUIList(loadedFile);
				}
				
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			//LoadScenarioMenu loadScenarioMenu = new LoadScenarioMenu(model,this);
			break;
		case "Create a new scenario": 
		case "Edit selected scenario": 
		case "Save selected scenario":
			System.out.println("You pressed a "+ e.getActionCommand().toString() + " button!");
			break;
		case "Run selected scenario":
			System.out.println("You pressed a "+ e.getActionCommand().toString() + " button!");
			if(this.view.ScenarioList.getSelectedIndex() != -1){
				Thread starterCodeThread = new Thread("Starter Code Thread") {
				    public void run(){    
				        ScenarioParser s = new ScenarioParser(true);
				        try {
							s.setScenarioFile(model.getFile().get(view.ScenarioList.getSelectedIndex()).getCanonicalPath());
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				    }
				};
				starterCodeThread.start();
					
				
			}
			break;
		case "Exit":
			System.out.println("You pressed a Exit button!");
			view.getEditorWindow().setVisible(false);
			break;
		default:
			System.out.println(e);
			break;
		} // switch
		
	} // actionperformed
	
	
	private File fileLoad(File dir) throws IOException{
		JFileChooser fileChooser = new JFileChooser();
		
		fileChooser.setCurrentDirectory(dir);
		
		if( fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION ){
			System.out.println(fileChooser.getSelectedFile().getAbsolutePath());
			return fileChooser.getSelectedFile();
		}else{
			return null;
		}
		
	}
	
	private void addFileToGUIList(File file){
		this.model.addFile(file);
		this.view.listModel.addElement(file.getName());
	}

}
