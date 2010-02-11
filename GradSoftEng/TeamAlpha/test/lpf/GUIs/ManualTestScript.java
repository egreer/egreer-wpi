package lpf.GUIs;

import static org.junit.Assert.*;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import lpf.configuration.SudokuAlphaGameConfiguration;
import lpf.gameLaunchers.SudokuAlpha;


public class ManualTestScript implements InteractiveTester{
	
	/** harness. */
	TestHarness harness; 
	
	/** determines if we are in a test case. */
	boolean active = false;
	
	/** success of last test case. */
	boolean result = false;
	 
	/** Active semaphore. */
	Object semaphore = new Object();
	
	@Before
	public void SetUp(){
		
		harness = new TestHarness();
		harness.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				testCaseDone(false);
				tearDown();
			}

			public void windowClosed(WindowEvent e) {
				testCaseDone(false);
				tearDown();
			}
		});
			
		new SudokuAlpha().run();
		SudokuAlphaGameConfiguration.getInstance().getGUI().myMainPanel.setLocation(0, 150);
		harness.add(SudokuAlphaGameConfiguration.getInstance().getGUI().myMainPanel);
		harness.setJMenuBar(new MainGUIMenu());
		
		harness.setAlwaysOnTop(false);
		harness.setVisible(true);
		
	}
	
	@After
	public void tearDown(){
		harness.setVisible(false);
	}
	
	
	@Test
	public void testManual(){
		harness.setTest (this, "Manual Script");
		harness.setInstructions ("Please run the \"Testing Script.docx\" located in the files folder");
		
		active = true;
		
		while (active) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// do nothing.
			}
		}
		
		assertTrue(result);
	}

	@Override
	public void testFails() {
		testCaseDone (false);
	}

	@Override
	public void testSucceeds() {
		testCaseDone (true);
	}
	
	/**
	 * When the test case has completed, invoke this method with success status.
	 * @param result
	 */
	public void testCaseDone(boolean result) {
		synchronized (semaphore) {
			active = false;
			this.result = result;
		}
	}
}
