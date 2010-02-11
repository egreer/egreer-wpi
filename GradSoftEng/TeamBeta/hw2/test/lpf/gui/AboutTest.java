package lpf.gui;

import junit.framework.TestCase;

public class AboutTest extends TestCase {

	public void testGUI() throws Exception
	{
//		KeyEvent e1 = new KeyEvent(about, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), KeyEvent.ALT_DOWN_MASK, KeyEvent.VK_ALT, KeyEvent.CHAR_UNDEFINED, KeyEvent.KEY_LOCATION_LEFT);
//		KeyEvent e2 = new KeyEvent(about, KeyEvent.KEY_PRESSED, System.currentTimeMillis()+100, KeyEvent.ALT_DOWN_MASK, KeyEvent.VK_F4, KeyEvent.CHAR_UNDEFINED, KeyEvent.KEY_LOCATION_STANDARD);
//		about.dispatchEvent(e1);
//		about.dispatchEvent(e2);
		
		new About(new KenKenGUI());
		
//		Robot robot = new Robot();
//		robot.delay(5000);
//		robot.keyPress(KeyEvent.VK_ALT);
//		robot.keyPress(KeyEvent.VK_F4);
//		robot.keyRelease(KeyEvent.VK_ALT);
	}

}
