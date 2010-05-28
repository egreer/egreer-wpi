package hd.client;

import hd.client.profile.Position;
import hd.client.profile.UserService;
import hd.client.profile.UserServiceAsync;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.data.BeanModelFactory;
import com.extjs.gxt.ui.client.data.BeanModelLookup;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.event.WindowEvent;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;

/** GUI listing of positions 
 * 
 * @author Eric Greer (egreer@alum.wpi.edu)
 * @author Jason Codding (jcodding@alum.wpi.edu)
 * WPI Helpdesk MQP 2009-2010
 */
public class ManagerPositionsListing extends LayoutContainer {  

	private ContentPanel outerContainer = new ContentPanel();
	private final ListStore<BeanModel> store = new ListStore<BeanModel>();
	private ColumnModel cm;

	/**
	 * Create a remote service proxy to talk to the server-side User service.
	 */
	private final UserServiceAsync UserService = GWT.create(UserService.class);
	BeanModelFactory factory = BeanModelLookup.get().getFactory(Position.class);

	@Override
	protected void onRender(Element parent, int index) {  
		super.onRender(parent, index);
		createManagerPositionListing();
		//setLayout(centerLayout);
		add(outerContainer);
		Info.display("Position Grid", "Rendered");
	}


	/** Creates the GUI Components*/
	@SuppressWarnings("unchecked")
	private void createManagerPositionListing() { 

		List<ColumnConfig> columns = new ArrayList<ColumnConfig>();  

		ColumnConfig column = new ColumnConfig();  
		column.setId("title");  
		column.setHeader("Title");  

		column.setWidth(200);  
		columns.add(column);


		column = new ColumnConfig();  
		column.setId("rateOfPay"); 
		column.setHeader("Rate of Pay");  
		column.setWidth(200);  
		columns.add(column);  

		column = new ColumnConfig();  
		column.setId("description");  
		column.setHeader("Description");  
		column.setAlignment(HorizontalAlignment.RIGHT);  
		column.setWidth(75);  
		columns.add(column);  

		cm = new ColumnModel(columns);  


		Grid<BeanModel> grid = new Grid<BeanModel>(store, cm);  

		grid.setStyleAttribute("borderTop", "none");  
		grid.setAutoExpandColumn("title");  
		grid.setBorders(true);  
		grid.setStripeRows(true);
		grid.setSize(600, 450);
		
		grid.addListener(Events.CellDoubleClick, new Listener<GridEvent>() {
			public void handleEvent(GridEvent be) {
				
				if (be.getType() == Events.CellDoubleClick) {
					final Long key = (Long) store.getAt(be.getRowIndex()).get("positionKey");
					Info.display("Cell Double clicked: ",be.getRowIndex()+" "+be.getColIndex() + "Key " + key);
					
					PositionWindow epw = new PositionWindow(key, "Edit");
					epw.render(outerContainer.getElement());
					
					epw.addListener(Events.Hide, new Listener<WindowEvent>(){

						@Override
						public void handleEvent(WindowEvent be) {
							updateModel(key);
						}
					});
					epw.show();
				}
			}
		});

		outerContainer.setHeading("Positions");
		outerContainer.setBodyBorder(false);
		outerContainer.setAutoHeight(true);
		outerContainer.setAutoWidth(true);

		outerContainer.add(grid);
		
		final PositionWindow createWindow = new PositionWindow(null, "Create");
		
		outerContainer.add(createWindow);
		createWindow.setShadow(false);
		
		createWindow.addListener(Events.Hide, new Listener<WindowEvent>(){

			@Override
			public void handleEvent(WindowEvent be) {
				Position p = createWindow.pf.getPosition();
				Long key = p.getPositionKey();
				createWindow.pf.reset();
				
				if (key != null){
					updateModel(key);
					Info.display("Update Grid", "I should");
				}
				else Info.display("Update Grid", "I should not");
			}
		});
		
		SelectionListener<ButtonEvent> createListener = new SelectionListener<ButtonEvent>(){

			@Override
			public void componentSelected(ButtonEvent ce) {
				createWindow.show();	
			}
		};
		
		Button createButton = new Button("Create Position", createListener);
				
		outerContainer.addButton(createButton);
		
		populateGrid();
	}

	/** Populates the grid with all the positions */
	void populateGrid(){
		UserService.fetchAllPositions(new AsyncCallback<Collection<Position>>(){

			@Override
			public void onFailure(Throwable caught) {
				Info.display("Retrive Position", "SERVER ERROR");
			}

			@Override
			public void onSuccess(Collection<Position> result) {
				Info.display("Retrive Position", "Sucess Number: " + result.size());
				
				store.add(factory.createModel(result));
			}

		});
	}
	
	/** Updates the listing of a particular position
	 * 
	 * @param key	The key of the position to update
	 */
	void updateModel(Long key){
		final Long key2 = key; 
		UserService.getPosition(key2, new AsyncCallback<Position>(){

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
			
		});
	}
}

