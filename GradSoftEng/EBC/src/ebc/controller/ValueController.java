package ebc.controller;

import java.awt.Scrollbar;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import ebc.model.Value;

/**
 * Controller to manage a Value object.
 * 
 * @author George Heineman
 */
public class ValueController  implements AdjustmentListener {

	/** Element being controlled. */
	Value value;
	
	/** Constructor for superclass to store value being managed. */
	public ValueController (Value v) {
		this.value = v;
	}
	
	/** Increment value by a fixed count. */
	public void incrementCount (int ct) {
		for (int i = 0; i < ct; i++) {
			if (value.getValue() != value.getMaximum()) {
				value.increment();
			}
		}
	}
	
	/** Decrement value by a fixed count. */
	public void decrementCount (int ct) {
		for (int i = 0; i < ct; i++) {
			if (value.getValue() != value.getMinimum()) {
				value.decrement();
			}
		}
	}

	/**
	 * Controller to respond to scrollbar events and update the model element accordingly.
	 */
	public void adjustmentValueChanged(AdjustmentEvent e) {
		int type = e.getAdjustmentType();
		
		// act differently based upon the action.
		switch (type) {
		case AdjustmentEvent.UNIT_INCREMENT:
			value.increment();
			break;
		case AdjustmentEvent.UNIT_DECREMENT:
			value.decrement();
			break;
		case AdjustmentEvent.BLOCK_INCREMENT:
			Scrollbar sc = (Scrollbar) e.getSource();  // scrollbar which generated event
			incrementCount (sc.getBlockIncrement());
			break;
		case AdjustmentEvent.BLOCK_DECREMENT:
			Scrollbar dc = (Scrollbar) e.getSource();  // scrollbar which generated event
			decrementCount (dc.getBlockIncrement());
			break;
			
			// dynamically track the changes as they come in 
		case AdjustmentEvent.TRACK:
			int newValue = e.getValue();
			if (newValue < value.getValue()) {
				decrementCount (value.getValue() - newValue);
			} else if (newValue > value.getValue()) {
				incrementCount (newValue - value.getValue());
			}
			break;
		}
	}
}
