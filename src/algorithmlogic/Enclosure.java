package algorithmlogic;

import java.util.LinkedList;
import java.util.List;

public class Enclosure {
	LinkedList<Node> nodes;
	
	public Enclosure(LinkedList<Node> nodeList){
		nodes = nodeList;
	}
	
	public int size(){
		return nodes.size();
	}
	
	public LinkedList<Node> getNodes() {
		return nodes;
	}
	
	public LinkedList<BoardCell> getCells(){
		LinkedList<BoardCell> cellList = new LinkedList<BoardCell>();
		for (Node node : nodes){
			cellList.add(node.getBoardCell());
		}
		return cellList;
	}
}
