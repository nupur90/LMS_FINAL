package com.ca.actions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ca.database.Database;
import com.ca.pojo.Event;
import com.ca.pojo.License;
import com.opensymphony.xwork2.ActionSupport;

public class RetrieveEventsAction extends ActionSupport {
	Event retrieveEvents;
	List<Event> allList;
	List<License> licenceList;
	double dvar = 0.0;
	List<License> totalamount;

	public Event getRetrieveEvents() {
		return retrieveEvents;
	}

	public void setRetrieveEvents(Event retrieveEvents) {
		this.retrieveEvents = retrieveEvents;
	}

	public RetrieveEventsAction() {
		// TODO Auto-generated constructor stub
	}

	public List<Event> getAllList() {
		return allList;
	}

	public void setAllList(List<Event> allList) {
		this.allList = allList;
	}

	public List<License> getLicenceList() {
		return licenceList;
	}

	public void setLicenceList(List<License> licenceList) {
		this.licenceList = licenceList;
	}
	
	
	


	public List<License> getTotalamount() {
		return totalamount;
	}

	public void setTotalamount(List<License> totalamount) {
		this.totalamount = totalamount;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		try {
			Database database = new Database();
			Connection con = database.Get_Connection();
			PreparedStatement ps = con
					.prepareStatement("SELECT EVENT_ID, EVENT_NAME,COMPANY_NAME,CONTACT_PERSON,CONTACT_NO,EMAIL_ID,EVENT_VENUE,date_format(FROM_DATE,'%d/%m/%Y') as dateFromDate,date_format(TO_DATE,'%d/%m/%Y') as dateToDate,EVENT_TIME,COMMISSION,date_format(EVENT_DATE,'%d/%m/%Y') as dateEventDate,date_format(PAYMENT_DATE,'%d/%m/%Y') as dateAsPayment,PAYMENT_MODE,CHEQUE_DD_NO,BANK_NAME,PROFIT_EARNED,TOTAL_AMOUNT,RECEIVED_AMOUNT,EVENT_TDS,BALANCE_AMOUNT,PAID_STATUS FROM EVENT WHERE EVENT_ID=?");
			ps.setString(1, retrieveEvents.getEventId());
			ResultSet rs = ps.executeQuery();
			allList = new ArrayList<Event>();
			while (rs.next()) {
				
				allList.add(new Event(rs.getString("EVENT_ID"),rs.getString("EVENT_NAME"), rs
						.getString("COMPANY_NAME"), rs
						.getString("CONTACT_PERSON"), rs
						.getString("CONTACT_NO"), rs.getString("EMAIL_ID"), rs
						.getString("EVENT_VENUE"),
						rs.getString("dateFromDate"), rs
								.getString("dateToDate"), rs
								.getString("EVENT_TIME"), rs
								.getString("COMMISSION"), rs
								.getString("dateEventDate"), rs
								.getString("dateAsPayment"), rs
								.getString("PAYMENT_MODE"), rs
								.getString("CHEQUE_DD_NO"), rs
								.getString("BANK_NAME"), rs
								.getString("PROFIT_EARNED"), rs
								.getString("TOTAL_AMOUNT"), rs
								.getString("RECEIVED_AMOUNT"), rs
								.getString("EVENT_TDS"), rs
								.getString("BALANCE_AMOUNT")));
				System.out.println("Inside Retrieve Events action!!!");

			}
			PreparedStatement ps1 = con
					.prepareStatement("SELECT LICENSE_ID,LICENSE_NAME,EVENT_NAME,APPLIED_DATE,RECEIVED_DATE,PERSON_ALLOTED,AMOUNT,REMARKS,DD_NUMBER,date_format(DD_DATE,'%d/%m/%Y') as ddDate,DD_AMOUNT,BANK_NAME,DD_SUBMITTED FROM LICENSE WHERE EVENT_ID=?");
			ps1.setString(1, retrieveEvents.getEventId());
			System.out.println("For Licence");
			ResultSet rs1 = ps1.executeQuery();
			licenceList = new ArrayList<License>();
			System.out.println("Step 1");
			while (rs1.next()) {
				System.out.println(rs1.getString("AMOUNT"));
				//dvar=dvar+Double.parseDouble(rs1.getString("AMOUNT")) ;
				System.out.println(dvar);
				licenceList.add(new License(rs1.getString("LICENSE_ID"), rs1
						.getString("LICENSE_NAME"),
						rs1.getString("EVENT_NAME"), rs1
								.getString("APPLIED_DATE"), rs1
								.getString("RECEIVED_DATE"), rs1
								.getString("PERSON_ALLOTED"), rs1
								.getString("AMOUNT"), rs1.getString("REMARKS"),
						rs1.getString("DD_NUMBER"), rs1.getString("ddDate"),
						rs1.getString("DD_AMOUNT"), rs1.getString("BANK_NAME"),
						rs1.getString("DD_SUBMITTED")));
				System.out.println("Inside Second Array List..");
			}
			System.out.println("Step 2");
			
			
			
			PreparedStatement ps2 = con
					.prepareStatement("select sum(amount) as totamount from license where event_id = ?");
			ps2.setString(1, retrieveEvents.getEventId());
			System.out.println("For Sum of total amount");
			
			ResultSet rs2 = ps2.executeQuery();
			totalamount = new ArrayList<License>();
			rs2.next();
			int totsum = rs2.getInt("totamount");
			System.out.println(totsum);
			totalamount.add(new License (rs2.getInt("totamount")));
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

}
