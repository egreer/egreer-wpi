package connectN;

import java.util.Enumeration;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Determines the best move for a game of connect n by determining the next level of a tree containing board states and comparing their hueristic values.
 * Uses alpha-beta pruning to eliminate some poor choices without having to evaulate them.
 * 
 * @author egreer & shl
 *
 */
public class MinMax {
	
	/**
	 *  Determines the best move for a game of connect n by determining the next level of a tree containing board states and comparing their hueristic values.
	 *  Uses alpha-beta pruning to eliminate some poor choices without having to evaulate them.
	 * 
	 * @param n			the root of the tree of board states to search
	 * @param player	the player who's move it is
	 * @param bestknown	the best known move so far (null if none is known)
	 * @return			an object array that contains the move to get to a new state and the heuristic value of that state, in that order if, the state is the next best perceived state
	 */
	public Object[] nextLevel(DefaultMutableTreeNode n, int player, Heuristic bestknown){
		int move = -1;
		Heuristic value = new Heuristic(new int[((Board)n.getUserObject()).getConnect()*2]);
		int nextplayer = 1;
		if(player == 1){
			nextplayer = 2;
		}
		if(n.getChildCount() == 0){
			int length = ((Board) n.getUserObject()).getBoard()[0].length;
			for(int i = 0; i < length; i++){
				Board newState = ((Board) n.getUserObject()).updateBoard(i, player);
				if(newState != null){
					DefaultMutableTreeNode m = new DefaultMutableTreeNode(newState);
					n.add(m);
					Heuristic mvalue = ((Board)m.getUserObject()).getHeuristic();
					if(move == -1){
						move = i;
						value = mvalue;
					}
					else if(mvalue.getValues()[((Board)n.getUserObject()).getConnect()*2-1] != 0){
							move = i;
							value = mvalue;
						if(bestknown != null && mvalue.getValues()[((Board)n.getUserObject()).getConnect()*2-1] < 0){
							return null;
						}
					}
					else if(player == 1 && mvalue.compareTo(value) > 0){
						move = i;
						value = mvalue;
					}
					else if(player == 2 && mvalue.compareTo(value) < 0){
						move = i;
						value = mvalue;
					}
					if(bestknown != null && mvalue.compareTo(bestknown) < 0 && player == 2){
						return null;
					}
				}
			}
			Object[] returnable = new Object [2];
			returnable[0] = (Integer) move;
			returnable[1] = value;
			return returnable;
		}
		Enumeration<DefaultMutableTreeNode> e = n.children();
		Heuristic best = bestknown;
		while(e.hasMoreElements()){
			Object[] check = nextLevel(e.nextElement(), nextplayer, best);
			if(check != null){
				value = (Heuristic) check[1];
				best = value;
				move = (Integer)check[0];
			}
		}
		Object[] returnable = new Object [2];
		int prevmove = ((Board)n.getUserObject()).getLastMove();
		if(prevmove != -1){
			returnable[0] = prevmove;
		}else{
			returnable[0] = move;
		}
		returnable[1] = value;
		return returnable;
	}
}
