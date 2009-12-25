package hw7;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

/** The Singleton that stores my information
 * 
 * @author Eric Greer
 *
 */
public class MyInfo {
	private static MyInfo instance;
	private MyInfo(){}
	
	/** The courses I have taken */	
	public static ArrayList<Course> courses = new ArrayList<Course>();

	/* Tags */
	public final static String xmlHeader = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
	
	public static final String termTag = "<term>";
	public static final String endTermTag = "</term>";

	public static final String courseTag = "<course>";
	public static final String endCourseTag = "</course>";

	public static final String nameTag = "<name>";
	public static final String endNameTag = "</name>";

	public static final String numberTag = "<number>";
	public static final String endNumberTag = "</number>";

	/** 
	 * Fills the array list with the courses I have taken
	 */
	private static void fill(){
		courses.add(new Course("Molecularity", "CH1010", "AP"));
		courses.add(new Course("CALCULUS I", "MA1021", "AP"));
		courses.add(new Course("CALCULUS II", "MA1022", "AP"));
		courses.add(new Course("GENERAL PHYSICS-MECHANICS", "PH1110", "AP"));
		courses.add(new Course("ELEMENTARY SPANISH I", "SP1523", "HS"));
		courses.add(new Course("INTRO TO PROGRAM DESIGN", "CS1101", "A06"));
		courses.add(new Course("OBJCT-ORIENTED DESIGN CONCEPTS", "CS2102", "A06"));
		courses.add(new Course("CALCULUS III", "MA1023", "A06"));
		courses.add(new Course("CALCULUS IV", "MA1024", "B06"));
		courses.add(new Course("CONCERT BAND", "MU2636", "B06"));
		courses.add(new Course("Basic Water Safty", "PE1007", "B06"));
		courses.add(new Course("INTERMEDIATE SPANISH I", "SP2521", "A06"));
		courses.add(new Course("INTERMEDIATE SPANISH I", "SP2522", "B06"));
		courses.add(new Course("DISCRETE MATHEMATICS", "CS2022", "C07"));
		courses.add(new Course("PROBABILITY FOR APPLICATIONS", "MA2621", "C07"));
		courses.add(new Course("LIFEGUARDING I", "PE1056", "C07"));
		courses.add(new Course("LIFEGUARDING II", "PE1057", "D07"));
		courses.add(new Course("ADVANCED SPANISH I", "SP3521", "C07"));
		courses.add(new Course("ADVANCED SPANISH II", "SP3522", "D07"));
		courses.add(new Course("FORCES AND BONDING", "CH1020", "B07"));
		courses.add(new Course("SYSTEMS PROGRAMMING CONCEPTS", "CS2303", "A07"));
		courses.add(new Course("FOUNDATIONS OF COMP. SCIENCE", "CS3133", "A07"));
		courses.add(new Course("INTRO TO PSYCHOLOGICAL SCIENCE", "PSY1400", "B07"));
		courses.add(new Course("SPANISH CULTURE CIVILIZATION", "SP3528", "A07"));
		courses.add(new Course("DATABASE SYSTEMS I", "CS3431", "B07"));
		courses.add(new Course("EQUILIBRIUM", "CS 1030", "C08"));
		courses.add(new Course("INTRO-COMP ORG ASSEMBLER", "CS2011", "C08")); //TODO Check
		courses.add(new Course("OPERATING SYSTEMS", "CS3013", "C08")); //TODO Check
		courses.add(new Course("HUMAN-COMPUTER INTERACTN","CS3041", "C08"));
		courses.add(new Course("SOCIAL IMPL OF INFO PROCESSING", "CS3043", "C08")); //TODO Check
		courses.add(new Course("SOFTWARE ENGINEERING", "CS3733", "D08"));
		courses.add(new Course("MATRICES LINEAR ALGEBRA I", "MA 2071", "D08"));//TODO Check
		courses.add(new Course("SWIMMING FOR FITNESS", "PE1077", "D08"));//TODO Check
		courses.add(new Course("OBJ-ORIENTED ANALYSIS   DESIGN", "CS4233", "A08"));
		courses.add(new Course("INTRO TO ARTIFICIAL INTELLIGEN", "CS4341", "B08"));
		courses.add(new Course("COMPUTER NETWORKS: ARCH   IMPL", "CS4514", "B08"));
		courses.add(new Course("INTRO TO ELEC   COMPUTER ENGIN", "ECE2011", "A08"));
		courses.add(new Course("INFORMATION SYSTEMS MANAGEMENT" , "MIS3700" , "B08"));
		courses.add(new Course("ORGANIZ SCIENCE - FOUNDATION", "OBC2300", "A08"));
		courses.add(new Course("COMPUTER ARCHITECTURE", "CS4515", "C09"));
		courses.add(new Course("SOC SCI RES-IQP (PUERTO RICO)", "ID2050", "C09"));
		courses.add(new Course("INTERACTIVE QUALIFYING PROJECT", "IQPKAL", "D09"));
		courses.add(new Course("APPLIED STATISTICS I", "MA2611", "C09"));
		courses.add(new Course("PRE/POST QUALIFYING PROJECT", "PQPKAL", "C09"));
		courses.add(new Course("BUSINESS DATA MANAGEMENT", "MIS3720", "A09"));
		courses.add(new Course("HELPDESK SHIFT SCHEDULER", "MQPMXC", "AB09C10"));
		courses.add(new Course("INTRO TO ALGRTHMS: DES   ANALY", "CS5084", "AB09"));
		courses.add(new Course("DESIGN OF SOFTWARE SYSTEMS", "CS509", "AB09"));
		courses.add(new Course("AQUATIC CONDITIONING", "PE1078", "B09"));
		courses.add(new Course("Webware", "CS4241", "B09"));
	}

	/** Checks to see if I took the given course 
	 * 
	 * @param course	The course number to check
	 * @return			Returns the XML for the term, empty tags if not
	 */
	public String DidITake(String course){
		Iterator<Course> iterator = courses.iterator();
		while(iterator.hasNext()){
			Course c = iterator.next();
			if(c.courseNumber.equals(course)) 
				return xmlHeader + termTag + c.term + endTermTag;  
		}

		return xmlHeader + termTag + endTermTag;
	}

	/** Exports my courses to XML
	 * 
	 * @return	Returns the XML for the courses I have taken.
	 */
	public String ExportMyCourses(){
		String returner  = "<courses>";
		Iterator<Course> iterator = courses.iterator();

		while (iterator.hasNext()){
			Course c = iterator.next();
			returner +=  courseTag + nameTag + c.name + endNameTag + 
			numberTag + c.courseNumber + endNumberTag+ endCourseTag;
		}
		return returner + "</courses>";
	}
	
	/** Singleton method 
	 * 
	 * @return
	 */
	public static MyInfo getInstance(){
		if (instance == null){
			instance = new MyInfo();
			fill();
			
		}
		return instance; 
	}
	
}
