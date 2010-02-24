package jm4games.hw1;

   /**
	 * @author Josh Montgomery,Eric Greer
	 * sn: jm4games,egreer
	 *
	 */
public class Problem3{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//INPUT
		double temp = 40;
		
		System.out.println("Celsius : Fahrenheit");
		System.out.println("--------------------");
		
		//PROCESSING -> OUTPUT
		while(temp >= -10)
		{
			System.out.println(temp+" : "+(((9.0/5)*temp)+32));
			temp--;
		}
	}

}