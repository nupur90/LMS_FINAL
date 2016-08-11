package com.ca.actions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ca.database.Database;
import com.ca.pojo.Event;
import com.opensymphony.xwork2.ActionSupport;

public class UpcomingEventViewAction extends ActionSupport{
	
	List<Event> dataForUpcomingEvents;

	public UpcomingEventViewAction() {
		
	}

	public List<Event> getDataForUpcomingEvents() {
		return dataForUpcomingEvents;
	}

	public void setDataForUpcomingEvents(List<Event> dataForUpcomingEvents) {
		this.dataForUpcomingEvents = dataForUpcomingEvents;
	}
	
	
	@Override
	public String execute() throws Exception {

		System.out.println("Inside Exceute of Upcoming event!!");
		try {
			Database database = new Database();
			Connection con = database.Get_Connection();
			PreparedStatement ps = con
					.prepareStatement("SELECT EVENT_ID,EVENT_NAME,COMPANY_NAME,CONTACT_PERSON,CONTACT_NO,EMAIL_ID,EVENT_VENUE,date_format(FROM_DATE,'%d%m%Y') as dateFromDate,date_format(TO_DATE,'%d/%m/%Y') as dateToDate,EVENT_TIME,COMMISSION,date_format(EVENT_DATE,'%d/%m/%Y') as dateEventDate,date_format(PAYMENT_DATE,'%d/%m/%Y') as dateAsPayment,PAYMENT_MODE,CHEQUE_DD_NO,BANK_NAME,PROFIT_EARNED,TOTAL_AMOUNT,RECEIVED_AMOUNT,EVENT_TDS,BALANCE_AMOUNT,PAID_STATUS FROM EVENT WHERE from_date >= NOW() AND PAID_STATUS = 'PAID' OR PAID_STATUS = 'UNPAID'  AND to_date >= NOW() ");
			ResultSet rs = ps.executeQuery();
			dataForUpcomingEvents = new ArrayList<Event>();

			while (rs.next()) {
				dataForUpcomingEvents.add(new Event(rs.getString("EVENT_ID"), rs
						.getString("EVENT_NAME"), rs.getString("COMPANY_NAME"),
						rs.getString("CONTACT_PERSON"), rs
								.getString("CONTACT_NO"), rs
								.getString("EMAIL_ID"), rs
								.getString("EVENT_VENUE"), rs
								.getString("dateFromDate"), rs
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
								.getString("BALANCE_AMOUNT"), rs
								.getString("PAID_STATUS")));
				
				System.out.println("inside while of upcoming event!!");
			}
			System.out.println("Records Added in upcoming events section");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

}

	
