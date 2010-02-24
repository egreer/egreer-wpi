package bayes;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;

/** Generates probability results from the bayesian network based on three queries given in the report.
 * 
 * Results in the form
 * Normalized Results Query x: {P(TRUE), P(FALSE)}
 * 
 * @author Eric Greer
 *
 */
public class bayes {
	static int numberOfSamples = 1000000;
	
	public static void main(String[] args){
		Sample querry1  = new Sample(2, 1, 0, 0);
		Sample querry2  = new Sample(1, 2, 0, 0);
		Sample querry3  = new Sample(1, 1, 0, 0);
		double[] results;
		
		System.out.println("Configuration Settings:");
		System.out.println("Number of Samples: " + numberOfSamples);
			
		
		System.out.print("\nNormalized Results Query 1: {");
		results = RejectionSampling('c', querry1);
		System.out.println(results[0] + ", " + results[1] + "}");
		
		System.out.print("\nNormalized Results Query 2: {");
		results = RejectionSampling('c', querry2);
		System.out.println(results[0] + ", " + results[1] + "}");
		
		System.out.print("\nNormalized Results Query 3: {");
		results = RejectionSampling('d', querry3);
		System.out.println(results[0] + ", " + results[1] + "}");
	}

	/** Generates the probabilities using rejections sampling 
	 * 
	 * @param variable	The value that should be true
	 * @param solution	The preconditions that are also true
	 * @return			Returns an array of the probabilities. Value in 0 is probability variable is true,
	 * 					and value in 1 is the probability variable is false. 		
	 */
	static double[] RejectionSampling(char variable, Sample solution){
		LinkedList<Sample> acceptableSolutions = new LinkedList<Sample>();
		
		for (int j = 0 ; j < numberOfSamples; j++ ){
			Sample temp = PriorSample();
			if (temp.valid(solution)){
				acceptableSolutions.add(temp);
			}
			
		}
		double[] returner = Normalize(variable, acceptableSolutions);
		return returner;
	}
	
	/** Generates a sample using the probability distribution of the bayesian network
	 * 
	 * @return the randomly generated sample
	 */
	static Sample PriorSample(){
		Sample returner = new Sample(0, 0, 0, 0);
		Random generator = new Random();
		double r;
		//Generate A
		r = generator.nextDouble();
		if (r <= .35) returner.a = 1;
		else returner.a = 2;
		
		//Generate B
		r = generator.nextDouble();
		if (r <= .45) returner.b = 1;
		else returner.b = 2;
		
		//Generate C
		r = generator.nextDouble();
		
		if(returner.a == 1){ 	//if A is True
			if(returner.b == 1){	//And B is true		
				if(r <= .50) returner.c = 1;
				else  returner.c = 2;
			}else{					//And B is False
				if (r <= .15) returner.c = 1;
				else  returner.c = 2;
			}
			
		}else {				//if A is False
			if(returner.b == 1){	//And B is true		
				if(r <= .15) returner.c = 1;
				else  returner.c = 2;
			}else{					//And B is False
				if (r <= .20) returner.c = 1;
				else  returner.c = 2;
			}
		}

		//Generate D
		r = generator.nextDouble();
		if(returner.c == 1){		//if C is True 
			if(r <= .85) returner.d = 1;
			else returner.d = 2;
		}else{						//if C is False
			if(r <= .25) returner.d = 1;
			else returner.d = 2;
		}
		
		return returner;
	}
	
	/** Normalizes the values into their probabilities
	 * 
	 * @param variable	The value that should be true
	 * @param solutions	The possible solutions found
	 * @return			Returns an array of the probabilities. Value in 0 is probability variable is true,
	 * 					and value in 1 is the probability variable is false. 
	 */
	static double[] Normalize(char variable, LinkedList<Sample> solutions){
		double[] returner = new double[2];
		int size = solutions.size();
		ListIterator<Sample> lister = solutions.listIterator();
		int numTrue = 0;
		
		while(lister.hasNext()){
			Sample temp = lister.next();
			if(temp.valueOf(variable) == 1) numTrue++;
		}
		
		returner[0] = (numTrue * 1.0 ) / (size * 1.0); //negates integer division
		returner[1] = (size - numTrue) / (size * 1.0);
		return returner;
	}
}
