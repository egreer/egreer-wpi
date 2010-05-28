package hd.client;

import java.util.Iterator;
import java.util.List;

import hd.client.ui.account.AccountListing;
import hd.client.ui.timesheet.AllTimesheetListing;
import hd.client.ui.timesheet.W2WFileUploadForm;

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

/** The Tab view a manage sees upon logging in
 * 
 * @author Eric Greer (egreer@alum.wpi.edu)
 * @author Jason Codding (jcodding@alum.wpi.edu)
 * WPI Helpdesk MQP 2009-2010
 *
 */
public class ManagerTabLayout extends LayoutContainer {
	private VerticalPanel vp;  
	private final ChangePasswordForm cpf = new ChangePasswordForm(); 
	@SuppressWarnings("unused")
	private final EmployeePositionManagementForm epm = new EmployeePositionManagementForm(74L);
	private ManagerProfileListing mpl = new ManagerProfileListing();
	private final ManagerPositionsListing mpol = new ManagerPositionsListing();
	private final W2WFileUploadForm fuf = new W2WFileUploadForm();
	private final AllTimesheetListing atl = new AllTimesheetListing();
	private final AccountListing al = new AccountListing(); 

	/** Constructor for the layout */
	public ManagerTabLayout() { 
		vp = new VerticalPanel();  
		vp.setSpacing(10);  
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
	TabPanel tabs = new TabPanel();  
	
	/** Creates the GUI components*/
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
		userProfile.setText("User Profile Listing");  
		userProfile.add(mpl);
		tabs.add(userProfile);


		TabItem timesheets = new TabItem();  
		timesheets.setStyleAttribute("padding", "10px");  
		timesheets.setText("Timesheets");  
		timesheets.setLayout(new FormLayout());
		timesheets.add(atl);
		tabs.add(timesheets);

		TabItem positions = new TabItem();  
		positions.setStyleAttribute("padding", "10px");  
		positions.setText("Positions");  
		positions.setLayout(new FormLayout());
		
		positions.add(mpol); //Manager Positions list
		tabs.add(positions);
		
		TabItem accounts = new TabItem();
		accounts.setStyleAttribute("padding", "10px");
		accounts.setText("Accounts");
		accounts.setLayout(new FormLayout());
		accounts.add(al);
		
		tabs.add(accounts);
		
		
		/*TabItem reviews = new TabItem();  
		reviews.setStyleAttribute("padding", "10px");  
		reviews.setText("Reviews");  
		reviews.setLayout(new FormLayout());
		reviews.add(epf);
		tabs.add(reviews);*/

		TabItem settings = new TabItem();  
		settings.setStyleAttribute("padding", "10px");  
		settings.setText("Settings");  
		settings.setLayout(new FormLayout());
		settings.add(cpf);
		settings.add(fuf); //TODO Move
		
		tabs.add(settings);

		TabItem logoutTab = new TabItem("Logout");  
		logoutTab.addListener(Events.Select, new Listener<TabPanelEvent>() {  
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
