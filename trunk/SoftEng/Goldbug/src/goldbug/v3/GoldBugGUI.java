package goldbug.v3;

import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.text.MaskFormatter;


public class GoldBugGUI extends JFrame {

	/** Avoids pesky Eclipse warnings. */
	private static final long serialVersionUID = 1L;
	
	/** Size of each line. */
	public static final int chunk = 64;
	
	/** Code. */
	Code code;
	
	/** Drawing canvas. */
	JEditorPane codeArea;
	
	public GoldBugGUI (Code code) {
		super();
		this.code = code;
		
		initialize();
	}
	
	public void redrawState() {
		updateText();
	}
	
	/** Update interior text. Use nice formatting. */ 
	private void updateText() {
		String enc = code.toString();
		String dec = code.decoded();
		
		int idx = 0;
		StringBuilder results = new StringBuilder("<pre>");
		while (idx < enc.length()) {
			String s = enc.substring(idx, Math.min(enc.length(), idx+chunk));
			//codeArea.setSelectedTextColor(c)
			results.append(s).append("\n");
			
			s = dec.substring(idx, Math.min(dec.length(), idx+chunk));
			results.append("<font color=\"red\">");
			results.append(s).append("</font>\n\n");
			
			idx += chunk;
		}
		
		results.append("</pre>");
		
		codeArea.setText (results.toString());
	}
	
	JPanel createLetters() {
		Iterator<Character> it = code.chars();
		int ct = 0;
		while (it.hasNext()) {
			ct++; it.next();
		}
		
		JPanel jp = new JPanel();
		jp.setLayout(new GridLayout (ct,2));
		
		it = code.chars();
		while (it.hasNext()) {
			final char ch = it.next();
			JButton jb = new JButton ("" + ch);
			jp.add (jb);
			try {
				final JFormattedTextField jt = new JFormattedTextField(new MaskFormatter("*"));
				
				jt.setText(""+code.retrieve(ch));
				jp.add (jt);
				
				jt.addKeyListener(new KeyListener() {

					public void keyPressed(KeyEvent e) {}

					public void keyReleased(KeyEvent e) {}

					public void keyTyped(KeyEvent e) {
						// find current value and key typed.
						char c1 = e.getKeyChar();
						if (c1 == '\b') {
							// backspace: remove mapping.
							code.clear(ch);
						} else {
							code.assign(ch, c1);
						}
						
						redrawState();  // !!! UPDATE !!!
					}
				});
			} catch (Exception e) { }
		}
		
		return jp;
	}
	
	/**
	 * This method initializes this GUI
	 * 
	 * @return void
	 */
	void initialize() {
		this.setLayout(null);
		this.setSize(680, 620);
		this.setTitle("Frame");
		
		codeArea = new JEditorPane ();
		codeArea.setContentType("text/html");
		codeArea.setBounds (32, 32, 505, 240);
		codeArea.setEditable(false);
		JScrollPane js = new JScrollPane (codeArea);
		js.setBounds(32, 32, 545, 280);

		// create right palette with buttons for codes.
		JPanel jp = createLetters();
		jp.setBounds(580, 32, 90, 480);
		add(jp);
		
		// set up text.
		updateText();
		
		add(js);	
	}

}
