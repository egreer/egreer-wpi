package hd.client.ui.timesheet;

import hd.client.timesheet.ScheduleDetails;
import hd.client.timesheet.TimesheetService;
import hd.client.timesheet.TimesheetServiceAsync;

import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ContainerEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.event.WindowEvent;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;

/** The GUI component that has the individual ScheduleDetails 
 * 
 * @author Eric Greer (egreer@alum.wpi.edu)
 * @author Jason Codding (jcodding@alum.wpi.edu)
 * WPI Helpdesk MQP 2009-2010
 *
 */
public class TimesheetDetailContainer extends LayoutContainer {

	/** Create a remote service proxy to talk to the server-side User service. */
	private final TimesheetServiceAsync TimesheetService = GWT.create(TimesheetService.class);

	ScheduleDetails detail;
	Long key;
	TimesheetDetailWindow window;

	/* Listens for a button click and shows the edit window*/
	SelectionListener<ButtonEvent> editListener = new SelectionListener<ButtonEvent>(){

		public void componentSelected(ButtonEvent ce) {
			Info.display("Click Event", "The hour button was clicked.");
			window.show();	
		}
	};

	Button hoursButton = new Button("0", editListener);  


	//Fields
	//Text hours = new Text("0");
	Text day = new Text("");
	DateTimeFormat df = DateTimeFormat.getFormat("MM/dd"); 
	Text date = new Text("");

	Listener<ContainerEvent<LayoutContainer, Text>> detailListener = new Listener<ContainerEvent<LayoutContainer, Text>>(){

		@Override
		public void handleEvent(ContainerEvent<LayoutContainer, Text> be) {
			Info.display("Double Click", "Detail: " + detail.getScheduleDetailsKey());
			window.show();	
		}

	};

	Listener<WindowEvent> windowListener = new Listener<WindowEvent>(){

		@Override
		public void handleEvent(WindowEvent be) {
			updateModel();
		}
	};

	/** The constructor of a Timesheet container
	 * 
	 * @param key	The key of the details
	 */
	TimesheetDetailContainer(Long key){
		this.key = key;
	}

	/*
	 * (non-Javadoc)
	 * @see com.extjs.gxt.ui.client.widget.LayoutContainer#onRender(com.google.gwt.user.client.Element, int)
	 */
	@Override
	protected void onRender(Element parent, int index){
		super.onRender(parent, index);
		createDetail();
		Info.display("Detail container", "rendered");
		setBorders(true);
		addListener(Events.OnDoubleClick, detailListener); //XXX I don't know if this works

	}

	/** Creates the GUI Details */
	private void createDetail(){
		setLayout(new RowLayout(Orientation.VERTICAL));

		//if (detail != null && detail.getCurrentHours() != null) hours.setText(detail.getCurrentHours().toString());

		day.setWidth(65);
		date.setWidth(65);
		//hours.setWidth(50);


		add(day, new RowData());
		add(date, new RowData());
		//add(hours, new RowData());
		add(hoursButton, new RowData());
		//XXX Remove this
		//day.addListener(Events.DoubleClick, detailListener);
		//date.addListener(Events.DoubleClick, detailListener);
		//hours.addListener(Events.DoubleClick, detailListener);

		setAutoHeight(true);
		setAutoWidth(true);

		updateModel();
	}

	/** Upadates the Model with the Details */
	void updateModel(){
		TimesheetService.getScheduleDetail(key, new AsyncCallback<ScheduleDetails>(){

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				Info.display("Display Schedule Detail", "Server Error");
			}

			@Override
			public void onSuccess(ScheduleDetails result) {
				if (result != null){
					Info.display("Display Schedule Detail", "Success");
					detail = result;

					window = new TimesheetDetailWindow(detail);
					window.addListener(Events.Hide, windowListener);

					//hours.setText(detail.getCurrentHours().toString());
					hoursButton.setText(detail.getCurrentHours().toString());
					day.setText(detail.getDay().toString());
					date.setText(df.format(detail.getDate()));

				}
				else Info.display("Display Schedule Detail", "Failure");

			}

		});
	}

}
