package game;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;

public class GameGUI {
	
	//checks when game is over
	private boolean gameOver = false;
	
	//keeps track of the game
	private Game game;
	private ArtificialIntelligence ai;
	private InstructionsGUI ig;
	
	//GUI setup
	private JFrame frame = new JFrame("THIRTY ONE");
	
	private JPanel mainpane = new JPanel();
	
	//Left Pane components
	public JButton[][] buttons = new JButton[4][6];
	
	//right pane components
	public JLabel total = new JLabel("TOTAL : 0");
	public JLabel p1Choice = new JLabel("Last Card Chosen by PLAYER: ");
	public JLabel p2Choice = new JLabel("Last Card Chosen by COMP: ");
	public JLabel lastcard1 = new JLabel("");
	public JLabel lastcard2 = new JLabel("");
	
	//buttons
	public JButton instructions = new JButton("Instructions");
	public JButton restart = new JButton("RESTART");
	public JButton instruction = new JButton("INSTRUCTIONS");
	
	/**
	 * initializes all the cards on the left pane
	 * and sets the last cards played by the player 
	 * and the computer to their initial values. 
	 */
	public void initializeButtons(){
		for (int i = 0; i < 4; i++){
			for(int j = 0; j < 6; j++){
				String s = "Cards"+File.separator + i +","+ j + ".jpg";
				ImageIcon icon = new ImageIcon(s);
				Image img = icon.getImage();
				Image newImg = img.getScaledInstance(130, 160,  java.awt.Image.SCALE_SMOOTH);
				buttons[i][j] = new JButton(new ImageIcon(newImg));
			}
		}
		String s = "Cards"+File.separator+"initial.jpg";
		ImageIcon icon = new ImageIcon(s);
		Image img = icon.getImage();
		Image newImg = img.getScaledInstance(130, 160,  java.awt.Image.SCALE_SMOOTH);
		lastcard1 = new JLabel(new ImageIcon(newImg));
		lastcard2 = new JLabel(new ImageIcon(newImg));
	}
	
	public GameGUI(Game g, ArtificialIntelligence a){
		setGame(g);
		setAi(a);
		ig = new InstructionsGUI();
		initializeButtons();
		setupHandlers();
		setup();
		
		frame.setVisible(true);
		frame.pack();
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Get the size of the screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		 
		// Determine the new location of the window
		int x = (dim.width-frame.getSize().width)/2;
		 
		// Move the window
		frame.setLocation(x, 0);
	}
	
	public void setupHandlers(){
		//ExceptionHandlers x = new ExceptionHandlers();
		restartButtonHandler rbh = new restartButtonHandler();
		restart.addActionListener(rbh);
		InstructionButtonHandler ibh = new InstructionButtonHandler();
		instruction.addActionListener(ibh);
		CardButtonHandler cbh = new CardButtonHandler();
		for (int i = 0; i < 4; i++){
			for(int j = 0; j < 6; j++){
					buttons[i][j].addActionListener(cbh);
			}
		}
		
	}
	
	public void setup(){
		JPanel leftPane = new JPanel();
		JPanel rightPane = new JPanel();
		
		Container container = frame.getContentPane();
		container.add(mainpane);
		mainpane.setLayout(new GridBagLayout());
		//LEFT PANE
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 6;
		mainpane.add(leftPane,c);
		leftPane.setLayout(new GridLayout(4,6));
		for (int i = 0; i < 4; i++){
			for(int j = 0; j < 6; j++){
				leftPane.add(buttons[i][j]);
			}
		}
		//RIGHT PANE
		c.fill = GridBagConstraints.VERTICAL;
		c.gridx = 7;
		c.gridwidth = 1;
		mainpane.add(rightPane,c);
		rightPane.setLayout(new GridBagLayout());
		//CONTENTS OF RIGHT PANE
		c.gridx = 0;
		c.gridy = 0;
		rightPane.add(instruction,c);

		c.gridy = 5;
		c.gridheight = 5;
		c.ipady = 100;
		rightPane.add(total,c);
		
		c.gridheight = 1;
		c.ipady = 1;
		c.gridy = 11;
		rightPane.add(p1Choice,c);
		
		c.gridy = 13;
		rightPane.add(lastcard1,c);
		
		c.insets = new Insets(30,0,0,0);
		c.gridy = 16;
		rightPane.add(p2Choice,c);
		
		c.insets = new Insets(0,0,0,0);
		c.gridy = 21;
		rightPane.add(lastcard2,c);
		
		c.insets = new Insets(60,0,0,0);
		c.gridy = 26;
		c.anchor = GridBagConstraints.PAGE_END;
		rightPane.add(restart,c);
		
	}
	
	public void setLast(int x, int y, int p){
		String s = "Cards" + File.separator + x +","+ y + ".jpg";
		ImageIcon icon = new ImageIcon(s);
		Image img = icon.getImage();
		Image newImg = img.getScaledInstance(130, 160,  java.awt.Image.SCALE_SMOOTH);
		String s2 = "Cards"+File.separator+"initial.jpg";
		ImageIcon icon2 = new ImageIcon(s2);
		Image img2 = icon2.getImage();
		Image newImg2 = img2.getScaledInstance(130, 160,  java.awt.Image.SCALE_SMOOTH);
		buttons[x][y] = new JButton(new ImageIcon(newImg2));
		if (p == 0){
			lastcard1 = new JLabel(new ImageIcon(newImg));
		}
		else{
			lastcard2 = new JLabel(new ImageIcon(newImg));
		}
	}
	
	public void setGame(Game game) {
		this.game = game;
	}

	public Game getGame() {
		return game;
	}

	public void setAi(ArtificialIntelligence ai) {
		this.ai = ai;
	}

	public ArtificialIntelligence getAi() {
		return ai;
	}
	
	public class restartButtonHandler implements ActionListener{
		public void actionPerformed(ActionEvent e){
			game = new Game();
			gameOver = false;
			ai = new ArtificialIntelligence();
			total = new JLabel("TOTAL : 0");
			Container container = frame.getContentPane();
			container.remove(mainpane);
			mainpane = new JPanel();
			initializeButtons();
			instructions = new JButton("Instructions");
			restart = new JButton("RESTART");
			setupHandlers();
			setup();
			container.validate();
		}
	}
	
	public class InstructionButtonHandler implements ActionListener{
		public void actionPerformed(ActionEvent e){
			ig.show();
		}
	}
	
	public class CardButtonHandler implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if (!gameOver){
			JButton source = (JButton) e.getSource();
			int x = -1;
			int y = -1;
			for (int i = 0; i < 4; i++){
				for(int j = 0; j < 6; j++){
					if (source == buttons[i][j]){
						x = i;
						y = j;
					}
				}
			}
			if (x == -1){
				System.err.println("Impossible source!");
			}
			else{
				//update the card
				setLast(x,y,0);
				
				//change the score
				game.updateScore(x,y);
				total = new JLabel("TOTAL : " + game.getScore());
				
				//check if the game is over
				int outcome = game.checkStatus();
				if (outcome == 0){
					//AI pick card
					Tuple tuple = game.aiChoice(ai);
					setLast(tuple.first,tuple.second,1);
					
					//change the score
					game.updateScore(tuple.first,tuple.second);
					total = new JLabel("TOTAL : " + game.getScore());
					
					//check game again
					if (game.checkStatus() == 0){
						//revamp the gui
						Container container = frame.getContentPane();
						container.remove(mainpane);
						mainpane = new JPanel();
						setup();
						container.validate();
					}
					else{
						if (game.checkStatus() == 1){
							total = new JLabel("TOTAL : " + game.getScore()+", YOU WIN!");
						}
						else{
							total = new JLabel("TOTAL : " + game.getScore()+", YOU LOSE!");
						}
						gameOver = true;
						Container container = frame.getContentPane();
						container.remove(mainpane);
						mainpane = new JPanel();
						setup();
						container.validate();
					}
				}				
				else{
					if (game.checkStatus() == 1){
						total = new JLabel("TOTAL : " + game.getScore()+", YOU WIN!");
					}
					else{
						total = new JLabel("TOTAL : " + game.getScore()+", YOU LOSE!");
					}
					gameOver = true;
					//revamp the gui
					Container container = frame.getContentPane();
					container.remove(mainpane);
					mainpane = new JPanel();
					setup();
					container.validate();
				}
			}
			}
		}
	}
	
}
