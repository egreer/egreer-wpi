package ebc.view;

import java.awt.Label;
import java.awt.Panel;
import java.awt.Rectangle;
import java.awt.Scrollbar;

import ebc.model.IModelUpdated;
import ebc.model.IValue;
import ebc.model.Model;

/**
 * Graphical elements used to present control widgets to the user.
 * 
 * @author George Heineman
 */
public class ControllerView extends Panel implements IModelUpdated {

	/** Avoids warning in Eclipse. */
	private static final long serialVersionUID = 1L;

	/** Model. */
	Model model;

	private Label labelH = null;
	private Label labelW = null;
	private Scrollbar scrollbarH = null;
	private Scrollbar scrollbarW = null;
	private Scrollbar scrollbarC = null;
	private Label labelC = null;

	public ControllerView (Model m) {
		super();

		this.model = m;

		initialize();
	}

	/**
	 * This method initializes controlPanel	
	 * 	
	 * @return java.awt.Panel	
	 */
	void initialize() {
		labelC = new Label();
		labelC.setBounds(new Rectangle(155, 9, 45, 23));
		labelC.setText("Color");
		labelW = new Label();
		labelW.setBounds(new Rectangle(87, 9, 51, 23));
		labelW.setText("Width");
		labelH = new Label();
		labelH.setText("Height");
		labelH.setBounds(new Rectangle(11, 9, 58, 23));

		setLayout(null);
		setBounds(new Rectangle(14, 44, 226, 167));
		add(labelH, null);
		add(labelW, null);
		add(getScrollbarH(), null);
		add(getScrollbarW(), null);
		add(getScrollbarC(), null);
		add(labelC, null);
	}
	
	/**
	 * This method initializes scrollbarH	
	 * 	
	 * @return java.awt.Scrollbar	
	 */
	public Scrollbar getScrollbarH() {
		if (scrollbarH == null) {
			scrollbarH = new Scrollbar();
			scrollbarH.setBounds(new Rectangle(14, 40, 16, 100));

			IValue val = model.getHeight();
			int pageSize = (val.getMaximum() - val.getMinimum())/8;
			scrollbarH.setValues(val.getValue(), pageSize, val.getMinimum(), val.getMaximum() + pageSize);
		}
		return scrollbarH;
	}

	/**
	 * This method initializes scrollbarV	
	 * 	
	 * @return java.awt.Scrollbar	
	 */
	public Scrollbar getScrollbarW() {
		if (scrollbarW == null) {
			scrollbarW = new Scrollbar();
			scrollbarW.setBounds(new Rectangle(90, 40, 16, 100));

			IValue val = model.getWidth();
			int pageSize = (val.getMaximum() - val.getMinimum())/8;
			scrollbarW.setValues(val.getValue(), pageSize, val.getMinimum(), val.getMaximum() + pageSize);
		}
		return scrollbarW;
	}

	/**
	 * This method initializes scrollbarC	
	 * 	
	 * @return java.awt.Scrollbar	
	 */
	Scrollbar getScrollbarC() {
		if (scrollbarC == null) {
			scrollbarC = new Scrollbar();
			scrollbarC.setBounds(new Rectangle(158, 40, 16, 100));

			IValue val = model.getColor();
			int pageSize = (val.getMaximum() - val.getMinimum())/8;
			scrollbarC.setValues(val.getValue(), pageSize, val.getMinimum(), val.getMaximum() + pageSize);
		}
		return scrollbarC;
	}

	// properly respond to updates to model.
	public void modelChanged() {
		scrollbarH.setValue(model.getHeight().getValue());
		scrollbarW.setValue(model.getWidth().getValue());
	}


}
