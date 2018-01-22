package enamel;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;
import java.io.*;

// I am in branch lab2 what upppp

// I am in branch lab2efdsafdsaf
// I am in branch lab2 yayasdf



public class JFileDemo extends JFrame implements ActionListener {

	private JFileChooser file;
	private File fileOpen;
	private BufferedReader read;
	
	public static void main(String[] args) {
		JFileDemo gui = new JFileDemo();
		gui.setVisible(true);
	}
	
	JFileDemo () {
		this.setPreferredSize(new Dimension(500,400));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.setPreferredSize(new Dimension(200,100));
		JButton button = new JButton ("Open file...");
		button.addActionListener(this);
		
		panel.add(button);
		this.add(panel, BorderLayout.CENTER);
		
		
		file = new JFileChooser();
		
	}
	
	public void actionPerformed (ActionEvent e) {
		String action = e.getActionCommand();
		
		
		if (action.equals("Open file...")) {
			int open = file.showOpenDialog(null);
			if (open == JFileChooser.APPROVE_OPTION) {
				fileOpen = file.getSelectedFile();
				try {
					String current = "";
					read = new BufferedReader(new FileReader(fileOpen));
					while ((current = read.readLine()) != null) {
						System.out.println(current);
					}
				} catch (IOException e1) {
					
					e1.printStackTrace();
				}
				
				
			}
			
			
			
		}
	}
}
