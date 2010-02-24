package montyHall.GUIS.simulation;

import java.awt.BorderLayout;
import java.awt.Container;
//import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
//import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author galia
 *
 */
public class SimGUI extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel lblNumDoors = new JLabel("Number of Doors: ");
	JLabel lblNumSims = new JLabel("Number of Trials: ");	
	JTextField txtNumDoors = new JTextField("3",  10);  //the 10 means up to 10 
	JTextField txtNumSims = new JTextField("1", 10);   //chars can be added.
	JLabel lblWinsSwitch = new JLabel("Wins When Switching: ");
	JLabel lblWinsStay = new JLabel("Wins When Staying: ");
	JLabel lblVarWinsSwitch = new JLabel("");
	JLabel lblVarWinsStay = new JLabel("");
	JButton btnSimulate = new JButton("Simulate");
	JButton btnExit = new JButton("Exit");
	
	/**
	 * Runs the Program 
	 */
	public static void main(String[] args) {		 
		new SimGUI();
    }
	
	
	
	public SimGUI(){
		//set up the frame
		super("Simulation Mode");		
		this.setSize(500, 300);		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		this.setLocationRelativeTo(null); //centers it		
		
		//make pane
		Container pane = getContentPane();
	    pane.setLayout(new GridLayout(5,2));
	    
	    //adding labels
	    pane.add(lblNumDoors);
	    pane.add(txtNumDoors);
	    
	    pane.add(lblNumSims);
	    pane.add(txtNumSims);
	    
	    pane.add(lblWinsSwitch);
	    pane.add(lblVarWinsSwitch);
	    
	    pane.add(lblWinsStay);
	    pane.add(lblVarWinsStay);
	    
	    pane.add(btnSimulate);
	    pane.add(btnExit);

		
	    //set the buttons to listen for clicks.
		btnSimulate.addActionListener(this);
		btnExit.addActionListener(this);
		
		setVisible(true);       
	}


	public void actionPerformed(ActionEvent e){
		
		//SIMULATE BUTTON CODE
		if (e.getSource().equals(btnSimulate)){
			//check input
			
			
			//RUN SIMULATE FUNCTION			
			int[] ar = new int[2];
			
			try{
			
			ar = simulate(Integer.parseInt(txtNumDoors.getText()) , 
					      Integer.parseInt(txtNumSims.getText()));
			}catch(Exception e1){
                //Create a new JFrame
				JFrame errorBox = new JFrame("Error");
				errorBox.setSize(220, 100);				
				errorBox.setLocationRelativeTo(null);
				errorBox.setLayout(new FlowLayout());
				//place and center label
				errorBox.add(BorderLayout.CENTER, new JLabel("Positive Integers Only"));				
				errorBox.setVisible(true);
			}
			
			lblVarWinsSwitch.setText(((Integer)ar[0]).toString());
			lblVarWinsStay.setText(((Integer)ar[1]).toString());
		}		
		
		//EXIT BUTTON CODE
		if (e.getSource().equals(btnExit)){
			System.exit(0);
		}
	}
	
      /**
       * @param doors - the number of doors  (should be 3 and up)
       * @param trials - the number of trial to simulate (1 and up)
       * @return results -  int[2] with the 1st element the # of wins w/ 
       * switch and the second is the # of wins w/o switching.
       * @throws exception when bad data is entered
       * 
    */
	public int[] simulate(int numDoors, int numSims){
		int[] ar = new int[2];		
		ar[0] = 1337;
		ar[1] = 7008;
		return ar;
	}
	
	
}
