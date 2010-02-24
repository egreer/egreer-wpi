package cs4233.hw3;
/**
 * This class is the adapter for the RadianDirectionControl to the IEngine interface.
 * 
 * @author Eric Greer  		(egreer)
 * @author Jason Codding  	(jcodding)
 * @date 09-14-08
 * CS 4233-A08 HW3
 */
public class RadianDirectionControlAdapter implements IDirectionController{
	
	RadianDirectionControl rdc;
	
	/**
	 * Default Constructor
	 * @param convert is the RadianDirectionControl to be converted
	 */
	public RadianDirectionControlAdapter(RadianDirectionControl convert){
		rdc = convert;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cs4233.hw3.IDirectionController#turn(double)
	 */
	public void turn(double turnAngle){
		if(turnAngle > 0){
			rdc.turnRight(Math.toRadians(turnAngle));
			
		}else if (turnAngle < 0){
			rdc.turnLeft(Math.toRadians(Math.abs(turnAngle)));
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see cs4233.hw3.IDirectionController#getCurrentDirection()
	 */
	
	public double getCurrentDirection(){ //rounded to 2 decimal places
		return (Math.round(100*Math.toDegrees(rdc.getDirection()))/100.00);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see cs4233.hw3.IDirectionController#setCurrentDirection(double)
	 */
	public void setCurrentDirection(double newDirection){
		this.turn(newDirection - this.getCurrentDirection()); 
	}
	
}
