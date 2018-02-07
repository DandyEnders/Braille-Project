package mainMenu;

import java.awt.EventQueue;

import javax.swing.JFrame;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.GridBagLayout;
import java.awt.CardLayout;
import javax.swing.SpringLayout;
import javax.swing.JPanel;
import java.awt.GridLayout;
import net.miginfocom.swing.MigLayout;
import scenarioEditorMenu.EditorMenuController;
import scenarioEditorMenu.EditorMenuModel;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.Box;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;

public class MainMenuView {

	private JFrame frmBrailleProjectProgram;
	private MainMenuModel model;
	private MainMenuController controller;

	/**
	 * Create the application.
	 */
	public MainMenuView() {
		// Assigning model and the controller to MainMenuView
		model = new MainMenuModel();
		controller = new MainMenuController(model, this);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frmBrailleProjectProgram = new JFrame();
		frmBrailleProjectProgram.setTitle("Braille Project Program");
		frmBrailleProjectProgram.setBounds(100, 100, 776, 528);
		frmBrailleProjectProgram.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBrailleProjectProgram.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel centrePanel = new JPanel();
		frmBrailleProjectProgram.getContentPane().add(centrePanel, BorderLayout.CENTER);
		centrePanel.setLayout(null);
		
		JButton btnScenarioEditor = new JButton("Scenario Editor");
		btnScenarioEditor.setBounds(54, 180, 282, 47);
		btnScenarioEditor.setFont(new Font("Tahoma", Font.PLAIN, 32));
		btnScenarioEditor.setToolTipText("Scenario Editor button");
		btnScenarioEditor.addActionListener(controller);
		centrePanel.add(btnScenarioEditor);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(401, 180, 107, 47);
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 32));
		btnExit.setToolTipText("Exit braille program button");
		btnExit.addActionListener(controller);
		centrePanel.add(btnExit);
		
		JPanel leftPanel = new JPanel();
		frmBrailleProjectProgram.getContentPane().add(leftPanel, BorderLayout.WEST);
		
		Component horizontalStrut = Box.createHorizontalStrut(80);
		leftPanel.add(horizontalStrut);
		
		JPanel rightPanel = new JPanel();
		frmBrailleProjectProgram.getContentPane().add(rightPanel, BorderLayout.EAST);
		
		Component horizontalStrut_4 = Box.createHorizontalStrut(80);
		rightPanel.add(horizontalStrut_4);
		
		JPanel bottomPanel = new JPanel();
		frmBrailleProjectProgram.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
		bottomPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		Component verticalStrut_1 = Box.createVerticalStrut(120);
		bottomPanel.add(verticalStrut_1);
		
		JPanel topPanel = new JPanel();
		frmBrailleProjectProgram.getContentPane().add(topPanel, BorderLayout.NORTH);
		
		JLabel lblBrailleProject = new JLabel("Braille Project");
		lblBrailleProject.setToolTipText("Title : Braille Project mainMenu");
		topPanel.add(lblBrailleProject);
		lblBrailleProject.setFont(new Font("Tahoma", Font.PLAIN, 72));
		lblBrailleProject.setHorizontalAlignment(SwingConstants.CENTER);
		
		frmBrailleProjectProgram.setVisible(true);
	}
	
}
