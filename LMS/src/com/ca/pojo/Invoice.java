package com.ca.pojo;

public class Invoice {
	public Invoice() {
		// TODO Auto-generated constructor stub
	}

	private String companyName;
	private String invoiceTotalAmount;
	private String sbcTax;
	private String serviceTax;
	private String netAmount;
	private String panNumber;
	private String serviceTaxNumber;
	private String invoiceNumber;
	private String totalAmount;
	private String invoiceEventsDate;
	private String eventName;
	private String eventId;
	private String licName[];
	private String kkcTax;

	public String getKkcTax() {
		return kkcTax;
	}

	public void setKkcTax(String kkcTax) {
		this.kkcTax = kkcTax;
	}

	public String[] getLicName() {
		return licName;
	}

	public void setLicName(String[] licName) {
		this.licName = licName;
	}

	public String getInvoiceEventsDate() {
		return invoiceEventsDate;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public void setInvoiceEventsDate(String invoiceEventsDate) {
		this.invoiceEventsDate = invoiceEventsDate;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getInvoiceTotalAmount() {
		return invoiceTotalAmount;
	}

	public void setInvoiceTotalAmount(String invoiceTotalAmount) {
		this.invoiceTotalAmount = invoiceTotalAmount;
	}

	public String getSbcTax() {
		return sbcTax;
	}

	public void setSbcTax(String sbcTax) {
		this.sbcTax = sbcTax;
	}

	public String getServiceTax() {
		return serviceTax;
	}

	public void setServiceTax(String serviceTax) {
		this.serviceTax = serviceTax;
	}

	public String getNetAmount() {
		return netAmount;
	}

	public void setNetAmount(String netAmount) {
		this.netAmount = netAmount;
	}

	public String getPanNumber() {
		return panNumber;
	}

	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}

	public String getServiceTaxNumber() {
		return serviceTaxNumber;
	}

	public void setServiceTaxNumber(String serviceTaxNumber) {
		this.serviceTaxNumber = serviceTaxNumber;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

}
