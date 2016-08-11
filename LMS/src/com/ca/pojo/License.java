package com.ca.pojo;

public class License {
	private String licenseId;
	private String licenseName;
	private String eventId;
	private String eventName;
	private String appliedDate;
	private String receivedDate;
	private String personAllotted;
	private String amount;
	private String remarks;
	private String ddSubmitted;
	private String chequeSubmitted;
	private String cashSubmitted;
	private String ddNumber;
	private String ddDate;
	private String ddAmount;
	private String bankName;
	private String ownerCompany;
	private double dvar;
	private int totamount;
	private String companyName;
	private String eventDate;
	private String invoiceNumber;
	
	public License() {
		// TODO Auto-generated constructor stub
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getOwnerCompany() {
		return ownerCompany;
	}

	public void setOwnerCompany(String ownerCompany) {
		this.ownerCompany = ownerCompany;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getDdNumber() {
		return ddNumber;
	}

	public void setDdNumber(String ddNumber) {
		this.ddNumber = ddNumber;
	}

	public String getDdDate() {
		return ddDate;
	}

	public void setDdDate(String ddDate) {
		this.ddDate = ddDate;
	}

	public String getDdAmount() {
		return ddAmount;
	}

	public void setDdAmount(String ddAmount) {
		this.ddAmount = ddAmount;
	}

	public double getDvar() {
		return dvar;
	}

	public void setDvar(double dvar) {
		this.dvar = dvar;
	}

	public int getTotamount() {
		return totamount;
	}

	public void setTotamount(int totamount) {
		this.totamount = totamount;
	}

	public String getEventDate() {
		return eventDate;
	}

	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}

	public License(String licenseId, String licenseName) {
		this.licenseId = licenseId;
		this.licenseName = licenseName;
	}

	public License(int totamount) {
		this.totamount = totamount;
	}

	public License(String eventId, String eventName, String companyName,
			String ownerCompany, String licenseName, String ddNumber,
			String ddDate, String ddAmount, String bankName) {
		// TODO Auto-generated constructor stub
		this.eventId = eventId;
		this.eventName = eventName;
		this.companyName = companyName;
		this.ownerCompany = ownerCompany;
		this.licenseName = licenseName;
		this.ddNumber = ddNumber;
		this.ddDate = ddDate;
		this.ddAmount = ddAmount;
		this.bankName = bankName;
	}

	public License(String licenseId, String licenseName, String eventName,
			String appliedDate, String receivedDate, String personAlloted,
			String amount, String remarks, String ddNumber, String ddDate,
			String ddAmount, String bankName, String ddSubmitted) {

		this.licenseId = licenseId;
		this.licenseName = licenseName;
		this.eventName = eventName;
		this.appliedDate = appliedDate;
		this.receivedDate = receivedDate;
		this.personAllotted = personAlloted;
		this.amount = amount;
		this.remarks = remarks;
		this.ddNumber = ddNumber;
		this.ddDate = ddDate;
		this.ddAmount = ddAmount;
		this.bankName = bankName;
		this.ddSubmitted = ddSubmitted;
	}

	public License(String invoiceNumber, String eventName, String companyName,
			String licenseName) {
		// TODO Auto-generated constructor stub

		this.invoiceNumber = invoiceNumber;
		this.eventName = eventName;
		this.companyName = companyName;
		this.licenseName = licenseName;
	}

	public License(String invoiceNumber, String eventName, String companyName,
			String licenseName, String licenseId) {
		// TODO Auto-generated constructor stub
		this.invoiceNumber = invoiceNumber;
		this.eventName = eventName;
		this.companyName = companyName;
		this.licenseName = licenseName;
		this.licenseId = licenseId;
	}

	public License(String invoiceNumber, String eventName, String companyName,
			String licenseName, String licenseId, String amount) {
		// TODO Auto-generated constructor stub
		this.invoiceNumber = invoiceNumber;
		this.eventName = eventName;
		this.companyName = companyName;
		this.licenseName = licenseName;
		this.licenseId = licenseId;
		this.amount = amount;

	}

	public License(String remarks, String amount, String personAllotted,
			String appliedDate, String receievedDate, String ddNumber, String ddDate,
			String ddAmount, String bankName, String companyName) {
		// TODO Auto-generated constructor stub
		this.remarks=remarks;
		this.amount=amount;
		this.personAllotted=personAllotted;
		this.appliedDate=appliedDate;
		this.receivedDate=receievedDate;
		this.ddNumber=ddNumber;
		this.ddDate=ddDate;
		this.ddAmount=ddAmount;
		this.bankName=bankName;
		this.companyName=companyName;
	}

	public String getLicenseId() {
		return licenseId;
	}

	public void setLicenseId(String licenseId) {
		this.licenseId = licenseId;
	}

	public String getLicenseName() {
		return licenseName;
	}

	public void setLicenseName(String licenseName) {
		this.licenseName = licenseName;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getAppliedDate() {
		return appliedDate;
	}

	public void setAppliedDate(String appliedDate) {
		this.appliedDate = appliedDate;
	}

	public String getReceivedDate() {
		return receivedDate;
	}

	public void setReceivedDate(String receivedDate) {
		this.receivedDate = receivedDate;
	}

	public String getPersonAllotted() {
		return personAllotted;
	}

	public void setPersonAllotted(String personAllotted) {
		this.personAllotted = personAllotted;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getDdSubmitted() {
		return ddSubmitted;
	}

	public void setDdSubmitted(String ddSubmitted) {
		this.ddSubmitted = ddSubmitted;
	}

	public String getChequeSubmitted() {
		return chequeSubmitted;
	}

	public void setChequeSubmitted(String chequeSubmitted) {
		this.chequeSubmitted = chequeSubmitted;
	}

	public String getCashSubmitted() {
		return cashSubmitted;
	}

	public void setCashSubmitted(String cashSubmitted) {
		this.cashSubmitted = cashSubmitted;
	}

}
