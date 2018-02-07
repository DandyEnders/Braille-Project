package scenarioEditorMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		case "Create a new scenario": 
		case "Edit selected scenario": 
		case "Save selected scenario":
		case "Load scenario": 
		case "Run selected scenario":
			System.out.println("You pressed a "+ e.getActionCommand().toString() + " button!");
			break;
		case "Exit":
			System.out.println("You pressed a Exit button!");
			view.getEditorWindow().setVisible(false);
			break;
		default:
			System.out.println(e);
			break;
	}
		
	}

}
