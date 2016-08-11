package com.ca.actions;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.html.WebColors;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPRow;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.codec.Base64.OutputStream;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class DataForGeneralReportsAction extends ActionSupport implements
		Preparable, SessionAware {
	private List<String> eventsGeneral = new ArrayList<String>();
	private List<String> companiesGeneral = new ArrayList<String>();
	private SessionMap<String, Object> sessionMapGeneral;
	List<String> eventIdList = new ArrayList<String>();
	List<String> eventNameList = new ArrayList<String>();
	List<String> eventVenueList = new ArrayList<String>();
	List<String> eventTimeList = new ArrayList<String>();
	List<String> companyNameList = new ArrayList<String>();
	List<String> totalAmountList = new ArrayList<String>();
	List<String> receivedAmountList = new ArrayList<String>();
	List<String> balanceAmountList = new ArrayList<String>();
	List<String> eventTdsList = new ArrayList<String>();
	List<String> paymentDateList = new ArrayList<String>();
	List<String> chequeDdList = new ArrayList<String>();

	private String eventGeneral = null;
	private String companyGeneral = null;
	List<Event> dataForGeneralReports;

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

	public List<String> getEventVenueList() {
		return eventVenueList;
	}

	public void setEventVenueList(List<String> eventVenueList) {
		this.eventVenueList = eventVenueList;
	}

	public List<String> getEventTimeList() {
		return eventTimeList;
	}

	public void setEventTimeList(List<String> eventTimeList) {
		this.eventTimeList = eventTimeList;
	}

	public List<String> getCompanyNameList() {
		return companyNameList;
	}

	public void setCompanyNameList(List<String> companyNameList) {
		this.companyNameList = companyNameList;
	}

	public List<String> getTotalAmountList() {
		return totalAmountList;
	}

	public void setTotalAmountList(List<String> totalAmountList) {
		this.totalAmountList = totalAmountList;
	}

	public List<String> getReceivedAmountList() {
		return receivedAmountList;
	}

	public void setReceivedAmountList(List<String> receivedAmountList) {
		this.receivedAmountList = receivedAmountList;
	}

	public List<String> getBalanceAmountList() {
		return balanceAmountList;
	}

	public void setBalanceAmountList(List<String> balanceAmountList) {
		this.balanceAmountList = balanceAmountList;
	}

	public List<String> getEventTdsList() {
		return eventTdsList;
	}

	public void setEventTdsList(List<String> eventTdsList) {
		this.eventTdsList = eventTdsList;
	}

	public List<String> getPaymentDateList() {
		return paymentDateList;
	}

	public void setPaymentDateList(List<String> paymentDateList) {
		this.paymentDateList = paymentDateList;
	}

	public List<String> getChequeDdList() {
		return chequeDdList;
	}

	public void setChequeDdList(List<String> chequeDdList) {
		this.chequeDdList = chequeDdList;
	}

	public SessionMap<String, Object> getSessionMapGeneral() {
		return sessionMapGeneral;
	}

	public void setSessionMapGeneral(
			SessionMap<String, Object> sessionMapGeneral) {
		this.sessionMapGeneral = sessionMapGeneral;
	}

	public String getEventGeneral() {
		return eventGeneral;
	}

	public void setEventGeneral(String eventGeneral) {
		this.eventGeneral = eventGeneral;
	}

	public String getCompanyGeneral() {
		return companyGeneral;
	}

	public void setCompanyGeneral(String companyGeneral) {
		this.companyGeneral = companyGeneral;
	}

	public List<Event> getDataForGeneralReports() {
		return dataForGeneralReports;
	}

	public void setDataForGeneralReports(List<Event> dataForGeneralReports) {
		this.dataForGeneralReports = dataForGeneralReports;
	}

	public List<String> getEventsGeneral() {
		return eventsGeneral;
	}

	public void setEventsGeneral(List<String> eventsGeneral) {
		this.eventsGeneral = eventsGeneral;
	}

	public List<String> getCompaniesGeneral() {
		return companiesGeneral;
	}

	public void setCompaniesGeneral(List<String> companiesGeneral) {
		this.companiesGeneral = companiesGeneral;
	}

	public DataForGeneralReportsAction() {
		// TODO Auto-generated constructor stub
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
				companiesGeneral.add(rs.getString("company_name"));
			}

			// load events
			ps = con.prepareStatement("SELECT DISTINCT event_name FROM event");
			rs = ps.executeQuery();
			while (rs.next()) {
				eventsGeneral.add(rs.getString("event_name"));
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
			String sql = "SELECT EVENT_ID, EVENT_NAME, COMPANY_NAME,EVENT_VENUE,TOTAL_AMOUNT,RECEIVED_AMOUNT,EVENT_TDS,BALANCE_AMOUNT,CHEQUE_DD_NO,"
					+ "date_format(PAYMENT_DATE,'%d/%m/%Y') as dateAsPayment,EVENT_TIME "
					+ "FROM event";
			String where = "";

			System.out.println(eventGeneral + "eventgeneral of execute");
			// if instead this action has been called from the JSP page,
			// the result is filtered on event and company:
			if (eventGeneral != null && companyGeneral != null) {
				where = " WHERE event_name = ? AND company_name = ?";
			}

			// load companies
			PreparedStatement ps = con.prepareStatement(sql + where);
			if (where.length() > 0) {
				ps.setString(1, eventGeneral);
				ps.setString(2, companyGeneral);
			}
			dataForGeneralReports = new ArrayList<Event>();
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				dataForGeneralReports.add(new Event(rs.getString("EVENT_ID"),
						rs.getString("EVENT_NAME"), rs
								.getString("COMPANY_NAME"), rs
								.getString("EVENT_VENUE"), rs
								.getString("EVENT_TIME"), rs
								.getString("TOTAL_AMOUNT"), rs
								.getString("RECEIVED_AMOUNT"), rs
								.getString("CHEQUE_DD_NO"), rs
								.getString("dateAsPayment"), rs
								.getString("BALANCE_AMOUNT"), rs
								.getString("EVENT_TDS")));

				eventIdList.add(rs.getString("EVENT_ID"));
				eventNameList.add(rs.getString("EVENT_NAME"));
				companyNameList.add(rs.getString("COMPANY_NAME"));
				eventVenueList.add(rs.getString("EVENT_VENUE"));
				eventTimeList.add(rs.getString("EVENT_TIME"));
				totalAmountList.add(rs.getString("TOTAL_AMOUNT"));
				receivedAmountList.add(rs.getString("RECEIVED_AMOUNT"));
				chequeDdList.add(rs.getString("CHEQUE_DD_NO"));
				paymentDateList.add(rs.getString("dateAsPayment"));
				eventTdsList.add(rs.getString("EVENT_TDS"));
				balanceAmountList.add(rs.getString("BALANCE_AMOUNT"));

			}
			sessionMapGeneral.put("dataforgeneral", dataForGeneralReports);
			sessionMapGeneral.put("eventIdPdf", eventIdList);
			sessionMapGeneral.put("eventNamePdf", eventNameList);
			sessionMapGeneral.put("companyNamePdf", companyNameList);
			sessionMapGeneral.put("eventVenuePdf", eventVenueList);
			sessionMapGeneral.put("eventTimePdf", eventTimeList);
			sessionMapGeneral.put("totalAmountPdf", totalAmountList);
			sessionMapGeneral.put("receivedAmountPdf", receivedAmountList);
			sessionMapGeneral.put("chequeDdPdf", chequeDdList);
			sessionMapGeneral.put("paymentDatePdf", paymentDateList);
			sessionMapGeneral.put("eventTdsPdf", eventTdsList);
			sessionMapGeneral.put("balanceAmountPdf", balanceAmountList);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
		}

		return SUCCESS;

	}

	public String generatePdfGeneral() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();

		System.out.println(sessionMapGeneral.get("eventIdPdf"));
		Document document = new Document(PageSize.A4, 0f, 0f, 0f, 0f);
		float[] columnWidths = { 4, 5, 5, 5, 6, 5, 6, 5, 6, 5, 5 };
		
		//two lines for pdf at client side
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
        
		PdfWriter writer = PdfWriter.getInstance(document,baos);
		//two lines for pdf at client side
		PdfPTable table = new PdfPTable(11);
		// 11 indicates no . of columns.
		table.setSpacingBefore(10);
		table.setWidthPercentage(100.0f);
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

		c1 = new PdfPCell(new Phrase("Event Time", f));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(myColor);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Event Venue", f));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(myColor);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Company Name", f));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(myColor);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Total Amount", f));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(myColor);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Received Amount", f));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(myColor);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Cheque/DD Number", f));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(myColor);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Payment Date", f));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(myColor);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Event TDS", f));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(myColor);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Balance Amount", f));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(myColor);
		table.addCell(c1);

		table.setHeaderRows(1);

		dataForGeneralReports = (List<Event>) sessionMapGeneral
				.get("dataforgeneral");

		for (Event dataValue : dataForGeneralReports) {

			String eventId = dataValue.getEventId();
			String eventName = dataValue.getEventName();

			String eventTime = dataValue.getEventTime();

			String eventVenue = dataValue.getEventVenue();

			String companyName = dataValue.getCompanyName();

			String totalAmount = dataValue.getTotalAmount();

			String receivedAmount = dataValue.getReceivedAmount();

			String paymentDate = dataValue.getPaymentDate();

			String chequeDd = dataValue.getChequeDd();

			String eventTds = dataValue.getEventTds();

			String balanceAmount = dataValue.getBalanceAmount();

			table.addCell(eventId);
			table.addCell(eventName);
			table.addCell(eventTime);
			table.addCell(eventVenue);
			table.addCell(companyName);
			table.addCell(totalAmount);
			table.addCell(receivedAmount);
			table.addCell(chequeDd);
			table.addCell(paymentDate);
			table.addCell(eventTds);
			table.addCell(balanceAmount);

		}

		table.completeRow();

		writer.close();
		document.open();

		document.add(table);

		document.close();
		
		//download pdf at client side
		
		ServletOutputStream outputStream = response.getOutputStream() ; 
        baos.writeTo(outputStream); 
        response.setHeader("Content-Disposition", "attachment; filename=\"GeneralReport.pdf\""); 
        response.setContentType("application/pdf"); 
        outputStream.flush(); 
        outputStream.close(); 
		
		return "success";

	}

	public String generateGeneralXls() throws Exception {
		try {

			System.out.println("insoide generate xls");

			String titles = "Event ID,Event Name,Event Time,Event Venue,Company Name,Total Amount,Received Amount,Payment Date,Cheque/DD No,Event TDS,Balance Amount";
			String[] arrTiltes = titles.split(",");

			//FileOutputStream fileOut = new FileOutputStream("D:/GeneralXLS.xls");
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
			dataForGeneralReports = (List<Event>) sessionMapGeneral
					.get("dataforgeneral");

			for (Event dataValue : dataForGeneralReports) {

				System.out.println("inside for each loop");
				HSSFRow rowValue = worksheet.createRow(row);

				HSSFCell cell0 = rowValue.createCell(0);
				cell0.setCellValue(dataValue.getEventId());

				HSSFCell cell1 = rowValue.createCell(1);
				cell1.setCellValue(dataValue.getEventName());

				HSSFCell cell2 = rowValue.createCell(2);
				cell2.setCellValue(dataValue.getEventTime());

				HSSFCell cell3 = rowValue.createCell(3);
				cell3.setCellValue(dataValue.getEventVenue());

				HSSFCell cell4 = rowValue.createCell(4);
				cell4.setCellValue(dataValue.getCompanyName());

				HSSFCell cell5 = rowValue.createCell(5);
				cell5.setCellValue(dataValue.getTotalAmount());

				HSSFCell cell6 = rowValue.createCell(6);
				cell6.setCellValue(dataValue.getReceivedAmount());

				HSSFCell cell7 = rowValue.createCell(7);
				cell7.setCellValue(dataValue.getPaymentDate());

				HSSFCell cell8 = rowValue.createCell(8);
				cell8.setCellValue(dataValue.getChequeDd());

				HSSFCell cell9 = rowValue.createCell(9);
				cell9.setCellValue(dataValue.getEventTds());

				HSSFCell cell10 = rowValue.createCell(10);
				cell10.setCellValue(dataValue.getBalanceAmount());

				row++;

			}

			//workbook.write(fileOut);
			System.out.println("after for each loop of xls");
			//fileOut.flush();
			//fileOut.close();
			//Code for downloading xls in client machine
			
			String fileName = "General_Report_XLS.xls";
			HttpServletResponse response = ServletActionContext.getResponse();
			
			response.setContentType("application/vnd.ms-excel"); // Set up mime type
			response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
			ServletOutputStream out = response.getOutputStream();
			workbook.write(out);
			out.flush();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return NONE;
	}

	@Override
	public void setSession(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sessionMapGeneral = (SessionMap) map;
	}

}
