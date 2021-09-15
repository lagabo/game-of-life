package GUI;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import Game.Simulation;

public class MyFrame extends JFrame{
	private JButton startButton, backButton, saveButton;//,save,load;
	private JPanel panel;
	private JTextField saveInputField;
	private Menu menu;
	
	private int width=1250, height=750;
	private Canvas canvas;
	private Simulation simulation;
	
	public Canvas getCanvas() {
		return canvas;
	}
	
	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
	}
	
	public Simulation getSimulation() {
		return simulation;
	}
	
	public void setSimulation(Simulation simulation) {
		this.simulation = simulation;
	}
	
	public Menu getMenu() {
		return menu;
	}
	
	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	
	public MyFrame() {
		setSize(width+50, height+100);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(true);
		
		canvas = new Canvas(width, height);
		startButton = new JButton("Start");
		backButton = new JButton("Menu");
		saveButton = new JButton("Save");
		panel = new JPanel();
		
		saveInputField = new JTextField("",20);
		saveInputField.setVisible(true);
		
		panel.add(backButton,BorderLayout.WEST);
		panel.add(startButton,BorderLayout.WEST);
		panel.add(saveButton);
		panel.add(saveInputField,BorderLayout.EAST);
		
		this.add(panel,BorderLayout.NORTH);
		this.add(canvas,BorderLayout.CENTER);
		
		simulation = new Simulation(canvas, (width-canvas.edgeSize*2)/canvas.cellSize, (height-canvas.edgeSize*2)/canvas.cellSize);
		
		startButton.setActionCommand("start");
		startButton.addActionListener(new ButtonPressed());
		backButton.setActionCommand("menu");
		backButton.addActionListener(new ButtonPressed());
		saveButton.setActionCommand("save");
		saveButton.addActionListener(new ButtonPressed());
		addMouseListener(new MouseEvent(canvas));
		setVisible(false);
	}
	
	public class ButtonPressed implements ActionListener {
		
		public void actionPerformed(ActionEvent actionEvent) {

			if(actionEvent.getActionCommand().equals("start")) {
				startButton.setText(  startButton.getText().equals("Start") ? "Stop" : "Start" );
				if(!simulation.isAlive()) {
					//todo: create an update function in the Simulation class, and use that here
					simulation = new Simulation(canvas, (width-canvas.edgeSize*2)/canvas.cellSize, (height-canvas.edgeSize*2)/canvas.cellSize);
					simulation.start();
				}
				else {
					simulation.setEnd(true);
				}
			}
			else if(actionEvent.getActionCommand().equals("menu")) {
				menu.setVisible(true);
				setVisible(false);
				simulation.setEnd(true);
				startButton.setText("Start");
			}
			else if(actionEvent.getActionCommand().equals("save")) {
				try {
					String name = saveInputField.getText()+".txt";
					
					String directory = System.getProperty("user.dir") + "\\saves";
					File first = new File(directory);
					if(!first.isDirectory()) {	
						first.mkdir();
					}
					
					directory = System.getProperty("user.dir") + "\\saves";
					File src = new File(directory, name);
					FileOutputStream fileStream = new FileOutputStream(src); 
					ObjectOutputStream output = new ObjectOutputStream(fileStream);
					output.writeObject(canvas.getColoredCells());
					output.close();
					
					menu.getLoader().reload();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public JButton getStartButton() {
		return startButton;
	}
	
	public JButton getBackButton() {
		return backButton;
	}
	
	public JButton getSaveButton() {
		return saveButton;
	}
	
}