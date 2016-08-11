package com.ca.actions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ca.database.Database;
import com.ca.pojo.Event;
import com.opensymphony.xwork2.ActionSupport;

public class DataForViewOrEditEventsAction extends ActionSupport {
	List<Event> dataForEditEventsJsp;
	private String searchEventTwo;

	public DataForViewOrEditEventsAction() {
		// TODO Auto-generated constructor stub
	}

	public String getSearchEventTwo() {
		return searchEventTwo;
	}

	public void setSearchEventTwo(String searchEventTwo) {
		this.searchEventTwo = searchEventTwo;
	}

	public List<Event> getDataForEditEventsJsp() {
		return dataForEditEventsJsp;
	}

	public void setDataForEditEventsJsp(List<Event> dataForEditEventsJsp) {
		this.dataForEditEventsJsp = dataForEditEventsJsp;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Inside Exceute");
		try {
			Database database = new Database();
			Connection con = database.Get_Connection();
			PreparedStatement ps;
			dataForEditEventsJsp = new ArrayList<Event>();

			if (getSearchEventTwo().length() > 0) {
				ps = con.prepareStatement("SELECT EVENT_ID,EVENT_NAME,COMPANY_NAME,CONTACT_PERSON,CONTACT_NO,EMAIL_ID,EVENT_VENUE,date_format(FROM_DATE,'%d/%m/%Y') as dateFromDate,date_format(TO_DATE,'%d/%m/%Y') as dateToDate,EVENT_TIME,COMMISSION,date_format(EVENT_DATE,'%d/%m/%Y') as dateEventDate,date_format(PAYMENT_DATE,'%d/%m/%Y') as dateAsPayment,PAYMENT_MODE,CHEQUE_DD_NO,BANK_NAME,PROFIT_EARNED,TOTAL_AMOUNT,RECEIVED_AMOUNT,EVENT_TDS,BALANCE_AMOUNT,PAID_STATUS FROM EVENT WHERE EVENT_NAME LIKE ?");
				ps.setString(1, "%" + getSearchEventTwo() + "%");
				ResultSet rs1 = ps.executeQuery();
				while (rs1.next()) {
					dataForEditEventsJsp.add(new Event(rs1
							.getString("EVENT_ID"),
							rs1.getString("EVENT_NAME"), rs1
									.getString("COMPANY_NAME"), rs1
									.getString("CONTACT_PERSON"), rs1
									.getString("CONTACT_NO"), rs1
									.getString("EMAIL_ID"), rs1
									.getString("EVENT_VENUE"), rs1
									.getString("dateFromDate"), rs1
									.getString("dateToDate"), rs1
									.getString("EVENT_TIME"), rs1
									.getString("COMMISSION"), rs1
									.getString("dateEventDate"), rs1
									.getString("dateAsPayment"), rs1
									.getString("PAYMENT_MODE"), rs1
									.getString("CHEQUE_DD_NO"), rs1
									.getString("BANK_NAME"), rs1
									.getString("PROFIT_EARNED"), rs1
									.getString("TOTAL_AMOUNT"), rs1
									.getString("RECEIVED_AMOUNT"), rs1
									.getString("EVENT_TDS"), rs1
									.getString("BALANCE_AMOUNT"), rs1
									.getString("PAID_STATUS")));
				}

			} else {
				ps = con.prepareStatement("SELECT EVENT_ID,EVENT_NAME,COMPANY_NAME,CONTACT_PERSON,CONTACT_NO,EMAIL_ID,EVENT_VENUE,date_format(FROM_DATE,'%d/%m/%Y') as dateFromDate,date_format(TO_DATE,'%d/%m/%Y') as dateToDate,EVENT_TIME,COMMISSION,date_format(EVENT_DATE,'%d/%m/%Y') as dateEventDate,date_format(PAYMENT_DATE,'%d/%m/%Y') as dateAsPayment,PAYMENT_MODE,CHEQUE_DD_NO,BANK_NAME,PROFIT_EARNED,TOTAL_AMOUNT,RECEIVED_AMOUNT,EVENT_TDS,BALANCE_AMOUNT,PAID_STATUS FROM EVENT");
				ResultSet rs = ps.executeQuery();

				while (rs.next()) {
					dataForEditEventsJsp.add(new Event(
							rs.getString("EVENT_ID"), rs
									.getString("EVENT_NAME"), rs
									.getString("COMPANY_NAME"), rs
									.getString("CONTACT_PERSON"), rs
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
					System.out.println("Inside Retrieve Events action!!!");

				}
			}
			System.out.println("Records Added");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

}
