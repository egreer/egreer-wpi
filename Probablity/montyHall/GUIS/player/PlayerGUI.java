package montyHall.GUIS.player;
import java.awt.BorderLayout;
//import java.awt.Container;
//import java.awt.Event;
//import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

//import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.JTextField;

/**
 * @author galia
 *
 */
public class PlayerGUI extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	int NUMDOORS = 5; //this MUST be greater than or equal to 3 
	int step =1;  //this keeps track of what stage of the game we are on
	              //each step opens a new door
				  //if step < (NUMDOORS - 1), the host will open a door
				  //if step = (NUMDOORS -1) then the players's pick is opened
	Door[] doors = new Door[NUMDOORS];
	
	int prizeDoor;  //keeps track of the door with the prize
	//initialize array of doors
	
	//this displays the host's dialouge
	JLabel lblWords = new JLabel();
	
	
	/**
	 * Runs the Program 
	 */
	public static void main(String[] args){		 
		new PlayerGUI();
	}
	
	
	/*
	 * Luanches GUI
	 */
	public PlayerGUI(){
		//set up the frame
		super("Player Mode");		
		this.setSize(500, 300);		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		this.setLocationRelativeTo(null); //centers it
		this.setLayout(new GridLayout());
		
		//set up the doors
		for (int i  = 0 ; i < NUMDOORS; i++){
			doors[i] = new Door();  //create door
			add(BorderLayout.CENTER, doors[i].button); //add it to the GUI
			doors[i].button.addActionListener(this); //listen for a click
		}
		
		placePrize(); //pick one of the doors to have the prize
	    
		//display dialouge	
		add(BorderLayout.SOUTH, lblWords);
		lblWords.setText(" Pick a door");
		System.out.println("Pick a door.");
		
		//make it all visable
		setVisible(true);       
	}

	
	/**
	 * This opens doors depending on which step of the game we are on
	 */
	public void actionPerformed(ActionEvent e){
		//if there are X doors then there are X-1 steps to the game
		//the int first X-2 steps, the player chooses doors and the host opens doors 
		if (step < (NUMDOORS-1)){			
			
			//find which door was picked 
			for (int i  = 0 ; i < NUMDOORS; i++){
				if (e.getSource().equals(doors[i].button)){
					
					//display dialouge
					System.out.println("You Picked Door #" + ( 1 + i));
					
					//host chooses a door to open
					choose(i);
					
					//display dialouges					
					System.out.println("Pick again.  You can stay  with your first pick or switch");
					lblWords.setText( " Pick again.");
					step++;  ///PROGRESS TO THE NEXT STEP
					return;					
				}
			}			
			
		}
		
		//this is the ending step.
		//on the last step, the winner picks a door
		//and the host opens it
		if (step == (NUMDOORS-1)){
			
			//find which door was picked
			for (int i = 0; i< NUMDOORS; i++){
				if (e.getSource().equals(doors[i].button)&& !doors[i].isOpen){
					
					//open the door
					doors[i].open();
					
					//display dialouge depending on whether or not it has a prize
					if (doors[i].hasPrize){
						lblWords.setText(" Winner!");
						System.out.println("We have a Winner!");
					}else{
						lblWords.setText(" You Loose");
						System.out.println("Sorry, you loose.");
					}
					
					System.out.println("Thank you for playing.");
					
					step++; ///PROGRESS TO THE NEXT STEP
				}
				
			}			
		}
		
		
		//Now the game is over
		//if they click again let them know that it's over 
		if (step >= (NUMDOORS)){
			lblWords.setText("Game Over");
			System.out.println("Game Over.");
		}
		
		
	}

	/**	
	 *places the prize in a random door
	 */ 
	public void placePrize(){
		Random foo = new Random();
		prizeDoor =(int) (foo.nextDouble()*(NUMDOORS-1));		
		doors[prizeDoor].hasPrize = true;
	}
	
	
	/**
	 * 
     *Randomly open the door that the host would open, assuming that:
          The host will never open the door with the car behind it, and
          The host will never open the door picked by the player.
	 * @param door door the player picked
	 */
	public void choose(int playersPick){
		 //there are 2 cases
		 //i) the picked door does not contain a prize
		 //   in that case open the other door w/o a prize
		 //ii) the picked door does contain the prize
		//    then randomly choose one of the other doors;
		//Random generator = new Random();
		
		// Checks to make sure that playerDoor, and correctDoor are vaild. 
			
		// Door that will be removed

		
		Random foo = new Random();
		
		// Door that will be removed
		int removedDoor;
		
		// Loop that makes sure the returned isn't the playerDoor or the correctDoor 
		while (true){
			removedDoor = (int) (foo.nextDouble()*(NUMDOORS));			
			if ( (removedDoor != playersPick) && (removedDoor != prizeDoor) && !doors[removedDoor].isOpen){
				doors[removedDoor].open();
				System.out.println("Lets See What's Behind Door #" + ( 1 + removedDoor) );
				return;
			}
		}		
		
		
	}
}
