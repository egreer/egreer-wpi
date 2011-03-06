package hw3;


public class ShuttleDataInstance {
	public float stability;
	public float error;
	public boolean	headWind;	// Head wind
	public boolean visability;
	public String classification; //True if auto
	public Double distance; // used for computing euclidean distance;
	
	public ShuttleDataInstance(float stability, float error, boolean headWind,
			boolean visability) {
		this(stability, error, headWind, visability, "None");
	}
	
	public ShuttleDataInstance(float stability, float error, boolean headWind,
			boolean visability, String classification) {
		this.stability = stability;
		this.error = error;
		this.headWind = headWind;
		this.visability = visability;
		this.classification = classification;
	}
	
	@Override
	public String toString() {
		String returner  = "Stability = (" + this.stability;
		returner += ") Error = (" + this.error;
		returner += ") " + ((this.headWind) ? "Head Wind" : "Tail Wind"); 
		returner += " " + ((this.visability) ? "Visability" : "No visability");
		returner += " Classification: " + classification;
		return returner;
	}
	
}
