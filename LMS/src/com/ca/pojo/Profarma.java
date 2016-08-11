package com.ca.pojo;

public class Profarma {
	private String invoiceNumber;
	private String eventsDate;
	private String toCompanyName;
	private String netAmount;
	private String serviceTax;
	private String sbcTax;
	private String totalAmount;
	private String panNumber;
	private String serviceTaxNumber;
	private String eventName;
	private String licProName[];
	private String kkcTax;

	public Profarma() {
		// TODO Auto-generated constructor stub
	}

	public String getKkcTax() {
		return kkcTax;
	}

	public void setKkcTax(String kkcTax) {
		this.kkcTax = kkcTax;
	}

	public String[] getLicProName() {
		return licProName;
	}

	public void setLicProName(String[] licProName) {
		this.licProName = licProName;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
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

	public String getEventsDate() {
		return eventsDate;
	}

	public void setEventsDate(String eventsDate) {
		this.eventsDate = eventsDate;
	}

	public String getToCompanyName() {
		return toCompanyName;
	}

	public void setToCompanyName(String toCompanyName) {
		this.toCompanyName = toCompanyName;
	}

	public String getNetAmount() {
		return netAmount;
	}

	public void setNetAmount(String netAmount) {
		this.netAmount = netAmount;
	}

	public String getServiceTax() {
		return serviceTax;
	}

	public void setServiceTax(String serviceTax) {
		this.serviceTax = serviceTax;
	}

	public String getSbcTax() {
		return sbcTax;
	}

	public void setSbcTax(String sbcTax) {
		this.sbcTax = sbcTax;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

}
