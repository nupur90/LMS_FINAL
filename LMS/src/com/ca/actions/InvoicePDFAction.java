package com.ca.actions;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.ca.database.Database;
import com.ca.pojo.Invoice;
import com.ca.pojo.License;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.opensymphony.xwork2.ActionSupport;
import com.service.Conversion;

public class InvoicePDFAction extends ActionSupport {
	Invoice invoiceVar;

	public InvoicePDFAction() {
		// TODO Auto-generated constructor stub
	}

	public Invoice getInvoiceVar() {
		return invoiceVar;
	}

	public void setInvoiceVar(Invoice invoiceVar) {
		this.invoiceVar = invoiceVar;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		HttpServletResponse response = ServletActionContext.getResponse();
		float temp = Integer
				.parseInt(invoiceVar.getNetAmount().split("\\.")[0])
				+ Integer.parseInt(invoiceVar.getServiceTax().split("\\.")[0])
				+ Integer.parseInt(invoiceVar.getSbcTax().split("\\.")[0]);
		// Double d = new Double(temp);
		long n = (long) temp;
		System.out.println(n);
		float[] columnWidths = { 2, 5, 5, 5 };
		String docname = "invoice"
				+ invoiceVar.getCompanyName().substring(0, 2);
		// + invoiceVar.getInvoiceNumber().substring(0, 2);
		Document document = new Document(PageSize.A4, 50, 50, 50, 50);
		Paragraph addLicense = null;

		Paragraph someSectionText = new Paragraph(
				"Invoice: "
						+ invoiceVar.getInvoiceNumber()
						+ "                                                                 Date: "
						+ invoiceVar.getInvoiceEventsDate() + "\n\n");
		Paragraph someSectionText2 = new Paragraph("To,");
		Paragraph someSectionText3 = new Paragraph("          "
				+ invoiceVar.getCompanyName());

		System.out.println("Into Action Class...");
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		PdfWriter writer = PdfWriter.getInstance(document, baos);
		PdfPTable table = new PdfPTable(4);
		table.setSpacingBefore(25);
		table.setWidths(columnWidths);
		table.setWidthPercentage(100);
		table.setSpacingAfter(25);

		PdfPCell c1 = new PdfPCell(new Phrase("Details "));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase(" "));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Description (Licenses Procured)"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Amount Details"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
		table.setHeaderRows(1);
		for (int i = 0; i < invoiceVar.getLicName().length; i++) {
			table.addCell(" ");
			table.addCell("");
			table.addCell(invoiceVar.getLicName()[i]);
			table.addCell("");
		}
		table.addCell("Pan Number: ");
		table.addCell(invoiceVar.getPanNumber());
		table.addCell("Net Amount");
		table.addCell(invoiceVar.getNetAmount());

		table.addCell("Service Tax Number: ");
		table.addCell(invoiceVar.getServiceTaxNumber());
		table.addCell("Service Tax @ 0.14 %");
		table.addCell(invoiceVar.getServiceTax());
		table.addCell("");
		table.addCell("");
		table.addCell("Sbc Tax @0.50%");
		table.addCell(invoiceVar.getSbcTax());
		table.addCell("");
		table.addCell("");
		table.addCell("Kkc Tax @0.50%");
		table.addCell(invoiceVar.getKkcTax());
		table.addCell("");
		table.addCell("");
		table.addCell("Total Amount");
		table.addCell(invoiceVar.getInvoiceTotalAmount());

		Paragraph amountPara = new Paragraph("\nTotal amount in words is :    "
				+ Conversion.evaluate(n) + " Only.");
		Paragraph someSectionText4 = new Paragraph(
				"\nReceiver's Signature:                                                                        Proprietor: ");

		document.open();
		document.add(someSectionText);
		document.add(someSectionText2);
		document.add(someSectionText3);
		document.add(table);
		document.add(amountPara);
		document.add(someSectionText4);
		document.close();
		System.out.println(invoiceVar.getEventName());
		Database database = new Database();
		Connection con = database.Get_Connection();

		PreparedStatement ps = con
				.prepareStatement("UPDATE PDFSTATUS SET I_PDF=? WHERE EVENT_NAME=? AND COMPANY_NAME=?");
		ps.setString(1, "one");
		ps.setString(2, invoiceVar.getInvoiceNumber());
		ps.setString(3, invoiceVar.getCompanyName());
		ps.executeUpdate();
		System.out.println("I_PDF RECORDS UPDATED !!");
		ServletOutputStream outputStream = response.getOutputStream();
		baos.writeTo(outputStream);
		response.setHeader("Content-Disposition",
				"attachment; filename=\"InvoicePDF.pdf\"");
		response.setContentType("application/pdf");
		outputStream.flush();
		outputStream.close();

		return "success";
	}

}
