package utility;

import java.util.ArrayList;
import java.util.List;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ViewUtil {
	
	
	public static List<Node> getAllNodes(Parent root) {
	    List<Node> nodes = new ArrayList<Node>();
	    addAllDescendents(root, nodes);
	    return nodes;
	}

	private static void addAllDescendents(Parent parent, List<Node> nodes) {
	    for (Node node : parent.getChildrenUnmodifiable()) {
	        nodes.add(node);
	        if (node instanceof Parent)
	            addAllDescendents((Parent)node, nodes);
	    }
	}
	
	
	public static void setLogger(Node node) {
		keyLogger(node);
		mouseLogger(node);
	}
	
	
	private static void keyLogger(Node node) {
		node.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>(){

			@Override
			public void handle(KeyEvent event) {
				try {
					
					String tostr = "";
					tostr = getNodeDescription(event);
					
					LoggerUtil.log("Key press \t" + "\"" + event.getText() + "\" at " + tostr, 
							"Key pressed at : " + tostr + " \"" + event.getText() + "\"");
					
					System.out.println("Key Logged");
					
				}catch (SecurityException e) {
					e.printStackTrace();
				}
				
			}
			
			
			
		});
	}
	
	private static void mouseLogger(Node node) {
		
		node.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				try {
					String tostr = "";
					tostr = getNodeDescription(event);
					
					
					LoggerUtil.log("Mouse press \t" + tostr, "Mouse pressed at : " + tostr);
					
					System.out.println("Mouse Logged");
					
				}catch (SecurityException e) {
					e.printStackTrace();
				}
				
			}

			
		});
		
	}
	
	private static String getNodeDescription(Event event) {
		String tostr;
		Node node = (Node)event.getSource();
		
		tostr = node.getId();
		
		if(tostr == null || tostr.equals("null") ) {
			tostr = event.getSource().toString();
		}

		return tostr + ", occured at " + ((Stage)node.getScene().getWindow()).getTitle().toString();
	}
}
