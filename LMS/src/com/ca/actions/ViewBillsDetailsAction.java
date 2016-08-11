package com.ca.actions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ca.database.Database;
import com.ca.pojo.Event;
import com.opensymphony.xwork2.ActionSupport;

public class ViewBillsDetailsAction extends ActionSupport {
	Event viewBills;
	List<Event> viewBillsDetailsList;

	public ViewBillsDetailsAction() {
		// TODO Auto-generated constructor stub
	}

	public Event getViewBills() {
		return viewBills;
	}

	public void setViewBills(Event viewBills) {
		this.viewBills = viewBills;
	}

	public List<Event> getViewBillsDetailsList() {
		return viewBillsDetailsList;
	}

	public void setViewBillsDetailsList(List<Event> viewBillsDetailsList) {
		this.viewBillsDetailsList = viewBillsDetailsList;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		try {
			Database database = new Database();
			Connection con = database.Get_Connection();
			PreparedStatement ps = con
					.prepareStatement("SELECT EVENT_NAME,COMPANY_NAME,CONTACT_PERSON,CONTACT_NO,EMAIL_ID,EVENT_VENUE ,date_format(FROM_DATE,'%d/%m/%Y') as fromBillsDate,date_format(TO_DATE,'%d/%m/%Y') as toBillsDate,EVENT_TIME ,date_format(EVENT_DATE,'%d/%m/%Y') as eventBillsDate,date_format(PAYMENT_DATE,'%d/%m/%Y') as paymentBillsDate,PAYMENT_MODE,CHEQUE_DD_NO,BANK_NAME,PROFIT_EARNED,TOTAL_AMOUNT,RECEIVED_AMOUNT,EVENT_TDS,BALANCE_AMOUNT,PAID_STATUS FROM EVENT WHERE EVENT_ID=?");
			ps.setString(1, viewBills.getEventId());
			ResultSet rs = ps.executeQuery();
			viewBillsDetailsList = new ArrayList<Event>();
			while (rs.next()) {
				viewBillsDetailsList.add(new Event(viewBills.getEventId(), rs
						.getString("EVENT_NAME"), rs.getString("COMPANY_NAME"),
						rs.getString("CONTACT_PERSON"), rs
								.getString("CONTACT_NO"), rs
								.getString("EMAIL_ID"), rs
								.getString("EVENT_VENUE"), rs
								.getString("fromBillsDate"), rs
								.getString("toBillsDate"), rs
								.getString("EVENT_TIME"), rs
								.getString("eventBillsDate"), rs
								.getString("paymentBillsDate"), rs
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
			System.out.println("Records Fetched...");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "success";
	}

}
