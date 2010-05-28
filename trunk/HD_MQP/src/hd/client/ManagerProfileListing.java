package hd.client;

import hd.client.profile.Employee;
import hd.client.profile.UserService;
import hd.client.profile.UserServiceAsync;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.data.BeanModelFactory;
import com.extjs.gxt.ui.client.data.BeanModelLookup;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.WindowEvent;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;


/** The GUI Manager Listing of all employee profiles
 * 
 * @author Eric Greer (egreer@alum.wpi.edu)
 * @author Jason Codding (jcodding@alum.wpi.edu)
 * WPI Helpdesk MQP 2009-2010
 *
 */
public class ManagerProfileListing extends LayoutContainer {  

	private ContentPanel outerContainer = new ContentPanel();
	private final ListStore<BeanModel> store = new ListStore<BeanModel>();
	private ColumnModel cm;

	/**
	 * Create a remote service proxy to talk to the server-side User service.
	 */
	private final UserServiceAsync UserService = GWT.create(UserService.class);
	BeanModelFactory factory = BeanModelLookup.get().getFactory(Employee.class);

	/*
	 * (non-Javadoc)
	 * @see com.extjs.gxt.ui.client.widget.LayoutContainer#onRender(com.google.gwt.user.client.Element, int)
	 */
	@Override
	protected void onRender(Element parent, int index) {  
		super.onRender(parent, index);
		createManagerProfileListing();
		//setLayout(centerLayout);
		add(outerContainer);
		Info.display("Employee Grid", "Rendered");
	}


/**Creates a listing of all employee profiles */
	@SuppressWarnings("unchecked")
	private void createManagerProfileListing() { 

		List<ColumnConfig> columns = new ArrayList<ColumnConfig>();  

		ColumnConfig column = new ColumnConfig();  
		column.setId("firstName");  
		column.setHeader("First Name");  

		column.setWidth(200);  
		columns.add(column);


		column = new ColumnConfig();  
		column.setId("lastName"); 
		column.setHeader("Last Name");  
		column.setWidth(200);  
		columns.add(column);  

		column = new ColumnConfig();  
		column.setId("ranking");  
		column.setHeader("Rank");  
		column.setAlignment(HorizontalAlignment.RIGHT);  
		column.setWidth(75);  
		columns.add(column);  

		column = new ColumnConfig("isWorkStudy", "Federal Funding", 100);  
		column.setAlignment(HorizontalAlignment.RIGHT);  
		columns.add(column);

		cm = new ColumnModel(columns);  


		Grid<BeanModel> grid = new Grid<BeanModel>(store, cm);  

		grid.setStyleAttribute("borderTop", "none");  
		grid.setAutoExpandColumn("firstName");  
		grid.setBorders(true);  
		grid.setStripeRows(true);
		grid.setSize(600, 450);
		Info.display("Employee Grid", "Created Grid");
		grid.addListener(Events.CellDoubleClick, new Listener<GridEvent>() {
			public void handleEvent(GridEvent be) {
				
				if (be.getType() == Events.CellDoubleClick) {
					final Long key = (Long) store.getAt(be.getRowIndex()).get("employeeKey");
					Info.display("Cell Double clicked: ",be.getRowIndex()+" "+be.getColIndex() + "Key " + key);
					EditEmployeeWindow eew = new EditEmployeeWindow(key);
					eew.render(outerContainer.getElement());
					eew.window.addListener(Events.Hide, new Listener<WindowEvent>(){

						@Override
						public void handleEvent(WindowEvent be) {
							updateModel(key);
						}
					});
				}
			}
		});

		outerContainer.setHeading("Employees");
		outerContainer.setBodyBorder(false);
		//outerContainer.setLayout(new FitLayout());
		outerContainer.setAutoHeight(true);
		outerContainer.setAutoWidth(true);

		outerContainer.add(grid);

		populateGrid();
	}

	/** Populates the grid with the positions */
	void populateGrid(){
		UserService.fetchAllEmployees(new AsyncCallback<Collection<Employee>>(){

			@Override
			public void onFailure(Throwable caught) {
				Info.display("Retrive Employees", "SERVER ERROR");
			}

			@Override
			public void onSuccess(Collection<Employee> result) {
				Info.display("Retrive Employees", "Sucess Number: " + result.size());
				
				store.add(factory.createModel(result));

				Info.display("Retrive Employees", "STORE Number: " + store.getCount());
				//outerContainer.repaint();
			}

		});
	}
	
	/** Updates the grid ionformation about a particular Profile
	 * 	@param key 	The long value of the Profiles's key   
	 */
	void updateModel(Long key){
		final Long key2 = key; 
		UserService.fetchProfile(key2, new AsyncCallback<Employee>(){

			@Override
			public void onFailure(Throwable caught) {
				Info.display("Retrieve Employee", "Server Error");
			}

			@Override
			public void onSuccess(Employee result) {
				BeanModel gridModel = store.findModel("employeeKey", key2);
				store.remove(gridModel);
				Info.display("Remove Models", "Success");
				store.add(factory.createModel(result));
				Info.display("Create Model", "Success");
			}
			
		});
	}
}

