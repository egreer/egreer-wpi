package ebc.controller;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import ebc.model.Model;
import ebc.model.Value;
import ebc.view.SquareDrawingCanvas;

public class BoxController extends MouseAdapter {
	// Start point
	Point start = null;
	
	// End point;
	Point end = null;
	
	/** Model. */
	Model model;
	
	int originalHeight;
	int originalWidth;
	
	public BoxController (Model m) {
		this.model = m;
	}
	
	/**
	 * Deal with user events by remembering first start point and subtracting
	 * differences (along y-coordinate for height and x-coordinate for width).
	 * 
	 * Pass these back to the primary view. 
	 */
	public void react() {
		if (start == null || end == null) return;
		
		int newH = end.x - SquareDrawingCanvas.OffsetX;
		int newV = end.y - SquareDrawingCanvas.OffsetY;
		
		// update the model
		int deltaH = newH - model.getWidth().getValue();
		
		if (deltaH < 0) {
			decrementCount (model.getWidth(), -deltaH);
		} else if (deltaH > 0){
			incrementCount (model.getWidth(), deltaH);
		}
		
		int deltaV = newV - model.getHeight().getValue();
		if (deltaV < 0) {
			decrementCount (model.getHeight(), -deltaV);
		} else if (deltaV > 0 ){
			incrementCount (model.getHeight(), deltaV);
		}
		
	}
	
	/** Increment value by a fixed count. */
	public void incrementCount (Value value, int ct) {
		for (int i = 0; i < ct; i++) {
			if (value.getValue() != value.getMaximum()) {
				value.increment();
			}
		}
	}
	
	/** Decrement value by a fixed count. */
	public void decrementCount (Value value, int ct) {
		for (int i = 0; i < ct; i++) {
			if (value.getValue() != value.getMinimum()) {
				value.decrement();
			}
		}
	}

	public void mouseDragged(MouseEvent me) {
		end = me.getPoint();
		react();
	}

	public void mousePressed(MouseEvent me) {
		start = me.getPoint();
		
		originalHeight = model.getHeight().getValue();
		originalWidth = model.getHeight().getValue();
	}

	public void mouseReleased(MouseEvent me) {
		end = me.getPoint();
		
		react();
		start = end = null;
	}

}
