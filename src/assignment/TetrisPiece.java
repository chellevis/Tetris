package assignment;

import java.awt.*;

/**
 * An immutable representation of a tetris piece in a particular rotation.
 * 
 * All operations on a TetrisPiece should be constant time, except for it's
 * initial construction. This means that rotations should also be fast - calling
 * clockwisePiece() and counterclockwisePiece() should be constant time! You may
 * need to do precomputation in the constructor to make this possible.
 */
public final class TetrisPiece implements Piece {
	
	private PieceType piecetype;
	private Point[] body;
	private int rotationindex;
	private int[] skirt;
	
    /**
     * Construct a tetris piece of the given type. The piece should be in it's spawn orientation,
     * i.e., a rotation index of 0.
     * 
     * You may freely add additional constructors, but please leave this one - it is used both in
     * the runner code and testing code.
     */
	
	//constructor
    public TetrisPiece(PieceType type) {
        this.piecetype = type;
        this.rotationindex = 0;
        this.body = Piece.getSpawnBody();
    }

    @Override
    public PieceType getType() {
        return this.piecetype;
    }

    @Override
    public int getRotationIndex() {
        return this.rotationindex;
    }

    @Override
    public Piece clockwisePiece() {
        return null;
    }

    @Override
    public Piece counterclockwisePiece() {
        return null;
    }

    @Override
    public int getWidth() {
        return this.piecetype.getBoundingBox().width;
    }

    @Override
    public int getHeight() {
        return this.piecetype.getBoundingBox().height;
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
