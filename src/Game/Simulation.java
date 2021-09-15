package Game;

import java.util.ArrayList;

import Data.Cell;
import Data.Cell.State;
import Data.CellStorage;
import GUI.Canvas;

public class Simulation extends Thread {
	private CellStorage cellStorage;
	private Canvas canvas;
	private boolean end = false;
	
	public Simulation(Canvas canvas, int width, int height) {
		this.canvas = canvas;
		cellStorage = new CellStorage(width, height);
		ArrayList<Cell> aliveCells = canvas.getColoredCells();
		for(int i=0; i<aliveCells.size(); i++) {
			Cell cell = aliveCells.get(i);
			cellStorage.setAlive(cell.getw(), cell.geth());
		}
	}
	
	public void setEnd(boolean end) {
		this.end = end;
	}
	
	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
	}
	
	public synchronized void run(){
		int time = 100;
		ArrayList<Cell> aliveCells = canvas.getColoredCells();
		
		for(int i=0; i < aliveCells.size(); i++) {
			Cell cell = aliveCells.get(i);
			cellStorage.setAlive(cell.getw(), cell.geth());
		}
		try {
			canvas.paint(canvas.getGraphics());
			sleep(time);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

	
		while(!end) {
			aliveCells.clear();
			int height = cellStorage.getHeight();
			int width = cellStorage.getWidth();
			
			Cell[][] tempArray = new Cell[height][width];
			
			for(int i=0;i<height;i++)
				for(int j=0;j<width;j++) {
					tempArray[i][j] = new Cell();
					tempArray[i][j].setwh(j, i);
					tempArray[i][j].setState(cellStorage.getCell(i, j).getState());
				}
			
			for(int i=0;i<height;i++) {
				for(int j=0;j<width;j++) {
					int neighbours = numberOfNeighbours(cellStorage.getCell(i, j));
					tempArray[i][j].setNeighbours(neighbours);
					Cell.State state = tempArray[i][j].getState();
					
					if(state == Cell.State.DEAD && neighbours == 3) {
						tempArray[i][j].setState(Cell.State.ALIVE);
						aliveCells.add(tempArray[i][j]);
					}
					else if((state == Cell.State.ALIVE) && (neighbours > 3 || neighbours < 2))  {
						tempArray[i][j].setState(Cell.State.DEAD);
					}
					else if(state == Cell.State.ALIVE){
						tempArray[i][j].setState(Cell.State.ALIVE);
						aliveCells.add(tempArray[i][j]);
					}
				}
			}
		
			cellStorage.setData(tempArray);
			
			canvas.setColoredCells(aliveCells);
			canvas.paint(canvas.getGraphics());
			try {
				sleep(time);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
	}
	
	private int numberOfNeighbours(Cell cell) {
		int neighbours = 0;		
		int left=-1,right=1,north=-1,south=1; //borders of the cell
		
		if(cell.getw()==0) {
			left = 0;
		} else if(cell.getw()==cellStorage.getWidth()-1) {
			right = 0;
		}
		
		if(cell.geth()==0) {
			north = 0;
		}
		else if(cell.geth()==cellStorage.getHeight()-1) {
			south = 0;
		}
		
		for(int i=north;i<=south;i++) {
			for(int j=left;j<=right;j++) {
				Cell neighbour = cellStorage.getCell(cell.geth()+i,cell.getw()+j);
				if(cell != neighbour) {
					if(neighbour.getState() == Cell.State.ALIVE) {
						neighbours++;				
					}
				}
			}
		}
		return neighbours;
	}
	
}