package com.ca.actions;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.ca.database.Database;
import com.ca.pojo.Event;
import com.ca.pojo.License;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class DataForFinancialReportAction extends ActionSupport implements
		Preparable, SessionAware {
	private double dailyExpense;
	private List<String> partyFinancial = new ArrayList<String>();

	private List<String> companiesGeneral = new ArrayList<String>();

	private String party = null;
	private String company = null;

	private List<Event> dataForFinancialReports;

	private List<License> otherExdataForFinancialReports;

	List<String> eventIdList = new ArrayList<String>();
	List<String> eventNameList = new ArrayList<String>();
	List<String> eventDateList = new ArrayList<String>();
	List<String> companyNameList = new ArrayList<String>();
	List<String> ownerCompanyNameList = new ArrayList<String>();
	List<String> eventTdsList = new ArrayList<String>();
	List<String> serviceTaxList = new ArrayList<String>();
	List<String> dailyExpenseAmountList = new ArrayList<String>();
	List<String> totalList = new ArrayList<String>();
	List<String> profitList = new ArrayList<String>();

	private SessionMap<String, Object> sessionMapFinancial;

	public DataForFinancialReportAction() {
		// TODO Auto-generated constructor stub
	}

	public List<String> getTotalList() {
		return totalList;
	}

	public void setTotalList(List<String> totalList) {
		this.totalList = totalList;
	}

	public List<String> getProfitList() {
		return profitList;
	}

	public void setProfitList(List<String> profitList) {
		this.profitList = profitList;
	}

	public List<String> getPartyFinancial() {
		return partyFinancial;
	}

	public void setPartyFinancial(List<String> partyFinancial) {
		this.partyFinancial = partyFinancial;
	}

	public List<String> getCompaniesGeneral() {
		return companiesGeneral;
	}

	public void setCompaniesGeneral(List<String> companiesGeneral) {
		this.companiesGeneral = companiesGeneral;
	}

	public String getParty() {
		return party;
	}

	public void setParty(String party) {
		this.party = party;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public List<Event> getDataForFinancialReports() {
		return dataForFinancialReports;
	}

	public void setDataForFinancialReports(List<Event> dataForFinancialReports) {
		this.dataForFinancialReports = dataForFinancialReports;
	}

	public List<String> getEventIdList() {
		return eventIdList;
	}

	public void setEventIdList(List<String> eventIdList) {
		this.eventIdList = eventIdList;
	}

	public List<String> getEventNameList() {
		return eventNameList;
	}

	public void setEventNameList(List<String> eventNameList) {
		this.eventNameList = eventNameList;
	}

	public List<String> getEventDateList() {
		return eventDateList;
	}

	public void setEventDateList(List<String> eventDateList) {
		this.eventDateList = eventDateList;
	}

	public List<String> getCompanyNameList() {
		return companyNameList;
	}

	public void setCompanyNameList(List<String> companyNameList) {
		this.companyNameList = companyNameList;
	}

	public List<String> getOwnerCompanyNameList() {
		return ownerCompanyNameList;
	}

	public void setOwnerCompanyNameList(List<String> ownerCompanyNameList) {
		this.ownerCompanyNameList = ownerCompanyNameList;
	}

	public List<License> getOtherExdataForFinancialReports() {
		return otherExdataForFinancialReports;
	}

	public void setOtherExdataForFinancialReports(
			List<License> otherExdataForFinancialReports) {
		this.otherExdataForFinancialReports = otherExdataForFinancialReports;
	}

	public double getDailyExpense() {
		return dailyExpense;
	}

	public void setDailyExpense(double dailyExpense) {
		this.dailyExpense = dailyExpense;
	}

	public SessionMap<String, Object> getSessionMapFinancial() {
		return sessionMapFinancial;
	}

	public void setSessionMapFinancial(
			SessionMap<String, Object> sessionMapFinancial) {
		this.sessionMapFinancial = sessionMapFinancial;
	}

	public List<String> getEventTdsList() {
		return eventTdsList;
	}

	public void setEventTdsList(List<String> eventTdsList) {
		this.eventTdsList = eventTdsList;
	}

	public List<String> getServiceTaxList() {
		return serviceTaxList;
	}

	public void setServiceTaxList(List<String> serviceTaxList) {
		this.serviceTaxList = serviceTaxList;
	}

	public List<String> getDailyExpenseAmountList() {
		return dailyExpenseAmountList;
	}

	public void setDailyExpenseAmountList(List<String> dailyExpenseAmountList) {
		this.dailyExpenseAmountList = dailyExpenseAmountList;
	}

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		Connection con = null;
		try {
			con = new Database().Get_Connection();

			System.out.println("IN PREPARE");

			// load companies
			PreparedStatement ps = con
					.prepareStatement("SELECT DISTINCT EVENT_NAME FROM license");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				partyFinancial.add(rs.getString("EVENT_NAME"));
			}

			// load events
			ps = con.prepareStatement("SELECT DISTINCT OWNER_COMPANY FROM EVENT");
			rs = ps.executeQuery();
			while (rs.next()) {
				companiesGeneral.add(rs.getString("OWNER_COMPANY"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
		}

	}

	@Override
	public String execute() throws Exception {

		Connection con = null;
		try {
			con = new Database().Get_Connection();

			// load the table. The first time the table is loaded completely
			String sql = "SELECT DISTINCT E.EVENT_ID,E.EVENT_DATE,E.EVENT_NAME,E.OWNER_COMPANY, E.COMPANY_NAME,E.EVENT_TDS,E.SERVICE_TAX_INVOICE,E.DEBIT_NOTE_AMOUNT,E.INVOICE_AMOUNT, date_format(E.EVENT_DATE,'%d/%m/%Y') as dateAsPayment , SUM(L.Amount) as dailyExpenseBaa FROM event as E JOIN License as L ON E.EVENT_ID = L.EVENT_ID ";
			String where = "";
			String groupBy = "GROUP BY E.EVENT_ID ";

			String sql2 = "select amount from license";
			// if instead this action has been called from the JSP page,
			// the result is filtered on event and company:
			if (party != null && company != null) {
				where = " WHERE E.event_name = ? AND E.owner_company = ?";
			}

			// load companies
			PreparedStatement ps = con.prepareStatement(sql + where + groupBy);
			if (where.length() > 0) {
				ps.setString(1, party);
				ps.setString(2, company);

				System.out.println("Inside IF!!");
			}
			dataForFinancialReports = new ArrayList<Event>();

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				String serviceTax = rs.getString("E.SERVICE_TAX_INVOICE");

				String debitNote = rs.getString("E.DEBIT_NOTE_AMOUNT");

				String invoiceAmount = rs.getString("E.INVOICE_AMOUNT");

				String eventTds = rs.getString("E.EVENT_TDS");

				System.out.println(serviceTax);
				double serviceTaxDouble = Double.parseDouble(serviceTax);

				double eventTdsDouble = Double.parseDouble(eventTds);

				double debitNoteDouble = Double.parseDouble(debitNote);

				double invoiceAmountDouble = Double.parseDouble(invoiceAmount);

				double totalAmountDouble = invoiceAmountDouble
						+ debitNoteDouble;

				double profitDouble = (totalAmountDouble - serviceTaxDouble)
						- eventTdsDouble;

				dataForFinancialReports.add(new Event(rs.getString("EVENT_ID"),
						rs.getString("EVENT_NAME"), rs
								.getString("COMPANY_NAME"), rs
								.getString("dateAsPayment"), rs
								.getString("OWNER_COMPANY"), rs
								.getString("EVENT_TDS"), rs
								.getString("SERVICE_TAX_INVOICE"),
						totalAmountDouble, profitDouble, rs
								.getString("dailyExpenseBaa")));

				eventIdList.add(rs.getString("EVENT_ID"));
				eventNameList.add(rs.getString("EVENT_NAME"));
				eventDateList.add(rs.getString("dateAsPayment"));
				companyNameList.add(rs.getString("COMPANY_NAME"));
				ownerCompanyNameList.add(rs.getString("OWNER_COMPANY"));
				eventTdsList.add(rs.getString("EVENT_TDS"));
				serviceTaxList.add(rs.getString("SERVICE_TAX_INVOICE"));
				dailyExpenseAmountList.add(rs.getString("dailyExpenseBaa"));
				totalList.add(Double.toString(totalAmountDouble));
				profitList.add(Double.toString(profitDouble));

				System.out.println("After Resultset!!");
			}
			sessionMapFinancial
					.put("dataforfinancial", dataForFinancialReports);
			sessionMapFinancial.put("eventIdPdf", eventIdList);
			sessionMapFinancial.put("eventNamePdf", eventNameList);
			sessionMapFinancial.put("companyNamePdf", companyNameList);
			sessionMapFinancial.put("eventDatePdf", eventDateList);
			sessionMapFinancial.put("ownerCompanyPdf", ownerCompanyNameList);
			sessionMapFinancial.put("eventTdsPdf", eventTdsList);
			sessionMapFinancial.put("serviceTaxPdf", serviceTaxList);
			sessionMapFinancial.put("dailyExpensePdf", dailyExpenseAmountList);
			sessionMapFinancial.put("totalAmountPdf", totalList);
			sessionMapFinancial.put("profitPdf", profitList);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
		}

		return SUCCESS;

	}

	public void generatePdfFinancial() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();

		// System.out.println(sessionMapGeneral.get("eventIdPdf"));
		Document document = new Document(PageSize.A4, 0f, 0f, 0f, 0f);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		float[] columnWidths = { 4, 5, 5, 5, 5, 5, 5, 5, 5, 5 };

		PdfWriter writer = PdfWriter.getInstance(document, baos);
		PdfPTable table = new PdfPTable(10);// 11 indicates no . of columns.
		table.setSpacingBefore(25);
		table.setWidthPercentage(100);
		table.setSpacingAfter(25);
		table.setWidths(columnWidths);

		Font f = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD);

		BaseColor myColor = BaseColor.CYAN;

		PdfPCell c1 = new PdfPCell(new Phrase("Event ID ", f));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(myColor);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Event Name ", f));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(myColor);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Party Name", f));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(myColor);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Event Date", f));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(myColor);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Company Name", f));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(myColor);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Other Expenses", f));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(myColor);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Service Tax", f));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(myColor);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("TDS", f));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(myColor);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Total", f));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(myColor);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Profit Gained", f));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(myColor);
		table.addCell(c1);

		table.setHeaderRows(1);

		dataForFinancialReports = (List<Event>) sessionMapFinancial
				.get("dataforfinancial");

		for (Event dataValue : dataForFinancialReports) {

			String eventId = dataValue.getEventId();

			String eventName = dataValue.getEventName();

			String companyName = dataValue.getCompanyName();

			String eventDate = dataValue.getEventDate();

			String ownerCompany = dataValue.getOwnerCompany();

			String amount = dataValue.getAmount();

			String serviceTax = dataValue.getServiceTaxInvoice();

			String eventTds = dataValue.getEventTds();

			String totalAmount = Double.toString(dataValue
					.getTotalAmountDouble());

			String profitDouble = Double.toString(dataValue.getProfitDouble());

			table.addCell(eventId);
			table.addCell(eventName);
			table.addCell(companyName);
			table.addCell(eventDate);
			table.addCell(ownerCompany);
			table.addCell(amount);
			table.addCell(serviceTax);
			table.addCell(eventTds);
			table.addCell(totalAmount);
			table.addCell(profitDouble);

		}

		table.completeRow();

		writer.close();
		document.open();

		document.add(table);

		document.close();
		ServletOutputStream outputStream = response.getOutputStream();
		baos.writeTo(outputStream);
		response.setHeader("Content-Disposition",
				"attachment; filename=\"Financial_Report.pdf\"");
		response.setContentType("application/pdf");
		outputStream.flush();
		outputStream.close();
	}

	public String generateFinancialXls() throws Exception {
		try {

			System.out.println("insoide generate xls");

			String titles = "Event ID,Event Name,Party Name,Event Date,Company Name,Other Expenses,Service Tax,TDS,Total,Profit Gained";
			String[] arrTiltes = titles.split(",");

			//FileOutputStream fileOut = new FileOutputStream(
			//		"D:/FinancialXLS.xls");
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet worksheet = workbook.createSheet("POI Worksheet");

			int row = 0;
			HSSFRow rowTitle = worksheet.createRow(row);

			// set titles

			for (int i = 0; i < arrTiltes.length; i++) {
				HSSFCell cellTitle = rowTitle.createCell(i);
				cellTitle.setCellValue(arrTiltes[i]);

			}

			// setting values
			row++;
			dataForFinancialReports = (List<Event>) sessionMapFinancial
					.get("dataforfinancial");

			for (Event dataValue : dataForFinancialReports) {

				System.out.println("inside for each loop");
				HSSFRow rowValue = worksheet.createRow(row);

				HSSFCell cell0 = rowValue.createCell(0);
				cell0.setCellValue(dataValue.getEventId());

				HSSFCell cell1 = rowValue.createCell(1);
				cell1.setCellValue(dataValue.getEventName());

				HSSFCell cell2 = rowValue.createCell(2);
				cell2.setCellValue(dataValue.getCompanyName());

				HSSFCell cell3 = rowValue.createCell(3);
				cell3.setCellValue(dataValue.getEventDate());

				HSSFCell cell4 = rowValue.createCell(4);
				cell4.setCellValue(dataValue.getOwnerCompany());

				HSSFCell cell5 = rowValue.createCell(5);
				cell5.setCellValue(dataValue.getAmount());

				HSSFCell cell6 = rowValue.createCell(6);
				cell6.setCellValue(dataValue.getServiceTaxInvoice());

				HSSFCell cell7 = rowValue.createCell(7);
				cell7.setCellValue(dataValue.getEventTds());

				HSSFCell cell8 = rowValue.createCell(8);
				cell8.setCellValue(dataValue.getTotalAmountDouble());

				HSSFCell cell9 = rowValue.createCell(9);
				cell9.setCellValue(dataValue.getProfitDouble());

				row++;

			}

		//	workbook.write(fileOut);
			System.out.println("after for each loop of xls");
		//	fileOut.flush();
		//	fileOut.close();
			String fileName = "Financial_Report_XLS.xls";
			HttpServletResponse response = ServletActionContext.getResponse();

			response.setContentType("application/vnd.ms-excel"); // Set up mime
																	// type
			response.addHeader("Content-Disposition", "attachment; filename="
					+ fileName);
			ServletOutputStream out = response.getOutputStream();
			workbook.write(out);
			out.flush();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		sessionMapFinancial = (SessionMap) arg0;
	}

}
