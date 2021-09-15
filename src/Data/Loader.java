package Data;

import java.awt.BorderLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListModel;

import GUI.MyFrame;

public class Loader extends JFrame{
	private JList<String> stringList;
	private DefaultListModel listModel;
	private JScrollPane scrollPanel;
	private JButton loadButton;	
	private MyFrame frame;
	private ArrayList<Cell> coloredCells;
	
	public JButton getLoadButton() {
		return loadButton;
	}
	
	public JList<String> getStringList() {
		return stringList;
	}
	
	public void reload() {
		String saveLocationString = System.getProperty("user.dir") + "\\saves";
		File saveLocation = new File(saveLocationString);
		listModel.clear();
		String[] fileList = saveLocation.list();
		for(int i=0;i<fileList.length;i++) {
			listModel.addElement(fileList[i]);			
		}
	}
	
	public Loader(MyFrame frame) {
		this.frame = frame;
		listModel = new DefaultListModel();
		stringList = new JList(listModel);
		loadButton = new JButton("Load");
		
		String saveLocationString = System.getProperty("user.dir") + "\\saves";
		File saveLocation = new File(saveLocationString);

		if(!saveLocation.isDirectory()) {	
			saveLocation.mkdir();
		}else {
			String[] fileList = saveLocation.list();
			for(int i=0;i<fileList.length;i++) {
				listModel.addElement(fileList[i]);
			}
		}
		
		scrollPanel = new JScrollPane(stringList);
		scrollPanel.setVisible(true);
		
		stringList.setVisibleRowCount(5);
		this.add(scrollPanel,BorderLayout.NORTH);
		this.add(loadButton,BorderLayout.SOUTH);
		this.pack();
		this.setVisible(false);
		
		loadButton.setActionCommand("load");
		loadButton.addActionListener(new ButtonPressed());
	}
	
	public class ButtonPressed implements ActionListener {	
		public void actionPerformed(ActionEvent actionEvent) {
			if(actionEvent.getActionCommand().equals("load")) {
				frame.setVisible(true);
				setVisible(false);
				try {
					String name = stringList.getSelectedValue();
					String directory = System.getProperty("user.dir") + "\\saves";
					File src = new File(directory, name);
					FileInputStream file = new FileInputStream(src); 
					ObjectInputStream inputStream = new ObjectInputStream(file);
					frame.getCanvas().setColoredCells((ArrayList)inputStream.readObject());
					frame.getSimulation().setCanvas(frame.getCanvas());
					inputStream.close();
				}catch(IOException e) {
					throw new RuntimeException(e);
				}catch(ClassNotFoundException ex) {
					//todo
				}
				frame.getCanvas().paint(frame.getCanvas().getGraphics());
			}
		}
	}
	
	public ArrayList getLoadedCells() {
		return coloredCells;
	}
	
}