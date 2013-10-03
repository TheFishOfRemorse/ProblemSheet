import java.awt.Point;
abstract class Piece {
	public Point location = new Point();
	public char symbol; 
	public Piece(){
		
	}
	//we minus 1 from the location so that the input is the same as the specification in the question pdf
	public Piece(int x, int y, char symbol){
		location.x = x - 1;
		location.y = y - 1;
		this.symbol = symbol;
	}
	
	public Piece(Point a, char symbol){
		location.x = a.x -1;
		location.y = a.y - 1;
		this.symbol = symbol;
	}
	
	public Piece(int x, int y){
		location.x = x -1;
		location.y = y - 1;
	}
	
}
