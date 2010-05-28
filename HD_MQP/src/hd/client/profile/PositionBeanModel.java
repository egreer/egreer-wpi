package hd.client.profile;
import com.extjs.gxt.ui.client.data.BeanModelMarker;
import com.extjs.gxt.ui.client.data.BeanModelMarker.BEAN;

/** The interface to convert the Position class to a Bean 
 * for use with GXT grids. 
 * 
 * @author Eric Greer (egreer@alum.wpi.edu)
 * @author Jason Codding (jcodding@alum.wpi.edu)
 * WPI Helpdesk MQP 2009-2010
 */
@BEAN(Position.class)
public interface PositionBeanModel extends BeanModelMarker{

}
