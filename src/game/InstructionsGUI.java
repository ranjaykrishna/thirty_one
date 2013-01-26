package game;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InstructionsGUI {
	
	private JFrame frame = new JFrame("THIRTY ONE");
	private JPanel mainpane = new JPanel();
	
	private int width = 400;
	private int height = 200;
	
	//instructions
	private JLabel instruction1 = new JLabel("TO WIN, GET TO 31.");
	private JLabel instruction2 = new JLabel("Choose a card to add to the total.");
	private JLabel instruction3 = new JLabel("After your turn, the computer will play its card");
	private JLabel instruction4 = new JLabel("Go over 31 and you lose.");
	private JLabel instruction5 = new JLabel("Make sure the computer doesnt get to 31 first");
	
	public InstructionsGUI(){
		// Get the size of the screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		 
		// Determine the new location of the window
		int x = (dim.width-width)/2;
		int y = (dim.height-height)/2;
		 
		// Move the window
		frame.setLocation(x, y);
		
		//setting fonts
		Font font = new Font("Helvatica",Font.BOLD,16);
		instruction1.setFont(font);
		instruction2.setFont(font);
		instruction3.setFont(font);
		instruction4.setFont(font);
		instruction5.setFont(font);
		instruction1.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		//setup for GUI
		Container container = frame.getContentPane();
		container.add(mainpane);
		mainpane.setLayout(new GridLayout(5,1));
		mainpane.add(instruction1);
		mainpane.add(instruction2);
		mainpane.add(instruction3);
		mainpane.add(instruction4);
		mainpane.add(instruction5);
		mainpane.setBackground(Color.WHITE);
		
		//basics
		frame.setSize(width, height);
		frame.setResizable(false);
	}
	
	public void show(){
		
		frame.setVisible(true);
	}
}
