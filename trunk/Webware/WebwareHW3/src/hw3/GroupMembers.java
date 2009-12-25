package hw3;

import java.io.PrintWriter;

/** This deals with the information regarding the groupmembers
 * 
 * @author Eric Greer
 *
 */
public class GroupMembers {
	
	/** Writes the page with the correct groupmembers based on the group number   
	 * 
	 * @param output		The writer to write the file to.
	 * @param groupNumber	The group to get information for.
	 */
	public static void writeMembersPage(PrintWriter output, int groupNumber){
		String[] members = getGroupMembers(groupNumber);
		output.println("<html><head><title>CS4241-B09</title>");
		output.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"hw3.css\" title=\"Default Style\"/></head>");
		output.println("<body>");
		output.println("<!-- Eric Greer -->");
		output.println("<h1>Project Team " + groupNumber + " Members:</h1>");
		output.println("<ul>");
		for( int i =0 ; i < members.length ; i++){
			output.println("<li>" + members[i] + "</li>");
		}
		output.println("</ul>");
		output.println("<p><a href=\"..\">Return to the Main Page</a>");
		output.println("</body></html>");
	}
	
	
	/** Returns the list of group members as an array of strings.  
	 * 
	 * @param groupNumber	The group number to get the members of  
	 * @return				returns an array of strings of the members
	 */
	 static String[] getGroupMembers(int groupNumber){
		 switch(groupNumber){
		 	case(1): return new String[]{ "Andreea Bodnari(andreeab)", "Kristen Hughes(hugheskm)","Elizabeth Labelle(lizlabelle)"};  
		 	case(2): return new String[]{ "Matthew Netsch", "Benjamin Petrin","Richard Pianka (pianka)"};
		 	case(3): return new String[]{ "Eric Greer(egreer)", "Jason Codding(jcodding)", "Matt Runkle(mrunkle)"};
		 	case(4): return new String[]{ "Dickson ~McCannell (dmccann)", "Greg Sheaffer (sheaffgr)","Nik Deapen  (ndeapen)"};
		 	case(5): return new String[]{ "Steve Franceschelli (stephenf)", "Jared Ingalls (jaredingalls","<open>"};
		 	case(6): return new String[]{ "Chris Petrie (cpetrie)", "Zach Miller (zm)","Chance Miller (chance)"};
		 	case(7): return new String[]{ "Richard ~DiCroce (~RDiCroce)", "Scott Almquist(almquist))","<open>"};
		 	case(8): return new String[]{ "Ethan Truong (etruong)", "Samuel Moniz (smoniz)","Mig Alvarado (migalvarado)"};
		 	case(9): return new String[]{ "Claudio Herreros(cherreros)", "Dylan James", "<open>"};
		 	case(10): return new String[]{ "Ross ~LeBeau(rlebeau)", "Paul Kehrer(pkehrer)","Megan Tsai(mtsai)"};
		 	case(11): return new String[]{ "Gage Fleischer(gage)", "Sarah Jaffer(sjaffer)","Michael Tidd(mtidd)"};
		 	case(12): return new String[]{ "Michael Oliver (mikeoliver)", "Andrew Tremblay (andrewtremblay)","Brendan ~McLoughlin(bmcloughlin)"};
		 	case(13): return new String[]{ "Edwin Lui (edwinl)", "Matthew Knapp (mknapp)","Jesse Bassett (jpbassett)"};
		 	case(14): return new String[]{ "Felix Nwaobasi", "Shikhar Saxena","Viktoras Truchanovicius"};
		 	case(15): return new String[]{ "Eric Magnuson(emagnuso)", "Lucas Scotta(lscotta)","Dan Mitchell (dmitch)"};
		 	case(16): return new String[]{ "Sean Beck(spcbeck)", "Ian Lonergan(ian.lonergan)","<open>)"};
		 	case(17): return new String[]{ "Brian Tate(btate)", "Ryan LaSante(rlasante)","Andrew Yee"};
		 	default: return new String[] {"Sorry No Members in group"};
		 }

	}
}
