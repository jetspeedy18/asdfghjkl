package main;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JLabel;


public class Game {
	
	JFrame window;
	Container con;
	JPanel titleNamePanel, startButtonPanel, optionsButtonPanel, quitButtonPanel, optionsPanel, backButtonPanel, easyButtonPanel, normalButtonPanel, hardButtonPanel;
	JLabel titleNameLabel;
	Font titleFont = new Font("", Font.PLAIN, 28);
	Font Code8x8;
	Font Code8x8small;
	JButton startButton, optionsButton, quitButton, easyButton, normalButton, hardButton, backButton;
	JTextArea textArea;
	
	StartButtonHandler sHandler = new StartButtonHandler();
	OptionsButtonHandler oHandler = new OptionsButtonHandler();
	BackButtonHandler bHandler = new BackButtonHandler();
	QuitButtonHandler qHandler = new QuitButtonHandler();
	EasyButtonHandler eHandler = new EasyButtonHandler();
	NormalButtonHandler nHandler = new NormalButtonHandler();
	HardButtonHandler hHandler = new HardButtonHandler();

	
	public static void main(String[] args) {
		new Game();
	}
	
	public Game() {
		
		try {
			Code8x8 = Font.createFont(Font.TRUETYPE_FONT, new File("Code8x8.ttf")).deriveFont(30f);
			Code8x8small = Font.createFont(Font.TRUETYPE_FONT, new File("Code8x8.ttf")).deriveFont(15f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Code8x8.ttf")));
		}
		catch(IOException | FontFormatException e) {
			
		}
		
		window = new JFrame();
		window.setSize(800,600);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setBackground(Color.black);
		window.setLayout(null);
		
		con = window.getContentPane();
		
		titleNamePanel = new JPanel();
		titleNamePanel.setBounds(100, 100, 600, 150);
		titleNamePanel.setBackground(Color.black);
		titleNameLabel = new JLabel("Archibalds Dungeon");
		titleNameLabel.setForeground(Color.white);
		titleNameLabel.setFont(Code8x8);
		
		startButtonPanel = new JPanel();
		startButtonPanel.setBounds(300, 300, 200, 50);
		startButtonPanel.setBackground(Color.black);
		
		startButton = new JButton("START");
		startButton.setBackground(Color.black);
		startButton.setForeground(Color.white);
		startButton.setFont(Code8x8small);
		startButton.setFocusPainted(false);
		startButton.addActionListener(sHandler);
		
		
		optionsButtonPanel = new JPanel();
		optionsButtonPanel.setBounds(300, 350, 200, 50);
		optionsButtonPanel.setBackground(Color.black);
		
		optionsButton = new JButton("OPTIONS");
		optionsButton.setBackground(Color.black);
		optionsButton.setForeground(Color.white);
		optionsButton.setFont(Code8x8small);
		optionsButton.setFocusPainted(false);
		optionsButton.addActionListener(oHandler);
		
		quitButtonPanel = new JPanel();
		quitButtonPanel.setBounds(300, 400, 200, 50);
		quitButtonPanel.setBackground(Color.black);
		
		quitButton = new JButton("QUIT");
		quitButton.setBackground(Color.black);
		quitButton.setForeground(Color.white);
		quitButton.setFont(Code8x8small);
		quitButton.setFocusPainted(false);
		quitButton.addActionListener(qHandler);
		
		titleNamePanel.add(titleNameLabel);
		startButtonPanel.add(startButton);
		optionsButtonPanel.add(optionsButton);
		quitButtonPanel.add(quitButton);
		
		con.add(titleNamePanel);
		con.add(startButtonPanel);
		con.add(optionsButtonPanel);
		con.add(quitButtonPanel);
		window.setVisible(true);
		
	}
	
	public void optionsScreen() {
		
		startButtonPanel.setVisible(false);
		optionsButtonPanel.setVisible(false);
		quitButtonPanel.setVisible(false);
		
		easyButtonPanel = new JPanel();
		easyButtonPanel.setBounds(300, 300, 200, 50);
		easyButtonPanel.setBackground(Color.black);
		
		easyButton = new JButton("EASY");
		easyButton.setBackground(Color.black);
		easyButton.setForeground(Color.white);
		easyButton.setFont(Code8x8small);
		easyButton.setFocusPainted(false);
		easyButton.addActionListener(eHandler);
		
		normalButtonPanel = new JPanel();
		normalButtonPanel.setBounds(300, 350, 200, 50);
		normalButtonPanel.setBackground(Color.black);
		
		normalButton = new JButton("NORMAL");
		normalButton.setBackground(Color.black);
		normalButton.setForeground(Color.white);
		normalButton.setFont(Code8x8small);
		normalButton.setFocusPainted(false);
		normalButton.addActionListener(nHandler);
		
		hardButtonPanel = new JPanel();
		hardButtonPanel.setBounds(300, 400, 200, 50);
		hardButtonPanel.setBackground(Color.black);
		
		hardButton = new JButton("HARD");
		hardButton.setBackground(Color.black);
		hardButton.setForeground(Color.white);
		hardButton.setFont(Code8x8small);
		hardButton.setFocusPainted(false);
		hardButton.addActionListener(hHandler);
		
		backButtonPanel = new JPanel();
		backButtonPanel.setBounds(300, 450, 200, 50);
		backButtonPanel.setBackground(Color.black);
		
		backButton = new JButton("BACK");
		backButton.setBackground(Color.black);
		backButton.setForeground(Color.white);
		backButton.setFont(Code8x8small);
		backButton.setFocusPainted(false);
		backButton.addActionListener(bHandler);
		
		titleNamePanel.add(titleNameLabel);
		easyButtonPanel.add(easyButton);
		normalButtonPanel.add(normalButton);
		hardButtonPanel.add(hardButton);
		backButtonPanel.add(backButton);
		
		con.add(titleNamePanel);
		con.add(easyButtonPanel);
		con.add(normalButtonPanel);
		con.add(hardButtonPanel);
		con.add(backButtonPanel);
	}
	
	public class StartButtonHandler implements ActionListener {

		public void actionPerformed(ActionEvent event) {
			
			titleNamePanel.setVisible(false);
			startButtonPanel.setVisible(false);
			optionsButtonPanel.setVisible(false);
			quitButtonPanel.setVisible(false);
			//Run Game
		}
		
	}
	
	public class OptionsButtonHandler implements ActionListener {

		public void actionPerformed(ActionEvent event) {
			
			optionsScreen();
		}
		
	}
	
	
	public class BackButtonHandler implements ActionListener {

		public void actionPerformed(ActionEvent event) {
			
			easyButtonPanel.setVisible(false);
			normalButtonPanel.setVisible(false);
			hardButtonPanel.setVisible(false);
			backButtonPanel.setVisible(false);
			
			startButtonPanel.setVisible(true);
			optionsButtonPanel.setVisible(true);
			quitButtonPanel.setVisible(true);
		}
		
	}
	
	public class QuitButtonHandler implements ActionListener {

		public void actionPerformed(ActionEvent event) {
			
			System.exit(0);
		}
		
	}
	public class EasyButtonHandler implements ActionListener {

		public void actionPerformed(ActionEvent event) {
			
			//Increase Player Health
		}
		
	}
	
	public class NormalButtonHandler implements ActionListener {

		public void actionPerformed(ActionEvent event) {
			
			//Normal Player Health
		}
		
	}
	
	public class HardButtonHandler implements ActionListener {

		public void actionPerformed(ActionEvent event) {
			
			//Lower Player Health
		}
		
	}
}
