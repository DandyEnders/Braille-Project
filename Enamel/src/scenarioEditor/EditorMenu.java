package scenarioEditor;

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
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditorMenu {

	private JFrame frmScenarioEditor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditorMenu window = new EditorMenu();
					window.frmScenarioEditor.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EditorMenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmScenarioEditor = new JFrame();
		frmScenarioEditor.setTitle("Scenario Editor");
		frmScenarioEditor.setBounds(100, 100, 451, 550);
		frmScenarioEditor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmScenarioEditor.getContentPane().setLayout(null);
		
		JTextArea txtrThisIsA = new JTextArea();
		txtrThisIsA.setToolTipText("This is a list of scenarios");
		txtrThisIsA.setBounds(12, 12, 177, 449);
		frmScenarioEditor.getContentPane().add(txtrThisIsA);
		
		JPanel panel = new JPanel();
		panel.setBounds(204, 7, 235, 494);
		frmScenarioEditor.getContentPane().add(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnCreateScenario = new JButton("Create scenario");
		panel.add(btnCreateScenario);
		
		JButton btnEditScenario = new JButton("Edit scenario");
		panel.add(btnEditScenario);
		
		JButton btnImportScenario = new JButton("Import scenario");
		panel.add(btnImportScenario);
		
		JButton btnExportScenario = new JButton("Export scenario");
		panel.add(btnExportScenario);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
			}
		});
		
		JButton btnSimulateScenario = new JButton("Simulate scenario");
		panel.add(btnSimulateScenario);
		panel.add(btnExit );
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(12, 462, 177, 39);
		frmScenarioEditor.getContentPane().add(panel_1);
		panel_1.setLayout(new GridLayout(1, 0, 0, 0));
		
		JButton btnOpen = new JButton("Open");
		btnOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel_1.add(btnOpen);
	}
}
