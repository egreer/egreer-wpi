package hd.client;

import hd.client.entities.Account;
import hd.client.entities.Permissions;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;


/** GUI component that processes login
 * 
 * @author Eric Greer (egreer@alum.wpi.edu)
 * @author Jason Codding (jcodding@alum.wpi.edu)
 * WPI Helpdesk MQP 2009-2010
 *
 */
public class LoginForm extends LayoutContainer {  

	private VerticalPanel vp;  

	private FormData formData;  
	private CreateEmployeeWindow cew = new CreateEmployeeWindow();

	/** Create a remote service proxy to talk to the server-side Login service. */
	private final LoginServiceAsync loginService = GWT
	.create(LoginService.class);

/*
 * (non-Javadoc)
 * @see com.extjs.gxt.ui.client.widget.LayoutContainer#onRender(com.google.gwt.user.client.Element, int)
 */
	@Override  
	protected void onRender(Element parent, int index) {  
		super.onRender(parent, index);  
		formData = new FormData("-20");  
		vp = new VerticalPanel();  
		vp.setSpacing(10);  
		createLoginForm();   
		add(vp);  
	}  

	/** Creates the GUI components for login*/
	private void createLoginForm() {  
		final FormPanel simple = new FormPanel();  
		simple.setHeading("Login");
		simple.setFrame(true);
		simple.setWidth(350);

		final TextField<String> username = new TextField<String>();  
		username.setFieldLabel("Username");  
		username.setAllowBlank(false);  
		simple.add(username, formData);  

		final TextField<String> password = new TextField<String>();  
		password.setFieldLabel("Password");  
		password.setAllowBlank(false);
		password.setPassword(true);
		simple.add(password, formData);  

		//Button Creates a account and sends it to the server
		SelectionListener<ButtonEvent> listener = new SelectionListener<ButtonEvent>(){

			public void componentSelected(ButtonEvent ce) {
				if (!simple.isValid()) return; //Validates the form 
				Info.display("Click Event", "The login button was clicked.");

				final String uname = username.getValue();
				final String pwd = password.getValue();

				if(uname != null && pwd != null){
					Account loginAcct = new Account(uname, pwd);



					loginService.validateLogin(loginAcct, new AsyncCallback<Boolean>() {
						public void onFailure(Throwable caught) {
							Info.display("Login Authentication", "Error Validating");
						}

						public void onSuccess(Boolean result) {
							if (result){
								Info.display("Login Authentication", "Success");
								HD_MQP.username = uname;

								//Get Permissions
								loginService.getPermissions(uname, new AsyncCallback<Permissions>() {
									public void onFailure(Throwable caught) {
										Info.display("Permissions Retrieval", "Error Retrieving Permissions");
									}

									public void onSuccess(Permissions result) {
										if (result != null){				
											HD_MQP.permission = result;
											Info.display("Permissions Retrieval", "Result: " + result);
										}else Info.display("Permissions Retrieval", "Result is Null ");
										HD_MQP.loginUser();
									}
								});
							}
							else Info.display("Login Authentication", "Failure");

						}
					});




				}}

			//public void componentSelected(KeyEvent ed){
			//	TODO Enter
			//}

		};


		Button loginButton = new Button("Login", listener);  
		simple.addButton(loginButton);  

		//Register Button listener
		vp.add(cew);
		SelectionListener<ButtonEvent> listener3 = new SelectionListener<ButtonEvent>(){

			@SuppressWarnings("static-access")
			public void componentSelected(ButtonEvent ce) {
				cew.window.show();
			}
		};

		//XXX move Registration
		Button registerButton = new Button("Create Employee", listener3);  
		simple.addButton(registerButton);  

		simple.setButtonAlign(HorizontalAlignment.CENTER);  

		FormButtonBinding binding = new FormButtonBinding(simple);  
		binding.addButton(loginButton);    
		vp.add(simple);
	}
}