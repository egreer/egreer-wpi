package tictactoe;

import java.util.Scanner;

/**
 * A program that plays Tic Tac Toe between two players using keyboard input
 *  
 * @author heineman
 */
public class Program {

	/** Shared input. */
	static Scanner sc;
	
	/**
	 * Select an empty Space in the game board, or return null if failed to do so.
	 * 
	 * @param game   The tic tac toe game
	 * @param mark   The player maker to be made.
	 * @return       null if no Space selected; otherwise, returns the Space representing desired move. 
	 */
	public static Space selectMove (TicTacToe game, char mark) {
		System.out.println ("Player " + mark + " make your move: two integers in range 1..3 representing [row column]");
		while (sc.hasNext()) {
			try {
				int r = sc.nextInt()-1;
				int c = sc.nextInt()-1;
				if (game.isSpaceEmpty (r, c)) {
					return new Space (r, c);
				} else {
					System.out.println ("That space is taken");
				}
				
			} catch (Exception e) {
				System.out.println ("Invalid input. Try entering two integers between 1 and 3.");
				sc.nextLine();   // drain remaining bad input
			}
		}
		
		// some unusual event prevented us from getting the Space from the user.
		return null;
	}
	
	/**
	 * Run Program.
	 */
	public static void main(String[] args) {
		sc = new Scanner (System.in);
		
		TicTacToe game = new TicTacToe();
		
		while (!game.isGameOver()) {
			// show current state.
			System.out.println (game.toString());
			
			// Have current player make a move. If player declines to make move (or if move
			// cannot be made for some reason) stop now and end game
			char cur = game.getCurrentPlayer();
			Space s = selectMove(game, cur);
			
			if (s == null) {
				// can't make move! forfeit!
				game.forfeit();
			} else {
				game.makeMove(s.getRow(), s.getColumn());
			}
		}
		
		// return final status of game (as String).
		System.out.println (game.getFinalStatus());
		System.out.println ();
		System.out.println (game.toString());

	}

}
