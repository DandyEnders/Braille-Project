package scenarioEditorMenu;

import java.io.File;
import java.util.LinkedList;

public class EditorMenuModel {
	private LinkedList<File> listOfScenarios;
	
	EditorMenuModel(){
		listOfScenarios = new LinkedList<File>();
	}
	
	public void addFile(File file){
		listOfScenarios.add(file);
	}
	
	public LinkedList<File> getFile(){
		return this.listOfScenarios;
	}

}
