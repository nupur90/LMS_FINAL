package com.ca.actions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.ca.database.Database;
import com.ca.pojo.Event;
import com.ca.pojo.License;
import com.opensymphony.xwork2.ActionSupport;

public class GenerateInvoiceAction extends ActionSupport {

	Event invoiceActionVar;
	List<License> invoiceActionList;
	private String invoiceNumber;

	public GenerateInvoiceAction() {
		// TODO Auto-generated constructor stub
	}

	public Event getInvoiceActionVar() {
		return invoiceActionVar;
	}

	public void setInvoiceActionVar(Event invoiceActionVar) {
		this.invoiceActionVar = invoiceActionVar;
	}

	public List<License> getInvoiceActionList() {
		return invoiceActionList;
	}

	public void setInvoiceActionList(List<License> invoiceActionList) {
		this.invoiceActionList = invoiceActionList;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		try {
			String uniqueID = UUID.randomUUID().toString();
			Database database = new Database();
			Connection con = database.Get_Connection();
			PreparedStatement ps = con
					.prepareStatement("SELECT EVENT_NAME,COMPANY_NAME ,LICENSE_NAME FROM LICENSE WHERE EVENT_ID=?");
			ps.setString(1, invoiceActionVar.getEventId());
			ResultSet rs = ps.executeQuery();
			invoiceActionList = new ArrayList<License>();

			while (rs.next()) {
				invoiceNumber = "Invoice" + uniqueID.substring(0, 2)
						+ rs.getString("COMPANY_NAME").substring(0, 2);
				invoiceActionList.add(new License(invoiceNumber, rs
						.getString("EVENT_NAME"), rs
						.getString("COMPANY_NAME"),rs.getString("LICENSE_NAME")));
			}
			System.out.println("Records Added to List..");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "success";
	}

}
