package hd.client;

import hd.client.profile.Position;
import hd.client.profile.UserService;
import hd.client.profile.UserServiceAsync;

import java.util.ArrayList;
import java.util.Iterator;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;

/** The GUI position form used to View, edit, or Create a position 
 * 
 * @author Eric Greer (egreer@alum.wpi.edu)
 * @author Jason Codding (jcodding@alum.wpi.edu)
 * WPI Helpdesk MQP 2009-2010
 *
 */
public class PositionForm extends LayoutContainer{
	Position p = new Position();
	Long key;
	final FormPanel positionForm = new FormPanel();
	ArrayList<Button> buttons = new ArrayList<Button>(); 
	//FormButtonBinding binding = new FormButtonBinding(positionForm);
	
	/** Create a remote service proxy to talk to the server-side User service.*/
	private final UserServiceAsync UserService = GWT.create(UserService.class);
	
	//Position Fields
	private final TextField<String> pname = new TextField<String>();
	private final TextArea pdesc = new TextArea();
	private final TextField<String> rop = new TextField<String>();
	
	/** Default constructor begins with the window in view mode */
	PositionForm(){
		super();
		this.setViewMode();
	}
	
	/** Constructor begins with the window in view mode
	 * @param key The key of the position to display 
	 */
	PositionForm(Long key){
		super();
		this.key = key;
		this.setViewMode();
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.extjs.gxt.ui.client.widget.LayoutContainer#onRender(com.google.gwt.user.client.Element, int)
	 */
	@Override
	protected void onRender(Element parent, int index) {  
		super.onRender(parent, index);
		createPositionForm();
		positionForm.setAutoHeight(true);
		positionForm.setWidth(350);
		positionForm.setFrame(true);
		positionForm.setButtonAlign(HorizontalAlignment.CENTER);  
		add(positionForm);
	}

	/** Creates the GUI components of the Form*/
	private void createPositionForm(){

		//Positions Name Field	
		pname.setWidth(200);
		pname.setHeight(22);
		pname.setBorders(true);
		pname.setFieldLabel("Name");
		pname.setLabelSeparator(":");

		//Description Field
		pdesc.setBorders(true);
		pdesc.setFieldLabel("Description");
		pdesc.setLabelSeparator(":");

		//Rate of Pay Field
		rop.setWidth(200);
		rop.setHeight(22);
		rop.setBorders(true);
		rop.setFieldLabel("Rate of Pay");
		rop.setLabelSeparator(":");

		//Attach Fields to Positions 1 panel
		positionForm.add(pname);
		positionForm.add(pdesc);
		positionForm.add(rop);
		this.addAllButtons();
		
		if (key != null){
			UserService.getPosition(key, new AsyncCallback<Position>(){

				@Override
				public void onFailure(Throwable caught) {
					Info.display("Position Retreive", "Server error");
					
				}

				@Override
				public void onSuccess(Position result) {
					if (result != null){
						Info.display("Position Retreive", "Success");
						p = result;
						updateForm();
					}else{
						Info.display("Position Retreive", "Failure");
						p = new Position();
						updateForm();
					}
					
				}
				
			});
		}else {
			updateForm();
		}

	}
	/** Sets whether the position can be edited*/
	public void setPositionEnabled(Boolean b){
		pname.setEnabled(b);
		pdesc.setEnabled(b);
		rop.setEnabled(b);
	}
	
	/** Sets the form to create a new position */
	void setCreateMode(){
		this.removeAllButtons();
		this.setPositionEnabled(true);
		
		//Create Button
		SelectionListener<ButtonEvent> listener = new SelectionListener<ButtonEvent>(){
			
			public void componentSelected(ButtonEvent ce) {
				updatePos();
				
				Info.display("Click Event", "The Position Create button was clicked.");
				UserService.createPosition(p, new AsyncCallback<Position>(){
					@Override
					public void onFailure(Throwable caught) {
						Info.display("Create Position", "Error");
					}

					@Override
					public void onSuccess(Position result) {
						if (result != null){
							Info.display("Create Position", "Sucess");
							p = result;
							updateForm();
						}else{
							Info.display("Create Position", "Failure");
						}
					}		
				});
			}
		};

		Button createButton = new Button("Create Position", listener);
		this.buttons.add(createButton);
		positionForm.setHeading("Create Position");
	}
	
	/** Sets the form to edit the position it was constructed with*/
	void setEditMode(){
		this.removeAllButtons();
		this.setPositionEnabled(true);
		
		//View/Edit Button
		SelectionListener<ButtonEvent> listener = new SelectionListener<ButtonEvent>(){
			
			public void componentSelected(ButtonEvent ce) {
				updatePos();
				
				Info.display("Click Event", "The Position edit button was clicked.");
				UserService.updatePosition(p, new AsyncCallback<Position>(){
					@Override
					public void onFailure(Throwable caught) {
						Info.display("Edit Position", "Server Error");
					}

					@Override
					public void onSuccess(Position result) {
						if (result != null){
							Info.display("Edit Position", "Sucess");
							p = result;
							updateForm();
						}else{
							Info.display("Edit Position", "Failure");
						}
						
					}
					
				});
			}
		};

		Button editButton = new Button("Edit Position", listener);
		this.buttons.add(editButton);
		positionForm.setHeading("Edit Position");
	}
	
	/** Sets the form into a mode where the position it was created with can 
	 * only be viewed
	 */
	void setViewMode(){
		this.removeAllButtons();
		this.setPositionEnabled(false);
		positionForm.setHeaderVisible(false);
	}
	
	/** Adds all the current buttons to the form*/
	void addAllButtons(){
		//Remove all buttons
		Iterator<Button> iterator = buttons.iterator();
		while(iterator.hasNext()){
			this.positionForm.addButton(iterator.next()); 
		}
	}
	
	/** Removes all the current buttons from the form*/
	void removeAllButtons(){
		//Remove all buttons
		Iterator<Button> iterator = buttons.iterator();
		while(iterator.hasNext()){
			this.positionForm.remove(iterator.next());
		}
	}
	
	/** updates the position with information from the form*/
	void updatePos(){
		p.setTitle(pname.getValue());
		p.setDescription(pdesc.getValue());
		p.setRateOfPay(rop.getValue());
	}
	
	/** updates the form with information from the position */
	void updateForm(){
		pname.setValue(p.getTitle());
		pdesc.setValue(p.getDescription());
		rop.setValue(p.getRateOfPay());
	}
	
	/** returns the position associated with the form*/
	public Position getPosition(){
		return p;
	}
	
	/** resets the position to create mode*/
	public void reset(){
		this.p = new Position();
		updateForm();
	}
	
}
