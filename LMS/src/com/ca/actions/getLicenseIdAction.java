package com.ca.actions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.ca.database.Database;
import com.ca.pojo.AddUserByAdmin;
import com.ca.pojo.License;
import com.opensymphony.xwork2.ActionSupport;

public class getLicenseIdAction extends ActionSupport implements SessionAware {

	private SessionMap<String, Object> licenseIdSession;
	License updateEvents;
	List<License> listForLicenseJsp;

	public getLicenseIdAction() {
		// TODO Auto-generated constructor stub
	}

	public List<License> getListForLicenseJsp() {
		return listForLicenseJsp;
	}

	public void setListForLicenseJsp(List<License> listForLicenseJsp) {
		this.listForLicenseJsp = listForLicenseJsp;
	}

	public License getUpdateEvents() {
		return updateEvents;
	}

	public void setUpdateEvents(License updateEvents) {
		this.updateEvents = updateEvents;
	}

	public SessionMap<String, Object> getLicenseIdSession() {
		return licenseIdSession;
	}

	public void setLicenseIdSession(SessionMap<String, Object> licenseIdSession) {
		this.licenseIdSession = licenseIdSession;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		licenseIdSession.put("licenseAction", updateEvents.getLicenseId());
		System.out.println(licenseIdSession.get("licenseAction"));
		String tempSession = (String) licenseIdSession.get("licenseAction");
		try {
			Database database = new Database();
			Connection con = database.Get_Connection();
			PreparedStatement ps = con
					.prepareStatement("SELECT REMARKS,AMOUNT,PERSON_ALLOTED,date_format(APPLIED_DATE,'%d/%m/%Y') as appliedDate,date_format(RECEIVED_DATE,'%d/%m/%Y') as receivedDate,DD_NUMBER,date_format(DD_DATE,'%d/%m/%Y') as ddDates,DD_AMOUNT,BANK_NAME,COMPANY_NAME FROM LICENSE WHERE LICENSE_ID=?");
			// ps.setString(1,selectuser.getUserName());
			ps.setString(1, (String) licenseIdSession.get("licenseAction"));
			ResultSet rs = ps.executeQuery();
			listForLicenseJsp = new ArrayList<License>();
			while (rs.next()) {
				listForLicenseJsp.add(new License(rs.getString("REMARKS"), rs
						.getString("AMOUNT"), rs.getString("PERSON_ALLOTED"),
						rs.getString("appliedDate"), rs
								.getString("receivedDate"), rs
								.getString("DD_NUMBER"),
						rs.getString("ddDates"), rs.getString("DD_AMOUNT"), rs
								.getString("BANK_NAME"), rs
								.getString("COMPANY_NAME")));
			}
			System.out.println("success");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "success";
	}

	@Override
	public void setSession(Map<String, Object> map) {
		// TODO Auto-generated method stub
		licenseIdSession = (SessionMap) map;
	}

}
