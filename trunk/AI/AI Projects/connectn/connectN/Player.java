package connectN;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * The player class interacts with the referee, or other players through standard IO.
 * It executes moves and processes initial setup information.
 *
 * @author Eric Greer (egreer)
 * @author Samuel LaFleche (shl)
 *
 */
public class Player {


	/** Main function for the program
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		int oppMove, myMove;

		System.out.println("Name");
		System.out.flush();


		//Configures the Game
		String [] gameConfig = input.readLine().split(" ");
		Game game = new Game(gameConfig);

		//Starts the game
		while (true){

			//If my turn
			if(game.turn == 0){
				//Call to Search for best move
				myMove = game.bestMove(1);
				System.err.println("myMove was: " + myMove);
				game.updateBoard(myMove, 1);

				System.out.print(myMove + "\n");
				System.out.flush();

			}

			else{
				//Wait for next move
				String given = input.readLine();
				System.err.println("opp move was: " + given);
				int i = given.indexOf(" ");
				if (i >= 0){
					given = (String) given.subSequence(0, i);
				}

				oppMove = Integer.parseInt(given);
				if (oppMove < 0) break;
				game.updateBoard(oppMove, 2);
			}

			game.advanceTurn();

		}
	}



}
