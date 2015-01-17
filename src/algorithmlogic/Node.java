package algorithmlogic;

public class Node {
	private Node prev;
	private int x;
	private int y;
	
	private BoardCell boardCell;
	
	public Node(BoardCell boardCell, Node prev){
		this.boardCell = boardCell;
		this.prev = prev;
		x = boardCell.getX();
		y = boardCell.getY();
	}
	
	public BoardCell getBoardCell(){
		return boardCell;
	}
	
	public Node getPrev(){
		return prev;
	}
	
	public int findManhattenDistance(Node other){
		return (Math.abs(x - other.x)) + (Math.abs(y - other.y));
	}
	
	public void printNode(){
		getBoardCell().print();
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	
	
}
