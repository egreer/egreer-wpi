package hw3;

import java.util.ArrayList;
import java.util.LinkedList;

public class Prb3 {
	
	static ArrayList<ShuttleDataInstance> testInstances = new ArrayList<ShuttleDataInstance>();
	static ArrayList<ShuttleDataInstance> trainingInstances = new ArrayList<ShuttleDataInstance>();

	public static void main(String args[]){
		System.out.println("Begin");
		init();
		processStability();
		System.out.println("Added Instances to Training and Test arrays");
		
		for (ShuttleDataInstance test : testInstances){
			System.out.println("Processing " + test.toString());
			LinkedList <ShuttleDataInstance> minimum = new LinkedList<ShuttleDataInstance>();
			
			//Computes the distances
			for(ShuttleDataInstance train : trainingInstances){
				train.distance = euclideanDistance(train, test);
				System.out.println("Computed distance to: \n\t" + train.toString() +  "\n\tis: " + train.distance);
			}			
			//finds the max
			for(ShuttleDataInstance train : trainingInstances){
				if (minimum.size() < 4) minimum.add(train);
				else {
					//Remove the smallest distance in Max 
					ShuttleDataInstance max = null;
					for (ShuttleDataInstance m : minimum){
						if( m.distance > train.distance){
							if (max == null) max = m;
							else if ( max.distance < m.distance) max = m;
						}
					}
					if (max != null){
						minimum.remove(max);
						minimum.add(train);
					}
				}
			}
			
			//Averages the result
			int countAuto = 0;
			int countNo = 0;
			System.out.println("Closest Neighbors:");
			for (ShuttleDataInstance m : minimum){
				if (m.classification.equals("auto")) countAuto++;
				else  if(m.classification.equals("noauto")) countNo++;
				System.out.println(m.toString() + " Distance: " + m.distance);
			}
			
			if(countAuto == countNo) {
				System.out.println("Stalemate! Classifying as Auto");
				test.classification = "auto";
				
			}else if(countAuto > countNo){
				System.out.println("Classifying as Auto");
				test.classification = "auto";
			}else {
				System.out.println("Classifying as noAuto");
				test.classification = "noauto";
			}
		}
		
		System.out.println("Classified as:");
		for(ShuttleDataInstance test : testInstances){
			System.out.println(test.toString());
		}
		
		System.out.println("End");
	}
	
	private static void processStability() {
		double maxStability = 50;
		double minStability = 50;
		
		for (ShuttleDataInstance train : trainingInstances){
			maxStability = Math.max(maxStability, train.stability);
			minStability = Math.min(minStability, train.stability);
		}
		
		for (ShuttleDataInstance train : trainingInstances){
			train.stability = (float) ((train.stability - minStability)/(maxStability - minStability));
		}
		
		for (ShuttleDataInstance test : testInstances){
			test.stability = (float) ((test.stability - minStability)/(maxStability -minStability));
		}
		
	}

	static double euclideanDistance(ShuttleDataInstance training, ShuttleDataInstance test){
		double distance = 0;
		
		distance += Math.sqrt(Math.pow((training.stability - test.stability), 2) + Math.pow((training.error - test.error), 2));
		if (training.headWind != test.headWind) distance += 1; //Head wind
		if (training.visability != test.visability) distance += 1; //Visability
		
		return distance;
	}
	
	
	static void init(){
		trainingInstances.add(new ShuttleDataInstance(60, .5f, false, false, "auto"));
		trainingInstances.add(new ShuttleDataInstance(75, 1.0f, true, true, "noauto"));
		trainingInstances.add(new ShuttleDataInstance(40, .9f, true, false, "auto"));
		trainingInstances.add(new ShuttleDataInstance(65, .0f, true, false, "auto"));
		trainingInstances.add(new ShuttleDataInstance(45, .2f, true, true, "auto"));
		trainingInstances.add(new ShuttleDataInstance(80, .1f, false, true, "noauto"));
		trainingInstances.add(new ShuttleDataInstance(30, .4f, true, true, "noauto"));
		trainingInstances.add(new ShuttleDataInstance(90, .6f, true, false, "auto"));
		trainingInstances.add(new ShuttleDataInstance(65, .1f, true, false, "auto"));
		trainingInstances.add(new ShuttleDataInstance(85, .5f, true, true, "noauto"));
		trainingInstances.add(new ShuttleDataInstance(25, .6f, false, false, "auto"));
		trainingInstances.add(new ShuttleDataInstance(40, .4f, false, true, "noauto"));
		trainingInstances.add(new ShuttleDataInstance(15, .6f, false, true, "noauto"));
		trainingInstances.add(new ShuttleDataInstance(25, .8f, true, true, "noauto"));
		trainingInstances.add(new ShuttleDataInstance(30, .2f, true, true, "auto"));
		trainingInstances.add(new ShuttleDataInstance(35, .4f, true, true, "noauto"));
		trainingInstances.add(new ShuttleDataInstance(70, .6f, false, false, "auto"));
		trainingInstances.add(new ShuttleDataInstance(20, .5f, false, true, "auto"));
		trainingInstances.add(new ShuttleDataInstance(75, .1f, false, false, "auto"));
		trainingInstances.add(new ShuttleDataInstance(80, .2f, true, true, "noauto"));
		trainingInstances.add(new ShuttleDataInstance(85, .8f, false, true, "noauto"));
		trainingInstances.add(new ShuttleDataInstance(60, .9f, false, true, "noauto"));
		
		testInstances.add(new ShuttleDataInstance(35, .1f, true, false));
		testInstances.add(new ShuttleDataInstance(80, .6f, false, true));
	}
}
