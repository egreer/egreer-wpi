package hd.client;

import hd.client.entities.Permissions;
import hd.client.profile.Employee;
import hd.client.profile.Gender;
import hd.client.profile.Size;
import hd.client.profile.UserService;
import hd.client.profile.UserServiceAsync;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.event.WindowEvent;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Slider;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.Radio;
import com.extjs.gxt.ui.client.widget.form.RadioGroup;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.extjs.gxt.ui.client.widget.form.SliderField;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.Validator;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.layout.ColumnData;
import com.extjs.gxt.ui.client.widget.layout.ColumnLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;

/** The complete profile of an employee
 * 
 * @author Eric Greer (egreer@alum.wpi.edu)
 * @author Jason Codding (jcodding@alum.wpi.edu)
 * WPI Helpdesk MQP 2009-2010
 *
 */
public class EmployeeProfileForm extends LayoutContainer {  

	private Object user;

	private Employee me = new Employee();
	
	//Layout Variables
	private LayoutContainer outerContainer = new LayoutContainer();
	private ColumnLayout columnLayout = new ColumnLayout();
	private ColumnData leftColumn = new ColumnData();
	private FormPanel BioBox = new FormPanel();
	private ColumnData rightColumn = new ColumnData();
	private RowLayout rightLayout = new RowLayout();
	private LayoutContainer rightLayoutContain = new LayoutContainer();
	private FormPanel positionsBox = new FormPanel();
	private RowLayout positionsRowLayout = new RowLayout();
	private LayoutContainer positionsRowLayoutContain = new LayoutContainer();
	private RowData position1 = new RowData();
		
	private RowData position2 = new RowData();
	private FormPanel empDetailsBox = new FormPanel();

	/* Form variables */
	final TextField<String> idnum = new TextField<String>();
	final TextField<String> firstname = new TextField<String>();
	final TextField<String> lastname = new TextField<String>();
	final TextField<String> nickname = new TextField<String>();
	final DateField dob = new DateField();

	//Gender Information
	final Radio male = new Radio();  
	final Radio female = new Radio();  
	final RadioGroup genderGroup = new RadioGroup();  

	final SimpleComboBox<Size> shirtsize = new SimpleComboBox<Size>();
	final TextField<String> email = new TextField<String>();
	final TextField<String> cellphone = new TextField<String>();
	final TextField<String> mb = new TextField<String>();
	final TextArea la = new TextArea(); 
	final TextField<String> homephone = new TextField<String>();
	final TextArea ha = new TextArea();
	final TextArea sn = new TextArea();

	final DateField hd =  new DateField();

	//WS Information
	final Radio federal = new Radio();  
	final Radio department = new Radio();  
	final RadioGroup wsGroup = new RadioGroup();  

	final TextArea st = new TextArea();
	final TextField<String> wss = new TextField<String>();
	final TextArea goals = new TextArea();
	final Slider rankslider = new Slider();  ;
	final SliderField rank = new SliderField(rankslider);  
	final TextArea comm = new TextArea();
	final TextArea fst = new TextArea(); 

	//Form Buttons
	
	//View/Edit Button
	SelectionListener<ButtonEvent> editListener = new SelectionListener<ButtonEvent>(){

		public void componentSelected(ButtonEvent ce) {
			Info.display("Click Event", "The View/Edit button was clicked.");
			toggleVEButton();
		}
	};

	Button editButton = new Button("Edit", editListener);  

	
	//Save Button
	SelectionListener<ButtonEvent> saveListener = new SelectionListener<ButtonEvent>(){

		public void componentSelected(ButtonEvent ce) {
			//if(!simple.isValid()) return; TODO vaildate form
			Info.display("Click Event", "The save button was clicked.");

			updateMe();
			Info.display("Create Employee", "Post Update");
			UserService.updateProfile(me, new AsyncCallback<Employee>() {
				public void onFailure(Throwable caught) {
					Info.display("Create Employee", "Server Error");
				}

				public void onSuccess(Employee result) {
					if (result != null){
						Info.display("Save Employee", "Success");
						updateForm();
					}
					else Info.display("Save Employee", "Failure");
				}
			});
		}
	};

	Button saveButton = new Button("Save", saveListener);

	//Position Management Button
	SelectionListener<ButtonEvent> positionListener = new SelectionListener<ButtonEvent>(){

		public void componentSelected(ButtonEvent ce) {
			EmployeePositionsWindow epw = new EmployeePositionsWindow(me.getEmployeeKey());
			epw.render(outerContainer.getElement());
			epw.window.addListener(Events.Hide, new Listener<WindowEvent>(){

				@Override
				public void handleEvent(WindowEvent be) {
					positionsRowLayoutContain.removeAll();
					positionsRowLayoutContain.layout();
					positionForm();
				}
			});			
		}
	};

	Button positionButton = new Button("Manage Positions", positionListener);
	
	//Validator to validate fields on the form
	Validator validator = new Validator(){
		String phoneRegx = "(\\d-)?(\\d{3}-)?\\d{3}-\\d{4}";
		String phoneError = "Invalid number. Please use one of these phone formats: 1-508-831-5888, 508-831-5889, 831-2221";
		String emailRegx = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
		String emailError = "Invalid email format.";
		@Override
		public String validate(Field<?> field, String value) {
			if (field == cellphone)
				if(!cellphone.getValue().matches(phoneRegx)) return phoneError;
			
			if (field == homephone)
				if(!homephone.getValue().matches(phoneRegx)) return phoneError;
			
			if (field == email)
				if(!email.getValue().matches(emailRegx)) return emailError;
			return null;
		}
		
	};


	/** Create a remote service proxy to talk to the server-side User service. */
	private final UserServiceAsync UserService = GWT.create(UserService.class);

	/** Constructor to create the form from a username
	 * 
	 * @param username 	The username of the employee to generate the form for 
	 */
	EmployeeProfileForm(String username){
		super();
		this.user = username;
	}
	
	/** Constructor to create form from the employee object key
	 * 
	 * @param key	The key of the employee object
	 */
	EmployeeProfileForm(Long key){
		super();
		this.user = key;
	}

/*
 * (non-Javadoc)
 * @see com.extjs.gxt.ui.client.widget.LayoutContainer#onRender(com.google.gwt.user.client.Element, int)
 */
	@Override
	protected void onRender(Element parent, int index) {  
		super.onRender(parent, index);
		createEmployeeProfileForm();   
		add(outerContainer);
	}

	/** createEmployeeProfileForm method contains layout and form generation used onRender */
	private void createEmployeeProfileForm() {  

		//NEW Layout variables
		FormLayout BioBoxFormLayout = new FormLayout();
		FlowLayout positionsFlowLayout = new FlowLayout();
		FormLayout empDetailFormLayout = new FormLayout();

		outerContainer.setStyleName("x-border x-column-layout-ct");
		//outerContainer.setWidth(704);
		//outerContainer.setHeight(404);
		outerContainer.setBorders(true);

		BioBox.setStyleName("x-panel x-form-label-left x-column");
		//BioBox.setWidth(340);
		//BioBox.setHeight(57);
		BioBox.setBorders(false);
		BioBox.setFrame(true);
		BioBox.setHeading("Biographical Information");

		leftColumn.setWidth(0.4854368932038835);

		//BioBoxFormLayout.setDefaultWidth(210);
		//BioBoxFormLayout.setLabelWidth(75);

		rightLayoutContain.setStyleName("x-border x-column");
		//rightLayoutContain.setWidth(340);
		//rightLayoutContain.setHeight(404);
		rightLayoutContain.setBorders(true);

		//rightColumn.setWidth(0.4854368932038835);

		positionsBox.setStyleName("x-panel x-border");
		positionsBox.setWidth(340);
		positionsBox.setHeight(200);
		positionsBox.setBorders(false);
		positionsBox.setPadding(0);
		positionsBox.setScrollMode(Style.Scroll.AUTOY);
		positionsBox.setHeading("Positions");

		//rowBox1.setHeight(0.5);

		positionsRowLayoutContain.setStyleName("x-border");
		//positionsRowLayoutContain.setWidth(704);
		//positionsRowLayoutContain.setHeight(404);
		positionsRowLayoutContain.setBorders(true);

		empDetailsBox.setStyleName("x-panel x-form-label-left");
		//empDetailsBox.setWidth(337);
		//empDetailsBox.setHeight(201);
		empDetailsBox.setBorders(false);
		empDetailsBox.setFrame(true);
		empDetailsBox.setHeading("Employee Details");

		//position2.setHeight(0.5);

		//empDetailFormLayout.setDefaultWidth(210);
		//empDetailFormLayout.setLabelWidth(75);


		//Fields for Biographical information
		//ID Number
		idnum.setWidth(200);
		idnum.setHeight(22);
		idnum.setBorders(true);
		idnum.setFieldLabel("ID Number");
		idnum.setLabelSeparator(":");
		idnum.setToolTip("WPI Student ID Number");

		//First Name Field
		firstname.setWidth(200);
		firstname.setHeight(22);
		firstname.setBorders(true);
		firstname.setFieldLabel("First Name");
		firstname.setLabelSeparator(":");
		

		//Last Name Field
		lastname.setWidth(200);
		lastname.setHeight(22);
		lastname.setBorders(true);
		lastname.setFieldLabel("Last Name");
		lastname.setLabelSeparator(":");
		
		//Nickname Field
		nickname.setWidth(200);
		nickname.setHeight(22);
		nickname.setBorders(true);
		nickname.setFieldLabel("Nickname");
		nickname.setLabelSeparator(":");

		//Date of Birth
		dob.setWidth(200);
		dob.setHeight(22);
		dob.setBorders(false);
		dob.setFieldLabel("Date of Birth");
		dob.setLabelSeparator(":");
		dob.setToolTip("MM/DD/YY");

		//Gender Buttons
		male.setBoxLabel("Male");  
		male.setValue(true);  

		female.setBoxLabel("Female"); 

		genderGroup.setFieldLabel("Gender");  
		genderGroup.add(male);
		genderGroup.add(female);

		//Shirt Size
		List<Size> sizes = Arrays.asList(Size.values());
		shirtsize.setWidth(200);
		shirtsize.setHeight(22);
		shirtsize.setBorders(false);
		shirtsize.setFieldLabel("Shirt Size");
		shirtsize.setLabelSeparator(":");
		shirtsize.setAllowBlank(true);
		shirtsize.setSimpleValue(Size.U);
		shirtsize.setForceSelection(true);
		shirtsize.setTriggerAction(TriggerAction.ALL);
		shirtsize.add(sizes);

		//Email Field
		email.setWidth(200);
		email.setHeight(22);
		email.setBorders(true);
		email.setValidator(validator);
		email.setValidateOnBlur(true);
		email.setFieldLabel("Email");
		email.setLabelSeparator(":");
		
		//Cell Phone Field 
		cellphone.setWidth(200);
		cellphone.setHeight(22);
		cellphone.setBorders(true);
		cellphone.setFieldLabel("Cell Phone");
		cellphone.setLabelSeparator(":");
		cellphone.setValidator(validator);
		cellphone.setValidateOnBlur(true);
		cellphone.setToolTip("XXX-XXX-XXXX");
		
		//Mailbox Field
		mb.setWidth(200);
		mb.setHeight(22);
		mb.setBorders(true);
		mb.setFieldLabel("Mailbox");
		mb.setLabelSeparator(":");

		//Local Address Field
		la.setWidth(200);
		la.setHeight(60);
		la.setBorders(true);
		la.setFieldLabel("Local Address");
		la.setLabelSeparator(":");

		//Home Phone Field
		homephone.setWidth(200);
		homephone.setHeight(22);
		homephone.setBorders(true);
		homephone.setFieldLabel("Home Phone");
		homephone.setLabelSeparator(":");
		homephone.setValidator(validator);
		homephone.setValidateOnBlur(true);
		homephone.setToolTip("XXX-XXX-XXXX");
		
		//Home Address Field
		ha.setWidth(200);
		ha.setHeight(60);
		ha.setBorders(true);
		ha.setFieldLabel("Home Address");
		ha.setLabelSeparator(":");

		//Social Network Field
		sn.setWidth(200);
		sn.setHeight(60);
		sn.setBorders(true);
		sn.setFieldLabel("Social Networks");
		sn.setLabelSeparator(":");

		//Edit button style
		//editButton.setStyleName("x-btn-wrap x-btn");
		editButton.setWidth("auto");
		editButton.setBorders(false);
		editButton.setToolTip("Click to allow editing of user profile");

		//Add save button
		//saveButton.setStyleName("x-btn-wrap x-btn");
		saveButton.setWidth("auto");
		saveButton.setBorders(false);
		saveButton.setToolTip("Click to save changes to user profile");

		//Add Position Management Button
		positionButton.setWidth("auto");
		positionButton.setBorders(false);
		positionButton.setToolTip("Click to Add or Remove Positions from this Employee");

		//Attach Biographical Fields to BioBox panel
		BioBox.add(idnum);
		BioBox.add(firstname);
		BioBox.add(lastname);
		BioBox.add(nickname);
		BioBox.add(dob);
		BioBox.add(genderGroup);
		BioBox.add(shirtsize);
		BioBox.add(email);
		BioBox.add(cellphone);
		BioBox.add(mb);
		BioBox.add(la);
		BioBox.add(homephone);
		BioBox.add(ha);
		BioBox.add(sn);

		//Attach Buttons to BioBox panel
		BioBox.addButton(editButton);
		BioBox.addButton(saveButton);
		BioBox.addButton(positionButton);
		BioBox.setButtonAlign(HorizontalAlignment.CENTER);

		
		//Employment details section
		//Hire Date Field			
		hd.setWidth(200);
		hd.setHeight(22);
		hd.setBorders(false);
		hd.setFieldLabel("Hire Date");
		hd.setLabelSeparator(":");
		hd.setToolTip("MM/DD/YY");
		
		//Toggle button for WS status		
		federal.setBoxLabel("Federal");  
		federal.setValue(true);  

		department.setBoxLabel("Department"); 

		wsGroup.setFieldLabel("Funding");  
		wsGroup.add(federal);
		wsGroup.add(department);

		//Special Training Field
		st.setWidth(200);
		st.setHeight(60);
		st.setBorders(true);
		st.setFieldLabel("Special Training");
		st.setLabelSeparator(":");
		st.setToolTip("For example: any certifications, advanced trainings, etc...");

		//Goals Field
		goals.setWidth(200);
		goals.setHeight(60);
		goals.setBorders(true);
		goals.setFieldLabel("Goals");
		goals.setLabelSeparator(":");
		
		//Ranking Field
		rankslider.setWidth(214);  
		rankslider.setMessage("Rank: {0}");
		rankslider.setIncrement(5);
		rankslider.setMaxValue(100);
		
		rank.setFieldLabel("Ranking");  
		rank.setLabelSeparator(":");
		
		
		//Comments Field
		comm.setWidth(200);
		comm.setHeight(60);
		comm.setBorders(true);
		comm.setFieldLabel("Comments");
		comm.setLabelSeparator(":");

		//Five Star Tickets Field 
		fst.setWidth(200);
		fst.setHeight(60);
		fst.setBorders(true);
		fst.setFieldLabel("Five Star Tickets");
		fst.setLabelSeparator(":");


		//Attach fields to Employee details form 
		empDetailsBox.add(hd);
		empDetailsBox.add(wsGroup);
		empDetailsBox.add(st);
		empDetailsBox.add(goals);
		empDetailsBox.add(rank);
		empDetailsBox.add(comm);
		empDetailsBox.add(fst);

		//Attach Layouts and Containers and Forms

		BioBox.setLayout(BioBoxFormLayout);
		outerContainer.add(BioBox,leftColumn);
		outerContainer.add(rightLayoutContain,rightColumn);
		rightLayoutContain.add(empDetailsBox, position1);
		
		positionsBox.add(positionsRowLayoutContain);
		
		//positionsRowLayoutContain.add(positions2DataContain,position1);
		
		
		//positions2DataContain.setLayout(position2DataFormLayout);
		positionsRowLayoutContain.setLayout(rightLayout);
		positionsBox.setLayout(positionsFlowLayout);
		rightLayoutContain.add(positionsBox, position2);
		empDetailsBox.setLayout(empDetailFormLayout);
		rightLayoutContain.setLayout(positionsRowLayout);
		outerContainer.setLayout(columnLayout);

		//On Load Fetch Profile and set fields to view only
		setMode("View");
		if (user instanceof String){
			UserService.fetchProfile((String)user, new AsyncCallback<Employee>() {
			public void onFailure(Throwable caught) {
				Info.display("Populate Form", "Error Populating");
			}

			public void onSuccess(Employee result) {
				if (result != null){
					me = result;
					updateForm();
					Info.display("Populate Form", "Success");
				
				}
				else Info.display("Populate Form", "Failure");

			}

		});
		}
		
		if (user instanceof Long){
			UserService.fetchProfile((Long)user, new AsyncCallback<Employee>() {
			public void onFailure(Throwable caught) {
				Info.display("Populate Profile Form", "Error Populating");
			}

			public void onSuccess(Employee result) {
				if (result != null){
					me = result;
					updateForm();
					Info.display("Populate Profile Form", "Success");
				
				}
				else Info.display("Populate Profile Form", "Failure");

			}

		});
		}
	}

	
	/** Updates the form from the employee */
	private void updateForm(){
		//SETS Positions
		this.positionForm();
		
		// Set form information 
		idnum.setValue(me.getIdNumber());
		firstname.setValue(me.getFirstName());
		lastname.setValue(me.getLastName());
		nickname.setValue(me.getNickname());
		dob.setValue(me.getDateOfBirth());

		if (me.getGender().equals(Gender.MALE)) genderGroup.setValue(male);
		else genderGroup.setValue(female);

		shirtsize.setSimpleValue(me.getShirtSize());
		email.setValue(me.getEmail());
		cellphone.setValue(me.getCellPhone());
		mb.setValue(me.getMailBox());
		la.setValue(me.getLocalAddress());
		homephone.setValue(me.getHomePhone());
		ha.setValue(me.getHomeAddress());
		sn.setValue(me.getSocialNetworks()); 

		hd.setValue(me.getHireDate());
		
		if (me.isWorkStudy()) wsGroup.setValue(federal);
		else wsGroup.setValue(department);

		st.setValue(me.getSpecialTraining()); 
		goals.setValue(me.getGoals());
		rankslider.setValue(me.getRanking());	
		comm.setValue(me.getComments());
		fst.setValue(me.getFiveStarTickets());
	}

	/**
	 * Retrieves information from the form and updates the local employee object
	 */
	private void updateMe(){
		me.setIdNumber(idnum.getValue());
		me.setFirstName(firstname.getValue());
		me.setLastName(lastname.getValue());
		me.setNickname(nickname.getValue());
		me.setDateOfBirth(dob.getValue());
		if (genderGroup.getValue().equals(male)) me.setGender(Gender.MALE);
		else me.setGender(Gender.FEMALE);

		me.setShirtSize(shirtsize.getSimpleValue()); 
		me.setEmail(email.getValue());

		me.setCellPhone(cellphone.getValue());
		me.setMailBox(mb.getValue());
		me.setLocalAddress(la.getValue());
		me.setHomePhone(homephone.getValue());
		me.setHomeAddress(ha.getValue());
		me.setSocialNetworks(sn.getValue());	

		me.setHireDate(hd.getValue());
		if (wsGroup.getValue().equals(federal)) me.setWorkStudy(true);
		else me.setWorkStudy(false);

		me.setSpecialTraining(st.getValue());
		me.setGoals(goals.getValue());
		me.setRanking(rankslider.getValue());
		me.setComments(comm.getValue());
		me.setFiveStarTickets(fst.getValue());
	}

	/**
	 * Toggles the view/edit button between view/edit views 
	 */
	private void toggleVEButton(){
		String text = editButton.getText();
		setMode(text);

		if(text.equals("Edit")){
			editButton.setText("View");
			editButton.setToolTip("Click to switch to view only mode");
		}else{
			editButton.setText("Edit");
			editButton.setToolTip("Click to allow editing of user profile");
		}
	}

	private void setMode(String mode){
		boolean edit = false;
		boolean employee = false;
		boolean manager = false;

		if (HD_MQP.getPermission() == Permissions.MANAGER){
			manager = true;
		}else{
			employee = true;
		}
		
		if (mode.equals("Edit")){
			edit = true;
		}
				
		//NOTE: ID is invisible to employees
		idnum.setVisible(manager);
		
		idnum.setEnabled(edit && (manager));
		firstname.setEnabled(edit && (manager));
		lastname.setEnabled(edit && (manager));
		nickname.setEnabled(edit && (employee || manager));
		dob.setEnabled(edit && (manager));
		genderGroup.setEnabled(edit && (manager));
		shirtsize.setEnabled(edit && (employee || manager));
		email.setEnabled(edit && (employee || manager));
		cellphone.setEnabled(edit && (employee || manager));
		mb.setEnabled(edit && (employee || manager));
		la.setEnabled(edit && (employee || manager));
		homephone.setEnabled(edit && (employee || manager));
		ha.setEnabled(edit && (employee || manager));
		sn.setEnabled(edit && (employee || manager));
		
		hd.setEnabled(edit && (manager));
		wsGroup.setEnabled(edit && (manager));
		st.setEnabled(edit && (manager));
		goals.setEnabled(edit && (manager));
		
		rank.setEnabled(edit && (manager));
		rankslider.setEnabled(edit && (manager));
		rankslider.setClickToChange(edit && manager);
		rankslider.setDraggable(edit && manager);
		
		comm.setEnabled(edit && (manager));
		fst.setEnabled(edit && (manager));
		
		saveButton.setVisible(edit);
		positionButton.setVisible(edit && manager);
	}
	
	private void positionForm(){
	
		Iterator<Long> iterator = me.getPositionsKeys().iterator();
		while (iterator.hasNext()){
			Long key = iterator.next();
			PositionForm pf = new PositionForm(key);
			positionsRowLayoutContain.add(pf, new RowData());
		}
		positionsRowLayoutContain.layout();
	}
}
