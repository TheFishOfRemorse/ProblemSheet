import java.util.Vector;
import java.awt.Point;
import java.util.Scanner;
public class Board {
	public Piece[][] board = new Piece[8][8];
	public Vector<Piece> pieces = new Vector<Piece>(); 
	public int attacked = 0;
	int food= 6;
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
	
	public void program_least_queens(){
		this.fillQueens();
		this.printBoard();
		System.out.println(pieces.size() + " Queens are required to cover all the tiles of an " + board.length + " by " + board.length + " board.");
		System.out.println();
	}
	
	public void newSize(int size){
		this.board = new Piece[size][size];
		for(int i =0; i < board.length; i++){
			for(int j=0; j < board.length; j++){
				board[i][j] = new Blank(i,j);
			}
		}
		// remove all previous pieces
		pieces.clear(); 
	}
	
	public void program_attack_queens(){
		Scanner input = new Scanner(System.in);
		int x = -1 , y = -1;
		System.out.println("Enter at least one Queen's coordinates ending with any character, 1 2 4 5");
		//input.useDelimiter(" *|\n");
		String s = input.nextLine();
		//regex throws away any, whitespace non digit chars, 0 or 9 (returns only numbers).
		String digits = s.replaceAll("\\s*[\\D^0|^9]*\\s*", "");
		if(!(digits.length() % 2 == 0)){
			System.out.println("Invalid input.");
			System.exit(-1);
		}
		for(int i =0; i < digits.length();i+=2){
			x = (int)( digits.charAt(i) - '0');
			y = (int)( digits.charAt(i+1) - '0');
			this.addPiece(new Queen(x,y));
		}
		checkForQueenAttack(this.pieces);
		printBoard();
		printQueensAttacked();
		clearBoard();
	}
	
	public void program_attack_queens(String[] args){
		
		int x = -1 , y = -1;
		String digits = new String();

		for(int i =0; i < args.length; i+=2){
			if(!(args.length % 2 == 0)) {
				System.out.println("Invalid Input.");
				System.exit(-1);
			}
			digits = args[i].replaceAll("\\s*[\\D^0|^9]*\\s*", "");
			digits += args[i+1].replaceAll("\\s*[\\D^0|^9]*\\s*", "");
		}

		if(!(digits.length() % 2 == 0)){
			System.out.println("Invalid input.");
			System.exit(-1);
		}

		for(int i =0; i < digits.length();i+=2){
				x = (int)( digits.charAt(i) - '0');
				y = (int)( digits.charAt(i+1) - '0');
				System.out.println(x + " " + y);
				this.addPiece(new Queen(x,y));
		}

		checkForQueenAttack(this.pieces);
		printBoard();
		printQueensAttacked();
		clearBoard();
	}
	
	public void program_attack_queens(int ... is){
		int x, y;
		if(is.length % 2 == 0 )
		try{
			for(int i =0; i < is.length; i+=2){
				x = is[i];
				y =  is[i+1];
				this.addPiece(new Queen(x,y));
			}
		}catch(java.lang.RuntimeException e1){
			System.out.println("You tried to access a tile out of bounds...");
			System.err.println(e1.toString());
		}
		checkForQueenAttack(this.pieces);
		printBoard();
		printQueensAttacked();
		clearBoard();
	}
	
	public void printQueensAttacked(){
		System.out.println(attacked + " Queens Attacked.");
		System.out.println();
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
		//check for a duplicate piece
		if(board[a.location.x][a.location.y] instanceof Blank){
			board[a.location.x][a.location.y] = a;	
			pieces.add(board[a.location.x][a.location.y]);

		}
	}
	
	public void removePiece(int a, int b){
		// to keep with specifications input 1,1 is the first tile
		int x = a - 1;
		int y = b - 1;
		
		for(int i = 0 ; i < pieces.size(); i++){
			if(pieces.elementAt(i).equals(board[x][y])){
				pieces.remove(i);
				board[x][y] = new Blank(x,y);
			}
		}
		//redraw board
		this.resetBlanks();
		this.checkForQueenAttack(pieces);
	}
	
	public void resetBlanks(){
		for(int i =0; i < board.length; i++){
			for(int j =0; j < board.length; j++){
				if(board[i][j] instanceof Blank){
					board[i][j].symbol = '_';
				}
			}
		}
	}
	
	public void clearBoard(){
		for(int i =0; i < board.length; i++){
			for(int j =0; j < board.length; j++){
				board[i][j] = new Blank(i,j);
			}
		}
		this.pieces.clear();
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
		//unfortunately we have to check if there are any blanks after we place each queen.
		int middle = (int)(Math.ceil(board.length / 2.0));
		this.addPiece(new Queen(middle,middle));
		this.checkForQueenAttack(pieces);
		if(checkBoardForBlanks()) return;
		this.addPiece(new Queen(board.length,board.length));
		this.checkForQueenAttack(pieces);
		if(checkBoardForBlanks()) return;
		
		int i = 1;
		while(!(checkBoardForBlanks())){

			this.addPiece(new Queen(middle - i,middle));
			this.checkForQueenAttack(pieces);
			if(checkBoardForBlanks()) return;
		
			this.addPiece(new Queen(middle + i, middle));
			this.checkForQueenAttack(pieces);
			i++;
		}
	}
	
	public void checkForQueenAttack(Vector<Piece> pieces){
		for(int z = 0; z < pieces.size(); z++){
			Piece a = pieces.get(z);
			
			for(int i = 0; i < board.length ; i++){
			//vertical checks
				if(board[i][a.location.y] instanceof Queen) {
					if(!(i == a.location.x)) attacked++;
				}else board[i][a.location.y].symbol = 'x';
			//horizontal checks
				if(board[a.location.x][i] instanceof Queen) {
					if(!(a.location.y == i)) attacked++;
				}else board[a.location.x][i].symbol = 'x';
			}
			//diagonal checks
			//contains the points of all the diagonals available for the queen to attack
			Vector<Point> diagonals = new Vector<Point>();
			for(int i =0; i < board.length; i++){
				int x = a.location.x - i;
				int x1 = a.location.x + i;
				int y = a.location.y - i;
				int y1 = a.location.y + i;
				
				if(x >= 0 && y >= 0) diagonals.add(new Point(x, y)) ;  // + +
				if((x1 < board.length && y1 < board.length)) diagonals.add(new Point(x1, y1)); // --	
				if(x1 < board.length && y >= 0) diagonals.add(new Point(x1, y));  // + -							
				if(x >= 0 && y1 < board.length) diagonals.add(new Point(x, y1));  // - +
			}
		
			for(int i =0; i < diagonals.size(); i++){
				Point abc = diagonals.elementAt(i);
				if(abc.x == a.location.x && abc.y == a.location.y) continue;
				//checks to see if there is a blank piece ( we do not want to change the symbol of any other chess piece)
				if((board[abc.x][abc.y] instanceof Blank)){ 
					board[abc.x][abc.y].symbol = 'x';
				}else {
					// check if the piece that is being attacked is actually a queen and not any others.
					if(board[abc.x][abc.y] instanceof Queen){ 
					attacked++; 
					}
				}

			}
		}
		//------------END OF CLASS------------//
	}
}