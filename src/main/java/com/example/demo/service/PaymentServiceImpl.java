package com.example.demo.service;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.time.LocalDateTime;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.demo.dto.MessageResponse;
import com.example.demo.model.Payment;
import com.example.demo.repository.PaymentRepository;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

@Service
public class PaymentServiceImpl implements PaymentService{
	@Autowired
	private PaymentRepository paymentRepo;
	
	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public MessageResponse addPayment(int price, String email, int deliveryFee, int totalFee) {
		MessageResponse message=null;
		try {
			Payment payment=new Payment();
			payment.setEmail(email);
			payment.setDeliveryFee(deliveryFee);
			payment.setTotalFee(totalFee);
			payment.setPrice(price);
			
			paymentRepo.save(payment);
			
			sendReport(price,email,deliveryFee,totalFee);
			
			message=new MessageResponse("Payment process is successfully completed."); 
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return message;
	}
	
	public void sendReport(int price, String email, int deliveryFee, int totalFee) {
		/* first, get and initialize an engine */
		VelocityEngine ve = new VelocityEngine();

		/* next, get the Template */
		ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		ve.setProperty("classpath.resource.loader.class",
				ClasspathResourceLoader.class.getName());
		ve.init();
		Template t = ve.getTemplate("templates/home.jsp");
		/* create a context and add data */
		VelocityContext context = new VelocityContext();
		context.put("email", email);	
		context.put("orderPrice", price);	
		context.put("deliveryPrice",deliveryFee);
		context.put("totalPrice", totalFee);
		context.put("date", LocalDateTime.now().toString());
		
		/* now render the template into a StringWriter */
		StringWriter writer = new StringWriter();
		t.merge(context, writer);
		/* show the World */
		System.out.println(writer.toString());
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		baos = generatePdf(writer.toString());
	
		String filename = "C:\\Users\\ayesh\\eclipse-workspace\\EEAassignmentFinal\\src\\main\\resources\\myfile.pdf";
		try {
			FileOutputStream output = new FileOutputStream(filename);
			output.write(baos.toByteArray());
			output.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		attachReportToEmail();
		
	}
	
	public void attachReportToEmail() {
MimeMessage msg = javaMailSender.createMimeMessage();

        
        MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(msg, true);
		
        helper.setTo("domsellanaka@gmail.com");

        helper.setSubject("Testing from Spring Boot");

         //default = text/plain
        helper.setText("Check attachment for image!");

        // true = text/html
        helper.setText("<h1>Check attachment for image!</h1>", true);
         
        MimeBodyPart messageBodyPart = new MimeBodyPart();

        Multipart multipart = new MimeMultipart();
        
        String file = "C:\\Users\\ayesh\\eclipse-workspace\\EEAassignmentFinal\\src\\main\\resources\\myfile.pdf";
        String fileName = "myfile.pdf";
        DataSource source = new FileDataSource(file);
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(fileName);
        multipart.addBodyPart(messageBodyPart);
        msg.setContent(multipart);
 
        javaMailSender.send(msg);
        
        
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public ByteArrayOutputStream generatePdf(String html) {

		PdfWriter pdfWriter = null;

		// create a new document
		Document document = new Document();
		try {

			document = new Document();
			// document header attributes
			document.addAuthor("Kinns");
			document.addAuthor("Kinns123");
			document.addCreationDate();
			document.addProducer();
			document.addCreator("kinns123.github.io");
			document.addTitle("HTML to PDF using itext");
			document.setPageSize(PageSize.LETTER);

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			PdfWriter.getInstance(document, baos);

			// open document
			document.open();

			XMLWorkerHelper xmlWorkerHelper = XMLWorkerHelper.getInstance();
			xmlWorkerHelper.getDefaultCssResolver(true);
			xmlWorkerHelper.parseXHtml(pdfWriter, document, new StringReader(
					html));
			// close the document
			document.close();
			System.out.println("PDF generated successfully");

			return baos;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	

}
