package hd.client.ui.timesheet;

import hd.client.timesheet.TimesheetService;
import hd.client.timesheet.TimesheetServiceAsync;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.FormEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FileUpload;

public class W2WFileUploadForm extends LayoutContainer{
	
	/** Create a remote service proxy to talk to the server-side Timesheet service. */
	private final TimesheetServiceAsync TimesheetService = GWT.create(TimesheetService.class);
	
	/*
	 * (non-Javadoc)
	 * @see com.extjs.gxt.ui.client.widget.LayoutContainer#onRender(com.google.gwt.user.client.Element, int)
	 */
	@Override
	protected void onRender(Element parent, int index) {  
		super.onRender(parent, index);  
		setStyleAttribute("margin", "10px");  

		final FormPanel panel = new FormPanel();  
		panel.setHeading("When 2 Work Schedule Upload");  
		panel.setFrame(true);  
	//	panel.setAction("/hd_mqp/w2w");  
	//	panel.setEncoding(Encoding.MULTIPART);  
	//	panel.setMethod(Method.POST);  
		panel.setButtonAlign(HorizontalAlignment.CENTER);  
		panel.setWidth(350);  

		TextField<String> name = new TextField<String>();  
		name.setFieldLabel("Name");  
		panel.add(name);  
		
		final TextArea xml = new TextArea();  
		xml.setFieldLabel("XML");  
		panel.add(xml);  
		
		
		FileUpload file = new FileUpload();  
		//file.setAllowBlank(false);  
		file.setName("File");  
		panel.add(file);  

		panel.addListener(Events.Submit, new Listener<FormEvent>() {

			@Override
			public void handleEvent(FormEvent be) {
				Window win = new Window();
				win.setTitle("File Upload Results");
				win.addText(be.getResultHtml());
				win.render(panel.getElement());
				win.show();
			}
		});


		Button btn = new Button("Reset");  
		btn.addSelectionListener(new SelectionListener<ButtonEvent>() {  
			@Override  
			public void componentSelected(ButtonEvent ce) {  
				panel.reset();  
			}  
		});  
		panel.addButton(btn);  

		btn = new Button("Submit");  
		btn.addSelectionListener(new SelectionListener<ButtonEvent>() {  
			@Override  
			public void componentSelected(ButtonEvent ce) {  
				Info.display("Send Button", "Clicked");
				/*if (!panel.isValid()) {  
					return;  
				} */
				//panel.submit();
				TimesheetService.uploadXML(xml.getValue(), new AsyncCallback<String>(){

					@Override
					public void onFailure(Throwable caught) {
						Info.display("XML Returned", "Server Error");	
					}

					@Override
					public void onSuccess(String result) {
						Info.display("XML Returned", "Success");
						Window win = new Window();
						win.setTitle("File Upload Results");
						win.addText(result);
						win.render(panel.getElement());
						win.show();
					}
					
				});
				
				MessageBox.info("Action", "Uploading File", null);
				
			}  
		});  
		panel.addButton(btn);  

		add(panel);  
	}  


}
