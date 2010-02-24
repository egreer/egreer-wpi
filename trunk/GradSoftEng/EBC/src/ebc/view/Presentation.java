package ebc.view;

import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.Label;

import ebc.model.Model;

/**
 * The containing Frame in which the views are found.
 * 
 * Note how all methods are 'package private' to allow a class within the same package
 * to be able to access the GUI elements to install controllers (as needed) to respond
 * to events. 
 * 
 * The view is typically unable to know the full set of controllers which may be desired
 * and so we defer that logic to an external class.
 *  
 */
public class Presentation extends Frame {

	// Including this conforms to the JDK serialization model and avoids an Eclipse warning.
	private static final long serialVersionUID = 1L;
	
	// GUI widgets for displaying information.
	private ControllerView controlPanel = null;
	private SquareDrawingCanvas viewOne = null;
	private TextView viewTwo = null;

	private Label labelView = null;
	private Label labelV2 = null;

	// The model being displayed
	Model model;
	
	/**
	 * This is the default constructor
	 */
	public Presentation(Model m) {
		super();
		
		this.model = m;
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	void initialize() {
		labelV2 = new Label();
		labelV2.setBounds(new Rectangle(388, 260, 102, 23));
		labelV2.setText("View Two");
		labelView = new Label();
		labelView.setBounds(new Rectangle(375, 41, 150, 23));
		labelView.setText("View One");
		this.setLayout(null);
		this.setSize(633, 490);
		this.setTitle("Frame");

		this.add(getControlPanel(), null);
		this.add(getViewOne(), null);
		this.add(labelView, null);
		this.add(labelV2, null);
		this.add(getViewTwo(), null);
	}
	
	/**
	 * This method initializes viewOne	
	 * 	
	 * @return java.awt.Panel	
	 */
	ControllerView getControlPanel() {
		if (controlPanel == null) {
			controlPanel = new ControllerView(model);
			controlPanel.setBounds(new Rectangle(15, 70, 256, 148));
		}
		return controlPanel;
	}
	
	/**
	 * This method initializes viewOne	
	 * 	
	 * @return java.awt.Panel	
	 */
	SquareDrawingCanvas getViewOne() {
		if (viewOne == null) {
			viewOne = new SquareDrawingCanvas(model);
			viewOne.setBounds(new Rectangle(300, 70, 256, 148));
		}
		return viewOne;
	}

	/**
	 * This method initializes viewTwo	
	 * 	
	 * @return java.awt.Panel	
	 */
	TextView getViewTwo() {
		if (viewTwo == null) {
			viewTwo = new TextView (model);
			viewTwo.setBounds(new Rectangle(300, 290, 289, 145));
		}
		
		return viewTwo;
	}

	
}  //  @jve:decl-index=0:visual-constraint="10,10"
