package hd.client;

import hd.client.ui.timesheet.EmployeeTimesheetListing;

import java.util.Iterator;
import java.util.List;

import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.TabPanelEvent;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Window;

/** The tab layout an employee sees after login
 * 
  * @author Eric Greer (egreer@alum.wpi.edu)
 * @author Jason Codding (jcodding@alum.wpi.edu)
 * WPI Helpdesk MQP 2009-2010
 *
 */
public class EmployeeTabLayout extends LayoutContainer {
	private VerticalPanel vp;  
	private EmployeeProfileForm epf;
	private final ChangePasswordForm cpf = new ChangePasswordForm(); 
	private EmployeeTimesheetListing etl = new EmployeeTimesheetListing();
	
	/** Constructor for the tab layout
	 * 
	 * @param epf	Takes in an employee profile form
	 */
	public EmployeeTabLayout(EmployeeProfileForm epf) { 
		vp = new VerticalPanel();  
		vp.setSpacing(10);  
		this.epf = epf;
	}  

	/*
	 * (non-Javadoc)
	 * @see com.extjs.gxt.ui.client.widget.LayoutContainer#onRender(com.google.gwt.user.client.Element, int)
	 */
	@Override  
	protected void onRender(Element parent, int index) {  
		super.onRender(parent, index);   
		createTabLayout(); 
		tabs.setHeight(1000); //TODO set height another way
		add(vp);  
	}
	final TabPanel tabs = new TabPanel();  
	
	private void createTabLayout() {  
		//FormData formData = new FormData("100%");  
		
		tabs.setWidth(800);
		
		TabItem homeScreen = new TabItem();  
		homeScreen.setStyleAttribute("padding", "10px");  
		homeScreen.setText("Home");  
		homeScreen.setLayout(new FormLayout());
		tabs.add(homeScreen);
		
		final TabItem userProfile = new TabItem();  
		userProfile.setStyleAttribute("padding", "10px");  
		userProfile.setText("User Profile");  
		userProfile.add(epf);
		tabs.add(userProfile);


		TabItem timesheets = new TabItem();  
		timesheets.setStyleAttribute("padding", "10px");  
		timesheets.setText("Timesheets");  
		timesheets.setLayout(new FormLayout());
		timesheets.add(etl);
		tabs.add(timesheets);

/*		TabItem reviews = new TabItem();  
		reviews.setStyleAttribute("padding", "10px");  
		reviews.setText("Reviews");  
		reviews.setLayout(new FormLayout());  
		tabs.add(reviews);*/

		TabItem settings = new TabItem();  
		settings.setStyleAttribute("padding", "10px");  
		settings.setText("Settings");  
		settings.setLayout(new FormLayout());
		settings.add(cpf);
		tabs.add(settings);

		TabItem logoutTab = new TabItem("Logout");
		
		logoutTab.addListener(Events.Select, new Listener<TabPanelEvent>() {  
			
			@Override
			public void handleEvent(TabPanelEvent be) {
				HD_MQP.clearVars();
				Window.open("../", "_self", ""); 
			}  
		});    
		tabs.add(logoutTab);
		
		//TODO Can't tell if this is working
		tabs.addListener(Events.BeforeSelect, new Listener<TabPanelEvent>(){

			@Override
			public void handleEvent(TabPanelEvent be) {
				
				List<Component> panels = be.getItem().getItems();
				Iterator<Component> iterator = panels.iterator();
				
				while(iterator.hasNext()){
					iterator.next().repaint();				
				}
			}
			
		});
		
		vp.add(tabs);  
	}  

}
