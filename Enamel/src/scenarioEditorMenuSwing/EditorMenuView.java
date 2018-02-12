package scenarioEditorMenuSwing;

/**
 * Swing version of EditorMenuView.
 * Deprecated.
 * @author Jinho Hwang
 *
 */

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Window.Type;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.accessibility.*;

public class EditorMenuView {

	private JFrame frmScenarioEditor;
	private EditorMenuModel model;
	private EditorMenuController controller;
	private LinkedList<JButton> buttonList = new LinkedList<JButton>();
	protected DefaultListModel listModel;
	protected JList ScenarioList;
	/**
	 * Create the application.
	 */
	public EditorMenuView() {
		model = new EditorMenuModel();
		controller = new EditorMenuController(model,this);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frmScenarioEditor = new JFrame();
		frmScenarioEditor.setTitle("Scenario Editor");
		frmScenarioEditor.setBounds(100, 100, 451, 550);
		frmScenarioEditor.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frmScenarioEditor.setResizable(false);
		frmScenarioEditor.getContentPane().setLayout(null);
		
		JLabel lblScenarioLists = new JLabel("Scenario Lists");
		lblScenarioLists.setToolTipText("Scenario List label");
		lblScenarioLists.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblScenarioLists.setHorizontalAlignment(SwingConstants.CENTER);
		lblScenarioLists.setBounds(10, 7, 179, 42);
		frmScenarioEditor.getContentPane().add(lblScenarioLists);
		
		JPanel buttonListPanel = new JPanel();
		buttonListPanel.setBounds(204, 7, 235, 494);
		frmScenarioEditor.getContentPane().add(buttonListPanel);
		buttonListPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnCreateScenario = new JButton("Create a new scenario");
		btnCreateScenario.setToolTipText("Create a new scenario button");
		btnCreateScenario.setFont(new Font("Tahoma", Font.PLAIN, 18));
		buttonListPanel.add(btnCreateScenario);
		
		JButton btnEditScenario = new JButton("Edit selected scenario");
		btnEditScenario.setToolTipText("Edit selected scenario button");
		btnEditScenario.setFont(new Font("Tahoma", Font.PLAIN, 18));
		buttonListPanel.add(btnEditScenario);
		
		JButton btnSaveScenario = new JButton("Save selected scenario");
		btnSaveScenario.setToolTipText("Save selected scenario button");
		btnSaveScenario.setFont(new Font("Tahoma", Font.PLAIN, 18));
		buttonListPanel.add(btnSaveScenario);
		
		JButton btnLoadScenario = new JButton("Load scenario");
		btnLoadScenario.setToolTipText("Load scenario button");
		btnLoadScenario.setFont(new Font("Tahoma", Font.PLAIN, 18));
		buttonListPanel.add(btnLoadScenario);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setToolTipText("Exit scenari editor button");
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JButton btnRunScenario = new JButton("Run selected scenario");
		btnRunScenario.setToolTipText("Run selected scenario button");
		btnRunScenario.setFont(new Font("Tahoma", Font.PLAIN, 18));
		buttonListPanel.add(btnRunScenario);
		buttonListPanel.add(btnExit );
		
		ScenarioList = new JList();
		listModel = new DefaultListModel();
		ScenarioList.setModel(listModel);
		
		ScenarioList.setBounds(12, 48, 177, 453);
		frmScenarioEditor.getContentPane().add(ScenarioList);
		
		// Puts all the buttons in a list, and add listener to all for
		// controller to control it.
		for(int i = 0 ; i < buttonListPanel.getComponentCount() ; i++) {
			buttonList.add((JButton)buttonListPanel.getComponent(i));
			buttonList.get(i).addActionListener(controller);
		}
		
		
		
	}
	
	public void update(){
		
	}
	
	public JFrame getEditorWindow() {
		return this.frmScenarioEditor;
	}
	
	protected LinkedList<JButton> getButtonList(){
		return this.buttonList;
	}
}
