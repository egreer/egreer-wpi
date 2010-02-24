package ebc;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import ebc.model.Model;
import ebc.view.Presentation;

public class PresentationMockUp {
	public static void main(String[] args) {
		Model m = new Model();
		final Presentation p = new Presentation(m);
		p.setVisible(true);		
		
		p.addWindowListener (new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				p.dispose();
			}
		});
	}
}
