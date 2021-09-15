package Data;

import java.io.Serializable;

public class Cell implements Serializable{
	public enum State{
		ALIVE, DEAD;
	}
	
	private int neighbours;
	private State state;
	private int width, height;
	
	public Cell() {
		state = State.DEAD;
		neighbours = 0;
		width = 0; 
		height = 0;
	}
	
	public void setNeighbours(int neighbours) {
		this.neighbours = neighbours;
	}
	
	public int getNeighbours() {
		return neighbours;
	}
	
	public void setwh(int width, int height) {
		this.width = width; 
		this.height = height;
	}
	
	public int getw() {
		return width;
	}
	
	public int geth() {
		return height;
	}
	
	public State getState() {
		return state;
	}
	
	public void setState(State state) {
		this.state = state;
	}
	
	@Override
	public boolean equals(Object object) {
		if(object == this) {
			return true;
		}
		
		if ((object instanceof Cell) == false) {
            return false;
        }
		
		Cell cell = (Cell)object;
		
		if(cell.width == this.width && cell.height == this.height) {
			return true;
		}
		
		return false;
	}
	
}