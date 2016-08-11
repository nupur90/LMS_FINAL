package com.ca.actions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.ParseConversionEvent;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.ca.database.Database;
import com.ca.pojo.Event;
import com.opensymphony.xwork2.ActionSupport;

public class UpdateBillDetailsAction extends ActionSupport implements
		SessionAware {
	Event updateBillsDetails;
	Event viewBills;
	String paidUnpaidStatus = "";
	String paidSessionStatus="";
	private SessionMap<String, Object> sessionMaps;

	public UpdateBillDetailsAction() {
		// TODO Auto-generated constructor stub
	}

	public Event getUpdateBillsDetails() {
		return updateBillsDetails;
	}

	public void setUpdateBillsDetails(Event updateBillsDetails) {
		this.updateBillsDetails = updateBillsDetails;
	}

	public Event getViewBills() {
		return viewBills;
	}

	public void setViewBills(Event viewBills) {
		this.viewBills = viewBills;
	}

	public SessionMap<String, Object> getSessionMaps() {
		return sessionMaps;
	}

	public void setSessionMaps(SessionMap<String, Object> sessionMaps) {
		this.sessionMaps = sessionMaps;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		int temp = Integer.parseInt(updateBillsDetails.getBalanceAmount());
		if (temp <= 0) {
			paidUnpaidStatus = "PAID";
			//sessionMaps.put("paidStatus", paidUnpaidStatus);
		} else {
			paidUnpaidStatus = "UNPAID";
			//sessionMaps.put("paidStatus", paidUnpaidStatus);
		}
		try {
			Database database = new Database();
			Connection con = database.Get_Connection();
			PreparedStatement ps = con
					.prepareStatement("UPDATE EVENT SET EVENT_NAME=?,COMPANY_NAME=?,CONTACT_PERSON=?,CONTACT_NO=?,EMAIL_ID=?,EVENT_VENUE=?,FROM_DATE=?,TO_DATE=?,EVENT_TIME=?,EVENT_DATE=?,PAYMENT_DATE=?,PAYMENT_MODE=?,CHEQUE_DD_NO=?,BANK_NAME=?,PROFIT_EARNED=?,TOTAL_AMOUNT=?,RECEIVED_AMOUNT=?,EVENT_TDS=?,BALANCE_AMOUNT=?,PAID_STATUS=? WHERE EVENT_ID=?");
			ps.setString(1, updateBillsDetails.getEventName());
			ps.setString(2, updateBillsDetails.getCompanyName());
			ps.setString(3, updateBillsDetails.getContactPerson());
			ps.setString(4, updateBillsDetails.getContactNumber());
			ps.setString(5, updateBillsDetails.getEmailId());
			ps.setString(6, updateBillsDetails.getEventVenue());
			ps.setString(7, updateBillsDetails.getFromDate());
			ps.setString(8, updateBillsDetails.getToDate());
			ps.setString(9, updateBillsDetails.getEventTime());
			ps.setString(10, updateBillsDetails.getEventDate());
			ps.setString(11, updateBillsDetails.getPaymentDate());
			ps.setString(12, updateBillsDetails.getPaymentMode());
			ps.setString(13, updateBillsDetails.getChequeDd());
			ps.setString(14, updateBillsDetails.getBankName());
			ps.setString(15, updateBillsDetails.getProfitEarned());
			ps.setString(16, updateBillsDetails.getTotalAmount());
			ps.setString(17, updateBillsDetails.getReceivedAmount());
			ps.setString(18, updateBillsDetails.getEventTds());
			ps.setString(19, updateBillsDetails.getBalanceAmount());
			ps.setString(20, paidUnpaidStatus);
			ps.setString(21, updateBillsDetails.getEventId());
			ps.executeUpdate();
			System.out.println("Bill Details Updated Successfully !!");
			//sessionMaps.put("paidStatus", paidSessionStatus);
			System.out.println("Records Fetched..");
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	/*@Override
	public void validate() {
		// TODO Auto-generated method stub
		super.validate();
		System.out.println("Inside Validate Method..");
		if (updateBillsDetails.getEventName().isEmpty()) {
			addFieldError("updateBillsDetails.eventName",
					"Please Enter Event Name..");
		}
		if (updateBillsDetails.getCompanyName().isEmpty()) {
			addFieldError("updateBillsDetails.companyName",
					"Please Enter Company Name..");
		}
		if (updateBillsDetails.getContactPerson().isEmpty()) {
			addFieldError("updateBillsDetails",
					"Please Enter Contact Person Name..");
		}
		if (updateBillsDetails.getContactNumber().isEmpty()
				|| updateBillsDetails.getContactNumber().length() > 10) {
			addFieldError("updateBillsDetails",
					"Please Enter Valid Contact Number..");
		} else {
			String expression = "^\\+?[0-9\\-]+\\*?$";
			CharSequence inputStr = updateBillsDetails.getContactNumber();
			Pattern pattern = Pattern.compile(expression,
					Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(inputStr);
			if (!matcher.matches())
				addFieldError("updateBillsDetails", "Invalid Contact Number..");

		}

		if (updateBillsDetails.getEmailId().isEmpty()) {
			addFieldError("updateBillsDetails", "Please Enter Email ID..");
		} else {
			String expression = "^[\\w\\-]([\\.\\w])+[\\w]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
			CharSequence inputStr = updateBillsDetails.getEmailId();
			Pattern pattern = Pattern.compile(expression,
					Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(inputStr);
			if (!matcher.matches())
				addFieldError("updateBillsDetails", "Invalid Email Address..");
		}

		if (updateBillsDetails.getEventVenue().isEmpty()) {
			addFieldError("updateBillsDetails.eventVenue",
					"Please Enter Event Venue..");
		}
		if (updateBillsDetails.getFromDate().isEmpty()) {
			addFieldError("updateBillsDetails.fromDate",
					"Please Enter From Date in yyyy-mm-dd Format..");
		}
		if (updateBillsDetails.getToDate().isEmpty()) {
			addFieldError("updateBillsDetails.toDate",
					"Please Enter To Date in yyyy-mm-dd Format..");
		}
		if (updateBillsDetails.getEventTime().isEmpty()) {
			addFieldError("updateBillsDetails.eventTime",
					"Please Enter Event Time..");
		}
		if (updateBillsDetails.getEventDate().isEmpty()) {
			addFieldError("updateBillsDetails.eventDate",
					"Please Enter Event Date in yyyy-mm-dd Format..");
		}
		if (updateBillsDetails.getEventTds().isEmpty()) {
			addFieldError("updateBillsDetails.eventTds",
					"Please Enter Event TDS..");
		}
		if (updateBillsDetails.getPaymentDate().isEmpty()) {
			addFieldError("updateBillsDetails.paymentDate",
					"Please Enter Payment Date in yyyy-mm-dd Format..");
		}
		if (updateBillsDetails.getBalanceAmount().isEmpty()) {
			addFieldError("updateBillsDetails.balanceAmount",
					"Please Enter Balance Amount..");
		} else {
			String expression = "^\\+?[0-9\\-]+\\*?$";
			CharSequence inputStr = updateBillsDetails.getBalanceAmount();
			Pattern pattern = Pattern.compile(expression,
					Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(inputStr);
			if (!matcher.matches())
				addFieldError("updateBillsDetails.balanceAmount",
						"Please Enter Balance Amount In Number Only..");

		}

		if (updateBillsDetails.getReceivedAmount().isEmpty()) {
			addFieldError("updateBillsDetails",
					"Please Enter Received Amount..");
		} else {
			String expression = "^\\+?[0-9\\-]+\\*?$";
			CharSequence inputStr = updateBillsDetails.getReceivedAmount();
			Pattern pattern = Pattern.compile(expression,
					Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(inputStr);
			if (!matcher.matches())
				addFieldError("updateBillsDetails.receivedAmount",
						"Please Enter Received Amount In Numbers Only..");

		}

		if (updateBillsDetails.getTotalAmount().isEmpty()) {
			addFieldError("updateBillsDetails.totalAmount",
					"Please Enter Total Amount..");
		} else {
			String expression = "^\\+?[0-9\\-]+\\*?$";
			CharSequence inputStr = updateBillsDetails.getTotalAmount();
			Pattern pattern = Pattern.compile(expression,
					Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(inputStr);
			if (!matcher.matches())
				addFieldError("updateBillsDetails.totalAmount",
						"Please Enter Amount In NUMBERS Only ..");

		}

		if (updateBillsDetails.getProfitEarned().isEmpty()) {
			addFieldError("updateBillsDetails.profitEarned",
					"Please Enter Profit Earned..");
		} else {
			String expression = "^\\+?[0-9\\-]+\\*?$";
			CharSequence inputStr = updateBillsDetails.getProfitEarned();
			Pattern pattern = Pattern.compile(expression,
					Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(inputStr);
			if (!matcher.matches())
				addFieldError("updateBillsDetails.profitEarned",
						"Please Enter NUMBER Only..");

		}

	}
*/
	@Override
	public void setSession(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sessionMaps = (SessionMap) map;

	}

}
