package ebc.view;

import java.awt.Label;
import java.awt.Panel;
import java.awt.Rectangle;
import java.awt.TextField;

import ebc.model.IModelUpdated;
import ebc.model.Model;
import ebc.model.Value;

public class TextView extends Panel implements IModelUpdated {

	/** Model Elements to be viewed. */
	Value height = null;
	Value width = null;
	Value color = null;
	
	/** GUI elements. */
	private TextField textFieldH = null;
	private TextField textFieldW = null;
	private TextField textFieldC = null;
	private Label labelH1 = null;
	private Label labelW1 = null;
	private Label labelC1 = null;
	
	/** Avoids warning in Eclipse. */
	private static final long serialVersionUID = 1L;

	/** Construct view of the model. */
	public TextView (Model m) {
		super ();
		
		height = m.getHeight();
		color = m.getColor();
		width = m.getWidth();
		
		initialize();
	}
	
	/**
	 * This method initializes viewTwo	
	 * 	
	 * @return java.awt.Panel	
	 */
	void initialize() {
		labelC1 = new Label();
		labelC1.setBounds(new Rectangle(13, 94, 48, 23));
		labelC1.setText("Color:");
		labelW1 = new Label();
		labelW1.setBounds(new Rectangle(13, 63, 53, 23));
		labelW1.setText("Width:");
		labelH1 = new Label();
		labelH1.setBounds(new Rectangle(13, 26, 45, 23));
		labelH1.setText("Height:");

		setLayout(null);
		setBounds(new Rectangle(300, 290, 289, 145));
		add(getTextFieldH(), null);
		add(getTextFieldW(), null);
		add(getTextFieldC(), null);
		add(labelH1, null);
		add(labelW1, null);
		add(labelC1, null);
	}

	/**
	 * This method initializes textFieldH	
	 * 	
	 * @return java.awt.TextField	
	 */
	TextField getTextFieldH() {
		if (textFieldH == null) {
			textFieldH = new TextField();
			textFieldH.setEditable(false);
			textFieldH.setBounds(new Rectangle(83, 26, 109, 23));
		}
		return textFieldH;
	}

	/**
	 * This method initializes textFieldW	
	 * 	
	 * @return java.awt.TextField	
	 */
	TextField getTextFieldW() {
		if (textFieldW == null) {
			textFieldW = new TextField();
			textFieldW.setEditable(false);
			textFieldW.setBounds(new Rectangle(83, 61, 106, 23));
		}
		return textFieldW;
	}

	/**
	 * This method initializes textFieldC	
	 * 	
	 * @return java.awt.TextField	
	 */
	TextField getTextFieldC() {
		if (textFieldC == null) {
			textFieldC = new TextField();
			textFieldC.setEditable(false);
			textFieldC.setBounds(new Rectangle(83, 95, 106, 23));
		}
		return textFieldC;
	}

	/** Respond to changes in the underlying model. */
	public void modelChanged() {
		textFieldH.setText("" + height.getValue());
		textFieldW.setText("" + width.getValue());
		textFieldC.setText("" + color.getValue());
	}
}
