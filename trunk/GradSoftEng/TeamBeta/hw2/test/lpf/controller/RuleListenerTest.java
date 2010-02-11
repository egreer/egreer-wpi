package lpf.controller;

import java.awt.event.ActionEvent;

import junit.framework.TestCase;
import lpf.gui.KenKenGUI;

public class RuleListenerTest extends TestCase {
	/*
	 * Test for 'RuleListener(KenKenGUI)'
	 */
	public void testRuleListener() {
		KenKenGUI gui = new KenKenGUI();
		RuleListener listener = new RuleListener(gui);
		assertNotNull(listener.gui);
	}

	/*
	 * Test for 'actionPerformed(ActionEvent e)'
	 */
	public void testActionPerformed() {

		KenKenGUI gui = new KenKenGUI();
		RuleListener listener = new RuleListener(gui);

		listener.actionPerformed(new ActionEvent(this, 1, ""));
	}
}
