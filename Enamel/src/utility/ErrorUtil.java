package utility;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;

public class ErrorUtil {

	private ErrorUtil() {}
	
	public static void alertMessageSimple(String headerText, String contentText) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Error!");
		alert.setHeaderText(headerText);
		alert.setContentText(contentText);
		alert.showAndWait();
	}
	
	public static void alertMessageShowException(String headerText, String contentText, Exception e) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Error!");
		alert.setHeaderText(headerText);
		alert.setContentText(contentText);
		
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		
		TextArea area = new TextArea(sw.toString());
		alert.getDialogPane().setExpandableContent(area);
		alert.showAndWait();
	}
	
	public static void errorLog(Exception exception, String message, String errorLoggerName) {
		ErrorUtil.errorLog(exception.toString(), message, errorLoggerName);
	}
	
	
	/**
	 * Writes an error and store in a text file.
	 * 
	 * @author ScenarioParser writer
	 * @param exception
	 *            the exception message
	 * @param message
	 *            the specific error message
	 * @param errorLoggerName
	 *            the name of error file and logger's name
	 */
	public static void errorLog(String exception, String message, String errorLoggerName) {
		Logger logger = Logger.getLogger(errorLoggerName);
		FileHandler fh;
	
		//System.out.println(message);
	
/*		alertMessageShowException("Error!", "Something went wrong in the program! Please consult \n" + 
				"teacher or administrator for assistance! Also please view the error log in errors folder\n" + 
				"for more details", new IllegalArgumentException(exception));*/
		
		// speak("Error! Something went wrong in the program! Please consult a
		// teacher "
		// + "or administrator for assistance! Also please view the ERROR_LOG
		// file for more details");
		// The try-catch block is to format the Logger class so that the error
		// log file is easier to understand.
		try {
			File f = new File("./errors/" + errorLoggerName + ".txt");
			fh = new FileHandler(f.toString());
	
			logger.addHandler(fh);
			logger.setUseParentHandlers(false);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);
	
			logger.warning(exception);
			logger.info(message);
			fh.close();
	
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// exit();
	}

}
