package lpf.GUIs;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.LayoutManager;

/**
 * 
 * The layout manager for the GUI
 * 
 * @author Nik Deapen
 *
 */
public class MainGUILayoutManager implements LayoutManager {

	public static final String MAIN_PANEL = "mainPanel";
	public static final String CONTROL_BUTTON = "controlButton";
	public static final int BUTTON_WIDTH = 130;
	public static final int BUTTON_HEIGHT = 30;
	public static final int TOP_BUTTON_PADDING = 50;
	public static final int BUTTON_PADDING = 15;
	public static final int LEFT_BUTTON_PADDING = 20;
	public static final int GRID_SIZE = 495;
	 
	/**
	 * Add a layout component to the GUI Layout 
	 * 
	 * @param s				type of component
	 * @param comp			the component being added
	 */
	@Override
	public void addLayoutComponent(String s, Component comp) {
		if (s.equals(MAIN_PANEL))
			setMainPanel(comp);
		else
			addControlButton(comp);
	}
	
	int numButtons = 0;
	
	/**
	 * Adds a control button to the GUI layout for the given component
	 * 
	 * @param comp			the component for which a control button is added 
	 */
	private void addControlButton(Component comp) {
		if (comp instanceof JButton){
			JButton b = (JButton)comp;
			b.setSize(BUTTON_WIDTH,BUTTON_HEIGHT);
			b.setLocation(LEFT_BUTTON_PADDING, TOP_BUTTON_PADDING + (BUTTON_HEIGHT+BUTTON_PADDING) * numButtons);
			numButtons++;
		}
		else
			throw new IllegalArgumentException();
	}

	
	/**
	 * Sets the GUI Layout's main panel
	 * 
	 * @param comp			the component that will be the main panel
	 */
	private void setMainPanel(Component comp) {
		comp.setLocation(LEFT_BUTTON_PADDING*2+BUTTON_WIDTH, TOP_BUTTON_PADDING/2);
		comp.setSize(GRID_SIZE,GRID_SIZE);
	}



	@Override
	public void layoutContainer(Container arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Dimension minimumLayoutSize(Container arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dimension preferredLayoutSize(Container arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeLayoutComponent(Component arg0) {
		// TODO Auto-generated method stub
		
	}

}
