package startProgram;

import gui.layouts.MainMenu;
import javafx.application.Application;

/**
 * This class starts the program execution by creating view, model, and a
 * controller for mainMenu.
 * 
 * @author Jinho Hwang
 *
 */

public class AuthoringApp {

	public static void main(String[] args) {

		new Thread() {
			@Override
			public void run() {
				Application.launch(MainMenu.class, args);
			}
		}.start();

	}
}