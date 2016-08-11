package com.ca.actions;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.ca.database.Database;
import com.ca.pojo.DebitNote;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.opensymphony.xwork2.ActionSupport;
import com.service.Conversion;

public class DebitPDFAction extends ActionSupport {
	DebitNote debitVar;

	public DebitPDFAction() {
		// TODO Auto-generated constructor stub
	}

	public DebitNote getDebitVar() {
		return debitVar;
	}

	public void setDebitVar(DebitNote debitVar) {
		this.debitVar = debitVar;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		HttpServletResponse response = ServletActionContext.getResponse();

		float temp = Integer
				.parseInt(debitVar.getDebitAmount().split("\\.")[0]);
		// Double d = new Double(temp);
		long n = (long) temp;
		System.out.println(n);
		// float[] columnWidths = { 2, 5};
		String docname = "Debit" + debitVar.getCompanyName().substring(0, 2)
				+ debitVar.getInvoicesNumber().substring(0, 2);
		Document document = new Document(PageSize.A4, 50, 50, 50, 50);
		Paragraph someSectionText = new Paragraph("To "
				+ debitVar.getCompanyName() + "\n" + "Debit Note No: "
				+ debitVar.getInvoicesNumber() + "\n" + "Date: "
				+ debitVar.getEventsDate());
		System.out.println("Into Action Class...");
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		PdfWriter writer = PdfWriter.getInstance(document,baos);
		PdfPTable table = new PdfPTable(2);
		table.setSpacingBefore(25);
		// table.setWidths(columnWidths);
		table.setWidthPercentage(100);
		table.setSpacingAfter(25);

		PdfPCell c1 = new PdfPCell(new Phrase("Description (Licenses Procured)"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase(" Amount "));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		table.setHeaderRows(1);
		for(int i=0;i<debitVar.getLicName().length;i++)
		{
		table.addCell("         " + debitVar.getLicName()[i] + "   ");
		table.addCell("");
		}
		table.addCell("");
		table.addCell("                                " + debitVar.getDebitAmount() + "   ");

		Paragraph amountPara = new Paragraph("\nTotal:  "
				+ debitVar.getDebitTotalAmount());
		Paragraph forCom=new Paragraph("\n For Company: "+debitVar.getForCompany());

		document.open();
		document.add(someSectionText);
		document.add(table);
		document.add(amountPara);
		document.add(forCom);
		document.close();
		System.out.println(debitVar.getEventName());
		Database database = new Database();
		Connection con = database.Get_Connection();

		PreparedStatement ps = con
				.prepareStatement("UPDATE PDFSTATUS SET D_PDF=? WHERE EVENT_NAME=? AND COMPANY_NAME=?");
		ps.setString(1, "one");
		ps.setString(2, debitVar.getEventName());
		ps.setString(3, debitVar.getCompanyName());
		ps.executeUpdate();
		System.out.println("D_PDF RECORDS UPDATED !!");
		
		ServletOutputStream outputStream = response.getOutputStream() ; 
        baos.writeTo(outputStream); 
        response.setHeader("Content-Disposition", "attachment; filename=\"DebitNote.pdf\""); 
        response.setContentType("application/pdf"); 
        outputStream.flush(); 
        outputStream.close(); 


		return "success";
	}

}
