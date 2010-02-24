package hw;

public class Mod9 {
	public static void main (String args[]){
		double t, s, x, y, r, z;
		x = (2^30000096) %9;
		t = (19^1000453) %9;
		s = 481 %9;
		z = (248^3456234) %9;
		r = 243 %9;
				
		y = (x + (s*t) + (r*z))  % 9.0;
		
		System.out.println(y);
		
		
	}
}
