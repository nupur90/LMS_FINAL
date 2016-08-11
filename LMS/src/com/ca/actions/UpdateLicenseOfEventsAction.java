package com.ca.actions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.ca.database.Database;
import com.ca.pojo.License;
import com.opensymphony.xwork2.ActionSupport;

public class UpdateLicenseOfEventsAction extends ActionSupport implements
		SessionAware {
	License updateEvents;
	private SessionMap<String, Object> licenseIdSession;
	getLicenseIdAction getsdsd;

	public UpdateLicenseOfEventsAction() {
		// TODO Auto-generated constructor stub
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

	public getLicenseIdAction getGetsdsd() {
		return getsdsd;
	}

	public void setGetsdsd(getLicenseIdAction getsdsd) {
		this.getsdsd = getsdsd;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		System.out.println(updateEvents.getAmount());
		System.out.println(updateEvents.getBankName());

		System.out.println(this.getLicenseIdSession().get("licenseAction"));
		try {
			Database database = new Database();
			Connection con = database.Get_Connection();
			PreparedStatement ps = con
					.prepareStatement("UPDATE LICENSE SET APPLIED_DATE=?,RECEIVED_DATE=?,PERSON_ALLOTED=?,AMOUNT=?,REMARKS=?,DD_NUMBER=?,DD_DATE=?,DD_AMOUNT=?,BANK_NAME=?,DD_SUBMITTED=?,OWNER_COMPANY=? WHERE LICENSE_ID=?");
			ps.setString(1, updateEvents.getAppliedDate());
			ps.setString(2, updateEvents.getReceivedDate());
			ps.setString(3, updateEvents.getPersonAllotted());
			ps.setString(4, updateEvents.getAmount());
			ps.setString(5, updateEvents.getRemarks());
			ps.setString(6, updateEvents.getDdNumber());
			ps.setString(7, updateEvents.getDdDate());
			ps.setString(8, updateEvents.getDdAmount());
			ps.setString(9, updateEvents.getBankName());
			ps.setString(10, updateEvents.getDdSubmitted());
			ps.setString(11, updateEvents.getOwnerCompany());
			ps.setString(12,
					(String) this.getLicenseIdSession().get("licenseAction"));
			ps.executeUpdate();
			System.out.println("Update Records Successfully..");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	/*@Override
	public void validate() {
		// TODO Auto-generated method stub
		super.validate();
		if (updateEvents.getRemarks().isEmpty()) {

			addFieldError("updateEvents.remarks", "Please Enter Remarks ..");
		}
		if (updateEvents.getAmount().isEmpty()) {
			addFieldError("updateEvents.amount", "Please Enter Amount.. ");
		} else if (!updateEvents.getAmount().matches("[0-9]+")
				&& updateEvents.getAmount().length() > 2) {
			addFieldError("updateEvents.amount",
					"Enter only numeric data for Amount! ");

		}

		if (updateEvents.getPersonAllotted().isEmpty()) {
			addFieldError("updateEvents.personAllotted",
					"Please Enter Name Of Person Allotted..");
		}

		if (updateEvents.getAppliedDate().isEmpty()) {
			addFieldError("updateEvents.appliedDate",
					"Please Enter Applied Date..");
		}

		if (updateEvents.getReceivedDate().isEmpty()) {
			addFieldError("updateEvents.receivedDate",
					"Please Enter Received Date..");
		}

		if (updateEvents.getOwnerCompany().equals("0")) {
			addFieldError("updateEvents.ownerCompany",
					"Please Select Owner Company Name!!..");
		}
		if (updateEvents.getDdSubmitted().equals("0")) {
			addFieldError("updateEvents.ddSubmitted",
					"Please Select DD Submitted Option!!..");
		}
		if (updateEvents.getDdNumber().isEmpty()) {
			addFieldError("updateEvents.ddNumber", "Please Enter DD Number..");
		}
		if (updateEvents.getDdDate().isEmpty()) {
			addFieldError("updateEvents.ddDate", "Please Select DD Date..");
		}
		if (updateEvents.getDdAmount().isEmpty()) {
			addFieldError("updateEvents.ddAmount", "Please Enter DD Amount..");
		} else if (!updateEvents.getDdAmount().matches("[0-9]+")
				&& updateEvents.getDdAmount().length() > 2) {
			addFieldError("updateEvents.ddAmount",
					"Enter only numeric data for DD Amount! ");

		}
		if (updateEvents.getBankName().isEmpty()) {
			addFieldError("updateEvents.bankName", "Please Enter Bank Name..");
		}

	}*/

	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub

		licenseIdSession = (SessionMap<String, Object>) arg0;
	}

}
