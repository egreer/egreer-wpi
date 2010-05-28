package hd.client.ui.timesheet;

import hd.client.HD_MQP;
import hd.client.entities.Permissions;
import hd.client.timesheet.Status;
import hd.client.timesheet.Timesheet;
import hd.client.timesheet.TimesheetService;
import hd.client.timesheet.TimesheetServiceAsync;

import java.util.ArrayList;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;

/** GUI window that displays entire Timesheet, and contains  buttons for managing 
 * timesheet statuses 
 * 
 * @author Eric Greer (egreer@alum.wpi.edu)
 * @author Jason Codding (jcodding@alum.wpi.edu)
 * WPI Helpdesk MQP 2009-2010
 *
 */
public class TimesheetWindow extends Window {
	/** Create a remote service proxy to talk to the server-side Timesheet service.*/
	private final TimesheetServiceAsync TimesheetService = GWT.create(TimesheetService.class);

	//CompleteTimesheet complete;
	Timesheet sheet;
	Long key;
	final FormPanel outerContainer = new FormPanel(); 
	Status status;
	Long[] detailsKeys;

	final RowData firstRow = new RowData();
	final RowData secondRow = new RowData();
	final RowData thirdRow = new RowData();

	/** A Timesheet window is created from details that can be found in 
	 * a CompleteTimesheet 
	 * 
	 * @param sheetKey		The Key for the particular Timesheet to display
	 * @param detailsKeys	An Array of ScheduleDetails Keys to display
	 */
	TimesheetWindow(Long sheetKey, Long[] detailsKeys){
		key = sheetKey;
		this.detailsKeys = detailsKeys;
	}


	/** Communicates updates of the status of a Timesheet to the server */
	AsyncCallback<Boolean> timesheetUpdate = new AsyncCallback<Boolean>(){

		@Override
		public void onFailure(Throwable caught) {
			Info.display("Saving Timesheets", "Server Error");
		}

		@Override
		public void onSuccess(Boolean result) {
			if (result) hide(); //Closes the window
			else Info.display("Timesheet Status", "Faulure to change timesheet");
		}

	};

	/** Listener for button events to save updates to the Timesheet*/
	SelectionListener<ButtonEvent> saveListener = new SelectionListener<ButtonEvent>(){
		public void componentSelected(ButtonEvent ce) {
			Button b = ce.getButton();
			if (b.equals(approveButton)) status = Status.Approved;
			else if (b.equals(denyButton)) status = Status.Assigned;
			else if (b.equals(closeButton)) status = Status.Closed;
			else if (b.equals(submitButton)) status = Status.Pending;
			else status = Status.Pending;

			TimesheetService.changeTimesheetStatus(sheet, status, timesheetUpdate); 
		}
	};

	final Button approveButton = new Button("Approve", saveListener);
	final Button denyButton = new Button("Deny", saveListener);
	final Button closeButton = new Button("Close", saveListener);
	final Button submitButton = new Button("Submit", saveListener);
	final Button printButton = new Button ("Print"); //TODO add print listener

	TextField<Double> total = new TextField<Double>();

	ArrayList<TimesheetDetailContainer> details = new ArrayList<TimesheetDetailContainer>();

	/*
	 * (non-Javadoc)
	 * @see com.extjs.gxt.ui.client.widget.Window#onRender(com.google.gwt.user.client.Element, int)
	 */
	@Override
	protected void onRender(Element parent, int index){
		super.onRender(parent, index);

		setHeading("Manage Timesheet");
		setModal(true);  
		setBlinkModal(true); 
		setShadow(false);

		outerContainer.setWidth(400);
		outerContainer.setHeight(300);
		outerContainer.setHeaderVisible(false);
		outerContainer.setLayout(new TableLayout(7));
		createTimesheetWindow();

		add(outerContainer);

		this.setAutoHeight(true);
		this.setAutoWidth(true);
	}

	/** Creates the GUI components of Window*/
	private void createTimesheetWindow() {
		addDetails();
		addButtons();

		//Retrieves the Timesheet from the key
		TimesheetService.retrieveTimesheet(key, new AsyncCallback<Timesheet>(){

			@Override
			public void onFailure(Throwable caught) {
			}

			@Override
			public void onSuccess(Timesheet result) {
				Info.display("Return Timesheet" , "Success");
				sheet = result;
				//TODO any thing else?
			}
		});

	}

	/** Attaches the ScheduleDetails to the Window */
	private void addDetails(){
		for (int j = 0 ; j < detailsKeys.length ; j++){
			TimesheetDetailContainer detail = new TimesheetDetailContainer(detailsKeys[j]);

			details.add(detail);
			outerContainer.add(detail);
		}
	}

	/** Adds the status buttons to the form based on permissions*/
	private void addButtons(){
		if(HD_MQP.getPermission().equals(Permissions.MANAGER)){
			outerContainer.addButton(approveButton);
			outerContainer.addButton(denyButton);
			outerContainer.addButton(closeButton);
			outerContainer.addButton(printButton);
		}else if(HD_MQP.getPermission().equals(Permissions.EMPLOYEE))
			outerContainer.addButton(submitButton);
		
		outerContainer.setButtonAlign(HorizontalAlignment.CENTER);
	}
}
