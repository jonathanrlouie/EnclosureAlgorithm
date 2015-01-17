package algorithmlogic;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;

public class InsideEnclosureAlgorithm {

	private LinkedList<Node> enclosure;
	private Board board;
	
	public InsideEnclosureAlgorithm(LinkedList<Node> enclosure, Board board){
		this.enclosure = enclosure;
		this.board = board;
	}
	
	public ArrayList<Node> findInnerNodes(){
		Node topLeftCell = findTopLeftCell(enclosure);
		/*
		if (topLeftCell.getPrev() != null && topLeftCell.getX() == 2 && topLeftCell.getY() == 2){
			topLeftCell.getPrev().printNode();
		}
		*/
		ArrayList<Node> borderNodes = findOuterNodes(new ArrayList<Node>(), topLeftCell);
		/*
		System.out.println("=============================");
		
		for (Node node: borderNodes){
			node.getPrev().printNode();
		}
		System.out.println("=============================");
		*/
		ArrayList<Node> layers = generateLayers(borderNodes, null);
		/*
		System.out.println("=============================");
		for (Node node: layers){
			node.printNode();
		}
		System.out.println("=============================");
		*/
		return layers;
	}
	
	private ArrayList<Node> generateLayers(ArrayList<Node> currentLayer, ArrayList<Node> prevLayer){
		ArrayList<Node> nextLayer = new ArrayList<Node>();
		/*
		System.out.println("=============================");
		if (prevLayer != null){
		for (Node node: prevLayer){
			node.printNode();
			
		}
		}
		
		System.out.println("=============================");
		*/
		if (!currentLayer.isEmpty()){
			for (int i = 1; i < currentLayer.size()-1; i++){
				Node currentNode = currentLayer.get(i);
				Node prevNode = currentLayer.get(i-1);
				Node nextNode = currentLayer.get(i+1);
				Node[] nodesToAdd = new Node[2];
				//prevNode.printNode();
				//System.out.println(findDir(currentNode,prevNode));
				//currentNode.printNode();
				switch (findDir(currentNode,prevNode)) {
				case 0x0:
					if (nextNode.getX() < currentNode.getX()){
						nodesToAdd[0] = new Node(board.getCell(currentNode.getX(),currentNode.getY()+1),null);
					} else if (nextNode.getY() > currentNode.getY()){
						nodesToAdd[0] = new Node(board.getCell(currentNode.getX(),currentNode.getY()+1),null);
						nodesToAdd[1] = new Node(board.getCell(currentNode.getX()-1, currentNode.getY()),null);
					}
					break;
				case 0x1:
					if (nextNode.getY() < currentNode.getY()){
						nodesToAdd[0] = new Node(board.getCell(currentNode.getX()+1,currentNode.getY()),null);
					} else if (nextNode.getX() < currentNode.getX()){
						nodesToAdd[0] = new Node(board.getCell(currentNode.getX()+1,currentNode.getY()),null);
						nodesToAdd[1] = new Node(board.getCell(currentNode.getX(),currentNode.getY()+1),null);
					}
					break;
				case 0x2:
					if (nextNode.getY() < currentNode.getY()){
						nodesToAdd[0] = new Node(board.getCell(currentNode.getX()+1,currentNode.getY()), null);
					} else if (nextNode.getX() < currentNode.getX()){
						nodesToAdd[0] = new Node(board.getCell(currentNode.getX()+1,currentNode.getY()),null);
						nodesToAdd[1] = new Node(board.getCell(currentNode.getX(),currentNode.getY()+1),null);
					}
					break;
				case 0x3:
					if (nextNode.getX() > currentNode.getX()){
						nodesToAdd[0] = new Node(board.getCell(currentNode.getX(),currentNode.getY()-1), null);
					} else if (nextNode.getY() > currentNode.getY()){
						nodesToAdd[0] = new Node(board.getCell(currentNode.getX(),currentNode.getY()-1),null);
						nodesToAdd[1] = new Node(board.getCell(currentNode.getX()+1,currentNode.getY()),null);
					}
					break;
				case 0x4:
					if (nextNode.getX() > currentNode.getX()){
						nodesToAdd[0] = new Node(board.getCell(currentNode.getX(),currentNode.getY()-1), null);
					} else if (nextNode.getY() > currentNode.getY()){
						nodesToAdd[0] = new Node(board.getCell(currentNode.getX(),currentNode.getY()-1),null);
						nodesToAdd[1] = new Node(board.getCell(currentNode.getX()+1,currentNode.getY()),null);
					}
					break;
				case 0x5:
					if (nextNode.getY() < currentNode.getY()){
						nodesToAdd[0] = new Node(board.getCell(currentNode.getX()-1,currentNode.getY()), null);
					} else if (nextNode.getX() > currentNode.getX()){
						nodesToAdd[0] = new Node(board.getCell(currentNode.getX()-1,currentNode.getY()),null);
						nodesToAdd[1] = new Node(board.getCell(currentNode.getX(),currentNode.getY()-1),null);
					}
					break;
				case 0x6:
					if (nextNode.getY() < currentNode.getY()){
						nodesToAdd[0] = new Node(board.getCell(currentNode.getX()-1,currentNode.getY()), null);
					} else if (nextNode.getX() > currentNode.getX()){
						nodesToAdd[0] = new Node(board.getCell(currentNode.getX()-1,currentNode.getY()),null);
						nodesToAdd[1] = new Node(board.getCell(currentNode.getX(),currentNode.getY()-1),null);
					}
					break;
				case 0x7:
					if (nextNode.getX() < currentNode.getX()){
						nodesToAdd[0] = new Node(board.getCell(currentNode.getX(),currentNode.getY()+1),null);
					} else if (nextNode.getY() > currentNode.getY()){
						nodesToAdd[0] = new Node(board.getCell(currentNode.getX(),currentNode.getY()+1),null);
						nodesToAdd[1] = new Node(board.getCell(currentNode.getX()-1, currentNode.getY()),null);
					}
					break;
				default:
				}
				for (Node nodeToAdd : nodesToAdd){
					if (nodeToAdd != null && !nextLayer.contains(nodeToAdd) && !currentLayer.contains(nodeToAdd)){
						nextLayer.add(nodeToAdd);
					}
				}
			}
			currentLayer.addAll(generateLayers(nextLayer,currentLayer));
		}
		return currentLayer;
	}
	
	/* Gives direction of firstNode relative to prevNode
	 * key:
	 * 0x0: Left
	 * 0x1: DownLeft
	 * 0x2: Down
	 * 0x3: DownRight
	 * 0x4: Right
	 * 0x5: UpRight
	 * 0x6: Up
	 * 0x7: UpLeft
	 * 0x8: Not adjacent (can't compare normally, do nothing)
	 */
	private byte findDir(Node firstNode, Node prevNode){
		if (firstNode.findManhattenDistance(prevNode) == 1){
			if (firstNode.getX() != prevNode.getX()){
				if (firstNode.getX() < prevNode.getX()){
					return 0x0;
				} else {
					return 0x4;
				}
			} else {
				if (firstNode.getY() > prevNode.getY()) {
					return 0x2;
				} else {
					return 0x6;
				}
			} 
		} else if (firstNode.findManhattenDistance(prevNode) == 2){
			// since manhatten distance can be 2 for nodes in a straight line with a gap between them, need
			// to account for that case
			if (firstNode.getX() < prevNode.getX() && firstNode.getY() > prevNode.getY()){
				return 0x1;
			} else if (firstNode.getX() > prevNode.getX() && firstNode.getY() > prevNode.getY()){
				return 0x3;
			} else if (firstNode.getX() > prevNode.getX() && firstNode.getY() < prevNode.getY()){
				return 0x5;
			} else if (firstNode.getX() < prevNode.getX() && firstNode.getY() < prevNode.getY()){
				return 0x7;
			} else {
				return 0x8;
			}
		} else {
			return 0x8;
		}
	}
	
	private Node findTopLeftCell(LinkedList<Node> enclosure){
		Node currentTopLeft = enclosure.getFirst();
		for (Node node : enclosure){
			if (node.getY() < currentTopLeft.getY()){
				currentTopLeft = node;
			} else if (node.getY() == currentTopLeft.getY() && node.getX() < currentTopLeft.getX()){
				currentTopLeft = node;
			}
		}
		return currentTopLeft;
	}
	
	private ArrayList<Node> findOuterNodes(ArrayList<Node> borderNodes, Node topLeftNode){
		//topLeftNode.getPrev().printNode();
		if (prevCCW(topLeftNode)){
			if (topLeftNode.getX() == 2 && topLeftNode.getY() == 2){
				topLeftNode.getPrev().printNode();
				System.out.println("entered ccw case");
			}
			//System.out.println("ran prevCCW");
			addPrevs(borderNodes, topLeftNode);
			return borderNodes;
		} else {
			/*
			if (topLeftNode.getX() == 2 && topLeftNode.getY() == 2){
				System.out.println("entered else case");
			}*/
			//System.out.println("did not run prevCCW");
			return new ArrayList<Node>(enclosure);
		}
	}
	
	// bug is here:
	// previous node might be SE, but actual node going ccw is south, thus SE node is the clockwise path!
	private boolean prevCCW(Node node){
		Node prev = node.getPrev();
		if (prev == null){
			prev = enclosure.getLast().getPrev();
		}
		return (checkDir(node, prev, -1) || (checkDir(node, prev, 0) && !board.cellHasBlock(node.getX()-1, node.getY()+1)) || (checkDir(node, prev, 1) && !board.cellHasBlock(node.getX()-1,node.getY()+1) && !board.cellHasBlock(node.getX(), node.getY()+1)));
	}
	
	private boolean checkDir(Node node, Node prev, int xoffset){
		return (prev.getX() == node.getX()+xoffset && prev.getY() == node.getY()+1);
	}
	
	private void addPrevs(ArrayList<Node> borderNodes, Node topLeftNode){
		addPrevs(borderNodes, topLeftNode, topLeftNode);
	}
	
	private void addPrevs(ArrayList<Node> borderNodes, Node current, Node topLeftNode){
		//current.getPrev().printNode();
		Node actualPrev;
		if (current.getPrev() == null){
			actualPrev = enclosure.getLast().getPrev();
		} else {
			actualPrev = current.getPrev();
		}
		if (actualPrev.equals(topLeftNode)){
			borderNodes.add(current);
			addPrevs(borderNodes, actualPrev, current);
		}
	}
}
