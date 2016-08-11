package com.ca.actions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.ca.database.Database;
import com.ca.pojo.AddUserByAdmin;
import com.ca.pojo.Event;
import com.opensymphony.xwork2.ActionSupport;

public class UserLoginAction extends ActionSupport implements SessionAware {
	AddUserByAdmin login;
	private SessionMap<String, Object> sessionMap;
	List<AddUserByAdmin> ownList;
	List<Event> dataForViewOrEditEvents;

	public UserLoginAction() {
		// TODO Auto-generated constructor stub
	}

	public List<Event> getDataForViewOrEditEvents() {
		return dataForViewOrEditEvents;
	}

	public void setDataForViewOrEditEvents(List<Event> dataForViewOrEditEvents) {
		this.dataForViewOrEditEvents = dataForViewOrEditEvents;
	}

	public AddUserByAdmin getLogin() {
		return login;
	}

	public void setLogin(AddUserByAdmin login) {
		this.login = login;
	}

	public SessionMap<String, Object> getSessionMap() {
		return sessionMap;
	}

	public void setSessionMap(SessionMap<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	public List<AddUserByAdmin> getOwnList() {
		return ownList;
	}

	public void setOwnList(List<AddUserByAdmin> ownList) {
		this.ownList = ownList;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		try {
			Database database = new Database();
			Connection con = database.Get_Connection();
			PreparedStatement ps = con
					.prepareStatement("SELECT * FROM EMPLOYEE_INFO WHERE USERNAME=? AND PASSWORD=? ");
			ps.setString(1, login.getUserName());
			ps.setString(2, login.getPassWord());
			ResultSet rs = ps.executeQuery();
			PreparedStatement ps1 = con
					.prepareStatement("SELECT EMPLOYEE_NAME,PASSWORD,ADDRESS,CONTACT_NO,EMAIL,ROLE,date_format(DOB,'%d/%m/%Y') as dateAsBirth from employee_info where USERNAME=?");
			ps1.setString(1, login.getUserName());
			ResultSet rs1 = ps1.executeQuery();
			ownList = new ArrayList<AddUserByAdmin>();
			PreparedStatement p1 = con
					.prepareStatement("SELECT EVENT_ID,EVENT_NAME,COMPANY_NAME,CONTACT_PERSON,CONTACT_NO,EMAIL_ID,EVENT_VENUE,date_format(FROM_DATE,'%d%m%Y') as dateFromDate,date_format(TO_DATE,'%d/%m/%Y') as dateToDate,EVENT_TIME,COMMISSION,date_format(EVENT_DATE,'%d/%m/%Y') as dateEventDate,date_format(PAYMENT_DATE,'%d/%m/%Y') as dateAsPayment,PAYMENT_MODE,CHEQUE_DD_NO,BANK_NAME,PROFIT_EARNED,TOTAL_AMOUNT,RECEIVED_AMOUNT,EVENT_TDS,BALANCE_AMOUNT,PAID_STATUS FROM EVENT");
			ResultSet r1 = p1.executeQuery();
			dataForViewOrEditEvents = new ArrayList<Event>();
			
			while (rs1.next()) {
				ownList.add(new AddUserByAdmin(rs1.getString("EMPLOYEE_NAME"),
						rs1.getString("PASSWORD"), rs1.getString("ADDRESS"),
						rs1.getString("CONTACT_NO"), rs1.getString("EMAIL"),
						rs1.getString("dateAsBirth"), rs1.getString("ROLE")));
			}
			System.out.println("Data Collected ...");
			while (r1.next()) {
				dataForViewOrEditEvents.add(new Event(r1.getString("EVENT_ID"),
						r1.getString("EVENT_NAME"), r1
								.getString("COMPANY_NAME"), r1
								.getString("CONTACT_PERSON"), r1
								.getString("CONTACT_NO"), r1
								.getString("EMAIL_ID"), r1
								.getString("EVENT_VENUE"), r1
								.getString("dateFromDate"), r1
								.getString("dateToDate"), r1
								.getString("EVENT_TIME"), r1
								.getString("COMMISSION"), r1
								.getString("dateEventDate"), r1
								.getString("dateAsPayment"), r1
								.getString("PAYMENT_MODE"), r1
								.getString("CHEQUE_DD_NO"), r1
								.getString("BANK_NAME"), r1
								.getString("PROFIT_EARNED"), r1
								.getString("TOTAL_AMOUNT"), r1
								.getString("RECEIVED_AMOUNT"), r1
								.getString("EVENT_TDS"), r1
								.getString("BALANCE_AMOUNT"), r1
								.getString("PAID_STATUS")));
			}

			if (rs.next()) {
				sessionMap.put("login", "true");
				sessionMap.put("name", login.getUserName());// username has been
															// set in session of
															// logged in user
															// here.
				sessionMap.put("role", rs.getString("ROLE"));// Role is set of
																// logged in
																// user.
				System.out.println("Logged in..");

				return "success";
			} else {
				return "error";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

/*	@Override
	public void validate() {
		// TODO Auto-generated method stub
		super.validate();
		if (login.getUserName().isEmpty()) {
			addFieldError("login.userName", "Please Enter Your Username..");
		}
		if (login.getPassWord().isEmpty()) {
			addFieldError("login.passWord", "Please Enter Your Password..");
		}
	}*/

	@Override
	public void setSession(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sessionMap = (SessionMap) map;
	}

	@SkipValidation
	public String logout() {
		if (sessionMap != null) {
			sessionMap.invalidate();
			sessionMap.remove("login");
			sessionMap.clear();
			System.out.println("Logged out");
			return "success";

		}
		return "success";

	}
}
