import java.util.Vector;
import java.awt.Point;

public class Board {
	public Piece[][] board = new Piece[8][8];
	public Vector<Piece> pieces = new Vector<Piece>(); 
	public int attacked = 0;
	
	public Board(){
		for(int i =0; i < board.length; i++){
			for(int j=0; j < board.length; j++){
				board[i][j] = new Blank(i,j);
			}
		}
	}
	
	public Board(int size){
		this.board = new Piece[size][size];
		for(int i =0; i < board.length; i++){
			for(int j=0; j < board.length; j++){
				board[i][j] = new Blank(i,j);
			}
		}
	}
	
	public void printQueensAttacked(){
		System.out.println(attacked + " Queens Attacked.");
	}
	public void printBoard(){
		for(int i =0; i < board.length; i++){
			for(int j=0; j < board.length; j++){
				System.out.print(board[i][j].symbol);
			}
			System.out.println();
		}
	}
	public void addPiece(Piece a){
		pieces.add(a);
		board[a.location.x][a.location.y] = a;
	}
	
	public void removePiece(int a, int b){
		int x = a - 1;
		int y = b - 1; // to keep with specifications
		board[x][y] = new Blank(x,y);
	}
	
	public boolean checkBoardForBlanks(){
		for(int i =0; i < board.length; i++ ){
			for(int j =0; j < board.length; j++){
				if(board[i][j].symbol == '_') return false;
			}
		}
		return true;
	}
	
	public void fillQueens(){
		this.addPiece(new Queen(board.length,1));
		this.addPiece(new Queen(1, board.length));
		int i = 1;
		int x1 = 2;
		while(!checkBoardForBlanks()){
			this.checkForQueenAttack(this.pieces); // place the x's
			int x = board.length - i;
			this.addPiece(new Queen(x,x));
			++i;
			if(!checkBoardForBlanks()){
				this.addPiece(new Queen(x1,x1));
				this.checkForQueenAttack(this.pieces);
				x1++;
			}
		}
	}
	
	public void checkForQueenAttack(Vector<Piece> pieces){
		for(int z = 0; z < pieces.size(); z++){
			Piece a = pieces.get(z);
			
			for(int i = 0; i < board.length ; i++){
			//vertical checks
				if(board[i][a.location.y] instanceof Queen) {
					if(!(i == a.location.x)) attacked++;
				}else board[i][a.location.y].symbol = 'x';//else board[i][a.location.y] = 'x';
			//horizontal checks
				if(board[a.location.x][i] instanceof Queen) {
					if(!(a.location.y == i)) attacked++;
				}else board[a.location.x][i].symbol = 'x';//else board[a.location.x][i] = 'x';
			}
			//diagonal checks
			Vector<Point> diagonals = new Vector<Point>(); // contains the points of all the diagonals available for the queen to attack
			for(int i =0; i < board.length; i++){
				int x = a.location.x - i;
				int x1 = a.location.x + i;
				int y = a.location.y - i;
				int y1 = a.location.y + i;
				
				if(x >= 0 && y >= 0) diagonals.add(new Point(x, y)) ;  // + +
				if((x1 < board.length && y1 < board.length)) diagonals.add(new Point(x1, y1)); // --	
				if(x1 < board.length && y >=0) diagonals.add(new Point(x1, y));  // + -							
				if(x >=0 && y1 < board.length) diagonals.add(new Point(x, y1));  // - +
			}
		
			for(int i =0; i < diagonals.size(); i++){
				Point abc = diagonals.elementAt(i);
				if(abc.x == a.location.x && abc.y == a.location.y) continue; 
				if((board[abc.x][abc.y] instanceof Blank)){ //checks to see if there is a blank piece ( we don not want to change the symbol of any other chess piece)
					board[abc.x][abc.y].symbol = 'x';
				}else {
					if(board[abc.x][abc.y] instanceof Queen){ // check if the piece that is being attacked is actually a queen and not any others.
					attacked++; 
					continue;
					}
				}

			}
		}
		//------------END OF CLASS------------//
	}
}