package lpf.GUIs;

import java.awt.Label;
import java.awt.Button;

import javax.swing.JFrame;

/**
 * Generic Test Harness needed (at times) to test GUI code.
 * 
 * @author heineman
 * @modified Eric Greer
 */
public class TestHarness extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5842183914756226661L;
	
	/** Area where instructions are to be placed. */
	private Label label = null;
	private Label testName = null;
	private Label testNameBeingRunLabel = null;
	private Button doneButton = null;
	private Button failureButton = null;
	private Label label1 = null;
	private Label instructionsLabel = null;

	private InteractiveTester suite;
	
	/**
	 * This is the default constructor
	 */
	public TestHarness() {
		super();
		initialize();
	}
	
	/**
	 * Set the test being run.
	 * 
	 * @param name   Name of the test.
	 */
	public void setTest (InteractiveTester suite, String name) {
		testNameBeingRunLabel.setText(name);
		this.suite = suite;
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		instructionsLabel = new Label();
		instructionsLabel.setBounds(new java.awt.Rectangle(110,109,436,23));
		instructionsLabel.setName("instructionsLabel");
		instructionsLabel.setText("");
		label1 = new Label();
		label1.setText("Instructions:");
		label1.setLocation(new java.awt.Point(13,108));
		label1.setSize(new java.awt.Dimension(78,23));
		testNameBeingRunLabel = new Label();
		testNameBeingRunLabel.setText("");
		testNameBeingRunLabel.setLocation(new java.awt.Point(109,76));
		testNameBeingRunLabel.setSize(new java.awt.Dimension(570,23));
		testName = new Label();
		testName.setText("Test Name:");
		testName.setLocation(new java.awt.Point(13,76));
		testName.setSize(new java.awt.Dimension(83,23));
		label = new Label();
		label.setText("This window is necessary to automate the testing of the JUnit test cases. The frame will close once the tests are complete.");
		label.setLocation(new java.awt.Point(13,44));
		label.setSize(new java.awt.Dimension(676,23));
		
		this.setLayout(null);
		this.setSize(724, 657);
		this.setTitle("Test Harness");
		this.add(label, null);
		this.add(testName, null);
		this.add(testNameBeingRunLabel, null);
		this.add(getDoneButton(), null);
		this.add(getFailureButton(), null);
		this.add(label1, null);
		this.add(instructionsLabel, null);
		
		// user defined extensions
		
	}

	/**
	 * This method initializes doneButton	
	 * 	
	 * @return java.awt.Button	
	 */
	private Button getDoneButton() {
		if (doneButton == null) {
			doneButton = new Button();
			doneButton.setBounds(new java.awt.Rectangle(553,110,71,23));
			doneButton.setLabel("Success!");
			doneButton.setName("succeedButton");
			doneButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					suite.testSucceeds();
				}
			});
		}
		return doneButton;
	}

	/**
	 * This method initializes failureButton	
	 * 	
	 * @return java.awt.Button	
	 */
	private Button getFailureButton() {
		if (failureButton == null) {
			failureButton = new Button();
			failureButton.setLabel("Failure!");
			failureButton.setSize(new java.awt.Dimension(70,23));
			failureButton.setLocation(new java.awt.Point(630,110));
			failureButton.setName("failureButton");
			failureButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					suite.testFails();
				}
			});
		}
		return failureButton;
	}

	/**
	 * Set the instruction text accordingly.
	 * 
	 * @param instructions
	 */
	public void setInstructions(String instructions) {
		this.instructionsLabel.setText(instructions);
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
