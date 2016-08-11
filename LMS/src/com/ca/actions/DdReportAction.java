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
import com.itextpdf.text.Chunk;
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

public class DdReportAction extends ActionSupport implements Preparable,
		SessionAware {

	private List<License> dataForDdReport = new ArrayList<License>();

	private SessionMap<String, Object> sessionMapDd;

	private List<String> eventDdList = new ArrayList<String>();
	private List<String> companyDdList = new ArrayList<String>();

	List<String> eventIdList = new ArrayList<String>();
	List<String> eventNameList = new ArrayList<String>();
	List<String> companyNameList = new ArrayList<String>();
	List<String> ownerCompanyList = new ArrayList<String>();
	List<String> licenseNameList = new ArrayList<String>();
	List<String> ddNumberList = new ArrayList<String>();
	List<String> ddAmountList = new ArrayList<String>();
	List<String> bankNameList = new ArrayList<String>();
	List<String> ddDateList = new ArrayList<String>();

	private String eventDd = null;
	private String ownerCompany = null;

	public List<License> getDataForDdReport() {
		return dataForDdReport;
	}

	public void setDataForDdReport(List<License> dataForDdReport) {
		this.dataForDdReport = dataForDdReport;
	}

	public List<String> getEventDdList() {
		return eventDdList;
	}

	public void setEventDdList(List<String> eventDdList) {
		this.eventDdList = eventDdList;
	}

	public List<String> getCompanyDdList() {
		return companyDdList;
	}

	public void setCompanyDdList(List<String> companyDdList) {
		this.companyDdList = companyDdList;
	}

	public String getEventDd() {
		return eventDd;
	}

	public void setEventDd(String eventDd) {
		this.eventDd = eventDd;
	}

	public String getOwnerCompany() {
		return ownerCompany;
	}

	public void setOwnerCompany(String ownerCompany) {
		this.ownerCompany = ownerCompany;
	}

	public DdReportAction() {
		// TODO Auto-generated constructor stub
	}

	public SessionMap<String, Object> getSessionMapDd() {
		return sessionMapDd;
	}

	public void setSessionMapDd(SessionMap<String, Object> sessionMapDd) {
		this.sessionMapDd = sessionMapDd;
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

	public List<String> getCompanyNameList() {
		return companyNameList;
	}

	public void setCompanyNameList(List<String> companyNameList) {
		this.companyNameList = companyNameList;
	}

	public List<String> getOwnerCompanyList() {
		return ownerCompanyList;
	}

	public void setOwnerCompanyList(List<String> ownerCompanyList) {
		this.ownerCompanyList = ownerCompanyList;
	}

	public List<String> getLicenseNameList() {
		return licenseNameList;
	}

	public void setLicenseNameList(List<String> licenseNameList) {
		this.licenseNameList = licenseNameList;
	}

	public List<String> getDdNumberList() {
		return ddNumberList;
	}

	public void setDdNumberList(List<String> ddNumberList) {
		this.ddNumberList = ddNumberList;
	}

	public List<String> getDdAmountList() {
		return ddAmountList;
	}

	public void setDdAmountList(List<String> ddAmountList) {
		this.ddAmountList = ddAmountList;
	}

	public List<String> getBankNameList() {
		return bankNameList;
	}

	public void setBankNameList(List<String> bankNameList) {
		this.bankNameList = bankNameList;
	}

	public List<String> getDdDateList() {
		return ddDateList;
	}

	public void setDdDateList(List<String> ddDateList) {
		this.ddDateList = ddDateList;
	}

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Connection con = null;
		try {
			con = new Database().Get_Connection();

			// load companies
			PreparedStatement ps = con
					.prepareStatement("SELECT DISTINCT OWNER_COMPANY FROM EVENT");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				companyDdList.add(rs.getString("OWNER_COMPANY"));
			}

			// load events
			ps = con.prepareStatement("SELECT DISTINCT event_name FROM event");
			rs = ps.executeQuery();
			while (rs.next()) {
				eventDdList.add(rs.getString("event_name"));
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
			String sql = "SELECT EVENT_ID,EVENT_NAME,COMPANY_NAME,OWNER_COMPANY,LICENSE_NAME,DD_NUMBER,date_format(DD_DATE,'%d/%m/%Y') as dateForDd,DD_AMOUNT,BANK_NAME FROM license";

			String where = "";

			// if instead this action has been called from the JSP page,
			// the result is filtered on event and company:
			if (eventDd != null && ownerCompany != null) {
				where = " WHERE event_name = ? AND owner_company = ?";
			}

			// load companies
			PreparedStatement ps = con.prepareStatement(sql + where);
			if (where.length() > 0) {
				ps.setString(1, eventDd);
				ps.setString(2, ownerCompany);
				System.out.println(ownerCompany);
			}
			System.out.println(sql + where);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				dataForDdReport.add(new License(rs.getString("EVENT_ID"), rs
						.getString("EVENT_NAME"), rs.getString("COMPANY_NAME"),
						rs.getString("OWNER_COMPANY"), rs
								.getString("LICENSE_NAME"), rs
								.getString("DD_NUMBER"), rs
								.getString("dateForDd"), rs
								.getString("DD_AMOUNT"), rs
								.getString("BANK_NAME")));

				eventIdList.add(rs.getString("EVENT_ID"));
				eventNameList.add(rs.getString("EVENT_NAME"));
				companyNameList.add(rs.getString("COMPANY_NAME"));
				ownerCompanyList.add(rs.getString("OWNER_COMPANY"));
				licenseNameList.add(rs.getString("LICENSE_NAME"));
				ddNumberList.add(rs.getString("DD_NUMBER"));
				ddAmountList.add(rs.getString("DD_AMOUNT"));
				bankNameList.add(rs.getString("BANK_NAME"));
				ddDateList.add(rs.getString("dateForDd"));

			}
			sessionMapDd.put("ddData", dataForDdReport);
			sessionMapDd.put("eventIdPdf", eventIdList);
			sessionMapDd.put("eventNamePdf", eventNameList);
			sessionMapDd.put("companyNamePdf", companyNameList);
			sessionMapDd.put("ownerCompanyPdf", ownerCompanyList);
			sessionMapDd.put("licenseNamePdf", licenseNameList);
			sessionMapDd.put("ddNumberPdf", ddNumberList);
			sessionMapDd.put("ddAmountPdf", ddAmountList);
			sessionMapDd.put("bankNamePdf", bankNameList);
			sessionMapDd.put("ddDatePdf", ddDateList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	public String generatePdfDd() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();

		Document document = new Document(PageSize.A4, 0f, 0f, 0f, 0f);
		float[] columnWidths = { 5, 6, 6, 6, 6, 6, 6, 6, 6 };
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		PdfWriter writer = PdfWriter.getInstance(document, baos);
		PdfPTable table = new PdfPTable(9);
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

		c1 = new PdfPCell(new Phrase("Company Name Of Event", f));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(myColor);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Company Name", f));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(myColor);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("License Name", f));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(myColor);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("DD Amount", f));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(myColor);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("DD Date", f));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(myColor);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("DD Number", f));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(myColor);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("DD Bank Name", f));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(myColor);
		table.addCell(c1);

		table.setHeaderRows(1);

		dataForDdReport = (List<License>) sessionMapDd.get("ddData");

		for (License dataValue : dataForDdReport) {

			String eventId = dataValue.getEventId();

			String eventName = dataValue.getEventName();

			String companyName = dataValue.getCompanyName();

			String ownerCompany = dataValue.getOwnerCompany();

			String licenseName = dataValue.getLicenseName();

			String ddAmount = dataValue.getDdAmount();

			String ddDate = dataValue.getDdDate();

			String ddNumber = dataValue.getDdNumber();

			String bankName = dataValue.getBankName();

			table.addCell(eventId);
			table.addCell(eventName);
			table.addCell(companyName);
			table.addCell(ownerCompany);
			table.addCell(licenseName);
			table.addCell(ddAmount);
			table.addCell(ddDate);
			table.addCell(ddNumber);
			table.addCell(bankName);

		}

		table.completeRow();

		writer.close();
		document.open();

		document.add(table);

		document.close();
		ServletOutputStream outputStream = response.getOutputStream() ; 
        baos.writeTo(outputStream); 
        response.setHeader("Content-Disposition", "attachment; filename=\"DdReport.pdf\""); 
        response.setContentType("application/pdf"); 
        outputStream.flush(); 
        outputStream.close(); 
	
		return "success";
	}

	public String generateDdXls() throws Exception {
		try {

			System.out.println("insoide generate xls");

			String titles = "Event ID , Event Name , Company Name Of Event , Company Name , License Name , DD Amount , DD Date , DD Number , DD Bank Name";
			String[] arrTiltes = titles.split(",");

		//	FileOutputStream fileOut = new FileOutputStream("D:/DdXLS.xls");
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
			dataForDdReport = (List<License>) sessionMapDd.get("ddData");

			for (License dataValue : dataForDdReport) {

				System.out.println("inside for each loop");
				HSSFRow rowValue = worksheet.createRow(row);

				HSSFCell cell0 = rowValue.createCell(0);
				cell0.setCellValue(dataValue.getEventId());

				HSSFCell cell1 = rowValue.createCell(1);
				cell1.setCellValue(dataValue.getEventName());

				HSSFCell cell2 = rowValue.createCell(2);
				cell2.setCellValue(dataValue.getCompanyName());

				HSSFCell cell3 = rowValue.createCell(3);
				cell3.setCellValue(dataValue.getOwnerCompany());

				HSSFCell cell4 = rowValue.createCell(4);
				cell4.setCellValue(dataValue.getLicenseName());

				HSSFCell cell5 = rowValue.createCell(5);
				cell5.setCellValue(dataValue.getDdAmount());

				HSSFCell cell6 = rowValue.createCell(6);
				cell6.setCellValue(dataValue.getDdDate());

				HSSFCell cell7 = rowValue.createCell(7);
				cell7.setCellValue(dataValue.getDdNumber());

				HSSFCell cell8 = rowValue.createCell(8);
				cell8.setCellValue(dataValue.getBankName());

				row++;

			}

		//	workbook.write(fileOut);
			System.out.println("after for each loop of xls");
		//	fileOut.flush();
		//	fileOut.close();

			String fileName = "Dd_Report_XLS.xls";
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
		sessionMapDd = (SessionMap) arg0;
	}

}
