package hd.client;

import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.google.gwt.user.client.Element;

/** Window container for a manager to manage the the Positions of an employee
 * 
 * @author Eric Greer (egreer@alum.wpi.edu)
 * @author Jason Codding (jcodding@alum.wpi.edu)
 * WPI Helpdesk MQP 2009-2010
 *
 */
public class EmployeePositionsWindow extends LayoutContainer{
	Long key;
	final Window window = new Window();

	EmployeePositionManagementForm epmf; 

	EmployeePositionsWindow(Long key){
		super();
		this.key = key;
		this.epmf = new EmployeePositionManagementForm(key);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.extjs.gxt.ui.client.widget.LayoutContainer#onRender(com.google.gwt.user.client.Element, int)
	 */
	@Override  
	protected void onRender(Element parent, int pos) {  
		super.onRender(parent, pos);  
		setLayout(new FlowLayout(10));  

		window.setSize(600, 300);
		//window.setAutoHeight(true);
		//window.setAutoWidth(true);
		window.setPlain(true);  
		window.setModal(true);  
		window.setBlinkModal(true);  
		window.setHeading("Add/Remove Employee Positions");  
		window.setLayout(new FitLayout());  

		window.add(this.epmf);
		Info.display("Add EPMF", "Success");
		window.show();
	}
}