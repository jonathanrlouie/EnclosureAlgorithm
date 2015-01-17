package algorithmlogic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class EnclosureGenerator {
	private Board board;
	private int largestArea;
	private BoardCell[] cellList;
	
	private LinkedList<Node> largestEnclosure;
	
	public List<LinkedList<Node>> generateLargestEnclosures(Board board){
		this.board = board;
		largestArea = 0;
		boolean foundCell = false;
		cellList = board.getCells();
		List<LinkedList<Node>> enclosures = new LinkedList<LinkedList<Node>>();
		for (int i = 0; i<board.getCells().length && !foundCell; i++){
			if (cellList[i].hasBlock()){
				foundCell = true;
				generateLargestEnclosure(cellList[i]);
				if (largestEnclosure != null){
					enclosures.add(largestEnclosure);
				}
			}
		}
		return enclosures;
	}
	
	private void generateLargestEnclosure(BoardCell cell){
		generateLargestEnclosureHelper(cell, null, new LinkedList<Node>());
	}
	
	private void generateLargestEnclosureHelper(BoardCell cell, Node prev, LinkedList<Node> enclosure){
		Node currentNode = new Node(cell, prev);
		if (!enclosure.contains(currentNode)){
			LinkedList<Node> updatedList = new LinkedList<Node>(enclosure);
			updatedList.add(currentNode);
			checkCell(1,0,cell,currentNode, updatedList);
			checkCell(1,-1,cell,currentNode, updatedList);
			checkCell(0,-1,cell,currentNode, updatedList);
			checkCell(-1,-1,cell,currentNode, updatedList);
			checkCell(-1,0,cell,currentNode, updatedList);
			checkCell(-1,1,cell,currentNode, updatedList);
			checkCell(0,1,cell,currentNode, updatedList);
			checkCell(1,1,cell,currentNode, updatedList);
		} else if (prev != null && prev.getPrev() != null && !prev.getPrev().equals(currentNode)){
			LinkedList<Node> updatedList = new LinkedList<Node>(enclosure);
			//updatedList.add(currentNode);
			LinkedList<Node> actualEnclosure = trimEnclosure(currentNode, updatedList);
			/*
			System.out.println("===========================");
			for (Node node: actualEnclosure){
				node.printNode();
			}
			*/
			InsideEnclosureAlgorithm algorithm = new InsideEnclosureAlgorithm(actualEnclosure,board);
			ArrayList<Node> filledEnclosure = algorithm.findInnerNodes();
			/*System.out.println("===========================");
			for (Node node: filledEnclosure){
				node.printNode();
			}*/
			int area = filledEnclosure.size();
			if (area > largestArea){
				//System.out.println(area);
				largestArea = area;
				largestEnclosure = new LinkedList<Node>(filledEnclosure);
			}
		}
	}
	
	private void checkCell(int xOffset, int yOffset, BoardCell cell, Node currentNode, LinkedList<Node> updatedList){
		int cellX = cell.getX();
		int cellY = cell.getY();
		if (validCell(cellX, cellY, xOffset, yOffset)){
			generateLargestEnclosureHelper(board.getCell(cellX+xOffset, cellY+yOffset), currentNode, updatedList);
		} 
	}
	
	private boolean validCell(int cellX, int cellY, int xOffset, int yOffset){
		return board.cellHasBlock(cellX+xOffset,cellY+yOffset) && diagonalOkay(cellX, cellY, xOffset, yOffset) &&
				verticalOkay(cellX, cellY, xOffset, yOffset) && horizontalOkay(cellX, cellY, xOffset, yOffset);
	}
	
	private boolean diagonalOkay(int cellX, int cellY, int xOffset, int yOffset){
		return !((Math.abs(xOffset) + Math.abs(yOffset) > 1) && board.cellHasBlock(cellX+xOffset, cellY) && board.cellHasBlock(cellX, cellY+yOffset));
	}
	
	private boolean verticalOkay(int cellX, int cellY, int xOffset, int yOffset){
		return !(xOffset == 0 && board.cellHasBlock(cellX+1, cellY+yOffset) && board.cellHasBlock(cellX-1, cellY+yOffset));
	}
	
	private boolean horizontalOkay(int cellX, int cellY, int xOffset, int yOffset){
		return !(yOffset == 0 && board.cellHasBlock(cellX+xOffset,cellY+1) && board.cellHasBlock(cellX+xOffset, cellY-1));
	}
	
	// trim any trailing nodes attached to this enclosure (if enclosure has a tail)
	private LinkedList<Node> trimEnclosure(Node lastNode, LinkedList<Node> enclosure){
		int index = 0;
		for (Node node : enclosure){
			if (node.equals(lastNode)){
				/*
				System.out.println("================================");
				enclosure.get(index).printNode();
				System.out.println("================================");
				*/
				LinkedList<Node> sublist = new LinkedList<Node>();
				Node nodeToAdd = new Node(enclosure.get(index).getBoardCell(),lastNode.getPrev());
				sublist.add(nodeToAdd);
				sublist.addAll(enclosure.subList(index+1, enclosure.size()));
				sublist.add(lastNode);
				return sublist;
			}
			index++;
		}
		return enclosure;
	}
}
