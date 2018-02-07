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

public class mainMenuWindow {

	private JFrame frmBrailleProjectProgram;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainMenuWindow window = new mainMenuWindow();
					window.frmBrailleProjectProgram.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public mainMenuWindow() {
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
		GridBagLayout gbl_centrePanel = new GridBagLayout();
		gbl_centrePanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_centrePanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_centrePanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_centrePanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		centrePanel.setLayout(gbl_centrePanel);
		
		JButton btnScenarioEditor = new JButton("Scenario Editor");
		btnScenarioEditor.setFont(new Font("Tahoma", Font.PLAIN, 32));
		btnScenarioEditor.setToolTipText("Scenario Editor button");
		GridBagConstraints gbc_btnScenarioEditor = new GridBagConstraints();
		gbc_btnScenarioEditor.gridwidth = 3;
		gbc_btnScenarioEditor.insets = new Insets(0, 0, 0, 5);
		gbc_btnScenarioEditor.gridx = 4;
		gbc_btnScenarioEditor.gridy = 6;
		centrePanel.add(btnScenarioEditor, gbc_btnScenarioEditor);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 32));
		btnExit.setToolTipText("Exit button");
		GridBagConstraints gbc_btnExit = new GridBagConstraints();
		gbc_btnExit.insets = new Insets(0, 0, 0, 5);
		gbc_btnExit.gridx = 9;
		gbc_btnExit.gridy = 6;
		centrePanel.add(btnExit, gbc_btnExit);
		
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
	}
}
