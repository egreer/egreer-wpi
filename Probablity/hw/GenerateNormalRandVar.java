package hw;

import java.util.Random;

public class GenerateNormalRandVar {
	
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
			int less10 = 0;
			int less11 = 0;
			int less12 = 0;
			int less13 = 0;
			int less14 = 0;
			int less15 = 0;
			int less16 = 0;
			int less17 = 0;
			int less18 = 0;
			int less19 = 0;
			int great20 = 0;
			
			for (int i = 0; i < 2500; i++){
				double r = generator.nextDouble();
				double q = generator.nextDouble();
				
				double temp1 = 1.7+(.475*(Math.sqrt(-2*Math.log(r))*Math.cos(2*Math.PI*q)));
				double temp2 = 1.7+(.475*(Math.sqrt(-2*Math.log(r))*Math.sin(2*Math.PI*q)));
				
				
				if (temp1 <= .17) less1++;
				else if (temp1 <= .36) less2++;
				else if (temp1 <= .55) less3++;
				else if (temp1 <= .74) less4++;
				else if (temp1 <= .93) less5++;
				else if (temp1 <= 1.12) less6++;
				else if (temp1 <= 1.31) less7++;
				else if (temp1 <= 1.50) less8++;
				else if (temp1 <= 1.69) less9++;
				else if (temp1 <= 1.88) less10++;
				else if (temp1 <= 2.07) less11++;
				else if (temp1 <= 2.26) less12++;
				else if (temp1 <= 2.45) less13++;
				else if (temp1 <= 2.64) less14++;
				else if (temp1 <= 2.83) less15++;
				else if (temp1 <= 3.02) less16++;
				else if (temp1 <= 3.21) less17++;
				else if (temp1 <= 3.40) less18++;
				else if (temp1 <= 3.59) less19++;
				else if (temp1 > 3.59)	great20++;	
				
				
				if (temp2 <= .17) less1++;
				else if (temp2 <= .36) less2++;
				else if (temp2 <= .55) less3++;
				else if (temp2 <= .74) less4++;
				else if (temp2 <= .93) less5++;
				else if (temp2 <= 1.12) less6++;
				else if (temp2 <= 1.31) less7++;
				else if (temp2 <= 1.50) less8++;
				else if (temp2 <= 1.69) less9++;
				else if (temp2 <= 1.88) less10++;
				else if (temp2 <= 2.07) less11++;
				else if (temp2 <= 2.26) less12++;
				else if (temp2 <= 2.45) less13++;
				else if (temp2 <= 2.64) less14++;
				else if (temp2 <= 2.83) less15++;
				else if (temp2 <= 3.02) less16++;
				else if (temp2 <= 3.21) less17++;
				else if (temp2 <= 3.40) less18++;
				else if (temp2 <= 3.59) less19++;
				else if (temp2 > 3.78)	great20++;
				
				
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
			System.out.println(less10);
			System.out.println(less11);
			System.out.println(less12);
			System.out.println(less13);
			System.out.println(less14);
			System.out.println(less15);
			System.out.println(less16);
			System.out.println(less17);
			System.out.println(less18);
			System.out.println(less19);
			System.out.println(great20);
	}
}