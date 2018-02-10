package mainMenuSwing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import scenarioEditorMenuSwing.EditorMenuView;

public class MainMenuController implements ActionListener {
	
	private MainMenuModel model;
	private MainMenuView view;
	EditorMenuView editorMenu = new EditorMenuView();
	
	public MainMenuController(MainMenuModel m, MainMenuView v){
		this.model = m;
		this.view = v;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		switch (e.getActionCommand().toString()) {
			case "Scenario Editor":
				System.out.println("You pressed a Scenario Editor button!");
				editorMenu.getEditorWindow().setVisible(true);
				break;
			case "Exit":
				System.out.println("Exiting program...");
				System.exit(0);
				break;
			default:
				System.out.println(e);
				break;
		}
	
		
	}
	
	
	
}
