package algorithmlogic;

import java.util.LinkedList;
import java.util.List;

public class Board {
	private int width;
	private int height;
	private BoardCell[] cells;
	
	public Board(BoardCell[] cells, int width, int height){
		this.cells = cells;
		this.width = width;
		this.height = height;
	}
	
	
	public void deleteEnclosures(List<LinkedList<Node>> largestEnclosures){
		BoardCell boardCell;
		if (!largestEnclosures.isEmpty()){
			for (LinkedList<Node> enclosure : largestEnclosures){
				for (Node node : enclosure){
					boardCell = node.getBoardCell();
					cells[boardCell.getX()+boardCell.getY()*width] = new BoardCell(boardCell.getX(), boardCell.getY(), false);
				}
			}
		}
	}
	
	public boolean cellHasBlock(int x, int y){
		if (x >= width || x < 0 || y >= height || y < 0){
			return false;
		} else {
			return getCell(x,y).hasBlock();
		}
	}
	
	public BoardCell getCell(int x, int y){
		return cells[x+y*width];
	}
	
	public BoardCell[] getCells(){
		return cells;
	}
	
	public int getWidth(){
		return width;
	}
	
	public void printCells(){
		for (int i = 0; i < cells.length; i++){
			if (cells[i] != null){
				cells[i].print();
			}
		}
	}
	
	public void printBoard(){
		for (int j = 0; j < height; j++){
			for (int i = 0; i < width; i++){
				if (cells[i+j*width] != null && cells[i+j*width].hasBlock()){
					System.out.print("B");
				} else {
					System.out.print("E");
				}
			}
			System.out.print("\n");
		}
	}
}
