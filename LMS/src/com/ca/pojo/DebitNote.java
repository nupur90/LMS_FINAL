package com.ca.pojo;

public class DebitNote {
	public DebitNote() {
		// TODO Auto-generated constructor stub
	}

	private String companyName;
	private String invoicesNumber;
	private String eventsDate;
	private String debitDesc;
	private String debitAmount;
	private String debitTotalAmount;
	private String eventName;
	private String licName[];
	private String forCompany;

	public String getForCompany() {
		return forCompany;
	}

	public void setForCompany(String forCompany) {
		this.forCompany = forCompany;
	}

	public String[] getLicName() {
		return licName;
	}

	public void setLicName(String[] licName) {
		this.licName = licName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getDebitTotalAmount() {
		return debitTotalAmount;
	}

	public void setDebitTotalAmount(String debitTotalAmount) {
		this.debitTotalAmount = debitTotalAmount;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getInvoicesNumber() {
		return invoicesNumber;
	}

	public void setInvoicesNumber(String invoicesNumber) {
		this.invoicesNumber = invoicesNumber;
	}

	public String getEventsDate() {
		return eventsDate;
	}

	public void setEventsDate(String eventsDate) {
		this.eventsDate = eventsDate;
	}

	public String getDebitDesc() {
		return debitDesc;
	}

	public void setDebitDesc(String debitDesc) {
		this.debitDesc = debitDesc;
	}

	public String getDebitAmount() {
		return debitAmount;
	}

	public void setDebitAmount(String debitAmount) {
		this.debitAmount = debitAmount;
	}

}
