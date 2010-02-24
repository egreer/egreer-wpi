/**
 * egreer
 * Test 1 Practice Problems
 */
package test1;

public class OrganizeThreeInts {
	
	public static void main(String[] args){
		//Input
		int a = 2;
		int b = 3;
		int c = 1;
		
		//Processing
		if ((a < b) && (b < c)){
			System.out.println(a + " " + b + " " + c);
			
		}else if ((a < c) && (c < b)){
			System.out.println(a + " " + c + " " + b);
			
		}else if ((c < a) && (a < b)){
			System.out.println(c + " " + a + " " + b);
			
		}else if ((b < a) && (a < c)){
			System.out.println(b + " " + a + " " + c);
		
		}else if ((b < c) && (c < a)){
			System.out.println(b + " " + c + " " + a);
		
		}else if ((c < b) && (b < a)){
			System.out.println(c + " " + b + " " + a);
		
		}else {
			System.out.println("WRONG!");
		}
		
		
	}

}
