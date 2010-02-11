package lpf.gui;

import org.junit.Before;

import junit.framework.TestCase;


public class MessageDialogTest extends TestCase {

	@Before
	public void setUp(){
		MessageDialog md = new MessageDialog();
	}
	
	public void testNo() throws Exception
	{
		assertFalse(MessageDialog.showAlertDlg("Please select No"));	
	}
	
	public void testYes() throws Exception
	{
		
		assertTrue(MessageDialog.showAlertDlg("Please select Yes"));	
	}
	
	
}
