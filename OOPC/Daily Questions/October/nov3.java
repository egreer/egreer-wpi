package October;

public class nov3 {
	
	public static String evaluate(int x) {
	    if (x < 10) {
	       return "low";
	    }
	    else{ 
	    	return "high";
	    }
	}
	public static void main(String[] args) {
		String t = evaluate(1);
		System.out.println(t);
		
	}

}
