package assignment;

import java.awt.*;
import java.util.*;

/**
 * An immutable representation of a tetris piece in a particular rotation.
 * 
 * All operations on a TetrisPiece should be constant time, except for it's
 * initial construction. This means that rotations should also be fast - calling
 * clockwisePiece() and counterclockwisePiece() should be constant time! You may
 * need to do precomputation in the constructor to make this possible.
 */

public final class TetrisPiece implements Piece {
	
	private PieceType piece_type;

	private int rotation_index;
	private int[] skirt;
	private Point[] body;
	private TetrisPiece[] piece_bag = null;
	
    /**
     * Construct a tetris piece of the given type. The piece should be in it's spawn orientation,
     * i.e., a rotation index of 0.
     * 
     * You may freely add additional constructors, but please leave this one - it is used both in
     * the runner code and testing code.
     */
	
	
	//constructor (initialize)
    public TetrisPiece(PieceType type) {
    	
        this.piece_type = type;
        this.rotation_index = 0;
        this.body = type.getSpawnBody();
		
		this.piece_bag = new TetrisPiece[4];
		
		TetrisPiece update = new TetrisPiece(type);
		
		for(int i = 0; i < 4; i++){
			this.piece_bag[i].body = update.body;
			//Dimension box = update.piece_type.getBoundingBox();
			//this.piece_bag[i].skirt =  update.calculate_skirt(update.body, box.width, box.height);
			update = update.rotatePiece(update);
		}
		
		
		
    }
    
	/*//constructor (rotation_index)
	public TetrisPiece(PieceType type, int rotation, Point[] body, Point[] skirt){
		
	}*/
	
	public int[] calculate_skirt (Point[] body, int width, int height) {

		boolean[][] skirt = new boolean[width][height];
		
		for (int i=0; i<width; i++) {
			for (int j=0; j<height; j++) {
				for (int k=0; k<body.length; k++) {
					if ((body[k].x == i)&&(body[k].y == j)) {
						skirt[i][j] = true;
					}
				}
			}
		}
		
		
		
		
		
		/*ArrayList<Integer>[] columns = new ArrayList<Integer>()[width];
		
		for (int i=0; i<body.length; i++) {
			int column = body[i].x;
			columns[column].
					
		}*/
		
		return skirt;
	}
	
	//returns NEW clockwise-rotated piece 
	public TetrisPiece rotatePiece(TetrisPiece piece) { 
		
		TetrisPiece new_piece = new TetrisPiece(piece.piece_type);
		
		for (int i=0; i<piece.body.length; i++) {
			int x = piece.body[i].y;
			int y = 2 - piece.body[i].x;
			new_piece.body[i].setLocation(x, y);
		}
		
		return new_piece;
		
	}
	
	//string representation (body)
	public String toString (TetrisPiece piece) {
		
		String body = "";
		
		for (int i=0; i<piece.body.length; i++) {
			String point = "(" + Integer.toString(piece.body[i].x) + "," + Integer.toString(piece.body[i].y) + ")";
			body += " " + point;
		}
		return body;
	}
	

    @Override
    public PieceType getType() {
        return this.piece_type;
    }

    @Override
    public int getRotationIndex() {
        return this.rotation_index;
    }

    @Override
    public Piece clockwisePiece() {
		rotation_index = (rotation_index + 1) % 4;
        return null;
    }

    @Override
    public Piece counterclockwisePiece() {
    	rotation_index = (rotation_index - 1) % 4;
        return null;
    }

    @Override
    public int getWidth() {
        return this.piece_type.getBoundingBox().width;
    }

    @Override
    public int getHeight() {
        return this.piece_type.getBoundingBox().height;
    }

    @Override
    public Point[] getBody() {
        return this.body;
    }

    @Override
    public int[] getSkirt() {     
        return this.skirt;
    }

    @Override
    public boolean equals(Object other) {
        // Ignore objects which aren't also tetris pieces.
        if(!(other instanceof TetrisPiece)) return false;
        TetrisPiece otherPiece = (TetrisPiece) other;
        if (otherPiece.body.equals(this.body)) return true;
        else return false;
        
    }
}
