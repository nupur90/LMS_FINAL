package com.ca.pojo;

import java.sql.Time;

public class Event {
	public Event() {
		// TODO Auto-generated constructor stub
	}

	public Event(String eventName, String companyName, String fromDate,
			String toDate) {
		// TODO Auto-generated constructor stub
		this.eventName = eventName;
		this.companyName = companyName;
		this.fromDate = fromDate;
		this.toDate = toDate;
	}

	public Event(String eventId, String contactPerson, String contactNumber,
			String emailId, String eventVenue, String fromDate, String toDate,
			String eventTime) {
		// TODO Auto-generated constructor stub
		this.eventId = eventId;
		this.contactPerson = contactPerson;
		this.contactNumber = contactNumber;
		this.emailId = emailId;
		this.eventVenue = eventVenue;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.eventTime = eventTime;
	}

	public Event(String eventId, String eventName, String companyName,
			String contactPerson, String contactNumber, String emailId,
			String eventVenue, String fromDate, String toDate, String eventTime) {
		this.eventId = eventId;
		this.eventName = eventName;
		this.companyName = companyName;
		this.contactPerson = contactPerson;
		this.contactNumber = contactNumber;
		this.emailId = emailId;
		this.eventVenue = eventVenue;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.eventTime = eventTime;

	}

	public Event(String eventId, String eventName, String companyName,
			String contactPerson, String contactNumber, String emailId,
			String eventVenue, String fromDate, String toDate,
			String eventTime, String eventDate, String paymentDate,
			String paymentMode, String chequeDd, String bankName,
			String profitEarned, String totalAmount, String receivedAmount,
			String eventTds, String balanceAmount, String paidStatus) {
		// TODO Auto-generated constructor stub
		this.eventId = eventId;
		this.eventName = eventName;
		this.companyName = companyName;
		this.contactPerson = contactPerson;
		this.contactNumber = contactNumber;
		this.emailId = emailId;
		this.eventVenue = eventVenue;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.eventTime = eventTime;
		this.eventDate = eventDate;
		this.paymentDate = paymentDate;
		this.paymentMode = paymentMode;
		this.chequeDd = chequeDd;
		this.bankName = bankName;
		this.profitEarned = profitEarned;
		this.totalAmount = totalAmount;
		this.receivedAmount = receivedAmount;
		this.eventTds = eventTds;
		this.balanceAmount = balanceAmount;
		this.paidStatus = paidStatus;
	}

	public Event(String eventId, String invoiceNumber, String eventName,
			String companyName, String contactPerson, String eventDate) {
		// TODO Auto-generated constructor stub
		this.eventId = eventId;
		this.invoiceNumber = invoiceNumber;
		this.eventName = eventName;
		this.companyName = companyName;
		this.contactPerson = contactPerson;
		this.eventDate = eventDate;
	}

	public Event(String eventId, String eventName, String companyName,
			String eventVenue, String eventTime, String totalAmount,
			String receivedAmount, String chequeDd, String paymentDate,
			String balanceAmount, String eventTds) {
		this.eventId = eventId;
		this.eventName = eventName;
		this.companyName = companyName;
		this.eventVenue = eventVenue;
		this.eventTime = eventTime;
		this.totalAmount = totalAmount;
		this.receivedAmount = receivedAmount;
		this.chequeDd = chequeDd;
		this.paymentDate = paymentDate;
		this.balanceAmount = balanceAmount;
		this.eventTds = eventTds;

	}

	public Event(String eventId, String eventName, String companyName,
			String contactPerson, String contactNumber, String emailId,
			String eventVenue, String fromDate, String toDate,
			String eventTime, String commission, String eventDate,
			String paymentDate, String paymentMode, String chequeDd,
			String bankName, String profitEarned, String totalAmount,
			String receivedAmount, String eventTds, String balanceAmount,
			String paidStatus) {
		// TODO Auto-generated constructor stub
		this.eventId = eventId;
		this.eventName = eventName;
		this.companyName = companyName;
		this.contactPerson = contactPerson;
		this.contactNumber = contactNumber;
		this.emailId = emailId;
		this.eventVenue = eventVenue;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.eventTime = eventTime;
		this.commission = commission;
		this.eventDate = eventDate;
		this.paymentDate = paymentDate;
		this.paymentMode = paymentMode;
		this.chequeDd = chequeDd;
		this.bankName = bankName;
		this.profitEarned = profitEarned;
		this.totalAmount = totalAmount;
		this.receivedAmount = receivedAmount;
		this.eventTds = eventTds;
		this.balanceAmount = balanceAmount;
		this.paidStatus = paidStatus;
	}

	public Event(String eventName, String companyName, String contactPerson,
			String contactNumber, String emailId, String eventVenue,
			String fromDate, String toDate, String eventTime,
			String commission, String eventDate, String paymentDate,
			String paymentMode, String chequeDd, String bankName,
			String profitEarned, String totalAmount, String receivedAmount,
			String eventTds, String balanceAmount) {
		// TODO Auto-generated constructor stub
		this.eventName = eventName;
		this.companyName = companyName;
		this.contactPerson = contactPerson;
		this.contactNumber = contactNumber;
		this.emailId = emailId;
		this.eventVenue = eventVenue;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.eventTime = eventTime;
		this.commission = commission;
		this.eventDate = eventDate;
		this.paymentDate = paymentDate;
		this.paymentMode = paymentMode;
		this.chequeDd = chequeDd;
		this.bankName = bankName;
		this.profitEarned = profitEarned;
		this.totalAmount = totalAmount;
		this.receivedAmount = receivedAmount;
		this.eventTds = eventTds;
		this.balanceAmount = balanceAmount;
	}

	public Event(String licenseId, String licenseName) {
		// TODO Auto-generated constructor stub
	}

	public Event(String eventId, String eventName, String eventDate,
			String companyName, String ownerCompany, String eventTds,
			String serviceTaxInvoice, Double totalAmountDouble,
			Double profitDouble, String amount) {
		// TODO Auto-generated constructor stub
		this.eventId = eventId;
		this.eventName = eventName;
		this.eventDate = eventDate;
		this.companyName = companyName;
		this.ownerCompany = ownerCompany;
		this.eventTds = eventTds;
		this.serviceTaxInvoice = serviceTaxInvoice;
		this.totalAmountDouble = totalAmountDouble;
		this.profitDouble = profitDouble;
		this.amount = amount;

	}

	public Event(String eventName, String ownerCompany,
			String serviceTaxInvoice, String debitNoteAmount,
			String invoiceAmount, String billNo, String billDate,
			String bankName, String eventTds, double total, double gross_total) {
		// TODO Auto-generated constructor stub
		this.eventName = eventName;
		this.ownerCompany = ownerCompany;
		this.serviceTaxInvoice = serviceTaxInvoice;
		this.debitNoteAmount = debitNoteAmount;
		this.invoiceAmount = invoiceAmount;
		this.billNo = billNo;
		this.billDate = billDate;
		this.bankName = bankName;
		this.eventTds = eventTds;
		this.total = total;
		this.gross_total = gross_total;

	}

	public Event(String eventId, String eventName, String companyName,
			String fromDate, String toDate) {
		// TODO Auto-generated constructor stub
		this.eventId = eventId;
		this.eventName = eventName;
		this.companyName = companyName;
		this.fromDate = fromDate;
		this.toDate = toDate;
	}

	private String billNo;
	private String billDate;
	private String licenseId;
	private String licenseName;
	private String invoiceNumber;
	private String eventProfarmaDate;
	private String chequeDd;
	private String eventName;
	private String companyName;
	private String contactPerson;
	private String contactNumber;
	private String emailId;
	private String eventVenue;
	private String fromDate;
	private String toDate;
	private String eventTime;
	private String eventCom;
	private String eventId;
	private String eventDate;
	private String paymentDate;
	private String paymentMode;
	private String bankName;
	private String profitEarned;
	private String totalAmount;
	private String receivedAmount;
	private String eventTds;
	private String balanceAmount;
	private String paidStatus;
	private String commission;
	private String ownerCompany;
	private String serviceTaxInvoice;
	private String invoiceAmount;
	private String debitNoteAmount;
	private double dailyExpense;
	private String amount;
	private double total;
	private double gross_total;
	private Double totalAmountDouble;

	private Double profitDouble;

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getGross_total() {
		return gross_total;
	}

	public void setGross_total(double gross_total) {
		this.gross_total = gross_total;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public String getBillDate() {
		return billDate;
	}

	public void setBillDate(String billDate) {
		this.billDate = billDate;
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

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public String getEventProfarmaDate() {
		return eventProfarmaDate;
	}

	public void setEventProfarmaDate(String eventProfarmaDate) {
		this.eventProfarmaDate = eventProfarmaDate;
	}

	public String getCommission() {
		return commission;
	}

	public void setCommission(String commission) {
		this.commission = commission;
	}

	public String getChequeDd() {
		return chequeDd;
	}

	public void setChequeDd(String chequeDd) {
		this.chequeDd = chequeDd;
	}

	public String getEventDate() {
		return eventDate;
	}

	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}

	public String getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getProfitEarned() {
		return profitEarned;
	}

	public void setProfitEarned(String profitEarned) {
		this.profitEarned = profitEarned;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getReceivedAmount() {
		return receivedAmount;
	}

	public void setReceivedAmount(String receivedAmount) {
		this.receivedAmount = receivedAmount;
	}

	public String getEventTds() {
		return eventTds;
	}

	public void setEventTds(String eventTds) {
		this.eventTds = eventTds;
	}

	public String getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(String balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

	public String getPaidStatus() {
		return paidStatus;
	}

	public void setPaidStatus(String paidStatus) {
		this.paidStatus = paidStatus;
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

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getEventVenue() {
		return eventVenue;
	}

	public void setEventVenue(String eventVenue) {
		this.eventVenue = eventVenue;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getEventTime() {
		return eventTime;
	}

	public void setEventTime(String eventTime) {
		this.eventTime = eventTime;
	}

	public String getEventCom() {
		return eventCom;
	}

	public void setEventCom(String eventCom) {
		this.eventCom = eventCom;
	}

	public String getOwnerCompany() {
		return ownerCompany;
	}

	public void setOwnerCompany(String ownerCompany) {
		this.ownerCompany = ownerCompany;
	}

	public String getServiceTaxInvoice() {
		return serviceTaxInvoice;
	}

	public void setServiceTaxInvoice(String serviceTaxInvoice) {
		this.serviceTaxInvoice = serviceTaxInvoice;
	}

	public String getInvoiceAmount() {
		return invoiceAmount;
	}

	public void setInvoiceAmount(String invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}

	public String getDebitNoteAmount() {
		return debitNoteAmount;
	}

	public void setDebitNoteAmount(String debitNoteAmount) {
		this.debitNoteAmount = debitNoteAmount;
	}

	public Double getTotalAmountDouble() {
		return totalAmountDouble;
	}

	public void setTotalAmountDouble(Double totalAmountDouble) {
		this.totalAmountDouble = totalAmountDouble;
	}

	public Double getProfitDouble() {
		return profitDouble;
	}

	public void setProfitDouble(Double profitDouble) {
		this.profitDouble = profitDouble;
	}

	public double getDailyExpense() {
		return dailyExpense;
	}

	public void setDailyExpense(double dailyExpense) {
		this.dailyExpense = dailyExpense;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

}
