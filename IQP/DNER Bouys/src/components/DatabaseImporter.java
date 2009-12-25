package components;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.SwingUtilities;
import javax.jnlp.*;

/** 
 * DatabaseImporter class is a sub class of JPanel that implements ActionListener. <br> 
 * 
 * This class contains the interface components, is responsible for handling the file retrieval, and reporting back to the user. <br>   
 * 
 * DatabaseImporter.java must be compiled with jnlp.jar. <br>
 * 
 * @author Ryan Lasante, Eric Greer, Brittany McNally, Greg Coffey <br>
 * 
 * This program was created for use by the DRNA,
 *  by students of WPI, and can be modified to fit their needs.     
 */
public class DatabaseImporter extends JPanel implements ActionListener 
{
	/* Variables for the interface */
	private static final long serialVersionUID = 1L;
	static private final String newline = "\n";
	JButton uploadButton;
	JTextArea log;
	JTextField textField = new JTextField(10);
	JPasswordField passwordField = new JPasswordField(10);
	
	/**
	 *  The constructor for the database importer. Generates the application features.
	 */
	public DatabaseImporter() 
	{
		super(new BorderLayout());

		// Create a regular text field.
		
		textField.setActionCommand("Username:");
		textField.addActionListener(this);

		// Create a password field.
		
		passwordField.setActionCommand("Password");
		passwordField.addActionListener(this);

		JLabel usernameLabel = new JLabel("Username:");
		JLabel passwordLabel = new JLabel("Password:");

		// Create the log first, because the action listeners
		// need to refer to it.
		log = new JTextArea(5, 20);
		log.setMargin(new Insets(5, 5, 5, 5));
		log.setEditable(false);
		JScrollPane logScrollPane = new JScrollPane(log);

		// Create the open button. We use the image from the JLF
		// Graphics Repository (but we extracted it from the jar).
		uploadButton = new JButton("Upload a File...");
		uploadButton.addActionListener(this);

		// For layout purposes, put the buttons in a separate panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(uploadButton);

		JPanel textPanel = new JPanel();
		textPanel.add(usernameLabel);
		textPanel.add(textField);
		textPanel.add(passwordLabel);
		textPanel.add(passwordField);
		
		// Add the buttons and the log to this panel.
		add(buttonPanel, BorderLayout.CENTER);
		add(logScrollPane, BorderLayout.PAGE_END);
		add(textPanel, BorderLayout.PAGE_START);

	}


	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) 
	{

		// Handle open button action.
		if (e.getSource() == uploadButton) {
			FileOpenService fos = null;
			FileContents fileContents = null;

			try {
				fos = (FileOpenService) ServiceManager
						.lookup("javax.jnlp.FileOpenService");
			} catch (UnavailableServiceException exc) {
			}

			if (fos != null) {
				try {
					fileContents = fos.openFileDialog(null, null);
				} catch (Exception exc) {
					log.append("Upload command failed: "
							+ exc.getLocalizedMessage() + newline);
					log.setCaretPosition(log.getDocument().getLength());
				}
			}

			// Check to see if the file was given by the user
			if (fileContents != null) {
				try {
					log.append("Uploaded file: " + fileContents.getName() + "."
							+ newline);
					Scanner uploadedFile = new Scanner(fileContents.getInputStream());
					
					//Begin parsing the file into the the database
					Logic function = new Logic();
					function.execute(uploadedFile, textField.getText(), String.valueOf(passwordField.getPassword()));
				
				} catch (IOException exc) {
					log.append("Problem uploading file: "
							+ exc.getLocalizedMessage() + newline);
				} catch (SQLException sqle){
					log.append("Invalid username or password: " + sqle.getLocalizedMessage() + newline);
					log.append(sqle.getMessage() + newline);
				} catch (ClassNotFoundException cnfe){
					log.append("Missing Driver Error Connecting to Database. Contact Administrator with: " + cnfe.getLocalizedMessage() + newline);
				}
			} else {
				log.append("User canceled upload request." + newline);
			}
			log.setCaretPosition(log.getDocument().getLength());
		}

	}

	/** Returns an ImageIcon, or null if the path was invalid.
	 *  
	 * @param path	The file path to the image
	 */
	protected static ImageIcon createImageIcon(String path) 
	{
		java.net.URL imgURL = DatabaseImporter.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event dispatch thread.
	 */
	private static void createAndShowGUI() 
	{
		// Create and set up the window.
		JFrame frame = new JFrame("Database Importer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Add content to the window.
		frame.add(new DatabaseImporter());

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	/** The main program to run the java webstart program
	 * 
	 * @param args	No arguments accepted
	 */
	public static void main(String[] args) 
	{
		// Schedule a job for the event dispatch thread:
		// creating and showing this application's GUI.
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				// Turn off metal's use of bold fonts
				UIManager.put("swing.boldMetal", Boolean.FALSE);
				createAndShowGUI();
			}
		});
	}
}
