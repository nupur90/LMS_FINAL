package com.ca.actions;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ca.database.Database;
import com.ca.pojo.Event;
import com.ca.pojo.Profarma;

import java.sql.PreparedStatement;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import jdk.nashorn.internal.objects.annotations.Getter;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class RetrieveEvNaCoNaAction extends ActionSupport implements
		Preparable, SessionAware {

	private static final long serialVersionUID = -5418233715172672477L;
	private SessionMap<String, Object> sessionMapPaid;

	private List<Event> dataForBillsJspList = new ArrayList<Event>();

	private List<String> events = new ArrayList<String>();
	private List<String> companies = new ArrayList<String>();

	private String event = null;
	private String company = null;
	private String fromdate = null;
	private String todate = null;

	public SessionMap<String, Object> getSessionMapPaid() {
		return sessionMapPaid;
	}

	public void setSessionMapPaid(SessionMap<String, Object> sessionMapPaid) {
		this.sessionMapPaid = sessionMapPaid;
	}

	public String getFromdate() {
		return fromdate;
	}

	public void setFromdate(String fromdate) {
		this.fromdate = fromdate;
	}

	public String getTodate() {
		return todate;
	}

	public void setTodate(String todate) {
		this.todate = todate;
	}

	public RetrieveEvNaCoNaAction() {
		// TODO Auto-generated constructor stub
	}

	public List<Event> getDataForBillsJspList() {
		return dataForBillsJspList;
	}

	public void setDataForBillsJspList(List<Event> dataForBillsJspList) {
		this.dataForBillsJspList = dataForBillsJspList;
	}

	public List<String> getEvents() {
		return events;
	}

	public void setEvents(List<String> events) {
		this.events = events;
	}

	public List<String> getCompanies() {
		return companies;
	}

	public void setCompanies(List<String> companies) {
		this.companies = companies;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@Override
	public void prepare() throws Exception {
		Connection con = null;
		try {
			con = new Database().Get_Connection();

			// load companies
			PreparedStatement ps = con
					.prepareStatement("SELECT DISTINCT company_name FROM event");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				companies.add(rs.getString("company_name"));
			}

			// load events
			ps = con.prepareStatement("SELECT DISTINCT event_name FROM event");
			rs = ps.executeQuery();
			while (rs.next()) {
				events.add(rs.getString("event_name"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
	}

	@Override
	public String execute() throws Exception {
		Connection con = null;
		try {
			con = new Database().Get_Connection();

			// load the table. The first time the table is loaded completely
			String sql = "SELECT EVENT_ID, EVENT_NAME, COMPANY_NAME, CONTACT_PERSON, CONTACT_NO, EMAIL_ID, EVENT_VENUE, "
					+ "date_format(FROM_DATE,'%d/%m/%Y') as dateAsFrom, date_format(TO_DATE,'%d/%m/%Y') as dateAsTo ,EVENT_TIME "
					+ "FROM event";
			String where = "";

			// if instead this action has been called from the JSP page,
			// the result is filtered on event and company:
			if (event != null || company != null) {
				where = " WHERE event_name = ? OR company_name = ?";
			}

			// load companies
			PreparedStatement ps = con.prepareStatement(sql + where);
			if (where.length() > 0) {
				ps.setString(1, event);
				ps.setString(2, company);
			}

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				dataForBillsJspList.add(new Event(rs.getString("EVENT_ID"), rs
						.getString("EVENT_NAME"), rs.getString("COMPANY_NAME"),
						rs.getString("CONTACT_PERSON"), rs
								.getString("CONTACT_NO"), rs
								.getString("EMAIL_ID"), rs
								.getString("EVENT_VENUE"), rs
								.getString("dateAsFrom"), rs
								.getString("dateAsTo"), rs
								.getString("EVENT_TIME")));
			}

			PreparedStatement ps2 = con
					.prepareStatement("SELECT PAID_STATUS FROM EVENT WHERE EVENT_NAME=? AND COMPANY_NAME=?");
			ps2.setString(1, event);
			ps2.setString(2, company);
			ResultSet rs2 = ps2.executeQuery();
			if (rs2.next()) {
				sessionMapPaid.put("paid_status", rs2.getString("PAID_STATUS"));
			}
			System.out.println("Paid Crossed");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
		}

		return SUCCESS;
	}

	@Override
	public void setSession(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sessionMapPaid = (SessionMap) map;
	}

}