package algorithmlogic;

public class BoardCell {
	private int x;
	private int y;
	private boolean hasBlock;
	
	public BoardCell(int x, int y, boolean hasBlock){
		this.x = x;
		this.y = y;
		this.hasBlock = hasBlock;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public boolean hasBlock(){
		return hasBlock;
	}
	
	public void print(){
		System.out.print(x);
		System.out.print(", ");
		System.out.println(y);
	}
}
