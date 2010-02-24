package goldbug.v3;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;


public class Launcher {
	/** Launch GUI by installing window controller on exit. */ 
	public static void main (String args[]) {
		Code c = new Code (
"53++!305))6*;4826)4+.)4+);806*;48!8`60))85;;]8*;:+*8!83(88)5*!;46(;88*96*?;8)*+(;485);5*!2:*+(;4956*2(5*-4)8`8*;4069285);)6!8)4++;1(+9;48081;8:8+1;48!85;4)485!528806*81(+9;48;(88;4(+?34;48)4+;161;:188;+?;"
);				
		GoldBugGUI g = new GoldBugGUI (c);
		
		// This controller is an anonymous class who responds to closing events.
		// by exiting the application.
		g.addWindowListener(new WindowListener() {
			public void windowOpened (WindowEvent e) { }
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		// launch everything and go!
		g.setVisible (true);
		g.redrawState();
	}
}
