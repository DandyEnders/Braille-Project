package gui.layouts;
import gui.controllers.ScenarioEditorController;
import javafx.stage.Stage;
import utility.Language;
/**
 * Scenario Editor GUI class.
 * This class is responsible for drawing the buttons, panes, scenes, etc.
 * FXML file is used to format the layout.
 * 
 * @author Jinho Hwang
 *
 */

public class ScenarioEditor extends View<ScenarioEditorController> {
	
	//When ScenarioEditor is called, it displays
	public ScenarioEditor(){
		super();
	}

	@Override
	protected void initialize() {
		
		// Window should not be resizeable ( else destroys our layout )
		window.setResizable(false);
		try {
			control.loadFileOn(Language.scenarioPath);
		}
		catch(Exception e) {
			
		}
		// If close button (red X button) is pressed, hide the window instead of destroy
		window.setOnCloseRequest(e -> hide());
		window.show();
	}
	
	
	
}
