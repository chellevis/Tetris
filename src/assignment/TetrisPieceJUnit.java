/**
 * 
 */
package assignment;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author chenyuexu
 *
 */
public class TetrisPieceJUnit {

	@Test
	public void test() {
		TetrisPiece t = new TetrisPiece(Piece.PieceType.LEFT_DOG);
		
		System.out.println(t.toString(t));
		
		t.rotatePiece(t);
		
		System.out.println(t.toString(t));
		
		//assertEquals(i, "Hi");
		
	}

}
