package cs4233.hw3;
/**
 * This class is the adapter for the SimpleSpeedController to the IEngine interface.
 * 
 * @author Eric Greer  		(egreer)
 * @author Jason Codding  	(jcodding)
 * @date 09-14-08
 * CS 4233-A08 HW3
 */
public class SimpleSpeedControllerAdapter implements ISpeedController{
	
	SimpleSpeedController ssc;
	
	/**
	 * Default Constructor
	 * @param convert is the SimpleSpeedController to be converted
	 * @param engine is the engine for the SpeedController
	 * @param gearRatio is the gearRatio for the Engine
	 */
	public SimpleSpeedControllerAdapter(SimpleSpeedController convert, IEngine engine, double gearRatio){
		ssc = convert;
		ssc.setEngine(engine, gearRatio);
	}
	

	/* (non-Javadoc)
	 * @see cs4233.hw3.ISpeedController#setSpeed(double)
	 */
	public boolean setSpeed(double cpmSpeed){
		
		
		if ((cpmSpeed < 0 )) return false;
		
		// 1in = 2.54cm
		double ipmSpeed = cpmSpeed / 2.54 ;
		double delta = ipmSpeed - ssc.getSpeed();
		ssc.changeSpeed(delta);
		
		double currentSpeed = ssc.getSpeed();
		
		
		if (currentSpeed == ipmSpeed) return true;
		//Else 5% Error is good enough to be true
		double percentError = ((currentSpeed - ipmSpeed) /ipmSpeed)* 100;
		if (5 >= percentError) return true;
		else return false;	
	}
	
	/* (non-Javadoc)
	 * @see cs4233.hw3.ISpeedController#getSpeed()
	 */
	public double getSpeed(){
		return (ssc.getSpeed() * 2.54);
	}
	
	/* (non-Javadoc)
	 * @see cs4233.hw3.ISpeedController#getControlledEngine()
	 */
	public IEngine getControlledEngine(){
		return ssc.getEngine();
	}
}
