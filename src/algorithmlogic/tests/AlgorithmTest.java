package algorithmlogic.tests;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import algorithmlogic.Board;
import algorithmlogic.BoardCell;
import algorithmlogic.BoardParser;
import algorithmlogic.Enclosure;
import algorithmlogic.EnclosureGenerator;
import algorithmlogic.Node;

public class AlgorithmTest {
	
	EnclosureGenerator generator;
	BoardParser boardParser;
	
	@Before
	public void init(){
		generator = new EnclosureGenerator();
		boardParser = new BoardParser();
	}
	
	@Test
	public void emptyTest(){
		Board board = boardParser.parseBoard(
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE");

		Board solution = boardParser.parseBoard(
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE");
		
		List<LinkedList<Node>> largestEnclosures = generator.generateLargestEnclosures(board);
		board.deleteEnclosures(largestEnclosures);
		//board.printBoard();
		assertTrue(compareBoards(board, solution));
	}
	
	@Test
	public void filledTest(){
		Board board = boardParser.parseBoard(
				"BBBBBBBBBB\n" +
				"BBBBBBBBBB\n" +
				"BBBBBBBBBB\n" +
				"BBBBBBBBBB\n" +
				"BBBBBBBBBB\n" +
				"BBBBBBBBBB\n" +
				"BBBBBBBBBB\n" +
				"BBBBBBBBBB\n" +
				"BBBBBBBBBB\n" +
				"BBBBBBBBBB");

		Board solution = boardParser.parseBoard(
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE");
		
		List<LinkedList<Node>> largestEnclosures = generator.generateLargestEnclosures(board);
		board.deleteEnclosures(largestEnclosures);
		//board.printBoard();
		assertTrue(compareBoards(board, solution));
	}
	
	@Test
	public void almostFilledTest(){
		Board board = boardParser.parseBoard(
				"EEEEEEEEEE\n" +
				"EBBBBBBBBE\n" +
				"EBBBBBBBBE\n" +
				"EBBBBBBBBE\n" +
				"EBBBBBBBBE\n" +
				"EBBBBBBBBE\n" +
				"EBBBBBBBBE\n" +
				"EBBBBBBBBE\n" +
				"EBBBBBBBBE\n" +
				"EEEEEEEEEE");

		Board solution = boardParser.parseBoard(
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE");
		
		List<LinkedList<Node>> largestEnclosures = generator.generateLargestEnclosures(board);
		board.deleteEnclosures(largestEnclosures);
		//board.printBoard();
		assertTrue(compareBoards(board, solution));
	}
	
	@Test
	public void largestTest(){
		Board board = boardParser.parseBoard(
				"BBBBBBBBBB\n" +
				"BEEEEEEEEB\n" +
				"BEEEEEEEEB\n" +
				"BEEEEEEEEB\n" +
				"BEEEEEEEEB\n" +
				"BEEEEEEEEB\n" +
				"BEEEEEEEEB\n" +
				"BEEEEEEEEB\n" +
				"BEEEEEEEEB\n" +
				"BBBBBBBBBB");

		Board solution = boardParser.parseBoard(
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE");
		
		List<LinkedList<Node>> largestEnclosures = generator.generateLargestEnclosures(board);
		board.deleteEnclosures(largestEnclosures);
		//board.printBoard();
		assertTrue(compareBoards(board, solution));
	}
	
	@Test
	public void simpleEnclosureTest() {
		Board board = boardParser.parseBoard(
				"EEEEEEE\n" +
				"EBBBBBE\n" +
				"EBEEEBE\n" +
				"EBEEEBE\n" +
				"EBBBBBE\n" +
				"EEEEEEE");

		Board solution = boardParser.parseBoard(
				"EEEEEEE\n" +
				"EEEEEEE\n" +
				"EEEEEEE\n" +
				"EEEEEEE\n" +
				"EEEEEEE\n" +
				"EEEEEEE");
		
		List<LinkedList<Node>> largestEnclosures = generator.generateLargestEnclosures(board);
		board.deleteEnclosures(largestEnclosures);
		//board.printBoard();
		assertTrue(compareBoards(board, solution));
	}
	
	@Test
	public void garbageBlockEnclosureTest() {
		Board board = boardParser.parseBoard(
				"EEEEEEEE\n" +
				"EBBBBBBE\n" +
				"EBEEEEBE\n" +
				"EBEBEEBE\n" +
				"EBEEEEBE\n" +
				"EBBBBBBE\n" +
				"EEEEEEEE");

		Board solution = boardParser.parseBoard(
				"EEEEEEEE\n" +
				"EEEEEEEE\n" +
				"EEEEEEEE\n" +
				"EEEEEEEE\n" +
				"EEEEEEEE\n" +
				"EEEEEEEE\n" +
				"EEEEEEEE");
		
		List<LinkedList<Node>> largestEnclosures = generator.generateLargestEnclosures(board);
		board.deleteEnclosures(largestEnclosures);
		//board.printBoard();
		assertTrue(compareBoards(board, solution));
	}
	
	@Test
	public void noCornersEnclosureTest() {
		Board board = boardParser.parseBoard(
				"EEEEEEE\n" +
				"EEBBBEE\n" +
				"EBEEEBE\n" +
				"EBEEEBE\n" +
				"EEBBBEE\n" +
				"EEEEEEE");

		Board solution = boardParser.parseBoard(
				"EEEEEEE\n" +
				"EEEEEEE\n" +
				"EEEEEEE\n" +
				"EEEEEEE\n" +
				"EEEEEEE\n" +
				"EEEEEEE");
		
		List<LinkedList<Node>> largestEnclosures = generator.generateLargestEnclosures(board);
		board.deleteEnclosures(largestEnclosures);
		//board.printBoard();
		assertTrue(compareBoards(board, solution));
	}
	

	@Test
	public void solidEnclosureTest() {
		Board board = boardParser.parseBoard(
				"EEEEEEE\n" +
				"EBBBBBE\n" +
				"EBBBBBE\n" +
				"EBBBBBE\n" +
				"EBBBBBE\n" +
				"EEEEEEE");

		Board solution = boardParser.parseBoard(
				"EEEEEEE\n" +
				"EEEEEEE\n" +
				"EEEEEEE\n" +
				"EEEEEEE\n" +
				"EEEEEEE\n" +
				"EEEEEEE");
		List<LinkedList<Node>> largestEnclosures = generator.generateLargestEnclosures(board);
		board.deleteEnclosures(largestEnclosures);
		//board.printBoard();
		assertTrue(compareBoards(board, solution));
	}
	
	@Test
	public void branchEnclosureTest(){
		Board board = boardParser.parseBoard(
				"EEEEEEEEEE\n" +
				"EEBEEEEEEE\n" +
				"EEBEEEEEEE\n" +
				"EEBBBBBBEE\n" +
				"EEBEEEEBEE\n" +
				"EEBBBBBBEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE");

		Board solution = boardParser.parseBoard(
				"EEEEEEEEEE\n" +
				"EEBEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE");
		
		List<LinkedList<Node>> largestEnclosures = generator.generateLargestEnclosures(board);
		board.deleteEnclosures(largestEnclosures);
		//board.printBoard();
		assertTrue(compareBoards(board, solution));
	}
	
	@Test
	public void adjoinedEnclosureTest(){
		Board board = boardParser.parseBoard(
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEBBBBBBEE\n" +
				"EEBEEBEBEE\n" +
				"EEBEEBEBEE\n" +
				"EEBEEBEBEE\n" +
				"EEBBBBBBEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE");

		Board solution = boardParser.parseBoard(
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE");
		
		List<LinkedList<Node>> largestEnclosures = generator.generateLargestEnclosures(board);
		board.deleteEnclosures(largestEnclosures);
		//board.printBoard();
		assertTrue(compareBoards(board, solution));
	}
	
	@Test
	public void noEnclosureTest(){
		Board board = boardParser.parseBoard(
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEBBBBBBEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE");

		Board solution = boardParser.parseBoard(
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEBBBBBBEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE");
		
		List<LinkedList<Node>> largestEnclosures = generator.generateLargestEnclosures(board);
		board.deleteEnclosures(largestEnclosures);
		assertTrue(compareBoards(board, solution));
	}
	
	@Test
	public void concaveEnclosureTest(){
		Board board = boardParser.parseBoard(
				"EEEEEEEEEE\n" +
				"EBBEEEBBEE\n" +
				"EBEBEBEBEE\n" +
				"EBEEBEEBEE\n" +
				"EEBEEEBEEE\n" +
				"EEEBEBEEEE\n" +
				"EEBEEEBEEE\n" +
				"EBEEBEEBEE\n" +
				"EBBBEBBBEE\n" +
				"EEEEEEEEEE");

		Board solution = boardParser.parseBoard(
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE");
		
		List<LinkedList<Node>> largestEnclosures = generator.generateLargestEnclosures(board);
		board.deleteEnclosures(largestEnclosures);
		//board.printBoard();
		assertTrue(compareBoards(board, solution));
	}
	
	@Test
	public void connectedConcaveEnclosureTest(){
		Board board = boardParser.parseBoard(
				"EEEEEEEEEE\n" +
				"EBBEEEBBEE\n" +
				"EBEBEBEBEE\n" +
				"EBEEBEEBEE\n" +
				"EEBEEEBEEE\n" +
				"EEEBBBEEEE\n" +
				"EEBEEEBEEE\n" +
				"EBEEBEEBEE\n" +
				"EBBBEBBBEE\n" +
				"EEEEEEEEEE");

		Board solution = boardParser.parseBoard(
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE");
		
		List<LinkedList<Node>> largestEnclosures = generator.generateLargestEnclosures(board);
		board.deleteEnclosures(largestEnclosures);
		//board.printBoard();
		assertTrue(compareBoards(board, solution));
	}
	
	@Test
	public void filledConcaveEnclosureTest(){
		Board board = boardParser.parseBoard(
				"EEEEEEEEEE\n" +
				"EBBEEEBBEE\n" +
				"EBBBEBBBEE\n" +
				"EBBBBBBBEE\n" +
				"EEBBBBBEEE\n" +
				"EEEBBBEEEE\n" +
				"EEBBBBBEEE\n" +
				"EBBBBBBBEE\n" +
				"EBBBEBBBEE\n" +
				"EEEEEEEEEE");

		Board solution = boardParser.parseBoard(
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE");
		
		List<LinkedList<Node>> largestEnclosures = generator.generateLargestEnclosures(board);
		board.deleteEnclosures(largestEnclosures);
		//board.printBoard();
		assertTrue(compareBoards(board, solution));
	}
	
	@Test
	public void linkedEnclosureTest(){
		Board board = boardParser.parseBoard(
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEBBEBBEEE\n" +
				"EBEEBEEBEE\n" +
				"EEBBEBBEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE");

		Board solution = boardParser.parseBoard(
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE\n" +
				"EEEEEEEEEE");
		
		List<LinkedList<Node>> largestEnclosures = generator.generateLargestEnclosures(board);
		board.deleteEnclosures(largestEnclosures);
		//board.printBoard();
		assertTrue(compareBoards(board, solution));
	}
	
	// helper method that returns true if boards match and false if they don't
	private boolean compareBoards(Board board1, Board board2){
		for (BoardCell cell : board1.getCells()){
			BoardCell solCell = board2.getCell(cell.getX(), cell.getY());
			if ((!cell.hasBlock() && solCell.hasBlock()) || (cell.hasBlock() && !solCell.hasBlock())){
				return false;
			}
		}
		return true;
	}
	
}
