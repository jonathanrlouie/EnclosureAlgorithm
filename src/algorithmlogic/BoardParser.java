package algorithmlogic;

public class BoardParser {
	
	private BoardCell[] cells;
	
	public Board parseBoard(String layout){
		String[] rows = layout.split("\n");
		int width = rows[0].length();
		int height = rows.length;
		String newLayout = "";
		for (String row : rows){
			newLayout += row;
		}
		cells = new BoardCell[newLayout.length()];
		generateCells(newLayout, width);
		return new Board(cells, width, height);
	}
	
	private void generateCells(String layout, int width){
		for (int i = 0; i < (layout.length()); i++){
			char cellChar = layout.charAt(i);
			switch (cellChar){
			case 'B':
				cells[i] = new BoardCell(i % width, i / width, true);
				break;
			case 'E':
				cells[i] = new BoardCell(i % width, i / width, false);
				break;
			default:
				
			}
		}
	}
}
