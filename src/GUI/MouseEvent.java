package GUI;

import java.awt.event.MouseListener;

import Data.Cell;

public class MouseEvent implements MouseListener{
	private Canvas canvas;
	private int x, y;
	
	public MouseEvent(Canvas canvas) {
		this.canvas = canvas;
	}
	
	@Override
	public void mouseClicked(java.awt.event.MouseEvent mouseEvent) {
		int edgeSize = canvas.edgeSize;
		int width = canvas.width;
		int height = canvas.height;
		int cellSize = canvas.cellSize;
		
		x = mouseEvent.getX()-edgeSize-10;
		y = mouseEvent.getY()-edgeSize-73;
		
		if(x>=0 && y >=0 && x <= 1199 && y<= 699) {
			Cell cell = new Cell();
			cell.setwh(x/cellSize, y/cellSize);
			
			if(canvas.contains(cell)) {
				canvas.removeFromList(cell);
			}
			else {
				canvas.addList(cell);
			}
			
			canvas.paint(canvas.getGraphics());
		}
	}

	@Override
	public void mouseEntered(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
	}
	
}