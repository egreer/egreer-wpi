package yostinso;

import java.awt.Label;
import java.awt.Panel;
import java.awt.Window;


/**
 * Splash screen for the solitaire plug in...
 */
public class FGSplashScreen extends Window {
	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 3905808582309131318L;
	private Label ivjLabel11 = null;
	private Label ivjLabel12 = null;
	private Label ivjLabel121 = null;
	private Panel ivjPanel1 = null;
/**
 * FGSplashScreen constructor comment.
 */
public FGSplashScreen(java.awt.Frame parent) {
	super(parent);
	initialize();
}
/**
 * Return the Label11 property value.
 * @return java.awt.Label
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private java.awt.Label getLabel11() {
	if (ivjLabel11 == null) {
		try {
			ivjLabel11 = new java.awt.Label();
			ivjLabel11.setName("Label11");
			ivjLabel11.setAlignment(java.awt.Label.LEFT);
			ivjLabel11.setText("foo");
			ivjLabel11.setBackground(java.awt.Color.white);
			ivjLabel11.setBounds(26, 45, 362, 18);
			// user code begin {1}
			// user code end
		} catch (java.lang.Throwable ivjExc) {
			// user code begin {2}
			// user code end
			handleException(ivjExc);
		}
	}
	return ivjLabel11;
}
/**
 * Return the Label12 property value.
 * @return java.awt.Label
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private java.awt.Label getLabel12() {
	if (ivjLabel12 == null) {
		try {
			ivjLabel12 = new java.awt.Label();
			ivjLabel12.setName("Label12");
			ivjLabel12.setAlignment(java.awt.Label.CENTER);
			ivjLabel12.setText("FlowerGarden");
			ivjLabel12.setBackground(java.awt.Color.white);
			ivjLabel12.setBounds(7, 5, 384, 23);
			// user code begin {1}
			// user code end
		} catch (java.lang.Throwable ivjExc) {
			// user code begin {2}
			// user code end
			handleException(ivjExc);
		}
	}
	return ivjLabel12;
}
/**
 * Return the Label121 property value.
 * @return java.awt.Label
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private java.awt.Label getLabel121() {
	if (ivjLabel121 == null) {
		try {
			ivjLabel121 = new java.awt.Label();
			ivjLabel121.setName("Label121");
			ivjLabel121.setAlignment(java.awt.Label.CENTER);
			ivjLabel121.setText("E.O. Stinson");
			ivjLabel121.setBackground(java.awt.Color.white);
			ivjLabel121.setBounds(7, 23, 384, 23);
			// user code begin {1}
			// user code end
		} catch (java.lang.Throwable ivjExc) {
			// user code begin {2}
			// user code end
			handleException(ivjExc);
		}
	}
	return ivjLabel121;
}
/**
 * Return the Panel1 property value.
 * @return java.awt.Panel
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private java.awt.Panel getPanel1() {
	if (ivjPanel1 == null) {
		try {
			ivjPanel1 = new java.awt.Panel();
			ivjPanel1.setName("Panel1");
			ivjPanel1.setLayout(null);
			ivjPanel1.setBackground(java.awt.Color.white);
			ivjPanel1.setBounds(6, 5, 386, 60);
			// user code begin {1}
			// user code end
		} catch (java.lang.Throwable ivjExc) {
			// user code begin {2}
			// user code end
			handleException(ivjExc);
		}
	}
	return ivjPanel1;
}
/**
 * Called whenever the part throws an exception.
 * @param exception java.lang.Throwable
 */
private void handleException(java.lang.Throwable exception) {

	/* Uncomment the following lines to print uncaught exceptions to stdout */
	// System.out.println("--------- UNCAUGHT EXCEPTION ---------");
	// exception.printStackTrace(System.out);
}
/**
 * Initialize the class.
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private void initialize() {
	try {
		// user code begin {1}
		// user code end
		setName("FGSplashScreen");
		setLayout(null);
		setBackground(java.awt.Color.red);
		setSize(397, 71);
		add(getLabel11(), getLabel11().getName());
		add(getLabel12(), getLabel12().getName());
		add(getLabel121(), getLabel121().getName());
		add(getPanel1(), getPanel1().getName());
	} catch (java.lang.Throwable ivjExc) {
		handleException(ivjExc);
	}
	// user code begin {2}
	// user code end
}
/**
 * main entrypoint - starts the part when it is run as an application
 * @param args java.lang.String[]
 */
public static void main(java.lang.String[] args) {
	try {
		FGSplashScreen aFGSplashScreen = new FGSplashScreen(new java.awt.Frame());
		aFGSplashScreen.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent e) {
				System.exit(0);
			};
		});
		aFGSplashScreen.setVisible(true);
	} catch (Throwable exception) {
		System.err.println("Exception occurred in main() of java.awt.Window");
		exception.printStackTrace(System.out);
	}
}
public void setContentsText(String s) {
	getLabel11().setText (s);
}
}
