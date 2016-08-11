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

public class GenerateDebitAction extends ActionSupport {
	Event debitActionVar;
	List<License> debitList;
	private String invoiceNumber;

	public GenerateDebitAction() {
		// TODO Auto-generated constructor stub
	}

	public Event getDebitActionVar() {
		return debitActionVar;
	}

	public void setDebitActionVar(Event debitActionVar) {
		this.debitActionVar = debitActionVar;
	}

	public List<License> getDebitList() {
		return debitList;
	}

	public void setDebitList(List<License> debitList) {
		this.debitList = debitList;
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
					.prepareStatement("SELECT EVENT_NAME,COMPANY_NAME ,LICENSE_NAME,LICENSE_ID FROM LICENSE WHERE EVENT_ID=?");
			ps.setString(1, debitActionVar.getEventId());
			ResultSet rs = ps.executeQuery();
			debitList = new ArrayList<License>();

			while (rs.next()) {
				invoiceNumber = "Debit" + uniqueID.substring(0, 2)
						+ rs.getString("COMPANY_NAME").substring(0, 2);
				debitList.add(new License(invoiceNumber, rs
						.getString("EVENT_NAME"), rs.getString("COMPANY_NAME"),
						rs.getString("LICENSE_NAME"),rs.getString("LICENSE_ID")));
			}
			System.out.println("Records Added to List..");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
}
