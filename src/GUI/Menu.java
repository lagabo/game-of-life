package GUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Data.Loader;

public class Menu extends JFrame{
	private JButton gameButton, loadButton, exitButton;
	private JPanel panel;
	private MyFrame frame;
	private Loader loader;
	
	public Loader getLoader() {
		return loader;
	}
	
	public JButton getGameButton() {
		return gameButton;
	}
	
	public JButton getLoadButton() {
		return loadButton;
	}
	
	public JButton getExitButton() {
		return exitButton;
	}
	
	public Menu(MyFrame frame, Loader loader) {
		gameButton = new JButton("Start");
		loadButton = new JButton("Load");
		exitButton = new JButton("Exit");
		gameButton.setActionCommand("Start");
		gameButton.addActionListener(new myActionListener());
		loadButton.setActionCommand("Load");
		loadButton.addActionListener(new myActionListener());
		exitButton.setActionCommand("Exit");
		exitButton.addActionListener(new myActionListener());
		
		this.frame = frame;
		this.loader = loader;
		frame.setMenu(this);
		
		add(gameButton,BorderLayout.NORTH);
		add(loadButton,BorderLayout.CENTER);
		add(exitButton,BorderLayout.SOUTH);
		pack();
		
		setVisible(true);
	}
	
	class myActionListener implements ActionListener{
		public void actionPerformed(ActionEvent actionEvent) {
			if(actionEvent.getActionCommand().equals("Load")) {
				loader.setVisible(true);
				setVisible(false);
			}
			else if(actionEvent.getActionCommand().equals("Start")) {
				frame.setVisible(true);
				setVisible(false);
			}
			else if(actionEvent.getActionCommand().equals("Exit")) {
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		}
	}
	
}