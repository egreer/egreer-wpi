package hd.client.ui.timesheet;

import hd.client.timesheet.ScheduleDetails;
import hd.client.timesheet.TimesheetService;
import hd.client.timesheet.TimesheetServiceAsync;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;

/** The GUI Window to edit a ScheduleDetails 
 * 
 * @author Eric Greer (egreer@alum.wpi.edu)
 * @author Jason Codding (jcodding@alum.wpi.edu)
 * WPI Helpdesk MQP 2009-2010
 *
 */
public class TimesheetDetailWindow extends Window {
	/**
	 * Create a remote service proxy to talk to the server-side Timesheetservice.
	 */
	private final TimesheetServiceAsync TimesheetService = GWT.create(TimesheetService.class);
	
	FormPanel panel = new FormPanel();
	ScheduleDetails detail;
	
	/** The constructor for the details 
	 * 
	 * @param detail	The detail to build the window on
	 */
	public TimesheetDetailWindow(ScheduleDetails detail) {
		this.detail = detail;
	}
	
		
	SelectionListener<ButtonEvent> saveListener = new SelectionListener<ButtonEvent>(){
		
		public void componentSelected(ButtonEvent ce) {
			updateDetails();
			TimesheetService.updateScheduleDetails(detail, new AsyncCallback<ScheduleDetails>(){

				@Override
				public void onFailure(Throwable caught) {
					Info.display("Saving Details", "Server Error");
				}

				@Override
				public void onSuccess(ScheduleDetails result) {
					
					if (result != null){
						Info.display("Detail Returned", "" + result.getCurrentHours());
						detail = result;
					}
					hide(); //Closes the window
					Info.display("Hiding", "Hidden");
				}
				
			});
		}
	};
	
	
	Button saveButton = new Button("Save Changes", saveListener);
	TextField<Double> hours = new TextField<Double>();
	TextField<String> comments = new TextField<String>();
	
	/*
	 * (non-Javadoc)
	 * @see com.extjs.gxt.ui.client.widget.Window#onRender(com.google.gwt.user.client.Element, int)
	 */
	@Override
	protected void onRender(Element parent, int index) {  
		super.onRender(parent, index);
		createTimesheetDetailWindow();
		panel.setHeaderVisible(false);
		this.setHeading("Modify Hours");
		add(panel);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.extjs.gxt.ui.client.widget.BoxComponent#onShow()
	 */
	@Override
	protected void onShow() {  
		updateForm();
	}
	
	/** Creates the GUI detail window components */
	void createTimesheetDetailWindow(){
		DateTimeFormat df = DateTimeFormat.getFormat("MM/dd"); 
		Text date = new Text(detail.getDay().toString() + ", " + df.format(detail.getDate()));
		
		hours.setWidth(200);
		hours.setHeight(22);
		hours.setBorders(true);
		hours.setFieldLabel("Hours");
		hours.setLabelSeparator(":");
		hours.setToolTip("The number of hours worked today");
		
		comments.setWidth(200);
		comments.setHeight(22);
		comments.setBorders(true);
		comments.setFieldLabel("Comments");
		comments.setLabelSeparator(":");
		comments.setToolTip("Provide an explanation for deviation of scheduled hours.");
		
		panel.add(date);
		panel.add(hours);
		panel.add(comments);
		panel.addButton(saveButton);
		
		updateForm();
	}
	
	/** Updates the details from the form
	 * 
	 */
	void updateDetails(){
		detail.setSpecialCase(hours.getValue());
		detail.setComments(comments.getValue());
	}
	
	/** Updates the form with the details
	 * 
	 */
	void updateForm(){
		hours.setValue(detail.getCurrentHours());
		comments.setValue(detail.getComments());
	}
}
