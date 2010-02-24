package cs4233.hw7;

/** 
 * When a customer connects to the TravelWeenie Web site, a connection is created. 
 This connection handles all transactions for the customer for the current session. 
 Whenever the customer wants to find flights, cars, or hotels for a trip, this
 connection manages each of the tasks.
 * 
 * TravelGuruConnection implements the Strategy Pattern. It goes through the algorithm 
 * that doesn't change and relies on subclasses to implement the behavior. The subclasses are all managers
 *
 */
public class TravelGuruConnection {
	flightManager fm;
	carManager cm;
	hotelManager hm;
	Customer cu;
	statusManagerRelation smr;

	/** Constructor for the TravelGuruConnection
	 * 
	 */
	public TravelGuruConnection(Customer customer){
		cu = customer;
	}
	
	/** Implements the algorithm of searching through flights, selecting flights, and then booking them.
	 * 
	 */
	public void getFlight(){

	}
	
	/** Implements the algorithm of searching through cars, selecting cars, and then booking them.
	 * 
	 */
	public void getCar(){

	}
	
	/** Implements the algorithm of searching through hotels, selecting hotels, and then booking them.
	 * 
	 */
	public void getHotel(){

	}
}
