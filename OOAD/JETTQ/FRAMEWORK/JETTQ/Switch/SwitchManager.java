/*
 * Created on Oct 10, 2008
 * 
 * JETTQ's framework for Sun Microsystem's SPOT (Small Programmable Object Technology).
 * Built for CS4233 in A-term of 2008.
 *
 * Copyright (C) 2008, Tim Navien, Eric Greer, Jason Codding, 
 * Qian Wei, Tyler Flaherty, Worcester Polytechnic Institute, JETTQ@wpi.edu.
 * 
 * The program is provided under the terms and conditions of the Eclipse Public License Version 1.0
 * ("EPL"). A copy of the EPL is available at http://www.eclipse.org/org/documents/epl-v10.php.
 */
package JETTQ.Switch;

import java.util.Vector;

import JETTQ.JETTQManager;

import com.sun.spot.sensorboard.peripheral.ISwitch;
import com.sun.spot.sensorboard.peripheral.ISwitchListener;

/**
 * Manager class for the SPOT's switches
 * @author JETTQ [jettq@wpi.edu]
 *
 */
public class SwitchManager implements ISwitchListener{

	private final static SwitchManager switchmanager = new SwitchManager(); //Fix?
	private ISwitch[] switches; 
	private int numSwitches;
	private Vector switchObservers = new Vector();
	
	/**
	 * Default Constructor
	**/
	 SwitchManager(){
			JETTQManager.decho("Initializing SwitchManager");
			switches = new ISwitch[0];
			numSwitches = 0;
	 }
	
	/**
	 * Protected Contructor
	 * @param switches an array of ISwitches
	 */
	protected SwitchManager(ISwitch[] switches)
	{
		JETTQManager.decho("Initializing SwitchManager");
		this.switches = switches;
		numSwitches = switches.length;
		this.registerSwitches();
	}
	
	/**
	 * Configures the manager for the given switches
	 * @param switches An array of ISwitches on the SPOT
	 * @return an instance of SwitchManager
	 */
	static public SwitchManager configureInstance(ISwitch[] switches)
	{
		JETTQManager.decho("Configuring SwitchManager");
		switchmanager.switches = switches;
		switchmanager.numSwitches = switches.length;
		switchmanager.registerSwitches();
		return switchmanager;
	}
	
	/**
	 * Sets up the manager to listen to the switches.
	 */
	private void registerSwitches(){
		for(int i = 0; i < numSwitches; i++){
			switches[i].addISwitchListener(this);
			JETTQManager.decho("Registered Switch:" + i);
		}
	}
	
	/**
	 * Gets the switches being managed
	 * @return An array of ISwitches
	 */
	public ISwitch[] getSwitches(){
		return switches;
	}
	
	/**
	 * Gets a specific switch
	 * @param idx Index of the switch to get
	 * @return ISwitch at the given index
	 */
	public ISwitch getSwitch(int idx){
		return switches[idx];
	}
	
	/**
	 * Checks if a specific switch is pressed
	 * @param idx Index of the switch to check
	 * @return true if the switch is pressed, false otherwise
	 */
	public boolean isSwitchPressed(int idx){
		return switches[idx].isClosed();
	}
	
	/**
	 * Checks if a given switch is pressed
	 * @param theswitch An ISwitch object to check
	 * @return true if the ISwitch is pressed, false otherwise
	 */
	public boolean isSwitchPressed(ISwitch theswitch)
	{
		return theswitch.isClosed();
	}
	
	/** Observation Functions **/
	
	/**
	 * Registers the observer for the switch
	 */
	public void registerObserver(ISwitchObserver iso) {
		this.switchObservers.addElement(iso);
	}
	
	/**
	 * Removes the observer for the switch
	 * @param iso the switch observer to be removed
	 */
	public void removeObserver(ISwitchObserver iso) {
		this.switchObservers.removeElement(iso);
	}
		
	/**
	 * Notifies the observers of the switches
	 * @throws Exception if observers aren't properly notified
	 */
	public void notifyObservers(ISwitch updatedSwitch) throws Exception{
		synchronized (this){
		for (int i = 0; i < this.switchObservers.size(); i++) {
			((ISwitchObserver)this.switchObservers.elementAt(i)).update(updatedSwitch);
			}
		}
	}
	
	/**
	 * Notifies the observers that the switch was pressed
	 * @param sw ISwitch that was pressed
	 */
	public void switchPressed(ISwitch sw) {
		JETTQManager.decho("Switch " + sw.toString() + " Pressed");
		try {
			this.notifyObservers(sw);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Notifies the observers that the switch was released.
	 * @param sw the ISwitch that was released.
	 */
	public void switchReleased(ISwitch sw) {
		JETTQManager.decho("Switch " + sw.toString() + " Released");
		try {
			this.notifyObservers(sw);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
