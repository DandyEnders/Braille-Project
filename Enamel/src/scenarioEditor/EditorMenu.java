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
import java.awt.Font;

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
		
		JTextArea scenarioLists = new JTextArea();
		scenarioLists.setToolTipText("This is a list of scenarios");
		scenarioLists.setBounds(12, 51, 177, 450);
		frmScenarioEditor.getContentPane().add(scenarioLists);
		
		JPanel buttonListPanel = new JPanel();
		buttonListPanel.setBounds(204, 7, 235, 494);
		frmScenarioEditor.getContentPane().add(buttonListPanel);
		buttonListPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnCreateScenario = new JButton("Create scenario");
		btnCreateScenario.setFont(new Font("Tahoma", Font.PLAIN, 18));
		buttonListPanel.add(btnCreateScenario);
		
		JButton btnEditScenario = new JButton("Edit scenario");
		btnEditScenario.setFont(new Font("Tahoma", Font.PLAIN, 18));
		buttonListPanel.add(btnEditScenario);
		
		JButton btnImportScenario = new JButton("Import scenario");
		btnImportScenario.setFont(new Font("Tahoma", Font.PLAIN, 18));
		buttonListPanel.add(btnImportScenario);
		
		JButton btnExportScenario = new JButton("Export scenario");
		btnExportScenario.setFont(new Font("Tahoma", Font.PLAIN, 18));
		buttonListPanel.add(btnExportScenario);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
			}
		});
		
		JButton btnSimulateScenario = new JButton("Simulate scenario");
		btnSimulateScenario.setFont(new Font("Tahoma", Font.PLAIN, 18));
		buttonListPanel.add(btnSimulateScenario);
		buttonListPanel.add(btnExit );
	}
}
