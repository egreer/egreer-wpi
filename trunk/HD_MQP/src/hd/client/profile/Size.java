package hd.client.profile;

/** Shirt Size enumeration:
 * Sizes:		
 * 		Small
 * 		Medium
 * 		Large
 * 		Extra Large
 * 		2XL
 * 		3XL
 * @author Eric Greer (egreer@alum.wpi.edu)
 * @author Jason Codding (jcodding@alum.wpi.edu)
 * WPI Helpdesk MQP 2009-2010 
 */
public enum Size{
	U(""), S("Small"), M("Medium"), L("Large"), XL("Extra Large"), 
	XXL("2XL"), XXXL("3XL");

	private String size;

	Size(String size) {
		this.size = size;
	}
	
	@Override
	public String toString() {
		return size;
	}

}
