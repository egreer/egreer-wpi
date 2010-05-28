package hd.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import hd.client.profile.Employee;
import hd.client.profile.Position;
import hd.client.profile.UserService;
import hd.client.profile.UserServiceAsync;

import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.data.BeanModelFactory;
import com.extjs.gxt.ui.client.data.BeanModelLookup;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.store.StoreSorter;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.DualListField;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.ListField;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;

/** GUI component a manager can use to edit which positions an employee has.
 * Consists of two lists: one of the available positions, and one of positions the employee has
 * Positions can be dragged from one list to the other.    
 * 
 * @author Eric Greer (egreer@alum.wpi.edu)
 * @author Jason Codding (jcodding@alum.wpi.edu)
 * WPI Helpdesk MQP 2009-2010
 *
 */
public class EmployeePositionManagementForm extends LayoutContainer{

	private FormPanel panel = new FormPanel();	

	private ListStore<BeanModel> fromStore = new ListStore<BeanModel>();
	private ListStore<BeanModel> toStore = new ListStore<BeanModel>();
	private Employee emp;
	/**
	 * Create a remote service proxy to talk to the server-side User service.
	 */
	private final UserServiceAsync UserService = GWT.create(UserService.class);
	
	BeanModelFactory factory = BeanModelLookup.get().getFactory(Position.class);

	public EmployeePositionManagementForm(Long key) {
		getEmployee(key);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.extjs.gxt.ui.client.widget.LayoutContainer#onRender(com.google.gwt.user.client.Element, int)
	 */
	@Override  
	protected void onRender(Element parent, int index) {  
		super.onRender(parent, index);  
		setStyleAttribute("margin", "10px");  
		createEmployeePositionManagement();
		add(panel); 
	}

	/**Creates the GUI components */
	private void createEmployeePositionManagement() {
		panel.setHeading("Employee Positions");
		
		final DualListField<BeanModel> lists = new DualListField<BeanModel>();  

		lists.setFieldLabel("Positions");  

		ListField<BeanModel> from = lists.getFromList();  
		from.setDisplayField("title");  
		
		fromStore.setStoreSorter(new StoreSorter<BeanModel>());  
		  
		from.setStore(fromStore);
		
		ListField<BeanModel> to = lists.getToList();  
		to.setDisplayField("title");

		toStore.setStoreSorter(new StoreSorter<BeanModel>());  
		to.setStore(toStore);


		panel.add(lists, new FormData("98%"));  
		panel.setWidth(550);
		
		SelectionListener<ButtonEvent> listener2 = new SelectionListener<ButtonEvent>(){

			public void componentSelected(ButtonEvent ce) {
				Info.display("Click Event", "The save button was clicked.");
				List<BeanModel> models = toStore.getModels();
				Iterator<BeanModel> iterator = models.iterator();
				ArrayList<Long> keys = new ArrayList<Long>(); 
				while(iterator.hasNext()){
					keys.add((Long)iterator.next().get("positionKey"));
				}
				
				UserService.addAllPositionsToEmployee(emp, keys, new AsyncCallback<Boolean>(){

					@Override
					public void onFailure(Throwable caught) {
						Info.display("Saved Positions", "Server Error");
					}

					@Override
					public void onSuccess(Boolean result) {
						if (result)	Info.display("Saved Positions", "Successfully");
						else Info.display("Saved Positions", "Failure");
					}
				});
			}
		};

		Button saveButton = new Button("Save Positions", listener2);
		saveButton.setWidth("auto");
		saveButton.setBorders(false);
		saveButton.setToolTip("Click to save changes to user positions");
		
		panel.addButton(saveButton);
		
		UserService.fetchAllPositions(new AsyncCallback<Collection<Position>> (){

			@Override
			public void onFailure(Throwable caught) {
				// TODO Error Fetching positions
				Info.display("Positions List", "Error");
			}

			@Override
			public void onSuccess(Collection<Position> result) {
				Info.display("Positions List", "Success Result: " +result.size());
				fromStore.add(factory.createModel(result));
				createToList();
				
			}
			
		});
	}  

	/** Converts all the positions an employee has into a distinct list */
	void createToList(){
		ArrayList<Long> positions = emp.getPositionsKeys();
		Iterator<Long> iterator = positions.iterator();
		while (iterator.hasNext()){
			Long key = iterator.next();
			UserService.getPosition(key, new AsyncCallback<Position>(){

				@Override
				public void onFailure(Throwable caught) {
					Info.display("Retrieve Position", "Server Fail");
				}

				@Override
				public void onSuccess(Position result) {
					Info.display("Retrieve Position", "Success");
					BeanModel pos = fromStore.findModel("positionKey", result.getPositionKey());
					toStore.add(pos);
					fromStore.remove(pos);
				}
			});
		}
	}
	
	/** retrieves the employee of a particular key from the DB
	 * 
	 * @param key	The employees DB key
	 */
	void getEmployee(Long key){
		UserService.fetchProfile(key , new AsyncCallback<Employee>() {
			public void onFailure(Throwable caught) {
				//TODO Fail to populate form
				Info.display("Populate Form", "Long Error Populating");
			}

			public void onSuccess(Employee result) {
				if (result != null){
					emp = result;
					Info.display("Populate Form", "Long Success");
				
				}
				else Info.display("Populate Form", "Long Failure");

			}

		});
	}
}

