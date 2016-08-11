package com.ca.actions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ca.database.Database;
import com.ca.pojo.Event;
import com.opensymphony.xwork2.ActionSupport;

public class HomeAction extends ActionSupport {
	private String searchEvent;

	public HomeAction() {

		// TODO Auto-generated constructor stub
	}

	List<Event> dataForViewOrEditEvents;

	public String getSearchEvent() {
		return searchEvent;
	}

	public void setSearchEvent(String searchEvent) {
		this.searchEvent = searchEvent;
	}

	public List<Event> getDataForViewOrEditEvents() {
		return dataForViewOrEditEvents;
	}

	public void setDataForViewOrEditEvents(List<Event> dataForViewOrEditEvents) {
		this.dataForViewOrEditEvents = dataForViewOrEditEvents;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("This is the value.." + getSearchEvent());
		Database database = new Database();
		Connection con = database.Get_Connection();
		PreparedStatement ps;
		dataForViewOrEditEvents = new ArrayList<Event>();

		try {

			if (getSearchEvent().length() > 0) {
				ps = con.prepareStatement("SELECT EVENT_ID,EVENT_NAME,COMPANY_NAME,date_format(FROM_DATE,'%d/%m/%Y') as dateFromDate,date_format(TO_DATE,'%d/%m/%Y') as dateToDate FROM EVENT WHERE EVENT_NAME LIKE ?");
				ps.setString(1, "%" + getSearchEvent() + "%");
				ResultSet r2 = ps.executeQuery();
				while (r2.next()) {
					dataForViewOrEditEvents.add(new Event(r2
							.getString("EVENT_ID"), r2.getString("EVENT_NAME"),
							r2.getString("COMPANY_NAME"), r2
									.getString("dateFromDate"), r2
									.getString("dateToDate")));
				}
			} else {
				ps = con.prepareStatement("SELECT EVENT_ID,EVENT_NAME,COMPANY_NAME,date_format(FROM_DATE,'%d/%m/%Y') as dateFromDate,date_format(TO_DATE,'%d/%m/%Y') as dateToDate FROM EVENT");
				ResultSet r1 = ps.executeQuery();
				while (r1.next()) {
					dataForViewOrEditEvents.add(new Event(r1
							.getString("EVENT_ID"), r1.getString("EVENT_NAME"),
							r1.getString("COMPANY_NAME"), r1
									.getString("dateFromDate"), r1
									.getString("dateToDate")));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
}
