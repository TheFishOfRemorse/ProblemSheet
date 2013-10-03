import java.awt.Point;

public class Blank extends Piece {
		public Blank(){
			this.symbol = '_';
		}
		
		public Blank(int x, int y){
			super(x,y);
			this.symbol = '_';
		}
		
		public Blank(Point a, char symbol){
			super(a,symbol);
		}
		
}
