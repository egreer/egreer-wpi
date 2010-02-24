package goldbug.khoadoba;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Scanner;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * 1. But, there being no division, my first step was to ascertain the predominant
 *  letters, as well as the least frequent. Counting all, I constructed a table, 
 *  thus:
 * 
 * @author George Heineman
 * @author Khoa Do Ba (GUI)
 *
 */
public class GoldBug {
	/** Read input. */
	static Scanner sc;
	
	/** The code. */
	static String code;
	
	/** Unknown character. */
	public static char unknown = '\001';
	
	/** Coded characters and their replacement. */
	static Hashtable<Character,Character> key = new Hashtable<Character,Character>(); 
	
	/** Characters in decreasing frequency. */
	static char chars[];
	
	/** And their frequency. */
	static int freq[];
	
	/** main GUI panel */
	static JFrame mainGUI;
	
	/** text area displaying code */
	static JTextArea txtCode;
	
	/** text area displaying letter assignments */
	static JTextArea txtLtrAsgn;
	
	/** status label */
	static JLabel lblStat;
	
	/** number of character perline */
	final static int MAXCHAR = 60;
	
	

	/** Compute frequency and build key table. */
	private static void process() {
		for (int i = 0; i < code.length(); i++) {
			char ch = code.charAt(i);
			if (key.containsKey(ch)) continue;
			
			key.put(ch, unknown);
		}
		
		int numUnique = key.size();
		chars = new char[numUnique];
		freq = new int [numUnique];
		
		int idx = 0;
		for (Iterator<Character> it = key.keySet().iterator(); it.hasNext(); idx++) {
			char ch = it.next();
			chars[idx] = ch;
			for (int i = 0 ; i < code.length(); i++) {
				if (code.charAt(i) == ch) {
					freq[idx]++;
				}
			}
		}
		
		// now sort by frequency. Bubble sort, for goodness' sake!
		for (int i = 0; i < freq.length - 1; i++) {
			for (int j = i+1; j < freq.length; j++) {
				if (freq[i] < freq[j]) {
					int tmp = freq[i];
					freq[i] = freq[j];
					freq[j] = tmp;
					
					char tc = chars[i];
					chars[i] = chars[j];
					chars[j] = tc;
				}
			}
		}
	}
	
	public static String decoded () {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < code.length(); i++) {
			sb.append(key.get(code.charAt(i)));
		}
		
		return sb.toString();
	}
	
	/** Output current key. */
	public static String showKey() {
		StringBuilder sb = new StringBuilder();
		for (Iterator<Character> it = key.keySet().iterator(); it.hasNext(); ) {
			char ch = it.next();
			char dec = key.get(ch);
			if (dec != unknown) {
				sb.append (" ").append(ch).append("=").append(dec).append(" ");
			}
		}
		
		return sb.toString();
	}
	
	/** display code */
	public static void displayCode() {
		
		String decodedStr = decoded();
		String codeStr = code;
		String outputStr = new String();
		
		/** break the output into multiple lines */
		while (codeStr.length() > MAXCHAR){ 
			outputStr += codeStr.substring(0, MAXCHAR);
			outputStr += "\n";
			outputStr += decodedStr.substring(0, MAXCHAR);
			outputStr += "\n";
			
			codeStr = codeStr.substring(MAXCHAR);
			decodedStr = decodedStr.substring(MAXCHAR);
		}
		outputStr += codeStr + "\n" + decodedStr;
		
		txtCode.setText(outputStr);
	}
	
	/** show the frequency of the letters */
	public static void showFrequency(){
		String output = new String();
		
		for (int i = 0; i < chars.length; i++) {
			output += chars[i] + " ";
		}
		
		/** show the output as a dialog */
		JOptionPane.showMessageDialog(mainGUI, output, 
				"Letter Frequency", JOptionPane.PLAIN_MESSAGE);
	}
	
	/** assign a letter to a character in the code */
	public static void assignLetters(){
		String inStr; // input string
		
		inStr = JOptionPane.showInputDialog(mainGUI, 
				"Enter the coded letter you wish to assign",
				"Assign a Letter", JOptionPane.QUESTION_MESSAGE);
		
		char c1 = (inStr == null) ? unknown : inStr.charAt(0);
		
		inStr = JOptionPane.showInputDialog(mainGUI, 
				"What code should be given?",
				"Assign a Letter", JOptionPane.QUESTION_MESSAGE);
		
		char c2 = (inStr == null) ? unknown : inStr.charAt(0);
		
		key.put(c1, c2);
	}
	
	/** clear an assignment */
	public static void clearAssignments(){
		String inStr; // input string
		
		inStr = JOptionPane.showInputDialog(mainGUI, 
				"Enter the coded letter you wish to clear",
				"Clear Assigments", JOptionPane.QUESTION_MESSAGE);
		
		char c1 = inStr.charAt(0);
		
		key.put(c1, unknown);
	}
	
	/** Launch whole program. */
	public static void main (String []args) throws FileNotFoundException {
		
		/** set GUI frame */
		mainGUI = new JFrame("Gold Bug");
		
		/** prompt for the input file */
		FileDialog fd = new FileDialog( mainGUI, "Select file containing code", 
									FileDialog.LOAD);
		fd.setVisible(true);
		String fileName = fd.getFile();
		
		/** load file */
		File f = new File (fileName);
		if (!f.exists()) {
			System.out.println (f + " does not exist.");
			System.exit(0);
		}
		
		Scanner fscan = new Scanner (f);
		StringBuilder sb = new StringBuilder();
		while (fscan.hasNext()) {
			sb.append(fscan.nextLine());
		}
		fscan.close();
		
		code = sb.toString();
		process();
		
		/****************************** 
		 * GUI components for main panel 
		 * ****************************/
		
		mainGUI.getContentPane().setLayout(new BorderLayout());
		
		/** status label */
		lblStat = new JLabel("");
		mainGUI.getContentPane().add(lblStat, BorderLayout.NORTH);
		
		/** displays the code and the letter assignment status*/
		JPanel pnlCenter = new JPanel();
		pnlCenter.setLayout(new BoxLayout(pnlCenter, BoxLayout.Y_AXIS));
		
		JLabel lblCode = new JLabel("Code: ");
		JLabel lblLtrAsgn = new JLabel("Letter Assignments:");
		
		txtLtrAsgn = new JTextArea(5, 40);
		txtLtrAsgn.setEditable(false);
		txtLtrAsgn.setFont(new Font("Lucida Console", Font.PLAIN, 12));

		txtCode = new JTextArea(10, 40);
		txtCode.setEditable(false);
		txtCode.setFont(new Font("Lucida Console", Font.PLAIN, 12));

		pnlCenter.add(lblCode);
		pnlCenter.add(txtCode);
		pnlCenter.add(lblLtrAsgn);
		pnlCenter.add(txtLtrAsgn);
		
		mainGUI.getContentPane().add(pnlCenter, BorderLayout.CENTER);
		
		/** buttons panel */
		JPanel pnlButtons = new JPanel(new FlowLayout());
		mainGUI.getContentPane().add(pnlButtons, BorderLayout.SOUTH);
		
		/** show frequency button */
		JButton btnFreq = new JButton("Frequency");
		btnFreq.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						/** show Frequency */
						showFrequency();
					}	
		});
		pnlButtons.add(btnFreq);
		
		/** assign letter button */
		JButton btnAssign = new JButton("Assign letter");
		btnAssign.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						assignLetters();
					}	
		});
		pnlButtons.add(btnAssign);
		
		/** clear letter assignment button */
		JButton btnClr = new JButton("Clear Assignments");
		btnClr.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						clearAssignments();
					}	
		});
		pnlButtons.add(btnClr);
		
		/** Quit button */
		JButton btnQuit = new JButton("Quit");
		btnQuit.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						System.exit(0);
					}	
		});
		pnlButtons.add(btnQuit);
		
		mainGUI.pack();
		mainGUI.setVisible(true);
		mainGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/*******************
		 * end of GUI management
		 ********************/
		
		System.out.println ("Welcome to the GoldBug decoding system");
		System.out.println ("Version 1.0");
		
		try{
			while (true){
				displayCode();
				txtLtrAsgn.setText(showKey());
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println ("\nAn error occured. Try again!");
		}
		
	}
}
