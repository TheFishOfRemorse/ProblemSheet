
public class Problem10 {
	//Test cases
	/*	1 1 1 2 1 3 1 4 1 5 1 6 1 7 1 8
		2 1 2 2 2 3 2 4 2 5 2 6 2 7 2 8
		3 1 3 2 3 3 3 4 3 5 3 6 3 7 3 8
		4 1 4 2 4 3 4 4 4 5 4 6 4 7 4 8
		5 1 5 2 5 3 5 4 5 5 5 6 5 7 5 8
		6 1 6 2 6 3 6 4 6 5 6 6 6 7 6 8
		7 1 7 2 7 3 7 4 7 5 7 6 7 7 7 8
		8 1 8 2 8 3 8 4 8 5 8 6 8 7 8 8 |
	*/
	//	1 2 4 5 |
	//  1 1 1 1 | 
	//  1 1 1 2 1 1 1 2 |
	public static void program(){
		Board chess_board = new Board();
		chess_board.program_attack_queens();
	}
	
	public static void program(int ...is){
		Board chess_board = new Board();
		chess_board.program_attack_queens(is);
	}
	
	public static void program(String[] args){
		Board chess_board = new Board();
		int[] is = new int[args.length];
		System.out.println("Enter at least one Queen's coordinates e.g, 1 2 4 5");
		/*
		for(int i =0; i < is.length;i++){
			is[i] = Integer.parseInt(args[i]);
		}
		chess_board.program_attack_queens(is);
		*/
		chess_board.program_attack_queens(args);

	}
	
}
