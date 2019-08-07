/**
 * 
 */
package assignment;

import java.awt.Point;
import java.util.Arrays;

//import static org.junit.Assert.*;

import org.junit.Test;

import assignment.Piece.PieceType;

/**
 * @author chenyuexu
 *
 */
public class TetrisPieceJUnit {

	@Test
	public void test() {
		
		TetrisPiece test = new TetrisPiece(Piece.PieceType.STICK);
		
		System.out.println(TetrisPiece.toString(test));
		
		
		
	}
	
	public void printFullRotation(TetrisPiece piece) {
		
		System.out.println("Clockwise Full Rotation: \n");
		System.out.println(TetrisPiece.toString(piece));
		
		for (int i=0; i<4; i++) {
			piece.clockwisePiece();
			System.out.println("\n"+TetrisPiece.toString(piece));
		}
	}
	
	
	public void printSkirts (TetrisPiece test) {
		System.out.println("Skirts: \n         " + Arrays.deepToString(test.getSkirts()));
	}
	
	
	public void printBodies (TetrisPiece test) {
		System.out.println("Bodies: " + TetrisPiece.toString(test.getBodies()));
	}
	
	
	public void printFullRotation(TetrisPiece piece, boolean bol) {
		
		System.out.println("Counterclockwise Full Rotation: \n");
		System.out.println(TetrisPiece.toString(piece));
		
		for (int i=0; i<4; i++) {
			piece.counterclockwisePiece();
			System.out.println("\n"+TetrisPiece.toString(piece));
		}
	}
	

}
