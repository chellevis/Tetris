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
	
	private PieceType pieceType; 
	private int rotationIndex; 
	
	private Point[] body;
	private int[] skirt; 
	
	private Dimension box;
	private Point[][] bodies;
	private int[][] skirts;
	
	
/**
 * Construct a tetris piece of the given type. The piece should be in it's spawn orientation,
 * i.e., a rotation index of 0.
 * 
 * You may freely add additional constructors, but please leave this one - it is used both in
 * the runner code and testing code.
 */
	
	
	// Constructor
    public TetrisPiece(PieceType type) {
        this.pieceType = type; this.rotationIndex = 0;
        
        this.box = type.getBoundingBox();
        this.body = type.getSpawnBody();
        this.skirt = TetrisPiece.calculateSkirt(this.body, this.box);
        
		this.bodies = new Point[4][];
		this.skirts = new int[4][];
		this.loadRotations();
		
	}
		
	
	// returns NEW clockwise-rotated body 
	public static Point[] rotateBody(Point[] body, Dimension box) { 
			
		Point[] newBody = new Point[body.length];
		for (int i=0; i<body.length; i++) {
			int x = body[i].y;
			int y = (box.width - 1) - body[i].x;
			newBody[i] = new Point(x, y);
		}
		return newBody;
	}
		
		
	// returns NEW skirt(int[]) from body, width, height (Point[], int, int)
	public static int[] calculateSkirt (Point[] body, Dimension box) {
		
		boolean[][] skirt = new boolean[box.width][box.height]; //mapping body in a bounding box sized boolean matrix
		for (int i=0; i<box.width; i++) {
			for (int j=0; j<box.height; j++) {
				for (int k=0; k<body.length; k++) {
					if ((body[k].x == i)&&(body[k].y == j)) { skirt[i][j] = true;}
				}
			}
		}
		int[] newSkirt = new int[box.width]; //record minimum from each vertical column
		Arrays.fill(newSkirt, Integer.MAX_VALUE);
		for (int i=0; i<box.width; i++) {
			for (int j=0; j<box.height; j++) {
				if (skirt[i][j] == true) { newSkirt[i] = j; break; }			
			}
		}
		return newSkirt;
	}
	
	
	public void loadRotations () {
		this.bodies[0] = this.pieceType.getSpawnBody();
		this.skirts[0] = TetrisPiece.calculateSkirt(this.bodies[0], this.box);
		
		for(int i = 1; i < 4; i++){
			//System.out.println(TetrisPiece.toString(bodies[0]));
			this.bodies[i] = TetrisPiece.rotateBody(this.bodies[i-1], this.box);
			this.skirts[i] = TetrisPiece.calculateSkirt(this.bodies[i], this.box);
		}
	}
	
	
    @Override
    public Piece clockwisePiece() {
		this.rotationIndex = (this.rotationIndex + 1) % 4;
		this.body = this.bodies[this.rotationIndex];
		this.skirt = this.skirts[this.rotationIndex];
        return this;
    }

    
    @Override
    public Piece counterclockwisePiece() {
    	this.rotationIndex = (this.rotationIndex + 3) % 4;
    	this.body = this.bodies[this.rotationIndex];
		this.skirt = this.skirts[this.rotationIndex];
        return this;
    }

    
    @Override
    public boolean equals(Object other) {
        if(!(other instanceof TetrisPiece)) return false; // Ignore objects which aren't also tetris pieces.
        TetrisPiece otherPiece = (TetrisPiece) other;
        if (otherPiece.body.equals(this.body)) return true;
        else return false;
    }
	
    
    // getters
    
    @Override public PieceType getType() { return this.pieceType; }
    @Override public int getRotationIndex() { return this.rotationIndex; }
    @Override public int getWidth() { return this.box.width; }
    @Override public int getHeight() { return this.box.height; }
    @Override public Point[] getBody() { return this.body; }
    @Override public int[] getSkirt() { return this.skirt; }
    public int[][] getSkirts() { return this.skirts; }
    public Point[][] getBodies() { return this.bodies; }
    
    
    // TO STRINGs
	
	public static String toString (TetrisPiece piece) {
		String pieceTypeString = "Piece Type:    " + piece.pieceType.toString() + " (" + piece.rotationIndex + ")";
		String bodyString = "Body:         " + TetrisPiece.toString(piece.body);
		String skirtString = "Skirt:         " + Arrays.toString(piece.skirt);
		return pieceTypeString + "\n" + bodyString + "\n" + skirtString;
	}
	
	public static String toString (Point[] body) {
		String bodyString = "";
		for (int i=0; i<body.length; i++) {
			String point = "(" + Integer.toString(body[i].x) + "," + Integer.toString(body[i].y) + ")";
			bodyString += " " + point;
		}
		return bodyString;
	}
	
	public static String toString (Point[][] bodies) {
		String bodiesString = "";
		for (int i=0; i<bodies.length; i++) { bodiesString += "\n         " + i + ": " + TetrisPiece.toString(bodies[i]); }
		return bodiesString;
	}
	
	public static String toString (int[][] skirts) { return Arrays.deepToString(skirts); }
    
}