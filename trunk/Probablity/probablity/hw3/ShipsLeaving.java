package probablity.hw3;

import java.util.Random;

public class ShipsLeaving {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Random generator = new Random();
				
		int ships0 = 0;
		int ships1 = 0;
		int ships2 = 0;
		int ships3 = 0;
		int ships4 = 0;
		int ships5 = 0;
		
		for(int i = 0; i < 1000; i++){
			double r = generator.nextDouble();
			
			if (r <= .0075){
				ships0++;
			
			}else if (r <= .0775){
				ships1++;
			
			}else if (r <= .265){
				ships2++;
			
			}else if (r <= .5825){
				ships3++;
				
			}else if (r <= .9175){
				ships4++;
				
			}else if (r <= 1){
				ships5++;
			
			}
					
		}
		
		
		
		/*for(int i = 0; i < 1000; i++){
			int r = generator.nextInt(12);
			
			if (r == 0){
				ships0++;
			
			}else if ((r == 1) || (r == 6)){
				ships1++;
			
			}else if ((r == 2) || (r == 7) || (r == 8)){
				ships2++;
			
			}else if ((r == 3) || (r == 9) || (r == 10)){
				ships3++;
				
			}else if ((r == 4) || (r == 11)){
				ships4++;
				
			}else if (r == 5){
				ships5++;
			
			}*/
					
			System.out.println("0: " + ships0); 
			System.out.println("1: " + ships1);
			System.out.println("2: " + ships2);
			System.out.println("3: " + ships3);
			System.out.println("4: " + ships4);
			System.out.println("5: " + ships5);
	}
}


