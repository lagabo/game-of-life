package Data;

import java.util.ArrayList;

import Data.Cell.State;

public class CellStorage{
	private Cell[][] data;
	private int width, height;
	
	public CellStorage(int width, int height) {
		this.width = width;
		this.height = height;
		data = new Cell[height][width];
		for(int i=0;i<height;i++) {
			for(int j=0;j<width;j++) {
				data[i][j] = new Cell();
				data[i][j].setwh(j, i);
			}
		}
	}
	
	public void setAlive(int x, int y) {
		data[y][x].setState(Cell.State.ALIVE);
	}
	
	public void setDead(int x, int y) {
		data[y][x].setState(Cell.State.DEAD);
	}
	
	public void setData(Cell[][] data) {
		this.data = data;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public Cell getCell(int i, int j) {
		return data[i][j];
	}
	
}