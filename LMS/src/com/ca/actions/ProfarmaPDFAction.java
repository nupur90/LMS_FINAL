package com.ca.actions;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.ca.database.Database;
import com.ca.pojo.Profarma;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPRow;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.opensymphony.xwork2.ActionSupport;
import com.service.Conversion;

public class ProfarmaPDFAction extends ActionSupport {
	Profarma profarmaPdf;
	static String resultString = "";

	public ProfarmaPDFAction() {
		// TODO Auto-generated constructor stub
	}

	public Profarma getProfarmaPdf() {
		return profarmaPdf;
	}

	public void setProfarmaPdf(Profarma profarmaPdf) {
		this.profarmaPdf = profarmaPdf;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		HttpServletResponse response = ServletActionContext.getResponse();

		float temp = Integer
				.parseInt(profarmaPdf.getNetAmount().split("\\.")[0])
				+ Integer.parseInt(profarmaPdf.getServiceTax().split("\\.")[0])
				+ Integer.parseInt(profarmaPdf.getSbcTax().split("\\.")[0]);
		// Double d = new Double(temp);
		long n = (long) temp;
		System.out.println(n);
		float[] columnWidths = { 2, 5, 5, 5 };
		System.out.println(profarmaPdf.getInvoiceNumber() + " "
				+ profarmaPdf.getToCompanyName() + " "
				+ profarmaPdf.getServiceTax() + " " + profarmaPdf.getSbcTax());

		String docname = profarmaPdf.getToCompanyName().substring(0, 2)
				+ profarmaPdf.getInvoiceNumber().substring(0, 2);
		Document document = new Document(PageSize.A4, 50, 50, 50, 50);
		Paragraph someSectionText = new Paragraph(
				"Invoice: "
						+ profarmaPdf.getInvoiceNumber()
						+ "                                                                 Date: "
						+ profarmaPdf.getEventsDate() + "\n\n");
		Paragraph someSectionText2 = new Paragraph("To,");
		Paragraph someSectionText3 = new Paragraph("          "
				+ profarmaPdf.getToCompanyName());

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

		c1 = new PdfPCell(new Phrase("Description"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Amount Details"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
		table.setHeaderRows(1);
		System.out.println("Length " + profarmaPdf.getLicProName().length);
		for (int i = 0; i < profarmaPdf.getLicProName().length; i++) {
			table.addCell("");
			table.addCell("");
			table.addCell(profarmaPdf.getLicProName()[i]);
			table.addCell("");

		}

		table.addCell("Pan Number: ");
		table.addCell(profarmaPdf.getPanNumber());
		table.addCell("Net Amount");
		table.addCell(profarmaPdf.getNetAmount());
		table.addCell("Service Tax Number: ");
		table.addCell(profarmaPdf.getServiceTaxNumber());
		table.addCell("Service Tax @ 0.14 %");
		table.addCell(profarmaPdf.getServiceTax());
		table.addCell("");
		table.addCell("");
		table.addCell("Sbc Tax @0.50%");
		table.addCell(profarmaPdf.getSbcTax());
		table.addCell("");
		table.addCell("");
		table.addCell("Kkc Tax @0.50%");
		table.addCell(profarmaPdf.getKkcTax());
		table.addCell("");
		table.addCell("");
		table.addCell("Total Amount");
		table.addCell(profarmaPdf.getTotalAmount());
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
		System.out.println(profarmaPdf.getEventName());
		System.out.println(profarmaPdf.getToCompanyName());

		Database database = new Database();

		System.out.println("After dtabase line");
		Connection con = database.Get_Connection();
		System.out.println("After connection line");
		PreparedStatement ps = con
				.prepareStatement("UPDATE PDFSTATUS SET P_PDF=? WHERE EVENT_NAME=? AND COMPANY_NAME=?");
		System.out.println("after prepared line");
		ps.setString(1, "one");
		ps.setString(2, profarmaPdf.getInvoiceNumber());
		ps.setString(3, profarmaPdf.getToCompanyName());
		ps.executeUpdate();
		System.out.println(ps.executeUpdate());
		System.out.println("P_PDF RECORDS UPDATED !!");
		ServletOutputStream outputStream = response.getOutputStream();
		baos.writeTo(outputStream);
		response.setHeader("Content-Disposition",
				"attachment; filename=\"ProformaPDF.pdf\"");
		response.setContentType("application/pdf");
		outputStream.flush();
		outputStream.close();

		return "success";

	}
}
