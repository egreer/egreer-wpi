
package cs4233.hw3;
/**
 * This class is the adapter for the NewModelEngine to the IEngine interface.
 * 
 * @author Eric Greer  		(egreer)
 * @author Jason Codding  	(jcodding)
 * @date 09-14-08
 * CS 4233-A08 HW3
 */
public class NewModelEngineAdapter implements IEngine{
	
	NewModelEngine engine;
	
	
	/**
	 * Default constructor. 
	 */
	public NewModelEngineAdapter(NewModelEngine e){
		engine = e;
	}
	
	/* (non-Javadoc)
	 * @see cs4233.hw3.IEngine#getRPM()
	 */
	public int getRPM(){
		return (int) engine.getEngineSpeed();
	}
	
	/* (non-Javadoc)
	 * @see cs4233.hw3.IEngine#setRPM(int)
	 */
	public void setRPM(int rpm){
		double change = rpm - engine.getEngineSpeed();
		engine.changeEngineSpeed(change);
	}
	
	/* (non-Javadoc)
	 * @see cs4233.hw3.IEngine#isRunning()
	 */
	public boolean isRunning(){
		if (engine.getEngineState() == NewModelEngine.EngineState.RUNNING ){
			return true;
		}else 
		return false;
	}
	
	/* (non-Javadoc)
	 * @see cs4233.hw3.IEngine#setRunning(boolean)
	 */
	public void setRunning(boolean running){
		if (running){
			engine.start();
		
		}else engine.stop();
	}
	
	/* (non-Javadoc)
	 * @see cs4233.hw3.IEngine#isGoingForward()
	 */
	public boolean isGoingForward(){
		return (engine.getDirection() == NewModelEngine.Direction.FORWARD);
	}
	
	/* (non-Javadoc)
	 * @see cs4233.hw3.IEngine#setGoingForward(boolean)
	 */
	public void setGoingForward(boolean goingForward){
		if((!this.isGoingForward() && goingForward) || (this.isGoingForward() && !goingForward)){
			engine.changeDirection();
		}

	}
}
