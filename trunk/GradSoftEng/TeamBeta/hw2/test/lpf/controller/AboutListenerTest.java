package lpf.controller;

import java.awt.event.ActionEvent;

import junit.framework.TestCase;

import lpf.gui.KenKenGUI;

public class AboutListenerTest extends TestCase {
	KenKenGUI gui;
	
	public void setUp() {
		gui = new KenKenGUI();
		
	}

	/*
	 * Test for 'actionPerformed(ActionEvent e)'
	 */
	public void testActionPerformed() {
		AboutListener listener = new AboutListener(gui);
		
		assertNotNull(listener.gui);

		listener.actionPerformed(new ActionEvent(this, 1, ""));
	}
}
