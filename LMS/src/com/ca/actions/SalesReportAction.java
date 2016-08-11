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

public class SalesReportAction extends ActionSupport implements Preparable,
		SessionAware {
	double total;
	double gross_total;
	private SessionMap<String, Object> sessionMapSales;

	private List<Event> dataForSalesReport = new ArrayList<Event>();

	private List<String> eventsSales = new ArrayList<String>();
	private List<String> companySalesList = new ArrayList<String>();

	private String eventSales = null;

	private String companySales = null;
	List<String> billNoList = new ArrayList<String>();
	List<String> billDateList = new ArrayList<String>();
	List<String> eventNameList = new ArrayList<String>();
	List<String> ownerCompanyList = new ArrayList<String>();
	List<String> debitNoteAmountList = new ArrayList<String>();
	List<String> invoiceAmountList = new ArrayList<String>();
	List<String> serviceTaxInvoiceList = new ArrayList<String>();

	public SalesReportAction() {
		// TODO Auto-generated constructor stub
	}

	public List<String> getBillNoList() {
		return billNoList;
	}

	public void setBillNoList(List<String> billNoList) {
		this.billNoList = billNoList;
	}

	public List<String> getBillDateList() {
		return billDateList;
	}

	public void setBillDateList(List<String> billDateList) {
		this.billDateList = billDateList;
	}

	public List<String> getEventNameList() {
		return eventNameList;
	}

	public void setEventNameList(List<String> eventNameList) {
		this.eventNameList = eventNameList;
	}

	public List<String> getOwnerCompanyList() {
		return ownerCompanyList;
	}

	public void setOwnerCompanyList(List<String> ownerCompanyList) {
		this.ownerCompanyList = ownerCompanyList;
	}

	public List<String> getDebitNoteAmountList() {
		return debitNoteAmountList;
	}

	public void setDebitNoteAmountList(List<String> debitNoteAmountList) {
		this.debitNoteAmountList = debitNoteAmountList;
	}

	public List<String> getInvoiceAmountList() {
		return invoiceAmountList;
	}

	public void setInvoiceAmountList(List<String> invoiceAmountList) {
		this.invoiceAmountList = invoiceAmountList;
	}

	public List<String> getServiceTaxInvoiceList() {
		return serviceTaxInvoiceList;
	}

	public void setServiceTaxInvoiceList(List<String> serviceTaxInvoiceList) {
		this.serviceTaxInvoiceList = serviceTaxInvoiceList;
	}

	public SessionMap<String, Object> getSessionMapSales() {
		return sessionMapSales;
	}

	public void setSessionMapSales(SessionMap<String, Object> sessionMapSales) {
		this.sessionMapSales = sessionMapSales;
	}

	public List<Event> getDataForSalesReport() {
		return dataForSalesReport;
	}

	public void setDataForSalesReport(List<Event> dataForSalesReport) {
		this.dataForSalesReport = dataForSalesReport;
	}

	public List<String> getCompanySalesList() {
		return companySalesList;
	}

	public void setCompanySalesList(List<String> companySalesList) {
		this.companySalesList = companySalesList;
	}

	public List<String> getEventsSales() {
		return eventsSales;
	}

	public void setEventsSales(List<String> eventsSales) {
		this.eventsSales = eventsSales;
	}

	public String getEventSales() {
		return eventSales;
	}

	public void setEventSales(String eventSales) {
		this.eventSales = eventSales;
	}

	public String getCompanySales() {
		return companySales;
	}

	public void setCompanySales(String companySales) {
		this.companySales = companySales;
	}

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		Connection con = null;
		try {
			con = new Database().Get_Connection();

			// load companies

			// load events
			PreparedStatement ps = con
					.prepareStatement("SELECT DISTINCT company_name FROM event");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				eventsSales.add(rs.getString("company_name"));
			}

			PreparedStatement ps1 = con
					.prepareStatement("SELECT DISTINCT OWNER_COMPANY FROM EVENT");
			ResultSet rs1 = ps1.executeQuery();
			while (rs1.next()) {
				companySalesList.add(rs1.getString("OWNER_COMPANY"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
		}

	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		Connection con = null;
		try {
			con = new Database().Get_Connection();

			// load the table. The first time the table is loaded completely
			String sql = "SELECT EVENT_NAME, OWNER_COMPANY,SERVICE_TAX_INVOICE,DEBIT_NOTE_AMOUNT,INVOICE_AMOUNT,BILL_NO,date_format(BILL_DATE,'%d/%m/%Y') as billDate,BANK_NAME,EVENT_TDS FROM EVENT";
			String where = "";

			// if instead this action has been called from the JSP page,
			// the result is filtered on event and company:
			if (eventSales != null && companySales != null) {
				where = " WHERE company_name = ? AND owner_company = ?";
			}

			// load companies
			PreparedStatement ps = con.prepareStatement(sql + where);
			if (where.length() > 0) {
				ps.setString(1, eventSales);
				ps.setString(2, companySales);
			}

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				total = Double.parseDouble(rs.getString("DEBIT_NOTE_AMOUNT"))
						+ Double.parseDouble(rs.getString("INVOICE_AMOUNT"));
				gross_total = Double.parseDouble(rs
						.getString("DEBIT_NOTE_AMOUNT"))
						+ Double.parseDouble(rs.getString("INVOICE_AMOUNT"))
						+ Double.parseDouble(rs
								.getString("SERVICE_TAX_INVOICE"));
				dataForSalesReport.add(new Event(rs.getString("EVENT_NAME"), rs
						.getString("OWNER_COMPANY"), rs
						.getString("SERVICE_TAX_INVOICE"), rs
						.getString("DEBIT_NOTE_AMOUNT"), rs
						.getString("INVOICE_AMOUNT"), rs.getString("BILL_NO"),
						rs.getString("billDate"), rs.getString("BANK_NAME"), rs
								.getString("EVENT_TDS"),total,gross_total));
				billNoList.add(rs.getString("BILL_NO"));
				billDateList.add(rs.getString("billDate"));
				eventNameList.add(rs.getString("EVENT_NAME"));
				ownerCompanyList.add(rs.getString("OWNER_COMPANY"));
				debitNoteAmountList.add(rs.getString("DEBIT_NOTE_AMOUNT"));
				invoiceAmountList.add(rs.getString("INVOICE_AMOUNT"));
				serviceTaxInvoiceList.add(rs.getString("SERVICE_TAX_INVOICE"));
				System.out.println("Data added to list");

			}
			sessionMapSales.put("saledata", dataForSalesReport);
			sessionMapSales.put("billNoPdf", billNoList);
			sessionMapSales.put("eventNamePdf", eventNameList);
			sessionMapSales.put("billDatePdf", billDateList);
			sessionMapSales.put("ownerCompanyPdf", ownerCompanyList);
			sessionMapSales.put("debitNoteAmountPdf", debitNoteAmountList);
			sessionMapSales.put("invoiceAmountPdf", invoiceAmountList);
			sessionMapSales.put("serviceTaxInvoicePdf", serviceTaxInvoiceList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	public String generatePdfSales() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		Document document = new Document(PageSize.A4, 0f, 0f, 0f, 0f);
		float[] columnWidths = { 4, 5, 5, 5, 5, 5, 5 };

		PdfWriter writer = PdfWriter.getInstance(document, baos);
		PdfPTable table = new PdfPTable(7);
		table.setSpacingBefore(25);
		table.setWidthPercentage(100);
		table.setSpacingAfter(25);
		table.setWidths(columnWidths);

		Font f = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD);

		BaseColor myColor = BaseColor.CYAN;

		PdfPCell c1 = new PdfPCell(new Phrase("Bill No ", f));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(myColor);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Bill Date ", f));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(myColor);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Party Name", f));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(myColor);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Company Name", f));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(myColor);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Debit Note", f));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(myColor);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Invoice", f));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(myColor);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Service Tax", f));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(myColor);
		table.addCell(c1);

		table.setHeaderRows(1);

		dataForSalesReport = (List<Event>) sessionMapSales.get("saledata");

		for (Event dataValue : dataForSalesReport) {

			String billNo = dataValue.getBillNo();

			String billDate = dataValue.getBillDate();

			String companyName = dataValue.getEventName();

			String ownerCompany = dataValue.getOwnerCompany();

			String debitAmount = dataValue.getDebitNoteAmount();

			String invoice = dataValue.getInvoiceAmount();

			String serviceTax = dataValue.getServiceTaxInvoice();

			table.addCell(billNo);
			table.addCell(billDate);
			table.addCell(companyName);
			table.addCell(ownerCompany);
			table.addCell(debitAmount);
			table.addCell(invoice);
			table.addCell(serviceTax);

		}

		table.completeRow();

		writer.close();
		document.open();

		document.add(table);

		document.close();
		ServletOutputStream outputStream = response.getOutputStream();
		baos.writeTo(outputStream);
		response.setHeader("Content-Disposition",
				"attachment; filename=\"GeneralReport.pdf\"");
		response.setContentType("application/pdf");
		outputStream.flush();
		outputStream.close();

		return "success";
	}

	public String generateSalesXls() throws Exception {
		try {

			System.out.println("insoide generate xls");

			String titles = "Bill No , Bill Date , Party Name , Company Name , Debit Note , Invoice , Service tax";
			String[] arrTiltes = titles.split(",");

			// FileOutputStream fileOut = new
			// FileOutputStream("D:/SalesXLS.xls");
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
			dataForSalesReport = (List<Event>) sessionMapSales.get("saledata");

			for (Event dataValue : dataForSalesReport) {

				System.out.println("inside for each loop");
				HSSFRow rowValue = worksheet.createRow(row);

				HSSFCell cell0 = rowValue.createCell(0);
				cell0.setCellValue(dataValue.getBillNo());

				HSSFCell cell1 = rowValue.createCell(1);
				cell1.setCellValue(dataValue.getBillDate());

				HSSFCell cell2 = rowValue.createCell(2);
				cell2.setCellValue(dataValue.getEventName());

				HSSFCell cell3 = rowValue.createCell(3);
				cell3.setCellValue(dataValue.getOwnerCompany());

				HSSFCell cell4 = rowValue.createCell(4);
				cell4.setCellValue(dataValue.getDebitNoteAmount());

				HSSFCell cell5 = rowValue.createCell(5);
				cell5.setCellValue(dataValue.getInvoiceAmount());

				HSSFCell cell6 = rowValue.createCell(6);
				cell6.setCellValue(dataValue.getServiceTaxInvoice());

				row++;

			}

			// workbook.write(fileOut);
			System.out.println("after for each loop of xls");
			// fileOut.flush();
			// fileOut.close();
			String fileName = "Sales_Report_XLS.xls";
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
	public void setSession(Map<String, Object> map) {

		// TODO Auto-generated method stub
		sessionMapSales = (SessionMap) map;

	}

}
