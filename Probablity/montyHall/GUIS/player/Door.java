package montyHall.GUIS.player;

import javax.swing.JButton;



public class Door {
	boolean isOpen;
	boolean hasPrize;
	JButton button = new JButton();
	
	Door(){
		isOpen = false;
		hasPrize = false;
		button.setText("Closed");
		
	}
	
	
	void open(){
		isOpen = true;
		if(hasPrize)button.setText("Prize!");
		else button.setText("No Prize.");
	}
	
	/**
	 * returns file path for doors picture
	 * if i have time I'll make it return a picture
	 */
    String getPic(){
    	if (!isOpen) return "CLOSED";
    	if (hasPrize) return "CAR";
    	else return "GOAT";    
    }
	

	
	
	
	
}
