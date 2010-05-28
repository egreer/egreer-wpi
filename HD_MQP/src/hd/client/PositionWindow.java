package hd.client;

import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.google.gwt.user.client.Element;

/** The GUI window wrapper for the the positions form
 * 
 * @author Eric Greer (egreer@alum.wpi.edu)
 * @author Jason Codding (jcodding@alum.wpi.edu)
 * WPI Helpdesk MQP 2009-2010
 *
 */
public class PositionWindow extends Window{
	Long key;
	
	PositionForm pf; 

	/** The constructor for positions window
	 * @param key	null = create mode
	 * @param mode	Create, Edit, View
	 */
	PositionWindow(Long key, String mode){
		super();
		this.key = key;
		this.pf = new PositionForm(key);
		if (mode.equals("Edit")) pf.setEditMode();
		else if (mode.equals("Create")) pf.setCreateMode();
		else pf.setViewMode();
		
		this.setHeading(mode + " Position");

		this.setModal(true);  
		this.setBlinkModal(true); 
		this.setShadow(false);
		
		this.setLayout(new FitLayout());
		this.add(this.pf);
		this.setAutoHeight(true);
		this.setAutoWidth(true);
		//this.setSize(350, 200);
	}

	/*
	 * (non-Javadoc)
	 * @see com.extjs.gxt.ui.client.widget.Window#onRender(com.google.gwt.user.client.Element, int)
	 */
	@Override  
	protected void onRender(Element parent, int pos) {  
		super.onRender(parent, pos);  
		setLayout(new FlowLayout(10));  
	}
}