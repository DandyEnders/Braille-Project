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

public class EditorMenu {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditorMenu window = new EditorMenu();
					window.frame.setVisible(true);
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
		frame = new JFrame();
		frame.setBounds(100, 100, 451, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTextArea txtrThisIsA = new JTextArea();
		txtrThisIsA.setToolTipText("This is a list of scenarios");
		txtrThisIsA.setBounds(12, 12, 180, 489);
		frame.getContentPane().add(txtrThisIsA);
		
		JPanel panel = new JPanel();
		panel.setBounds(204, 7, 235, 494);
		frame.getContentPane().add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnCreateScenario = new JButton("Create scenario");
		panel.add(btnCreateScenario);
		
		JButton btnEditScenario = new JButton("Edit scenario");
		panel.add(btnEditScenario);
		
		JButton btnImportScenario = new JButton("Import scenario");
		panel.add(btnImportScenario);
		
		JButton btnExportScenario = new JButton("Export scenario");
		panel.add(btnExportScenario);
	}
}
