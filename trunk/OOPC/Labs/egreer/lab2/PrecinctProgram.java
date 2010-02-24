package egreer.lab2;

/**
 * @author egreer
 */

public class PrecinctProgram {

	public static void main(String[] args) {
		Precinct pre;
		pre = new Precinct();
		pre.name = new String ("K12");
		pre.address = new String ("130 Winter Street");
		pre.population = 673;
		
		System.out.println ("Precinct Info:" + pre);

		pre.add (20);

		System.out.println ("Precinct Info:" + pre);

	}
}
