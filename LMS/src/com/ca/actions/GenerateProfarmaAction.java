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
import com.opensymphony.xwork2.Preparable;

public class GenerateProfarmaAction extends ActionSupport implements Preparable {
	Event profarmaVar;
	List<License> profarmaList;
	private String invoiceNumber;
	private List<String> toCompanyNameList;

	public GenerateProfarmaAction() {
		// TODO Auto-generated constructor stub
	}

	public List<String> getToCompanyNameList() {
		return toCompanyNameList;
	}

	public void setToCompanyNameList(List<String> toCompanyNameList) {
		this.toCompanyNameList = toCompanyNameList;
	}

	public Event getProfarmaVar() {
		return profarmaVar;
	}

	public void setProfarmaVar(Event profarmaVar) {
		this.profarmaVar = profarmaVar;
	}

	public List<License> getProfarmaList() {
		return profarmaList;
	}

	public void setProfarmaList(List<License> profarmaList) {
		this.profarmaList = profarmaList;
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
					.prepareStatement("SELECT EVENT_NAME,COMPANY_NAME ,LICENSE_NAME,LICENSE_ID ,AMOUNT FROM LICENSE WHERE EVENT_ID=?");
			ps.setString(1, profarmaVar.getEventId());
			ResultSet rs = ps.executeQuery();
			profarmaList = new ArrayList<License>();

			while (rs.next()) {
				invoiceNumber = uniqueID.substring(0, 2)
						+ rs.getString("COMPANY_NAME").substring(0, 2);
				profarmaList.add(new License(invoiceNumber, rs
						.getString("EVENT_NAME"), rs.getString("COMPANY_NAME"),
						rs.getString("LICENSE_NAME"), rs
								.getString("LICENSE_ID"), rs
								.getString("AMOUNT")));
			}
			System.out
					.println("Records Added to List of CAV generate proforma action list..");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		Connection con = null;
		try {
			con = new Database().Get_Connection();

			// load companies
			PreparedStatement ps = con
					.prepareStatement("SELECT DISTINCT company_name FROM event");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				toCompanyNameList.add(rs.getString("company_name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
		}

	}

}
