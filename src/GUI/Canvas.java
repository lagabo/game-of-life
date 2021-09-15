package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JPanel;

import Data.Cell;

public class Canvas extends JPanel{
	int width, height;
	static int cellSize = 20;
	static int edgeSize = 25;
	
	private ArrayList<Cell> coloredCells;

	public ArrayList<Cell> getColoredCells() {
		return coloredCells;
	}
	
	public void setColoredCells(ArrayList<Cell> coloredCells) {
		this.coloredCells = coloredCells;
	}
	
	public Canvas(int width, int height) {
		this.width = width;
		this.height = height;
		setVisible(true);
		coloredCells = new ArrayList<Cell>();
	}
	
	public void removeFromList(Cell cell) {
		coloredCells.remove(cell);
	}
	
	public void addList(Cell c) {
		coloredCells.add(c);
	}
	
	public Cell getCell(int i) {
		return coloredCells.get(i);
	}
	
	public boolean contains(Cell cell) {
		return coloredCells.contains(cell);
	}
	
	public void paint(Graphics graphics) {
		super.paint(graphics);
		int numberOfCells = (width-edgeSize*2)/cellSize;
		graphics.setColor(Color.black);
		for(int i=0;i<=numberOfCells;i++) {
			graphics.drawLine(edgeSize+i*cellSize, edgeSize,edgeSize+i*cellSize, height-edgeSize);
		}
		numberOfCells = (height-edgeSize*2)/cellSize;
		for(int i=0;i<=numberOfCells;i++) {
			graphics.drawLine(edgeSize, edgeSize+i*cellSize, width-edgeSize, edgeSize+i*cellSize);
		}
		graphics.setColor(Color.GREEN);
		for(int i=0;i<coloredCells.size();i++) {
			int x,y;
			x = edgeSize+coloredCells.get(i).getw()*cellSize;
			y = edgeSize+coloredCells.get(i).geth()*cellSize;
			graphics.fillRect(x+1,y+1 ,cellSize-1 , cellSize-1);
		}
	}
	
}