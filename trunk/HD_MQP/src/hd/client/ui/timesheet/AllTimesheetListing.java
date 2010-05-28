package hd.client.ui.timesheet;

import hd.client.timesheet.CompleteTimesheet;
import hd.client.timesheet.TimesheetService;
import hd.client.timesheet.TimesheetServiceAsync;

import java.util.ArrayList;
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


/** GUI listing of all Timesheets
 * 
 * @author Eric Greer (egreer@alum.wpi.edu)
 * @author Jason Codding (jcodding@alum.wpi.edu)
 * WPI Helpdesk MQP 2009-2010
 *
 */
public class AllTimesheetListing extends LayoutContainer {  

	private ContentPanel outerContainer = new ContentPanel();
	private final ListStore<BeanModel> store = new ListStore<BeanModel>();
	private ColumnModel cm;

	/** Create a remote service proxy to talk to the server-side Timesheet service. */
	private final TimesheetServiceAsync TimesheetService = GWT.create(TimesheetService.class);
	BeanModelFactory factory = BeanModelLookup.get().getFactory(CompleteTimesheet.class);

	/*
	 * (non-Javadoc)
	 * @see com.extjs.gxt.ui.client.widget.LayoutContainer#onRender(com.google.gwt.user.client.Element, int)
	 */
	@Override
	protected void onRender(Element parent, int index) {  
		super.onRender(parent, index);
		createEmployeeTimesheetListing();
		//setLayout(centerLayout);
		add(outerContainer);
		Info.display("Timesheet Listing Grid", "Rendered");
	}
	
	/** Creates the GUI components*/
	@SuppressWarnings("unchecked")
	private void createEmployeeTimesheetListing() { 

		List<ColumnConfig> columns = new ArrayList<ColumnConfig>();  

		ColumnConfig column = new ColumnConfig();  
		column.setId("startDate");  
		column.setHeader("Start Date");  

		column.setWidth(100);  
		columns.add(column);

		column = new ColumnConfig();
		column.setId("endDate");  
		column.setHeader("End Date");  
		column.setWidth(100);  
		columns.add(column);

		column = new ColumnConfig();  
		column.setId("employeeFirstName"); 
		column.setHeader("First Name");  
		column.setWidth(100);  
		columns.add(column);  

		column = new ColumnConfig();  
		column.setId("employeeLastName"); 
		column.setHeader("Last Name");  
		column.setWidth(100);  
		columns.add(column);  

		column = new ColumnConfig();  
		column.setId("totalHours"); 
		column.setHeader("Total Hours");  
		column.setWidth(100);  
		columns.add(column);  

		column = new ColumnConfig();  
		column.setId("status"); 
		column.setHeader("Status");  
		column.setWidth(100);  
		columns.add(column);  

		column = new ColumnConfig();  
		column.setId("printed");  
		column.setHeader("Printed");  
		column.setAlignment(HorizontalAlignment.RIGHT);  
		column.setWidth(75);  
		columns.add(column);  

		cm = new ColumnModel(columns);  


		Grid<BeanModel> grid = new Grid<BeanModel>(store, cm);  

		grid.setStyleAttribute("borderTop", "none");  
		grid.setAutoExpandColumn("printed");  
		grid.setBorders(true);  
		grid.setStripeRows(true);
		grid.setSize(600, 450);

		grid.addListener(Events.CellDoubleClick, new Listener<GridEvent>() {
			public void handleEvent(GridEvent be) {

				if (be.getType() == Events.CellDoubleClick) {
					final Long key = (Long) store.getAt(be.getRowIndex()).get("timesheetKey");
					Info.display("Cell Double clicked: ",be.getRowIndex()+" "+be.getColIndex() + "Key " + key);

					TimesheetWindow tw = new TimesheetWindow((Long)store.getAt(be.getRowIndex()).get("timesheetKey"), (Long[]) store.getAt(be.getRowIndex()).get("scheduleDetailsKeys"));

					tw.render(outerContainer.getElement());
					Info.display("Retrieved Timesheet", "Render");

					tw.show();
					Info.display("Retrieved Timesheet", "Show");

					tw.addListener(Events.Hide, new Listener<WindowEvent>(){

						@Override
						public void handleEvent(WindowEvent be) {
							updateModel(key);
						}
					});
				}
			}});

		outerContainer.setHeading("Timesheets");
		outerContainer.setBodyBorder(false);
		//outerContainer.setLayout(new FitLayout());
		outerContainer.setAutoHeight(true);
		outerContainer.setAutoWidth(true);

		outerContainer.add(grid);

		populateGrid();
	}

	/** Populates the grid with all the timesheets */
	void populateGrid(){
		TimesheetService.retrieveAllCompleteTimesheets(new AsyncCallback<List<CompleteTimesheet>>(){

			@Override
			public void onFailure(Throwable caught) {
				Info.display("Retrive Timesheets", "SERVER ERROR");
			}


			@Override
			public void onSuccess(List<CompleteTimesheet> result) {
				Info.display("Retrive Timesheets", "Sucess Number: " + result.size());
				store.add(factory.createModel(result));
			}

		});
	}

	/** Updates the Grid with the changed account*/
	void updateModel(Long key){
		final Long key2 = key; 
		TimesheetService.retrieveCompleteTimesheet(key2, new AsyncCallback<CompleteTimesheet>(){

			@Override
			public void onFailure(Throwable caught) {
				Info.display("Retrieve Timesheet", "Server Error");
			}

			@Override
			public void onSuccess(CompleteTimesheet result) {
				BeanModel gridModel = store.findModel("timesheetKey", key2);
				store.remove(gridModel);
				Info.display("Remove Models", "Success");
				store.add(factory.createModel(result));
				Info.display("Create Model", "Success");
			}

		});
	}
}

