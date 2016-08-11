package com.ca.actions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ca.database.Database;
import com.ca.pojo.Event;
import com.opensymphony.xwork2.ActionSupport;

public class PastEventViewAction extends ActionSupport {

	List<Event> dataForPastEvents;

	public PastEventViewAction() {

	}

	public List<Event> getDataForPastEvents() {
		return dataForPastEvents;
	}

	public void setDataForPastEvents(List<Event> dataForPastEvents) {
		this.dataForPastEvents = dataForPastEvents;
	}

	@Override
	public String execute() throws Exception {

		System.out.println("Inside Exceute of Past event!!");
		try {
			Database database = new Database();
			Connection con = database.Get_Connection();
			PreparedStatement ps = con
					.prepareStatement("SELECT EVENT_ID,EVENT_NAME,COMPANY_NAME,CONTACT_PERSON,CONTACT_NO,EMAIL_ID,EVENT_VENUE,date_format(FROM_DATE,'%d%m%Y') as dateFromDate,date_format(TO_DATE,'%d/%m/%Y') as dateToDate,EVENT_TIME,COMMISSION,date_format(EVENT_DATE,'%d/%m/%Y') as dateEventDate,date_format(PAYMENT_DATE,'%d/%m/%Y') as dateAsPayment,PAYMENT_MODE,CHEQUE_DD_NO,BANK_NAME,PROFIT_EARNED,TOTAL_AMOUNT,RECEIVED_AMOUNT,EVENT_TDS,BALANCE_AMOUNT,PAID_STATUS FROM EVENT WHERE from_date <= NOW() AND PAID_STATUS = 'UNPAID' AND to_date <= NOW() ");
			ResultSet rs = ps.executeQuery();
			dataForPastEvents = new ArrayList<Event>();

			while (rs.next()) {
				dataForPastEvents.add(new Event(rs.getString("EVENT_ID"), rs
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
			}
			System.out.println("Records Added");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

}
