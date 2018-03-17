package gui.layouts;

import gui.controllers.Controller;
import gui.controllers.Returnable;
import javafx.stage.Stage;

public abstract class ReturnableView<T extends Controller & Returnable<A>,A> extends View<T> {

	public ReturnableView(){
		super();
	}
	
	public ReturnableView(Stage window) {
		super(window);
	}
	
	public A getReturn() {
		if(isDisplayed)
			return control.getReturn();
		else {
			if(window != null) {
				this.display(this.window);
				return control.getReturn();
			}else {
				throw new IllegalStateException("The view has no window!"); 
			}
		}
	}
}
