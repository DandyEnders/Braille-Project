/**
 * 
 */
package gui.layouts;

import java.io.File;
import java.io.IOException;
import java.util.List;

import gui.controllers.ErrorListReportPopUpBoxController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import utility.Language;

/**
 * A pop up box with a list of errors to show.
 * @author Jinho Hwang
 *
 */
public class ErrorListReportPopUpBox extends View {
	
	// control.
	ErrorListReportPopUpBoxController control;
	
	private String label;
	private List<File> errorList;
		
	public ErrorListReportPopUpBox(String title, String label, List<File> errorList) {
		super();
		this.title = title;
		this.label = label;
		this.errorList = errorList;
	}

	@Override
	protected void initialize() {
		// Window should not be re-sizeable ( else destroys our layout )
		window.setResizable(false);
		
		// Make this box a pop up and stops any input until this pop up is resolved ( closed )
		window.initModality(Modality.APPLICATION_MODAL);
		
		control.setErrorList(errorList);
		control.setLabel(label);
	}
}
