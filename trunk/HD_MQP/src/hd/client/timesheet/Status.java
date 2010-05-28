package hd.client.timesheet;

/** Enumeration of Status Codes for Timesheets
 * Status:
 * 		Assigned:	Sheet is waiting for the employee to complete 
 * 		Pending:	Sheet has been submitted and is waiting for manager approval
 * 		Approved:	Sheet has been approved by the manager 
 * 		Closed:		Manager is done with the sheet
 * 
 * @author Eric Greer (egreer@alum.wpi.edu)
 * @author Jason Codding (jcodding@alum.wpi.edu)
 * WPI Helpdesk MQP 2009-2010 
 */
public enum Status {
	Assigned("Assigned"), Pending("Pending"), Approved("Approved"), Closed("Closed");
	private String status;

	Status(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return status;
	}
}
