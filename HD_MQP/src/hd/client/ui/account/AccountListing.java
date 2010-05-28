package hd.client.ui.account;

import hd.client.LoginService;
import hd.client.LoginServiceAsync;
import hd.client.entities.Account;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.data.BeanModelFactory;
import com.extjs.gxt.ui.client.data.BeanModelLookup;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;

/** The listing grid view of all accounts
 * 
 * @author Eric Greer (egreer@alum.wpi.edu)
 * @author Jason Codding (jcodding@alum.wpi.edu)
 * WPI Helpdesk MQP 2009-2010
 *
 */
public class AccountListing extends LayoutContainer{

	private ContentPanel outerContainer = new ContentPanel();
	private final ListStore<BeanModel> store = new ListStore<BeanModel>();
	private ColumnModel cm;

	/** Create a remote service proxy to talk to the server-side User service. */
	private final LoginServiceAsync LoginService = GWT.create(LoginService.class);
	BeanModelFactory factory = BeanModelLookup.get().getFactory(Account.class);

	/*
	 * (non-Javadoc)
	 * @see com.extjs.gxt.ui.client.widget.LayoutContainer#onRender(com.google.gwt.user.client.Element, int)
	 */
	@Override
	protected void onRender(Element parent, int index) {  
		super.onRender(parent, index);
		createAccountListing();
		//setLayout(centerLayout);
		add(outerContainer);
		Info.display("Position Grid", "Rendered");
	}
	
	/** Creates the information */
	void createAccountListing(){

		List<ColumnConfig> columns = new ArrayList<ColumnConfig>();  

		ColumnConfig column = new ColumnConfig();  
		column.setId("username");  
		column.setHeader("Username");  

		column.setWidth(200);  
		columns.add(column);


		column = new ColumnConfig();  
		column.setId("permission"); 
		column.setHeader("Permission");  
		column.setWidth(200);  
		columns.add(column);  

		cm = new ColumnModel(columns);  
	
		Grid<BeanModel> grid = new Grid<BeanModel>(store, cm);  

		grid.setStyleAttribute("borderTop", "none");  
		grid.setAutoExpandColumn("username");  
		grid.setBorders(true);  
		grid.setStripeRows(true);
		grid.setSize(600, 450);
		
		grid.addListener(Events.CellDoubleClick, new Listener<GridEvent>() {
			public void handleEvent(GridEvent be) {
				
				if (be.getType() == Events.CellDoubleClick) {
					final Long key = (Long) store.getAt(be.getRowIndex()).get("accountID");
					Info.display("Cell Double clicked: ",be.getRowIndex()+" "+be.getColIndex() + "Key " + key);
					
					/*PositionWindow epw = new PositionWindow(key, "Edit");
					epw.render(outerContainer.getElement());
					
					epw.addListener(Events.Hide, new Listener<WindowEvent>(){

						@Override
						public void handleEvent(WindowEvent be) {
							updateModel(key);
						}
					});
					epw.show();
*/				}
			}
		});

		outerContainer.setHeading("Accounts");
		outerContainer.setBodyBorder(false);
		outerContainer.setAutoHeight(true);
		outerContainer.setAutoWidth(true);

		outerContainer.add(grid);
		
		populateGrid();
	}

	/** Populates the grid with accounts
	 * 
	 */
	void populateGrid(){
		
		LoginService.retrieveAllAccounts(new AsyncCallback<List<Account>>(){

			@Override
			public void onFailure(Throwable caught) {
				Info.display("Retrive Accounts", "SERVER ERROR");
			}

			@Override
			public void onSuccess(List<Account> result) {
				Info.display("Retrive Position", "Sucess Number: " + result.size());
				store.add(factory.createModel(result));
			}
			
		});
	}
	
	/** Updates the model of the Account
	 * 
	 * @param key	The key of the account that was updated
	 */
	void updateModel(Long key){
		//final Long key2 = key; 
		/*UserService.getPosition(key2, new AsyncCallback<Position>(){

			@Override
			public void onFailure(Throwable caught) {
				Info.display("Retrieve Position", "Server Error");
			}

			@Override
			public void onSuccess(Position result) {
				BeanModel gridModel = store.findModel("positionKey", key2);
				store.remove(gridModel);
				Info.display("Remove Models", "Success");
				store.add(factory.createModel(result));
				Info.display("Create Model", "Success");
			}
			
		});*/
	}

}
