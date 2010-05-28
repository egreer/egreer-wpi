package hd.client;

import hd.client.entities.Account;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;

/** GUI component that processes change passwords  
 * 
 * @author Eric Greer (egreer@alum.wpi.edu)
 * @author Jason Codding (jcodding@alum.wpi.edu)
 * WPI Helpdesk MQP 2009-2010
 *
 */
public class ChangePasswordForm extends LayoutContainer {  
  
  final FormPanel simple = new FormPanel();  
  
  /** Create a remote service proxy to talk to the server-side Login service. */
  private final LoginServiceAsync loginService = GWT.create(LoginService.class);
  
  /*
   * (non-Javadoc)
   * @see com.extjs.gxt.ui.client.widget.LayoutContainer#onRender(com.google.gwt.user.client.Element, int)
   */
  @Override  
  protected void onRender(Element parent, int index) {  
    super.onRender(parent, index);  
    createLoginForm();
    add(simple);
  }  
  
  /** Creates the Login form GUI components */
  private void createLoginForm() {  

    simple.setHeading("Change Password");
    simple.setFrame(true);
    simple.setWidth(350);
  
    final TextField<String> password = new TextField<String>();  
    password.setFieldLabel("Old Password");
    password.setLabelSeparator(":");
    password.setAllowBlank(false);
    password.setPassword(true);
    simple.add(password);  
  
    final TextField<String> newpassword = new TextField<String>();  
    newpassword.setFieldLabel("New Password");  
    newpassword.setLabelSeparator(":");
    newpassword.setAllowBlank(false);
    newpassword.setPassword(true);
    simple.add(newpassword);
    
    final TextField<String> confirmpassword = new TextField<String>();  
    confirmpassword.setFieldLabel("Confirm Password");
    confirmpassword.setLabelSeparator(":");
    confirmpassword.setAllowBlank(false);
    confirmpassword.setPassword(true);
    simple.add(confirmpassword);
    
    //Button Creates a account and sends it to the server
    SelectionListener<ButtonEvent> listener = new SelectionListener<ButtonEvent>(){

		public void componentSelected(ButtonEvent ce) {
			if (!simple.isValid()) return; //Validates the form 
			Info.display("Click Event", "The login button was clicked.");
			 
			 final String uname = HD_MQP.getUsername();
			 final String pwd = password.getValue();
			 			 
			 if(uname != null && pwd != null){
				 Account login = new Account(uname, pwd);
				 
			 loginService.changePassword(login, newpassword.getValue(), confirmpassword.getValue(), new AsyncCallback<Boolean>() {
						public void onFailure(Throwable caught) {
							Info.display("Change Password", "Error Validating");
						}

						public void onSuccess(Boolean result) {
							if (result)Info.display("Change Password", "Success");
							else Info.display("Change Password", "Failure");
						}
					});
		  
		}}
		
		//public void componentSelected(KeyEvent ed){
			//	TODO Enter
		//}
		
	};
    
    
    Button changeButton = new Button("Change", listener);  
    simple.addButton(changeButton);  
    FormButtonBinding binding = new FormButtonBinding(simple);  
	binding.addButton(changeButton);
    simple.setButtonAlign(HorizontalAlignment.CENTER);     
  }
}