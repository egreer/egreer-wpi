package hd.client;

import hd.client.entities.Permissions;

import java.util.Arrays;
import java.util.List;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.event.WindowEvent;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;

/** GUI wizard for creating a new employee beginning with accounts and then 
 * personal information 
 * 
 * @author Eric Greer (egreer@alum.wpi.edu)
 * @author Jason Codding (jcodding@alum.wpi.edu)
 * WPI Helpdesk MQP 2009-2010
 *
 */
public class CreateEmployeeWindow extends LayoutContainer{

	static final Window window = new Window();

	static FormPanel create = new FormPanel();
	static EmployeeProfileForm epf; 
	final static TextField<String> newusername = new TextField<String>();
	final TextField<String> newpassword = new TextField<String>();
	final TextField<String> newconfpassword = new TextField<String>();
	final SimpleComboBox<Permissions> permissions = new SimpleComboBox<Permissions>();

	private final LoginServiceAsync loginService = GWT
	.create(LoginService.class);

	Listener<WindowEvent> hide = new Listener<WindowEvent>(){

		@Override
		public void handleEvent(WindowEvent be) {
			newusername.clear();
			newpassword.clear();
			newconfpassword.clear();
			permissions.clear();
			window.remove(epf);
			window.add(create);
		}
	};

	/*
	 * (non-Javadoc)
	 * @see com.extjs.gxt.ui.client.widget.LayoutContainer#onRender(com.google.gwt.user.client.Element, int)
	 */
	@Override  
	protected void onRender(Element parent, int pos) {  
		super.onRender(parent, pos);  
		setLayout(new FlowLayout(10));  

		//window.setSize(500, 300);
		window.setAutoHeight(true);
		window.setAutoWidth(true);
		window.setPlain(true);  
		window.setModal(true);  
		window.setBlinkModal(true);  
		window.setHeading("Hello Window");  
		window.setLayout(new FitLayout());  
		window.addListener(Events.Hide, hide); 

		create.setHeading("Create New Account");  
		create.setFrame(true);  
		create.setWidth(350);  


		newusername.setFieldLabel("Username");  
		newusername.setAllowBlank(false);  
		create.add(newusername);  


		newpassword.setFieldLabel("Password");  
		newpassword.setAllowBlank(false);
		newpassword.setPassword(true);
		create.add(newpassword);  


		newconfpassword.setFieldLabel("Confirm Password");  
		newconfpassword.setAllowBlank(false);
		newconfpassword.setPassword(true);
		create.add(newconfpassword);  

		List<Permissions> sizes = Arrays.asList(Permissions.values());
		permissions.setWidth(200);
		permissions.setHeight(22);
		permissions.setBorders(false);
		permissions.setFieldLabel("Permissions");
		permissions.setLabelSeparator(":");
		permissions.setAllowBlank(true);
		permissions.setForceSelection(true);
		permissions.setTriggerAction(TriggerAction.ALL);
		permissions.add(sizes);
		create.add(permissions);

		//Button Creates a account and sends it to the server
		SelectionListener<ButtonEvent> listener2 = new SelectionListener<ButtonEvent>(){

			public void componentSelected(ButtonEvent ce) {
				if (!create.isValid()) return; //Validates the form 
				Info.display("Click Event", "The create button was clicked.");

				String uname = newusername.getValue();
				String pwd = newpassword.getValue();
				String confpwd = newconfpassword.getValue();
				Permissions perm = permissions.getSimpleValue();


				if(uname != null && pwd != null && confpwd != null){			 
					
					loginService.createAccount(uname, pwd, confpwd, perm, new AsyncCallback<Boolean>() {
						public void onFailure(Throwable caught) {
							Info.display("Account Creation Error", "Server Error");
						}

						public void onSuccess(Boolean result) {
							if (result){
								Info.display("Account Created", "Success");
								CreateEmployeeWindow.switchToEmployee();
							}
							else Info.display("Account Not Created", "Failure");

						}
					});

				}}

		};


		Button createButton = new Button("Create", listener2);  
		create.addButton(createButton);  

		create.setButtonAlign(HorizontalAlignment.CENTER);  

		FormButtonBinding binding2 = new FormButtonBinding(create);  
		create.addButton(createButton);    
		window.add(create);
	}

	/** Switches the form from account creation to employee form*/
	private static void switchToEmployee(){
		window.remove(create);
		epf = new EmployeeProfileForm(newusername.getValue());
		window.add(epf);
	}
}