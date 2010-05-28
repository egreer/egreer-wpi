package hd.server.timesheet;

import hd.client.profile.Employee;
import hd.client.profile.UserService;
import hd.client.timesheet.ScheduleDetails;
import hd.client.timesheet.Timesheet;
import hd.client.timesheet.TimesheetService;
import hd.server.profile.UserServiceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pdfjet.Box;
import com.pdfjet.Font;
import com.pdfjet.Letter;
import com.pdfjet.Line;
import com.pdfjet.PDF;
import com.pdfjet.Page;
import com.pdfjet.TextLine;

public class TimesheetPrintService extends HttpServlet {

	/**Auto Generated Serialization ID*/
	private static final long serialVersionUID = -1184618409955840499L;
	private TimesheetService ts = new TimesheetServiceImpl();
	private UserService us = new UserServiceImpl();
	
	/** Constants */
	//Height Position
	double payLine = 50;
	double titleLine = 80;
	double nameLine = 100;
	double statusLine = 120;
	double dayLine = 160;
	double regLine = 190;
	double otrLine = 210;
	
	double empSigLine = 290;
	double superSigLine = 320;
	double deptSigLine = 350;
	double sigVerLine = 380;
	double instructionLine = 425;
	
	//Width Position
	double sigCol = 120;
	double dateCol = 360;
	
	double boxHeight = 20;
	double boxWidth = 40;
	
	double noSpace = 10;
	double spacing = 12;
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String[] idnumbs = req.getParameterValues("IDs");
		
		try {
			PDF pdf = new PDF();
		if (idnumbs != null){
			for(int i = 0; i < idnumbs.length ;i++){
				createPage(pdf, Long.parseLong((idnumbs[i])));
			}
		} else createPage(pdf, -1L);
		
			pdf.wrap();

			res.setStatus(200);
			res.setContentType( "application/pdf" );
			res.getOutputStream().write(pdf.getData().toByteArray());
			
		} catch (Exception e) {
			e.printStackTrace();
			res.setContentType("text/html");
			res.getWriter().write("Error creating PDF");
		}
	}
	
	void createPage(PDF pdf, long timesheetID) throws Exception{
		//If negative print blank time sheet
		Timesheet sheet;
		Employee emp;
		List<ScheduleDetails> details;
		if (timesheetID < 0){
			sheet = new Timesheet();
			emp = new Employee();
			details = new ArrayList<ScheduleDetails>();
		} else{
			sheet = ts.retrieveTimesheet(timesheetID);
			if (sheet == null) return;
			emp = us.fetchProfile(sheet.getEmployeeKey());
			if (emp == null) return;
			details = ts.retrieveScheduleDetails(sheet);
			padDetails(sheet.getStartDate(), sheet.getEndDate(), details);
		}
		
		
		
		Page page = new Page(pdf, Letter.LANDSCAPE);
		
		Font f1 = new Font(pdf, "Helvetica");
		f1.setSize(8);
		
		Font f2 = new Font(pdf, "Helvetica");
		f2.setSize(10);
		
		Font timeFont = new Font(pdf, "Helvetica");
		timeFont.setSize(12);

		//Payroll Line
		TextLine payId = new TextLine(f1,"Payroll Id: ST");
		payId.setPosition(30, payLine);
		payId.drawOn(page);

		TextLine payNum = new TextLine(f1, "Payroll No: ");
		payNum.setPosition(100, payLine);
		payNum.drawOn(page);
		
		TextLine payPeriod = new TextLine(f1, 
				"Pay Period: " + sheet.getStartDate() + " TO " + sheet.getEndDate());
		payPeriod.setPosition(200, payLine);
		payPeriod.drawOn(page);
		
		
		//Title Line
		TextLine empID = new TextLine(f1, "EMP ID#");
		empID.setPosition(220, titleLine);
		empID.drawOn(page);

		TextLine org = new TextLine(f1, "ORG");
		org.setPosition(300, titleLine);
		org.drawOn(page);
		
		TextLine dept = new TextLine(f1,"DEPT");
		dept.setPosition(350, titleLine);
		dept.drawOn(page);
		
		//Name Line
		TextLine name = new TextLine(f1, "Name: " + emp.getLastName() + ", " + emp.getFirstName());
		name.setPosition(20, nameLine);
		name.drawOn(page);
		
		TextLine id = new TextLine(f1, "ID: " + emp.getIdNumber());
		id.setPosition(190, nameLine);
		id.drawOn(page);
		
		TextLine orgNum = new TextLine(f1, "1    8100");
		orgNum.setPosition(280, nameLine);
		orgNum.drawOn(page);
		
		TextLine orgName = new TextLine(f1, "Computing and Communic");
		orgName.setPosition(340, nameLine);
		orgName.drawOn(page);
		
		//Status Line
		TextLine empStat = new TextLine(f1, "A Active");
		empStat.setPosition(sigCol, statusLine);
		empStat.drawOn(page);
		
		TextLine num = new TextLine(f1, "1.000   N");
		num.setPosition(280, statusLine);
		num.drawOn(page);
		
		TextLine ug = new TextLine(f1,
        "US Undergraduate Student Wor   1 8100 Computing and Communications Cent");
		ug.setPosition(360, statusLine);
		ug.drawOn(page);
		
		//Day Line
		TextLine sun1 = new TextLine(f1, "SU");
		sun1.setPosition(sigCol + boxWidth/2, dayLine);
		sun1.drawOn(page);
		
		TextLine mon1 = new TextLine(f1, "MO");
		mon1.setPosition(sigCol + 3*(boxWidth/2), dayLine);
		mon1.drawOn(page);
		
		TextLine tue1 = new TextLine(f1, "TU");
		tue1.setPosition(sigCol + 5*(boxWidth/2), dayLine);
		tue1.drawOn(page);
		
		TextLine wed1 = new TextLine(f1, "WE");
		wed1.setPosition(sigCol + 7*(boxWidth/2), dayLine);
		wed1.drawOn(page);
		
		TextLine thu1 = new TextLine(f1, "TH");
		thu1.setPosition(sigCol + 9*(boxWidth/2), dayLine);
		thu1.drawOn(page);
		
		TextLine fri1 = new TextLine(f1, "FR");
		fri1.setPosition(sigCol + 11*(boxWidth/2), dayLine);
		fri1.drawOn(page);
		
		TextLine sat1 = new TextLine(f1, "SA");
		sat1.setPosition(sigCol + 13*(boxWidth/2), dayLine);
		sat1.drawOn(page);
		
		TextLine sun2 = new TextLine(f1, "SU");
		sun2.setPosition(sigCol + 15*(boxWidth/2), dayLine);
		sun2.drawOn(page);
		
		TextLine mon2 = new TextLine(f1, "MO");
		mon2.setPosition(sigCol + 17*(boxWidth/2), dayLine);
		mon2.drawOn(page);
		
		TextLine tue2 = new TextLine(f1, "TU");
		tue2.setPosition(sigCol + 19*(boxWidth/2), dayLine);
		tue2.drawOn(page);
		
		TextLine wed2 = new TextLine(f1, "WE");
		wed2.setPosition(sigCol + 21*(boxWidth/2), dayLine);
		wed2.drawOn(page);
		
		TextLine thu2 = new TextLine(f1, "TH");
		thu2.setPosition(sigCol + 23*(boxWidth/2), dayLine);
		thu2.drawOn(page);
		
		TextLine fri2 = new TextLine(f1, "FR");
		fri2.setPosition(sigCol + 25*(boxWidth/2), dayLine);
		fri2.drawOn(page);
		
		TextLine sat2 = new TextLine(f1, "SA");
		sat2.setPosition(sigCol + 27*(boxWidth/2), dayLine);
		sat2.drawOn(page);
		
		//TotalHours
		TextLine totalLabel = new TextLine(f1, "TOTAL HOURS");
		totalLabel.setPosition(sigCol + 14*boxWidth, regLine - spacing);
		totalLabel.drawOn(page);
		
		//Reg Line
		TextLine regLabel = new TextLine(f2, "REG");
		regLabel.setPosition(90, regLine + spacing);
		regLabel.drawOn(page);
		
		for (int i = 0 ; i < 15 ; i++ ){
			Box tempBox = new Box(sigCol + i*(boxWidth), regLine, boxWidth, boxHeight);
			tempBox.drawOn(page);	
		}
					
		//Otr Line
		TextLine otrLabel = new TextLine(f2, "OTR");
		otrLabel.setPosition(90, otrLine + spacing);
		otrLabel.drawOn(page);
		
		for (int i = 0 ; i < 15 ; i++ ){
			Box tempBox = new Box(sigCol + i*(boxWidth), otrLine, boxWidth, boxHeight);
			tempBox.drawOn(page);	
		}
		
		//Student Signature Line
		Line empLine = new Line(sigCol, empSigLine - 8 , dateCol + 20 , empSigLine - 8);
		empLine.drawOn(page);
		
		TextLine studentSig = new TextLine(f1, "WPI STUDENT (REQUIRED)");
		studentSig.setPosition(sigCol, empSigLine);
		studentSig.drawOn(page);
		
		TextLine studentSigDate = new TextLine(f1, "DATE");
		studentSigDate.setPosition(dateCol, empSigLine);
		studentSigDate.drawOn(page);
		
		
		//Supervisor Signature Line
		Line superLine = new Line(sigCol, superSigLine - 8 , dateCol + 20 , superSigLine - 8);
		superLine.drawOn(page);
		
		TextLine supervisorSig = new TextLine(f1, "SUPERVISOR (OPRTIONAL)");
		supervisorSig.setPosition(sigCol, superSigLine);
		supervisorSig.drawOn(page);
		
		TextLine supervisorSigDate = new TextLine(f1, "DATE");
		supervisorSigDate.setPosition(dateCol, superSigLine);
		supervisorSigDate.drawOn(page);
		
		//Dept Head Signiture Line
		Line deptLine = new Line(sigCol, deptSigLine - 8 , dateCol + 20 , deptSigLine - 8);
		deptLine.drawOn(page);
		
		TextLine deptSig = new TextLine(f1, "DEPT HEAD (REQUIRED)");
		deptSig.setPosition(sigCol, deptSigLine);
		deptSig.drawOn(page);
		
		TextLine deptSigDate = new TextLine(f1, "DATE");
		deptSigDate.setPosition(dateCol, deptSigLine);
		deptSigDate.drawOn(page);
		
		//Sig Verification Line
		TextLine sigVer = new TextLine(f2, 
				"Signatures verify work was performed in a satisfactory manner.");
		sigVer.setPosition(sigCol, sigVerLine);
		sigVer.drawOn(page);
					
		
		//Instructions
		TextLine instTitle = new TextLine(f2, "INSTRUCTIONS");
		instTitle.setPosition(sigCol, instructionLine);
		instTitle.drawOn(page);
		
		TextLine payrollDetails = new TextLine(f1, "PAYROLL OFFICE DEADLINES:");
		payrollDetails.setPosition(sigCol, instructionLine+spacing);
		payrollDetails.drawOn(page);
		
		TextLine payrollDetails2 = new TextLine(f1, 
				"a. Employment Authorization and Change of Personnel Status forms: 10 a.m. of the second Thursday of each pay period.");
		payrollDetails2.setPosition(sigCol, instructionLine+ spacing + noSpace);
		payrollDetails2.drawOn(page);
		
		TextLine payrollDetails3 = new TextLine(f1, 
				"B. Time Cards: 10 a.m. of the Monday following each pay period");
		payrollDetails3.setPosition(sigCol, instructionLine + spacing + 2*(noSpace));
		payrollDetails3.drawOn(page);
					
		TextLine reptDetails = new TextLine(f1,"REPORTING HOURS:");
		reptDetails.setPosition(sigCol, instructionLine+ 2*(spacing) + 2*(noSpace));
		reptDetails.drawOn(page);
		
		TextLine reptDetails2 = new TextLine(f1, 
				"A. Report only hours actually worked");
		reptDetails2.setPosition(sigCol, instructionLine+ 2*(spacing) + 3*(noSpace));
		reptDetails2.drawOn(page);
		
		TextLine reptDetails3 = new TextLine(f1, 
				"B. Partial hours must be expressed as following decimals only:     .25 .50 and .75 all other designations will be interperted as .00 for payroll purpose");
		reptDetails3.setPosition(sigCol, instructionLine + 2*(spacing) + 4*(noSpace));
		reptDetails3.drawOn(page);
		
		
		TextLine twoDetails = new TextLine(f1, 
				"TWO-WEEK PERIOD ENDED:");
		twoDetails.setPosition(sigCol, instructionLine + 3*(spacing) + 4*(noSpace));
		twoDetails.drawOn(page);
		
		TextLine twoDetails2 = new TextLine(f1, 
				"Must ");
		twoDetails2.setPosition(sigCol, instructionLine + 3*(spacing) + 5*(noSpace));
		twoDetails2.drawOn(page);
		
		TextLine orgDetails = new TextLine(f1, 
				"ORG:");
		orgDetails.setPosition(sigCol, instructionLine + 4*(spacing) + 5*(noSpace));
		orgDetails.drawOn(page);

		TextLine orgDetails2 = new TextLine(f1, 
				"Must \n");
		orgDetails2.setPosition(sigCol, instructionLine + 4*(spacing) + 6*(noSpace));
		orgDetails2.drawOn(page);

		
		TextLine timeDetails = new TextLine(f1, 
				"TIME CARDS WITHOUT STUDENT AND DEPT. HEAD SIGNATURES:");
		timeDetails.setPosition(sigCol, instructionLine + 5*(spacing) + 6*(noSpace));
		timeDetails.drawOn(page);
		
		TextLine timeDetails2 = new TextLine(f1, 
				"Will not be processed until properly completed.");
		timeDetails2.setPosition(sigCol, instructionLine + 5*(spacing) + 7*(noSpace));
		timeDetails2.drawOn(page);
	}

	void padDetails(Date start, Date end, List<ScheduleDetails> details){
		while(true){
			Date tempDate = null;
			
			Iterator<ScheduleDetails> iterator = details.iterator();
			while (iterator.hasNext()){
				ScheduleDetails tempDetail = iterator.next();
				if (tempDetail.getDate().equals(tempDate)) break;
			}
			
		}
	}
}
