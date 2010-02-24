package ebc.view;

import ebc.controller.BoxController;
import ebc.controller.ValueController;
import ebc.model.Model;

/**
 * The class responsible for assigning controllers to the GUI elements within the 
 * Presentation class.
 * 
 * @author George Heineman
 */
public class PresentationConfigurer {

	/** The Presentation object being configured. */
	Presentation presentation;
	
	public PresentationConfigurer (Presentation p) {
		this.presentation = p;
	}
	
	/** 
	 * Configure the internal views to manipulate the model. 
	 * 
	 * Note that the code ugliness below is hidden within this configuration class. It is
	 * worth a discussion at some point to show how to break up this mess.
	 */
	public void configure (Model m) {
		// this code is a bit ugly since it 
		presentation.getControlPanel().getScrollbarH().addAdjustmentListener(new ValueController(m.getHeight()));
		presentation.getControlPanel().getScrollbarW().addAdjustmentListener(new ValueController(m.getWidth()));
		presentation.getControlPanel().getScrollbarC().addAdjustmentListener(new ValueController(m.getColor()));
		
		BoxController bc = new BoxController(m);
		presentation.getViewOne().addMouseListener(bc);
		presentation.getViewOne().addMouseMotionListener(bc);		
		
		// assign views to their listeners. This is a bit inefficient since we are 
		// registering listeners with each value independently although the event
		// coming out of the element will simply report that the model has been updated.
		m.getHeight().addListener(presentation.getViewOne());
		m.getHeight().addListener(presentation.getViewTwo());
		m.getHeight().addListener(presentation.getControlPanel());
		
		m.getWidth().addListener(presentation.getViewOne());
		m.getWidth().addListener(presentation.getViewTwo());
		m.getWidth().addListener(presentation.getControlPanel());
		
		m.getColor().addListener(presentation.getViewOne());
		m.getColor().addListener(presentation.getViewTwo());
		m.getColor().addListener(presentation.getControlPanel());
	}
}
