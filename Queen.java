import java.awt.Point;


public class Queen extends Piece {
	public Queen(){
		this.symbol = 'Q';
	}
	
	public Queen(int x, int y, char symbol){
		super(x,y,symbol);
	}
	
	public Queen(int x, int y){
		super(x,y);
		this.symbol = 'Q';
	}
	
	public Queen(Point a, char symbol){
		super(a,symbol);
	}
}
