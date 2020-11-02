package com.mvc.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

/**
 * Servlet implementation class MailInvoiceController
 */
public class MailInvoiceController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MailInvoiceController() {
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		String email = "";
		String name = "";
		int orderid = -1;

		PrintWriter writer = response.getWriter();

		// Checking if user is logged in or not
		HttpSession session = request.getSession(false);
		if (session == null) {
			response.sendRedirect("login.jsp");
		} else {
			//Getting the parameters from the form
			orderid = Integer.parseInt(request.getParameter("orderid"));
			name = request.getParameter("name");
			email = request.getParameter("email");
			String phoneno = request.getParameter("phoneno");
			String products = request.getParameter("products");
			int quantity = Integer.parseInt(request.getParameter("quantity"));
			int totalcost = Integer.parseInt(request.getParameter("totalcost"));
			int paid = Integer.parseInt(request.getParameter("paid"));
			int due = Integer.parseInt(request.getParameter("due"));

			try {
				Document doc = new Document();
				
				//invoicegenerated.pdf is the file name, we are using iTextPDF to generate pdf
				PdfWriter.getInstance(doc, new FileOutputStream("D://invoicegenerated.pdf"));
				doc.open();
				Paragraph p = new Paragraph();
				p.setAlignment(Element.ALIGN_CENTER);
				p.add("Invoice for your purchase");
				doc.add(p);
				doc.add(Chunk.NEWLINE);
				p = new Paragraph();
				p.setAlignment(Element.ALIGN_LEFT);
				p.add("Sarvesh pvt. ltd.");
				doc.add(p);

				p = new Paragraph();
				p.setAlignment(Element.ALIGN_RIGHT);
				p.add("Name: " + name);
				doc.add(p);
				doc.add(Chunk.NEWLINE);
				p = new Paragraph();
				p.setAlignment(Element.ALIGN_RIGHT);
				p.add("EMail: " + email);
				doc.add(p);
				doc.add(Chunk.NEWLINE);
				p = new Paragraph();
				p.setAlignment(Element.ALIGN_RIGHT);
				p.add("Phone No.: " + phoneno);
				doc.add(p);
				doc.add(Chunk.NEWLINE);

				PdfPTable t = new PdfPTable(6);
				t.setSpacingBefore(15);
				t.setSpacingAfter(15);
				PdfPCell cell0 = new PdfPCell(new Phrase("ORDER ID"));
				t.addCell(cell0);
				PdfPCell cell1 = new PdfPCell(new Phrase("PRODUCTS"));
				t.addCell(cell1);
				PdfPCell cell2 = new PdfPCell(new Phrase("QUANTITY"));
				t.addCell(cell2);
				PdfPCell cell3 = new PdfPCell(new Phrase("TOTAL COST"));
				t.addCell(cell3);
				PdfPCell cell4 = new PdfPCell(new Phrase("PAID"));
				t.addCell(cell4);
				PdfPCell cell5 = new PdfPCell(new Phrase("DUE"));
				t.addCell(cell5);

				t.addCell(String.valueOf(orderid));
				t.addCell(products);
				t.addCell(String.valueOf(quantity));
				t.addCell(String.valueOf(totalcost));
				t.addCell(String.valueOf(paid));
				t.addCell(String.valueOf(due));
				doc.add(t);

				Image img = Image.getInstance("C:\\Users\\sarvesh\\Desktop\\Virtusa\\sign.jpg");
				img.scaleAbsolute(32, 32);
				doc.add(img);

				doc.close();
				System.out.println("Done!!!");

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		//Sending the generated pdf as a email to customer using javax mail
		String to = email;
		String from = "sarvesh1challawar@gmail.com";
		String pass = "iamI1234";

		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.port", 587);
		properties.put("mail.smtp.starttls.enable", "true");

		Session session1 = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, pass);
			}
		});

		try {
			MimeMessage message = new MimeMessage(session1);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject("Invoice for your order");

			BodyPart messagePart1 = new MimeBodyPart();
			messagePart1.setText("Dear " + name + " , PFA the invoice of your purchase from our shop.Regards.");

			MimeBodyPart messagePart2 = new MimeBodyPart();
			DataSource source = new FileDataSource("D:\\invoicegenerated.pdf");
			messagePart2.setDataHandler(new DataHandler(source));
			messagePart2.setFileName("invoice.pdf");

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messagePart1);
			multipart.addBodyPart(messagePart2);

			message.setContent(multipart);

			Transport.send(message);
			System.out.println("Message sent!!");

		} catch (Exception e) {
			e.printStackTrace();
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("customers.jsp");
		request.setAttribute("EmailSent", "The invoice has been sent to the customer");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
