package enamel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.logging.Level;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;
//test2

public class AudioPlayer extends Player {
	
	
	private JFrame frame;
	private GridLayout cellGrid = new GridLayout(4, 2);
	LinkedList<JPanel> panelList = new LinkedList<JPanel>();
	LinkedList<JButton> buttonList = new LinkedList<JButton>();
	JPanel southPanel = new JPanel();
	JPanel centerPanel = new JPanel();
	JPanel westPanel = new JPanel();
	JRadioButton[] pins = new JRadioButton[8];
	int[] pinIndex = {0, 2, 4, 1, 3, 5, 6, 7};
	///private boolean displayed = false;
	
	public AudioPlayer(int cellNum, int buttonNum)
	{
		super(cellNum, buttonNum);

		SwingUtilities.invokeLater(new Runnable() {
			//@Override
			public void run() {
				frame = new JFrame();
				frame.setTitle("Simulator");
				frame.setBounds(100, 100, 627, 459);
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.getContentPane().setLayout(new BorderLayout());

				for (int i = 0; i < brailleCellNumber; i++) {

					JPanel panel = new JPanel(cellGrid);
					for (int j = 0; j < 8; j++) {
						JRadioButton radioButton = new JRadioButton();
						radioButton.setEnabled(false);
						radioButton.setSize(25, 25);
						radioButton.getAccessibleContext().setAccessibleName("Cell " + (j + 1));

						pins[j] = radioButton;

						panel.add(radioButton);
						panel.repaint();
					}
					
					panel.setVisible(true);
					
					panelList.add(panel);
					panel.setSize(50, 50);
					panel.setBorder(BorderFactory.createLineBorder(Color.black));
					centerPanel.add(panel);

					if (i == (brailleCellNumber - 1))
						frame.getContentPane().add(centerPanel, BorderLayout.CENTER);
				}

				for (int i = 0; i < buttonNumber; i++) {
					JButton button = new JButton("" + (i + 1));

					buttonList.add(button);
					southPanel.add(button);
				}
				
				

				frame.getContentPane().add(southPanel, BorderLayout.SOUTH);

				frame.repaint();
				frame.setVisible(true);
			}
		});
	}

	@Override
	public void refresh() {
		for (BrailleCell s : brailleList) {
			for (int i = 0; i < s.getNumberOfPins(); i++) {
				pins[pinIndex[i]].setSelected(s.getPinState(i));
			}
		}
		
	}

	@Override
	public void addSkipButtonListener(int index, String param, ScenarioParser sp) {
		buttonList.get(index).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (sp.userInput) {
					sp.skip(param);
					//logger.log(Level.INFO, "Button {0} was pressed", index+1);
					sp.userInput = false;
				}
			}
		});
		
	}

	@Override
	public void removeButtonListener(int index) {
		if (index >= this.buttonNumber || index < 0) {
            throw new IllegalArgumentException("Invalid index.");
        }
		ActionListener[] aList = getButton(index).getActionListeners();
		if (aList.length > 0) {
			for (int x = 0; x < aList.length; x++) {
				getButton(index).removeActionListener(getButton(index).getActionListeners()[x]);
			}
		}
		
	}

	@Override
	public void addRepeatButtonListener(int index, ScenarioParser sp) {
		getButton(index).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			
				if (sp.userInput) {
					repeat++;
					logger.log(Level.INFO, "Repeat Button was pressed.");
					logger.log(Level.INFO, "Repeat Button was pressed {0} times", repeat);
					sp.repeatText();
				}
			}
		});
		
	}
	
	public JButton getButton(int index) {
		if (index >= this.buttonNumber || index < 0) {
			throw new IllegalArgumentException("Invalid button index.");
		}
		return this.buttonList.get(index);
	}
	
	

}
