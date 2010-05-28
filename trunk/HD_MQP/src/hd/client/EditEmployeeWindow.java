package hd.client;

import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.google.gwt.user.client.Element;

/** GUI component for a manager to edit a particular employee in a window
 * 
 * @author Eric Greer (egreer@alum.wpi.edu)
 * @author Jason Codding (jcodding@alum.wpi.edu)
 * WPI Helpdesk MQP 2009-2010
 *
 */
public class EditEmployeeWindow extends LayoutContainer{
	Long key;
	final Window window = new Window();

	EmployeeProfileForm epf; 

	/** Constructor for the window 
	 * 
	 * @param key	The key of the employee
	 */
	EditEmployeeWindow(Long key){
		super();
		this.key = key;
		this.epf = new EmployeeProfileForm(key);
	}

	/*
	 * (non-Javadoc)
	 * @see com.extjs.gxt.ui.client.widget.LayoutContainer#onRender(com.google.gwt.user.client.Element, int)
	 */
	@Override  
	protected void onRender(Element parent, int pos) {  
		super.onRender(parent, pos);  
		setLayout(new FlowLayout(10));  

		window.setSize(800, 700);
		//window.setAutoHeight(true);
		//window.setAutoWidth(true);
		window.setPlain(true);  
		window.setModal(true);  
		window.setBlinkModal(true);  
		window.setHeading("Edit Employee");  
		window.setLayout(new FitLayout());  

		window.add(this.epf);
		Info.display("Add EPF", "Success");
		window.show();
	}
}