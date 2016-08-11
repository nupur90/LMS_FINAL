package com.ca.actions;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ca.database.Database;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class GeneratePdfGeneralAction extends ActionSupport implements
		Preparable {

	private List<String> eventsGeneral = new ArrayList<String>();
	private List<String> companiesGeneral = new ArrayList<String>();

	private String eventGeneral = null;
	private String companyGeneral = null;

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

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub

		Connection con = null;
		try {
			con = new Database().Get_Connection();

			// load companies
			PreparedStatement ps = con
					.prepareStatement("SELECT DISTINCT company_name FROM event");
			
			System.out.println("Inside generate pdf general action!!");
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
		Document document = new Document(PageSize.A4, 0f, 0f, 0f, 0f);
		float[] columnWidths = { 4, 5, 5, 5, 6, 5, 6, 5, 6, 5, 5 };

		PdfWriter writer = PdfWriter.getInstance(document,
				new FileOutputStream("D:\\GeneralReports.pdf"));
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

		Connection con = null;
		try {
			con = new Database().Get_Connection();

			// load the table. The first time the table is loaded completely
			String sql = "SELECT EVENT_ID, EVENT_NAME, COMPANY_NAME,EVENT_VENUE,TOTAL_AMOUNT,RECEIVED_AMOUNT,EVENT_TDS,BALANCE_AMOUNT,CHEQUE_DD_NO,"
					+ "date_format(PAYMENT_DATE,'%d/%m/%Y') as dateAsPayment,EVENT_TIME "
					+ "FROM event";
			String where = "";

			System.out.println("after sql!!!!!!!!");
			
			// if instead this action has been called from the JSP page,
			// the result is filtered on event and company:
			if (eventGeneral != null && companyGeneral != null) {
				where = " WHERE event_name = ? AND company_name = ?";

				System.out
						.println("Inside if of pdf method for where condition");
			}

			System.out.println("after if of generatepdfgeneralaction");
			
			// load companies
			PreparedStatement ps = con.prepareStatement(sql + where);
			if (where.length() > 0) {
				ps.setString(1, eventGeneral);
				ps.setString(2, companyGeneral);
			}

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				String eventId = rs.getString("EVENT_ID");
				String eventName = rs.getString("EVENT_NAME");
				String eventTime = rs.getString("EVENT_TIME");
				String eventVenue = rs.getString("EVENT_VENUE");
				String companyName = rs.getString("COMPANY_NAME");
				String totalAmount = rs.getString("TOTAL_AMOUNT");
				String receivedAmount = rs.getString("RECEIVED_AMOUNT");
				String chequeDdNumber = rs.getString("CHEQUE_DD_NO");
				String paymentDate = rs.getString("dateAsPayment");
				String eventTds = rs.getString("EVENT_TDS");
				String balanceAmount = rs.getString("BALANCE_AMOUNT");

				table.addCell(eventId);
				table.addCell(eventName);
				table.addCell(eventTime);
				table.addCell(eventVenue);
				table.addCell(companyName);
				table.addCell(totalAmount);
				table.addCell(receivedAmount);
				table.addCell(chequeDdNumber);
				table.addCell(paymentDate);
				table.addCell(eventTds);
				table.addCell(balanceAmount);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
		}

		table.completeRow();

		writer.close();
		document.open();

		document.add(table);

		document.close();
		return "success";

	}

}
