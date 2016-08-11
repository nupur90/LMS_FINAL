package com.ca.actions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ca.database.Database;
import com.ca.pojo.Event;
import com.opensymphony.xwork2.ActionSupport;

public class FetchDetailsForBillsJspTableAction extends ActionSupport {
	Event fetchBills;
	List<Event> fetchList;
	private String eventState;
	private String companyState;

	public List<Event> getFetchList() {
		return fetchList;
	}

	public void setFetchList(List<Event> fetchList) {
		this.fetchList = fetchList;
	}

	public Event getFetchBills() {
		return fetchBills;
	}

	public void setFetchBills(Event fetchBills) {
		this.fetchBills = fetchBills;
	}

	public FetchDetailsForBillsJspTableAction() {
		// TODO Auto-generated constructor stub

	}

	public String getEventState() {
		return eventState;
	}

	public void setEventState(String eventState) {
		this.eventState = eventState;
	}

	public String getCompanyState() {
		return companyState;
	}

	public void setCompanyState(String companyState) {
		this.companyState = companyState;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub

		try {
			Database database = new Database();
			Connection con = database.Get_Connection();

			PreparedStatement ps = con
					.prepareStatement("SELECT EVENT_ID,CONTACT_PERSON,CONTACT_NO,EMAIL_ID,EVENT_VENUE,date_format(FROM_DATE,'%d/%m/%Y') as dateAsFromDate,date_format(TO_DATE,'%d/%m/%Y') as dateAsToDate ,EVENT_TIME FROM EVENT WHERE EVENT_NAME=? AND COMPANY_NAME=?");
			ps.setString(1, getEventState());
			ps.setString(2, getCompanyState());
			System.out.println(getEventState());
			fetchList = new ArrayList<Event>();
			System.out.println("Step 1");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				fetchList.add(new Event(rs.getString("EVENT_ID"), rs
						.getString("CONTACT_PERSON"), rs
						.getString("CONTACT_NO"), rs.getString("EMAIL_ID"), rs
						.getString("EVENT_VENUE"), rs
						.getString("dateAsFromDate"), rs
						.getString("dateAsToDate"), rs.getString("EVENT_TIME")));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		;
		return "success";

	}

}
