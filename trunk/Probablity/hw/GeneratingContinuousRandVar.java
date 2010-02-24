package hw;

import java.util.Random;

public class GeneratingContinuousRandVar {

	public static void main(String args[]){
		
		Random generator = new Random();
		
		
		int less1 = 0;
		int less2 = 0;
		int less3 = 0;
		int less4 = 0;
		int less5 = 0;
		int less6 = 0;
		int less7 = 0;
		int less8 = 0;
		int less9 = 0;
		int great9 =0;
		
		for (int i =0; i < 100; i++){
			double r = generator.nextDouble();
			
			double temp = -4*Math.log(1 - r);
			
			if (temp <= 1) less1++;
			else if (temp <= 2) less2++;
			else if (temp <= 3) less3++;
			else if (temp <= 4) less4++;
			else if (temp <= 5) less5++;
			else if (temp <= 6) less6++;
			else if (temp <= 7) less7++;
			else if (temp <= 8) less8++;
			else if (temp <= 9) less9++;
			else if (temp > 9) great9++;
					
		}
			
		System.out.println(less1);
		System.out.println(less2);
		System.out.println(less3);
		System.out.println(less4);
		System.out.println(less5);
		System.out.println(less6);
		System.out.println(less7);
		System.out.println(less8);
		System.out.println(less9);
		System.out.println(great9);
		
	}
}
